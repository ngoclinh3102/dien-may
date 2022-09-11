/*
 Navicat Premium Data Transfer

 Source Server         : my-server
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : dien-may

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 05/09/2022 14:41:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cart_detail
-- ----------------------------
DROP TABLE IF EXISTS `cart_detail`;
CREATE TABLE `cart_detail`  (
  `customer_id` int NOT NULL,
  `product_code` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `quantity` int NOT NULL,
  `status` int NULL DEFAULT 1,
  PRIMARY KEY (`customer_id`, `product_code`) USING BTREE,
  INDEX `fk_cart_detail_product`(`product_code` ASC) USING BTREE,
  CONSTRAINT `fk_cart_detail_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_cart_detail_product` FOREIGN KEY (`product_code`) REFERENCES `product` (`code`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_vietnamese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cart_detail
-- ----------------------------
INSERT INTO `cart_detail` VALUES (2, 'dh02', 1, 1);
INSERT INTO `cart_detail` VALUES (2, 'tv01', 1, 1);

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `code` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  PRIMARY KEY (`code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_vietnamese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('AIR_CON', 'máy lạnh');
INSERT INTO `category` VALUES ('FRIDGE', 'tủ lạnh');
INSERT INTO `category` VALUES ('LAPTOP', 'máy tính xách tay');
INSERT INTO `category` VALUES ('PHONE', 'điện thoại');
INSERT INTO `category` VALUES ('TV', 'tivi');
INSERT INTO `category` VALUES ('WASHING_MACHINE', 'máy giặt');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `last_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `birthday` date NULL DEFAULT NULL,
  `phone` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NULL DEFAULT NULL,
  `mail` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NULL DEFAULT NULL,
  `password` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `status` int NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_customer_phone`(`phone` ASC) USING BTREE,
  UNIQUE INDEX `uk_customer_mail`(`mail` ASC) USING BTREE,
  INDEX `ix_customer_fullname`(`first_name` ASC, `last_name` ASC) USING BTREE,
  INDEX `ix_customer_hoten`(`last_name` ASC, `first_name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_vietnamese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (1, 'Millie', 'Bobby Brown', '2000-02-02', '0369369369', 'millie11@gmail.com', 'c1.png', '1', 1);
INSERT INTO `customer` VALUES (2, 'Ngọc Linh', 'Vũ', '2000-01-16', '0328374035', 'ahrix268@gmail.com', 'c3.jpg', '1', 1);
INSERT INTO `customer` VALUES (3, 'Tom', 'Hardy', '1999-03-26', '0357357357', 'tomhardy@gmail.com', 'c2.png', '1', 1);
INSERT INTO `customer` VALUES (5, 'DEF', 'ABC', '2000-01-16', '0987987987', 'ABCDEF@GMAIL.COM', NULL, '123', 1);
INSERT INTO `customer` VALUES (6, 'dsavgbzxc', 'asddfsa', '2022-09-06', '0835456235', 'dsavgbzxc@gmail.com', NULL, '213', 1);
INSERT INTO `customer` VALUES (7, 'dsavgbzxc', 'asddfsa', '2022-09-06', '0835456237', 'trqwetrsd@gmail.com', NULL, '123', 1);
INSERT INTO `customer` VALUES (8, 'ádg', 'agads', '2022-09-06', '0835456232', 'dsavbszxc@gmail.com', NULL, '123', 1);
INSERT INTO `customer` VALUES (10, 'zxcbz', 'bvzxb', NULL, '0986', NULL, NULL, '123', 1);

-- ----------------------------
-- Table structure for delivery
-- ----------------------------
DROP TABLE IF EXISTS `delivery`;
CREATE TABLE `delivery`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_at` datetime NOT NULL,
  `payment_method` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `shipping_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `customer_id` int NOT NULL,
  `employee_id` int NULL DEFAULT NULL,
  `status` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NULL DEFAULT NULL,
  `shipping_agent_code` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `voucher_code` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_delivery_cusomer`(`customer_id` ASC) USING BTREE,
  INDEX `fk_delivery_employee`(`employee_id` ASC) USING BTREE,
  INDEX `fk_delivery_shipping_agent`(`shipping_agent_code` ASC) USING BTREE,
  INDEX `fk_delivery_voucher`(`voucher_code` ASC) USING BTREE,
  CONSTRAINT `fk_delivery_cusomer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_delivery_employee` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_delivery_shipping_agent` FOREIGN KEY (`shipping_agent_code`) REFERENCES `shipping_agent` (`code`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_delivery_voucher` FOREIGN KEY (`voucher_code`) REFERENCES `voucher` (`code`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_vietnamese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of delivery
-- ----------------------------
INSERT INTO `delivery` VALUES (8, '2022-08-27 23:58:35', 'Thanh toán khi nhận hàng', '97 Man Thiện', 2, NULL, 'FULFILLED', 'nothing', 'TURBOTRANS', 'ss99');
INSERT INTO `delivery` VALUES (9, '2022-09-04 18:21:08', 'Thanh toán khi nhận hàng', '97 Man Thiện', 2, NULL, 'FULFILLED', '', 'GHN', NULL);
INSERT INTO `delivery` VALUES (10, '2022-09-04 18:22:59', 'Thanh toán khi nhận hàng', '97 Man Thiện', 2, NULL, 'FULFILLED', '', 'GHN', NULL);

-- ----------------------------
-- Table structure for delivery_detail
-- ----------------------------
DROP TABLE IF EXISTS `delivery_detail`;
CREATE TABLE `delivery_detail`  (
  `delivery_id` int NOT NULL,
  `product_code` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `quantity` int NOT NULL,
  `price` float NOT NULL,
  PRIMARY KEY (`delivery_id`, `product_code`) USING BTREE,
  INDEX `fk_delivery_detail_product`(`product_code` ASC) USING BTREE,
  CONSTRAINT `fk_delivery_detail_delivery` FOREIGN KEY (`delivery_id`) REFERENCES `delivery` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_delivery_detail_product` FOREIGN KEY (`product_code`) REFERENCES `product` (`code`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_vietnamese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of delivery_detail
-- ----------------------------
INSERT INTO `delivery_detail` VALUES (8, 'ip13promax', 2, 29000000);
INSERT INTO `delivery_detail` VALUES (8, 'tl01', 1, 4500000);
INSERT INTO `delivery_detail` VALUES (9, 'dh02', 1, 3600000);
INSERT INTO `delivery_detail` VALUES (9, 'ip13promax', 1, 29000000);
INSERT INTO `delivery_detail` VALUES (9, 'tl01', 1, 4500000);
INSERT INTO `delivery_detail` VALUES (9, 'tv01', 1, 4800000);
INSERT INTO `delivery_detail` VALUES (10, 'dh02', 1, 3600000);
INSERT INTO `delivery_detail` VALUES (10, 'tv01', 1, 4800000);

-- ----------------------------
-- Table structure for discount
-- ----------------------------
DROP TABLE IF EXISTS `discount`;
CREATE TABLE `discount`  (
  `code` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `value` decimal(10, 0) NOT NULL,
  `status` int NOT NULL,
  PRIMARY KEY (`code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_vietnamese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of discount
-- ----------------------------

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `last_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `role` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `birthday` date NULL DEFAULT NULL,
  `phone` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `status` int NOT NULL,
  `password` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_vietnamese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (6, 'Robert', 'Pattinson', 'admin', '1998-10-28', '0328374035', 1, '123');
INSERT INTO `employee` VALUES (7, 'Linh', 'Vu Ngoc', 'admin', '1999-12-27', '0123123123', 1, '1');

-- ----------------------------
-- Table structure for export
-- ----------------------------
DROP TABLE IF EXISTS `export`;
CREATE TABLE `export`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_at` datetime NOT NULL,
  `status` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NULL DEFAULT NULL,
  `employee_id` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_export_employee`(`employee_id` ASC) USING BTREE,
  CONSTRAINT `fk_export_employee` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_vietnamese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of export
-- ----------------------------

-- ----------------------------
-- Table structure for export_detail
-- ----------------------------
DROP TABLE IF EXISTS `export_detail`;
CREATE TABLE `export_detail`  (
  `export_id` int NOT NULL,
  `product_code` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `quantity` int NOT NULL,
  `price` float NOT NULL,
  PRIMARY KEY (`export_id`, `product_code`) USING BTREE,
  INDEX `fk_export_detail_product`(`product_code` ASC) USING BTREE,
  CONSTRAINT `fk_export_detail_export` FOREIGN KEY (`export_id`) REFERENCES `export` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_export_detail_product` FOREIGN KEY (`product_code`) REFERENCES `product` (`code`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_vietnamese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of export_detail
-- ----------------------------

-- ----------------------------
-- Table structure for import
-- ----------------------------
DROP TABLE IF EXISTS `import`;
CREATE TABLE `import`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_at` datetime NOT NULL,
  `status` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NULL DEFAULT NULL,
  `employee_id` int NOT NULL,
  `order_id` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_import_order`(`order_id` ASC) USING BTREE,
  INDEX `fk_import_employee`(`employee_id` ASC) USING BTREE,
  CONSTRAINT `fk_import_employee` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_import_order` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_vietnamese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of import
-- ----------------------------

-- ----------------------------
-- Table structure for import_detail
-- ----------------------------
DROP TABLE IF EXISTS `import_detail`;
CREATE TABLE `import_detail`  (
  `import_id` int NOT NULL,
  `product_code` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `quantity` int NOT NULL,
  `price` float NOT NULL,
  PRIMARY KEY (`import_id`, `product_code`) USING BTREE,
  INDEX `fk_import_detail_product`(`product_code` ASC) USING BTREE,
  CONSTRAINT `fk_import_detail_import` FOREIGN KEY (`import_id`) REFERENCES `import` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_import_detail_product` FOREIGN KEY (`product_code`) REFERENCES `product` (`code`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_vietnamese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of import_detail
-- ----------------------------

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_at` datetime NOT NULL,
  `supplier` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NULL DEFAULT NULL,
  `warehouse` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NULL DEFAULT NULL,
  `status` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NULL DEFAULT NULL,
  `employee_id` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_order_employee`(`employee_id` ASC) USING BTREE,
  CONSTRAINT `fk_order_employee` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_vietnamese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (1, '2022-08-15 14:44:16', 'Tomahawk', 'TP Thủ Đức', 'ready', 'nhập mới', 6);

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail`  (
  `order_id` int NOT NULL,
  `product_code` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `quantity` int NOT NULL,
  `price` float NOT NULL,
  PRIMARY KEY (`order_id`, `product_code`) USING BTREE,
  INDEX `fk_order_detail_product`(`product_code` ASC) USING BTREE,
  CONSTRAINT `fk_order_detail_order` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_order_detail_product` FOREIGN KEY (`product_code`) REFERENCES `product` (`code`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_vietnamese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES (1, 'tv01', 12, 5000000);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `code` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `desc` text CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NULL,
  `brand` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `category_code` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `unit` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `inventory` int NULL DEFAULT 0,
  `price` float NOT NULL,
  `price_0` float NULL DEFAULT NULL,
  `bought` int NULL DEFAULT 0,
  `discount_code` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NULL DEFAULT NULL,
  `status` int NOT NULL DEFAULT 1,
  `warranty` int NULL DEFAULT 0,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`code`) USING BTREE,
  UNIQUE INDEX `uk_product_name`(`name` ASC) USING BTREE,
  INDEX `ix_product_name`(`name` ASC) USING BTREE,
  INDEX `fk_product_category`(`category_code` ASC) USING BTREE,
  INDEX `fk_product_discount`(`discount_code` ASC) USING BTREE,
  CONSTRAINT `fk_product_category` FOREIGN KEY (`category_code`) REFERENCES `category` (`code`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_product_discount` FOREIGN KEY (`discount_code`) REFERENCES `discount` (`code`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_vietnamese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('dh02', 'Máy lạnh Panasonic 1.5 HP', 'Thiết kế sang trọng, độc đáo: Sử dụng tông màu trắng làm tôn lên vẻ đẹp sang trọng\nTính năng lọc bụi, kháng khuẩn cao: Loại bỏ bụi bẩn trong không khí kế cả các hạt nhỏ PM2.5* như vi khuẩn, vi rút và nấm mốc.\nTính năng hút ẩm, tạo không gian khô thoáng: Vô hiệu hóa các vi sinh vật bám dính và các phần gây mùi bám dính trên bề mặt đồ vật, đồng thời hạn chế nấm mốc trên bề mặt đồ vật.\nTính năng hẹn giờ: Nhờ có chức năng hẹn giờ, bạn có thể kiểm soát được thời gian hoạt động của máy lạnh dù là ban ngày hay ban đêm.', 'PANASONIC', 'AIR_CON', 'chiếc', 12, 3600000, 3600000, 0, NULL, 1, 18, '2022-08-23 10:54:39');
INSERT INTO `product` VALUES ('ip13promax', 'Điện thoại iPhone 13 Pro Max', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'APPLE', 'PHONE', 'chiếc', 69, 29000000, 30000000, 0, NULL, 1, 0, '2022-08-21 10:54:59');
INSERT INTO `product` VALUES ('mg10', 'Máy giặt LG Inverter 10 kg', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'LG', 'WASHING_MACHINE', 'chiếc', 0, 190, 1900000, 0, NULL, 1, 12, '2022-08-22 10:55:05');
INSERT INTO `product` VALUES ('tl01', 'Tủ lạnh Hitachi Inverter 569L GMG', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'HITACHI', 'FRIDGE', 'chiếc', 36, 4500000, 4500000, 0, NULL, 1, 24, '2022-08-23 10:55:08');
INSERT INTO `product` VALUES ('tv01', 'Smart Tivi OLED LG 4K 48 inch', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'LG', 'TV', 'chiếc', 451, 4800000, 5000000, 0, NULL, 1, 12, '2022-08-20 10:55:12');

-- ----------------------------
-- Table structure for product_img
-- ----------------------------
DROP TABLE IF EXISTS `product_img`;
CREATE TABLE `product_img`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `product_code` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_product_img_product`(`product_code` ASC) USING BTREE,
  CONSTRAINT `fk_product_img_product` FOREIGN KEY (`product_code`) REFERENCES `product` (`code`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 88 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_vietnamese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_img
-- ----------------------------
INSERT INTO `product_img` VALUES (64, 'tulanh1.jpg', 'tl01');
INSERT INTO `product_img` VALUES (65, 'tulanh2.jpg', 'tl01');
INSERT INTO `product_img` VALUES (66, 'tulanh3.jpg', 'tl01');
INSERT INTO `product_img` VALUES (67, 'tulanh4.jpg', 'tl01');
INSERT INTO `product_img` VALUES (68, 'tulanh5.jpg', 'tl01');
INSERT INTO `product_img` VALUES (69, 'dieuhoa1.jpg', 'dh02');
INSERT INTO `product_img` VALUES (70, 'dieuhoa3.jpg', 'dh02');
INSERT INTO `product_img` VALUES (71, 'dieuhoa4.jpg', 'dh02');
INSERT INTO `product_img` VALUES (72, 'dieuhoa5.jpg', 'dh02');
INSERT INTO `product_img` VALUES (73, 'maygiat1.jpg', 'mg10');
INSERT INTO `product_img` VALUES (74, 'maygiat2.jpg', 'mg10');
INSERT INTO `product_img` VALUES (75, 'maygiat3.jpg', 'mg10');
INSERT INTO `product_img` VALUES (76, 'maygiat4.jpg', 'mg10');
INSERT INTO `product_img` VALUES (77, 'phone1.jpg', 'ip13promax');
INSERT INTO `product_img` VALUES (78, 'phone2.jpg', 'ip13promax');
INSERT INTO `product_img` VALUES (79, 'phone3.jpg', 'ip13promax');
INSERT INTO `product_img` VALUES (80, 'phone4.jpg', 'ip13promax');
INSERT INTO `product_img` VALUES (81, 'phone5.jpg', 'ip13promax');
INSERT INTO `product_img` VALUES (82, 'phone6.jpg', 'ip13promax');
INSERT INTO `product_img` VALUES (83, 'tv1.jpg', 'tv01');
INSERT INTO `product_img` VALUES (84, 'tv2.jpg', 'tv01');
INSERT INTO `product_img` VALUES (85, 'tv3.jpg', 'tv01');
INSERT INTO `product_img` VALUES (86, 'tv4.jpg', 'tv01');

-- ----------------------------
-- Table structure for shipping_agent
-- ----------------------------
DROP TABLE IF EXISTS `shipping_agent`;
CREATE TABLE `shipping_agent`  (
  `code` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `cost` int NOT NULL,
  `delivery_average_time` int NULL DEFAULT 2,
  PRIMARY KEY (`code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_vietnamese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shipping_agent
-- ----------------------------
INSERT INTO `shipping_agent` VALUES ('GHN', 'Giao Hàng Nhanh', 25000, 2);
INSERT INTO `shipping_agent` VALUES ('GHTK', 'Giao Hàng Tiết Kiệm', 18000, 4);
INSERT INTO `shipping_agent` VALUES ('ShopeeX', 'Shopee Express', 20000, 3);
INSERT INTO `shipping_agent` VALUES ('TURBOTRANS', 'Vận Chuyển Hỏa Tốc', 60000, 0);

-- ----------------------------
-- Table structure for voucher
-- ----------------------------
DROP TABLE IF EXISTS `voucher`;
CREATE TABLE `voucher`  (
  `code` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `value` float NOT NULL DEFAULT 0,
  `quantity` int NULL DEFAULT 999999999,
  `used` int NULL DEFAULT 0,
  `time_begin` datetime NOT NULL,
  `time_end` datetime NOT NULL,
  PRIMARY KEY (`code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_vietnamese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of voucher
-- ----------------------------
INSERT INTO `voucher` VALUES ('<none>', '<none>', 0, 999999999, 0, '2022-08-28 11:01:18', '2022-09-09 11:01:23');
INSERT INTO `voucher` VALUES ('ss99', 'Siêu Sale Tháng 9', 9.9, 1, 1, '2022-08-27 15:06:54', '2022-09-09 15:07:00');

-- ----------------------------
-- Procedure structure for SP_ADMINLOGIN
-- ----------------------------
DROP PROCEDURE IF EXISTS `SP_ADMINLOGIN`;
delimiter ;;
CREATE PROCEDURE `SP_ADMINLOGIN`(IN LOGIN_ID VARCHAR(16),
	IN LOGIN_PASSWORD VARCHAR(16))
BEGIN
	IF EXISTS (SELECT * FROM employee WHERE employee.phone=LOGIN_ID) THEN
		BEGIN
			IF EXISTS (SELECT * FROM employee WHERE employee.phone=LOGIN_ID AND employee.`password`=LOGIN_PASSWORD) THEN
				BEGIN
					SELECT employee.id FROM employee WHERE employee.phone=LOGIN_ID AND employee.`password`=LOGIN_PASSWORD;
				END;
			ELSE
				BEGIN
					SELECT '-100';
				END;
			END IF;
		END;
	ELSE
		BEGIN
			SELECT '-200';
		END;
	END IF;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for SP_CHECKVOUCHER
-- ----------------------------
DROP PROCEDURE IF EXISTS `SP_CHECKVOUCHER`;
delimiter ;;
CREATE PROCEDURE `SP_CHECKVOUCHER`(IN VOUCHERCODE VARCHAR(16))
BEGIN
	IF EXISTS (SELECT * FROM voucher WHERE voucher.`code`=VOUCHERCODE) THEN
		BEGIN
			IF EXISTS (SELECT * FROM voucher 
										WHERE voucher.`code`=VOUCHERCODE AND
													(CURRENT_TIMESTAMP() BETWEEN voucher.time_begin AND voucher.time_end) AND
													(voucher.quantity-voucher.used)>0) THEN
				BEGIN
					SELECT voucher.`value` FROM voucher WHERE voucher.`code`=VOUCHERCODE;
				END;
			ELSE
				BEGIN
					SELECT '-100';
				END;
			END IF;
		END;
	ELSE
		BEGIN
			SELECT '-404';
		END;
	END IF;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for SP_CUSTOMERSIGNUP
-- ----------------------------
DROP PROCEDURE IF EXISTS `SP_CUSTOMERSIGNUP`;
delimiter ;;
CREATE PROCEDURE `SP_CUSTOMERSIGNUP`(IN HO VARCHAR(30),
	IN TEN VARCHAR(30),
	IN NGAYSINH VARCHAR(16),
	IN SDT VARCHAR(16),
	IN MAIL VARCHAR(64),
	IN MATKHAU VARCHAR(16))
BEGIN
  IF EXISTS (SELECT * FROM customer WHERE customer.phone = SDT) THEN
		BEGIN
			SELECT '-100';
		END;
	ELSE
		BEGIN
			IF EXISTS (SELECT * FROM customer WHERE customer.mail = MAIL) THEN
				BEGIN
					SELECT '-101';
				END;
			ELSE
				BEGIN
					INSERT INTO `dien-may`.`customer` (`first_name`, `last_name`, `birthday`, `phone`, `mail`, `avatar`, `password`, `status`) 
						VALUES (TEN, HO, NGAYSINH, SDT, MAIL, NULL, MATKHAU, 1);
					SELECT '1';
				END;
			END IF;
		END;
	END IF;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for SP_LOGIN
-- ----------------------------
DROP PROCEDURE IF EXISTS `SP_LOGIN`;
delimiter ;;
CREATE PROCEDURE `SP_LOGIN`(IN LOGIN_ID VARCHAR(64),
	IN LOGIN_PASSWORD VARCHAR(16))
BEGIN
	IF EXISTS (SELECT customer.id FROM customer WHERE customer.mail = LOGIN_ID OR customer.phone = LOGIN_ID) THEN
		BEGIN
			IF EXISTS (SELECT customer.id FROM customer WHERE (customer.mail = LOGIN_ID OR customer.phone = LOGIN_ID) AND customer.`password` = LOGIN_PASSWORD) THEN
				SELECT customer.id FROM customer WHERE customer.mail = LOGIN_ID OR customer.phone = LOGIN_ID;
			ELSE
				SELECT '-1';
			END IF;
		END;
	ELSE
		SELECT '-2';
	END IF;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for SP_POSTCARTDETAIL
-- ----------------------------
DROP PROCEDURE IF EXISTS `SP_POSTCARTDETAIL`;
delimiter ;;
CREATE PROCEDURE `SP_POSTCARTDETAIL`(IN CUSTOMERID INT,
	IN PRODUCTCODE VARCHAR(16))
BEGIN
	IF EXISTS (SELECT * FROM cart_detail WHERE cart_detail.customer_id = CUSTOMERID AND cart_detail.product_code = PRODUCTCODE) THEN
		BEGIN
			UPDATE cart_detail
				SET cart_detail.quantity = cart_detail.quantity + 1
				WHERE cart_detail.customer_id = CUSTOMERID AND cart_detail.product_code = PRODUCTCODE;
			SELECT '2';
		END;
	ELSE
		BEGIN
			INSERT INTO cart_detail VALUES (CUSTOMERID,PRODUCTCODE,1,1);
			SELECT '1';
		END;
	END IF;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for SP_POSTDELIVERY
-- ----------------------------
DROP PROCEDURE IF EXISTS `SP_POSTDELIVERY`;
delimiter ;;
CREATE PROCEDURE `SP_POSTDELIVERY`(IN paymentMethod VARCHAR(255),
	IN shippingAddress VARCHAR(255),
	IN customerId INT,
	IN note VARCHAR(255),
	IN shippingAgentCode VARCHAR(16),
	IN voucherCode VARCHAR(16))
BEGIN
	IF voucherCode = '' THEN
		SET voucherCode = NULL;
	END IF;
	SET @TODAY = CURRENT_TIMESTAMP();

	INSERT INTO `delivery` (`id`, `create_at`, `payment_method`, `shipping_address`, `customer_id`, `employee_id`, `status`, `note`, `shipping_agent_code`, `voucher_code`) 
		VALUES (NULL, @TODAY, paymentMethod, shippingAddress, customerId, NULL, 'READY', note, shippingAgentCode, voucherCode);
		
	SELECT delivery.id FROM delivery WHERE create_at = @TODAY;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for SP_REPORT_MOSTSELLPRODUCT
-- ----------------------------
DROP PROCEDURE IF EXISTS `SP_REPORT_MOSTSELLPRODUCT`;
delimiter ;;
CREATE PROCEDURE `SP_REPORT_MOSTSELLPRODUCT`(IN TYPE VARCHAR(16))
BEGIN
	DROP TABLE IF EXISTS REPORT;

	CREATE TEMPORARY TABLE REPORT(
		PRODUCTNAME VARCHAR(50) NOT NULL PRIMARY KEY,
		TOTAL INT NOT NULL DEFAULT 0
	);

	SELECT * FROM REPORT;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
