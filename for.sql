use forfruit;
/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50022
Source Host           : localhost:3306
Source Database       : forfruit

Target Server Type    : MYSQL
Target Server Version : 50022
File Encoding         : 65001

Date: 2016-03-11 22:27:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `cus_user`
-- ----------------------------
DROP TABLE IF EXISTS `cus_user`;
CREATE TABLE `cus_user` (
	`USER_ID` char(32) NOT NULL COMMENT '用户id',
	`USER_NO` int(8) unsigned zerofill NOT NULL auto_increment COMMENT '用户编码',
	`LOGIN_NAME` varchar(50) default NULL COMMENT '用户名称',
	`PASSWORD` char(32) default NULL COMMENT '密码',
	`USER_NAME` varchar(50) default NULL COMMENT '用户名称',
	`SEX` int(1) default '0' COMMENT '性别',
	`HEAD_IMAGE_URL` varchar(80) default NULL COMMENT '用户头像',
	`PHONE` varchar(15) default NULL COMMENT '手机号码',
	`PHONE_VERIFY` int(1) default '0' COMMENT '手机号码是否已经验证 0验证不通过  1验证通过',
	`EMAIL` varchar(30) default NULL COMMENT '邮箱',
	`EMAIL_VERIFY` int(1) default '0' COMMENT '0 未通过  1 通过',
	`QQ` int(11) default NULL COMMENT 'qq号码',
	`INTEGRAL` bigint(20) default NULL COMMENT '积分',
	`REMARK` varchar(300) default NULL COMMENT '备注',
	`IS_DELETE` int(1) default '0' COMMENT '是否删除 - 采用逻辑删除',
	`CREATE_DATE` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '创建时间',
	`UPDATE_DATE` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '修改时间',
	PRIMARY KEY  (`USER_ID`),
	UNIQUE KEY `USER_NO` (`USER_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cus_user
-- ----------------------------
INSERT INTO `cus_user` VALUES ('402881e6535944e501535948dd600003', '00000001', 'zlm', '21232f297a57a5a743894a0e4a801fc3', null, '0', null, null, '0', null, '0', null, null, null, '0', '2016-03-09 10:51:00', '2016-03-09 10:51:00');
INSERT INTO `cus_user` VALUES ('402881e6535a1ddb01535a20765c0002', '00000002', 'xiao', '21232f297a57a5a743894a0e4a801fc3', null, '0', null, null, '0', null, '0', null, null, null, '0', '2016-03-09 14:46:29', '2016-03-09 14:46:29');
