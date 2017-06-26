DROP TRIGGER IF EXISTS update_kafet_on_event_accessory_update;
CREATE TRIGGER update_kafet_on_event_accessory_update
AFTER UPDATE ON event_accessories
FOR EACH ROW
    BEGIN
        SET @cost = NEW.cost;
        SET @quantity = NEW.quantity - OLD.quantity;
        SET @totalCost = @cost * @quantity;
        
        SET @balance = 0;
        #### Get back the balance value
        (SELECT balance
         INTO @balance
         FROM kfet
         ORDER BY id DESC
         LIMIT 1);
        ####
        
        SET @newBalance = @balance - @totalCost;
        
        #### Actualize the new balance value
        INSERT INTO kfet (balance, source) VALUES (@newBalance, CONCAT('event_accessories - update - ', NEW.id));
        ####
    
    END;