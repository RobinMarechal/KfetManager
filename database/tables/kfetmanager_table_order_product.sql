
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
