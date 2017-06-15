/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50535
Source Host           : localhost:3306
Source Database       : gxxljk

Target Server Type    : MYSQL
Target Server Version : 50535
File Encoding         : 65001

Date: 2017-06-15 14:18:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for z_well
-- ----------------------------
DROP TABLE IF EXISTS `z_well`;
CREATE TABLE `z_well` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `host_id` bigint(20) NOT NULL,
  `name` varchar(100) DEFAULT '',
  `location` varchar(200) NOT NULL,
  `lat` varchar(20) DEFAULT '',
  `lon` varchar(20) DEFAULT '',
  `distance` int(11) NOT NULL,
  `create_time` bigint(11) NOT NULL,
  `range` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of z_well
-- ----------------------------
INSERT INTO `z_well` VALUES ('1', '1', '', '鞍山道公安局楼下', '', '', '21000', '1496717499', '500');
INSERT INTO `z_well` VALUES ('2', '2', '', '侯台公安局楼下（碧水家园对面）', '', '', '500', '1496717499', '600');
INSERT INTO `z_well` VALUES ('3', '9', '', '开发区公安局楼下（泰达MSD边上）', '', '', '72000', '1496717499', '700');
INSERT INTO `z_well` VALUES ('4', '10', '', '滨海新区公安局院内（永丰街23号）', '', '', '85000', '1496717499', '800');
