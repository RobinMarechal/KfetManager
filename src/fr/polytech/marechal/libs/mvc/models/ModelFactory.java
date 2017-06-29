package fr.polytech.marechal.libs.mvc.models;

import fr.polytech.marechal.libs.Helpers;
import fr.polytech.marechal.libs.api.ApiQuery;
import fr.polytech.marechal.libs.api.ApiQueryBuilder;
import fr.polytech.marechal.libs.api.UrlParametersMap;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * @author Robin
 * @date 15/06/2017
 */
public abstract class ModelFactory<T extends Model>
{
    public T find (int id, UrlParametersMap parametersMap)
    {
        try
        {
            JSONArray array = ApiQueryBuilder.create(getBaseUrl() + "/" + id)
                                             .setUrlParams(parametersMap)
                                             .getQuery()
                                             .execute()
                                             .getJson();

            if (array != null && !((JSONObject) array.get(0)).isEmpty())
            {
                return buildFromJson((JSONObject) array.get(0));
            }

            return null;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public T find (int id)
    {
        return find(id, new UrlParametersMap());
    }

    public ArrayList<T> all (@NotNull UrlParametersMap parametersMap)
    {
        try
        {
            ArrayList<T> list = new ArrayList<>();

            ApiQuery q = ApiQueryBuilder.create(getBaseUrl())
                                        .setUrlParams(parametersMap)
                                        .getQuery();

            System.out.println(q);

            JSONArray array = q.execute()
                               .getJson();

            array.forEach(jsonObject ->
            {
                try
                {
                    list.add(buildFromJson((JSONObject) jsonObject));
                }
                catch (ReflectiveOperationException e)
                {
                    e.printStackTrace();
                }
            });

            return list;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<T> all ()
    {
        return all(new UrlParametersMap());
    }

    public T buildFromJson (JSONObject json) throws ReflectiveOperationException
    {
        Class<T> clazz = getModelInstanceClass();

        T instance = clazz.newInstance();

        for (Object item : json.keySet())
        {
            String key   = item.toString();
            Object value = json.get(key);

            if (value == null || key.equals("pivot"))
            {
                continue;
            }

            if(key.equals("id"))
            {
                instance.setId(Integer.valueOf(value.toString()).intValue());
                continue;
            }

            String camelCaseKey = Helpers.snakeCaseToCamelCase(key);
            Field  field        = clazz.getDeclaredField(camelCaseKey);

            Method setter;
            String setterName = "set" + camelCaseKey.substring(0, 1)
                                                    .toUpperCase() + camelCaseKey.substring(1);

            String fieldClassName = field.getType()
                                         .getTypeName();

            if (fieldClassName.equals("int"))
            {
                int v = Integer.valueOf(value.toString());
                setter = clazz.getDeclaredMethod(setterName, int.class);
                setter.invoke(instance, v);
            }
            else if (fieldClassName.equals("long"))
            {
                long v = Long.valueOf(value.toString());
                setter = clazz.getDeclaredMethod(setterName, long.class);
                setter.invoke(instance, v);
            }
            else if (fieldClassName.equals("float"))
            {
                float v = Float.valueOf(value.toString());
                setter = clazz.getDeclaredMethod(setterName, float.class);
                setter.invoke(instance, v);
            }
            else if (fieldClassName.equals("short"))
            {
                short v = Short.valueOf(value.toString());
                setter = clazz.getDeclaredMethod(setterName, short.class);
                setter.invoke(instance, v);
            }
            else if (fieldClassName.equals("double"))
            {
                double v = Double.valueOf(value.toString());
                setter = clazz.getDeclaredMethod(setterName, double.class);
                setter.invoke(instance, v);
            }
            else if (fieldClassName.equals("boolean"))
            {
                boolean v = Boolean.valueOf(value.toString());
                setter = clazz.getDeclaredMethod(setterName, boolean.class);
                setter.invoke(instance, v);
            }
            else if (fieldClassName.equals("byte"))
            {
                byte v = Byte.valueOf(value.toString());
                setter = clazz.getDeclaredMethod(setterName, byte.class);
                setter.invoke(instance, v);
            }
            else if (fieldClassName.equals("char"))
            {
                char v = Character.valueOf(value.toString()
                                                .charAt(0));
                setter = clazz.getDeclaredMethod(setterName, char.class);
                setter.invoke(instance, v);
            }
            else if (fieldClassName.equals(String.class.getName()))
            {
                setter = clazz.getDeclaredMethod(setterName, String.class);
                setter.invoke(instance, value.toString());
            }
            else if (fieldClassName.equals(LocalDate.class.getName()))
            {
                String    strDate = value.toString();
                LocalDate date    = LocalDate.parse(strDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                setter = clazz.getDeclaredMethod(setterName, LocalDate.class);
                setter.invoke(instance, date);
            }
            else if (fieldClassName.equals(LocalTime.class.getName()))
            {
                String    strTime = value.toString();
                LocalTime time    = LocalTime.parse(strTime, DateTimeFormatter.ofPattern("HH:mm"));

                setter = clazz.getDeclaredMethod(setterName, LocalTime.class);
                setter.invoke(instance, time);
            }
            else if (fieldClassName.equals(LocalDateTime.class.getName()))
            {
                String        strDateTime = value.toString();
                LocalDateTime dateTime    = LocalDateTime.parse(strDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

                setter = clazz.getDeclaredMethod(setterName, LocalDateTime.class);
                setter.invoke(instance, dateTime);
            }
            else if (fieldClassName.equals(ArrayList.class.getName())) // ArrayList<Model>
            {
                ParameterizedType listType = (ParameterizedType) field.getGenericType();

                String typeName         = listType.getActualTypeArguments()[0].getTypeName();
                String factoryClassName = Helpers.getFactoryOfModel(typeName);

                Class<?> modelClazz   = Class.forName(typeName);
                Class<?> factoryClazz = Class.forName(factoryClassName);

                ModelFactory factory = (ModelFactory) factoryClazz.newInstance();

                String addMethodName = Helpers.getAddingMethodName(camelCaseKey);
                Method addMethod     = clazz.getDeclaredMethod(addMethodName, modelClazz);

                JSONArray array = (JSONArray) value;

                for (Object v : array)
                {
                    JSONObject vJson = ((JSONObject) v);

                    Object result = factory.buildFromJson(vJson);

                    addMethod.invoke(instance, modelClazz.cast(result));
                }
            }
            else if (fieldClassName.equals(RelationWithPivot.class.getName()))
            {
                ParameterizedType listType = (ParameterizedType) field.getGenericType();

                String modelTypeName = listType.getActualTypeArguments()[0].getTypeName();
                String pivotTypeName = listType.getActualTypeArguments()[1].getTypeName();

                String modelFactoryClassName = Helpers.getFactoryOfModel(modelTypeName);
                String pivotFactoryClassName = Helpers.getFactoryOfModel(pivotTypeName);

                Class<?> modelClazz        = Class.forName(modelTypeName);
                Class<?> pivotClazz        = Class.forName(pivotTypeName);

                Class<?> modelFactoryClazz = Class.forName(modelFactoryClassName);
                Class<?> pivotFactoryClazz = Class.forName(pivotFactoryClassName);

                ModelFactory modelFactory = (ModelFactory) modelFactoryClazz.newInstance();
                ModelFactory pivotFactory = (ModelFactory) pivotFactoryClazz.newInstance();

                String addMethodName = Helpers.getAddingMethodName(camelCaseKey);
                Method addMethod     = clazz.getDeclaredMethod(addMethodName, modelClazz, pivotClazz);

                JSONArray array = (JSONArray) value;

                for (Object v : array)
                {
                    JSONObject modelJson = ((JSONObject) v);
                    Object model = modelFactory.buildFromJson(modelJson);

                    JSONObject pivotJson = (JSONObject) modelJson.get("pivot");
                    Object pivot = pivotJson == null ? null : pivotFactory.buildFromJson(pivotJson);

                    addMethod.invoke(instance, modelClazz.cast(model), pivotClazz.cast(pivot));
                }
            }
            else // object
            {
                String objName = field.getType()
                                      .getName();

                String factoryClassName = Helpers.getFactoryOfModel(objName);

                Class<?> modelClazz   = Class.forName(objName);
                Class<?> factoryClazz = Class.forName(factoryClassName);

                ModelFactory factory = (ModelFactory) factoryClazz.newInstance();

                Method factoryBuildMethod = factoryClazz.getMethod("buildFromJson", JSONObject.class);

                Object result = factoryBuildMethod.invoke(factory, ((JSONObject) value));

                setter = clazz.getDeclaredMethod(setterName, modelClazz);
                setter.invoke(instance, modelClazz.cast(result));

            }
        }

        return instance;
    }

    public abstract String getBaseUrl ();

    public abstract ArrayList<T> allWithRelations ();

    protected abstract Class<T> getModelInstanceClass ();

    public String getModelTableName () throws ReflectiveOperationException
    {
        return (String) getModelInstanceClass().getField("TABLE_NAME")
                                               .get(null);
    }
}
