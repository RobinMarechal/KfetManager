CREATE TRIGGER update_kfet_on_event_product_update
AFTER UPDATE ON event_products
FOR EACH ROW
    BEGIN
        SET @balance = 0;
        SET @quantityBought = NEW.quantity_bought - OLD.quantity_bought;
        SET @quantitySold = NEW.quantity_sold - OLD.quantity_sold;
        SET @price = NEW.price;
        SET @cost = NEW.cost;
        
        SET @toPay = @quantityBought * @cost;
        SET @toCollect = @quantitySold * @price;
        
        #### Get back the balance value
        (SELECT balance
         INTO @balance
         FROM kfet
         ORDER BY id DESC
         LIMIT 1);
        
        SET @newBalance = @balance - @toPay + @toCollect;
        
        #### Actualize the new balance value
        INSERT INTO kfet (balance) VALUES (@newBalance);
        ####
    
    END;
