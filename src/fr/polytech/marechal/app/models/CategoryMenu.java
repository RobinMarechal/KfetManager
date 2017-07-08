package fr.polytech.marechal.app.models;

import fr.polytech.marechal.libs.api.UrlParametersMap;
import fr.polytech.marechal.libs.mvc.models.Model;
import fr.polytech.marechal.libs.mvc.models.ModelManager;
import fr.polytech.marechal.app.models.managers.CategoriesManager;
import fr.polytech.marechal.app.models.managers.CategoryMenusManager;
import fr.polytech.marechal.app.models.managers.MenusManager;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class CategoryMenu extends Model<CategoryMenu>
{
    private int menuId;
    private int categoryId;

    private Menu menu;
    private Category category;

    public Menu getMenu ()
    {
        return menu;
    }

    public void setMenu (Menu menu)
    {
        this.menu = menu;
    }

    public fr.polytech.marechal.app.models.Category getCategory ()
    {
        return category;
    }

    public void setCategory (fr.polytech.marechal.app.models.Category category)
    {
        this.category = category;
    }

    public int getMenuId ()
    {
        return menuId;
    }

    public void setMenuId (int menuId)
    {
        this.menuId = menuId;
    }

    public int getCategoryId ()
    {
        return categoryId;
    }

    public void setCategoryId (int categoryId)
    {
        this.categoryId = categoryId;
    }

    public CategoryMenu loadMenu ()
    {
        return loadMenu(new UrlParametersMap());
    }

    public CategoryMenu loadMenu (UrlParametersMap parameters)
    {
        menu = new MenusManager().find(menuId, parameters);
        return this;
    }

    public CategoryMenu loadCategory ()
    {
        return loadCategory(new UrlParametersMap());
    }

    public CategoryMenu loadCategory (UrlParametersMap parameters)
    {
        category = new CategoriesManager().find(categoryId, parameters);
        return this;
    }

    @Override

    protected void recopy (CategoryMenu obj)
    {
        menuId = obj.menuId;
        categoryId = obj.categoryId;
        menu = obj.menu;
        category = obj.category;
    }

    @Override
    public boolean saveAll () throws IOException
    {
        if(menuId < 1 || categoryId < 1)
            return false;

        return save();
    }

    @Override
    public CategoryMenu loadAll ()
    {
        CategoryMenu tmp = new CategoryMenusManager().find(getId(), new UrlParametersMap().withAllRelations());
        recopy(tmp);
        return this;
    }

    @Override
    public CategoryMenu loadAll (UrlParametersMap parameters)
    {
        loadMenu(parameters);
        loadCategory(parameters);
        return this;
    }

    @Override
    public ModelManager<CategoryMenu> getManagerInstance ()
    {
        return new CategoryMenusManager();
    }

    @Override
    public HashMap<String, Object> toHashMap ()
    {
        HashMap<String, Object> map = super.toHashMap();
        map.put("menu_id", menuId);
        map.put("category_id", categoryId);

        return map;
    }

    @Override
    public String toString ()
    {
        return "CategoryMenu{" + "id=" + getId() + ", menuId=" + menuId + ", categoryId=" + categoryId + ", menu=" + menu + ", category=" + category + '}';
    }
}
