/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : bookstore

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 06/09/2018 16:42:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_book
-- ----------------------------
DROP TABLE IF EXISTS `tb_book`;
CREATE TABLE `tb_book`  (
  `bid` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `bname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `price` decimal(5, 1) NULL DEFAULT NULL,
  `author` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `image` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `cid` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`bid`) USING BTREE,
  INDEX `cid`(`cid`) USING BTREE,
  CONSTRAINT `tb_book_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `tb_category` (`cid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_book
-- ----------------------------
INSERT INTO `tb_book` VALUES ('1', 'Java编程思想（第4版）', 75.6, 'qdmmy6', 'book_img/9317290-1_l.jpg', '1');
INSERT INTO `tb_book` VALUES ('2', 'Java核心技术卷1', 68.5, 'qdmmy6', 'book_img/20285763-1_l.jpg', '1');
INSERT INTO `tb_book` VALUES ('3', 'Java就业培训教程', 39.9, '张孝祥', 'book_img/8758723-1_l.jpg', '1');
INSERT INTO `tb_book` VALUES ('4', 'Head First java', 47.5, '（美）塞若', 'book_img/9265169-1_l.jpg', '1');
INSERT INTO `tb_book` VALUES ('5', 'JavaWeb开发详解', 83.3, '孙鑫', 'book_img/22788412-1_l.jpg', '2');
INSERT INTO `tb_book` VALUES ('6', 'Struts2深入详解', 63.2, '孙鑫', 'book_img/20385925-1_l.jpg', '2');
INSERT INTO `tb_book` VALUES ('7', '精通Hibernate', 30.0, '孙卫琴', 'book_img/8991366-1_l.jpg', '2');
INSERT INTO `tb_book` VALUES ('8', '精通Spring2.x', 63.2, '陈华雄', 'book_img/20029394-1_l.jpg', '2');
INSERT INTO `tb_book` VALUES ('9', 'Javascript权威指南', 93.6, '（美）弗兰纳根', 'book_img/22722790-1_l.jpg', '3');

-- ----------------------------
-- Table structure for tb_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_category`;
CREATE TABLE `tb_category`  (
  `cid` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `cname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_category
-- ----------------------------
INSERT INTO `tb_category` VALUES ('1', 'JavaSE');
INSERT INTO `tb_category` VALUES ('2', 'JavaEE');
INSERT INTO `tb_category` VALUES ('3', 'Javascript');

-- ----------------------------
-- Table structure for tb_orderitem
-- ----------------------------
DROP TABLE IF EXISTS `tb_orderitem`;
CREATE TABLE `tb_orderitem`  (
  `iid` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `count` int(11) NULL DEFAULT NULL,
  `subtotal` decimal(10, 0) NULL DEFAULT NULL,
  `oid` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `bid` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`iid`) USING BTREE,
  INDEX `oid`(`oid`) USING BTREE,
  INDEX `bid`(`bid`) USING BTREE,
  CONSTRAINT `tb_orderitem_ibfk_1` FOREIGN KEY (`oid`) REFERENCES `tb_orders` (`oid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `tb_orderitem_ibfk_2` FOREIGN KEY (`bid`) REFERENCES `tb_book` (`bid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_orders
-- ----------------------------
DROP TABLE IF EXISTS `tb_orders`;
CREATE TABLE `tb_orders`  (
  `oid` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `ordertime` datetime(0) NULL DEFAULT NULL,
  `total` decimal(10, 0) NULL DEFAULT NULL,
  `state` smallint(1) NULL DEFAULT NULL,
  `uid` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`oid`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE,
  CONSTRAINT `tb_orders_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `tb_user` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `uid` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `code` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `state` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'xia', '12345678', 'goldenkitten@163.com', '1', 1);

SET FOREIGN_KEY_CHECKS = 1;
