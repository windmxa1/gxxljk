/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50543
Source Host           : 127.0.0.1:3306
Source Database       : zabbix

Target Server Type    : MYSQL
Target Server Version : 50699
File Encoding         : 65001

Date: 2017-07-04 14:29:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for z_host_well
-- ----------------------------
DROP TABLE IF EXISTS `z_host_well`;
CREATE TABLE `z_host_well` (
`id`  bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT ,
`well_id`  bigint(20) NOT NULL ,
`host_id`  bigint(20) NOT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=32

;

-- ----------------------------
-- Records of z_host_well
-- ----------------------------
BEGIN;
INSERT INTO `z_host_well` VALUES ('1', '1', '10'), ('2', '2', '10'), ('3', '3', '10'), ('4', '4', '10'), ('5', '5', '14'), ('6', '6', '14'), ('7', '7', '14'), ('8', '8', '14'), ('9', '9', '10'), ('10', '10', '10'), ('11', '10', '13'), ('12', '10', '14'), ('13', '10', '15'), ('14', '11', '14'), ('15', '12', '14'), ('16', '13', '10'), ('17', '14', '10'), ('18', '1', '15'), ('19', '2', '15'), ('20', '3', '15'), ('21', '4', '15'), ('22', '9', '15'), ('24', '13', '15'), ('25', '14', '15'), ('26', '5', '13'), ('27', '6', '13'), ('28', '7', '13'), ('29', '8', '13'), ('30', '11', '13'), ('31', '12', '13');
COMMIT;

-- ----------------------------
-- Table structure for z_well
-- ----------------------------
DROP TABLE IF EXISTS `z_well`;
CREATE TABLE `z_well` (
`id`  bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT ,
`name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' ,
`location`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`lat`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' ,
`lon`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' ,
`begin_distance`  int(11) NOT NULL ,
`end_distance`  int(11) NOT NULL ,
`create_time`  bigint(11) NOT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=17

;

-- ----------------------------
-- Records of z_well
-- ----------------------------
BEGIN;
INSERT INTO `z_well` VALUES ('1', '2号缆芥园西道井02', '侯台公安局楼下（碧水家园对面）', '39.128290', '117.104342', '7800', '8600', '1496717499'), ('2', '2号缆芥园西道井01', '开发区公安局楼下（泰达MSD边上）', '39.1259587837', '117.100739479', '-1', '-1', '1496717499'), ('3', '2号缆万卉路井00', '滨海新区公安局院内（永丰街23号）', '39.1068132637', '117.090770', '800', '1800', '1496717499'), ('4', '2号缆鞍山道井', '侯台公安局楼下（碧水家园对面）', '39.126869', '117.193070', '-1', '-1', '1496717499'), ('5', '3号缆鞍山道井', '', '39.126869', '117.193070', '19000', '21000', '1496717499'), ('6', '3号缆碧欣路井02', '', '39.105522', '117.116255', '1600', '4000', '1496717499'), ('7', '3号缆新科道井01', '', '39.105568', '117.105129', '-1', '-1', '1496717499'), ('8', '3号缆开发区公安局井', ' ', '39.021996', '117.705820', '71500', '72500', '1496717499'), ('9', '2号缆滨海新区公安局井', ' ', '39.066912', '117.730034', '84500', '85500', '1496717499'), ('10', '侯台公安局', ' ', '39.106584', '117.100605', '0', '2500', '1496717499'), ('11', '3号鞍山西道井', ' ', '39.117138', '117.150877', '12000', '13000', '1496717499'), ('12', '3号鞍山西道井', ' ', '39.117138', '117.150877', '12000', '13000', '1496717499'), ('13', '2号缆黄河道井03', '鞍山道公安局楼下', '39.138225', '117.159450', '14000', '16000', '1496717499'), ('14', '2号缆黄河道井03', '鞍山道公安局楼下', '39.138225', '117.159450', '14000', '16000', '1496717499');
COMMIT;

-- ----------------------------
-- Auto increment value for z_host_well
-- ----------------------------
ALTER TABLE `z_host_well` AUTO_INCREMENT=32;

-- ----------------------------
-- Auto increment value for z_well
-- ----------------------------
ALTER TABLE `z_well` AUTO_INCREMENT=17;
