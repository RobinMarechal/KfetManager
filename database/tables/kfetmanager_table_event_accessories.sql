
-- --------------------------------------------------------

--
-- Table structure for table `event_accessories`
--

DROP TABLE IF EXISTS `event_accessories`;
CREATE TABLE `event_accessories` (
  `id` int(10) UNSIGNED NOT NULL,
  `event_id` int(10) UNSIGNED DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `cost` decimal(8,2) NOT NULL DEFAULT '0.00',
  `quantity` int(11) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Triggers `event_accessories`
--
DROP TRIGGER IF EXISTS `update_kafet_on_event_accessory_insert`;
DELIMITER $$
CREATE TRIGGER `update_kafet_on_event_accessory_insert` AFTER INSERT ON `event_accessories` FOR EACH ROW BEGIN
        SET @cost = NEW.cost;
        SET @quantity = NEW.quantity;
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
        INSERT INTO kfet (balance, source) VALUES (@newBalance, CONCAT('event_accessories - insert - ', NEW.id));
        ####
    
    END
$$
DELIMITER ;
DROP TRIGGER IF EXISTS `update_kafet_on_event_accessory_update`;
DELIMITER $$
CREATE TRIGGER `update_kafet_on_event_accessory_update` AFTER UPDATE ON `event_accessories` FOR EACH ROW BEGIN
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
    
    END
$$
DELIMITER ;
