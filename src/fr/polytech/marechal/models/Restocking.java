package fr.polytech.marechal.models;

import fr.polytech.marechal.libs.database.query.results.QueryResult;
import fr.polytech.marechal.libs.mvc.models.Model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Robin
 * @date 25/06/2017
 */
public class Restocking extends Model<Restocking>
{
    private LocalDate date;
    private double cost;
    private String description;

    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<ProductRestocking> productRestockings = new ArrayList<>();

    public ArrayList<Product> getProducts ()
    {
        return products;
    }

    public void setProducts (ArrayList<Product> products)
    {
        this.products = products;
    }

    public void addProduct(Product product)
    {
        this.products.add(product);
    }


    public LocalDate getDate ()
    {
        return date;
    }

    public void setDate (LocalDate date)
    {
        this.date = date;
    }

    public double getCost ()
    {
        return cost;
    }

    public void setCost (double cost)
    {
        this.cost = cost;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public ArrayList<ProductRestocking> getProductRestockings ()
    {
        return productRestockings ;
    }

    public void setProductRestockings (ArrayList<ProductRestocking> productRestockings)
    {
        this.productRestockings = productRestockings;
    }

    public void addProductRestocking (ProductRestocking productRestocking)
    {
        this.productRestockings.add(productRestocking);
    }

    @Override
    protected void recopy (Restocking obj)
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
        return "Restocking{" + "id=" + getId() + ", date=" + date + ", cost=" + cost + ", description='" + description + '\'' + '}';
    }
}
