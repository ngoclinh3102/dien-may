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

 Date: 23/08/2022 16:50:23
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
  `status` int NOT NULL DEFAULT 49,
  PRIMARY KEY (`customer_id`, `product_code`) USING BTREE,
  INDEX `fk_cart_detail_product`(`product_code` ASC) USING BTREE,
  CONSTRAINT `fk_cart_detail_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_cart_detail_product` FOREIGN KEY (`product_code`) REFERENCES `product` (`code`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_vietnamese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cart_detail
-- ----------------------------

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
INSERT INTO `category` VALUES ('air_con', 'máy lạnh');
INSERT INTO `category` VALUES ('fridge', 'tủ lạnh');
INSERT INTO `category` VALUES ('laptop', 'máy tính xách tay');
INSERT INTO `category` VALUES ('phone', 'điện thoại');
INSERT INTO `category` VALUES ('tv', 'tivi');
INSERT INTO `category` VALUES ('washing_machine', 'máy giặt');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `last_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `birthday` date NULL DEFAULT NULL,
  `phone` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `mail` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NULL DEFAULT NULL,
  `password` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `status` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `ix_customer_fullname`(`first_name` ASC, `last_name` ASC) USING BTREE,
  INDEX `ix_customer_hoten`(`last_name` ASC, `first_name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_vietnamese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (1, 'Millie', 'Bobby Brown', '2000-02-02', '0328374035', 'millie11@gmail.com', 'c001.jpg', '213', 1);
INSERT INTO `customer` VALUES (2, 'Ngọc Linh', 'Vũ', '2000-01-16', '0369369369', 'ahrix268@gmail.com', NULL, '123456', 1);
INSERT INTO `customer` VALUES (3, 'Tom', 'Hardy', '1999-03-26', '0357357357', 'tomhardy@gmail.com', NULL, '123456', 1);

-- ----------------------------
-- Table structure for delivery
-- ----------------------------
DROP TABLE IF EXISTS `delivery`;
CREATE TABLE `delivery`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_at` datetime NOT NULL,
  `payment_method` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `shipping_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `customer_id` int NOT NULL,
  `employee_id` int NOT NULL,
  `status` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_delivery_cusomer`(`customer_id` ASC) USING BTREE,
  INDEX `fk_delivery_employee`(`employee_id` ASC) USING BTREE,
  CONSTRAINT `fk_delivery_cusomer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_delivery_employee` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_vietnamese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of delivery
-- ----------------------------

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_vietnamese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1, 'Robert', 'Pattinson', 'admin', '1998-10-28', '0324324324', 1, '123');

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
INSERT INTO `order` VALUES (1, '2022-08-15 14:44:16', 'Tomahawk', 'TP Thủ Đức', 'ready', 'nhập mới', 1);

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
  PRIMARY KEY (`code`) USING BTREE,
  INDEX `ix_product_name`(`name` ASC) USING BTREE,
  INDEX `fk_product_category`(`category_code` ASC) USING BTREE,
  INDEX `fk_product_discount`(`discount_code` ASC) USING BTREE,
  CONSTRAINT `fk_product_category` FOREIGN KEY (`category_code`) REFERENCES `category` (`code`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_product_discount` FOREIGN KEY (`discount_code`) REFERENCES `discount` (`code`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_vietnamese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('dh02', 'Điều hòa Panasonic Inverter 1HP', 'Thiết kế sang trọng, độc đáo: Sử dụng tông màu trắng làm tôn lên vẻ đẹp sang trọng\nTính năng lọc bụi, kháng khuẩn cao: Loại bỏ bụi bẩn trong không khí kế cả các hạt nhỏ PM2.5* như vi khuẩn, vi rút và nấm mốc.\nTính năng hút ẩm, tạo không gian khô thoáng: Vô hiệu hóa các vi sinh vật bám dính và các phần gây mùi bám dính trên bề mặt đồ vật, đồng thời hạn chế nấm mốc trên bề mặt đồ vật.\nTính năng hẹn giờ: Nhờ có chức năng hẹn giờ, bạn có thể kiểm soát được thời gian hoạt động của máy lạnh dù là ban ngày hay ban đêm.', 'PANASONIC', 'air_con', 'chiếc', 0, 3600000, 3600000, 0, NULL, 1, 18);
INSERT INTO `product` VALUES ('ip13promax', 'Iphone 13 Pro MAX', '<chưa có mô tả>', 'APPLE', 'phone', 'chiếc', 0, 31000700, 30000000, 0, NULL, 0, 0);
INSERT INTO `product` VALUES ('mg10', 'Máy giặt LG Inverter 10KG  FV1410S3B', '<chưa có mô tả>', 'LG', 'washing_machine', 'chiếc', 0, 1900000, 1900000, 0, NULL, 1, 12);
INSERT INTO `product` VALUES ('tl01', 'Tủ lạnh Hitachi 144L', '<chưa có mô tả>', 'HITACHI', 'fridge', 'chiếc', 0, 4500000, 4500000, 0, NULL, 1, 24);
INSERT INTO `product` VALUES ('tv01', 'Tivi LG OLED TV 48in', '<chưa có mô tả>', 'LG', 'tv', 'chiếc', 0, 5000000, 5000000, 0, NULL, 1, 12);

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
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_vietnamese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_img
-- ----------------------------
INSERT INTO `product_img` VALUES (45, 'Capture.png', 'dh02');
INSERT INTO `product_img` VALUES (46, 'panasonic-cu-cs-pu9wkh-8m.jpg', 'dh02');
INSERT INTO `product_img` VALUES (47, 'lg-inverter-10-kg-fv1410s3b637739487521892515.jpg', 'mg10');
INSERT INTO `product_img` VALUES (51, 'lg-inverter-10-kg-fv1410s3b637739487517752404.jpg', 'mg10');
INSERT INTO `product_img` VALUES (52, 'lg-inverter-10-kg-fv1410s3b637739487519482462.jpg', 'mg10');
INSERT INTO `product_img` VALUES (57, 'c001.jpg', 'tl01');
INSERT INTO `product_img` VALUES (58, 'Capture.png', 'tl01');
INSERT INTO `product_img` VALUES (59, 'iphone-13-pro-max-sierra-blue-600x600.jpg', 'ip13promax');
INSERT INTO `product_img` VALUES (60, 'vi-vn-iphone-13-pro-max-slider-camera.jpg', 'ip13promax');

SET FOREIGN_KEY_CHECKS = 1;
