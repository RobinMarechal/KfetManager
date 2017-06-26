DELIMITER $$
CREATE
TRIGGER update_stock_on_order_product
AFTER INSERT ON order_product
FOR EACH ROW
    BEGIN
        SET @quantity = 0;
        SET @productId = 0;
        
        #### We get the information of the order (the product ID, the quantity)
        (SELECT
             quantity,
             product_id
         INTO @quantity, @productId
         FROM order_product
         WHERE order_id = NEW.id
         LIMIT 1);
        ####
        
        SET @actualStock = 0;
        
        #### We retrieve the actual stock before the order
        (SELECT stock
         INTO @actualStock
         FROM products
         WHERE id = @productId);
        ####
        
        SET @newStock = @actualStock - @quantity;
        
        #### We update the stock of the product
        UPDATE products
        SET stock = @newStock
        ####
    END $$
DELIMITER ;