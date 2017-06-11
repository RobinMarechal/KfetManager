DELIMITER $$
CREATE
TRIGGER update_kfet_and_stocks_on_product_restocking
AFTER INSERT ON product_restocking
FOR EACH ROW
    BEGIN
        SET @cost = NEW.cost;
        SET @quantity = NEW.quantity;
        SET @product_id = NEW.product_id;
        SET @totalCost = @cost * @quantity;
        SET @balance = 0;
        
        IF @totalCost != 0 AND @product_id IS NOT NULL
        THEN
            #### Get back the balance value
            (SELECT balance
             INTO @balance
             FROM kfet
             ORDER BY id DESC
             LIMIT 1);
            ####
            
            SET @newBalance = @balance - @totalCost;
            
            #### Actualize the new balance value
            INSERT INTO kfet (balance) VALUES (@newBalance);
            ####
            
            SET @stock = 0;
            
            #### We get the actual stock before the restock
            (SELECT stock
             INTO @stock
             FROM products
             WHERE id = @product_id);
            ####
            
            SET @newStock = @stock + @quantity;
            
            #### Actualize the stocks
            UPDATE products
            SET stock = @newStock
            WHERE id = @product_id;
        ####
        END IF;
    
    END $$
DELIMITER ;