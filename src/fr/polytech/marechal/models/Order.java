package fr.polytech.marechal.models;

import fr.polytech.marechal.libs.api.UrlParametersMap;
import fr.polytech.marechal.libs.mvc.models.Model;
import fr.polytech.marechal.libs.mvc.models.ModelManager;
import fr.polytech.marechal.libs.mvc.models.RelationsMap;
import fr.polytech.marechal.models.managers.*;

import java.io.IOException;
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
    private int menuId;

    private RelationsMap<Product, OrderProduct> products = new RelationsMap<>();
    private ArrayList<OrderProduct> orderProducts = new ArrayList<>();
    private Menu menu;
    private Customer customer;

    public Order loadCustomer ()
    {
        return loadCustomer(new UrlParametersMap());
    }

    public Order loadCustomer (UrlParametersMap parameters)
    {
        customer = new CustomersManager().find(customerId, parameters);
        return this;
    }

    public Order loadMenu ()
    {
        return loadMenu(new UrlParametersMap());
    }

    public Order loadMenu (UrlParametersMap parameters)
    {
        menu = new MenusManager().find(menuId, parameters);
        return this;
    }

    public Order loadProducts ()
    {
        return loadProducts(new UrlParametersMap());
    }

    public Order loadProducts (UrlParametersMap parameters)
    {
        ArrayList<Product> tmp = new ProductsManager().ofUrl("orders/" + getId() + "/products", parameters);
        this.products = new RelationsMap<>();
        this.products.addModels(tmp);
        return this;
    }

    public Order loadOrderProducts ()
    {
        return loadOrderProducts(new UrlParametersMap());
    }

    public Order loadOrderProducts (UrlParametersMap parameters)
    {
        orderProducts = new OrderProductsManager().ofUrl("orders/" + getId() + "/orderProducts", parameters);
        return this;
    }

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

    public int getMenuId ()
    {
        return menuId;
    }

    public void setMenuId (int menuId)
    {
        this.menuId = menuId;
    }

    public Menu getMenu ()
    {
        return menu;
    }

    public void setMenu (Menu menu)
    {
        this.menu = menu;
    }

    public RelationsMap<Product, OrderProduct> getProducts ()
    {
        return products;
    }

    public void setProducts (RelationsMap<Product, OrderProduct> products)
    {
        this.products = products;
    }

    public void addProduct (Product product, OrderProduct pivot)
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

    public void addOrderProduct (OrderProduct orderProduct)
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
        customerId = obj.customerId;
        menuId = obj.menuId;
        createdAt = obj.createdAt;
        updatedAt = obj.updatedAt;
        products = obj.products;
        orderProducts = obj.orderProducts;
        menu = obj.menu;
        customer = obj.customer;
    }

    @Override
    public boolean save () throws IOException
    {
        boolean success = saveWithoutRelations();

        for (OrderProduct op : orderProducts)
        {
            op.setOrderId(getId());
            success &= op.save();
        }

        return success;
    }

    @Override
    public Order loadAll ()
    {
        Order tmp = new OrdersManager().find(getId(), new UrlParametersMap().withAllRelations());
        recopy(tmp);
        return this;
    }

    @Override
    public Order loadAll (UrlParametersMap parameters)
    {
        loadCustomer(parameters);
        loadProducts(parameters);
        loadMenu(parameters);
        loadOrderProducts(parameters);
        return this;
    }

    @Override
    public ModelManager<Order> getManagerInstance ()
    {
        return new OrdersManager();
    }

    @Override
    public HashMap<String, Object> toHashMap ()
    {
        HashMap<String, Object> map = super.toHashMap();
        map.put("customer_id", customerId);
        map.put("menu_id", menuId);

        return map;
    }

    @Override
    public String toString ()
    {
        return "Order{" + "id=" + getId() + ", customerId=" + customerId + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", "
                + "menu=" + menu + ", products=" + products + ", customer=" + customer + '}';
    }
}
