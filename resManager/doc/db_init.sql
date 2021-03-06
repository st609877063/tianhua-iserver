SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS res_items;
CREATE TABLE res_items (
  i_id int(11) NOT NULL auto_increment DEFAULT NULL COMMENT '唯一ID',
  i_no varchar(100) DEFAULT NULL COMMENT '食品编号',
  i_name varchar(100) DEFAULT NULL COMMENT '食品名称',
  i_img varchar(100) DEFAULT NULL COMMENT '食品图片',
  i_imgpath varchar(100) DEFAULT NULL COMMENT '食品图片路径',
  i_money varchar(100) default NULL COMMENT '价格',
  i_desc varchar(100) DEFAULT NULL COMMENT '食品说明',
  i_memo varchar(1000) DEFAULT NULL COMMENT '食品备注',
  i_type char(1) default '1' COMMENT '主副食品，1:主食 2：副食',
  
  i_createtime int(10) DEFAULT NULL COMMENT '创建时间',
  i_adduser int(10) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY  (i_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS res_items_img;
CREATE TABLE res_items_img (
  pk_id int(11) NOT NULL auto_increment DEFAULT NULL COMMENT '主键ID',
  i_id int(11) DEFAULT NULL COMMENT '食品ID',
  i_no varchar(100) DEFAULT NULL COMMENT '食品编号',
  i_img varchar(100) DEFAULT NULL COMMENT '食品图片',
  i_imgpath varchar(100) DEFAULT NULL COMMENT '食品图片路径',
  PRIMARY KEY  (pk_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS res_menus;
CREATE TABLE res_menus (
  pk_id 	int(11) NOT NULL auto_increment DEFAULT NULL COMMENT '主键ID',
  i_id 		int(11) DEFAULT NULL COMMENT '食品ID',
  i_no 		varchar(100) DEFAULT NULL COMMENT '食品编号',
  m_money 	varchar(100) default NULL COMMENT '价格',
  m_memo 	varchar(100) DEFAULT NULL COMMENT '食品备注',
  m_date 	varchar(100) DEFAULT NULL COMMENT '时间',
  m_type 	char(1) default '1' COMMENT '1:午餐 2：晚餐 3：生食',
  
  m_createtime int(10) DEFAULT NULL COMMENT '创建时间',
  m_adduser int(10) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY  (pk_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS res_orders;
CREATE TABLE res_orders (
  pk_id 	int(11) NOT NULL auto_increment DEFAULT NULL COMMENT '主键ID',
  o_no 		int(11) DEFAULT NULL COMMENT '订单编号',
  o_user 	varchar(100) DEFAULT NULL COMMENT '用户名',
  o_phone 	int(11)  DEFAULT NULL COMMENT '用户手机号',
  o_date 	varchar(100) DEFAULT NULL COMMENT '时间',
  o_type 	char(1) default '1' COMMENT '1:午餐 2：晚餐 3：生食',
  o_memo 	varchar(100) DEFAULT NULL COMMENT '备注',
  o_fee 	varchar(100) DEFAULT NULL COMMENT '费用',
  
  o_createtime int(10) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY  (pk_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS res_order_menu;
CREATE TABLE res_order_menu (
  pk_id 	int(11) NOT NULL auto_increment DEFAULT NULL COMMENT '主键ID',
  o_no 		int(11) DEFAULT NULL COMMENT '订单编号',
  i_id 		int(11) DEFAULT NULL COMMENT '食品ID',
  i_no 		varchar(100) DEFAULT NULL COMMENT '食品编号',
  om_money 	varchar(100) default NULL COMMENT '价格',
  om_num 	int(11) default NULL COMMENT '份数',
  om_total 	varchar(100) default NULL COMMENT '价格',
  
  om_createtime int(10) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY  (pk_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for res_user
-- ----------------------------
DROP TABLE IF EXISTS res_user;
CREATE TABLE res_user (
  user_id int(11) NOT NULL auto_increment,
  user_name varchar(50) default NULL,
  nick_name varchar(50) default NULL,
  password varchar(50) default NULL,
  user_img varchar(100) default NULL COMMENT '照片',
  user_title varchar(50) default NULL COMMENT '头衔',
  user_type char(1) default NULL COMMENT '用户类型：系统使用者1，接受礼品者0，两者都是2',
  user_admin char(1) default NULL COMMENT '系统使用者类型：超级管理员1，一般管理员2，普通用户3',
  is_close char(1) default NULL COMMENT '是否关闭：关闭1，开启0',
  create_time int(10) default NULL COMMENT '创建时间',
  add_user_id int(10) default NULL COMMENT '创建人',
  PRIMARY KEY  (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO res_user VALUES ('1', 'admin', '超级管理员', '123456', '1', '超级管理员', '1', '1', '0', '2', '1');
INSERT INTO res_user VALUES ('3', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO res_user VALUES ('4', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO res_user VALUES ('5', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1');
