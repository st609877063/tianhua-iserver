/*
Navicat MySQL Data Transfer

Source Server         : 研发部测试服务器
Source Server Version : 40113
Source Host           : 172.30.76.71:3306
Source Database       : gift

Target Server Type    : MYSQL
Target Server Version : 40113
File Encoding         : 65001

Date: 2012-05-31 14:28:28
*/

SET FOREIGN_KEY_CHECKS=0;


-- ----------------------------
-- gift_items 礼品表
-- ----------------------------
DROP TABLE IF EXISTS `gift_items`;
CREATE TABLE `gift_items` (
  `i_id` int(11) NOT NULL auto_increment DEFAULT NULL COMMENT '唯一ID',
  `i_no` varchar(500) DEFAULT NULL COMMENT '礼品编号',
  `i_name` varchar(500) DEFAULT NULL COMMENT '礼品名称',
  `i_zlr` int(10) DEFAULT NULL COMMENT '赠礼人信息(对应受赠礼人表gift_peoples的p_id)',
  `i_slr` int(10) DEFAULT NULL COMMENT '受礼人信息(对应受赠礼人表gift_peoples的p_id)',
  `i_sztime` int(10) DEFAULT NULL COMMENT '受赠时间',
  `i_unit` varchar(500) DEFAULT NULL COMMENT '单位(套，件)',
  `i_num` int(10) DEFAULT NULL COMMENT '礼品数量',
  `i_zhidi` varchar(500) DEFAULT NULL COMMENT '礼品质地',
  `i_type` varchar(500) DEFAULT NULL COMMENT '礼品类型',
  `i_chandi` varchar(500) DEFAULT NULL COMMENT '礼品产地',
  `i_status` varchar(500) DEFAULT NULL COMMENT '礼品现状',
  `i_gongyi` varchar(500) DEFAULT NULL COMMENT '礼品工艺',
  `i_background` varchar(500) DEFAULT NULL COMMENT '赠送背景',
  `i_desc` varchar(500) DEFAULT NULL COMMENT '礼品说明',
  `i_memo` varchar(5000) DEFAULT NULL COMMENT '礼品备注',
-- `i_fj` int(10) DEFAULT NULL COMMENT '附件信息(对应受赠礼人表gift_peoples的p_id)',
-- `i_ck` int(10) DEFAULT NULL COMMENT '仓库信息(对应仓库表gift_cangku的ck_id)',
  `i_attribute1` varchar(500) DEFAULT NULL COMMENT '礼品额外属性1',
  `i_attribute2` varchar(500) DEFAULT NULL COMMENT '礼品额外属性2',
  `i_attribute3` varchar(500) DEFAULT NULL COMMENT '礼品额外属性3',
  `i_attribute4` varchar(500) DEFAULT NULL COMMENT '礼品额外属性4',
  `i_attribute5` varchar(500) DEFAULT NULL COMMENT '礼品额外属性5',
  `i_createtime` int(10) DEFAULT NULL COMMENT '创建时间',
  `i_adduser` int(10) DEFAULT NULL COMMENT '创建人',
  `i_qrcode` varchar(500) DEFAULT NULL COMMENT '二维码',
  PRIMARY KEY  (`i_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- gift_peoples 受赠礼人表
-- ----------------------------
DROP TABLE IF EXISTS `gift_peoples`;
CREATE TABLE `gift_peoples` (
  `p_id` int(11) NOT NULL auto_increment DEFAULT NULL COMMENT '受赠礼人唯一ID',
  `p_name` varchar(500) DEFAULT NULL COMMENT '姓名',
  `p_flag` int(10) DEFAULT NULL COMMENT '0赠礼人，1受礼人',
  `p_type` int(10) DEFAULT NULL COMMENT '0本人，1夫人，2夫妇',
  `p_spouse` varchar(500) DEFAULT NULL COMMENT '配偶姓名',
  `p_country` varchar(500) DEFAULT NULL COMMENT '所属国籍',
  `p_bm` varchar(500) DEFAULT NULL COMMENT '所属部门',
  `p_title` varchar(500) DEFAULT NULL COMMENT '头衔',
  `p_attribute1` varchar(500) DEFAULT NULL COMMENT '受赠礼人额外属性1',
  `p_attribute2` varchar(500) DEFAULT NULL COMMENT '受赠礼人额外属性2',
  `p_attribute3` varchar(500) DEFAULT NULL COMMENT '受赠礼人额外属性3',
  `p_attribute4` varchar(500) DEFAULT NULL COMMENT '受赠礼人额外属性4',
  `p_attribute5` varchar(500) DEFAULT NULL COMMENT '受赠礼人额外属性5',
  `p_createtime` int(10) DEFAULT NULL COMMENT '创建时间',
  `p_adduser` int(10) DEFAULT NULL COMMENT '创建人',
PRIMARY KEY (`p_id`) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `gift_peoples` VALUES ('1', '胡锦涛', '1', '0', '刘永清', '中华人民共和国', '2#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('2', '吴邦国', '1', '0', '章瑞珍', '中华人民共和国', '2#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('3', '温家宝', '1', '0', '张培莉', '中华人民共和国', '2#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('4', '贾庆林', '1', '0', '', '中华人民共和国', '2#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('5', '李长春', '1', '0', '', '中华人民共和国', '2#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('6', '习近平', '1', '0', '', '中华人民共和国', '2#4#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('7', '李克强', '1', '0', '', '中华人民共和国', '2#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('8', '贺国强', '1', '0', '', '中华人民共和国', '2#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('9', '周永康', '1', '0', '', '中华人民共和国', '2#', '', '', '', '', '', '', '', '0');

INSERT INTO `gift_peoples` VALUES ('10', '王刚', '1', '0', '', '中华人民共和国', '3#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('11', '王乐泉', '1', '0', '', '中华人民共和国', '3#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('12', '王兆国', '1', '0', '', '中华人民共和国', '3#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('13', '王岐山', '1', '0', '', '中华人民共和国', '3#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('14', '回良玉', '1', '0', '', '中华人民共和国', '3#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('15', '刘淇', '1', '0', '', '中华人民共和国', '3#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('16', '刘云山', '1', '0', '', '中华人民共和国', '3#4#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('17', '刘延东', '1', '0', '', '中华人民共和国', '3#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('18', '李源潮', '1', '0', '', '中华人民共和国', '3#4#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('19', '汪洋', '1', '0', '', '中华人民共和国', '3#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('20', '张高丽', '1', '0', '', '中华人民共和国', '3#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('21', '张德江', '1', '0', '', '中华人民共和国', '3#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('22', '俞正声', '1', '0', '', '中华人民共和国', '3#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('23', '徐才厚', '1', '0', '', '中华人民共和国', '3#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('24', '郭伯雄', '1', '0', '', '中华人民共和国', '3#', '', '', '', '', '', '', '', '0');

INSERT INTO `gift_peoples` VALUES ('25', '何勇', '1', '0', '', '中华人民共和国', '4#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('26', '令计划', '1', '0', '', '中华人民共和国', '4#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('27', '王沪宁', '1', '0', '', '中华人民共和国', '4#', '', '', '', '', '', '', '', '0');

INSERT INTO `gift_peoples` VALUES ('28', '江泽民', '1', '0', '', '中华人民共和国', '5#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('29', '曾庆红', '1', '0', '', '中华人民共和国', '5#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('30', '黄菊', '1', '0', '', '中华人民共和国', '5#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('31', '吴官正', '1', '0', '', '中华人民共和国', '5#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('32', '罗干', '1', '0', '', '中华人民共和国', '5#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('33', '李鹏', '1', '0', '', '中华人民共和国', '5#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('34', '朱镕基', '1', '0', '', '中华人民共和国', '5#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('35', '李瑞环', '1', '0', '', '中华人民共和国', '5#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('36', '尉健行', '1', '0', '', '中华人民共和国', '5#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('37', '李岚清', '1', '0', '', '中华人民共和国', '5#', '', '', '', '', '', '', '', '0');

INSERT INTO `gift_peoples` VALUES ('38', '梁光烈', '1', '0', '', '中华人民共和国', '6#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('39', '马凯', '1', '0', '', '中华人民共和国', '6#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('40', '孟建柱', '1', '0', '', '中华人民共和国', '6#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('41', '戴秉国', '1', '0', '', '中华人民共和国', '6#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('42', '路甬祥', '1', '0', '', '中华人民共和国', '6#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('43', '乌云其木格', '1', '0', '', '中华人民共和国', '6#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('44', '韩启德', '1', '0', '', '中华人民共和国', '6#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('45', '华建敏', '1', '0', '', '中华人民共和国', '6#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('46', '陈至立', '1', '0', '', '中华人民共和国', '6#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('47', '周铁农', '1', '0', '', '中华人民共和国', '6#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('48', '李建国', '1', '0', '', '中华人民共和国', '6#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('49', '司马义·铁力瓦尔地', '1', '0', '', '中华人民共和国', '6#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('50', '蒋树声', '1', '0', '', '中华人民共和国', '6#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('51', '陈昌智', '1', '0', '', '中华人民共和国', '6#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('52', '严隽琪', '1', '0', '', '中华人民共和国', '6#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('53', '桑国卫', '1', '0', '', '中华人民共和国', '6#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('54', '廖晖', '1', '0', '', '中华人民共和国', '6#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('55', '杜青林', '1', '0', '', '中华人民共和国', '6#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('56', '帕巴拉·格列朗杰', '1', '0', '', '中华人民共和国', '6#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('57', '马万祺', '1', '0', '', '中华人民共和国', '6#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('58', '白立忱', '1', '0', '', '中华人民共和国', '6#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('59', '陈奎元', '1', '0', '', '中华人民共和国', '6#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('60', '阿不来提·阿不都热西提', '1', '0', '', '中华人民共和国', '6#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('61', '李兆焯', '1', '0', '', '中华人民共和国', '6#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('62', '黄孟复', '1', '0', '', '中华人民共和国', '6#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('63', '董建华', '1', '0', '', '中华人民共和国', '6#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('64', '张梅颖', '1', '0', '', '中华人民共和国', '6#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('65', '张榕明', '1', '0', '', '中华人民共和国', '6#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('66', '钱运录', '1', '0', '', '中华人民共和国', '6#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('67', '孙家正', '1', '0', '', '中华人民共和国', '6#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('68', '李金华', '1', '0', '', '中华人民共和国', '6#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('69', '郑万通', '1', '0', '', '中华人民共和国', '6#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('70', '邓朴方', '1', '0', '', '中华人民共和国', '6#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('71', '万钢', '1', '0', '', '中华人民共和国', '6#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('72', '林文漪', '1', '0', '', '中华人民共和国', '6#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('73', '厉无畏', '1', '0', '', '中华人民共和国', '6#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('74', '罗富和', '1', '0', '', '中华人民共和国', '6#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('75', '陈宗兴', '1', '0', '', '中华人民共和国', '6#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('76', '王志珍', '1', '0', '', '中华人民共和国', '6#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('77', '何厚铧', '1', '0', '', '中华人民共和国', '6#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('78', '王胜俊', '1', '0', '', '中华人民共和国', '6#', '', '', '', '', '', '', '', '0');
INSERT INTO `gift_peoples` VALUES ('79', '曹建明', '1', '0', '', '中华人民共和国', '6#', '', '', '', '', '', '', '', '0');



-- ----------------------------
-- gift_fujian 附件表
-- ----------------------------
DROP TABLE IF EXISTS `gift_fujian`;
CREATE TABLE `gift_fujian` (
  `fj_id` int(11) NOT NULL auto_increment DEFAULT NULL COMMENT '附件唯一ID',
  `fj_i_no` varchar(500) DEFAULT NULL COMMENT '礼品编号(对应gift_items中i_no)',
  `fj_name` varchar(500) DEFAULT NULL COMMENT '附件名称',
  `fj_path` varchar(500) DEFAULT NULL COMMENT '附件地址',
  `fj_type` varchar(500) DEFAULT NULL COMMENT '附件类型',
  `fj_desc` varchar(500) DEFAULT NULL COMMENT '附件说明',
  `fj_attribute1` varchar(500) DEFAULT NULL COMMENT '附件额外属性1',
  `fj_attribute2` varchar(500) DEFAULT NULL COMMENT '附件额外属性2',
  `fj_attribute3` varchar(500) DEFAULT NULL COMMENT '附件额外属性3',
  `fj_attribute4` varchar(500) DEFAULT NULL COMMENT '附件额外属性4',
  `fj_attribute5` varchar(500) DEFAULT NULL COMMENT '附件额外属性5',
  `fj_createtime` int(10) DEFAULT NULL COMMENT '创建时间',
  `fj_adduser` int(10) DEFAULT NULL COMMENT '创建人',
PRIMARY KEY (`fj_id`) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- gift_cangku 仓库表
-- ----------------------------
DROP TABLE IF EXISTS `gift_cangku`;
CREATE TABLE `gift_cangku` (
  `ck_id` int(11) NOT NULL auto_increment DEFAULT NULL COMMENT '仓库唯一ID',
  `ck_i_no` varchar(500) DEFAULT NULL COMMENT '礼品编号(对应gift_items中i_no)',
  `ck_kufang` varchar(500) DEFAULT NULL COMMENT '库房号',
  `ck_huojia` 	varchar(500) DEFAULT NULL COMMENT '货架号',
  `ck_ceng` varchar(500) DEFAULT NULL COMMENT '层数',
  `ck_attribute1` varchar(500) DEFAULT NULL COMMENT '仓库额外属性1',
  `ck_attribute2` varchar(500) DEFAULT NULL COMMENT '仓库额外属性2',
  `ck_attribute3` varchar(500) DEFAULT NULL COMMENT '仓库额外属性3',
  `ck_attribute4` varchar(500) DEFAULT NULL COMMENT '仓库额外属性4',
  `ck_attribute5` varchar(500) DEFAULT NULL COMMENT '仓库额外属性5',
  `ck_createtime` int(10) DEFAULT NULL COMMENT '创建时间',
  `ck_adduser` int(10) DEFAULT NULL COMMENT '创建人',
PRIMARY KEY (`ck_id`) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `gift_code`
-- ----------------------------
DROP TABLE IF EXISTS `gift_code`;
CREATE TABLE `gift_code` (
  `pk_id` int(11) NOT NULL auto_increment,
  `name` varchar(64) default NULL COMMENT '显示名',
  `code` varchar(64) default NULL COMMENT 'DB中存储的对应的code',
  `parent_code` varchar(64) default NULL COMMENT '父节点。首节点此为"PARENT"',
  `create_time` int(10) default NULL COMMENT '创建时间',
  `add_user_id` int(10) default NULL COMMENT '创建人',
  PRIMARY KEY  (`pk_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `gift_code` VALUES ('1', '受礼人职务', '', 'PARENT', '', '0');
INSERT INTO `gift_code` VALUES ('2', '中央政治局常务委员会委员', '', '1', '', '0');
INSERT INTO `gift_code` VALUES ('3', '中央政治局委员', '', '1', '', '0');
INSERT INTO `gift_code` VALUES ('4', '中央书记处书记', '', '1', '', '0');
INSERT INTO `gift_code` VALUES ('5', '历届党和国家领导人', '', '1', '', '0');
INSERT INTO `gift_code` VALUES ('6', '四副两高', '', '1', '', '0');



-- ---------------------------------以下为user，group，operation，log等------------------------------------------------
-- ----------------------------
-- Table structure for `gift_log`
-- ----------------------------
DROP TABLE IF EXISTS `gift_log`;
CREATE TABLE `gift_log` (
  `log_id` int(11) NOT NULL auto_increment,
  `log_content` varchar(255) default NULL,
  `log_uri` varchar(50) default NULL,
  `log_type` varchar(50) default NULL COMMENT '日志类型',
  `add_time` int(10) default NULL COMMENT '添加时间',
  `add_user_id` int(10) default NULL COMMENT '添加人',
  PRIMARY KEY  (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `gift_user`
-- ----------------------------
DROP TABLE IF EXISTS `gift_user`;
CREATE TABLE `gift_user` (
  `user_id` int(11) NOT NULL auto_increment,
  `user_name` varchar(50) default NULL,
  `nick_name` varchar(50) default NULL,
  `password` varchar(50) default NULL,
  `user_img` varchar(100) default NULL COMMENT '照片',
  `user_title` varchar(50) default NULL COMMENT '头衔',
  `user_type` char(1) default NULL COMMENT '用户类型：系统使用者1，接受礼品者0，两者都是2',
  `user_admin` char(1) default NULL COMMENT '系统使用者类型：超级管理员1，一般管理员2，普通用户3',
  `is_close` char(1) default NULL COMMENT '是否关闭：关闭1，开启0',
  `create_time` int(10) default NULL COMMENT '创建时间',
  `add_user_id` int(10) default NULL COMMENT '创建人',
  PRIMARY KEY  (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `gift_user` VALUES ('1', 'admin', '超级管理员', '123456', '1', '超级管理员', '1', '1', '0', '2', '1');
INSERT INTO `gift_user` VALUES ('3', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `gift_user` VALUES ('4', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `gift_user` VALUES ('5', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1');


-- ----------------------------
-- Table structure for `gift_operation`
-- ----------------------------
DROP TABLE IF EXISTS `gift_operation`;
CREATE TABLE `gift_operation` (
  `oper_id` int(11) NOT NULL auto_increment,
  `oper_name` varchar(50) default NULL COMMENT '权限名称',
  `oper_parent` varchar(50) default NULL COMMENT '父节点',
  `oper_uri` varchar(50) default NULL COMMENT '操作链接（即Action）',
  `create_time` int(10) default NULL COMMENT '创建时间',
  `add_user_id` int(10) default NULL COMMENT '创建人',
  PRIMARY KEY  (`oper_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `gift_user_operation`
-- ----------------------------
DROP TABLE IF EXISTS `gift_user_operation`;
CREATE TABLE `gift_user_operation` (
  `uo_id` int(11) NOT NULL auto_increment,
  `user_id` int(11) default NULL COMMENT '组ID(对应gift_users中user_id)',
  `oper_id` int(11) default NULL COMMENT '权限ID(对应gift_operation中oper_id)',
  `add_time` int(10) default NULL COMMENT '创建时间',
  `add_user_id` int(10) default NULL COMMENT '创建人',
  PRIMARY KEY  (`uo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `gift_group`
-- ----------------------------
DROP TABLE IF EXISTS `gift_group`;
CREATE TABLE `gift_group` (
  `group_id` int(11) NOT NULL auto_increment,
  `group_name` varchar(50) default NULL COMMENT '组名',
  `group_parent` varchar(50) default NULL COMMENT '父节点',
  `group__desc` varchar(50) default NULL COMMENT '组描述',
  `create_time` int(10) default NULL COMMENT '创建时间',
  `add_user_id` int(10) default NULL COMMENT '创建人',
  PRIMARY KEY  (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `gift_group` VALUES ('2', '12', '12', '12', '12', '12');
INSERT INTO `gift_group` VALUES ('3', '1', '1', '1', '1', '1');
INSERT INTO `gift_group` VALUES ('7', '1', '1', '1', '1', '1');
INSERT INTO `gift_group` VALUES ('8', '1', '1', '1', '1', '1');
INSERT INTO `gift_group` VALUES ('9', '1', '1', '1', '1', '1');
INSERT INTO `gift_group` VALUES ('10', '1', '1', '1', '1', '1');

-- ----------------------------
-- Table structure for `gift_group_operation`
-- ----------------------------
DROP TABLE IF EXISTS `gift_group_operation`;
CREATE TABLE `gift_group_operation` (
  `go_id` int(11) NOT NULL auto_increment,
  `group_id` int(11) default NULL COMMENT '组ID(对应gift_group中group_id)',
  `oper_id` int(11) default NULL COMMENT '权限ID(对应gift_operation中oper_id)',
  `add_time` int(10) default NULL COMMENT '创建时间',
  `add_user_id` int(10) default NULL COMMENT '创建人',
  PRIMARY KEY  (`go_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `gift_group_user`
-- ----------------------------
DROP TABLE IF EXISTS `gift_group_user`;
CREATE TABLE `gift_group_user` (
  `gu_id` int(11) NOT NULL auto_increment,
  `group_id` int(11) default NULL COMMENT '组ID(对应gift_group中group_id)',
  `user_id` int(11) default NULL COMMENT '组ID(对应gift_users中user_id)',
  `add_time` int(10) default NULL COMMENT '添加时间',
  `add_user_id` int(10) default NULL COMMENT '创建人',
  PRIMARY KEY  (`gu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `gift_group_user` VALUES ('1', '1', '1', '1', '1');

