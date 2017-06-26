
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
