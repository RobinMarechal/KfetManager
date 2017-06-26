
-- --------------------------------------------------------

--
-- Table structure for table `purchases`
--

DROP TABLE IF EXISTS `purchases`;
CREATE TABLE `purchases` (
  `id` int(10) UNSIGNED NOT NULL,
  `cost` decimal(8,2) NOT NULL DEFAULT '0.00',
  `quantity` int(11) NOT NULL DEFAULT '1',
  `date` date NOT NULL,
  `description` text COLLATE utf8mb4_unicode_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Triggers `purchases`
--
DROP TRIGGER IF EXISTS `update_kfet_on_purchases`;
DELIMITER $$
CREATE TRIGGER `update_kfet_on_purchases` AFTER INSERT ON `purchases` FOR EACH ROW BEGIN
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
        INSERT INTO kfet (balance, source) VALUES (@newBalance, CONCAT('purchases - insert - ', NEW.id));
        ####
        END IF;
    
    END
$$
DELIMITER ;
