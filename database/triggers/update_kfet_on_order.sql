DELIMITER $$
CREATE
TRIGGER update_balance_after_order
AFTER INSERT ON orders
FOR EACH ROW
    BEGIN
        SET @price = 0;
        SET @balance = 0;
        #we get the actual KFET balance amount
        SELECT balance
        INTO @balance
        FROM kfet
        ORDER BY id DESC
        LIMIT 1;
        SET @quantity = NEW.quantity;

        #If it's a menu
        IF NEW.menu_id IS NOT NULL
        THEN
            SET @menu_id = NEW.menu_id;
            #we get the price of the menu
            SELECT price
            INTO @price
            FROM menus
            WHERE id = @menu_id;
        #else if it's a product
        ELSE
            IF NEW.product_id IS NOT NULL
            THEN
                SET @product_id = NEW.product_id;
                #we get the price of the product
                SELECT price
                INTO @price
                FROM products
                WHERE id = @product_id;
            END IF;
        END IF;

        IF @price != 0 AND @quantity != 0
        THEN
            SET @newValue = @balance + @price * @quantity;
            #we insert a new row into kfet table
            INSERT INTO kfet
            SET balance = @newValue;
        END IF;
    END $$
DELIMITER ;