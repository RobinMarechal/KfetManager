DELIMITER $$
CREATE
TRIGGER update_kfet_on_money_addings
AFTER INSERT ON money_addings
FOR EACH ROW
    BEGIN
        SET @amount = NEW.amount;
        SET @balance = 0;
        
        IF @amount != 0
        THEN
            #### Get back the balance value
            (SELECT balance
             INTO @balance
             FROM kfet
             ORDER BY id DESC
             LIMIT 1);
            ####
            
            SET @newBalance = @balance + @amount;
            
            #### Actualize the new balance value
            INSERT INTO kfet (balance) VALUES (@newBalance);
        ####
        END IF;
    
    END $$
DELIMITER ;