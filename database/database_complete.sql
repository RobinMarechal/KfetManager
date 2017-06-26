-- phpMyAdmin SQL Dump
-- version 4.5.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 12, 2017 at 12:01 AM
-- Server version: 5.7.11
-- PHP Version: 5.6.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kfetmanager`
--
CREATE DATABASE IF NOT EXISTS `kfetmanager` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `kfetmanager`;

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
CREATE TABLE `categories` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `category_menu`
--

DROP TABLE IF EXISTS `category_menu`;
CREATE TABLE `category_menu` (
  `id` int(10) UNSIGNED NOT NULL,
  `menu_id` int(10) UNSIGNED DEFAULT NULL,
  `category_id` int(10) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
CREATE TABLE `customers` (
  `id` int(10) UNSIGNED NOT NULL,
  `firstname` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `lastname` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `balance` decimal(8,2) NOT NULL DEFAULT '0.00',
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `events`
--

DROP TABLE IF EXISTS `events`;
CREATE TABLE `events` (
  `id` int(10) UNSIGNED NOT NULL,
  `date` date NOT NULL,
  `description` text COLLATE utf8mb4_unicode_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

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

-- --------------------------------------------------------

--
-- Table structure for table `kfet`
--

DROP TABLE IF EXISTS `kfet`;
CREATE TABLE `kfet` (
  `id` int(10) UNSIGNED NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `balance` decimal(8,2) NOT NULL DEFAULT '0.00',
  `source` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `menus`
--

DROP TABLE IF EXISTS `menus`;
CREATE TABLE `menus` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` text COLLATE utf8mb4_unicode_ci,
  `price` decimal(8,2) NOT NULL DEFAULT '0.00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

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

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(10) UNSIGNED NOT NULL,
  `customer_id` int(10) UNSIGNED DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Triggers `orders`
--
DROP TRIGGER IF EXISTS `delete_order_product_on_order_deletion`;
DELIMITER $$
CREATE TRIGGER `delete_order_product_on_order_deletion` BEFORE DELETE ON `orders` FOR EACH ROW DELETE FROM order_product WHERE order_id = OLD.id
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `order_product`
--

DROP TABLE IF EXISTS `order_product`;
CREATE TABLE `order_product` (
  `id` int(11) NOT NULL,
  `product_id` int(10) UNSIGNED DEFAULT NULL,
  `order_id` int(10) UNSIGNED DEFAULT NULL,
  `menu_id` int(10) UNSIGNED DEFAULT NULL,
  `quantity` int(11) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Triggers `order_product`
--
DROP TRIGGER IF EXISTS `update_stock_on_order_product`;
DELIMITER $$
CREATE TRIGGER `update_stock_on_order_product` AFTER INSERT ON `order_product` FOR EACH ROW BEGIN
        SET @quantity = NEW.quantity;
        SET @productId = NEW.product_id;

            SET @actualStock = 0;
            
            #### We retrieve the actual stock before the order
            (SELECT stock INTO @actualStock FROM products WHERE id = @productId);
            ####
            
            SET @newStock = @actualStock - @quantity;
            
            #### We update the stock of the product
            UPDATE products SET stock = @newStock WHERE id = @productId;
            ####
    END
$$
DELIMITER ;
DROP TRIGGER IF EXISTS `update_stock_on_order_product_deletion`;
DELIMITER $$
CREATE TRIGGER `update_stock_on_order_product_deletion` BEFORE DELETE ON `order_product` FOR EACH ROW BEGIN
        SET @quantity = OLD.quantity;
        SET @productId = OLD.product_id;
        
        SET @actualStock = 0;
        
        #### We retrieve the actual stock before the order
        (SELECT stock
         INTO @actualStock
         FROM products
         WHERE id = @productId);
        ####
        
        SET @newStock = @actualStock + @quantity;
        
        #### We update the stock of the product
        UPDATE products
        SET stock = @newStock
        WHERE id = @productId;
        ####
    END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
CREATE TABLE `products` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` text COLLATE utf8mb4_unicode_ci,
  `price` decimal(8,2) NOT NULL DEFAULT '0.00',
  `stock` int(11) NOT NULL DEFAULT '0',
  `subcategory_id` int(10) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

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

-- --------------------------------------------------------

--
-- Table structure for table `restockings`
--

DROP TABLE IF EXISTS `restockings`;
CREATE TABLE `restockings` (
  `id` int(10) UNSIGNED NOT NULL,
  `date` date NOT NULL,
  `description` text COLLATE utf8mb4_unicode_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff` (
  `id` int(10) UNSIGNED NOT NULL,
  `customer_id` int(10) UNSIGNED DEFAULT NULL,
  `firstname` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `lastname` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `role` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `subcategories`
--

DROP TABLE IF EXISTS `subcategories`;
CREATE TABLE `subcategories` (
  `id` int(10) UNSIGNED NOT NULL,
  `category_id` int(10) UNSIGNED DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `price` decimal(8,2) NOT NULL DEFAULT '0.00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `category_menu`
--
ALTER TABLE `category_menu`
  ADD PRIMARY KEY (`id`),
  ADD KEY `category_menu_menu_id_foreign` (`menu_id`),
  ADD KEY `category_menu_category_id_foreign` (`category_id`);

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `events`
--
ALTER TABLE `events`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `event_accessories`
--
ALTER TABLE `event_accessories`
  ADD PRIMARY KEY (`id`),
  ADD KEY `event_accessories_event_id_foreign` (`event_id`);

--
-- Indexes for table `event_products`
--
ALTER TABLE `event_products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `event_products_product_id_foreign` (`product_id`),
  ADD KEY `event_products_events_id_fk` (`event_id`);

--
-- Indexes for table `kfet`
--
ALTER TABLE `kfet`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `menus`
--
ALTER TABLE `menus`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `money_addings`
--
ALTER TABLE `money_addings`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `orders_customer_id_foreign` (`customer_id`);

--
-- Indexes for table `order_product`
--
ALTER TABLE `order_product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `order_product_products_id_fk` (`product_id`),
  ADD KEY `order_product_menus_id_fk` (`menu_id`),
  ADD KEY `order_product_orders_id_fk` (`order_id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `products_subcategory_id_foreign` (`subcategory_id`);

--
-- Indexes for table `product_restocking`
--
ALTER TABLE `product_restocking`
  ADD PRIMARY KEY (`id`),
  ADD KEY `product_restocking_product_id_foreign` (`product_id`),
  ADD KEY `product_restocking_restocking_id_foreign` (`restocking_id`);

--
-- Indexes for table `purchases`
--
ALTER TABLE `purchases`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `restockings`
--
ALTER TABLE `restockings`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`id`),
  ADD KEY `staff_customer_id_foreign` (`customer_id`);

--
-- Indexes for table `subcategories`
--
ALTER TABLE `subcategories`
  ADD PRIMARY KEY (`id`),
  ADD KEY `subcategories_category_id_foreign` (`category_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `category_menu`
--
ALTER TABLE `category_menu`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `events`
--
ALTER TABLE `events`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `event_accessories`
--
ALTER TABLE `event_accessories`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `event_products`
--
ALTER TABLE `event_products`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `kfet`
--
ALTER TABLE `kfet`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;
--
-- AUTO_INCREMENT for table `menus`
--
ALTER TABLE `menus`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `money_addings`
--
ALTER TABLE `money_addings`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `order_product`
--
ALTER TABLE `order_product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `product_restocking`
--
ALTER TABLE `product_restocking`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `purchases`
--
ALTER TABLE `purchases`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `restockings`
--
ALTER TABLE `restockings`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `staff`
--
ALTER TABLE `staff`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `subcategories`
--
ALTER TABLE `subcategories`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `category_menu`
--
ALTER TABLE `category_menu`
  ADD CONSTRAINT `category_menu_category_id_foreign` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `category_menu_menu_id_foreign` FOREIGN KEY (`menu_id`) REFERENCES `menus` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `event_accessories`
--
ALTER TABLE `event_accessories`
  ADD CONSTRAINT `event_accessories_event_id_foreign` FOREIGN KEY (`event_id`) REFERENCES `events` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `event_products`
--
ALTER TABLE `event_products`
  ADD CONSTRAINT `event_products_events_id_fk` FOREIGN KEY (`event_id`) REFERENCES `events` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `event_products_product_id_foreign` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_customer_id_foreign` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `order_product`
--
ALTER TABLE `order_product`
  ADD CONSTRAINT `order_product_menus_id_fk` FOREIGN KEY (`menu_id`) REFERENCES `menus` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `order_product_orders_id_fk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `order_product_products_id_fk` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_subcategory_id_foreign` FOREIGN KEY (`subcategory_id`) REFERENCES `subcategories` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `product_restocking`
--
ALTER TABLE `product_restocking`
  ADD CONSTRAINT `product_restocking_product_id_foreign` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `product_restocking_restocking_id_foreign` FOREIGN KEY (`restocking_id`) REFERENCES `restockings` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `staff`
--
ALTER TABLE `staff`
  ADD CONSTRAINT `staff_customer_id_foreign` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `subcategories`
--
ALTER TABLE `subcategories`
  ADD CONSTRAINT `subcategories_category_id_foreign` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
