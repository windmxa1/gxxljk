/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50535
Source Host           : localhost:3306
Source Database       : gxxljk

Target Server Type    : MYSQL
Target Server Version : 50535
File Encoding         : 65001

Date: 2017-04-02 15:14:17
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `z_alarm`
-- ----------------------------
DROP TABLE IF EXISTS `z_alarm`;
CREATE TABLE `z_alarm` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `time` bigint(20) NOT NULL,
  `distance` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `level` varchar(255) NOT NULL,
  `ack` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of z_alarm
-- ----------------------------
INSERT INTO `z_alarm` VALUES ('1', '1491098583', '2', 'e', 'e', '1');

-- ----------------------------
-- Table structure for `z_log`
-- ----------------------------
DROP TABLE IF EXISTS `z_log`;
CREATE TABLE `z_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `operation` varchar(255) NOT NULL,
  `time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of z_log
-- ----------------------------
INSERT INTO `z_log` VALUES ('1', 'tom', 'login', '1491106704');
INSERT INTO `z_log` VALUES ('2', 'tom', 'login', '1491115894');
INSERT INTO `z_log` VALUES ('3', 'tom', 'getAlarmList', '1491115900');

-- ----------------------------
-- Table structure for `z_operation`
-- ----------------------------
DROP TABLE IF EXISTS `z_operation`;
CREATE TABLE `z_operation` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `operation` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of z_operation
-- ----------------------------
INSERT INTO `z_operation` VALUES ('1', 'login', '登录');
INSERT INTO `z_operation` VALUES ('2', 'getAlarmList', '获取报警列表');

-- ----------------------------
-- Table structure for `z_state`
-- ----------------------------
DROP TABLE IF EXISTS `z_state`;
CREATE TABLE `z_state` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `time` bigint(20) NOT NULL,
  `ack` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of z_state
-- ----------------------------
INSERT INTO `z_state` VALUES ('1', '1491100431', '0');

-- ----------------------------
-- Table structure for `z_user`
-- ----------------------------
DROP TABLE IF EXISTS `z_user`;
CREATE TABLE `z_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of z_user
-- ----------------------------
INSERT INTO `z_user` VALUES ('1', 'admin', 'admin');
INSERT INTO `z_user` VALUES ('2', 'tom', '123');

-- ----------------------------
-- View structure for `v_alarm`
-- ----------------------------
DROP VIEW IF EXISTS `v_alarm`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_alarm` AS select `a`.`id` AS `id`,`a`.`time` AS `time`,`a`.`distance` AS `distance`,`a`.`description` AS `description`,`a`.`level` AS `level`,`a`.`ack` AS `ack`,date_format(from_unixtime(`a`.`time`),'%Y-%m-%d %H:%i:%S') AS `vtime` from `z_alarm` `a`;

-- ----------------------------
-- View structure for `v_log`
-- ----------------------------
DROP VIEW IF EXISTS `v_log`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_log` AS select `l`.`id` AS `id`,`l`.`username` AS `username`,`l`.`operation` AS `operation`,`l`.`time` AS `time`,date_format(from_unixtime(`l`.`time`),'%Y-%m-%d %H:%i:%S') AS `vtime`,(select `o`.`name` from `z_operation` `o` where (`o`.`operation` = `l`.`operation`)) AS `name` from `z_log` `l`;

-- ----------------------------
-- View structure for `v_state`
-- ----------------------------
DROP VIEW IF EXISTS `v_state`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_state` AS select `s`.`id` AS `id`,`s`.`time` AS `time`,`s`.`ack` AS `ack`,date_format(from_unixtime(`s`.`time`),'%Y-%m-%d %H:%i:%S') AS `vtime` from `z_state` `s`;
