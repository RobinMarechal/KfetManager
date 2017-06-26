
-- --------------------------------------------------------

--
-- Table structure for table `money_addings`
--

DROP TABLE IF EXISTS `money_addings`;
CREATE TABLE `money_addings` (
  `id` int(10) UNSIGNED NOT NULL,
  `date` date NOT NULL,
  `amount` decimal(8,2) NOT NULL DEFAULT '0.00',
  `reason` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` text COLLATE utf8mb4_unicode_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Triggers `money_addings`
--
DROP TRIGGER IF EXISTS `update_kfet_on_money_addings`;
DELIMITER $$
CREATE TRIGGER `update_kfet_on_money_addings` AFTER INSERT ON `money_addings` FOR EACH ROW BEGIN
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
        INSERT INTO kfet (balance, source) VALUES (@newBalance, CONCAT('money_addings - insert - ', NEW.id));
        ####
        END IF;
    
    END
$$
DELIMITER ;
