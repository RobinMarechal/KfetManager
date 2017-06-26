
-- --------------------------------------------------------

--
-- Table structure for table `product_restocking`
--

DROP TABLE IF EXISTS `product_restocking`;
CREATE TABLE `product_restocking` (
  `id` int(10) UNSIGNED NOT NULL,
  `product_id` int(10) UNSIGNED DEFAULT NULL,
  `restocking_id` int(10) UNSIGNED DEFAULT NULL,
  `quantity` int(11) NOT NULL DEFAULT '1',
  `cost` double NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Triggers `product_restocking`
--
DROP TRIGGER IF EXISTS `update_kfet_and_stocks_on_product_restocking`;
DELIMITER $$
CREATE TRIGGER `update_kfet_and_stocks_on_product_restocking` AFTER INSERT ON `product_restocking` FOR EACH ROW BEGIN
        SET @cost = NEW.cost;
        SET @quantity = NEW.quantity;
        SET @product_id = NEW.product_id;
        SET @totalCost = @cost * @quantity;
        SET @balance = 0;
        
        IF @quantity != 0 AND @product_id IS NOT NULL
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
        INSERT INTO kfet (balance, source) VALUES (@newBalance, CONCAT('product_restocking - insert - ', NEW.id));
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
    
    END
$$
DELIMITER ;
