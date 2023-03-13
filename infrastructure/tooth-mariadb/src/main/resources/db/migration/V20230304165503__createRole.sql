CREATE TABLE IF NOT EXISTS `member_role` (
    `id` bigint(20) PRIMARY KEY AUTO_INCREMENT,

    `created_at` datetime(6) NOT NULL,
    `updated_at` datetime(6) NOT NULL,

    `member_id` bigint(20) NOT NULL,
    `role` varchar(30) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;
