/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50638
Source Host           : localhost:3306
Source Database       : ams

Target Server Type    : MYSQL
Target Server Version : 50638
File Encoding         : 65001

Date: 2018-05-04 14:04:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `username` varchar(15) NOT NULL,
  `password` varchar(15) NOT NULL,
  `name` varchar(15) NOT NULL,
  `logincount` int(4) NOT NULL,
  `department` int(4) NOT NULL,
  `email` varchar(30) DEFAULT NULL,
  `workphone` varchar(20) DEFAULT NULL,
  `mobilephone` varchar(20) DEFAULT NULL,
  `permission` int(1) NOT NULL,
  `lasttime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK586034F7C32472F` (`department`),
  KEY `FK586034F6E7261AF` (`department`),
  CONSTRAINT `FK586034F6E7261AF` FOREIGN KEY (`department`) REFERENCES `department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'SuperAdmin', '123456', 'Ryan', '54', '1', 'fan.zhu@hp.com', '112589545', '15589445851', '0', '2011-04-29 20:07:12');
INSERT INTO `admin` VALUES ('2', 'Ryanseac', '123456', 'Ryan', '99', '1', 'fengxidadi@163.com', '15465632623', '5656526565523232', '1', '2011-05-06 09:01:32');
INSERT INTO `admin` VALUES ('4', 'asdadasd', '111111', 'asdasd', '0', '2', 'asd@df.h', '', '', '1', '2011-04-09 17:26:33');

-- ----------------------------
-- Table structure for `assets`
-- ----------------------------
DROP TABLE IF EXISTS `assets`;
CREATE TABLE `assets` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `aid` varchar(15) NOT NULL,
  `assetname` varchar(20) NOT NULL,
  `type` int(4) NOT NULL,
  `version` varchar(20) DEFAULT NULL,
  `manufacturer` varchar(20) DEFAULT NULL,
  `manufacturedate` datetime DEFAULT NULL,
  `buydate` datetime NOT NULL,
  `price` double NOT NULL,
  `usestate` int(1) NOT NULL,
  `deprecition` int(1) NOT NULL,
  `department` int(4) DEFAULT NULL,
  `user` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKAC1073836E7261AF` (`department`),
  KEY `FKAC10738349DADDBF` (`type`),
  KEY `FKAC10738349DB96E1` (`user`),
  CONSTRAINT `FKAC10738349DADDBF` FOREIGN KEY (`type`) REFERENCES `type` (`id`),
  CONSTRAINT `FKAC10738349DB96E1` FOREIGN KEY (`user`) REFERENCES `user` (`id`),
  CONSTRAINT `FKAC1073836E7261AF` FOREIGN KEY (`department`) REFERENCES `department` (`id`),
  CONSTRAINT `assets_department` FOREIGN KEY (`department`) REFERENCES `department` (`id`),
  CONSTRAINT `assets_type` FOREIGN KEY (`type`) REFERENCES `type` (`id`),
  CONSTRAINT `assets_user` FOREIGN KEY (`user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of assets
-- ----------------------------
INSERT INTO `assets` VALUES ('1', 'GHJYIOI', 'Opera测试', '11', '', '', null, '2011-04-14 00:00:00', '1000', '1', '1', null, null);
INSERT INTO `assets` VALUES ('2', 'GHJYIOI', 'Opera测试2', '11', '', '', null, '2011-04-14 00:00:00', '1000', '1', '2', null, null);
INSERT INTO `assets` VALUES ('3', 'OIYBJGUI', 'IE8测试', '10', 'EB1001', '504', '2011-04-01 00:00:00', '2011-04-12 00:00:00', '1000', '2', '2', '1', '1');
INSERT INTO `assets` VALUES ('4', 'HJHUI', 'Price测试', '10', '', '', null, '2011-04-12 00:00:00', '2000', '1', '1', null, null);
INSERT INTO `assets` VALUES ('5', 'HJHUI', 'Price测试2', '10', '', '', null, '2011-04-12 00:00:00', '2000.1', '1', '1', null, null);
INSERT INTO `assets` VALUES ('6', 'YH7FR9I0', 'WS', '11', '', '', null, '2011-04-14 00:00:00', '1000000', '3', '2', null, null);
INSERT INTO `assets` VALUES ('7', 'YH7FR9I0', 'WS', '11', '', '', null, '2011-04-14 00:00:00', '1000000', '1', '2', null, null);
INSERT INTO `assets` VALUES ('8', 'YH7FR9I0', 'WS', '11', '', '', null, '2011-04-14 00:00:00', '1000000', '1', '2', null, null);
INSERT INTO `assets` VALUES ('9', 'YH7FR9I0', 'WS', '11', '', '', null, '2011-04-14 00:00:00', '1000000', '1', '2', null, null);
INSERT INTO `assets` VALUES ('10', 'YH7FR9I0', 'WS', '11', '', '', null, '2011-04-14 00:00:00', '1000000', '1', '2', null, null);
INSERT INTO `assets` VALUES ('11', 'YH7FR9I0', 'WS', '11', '', '', null, '2011-04-14 00:00:00', '1000000', '1', '2', null, null);
INSERT INTO `assets` VALUES ('12', 'YH7FR9I0', 'WS', '11', '', '', null, '2011-04-14 00:00:00', '1000000', '1', '2', null, null);
INSERT INTO `assets` VALUES ('13', 'YH7FR9I0', 'WS', '11', '', '', null, '2011-04-14 00:00:00', '1000000', '1', '2', null, null);
INSERT INTO `assets` VALUES ('14', 'YH7FR9I0', 'WS', '11', '', '', null, '2011-04-14 00:00:00', '1000000', '1', '2', null, null);
INSERT INTO `assets` VALUES ('15', 'YH7FR9I0', 'WS', '11', '', '', null, '2011-04-14 00:00:00', '1000000', '1', '2', null, null);
INSERT INTO `assets` VALUES ('16', 'YH7FR9I0', 'WS', '11', '', '', null, '2011-04-14 00:00:00', '1000000', '1', '2', null, null);

-- ----------------------------
-- Table structure for `bsend`
-- ----------------------------
DROP TABLE IF EXISTS `bsend`;
CREATE TABLE `bsend` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `aid` int(4) NOT NULL,
  `bSenddate` datetime NOT NULL,
  `cost` double NOT NULL,
  `state` int(11) NOT NULL,
  `bSendperson` int(4) NOT NULL,
  `description` text,
  PRIMARY KEY (`id`),
  KEY `FK59ACEEA510D054A` (`aid`),
  CONSTRAINT `FK59ACEEA510D054A` FOREIGN KEY (`aid`) REFERENCES `assets` (`id`),
  CONSTRAINT `bsend_assets` FOREIGN KEY (`aid`) REFERENCES `assets` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bsend
-- ----------------------------
INSERT INTO `bsend` VALUES ('1', '6', '2011-04-19 00:00:00', '1222', '1', '5', '');

-- ----------------------------
-- Table structure for `department`
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dpname` varchar(20) NOT NULL,
  `description` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', 'DIT', null);
INSERT INTO `department` VALUES ('2', 'TIT', null);
INSERT INTO `department` VALUES ('3', 'DEF', null);
INSERT INTO `department` VALUES ('4', 'FTTF', null);
INSERT INTO `department` VALUES ('5', 'RTTYB', '');
INSERT INTO `department` VALUES ('6', 'IRRS', null);
INSERT INTO `department` VALUES ('7', 'ERR', null);
INSERT INTO `department` VALUES ('8', 'PBT', null);
INSERT INTO `department` VALUES ('9', 'BTY', null);
INSERT INTO `department` VALUES ('10', 'BTO', null);
INSERT INTO `department` VALUES ('11', 'C++', null);
INSERT INTO `department` VALUES ('12', 'JAVA', null);
INSERT INTO `department` VALUES ('17', '注册', '');

-- ----------------------------
-- Table structure for `test`
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `testdate` datetime NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES ('2011-02-08 16:25:47', '1', 'ss');
INSERT INTO `test` VALUES ('2011-01-02 16:27:05', '2', 'ss');
INSERT INTO `test` VALUES ('2010-12-10 16:27:16', '3', 'ss');
INSERT INTO `test` VALUES ('2011-03-07 23:25:30', '4', 'ss');
INSERT INTO `test` VALUES ('2011-03-07 23:56:41', '5', 'aaa');
INSERT INTO `test` VALUES ('2011-03-07 23:53:04', '6', 'ss');
INSERT INTO `test` VALUES ('2011-03-07 23:53:21', '7', 'ss');

-- ----------------------------
-- Table structure for `type`
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `typename` varchar(20) NOT NULL,
  `description` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES ('2', '手机', '');
INSERT INTO `type` VALUES ('10', '电脑', '');
INSERT INTO `type` VALUES ('11', '生活用品', '');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(15) NOT NULL DEFAULT '',
  `department` int(4) NOT NULL,
  `email` varchar(25) DEFAULT NULL,
  `workphone` varchar(15) DEFAULT NULL,
  `mobilephone` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK36EBCB6E7261AF` (`department`),
  CONSTRAINT `FK36EBCB6E7261AF` FOREIGN KEY (`department`) REFERENCES `department` (`id`),
  CONSTRAINT `FK_user` FOREIGN KEY (`department`) REFERENCES `department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'Ryan', '1', 'fengxidadi@163.com', '02783212002', '13469996379');
INSERT INTO `user` VALUES ('2', 'Bnuindu', '5', 'sdsd@fdd.j', '164645454', '45487415');
INSERT INTO `user` VALUES ('3', 'Bnuindu', '5', 'sdsd@fdd.j', '164645454', '45487415');
INSERT INTO `user` VALUES ('4', 'Bnuindu', '5', 'sdsd@fdd.j', '164645454', '45487415');
INSERT INTO `user` VALUES ('5', 'Bnuindu', '5', 'sdsd@fdd.j', '164645454', '45487415');
INSERT INTO `user` VALUES ('6', 'Bnuindu', '5', 'sdsd@fdd.j', '164645454', '45487415');
