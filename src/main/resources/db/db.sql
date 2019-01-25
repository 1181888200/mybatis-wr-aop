/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50136
Source Host           : localhost:3306
Source Database       : test_master

Target Server Type    : MYSQL
Target Server Version : 50136
File Encoding         : 65001

Date: 2019-01-24 16:34:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `test_user`
-- ----------------------------
DROP TABLE IF EXISTS `test_user`;
CREATE TABLE `test_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `job` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of test_user
-- ----------------------------
INSERT INTO `test_user` VALUES ('1', '主库222', '女', '女王大人2222');
INSERT INTO `test_user` VALUES ('21', '主库222', '女', '女王大人2222');
INSERT INTO `test_user` VALUES ('31', '主库3333', '女333', '女王大人3333');
INSERT INTO `test_user` VALUES ('41', '主库aaaa', '女', '女王大人aaaa');
INSERT INTO `test_user` VALUES ('51', '主库111111', '女111111', '女王大人11111');

-- ----------------------------
-- Table structure for `test_user_book`
-- ----------------------------
DROP TABLE IF EXISTS `test_user_book`;
CREATE TABLE `test_user_book` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `totalPage` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of test_user_book
-- ----------------------------
INSERT INTO `test_user_book` VALUES ('1', '天龙八部', '金庸', '1100');
INSERT INTO `test_user_book` VALUES ('11', '天龙八部2', '金庸', '222');
INSERT INTO `test_user_book` VALUES ('21', '天龙八部3', '金庸', '333');
