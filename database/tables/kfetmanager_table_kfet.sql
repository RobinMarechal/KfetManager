
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
