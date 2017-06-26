package fr.polytech.marechal.models;

import fr.polytech.marechal.libs.database.query.results.QueryResult;
import fr.polytech.marechal.libs.mvc.Model;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class CategoryMenu extends Model<CategoryMenu>
{
    private int id;
    private int menuId;
    private int categoryId;

    private Menu menu;
    private Category Category;

    public Menu getMenu ()
    {
        return menu;
    }

    public void setMenu (Menu menu)
    {
        this.menu = menu;
    }

    public fr.polytech.marechal.models.Category getCategory ()
    {
        return Category;
    }

    public void setCategory (fr.polytech.marechal.models.Category category)
    {
        Category = category;
    }

    public int getId ()

    {
        return id;
    }

    public void setId (int id)
    {
        this.id = id;
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

    @Override

    protected void recopy (CategoryMenu obj)
    {

    }

    @Override
    public boolean update (HashMap<String, Object> data)
    {
        return false;
    }

    @Override
    protected String getPrimaryKeyValue ()
    {
        return null;
    }

    @Override
    public void buildFromResultMap (QueryResult rs) throws SQLException
    {

    }

    @Override
    public boolean save ()
    {
        return false;
    }

    @Override
    public String toString ()
    {
        return "CategoryMenu{" + "id=" + id + ", menuId=" + menuId + ", categoryId=" + categoryId + ", menu=" + menu + ", Category=" +
                Category + '}';
    }
}
