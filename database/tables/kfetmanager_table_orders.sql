
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
