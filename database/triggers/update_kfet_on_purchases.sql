DELIMITER $$
CREATE
TRIGGER update_kfet_on_buyings
AFTER INSERT ON buyings
FOR EACH ROW
    BEGIN
        SET @cost = NEW.cost;
        SET @quantity = NEW.quantity;
        SET @totalPaid = @cost * @quantity;
        SET @balance = 0;
        
        IF @cost != 0 AND @quantity != 0
        THEN
            #### Get back the balance value
            (SELECT balance
             INTO @balance
             FROM kfet
             ORDER BY id DESC
             LIMIT 1);
            ####
            
            SET @newBalance = @balance - @totalPaid;
            
            #### Actualize the new balance value
            INSERT INTO kfet (balance) VALUES (@newBalance);
        ####
        END IF;
    
    END $$
DELIMITER ;