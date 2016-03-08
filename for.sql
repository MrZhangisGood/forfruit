SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS CUS_USER;




/* Create Tables */

CREATE TABLE CUS_USER
(
	-- 用户id
	USER_ID char(32) NOT NULL COMMENT '用户id',
	-- 用户编码
	USER_NO int(8) DEFAULT 10000001 NOT NULL COMMENT '用户编码',
	-- 用户名称
	LOGIN_NAME varchar(50) COMMENT '用户名称',
	-- 密码
	PASSWORD char(32) COMMENT '密码',
	-- 用户名称
	USER_NAME varchar(50) COMMENT '用户名称',
	-- 性别
	SEX int(1) DEFAULT 0 COMMENT '性别',
	-- 用户头像
	HEAD_IMAGE_URL varchar(80) COMMENT '用户头像',
	-- 手机号码
	PHONE varchar(15) COMMENT '手机号码',
	-- 手机号码是否已经验证 0验证不通过  1验证通过
	PHONE_VERIFY int(1) DEFAULT 0 COMMENT '手机号码是否已经验证 0验证不通过  1验证通过',
	-- 邮箱
	EMAIL varchar(30) COMMENT '邮箱',
	-- 0 未通过  1 通过
	EMAIL_VERIFY int(1) DEFAULT 0 COMMENT '0 未通过  1 通过',
	-- qq号码
	QQ int(11) COMMENT 'qq号码',
	-- 积分
	INTEGRAL bigint COMMENT '积分',
	-- 备注
	REMARK varchar(300) COMMENT '备注',
	-- 是否删除 - 采用逻辑删除
	IS_DELETE int(1) DEFAULT 0 COMMENT '是否删除 - 采用逻辑删除',
	-- 创建时间
	CREATE_DATE timestamp COMMENT '创建时间',
	-- 修改时间
	UPDATE_DATE timestamp COMMENT '修改时间',
	PRIMARY KEY (USER_ID),
	UNIQUE (USER_NO)
) DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;



