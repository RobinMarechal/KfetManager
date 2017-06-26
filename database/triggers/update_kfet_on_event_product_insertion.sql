DELIMITER $$
CREATE
TRIGGER update_kfet_on_event_product_insertion
AFTER INSERT ON event_products
FOR EACH ROW
    BEGIN
        SET @unitCost = NEW.cost;
        SET @quantity = NEW.quantity_bought;
        SET @totalCost = @unitCost * @quantity;
        SET @balance = 0;
        
        IF @totalCost != 0
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
        END IF;
    
    END;
