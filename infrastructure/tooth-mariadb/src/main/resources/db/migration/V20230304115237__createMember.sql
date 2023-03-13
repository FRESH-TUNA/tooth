CREATE TABLE IF NOT EXISTS `member` (
    `id` bigint(20) PRIMARY KEY AUTO_INCREMENT,
    `public_id` BINARY(16) NOT NULL UNIQUE,

    `created_at` datetime(6) NOT NULL,
    `updated_at` datetime(6) NOT NULL,

    `member_type` varchar(30) NOT NULL,

    `local_id` varchar(100) NULL UNIQUE,
    `local_password` varchar(100) NULL UNIQUE,

    `nickname` varchar(30) NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;