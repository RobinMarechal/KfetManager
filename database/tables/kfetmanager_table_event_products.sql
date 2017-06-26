
-- --------------------------------------------------------

--
-- Table structure for table `event_products`
--

DROP TABLE IF EXISTS `event_products`;
CREATE TABLE `event_products` (
  `id` int(10) UNSIGNED NOT NULL,
  `product_id` int(10) UNSIGNED DEFAULT NULL,
  `cost` decimal(8,2) NOT NULL DEFAULT '0.00',
  `price` decimal(8,2) NOT NULL DEFAULT '0.00',
  `quantity_sold` int(11) NOT NULL DEFAULT '0',
  `quantity_bought` int(11) NOT NULL DEFAULT '0',
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `event_id` int(10) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Triggers `event_products`
--
DROP TRIGGER IF EXISTS `update_kfet_on_event_product_insertion`;
DELIMITER $$
CREATE TRIGGER `update_kfet_on_event_product_insertion` AFTER INSERT ON `event_products` FOR EACH ROW BEGIN
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
            INSERT INTO kfet (balance, source) VALUES (@newBalance, CONCAT('event_products - insert - ', NEW.id));
            ####
        END IF;
    
    END
$$
DELIMITER ;
DROP TRIGGER IF EXISTS `update_kfet_on_event_product_update`;
DELIMITER $$
CREATE TRIGGER `update_kfet_on_event_product_update` AFTER UPDATE ON `event_products` FOR EACH ROW BEGIN
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
        INSERT INTO kfet (balance, source) VALUES (@newBalance, CONCAT('event_products - update - ', NEW.id));
        ####
    
    END
$$
DELIMITER ;
