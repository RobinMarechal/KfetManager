package fr.polytech.marechal;

import fr.polytech.marechal.libs.api.*;
import fr.polytech.marechal.libs.database.query.builders.SelectQueryBuilder;
import fr.polytech.marechal.libs.database.query.builders.enums.Functions;
import fr.polytech.marechal.models.Category;
import fr.polytech.marechal.models.Product;
import fr.polytech.marechal.models.factories.CategoryFactory;
import fr.polytech.marechal.models.factories.ProductFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;

/**
 * @author Robin
 * @date 10/06/2017
 */
public class Main
{
    public static void main (String[] args)
    {
        //        testBuildWithRelations();
        testQueryBuilder();
    }

    private static void testQueryBuilder ()
    {
        try
        {
            ApiQuery query = ApiQueryBuilder.forModelFactory(new CategoryFactory())
                                            .limit(5, 10)
                                            .orderBy("id")
                                            .with("subcategories", "menus")
                                            .getQuery();

            //            System.out.println(query);
            //
            query.execute()
                 .getJson();
            //
            //            System.out.println("-----------2-----------");

            UrlParametersMap paramMap = new UrlParametersMap().setRelations("menus");

            Category category = new CategoryFactory().find(3, paramMap);

            System.out.println(category);

            category.getMenus().getPivotList().forEach(categoryMenu -> System.out.println(categoryMenu));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static void apiTests ()
    {
        try
        {
            examplePostRequest();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        try
        {
            examplePutRequest();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        try
        {
            exampleGetRequest();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        try
        {
            exampleDeleteRequest();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            getAllCategories();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

    }

    private static void examplePostRequest () throws Exception
    {
        long l = LocalDateTime.now()
                              .toEpochSecond(ZoneOffset.UTC);

        HashMap<String, Object> data = new HashMap<>();
        data.put("name", "cateTestApi" + l);

        ApiResponse resp = Api.post("api://kfetmanager.api/api/categories", data);

        System.out.println(resp.getCode());
        System.out.println(resp.getJson()
                               .toJSONString());
    }

    private static void exampleDeleteRequest () throws IOException, ParseException
    {
        ApiResponse resp = Api.delete("api://kfetmanager.api/api/categories/7");

        System.out.println(resp.getCode());
        System.out.println(resp.getJson()
                               .toJSONString());
    }

    private static void exampleGetRequest () throws IOException, ParseException
    {
        ApiResponse resp = Api.get("api://kfetmanager.api/api/categories/1");

        System.out.println(resp.getCode());
        System.out.println(resp.getJson()
                               .toJSONString());
    }

    private static void getAllCategories () throws IOException, ParseException
    {
        ApiResponse resp = Api.get("api://kfetmanager.api/api/categories");

        System.out.println(resp.getCode());
        JSONArray arr = resp.getJson();

        arr.forEach(o -> System.out.println(((JSONObject) o).get("id")));
    }

    private static void examplePutRequest () throws IOException, ParseException
    {
        long l = LocalDateTime.now()
                              .toEpochSecond(ZoneOffset.UTC);

        HashMap<String, Object> data = new HashMap<>();
        data.put("name", "PutTest" + l);

        ApiResponse resp = Api.put("api://kfetmanager.api/api/categories/2", data);

        System.out.println(resp.getCode());
        System.out.println(resp.getJson()
                               .toJSONString());
    }

    private static void exampleFactory ()
    {
        JSONObject json = new JSONObject();
        json.put("id", 5);
        json.put("name", "abc");
        json.put("subcategory_id", 8);
        json.put("price", 3.20);
        json.put("stock", 15);
        json.put("description", "teeeest");

        Product p = null;

        try
        {
            p = new ProductFactory().buildFromJson(json);
        }
        catch (ReflectiveOperationException e)
        {
            e.printStackTrace();
        }

        System.out.println(p);

        System.out.println(json);
    }

    private static void exampleCount ()
    {
        String q = SelectQueryBuilder.select("table")
                                     .count()
                                     .where("pk", "=", "pkValue")
                                     .buildQuery()
                                     .toString();

        System.out.println(q);
    }

    private static void exampleSelect ()
    {
        String q = SelectQueryBuilder.select("table")
                                     .where("name", "=", "name1")
                                     .orWhere("name", "=", "name2")
                                     .innerJoin("Table2", "id1", "=", "table_id")
                                     .groupBy("group")
                                     .having(Functions.COUNT, "f1", ">", 5)
                                     .orderBy("name", fr.polytech.marechal.libs.database.query.builders.enums.OrderBy.ASC)
                                     .limit(5)
                                     .offset(10)
                                     .buildQuery()
                                     .toString();

        System.out.println(q);
    }
}
