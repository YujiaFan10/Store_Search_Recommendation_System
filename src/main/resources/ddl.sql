CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `telephone` varchar(45) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `password` varchar(200) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `nick_name` varchar(45) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `gender` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `telephone_unique_index` (`telephone`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci


CREATE TABLE `seller` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `name` varchar(80) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `remark_score` decimal(2,1) NOT NULL DEFAULT '0.0',
  `disabled_flag` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci

CREATE TABLE `searchdb`.`category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `name` VARCHAR(20) NOT NULL DEFAULT '',
  `icon_url` VARCHAR(200) NOT NULL DEFAULT '',
  `sort` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_unique_index` USING BTREE (`name`) VISIBLE);

CREATE TABLE `shop` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `name` varchar(80) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `remark_score` decimal(2,1) NOT NULL DEFAULT '0.0',
  `price_per_man` int NOT NULL DEFAULT '0',
  `latitude` decimal(10,6) NOT NULL DEFAULT '0.000000',
  `longitude` decimal(10,6) NOT NULL DEFAULT '0.000000',
  `category_id` int NOT NULL DEFAULT '0',
  `tags` varchar(2000) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `start_time` varchar(200) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `end_time` varchar(200) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `address` varchar(200) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `seller_id` int NOT NULL DEFAULT '0',
  `icon_url` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
