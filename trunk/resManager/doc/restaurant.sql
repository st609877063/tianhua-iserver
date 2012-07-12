/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50154
Source Host           : localhost:3306
Source Database       : restaurant

Target Server Type    : MYSQL
Target Server Version : 50154
File Encoding         : 65001

Date: 2012-07-12 14:20:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `res_items`
-- ----------------------------
DROP TABLE IF EXISTS `res_items`;
CREATE TABLE `res_items` (
  `i_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一ID',
  `i_no` varchar(100) DEFAULT NULL COMMENT '食品编号',
  `i_name` varchar(100) DEFAULT NULL COMMENT '食品名称',
  `i_img` varchar(100) DEFAULT NULL COMMENT '食品图片',
  `i_imgpath` varchar(100) DEFAULT NULL,
  `i_money` varchar(100) DEFAULT NULL COMMENT '价格',
  `i_desc` varchar(100) DEFAULT NULL COMMENT '食品说明',
  `i_memo` varchar(1000) DEFAULT NULL COMMENT '食品备注',
  `i_type` char(1) DEFAULT '1' COMMENT '主副食品，1:主食 2：副食',
  `i_createtime` int(10) DEFAULT NULL COMMENT '创建时间',
  `i_adduser` int(10) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`i_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of res_items
-- ----------------------------
INSERT INTO `res_items` VALUES ('1', 'rs', '肉丝', 'default.jpg', null, '1', '1', '1', '2', '1341917341', '0');
INSERT INTO `res_items` VALUES ('2', 'mt', '馒头', 'default.jpg', null, '2', '2', '2', '1', '1341917341', '0');
INSERT INTO `res_items` VALUES ('3', 'gl', '咖喱', 'default.jpg', null, '11.5', 'asdfas', 'asdfasdf', '1', '1341917341', '0');
INSERT INTO `res_items` VALUES ('4', 'y', '鱼', '1341921515468_111.jpg', 'C:\\Program Files\\apache-tomcat-6-v2\\webapps\\res\\fujian\\1341921515468_111.jpg', '21.5', 'asdf', 'asdf', '2', '1341919097', '0');
INSERT INTO `res_items` VALUES ('5', 'lx', '龙虾', '1341923400703_111.jpg', 'C:\\Program Files\\apache-tomcat-6-v2\\webapps\\res\\fujian\\1341923400703_111.jpg', '31.5', 'asdf', 'asdf', '2', '1341919118', '0');

-- ----------------------------
-- Table structure for `res_items_img`
-- ----------------------------
DROP TABLE IF EXISTS `res_items_img`;
CREATE TABLE `res_items_img` (
  `pk_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `i_id` int(11) DEFAULT NULL COMMENT '食品ID',
  `i_no` varchar(100) DEFAULT NULL COMMENT '食品编号',
  `i_img` varchar(100) DEFAULT NULL COMMENT '食品图片',
  `i_imgpath` varchar(100) DEFAULT NULL COMMENT '食品图片路径',
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of res_items_img
-- ----------------------------

-- ----------------------------
-- Table structure for `res_menus`
-- ----------------------------
DROP TABLE IF EXISTS `res_menus`;
CREATE TABLE `res_menus` (
  `pk_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `i_id` int(11) DEFAULT NULL COMMENT '食品ID',
  `i_no` varchar(100) DEFAULT NULL COMMENT '食品编号',
  `m_money` varchar(100) DEFAULT NULL COMMENT '价格',
  `m_memo` varchar(100) DEFAULT NULL COMMENT '食品备注',
  `m_date` varchar(100) DEFAULT NULL COMMENT '时间',
  `m_type` char(1) DEFAULT '1' COMMENT '1:午餐 2：晚餐 3：生食',
  `m_createtime` int(10) DEFAULT NULL COMMENT '创建时间',
  `m_adduser` int(10) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of res_menus
-- ----------------------------
INSERT INTO `res_menus` VALUES ('1', '2', null, '2.8', '', '2012-07-11', '1', '1341976877', '0');
INSERT INTO `res_menus` VALUES ('2', '5', null, '31', '', '2012-07-11', '1', '1341976877', '0');
INSERT INTO `res_menus` VALUES ('3', '2', null, '2', '', '2012-07-11', '2', '1341991298', '0');
INSERT INTO `res_menus` VALUES ('4', '3', null, '11.5', '', '2012-07-11', '2', '1341991298', '0');
INSERT INTO `res_menus` VALUES ('5', '4', null, '21.5', '', '2012-07-10', '1', '1341991298', '0');
INSERT INTO `res_menus` VALUES ('6', '2', null, '2', '', '2012-07-10', '1', '1341991331', '0');
INSERT INTO `res_menus` VALUES ('7', '3', null, '11.5', '', '2012-07-10', '2', '1341991331', '0');
INSERT INTO `res_menus` VALUES ('8', '4', null, '21.5', '', '2012-07-10', '2', '1341991331', '0');
INSERT INTO `res_menus` VALUES ('10', '3', null, '11.5', '', '2012-07-10', '3', '1341991334', '0');
INSERT INTO `res_menus` VALUES ('11', '4', null, '21.5', '', '2012-07-10', '3', '1341991334', '0');
INSERT INTO `res_menus` VALUES ('12', '2', null, '2', '', '2012-07-08', '1', '1341993366', '0');
INSERT INTO `res_menus` VALUES ('13', '4', null, '21.5', '', '2012-07-08', '1', '1341993366', '0');
INSERT INTO `res_menus` VALUES ('14', '2', null, '2', '', '2012-07-08', '2', '1341993396', '0');
INSERT INTO `res_menus` VALUES ('15', '4', null, '21.5', '', '2012-07-08', '2', '1341993396', '0');
INSERT INTO `res_menus` VALUES ('16', '3', null, '11.5', '', '2012-07-07', '1', '1341993568', '0');
INSERT INTO `res_menus` VALUES ('17', '5', null, '31.5', '', '2012-07-07', '1', '1341993568', '0');
INSERT INTO `res_menus` VALUES ('18', '2', null, '2', '', '2012-07-01', '1', '1341993600', '0');
INSERT INTO `res_menus` VALUES ('19', '1', null, '1', '', '2012-07-01', '1', '1341993600', '0');

-- ----------------------------
-- Table structure for `res_orders`
-- ----------------------------
DROP TABLE IF EXISTS `res_orders`;
CREATE TABLE `res_orders` (
  `pk_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `o_no` int(11) DEFAULT NULL COMMENT '订单编号',
  `o_user` varchar(100) DEFAULT NULL COMMENT '用户名',
  `o_phone` int(11) DEFAULT NULL COMMENT '用户手机号',
  `o_date` varchar(100) DEFAULT NULL COMMENT '时间',
  `o_type` char(1) DEFAULT '1' COMMENT '1:午餐 2：晚餐 3：生食',
  `o_memo` varchar(100) DEFAULT NULL COMMENT '备注',
  `o_fee` varchar(100) DEFAULT NULL COMMENT '费用',
  `o_createtime` int(10) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of res_orders
-- ----------------------------
INSERT INTO `res_orders` VALUES ('1', '1342062484', 'test', '1111', '2012-07-11', '1', '', '39.4', '1342062484');
INSERT INTO `res_orders` VALUES ('2', '1342062642', 'test', '1111', '2012-07-11', '1', '', '39.4', '1342062642');
INSERT INTO `res_orders` VALUES ('3', '1342062691', 'test', '1111', '2012-07-11', '1', '', '39.4', '1342062691');
INSERT INTO `res_orders` VALUES ('4', '1342062696', 'test', '1111', '2012-07-11', '1', '', '39.4', '1342062696');
INSERT INTO `res_orders` VALUES ('5', '1342063456', 'aa', '11', '2012-07-11', '2', '', '17.5', '1342063456');
INSERT INTO `res_orders` VALUES ('6', '1342065307', 'aaa', '111', '2012-07-10', '1', '', '72.5', '1342065307');

-- ----------------------------
-- Table structure for `res_order_menu`
-- ----------------------------
DROP TABLE IF EXISTS `res_order_menu`;
CREATE TABLE `res_order_menu` (
  `pk_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `o_no` int(11) DEFAULT NULL COMMENT '订单编号',
  `i_id` int(11) DEFAULT NULL COMMENT '食品ID',
  `i_no` varchar(100) DEFAULT NULL COMMENT '食品编号',
  `om_money` varchar(100) DEFAULT NULL COMMENT '价格',
  `om_num` int(11) DEFAULT NULL COMMENT '份数',
  `om_total` varchar(100) DEFAULT NULL COMMENT '价格',
  `om_createtime` int(10) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of res_order_menu
-- ----------------------------
INSERT INTO `res_order_menu` VALUES ('1', '1342062484', '2', '', '', '3', '', '1342062484');
INSERT INTO `res_order_menu` VALUES ('2', '1342062484', '5', '', '', '1', '', '1342062484');
INSERT INTO `res_order_menu` VALUES ('3', '1342062642', '2', '', '', '3', '', '1342062642');
INSERT INTO `res_order_menu` VALUES ('4', '1342062642', '5', '', '', '1', '', '1342062642');
INSERT INTO `res_order_menu` VALUES ('5', '1342062691', '2', '', '', '3', '', '1342062691');
INSERT INTO `res_order_menu` VALUES ('6', '1342062691', '5', '', '', '1', '', '1342062691');
INSERT INTO `res_order_menu` VALUES ('7', '1342062696', '2', '', '', '3', '', '1342062696');
INSERT INTO `res_order_menu` VALUES ('8', '1342062696', '5', '', '', '1', '', '1342062696');
INSERT INTO `res_order_menu` VALUES ('9', '1342063456', '2', '', '', '3', '', '1342063456');
INSERT INTO `res_order_menu` VALUES ('10', '1342063456', '3', '', '', '1', '', '1342063456');
INSERT INTO `res_order_menu` VALUES ('11', '1342065307', '4', '', '', '3', '', '1342065307');
INSERT INTO `res_order_menu` VALUES ('12', '1342065307', '2', '', '', '4', '', '1342065307');

-- ----------------------------
-- Table structure for `res_user`
-- ----------------------------
DROP TABLE IF EXISTS `res_user`;
CREATE TABLE `res_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) DEFAULT NULL,
  `nick_name` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `user_img` varchar(100) DEFAULT NULL COMMENT '照片',
  `user_title` varchar(50) DEFAULT NULL COMMENT '头衔',
  `user_type` char(1) DEFAULT NULL COMMENT '用户类型：系统使用者1，接受礼品者0，两者都是2',
  `user_admin` char(1) DEFAULT NULL COMMENT '系统使用者类型：超级管理员1，一般管理员2，普通用户3',
  `is_close` char(1) DEFAULT NULL COMMENT '是否关闭：关闭1，开启0',
  `create_time` int(10) DEFAULT NULL COMMENT '创建时间',
  `add_user_id` int(10) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of res_user
-- ----------------------------
INSERT INTO `res_user` VALUES ('1', 'admin', '超级管理员', '123456', '1', '超级管理员', '1', '1', '0', '2', '1');
INSERT INTO `res_user` VALUES ('3', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `res_user` VALUES ('4', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `res_user` VALUES ('5', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1');
