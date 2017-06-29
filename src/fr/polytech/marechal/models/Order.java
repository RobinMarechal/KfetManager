package fr.polytech.marechal.models;

import fr.polytech.marechal.libs.database.query.results.QueryResult;
import fr.polytech.marechal.libs.mvc.models.Model;
import fr.polytech.marechal.libs.mvc.models.RelationWithPivot;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Robin
 * @date 15/06/2017
 */
public class Order extends Model<Order>
{
    private int customerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ArrayList<Menu> menus = new ArrayList<>();
    private RelationWithPivot<Product, OrderProduct> products = new RelationWithPivot<>();
    private ArrayList<OrderProduct> orderProducts = new ArrayList<>();
    private Customer customer;


    public int getCustomerId ()
    {
        return customerId;
    }

    public void setCustomerId (int customerId)
    {
        this.customerId = customerId;
    }

    public LocalDateTime getCreatedAt ()
    {
        return createdAt;
    }

    public void setCreatedAt (LocalDateTime createdAt)
    {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt ()
    {
        return updatedAt;
    }

    public void setUpdatedAt (LocalDateTime updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public ArrayList<Menu> getMenus ()
    {
        return menus;
    }

    public void setMenus (ArrayList<Menu> menus)
    {
        this.menus = menus;
    }

    public void addMenu(Menu menu)
    {
        this.menus.add(menu);
    }

    public RelationWithPivot<Product, OrderProduct> getProducts ()
    {
        return products;
    }

    public void setProducts (RelationWithPivot<Product, OrderProduct> products)
    {
        this.products = products;
    }

    public void addProduct(Product product, OrderProduct pivot)
    {
        this.products.put(product, pivot);
    }

    public ArrayList<OrderProduct> getOrderProducts ()
    {
        return orderProducts;
    }

    public void setOrderProducts (ArrayList<OrderProduct> orderProducts)
    {
        this.orderProducts = orderProducts;
    }

    public void addOrderProduct(OrderProduct orderProduct)
    {
        this.orderProducts.add(orderProduct);
    }

    public Customer getCustomer ()
    {
        return customer;
    }

    public void setCustomer (Customer customer)
    {
        this.customer = customer;
    }

    @Override
    protected void recopy (Order obj)
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
        return "Order{" + "id=" + getId() + ", customerId=" + customerId + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", " +
                "menus=" + menus + ", products=" + products + ", customer=" + customer + '}';
    }
}
