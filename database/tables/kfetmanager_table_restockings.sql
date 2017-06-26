
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
