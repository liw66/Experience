/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : experience

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-08-16 17:56:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_dept`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `deptid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parentid` bigint(20) DEFAULT NULL COMMENT '上级id',
  `deptname` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `sort` int(11) DEFAULT NULL COMMENT '序号',
  `del` int(11) DEFAULT NULL COMMENT '是否删除 0 否 1 是',
  `state` int(11) DEFAULT NULL COMMENT '状态 0 禁用 1 启用',
  `createdat` datetime DEFAULT NULL COMMENT '创建时间',
  `createdby` varchar(50) DEFAULT NULL COMMENT '创建人',
  `updatedat` datetime DEFAULT NULL COMMENT '更新时间',
  `updatedby` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`deptid`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='部门信息表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', null, '总部', '1', null, '1', null, null, '2019-06-18 09:12:41', null);
INSERT INTO `sys_dept` VALUES ('5', '1', '财务部', null, null, '1', null, null, null, null);
INSERT INTO `sys_dept` VALUES ('16', '1', '行政部', null, null, '1', null, null, null, null);
INSERT INTO `sys_dept` VALUES ('18', '1', '开发部', null, null, '1', null, null, null, null);

-- ----------------------------
-- Table structure for `sys_dict`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `dictid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '字典id',
  `name` varchar(500) DEFAULT NULL COMMENT '数据名',
  `value` varchar(500) DEFAULT NULL COMMENT '数据值',
  `type` varchar(500) DEFAULT NULL COMMENT '类型',
  `desc` varchar(500) DEFAULT NULL COMMENT '类型描述',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `sort` int(11) DEFAULT NULL COMMENT '序号',
  `del` tinyint(1) unsigned DEFAULT NULL COMMENT '是否删除 0 否 1 是',
  `createdat` datetime DEFAULT NULL COMMENT '创建时间',
  `createdby` varchar(50) DEFAULT NULL COMMENT '创建人',
  `updatedat` datetime DEFAULT NULL COMMENT '更新时间',
  `updatedby` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`dictid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='字典信息表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('1', '未知的性别', '0', 'sex', '性别', '', '1', null, '2019-08-16 08:47:13', 'admin', '2019-08-16 09:34:08', 'admin');
INSERT INTO `sys_dict` VALUES ('2', '男性', '1', 'sex', '性别', '', '2', null, '2019-08-16 09:35:38', 'admin', null, null);
INSERT INTO `sys_dict` VALUES ('3', '女性', '2', 'sex', '性别', '', '3', null, '2019-08-16 09:36:23', 'admin', '2019-08-16 09:56:01', 'admin');
INSERT INTO `sys_dict` VALUES ('4', '未说明的性别', '9', 'sex', '性别', '', '4', null, '2019-08-16 09:36:46', 'admin', '2019-08-16 09:56:05', 'admin');

-- ----------------------------
-- Table structure for `sys_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `logid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '日志id',
  `userid` bigint(20) DEFAULT NULL COMMENT '用户id',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `oper` varchar(500) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(500) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `result` varchar(5000) DEFAULT NULL COMMENT '返回值',
  `reqtype` varchar(50) DEFAULT NULL COMMENT '请求类型 GET POST',
  `logtype` tinyint(1) unsigned DEFAULT NULL COMMENT '日志类型 0 登录 1 操作 2 异常',
  `ip` varchar(50) DEFAULT NULL COMMENT '请求ip',
  `time` int(11) DEFAULT NULL COMMENT '响应时间',
  `del` tinyint(1) unsigned DEFAULT '0' COMMENT '是否删除 0 未删除 1 删除',
  `createdat` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`logid`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8 COMMENT='系统日志表';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('77', '7', 'admin', '登录', 'com.li.experience.modules.sys.controller.LoginController.login', '{\"password\":\"admin\",\"username\":\"admin\"}', 'com.li.experience.common.core.Result@342ab95b', 'POST', '0', '172.20.149.28', '16', '0', '2019-08-14 10:13:47');
INSERT INTO `sys_log` VALUES ('78', '7', 'admin', '登录', 'com.li.experience.modules.sys.controller.LoginController.login', '{\"password\":\"admin\",\"username\":\"admin\"}', 'com.li.experience.common.core.Result@2753ec0a', 'POST', '0', '172.20.149.28', '11', '0', '2019-08-15 09:10:23');
INSERT INTO `sys_log` VALUES ('79', '7', 'admin', '登录', 'com.li.experience.modules.sys.controller.LoginController.login', '{\"password\":\"admin\",\"username\":\"admin\"}', '', 'POST', '0', '172.20.149.28', '6', '0', '2019-08-16 02:41:01');
INSERT INTO `sys_log` VALUES ('80', '7', 'admin', '登录', 'com.li.experience.modules.sys.controller.LoginController.login', '{\"password\":\"admin\",\"username\":\"admin\"}', 'com.li.experience.common.core.Result@3be31aeb', 'POST', '0', '172.20.149.28', '5', '0', '2019-08-16 03:41:36');
INSERT INTO `sys_log` VALUES ('81', '7', 'admin', '登录', 'com.li.experience.modules.sys.controller.LoginController.login', '{\"password\":\"admin\",\"username\":\"admin\"}', 'com.li.experience.common.core.Result@44bc9f1', 'POST', '0', '172.20.149.28', '6', '0', '2019-08-16 03:55:20');
INSERT INTO `sys_log` VALUES ('82', '7', 'admin', '登录', 'com.li.experience.modules.sys.controller.LoginController.login', '{\"password\":\"admin\",\"username\":\"admin\"}', 'com.li.experience.common.core.Result@2a1c6581', 'POST', '0', '172.20.149.28', '2', '0', '2019-08-16 05:34:40');
INSERT INTO `sys_log` VALUES ('83', '7', 'admin', '登录', 'com.li.experience.modules.sys.controller.LoginController.login', '{\"password\":\"admin\",\"username\":\"admin\"}', 'com.li.experience.common.core.Result@7e62135b', 'POST', '0', '172.20.149.28', '9', '0', '2019-08-16 05:36:05');
INSERT INTO `sys_log` VALUES ('84', '7', 'admin', '登录', 'com.li.experience.modules.sys.controller.LoginController.login', '{\"password\":\"admin\",\"username\":\"admin\"}', 'com.li.experience.common.core.Result@3b51917f', 'POST', '0', '172.20.149.28', '9', '0', '2019-08-16 05:37:02');
INSERT INTO `sys_log` VALUES ('85', '7', 'admin', '登录', 'com.li.experience.modules.sys.controller.LoginController.login', '{\"password\":\"admin\",\"username\":\"admin\"}', 'com.li.experience.common.core.Result@68b72a0c', 'POST', '0', '172.20.149.28', '11', '0', '2019-08-16 05:40:49');
INSERT INTO `sys_log` VALUES ('86', '7', 'admin', '登录', 'com.li.experience.modules.sys.controller.LoginController.login', '{\"password\":\"admin\",\"username\":\"admin\"}', 'com.li.experience.common.core.Result@160aed61', 'POST', '0', '172.20.149.28', '13', '0', '2019-08-16 05:48:50');
INSERT INTO `sys_log` VALUES ('87', '7', 'admin', '登录', 'com.li.experience.modules.sys.controller.LoginController.login', '{\"password\":\"admin\",\"username\":\"admin\"}', 'com.li.experience.common.core.Result@9afb8ce', 'POST', '0', '172.20.149.28', '11', '0', '2019-08-16 06:00:26');
INSERT INTO `sys_log` VALUES ('88', '7', 'admin', '登录', 'com.li.experience.modules.sys.controller.LoginController.login', '{\"password\":\"admin\",\"username\":\"admin\"}', 'com.li.experience.common.core.Result@463be12b', 'POST', '0', '172.20.149.28', '13', '0', '2019-08-16 06:47:18');
INSERT INTO `sys_log` VALUES ('89', '7', 'admin', '登录', 'com.li.experience.modules.sys.controller.LoginController.login', '{\"password\":\"admin\",\"username\":\"admin\"}', 'com.li.experience.common.core.Result@28e63334', 'POST', '0', '172.20.149.28', '16', '0', '2019-08-16 08:43:44');
INSERT INTO `sys_log` VALUES ('90', '7', 'admin', '登录', 'com.li.experience.modules.sys.controller.LoginController.login', '{\"password\":\"admin\",\"username\":\"admin\"}', 'com.li.experience.common.core.Result@215b2aff', 'POST', '0', '172.20.149.28', '9', '0', '2019-08-16 09:21:11');
INSERT INTO `sys_log` VALUES ('91', '7', 'admin', '登录', 'com.li.experience.modules.sys.controller.LoginController.login', '{\"password\":\"admin\",\"username\":\"admin\"}', 'com.li.experience.common.core.Result@36073131', 'POST', '0', '172.20.149.28', '8', '0', '2019-08-16 09:25:54');
INSERT INTO `sys_log` VALUES ('92', '7', 'admin', '登录', 'com.li.experience.modules.sys.controller.LoginController.login', '{\"password\":\"admin\",\"username\":\"admin\"}', 'com.li.experience.common.core.Result@358d5205', 'POST', '0', '172.20.149.28', '8', '0', '2019-08-16 09:51:44');
INSERT INTO `sys_log` VALUES ('93', '7', 'admin', '登录', 'com.li.experience.modules.sys.controller.LoginController.login', '{\"password\":\"admin\",\"username\":\"admin\"}', 'com.li.experience.common.core.Result@42ff6f24', 'POST', '0', '172.20.149.28', '6', '0', '2019-08-16 09:55:10');

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menuid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `parentid` bigint(20) unsigned DEFAULT NULL COMMENT '上级id',
  `menuname` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(50) DEFAULT NULL COMMENT '菜单地址',
  `perms` varchar(50) DEFAULT NULL COMMENT '权限标志',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `type` tinyint(1) unsigned DEFAULT NULL COMMENT '菜单类型 0 目录 1 菜单 2 按钮',
  `sort` int(11) unsigned DEFAULT NULL COMMENT '序号',
  `del` tinyint(1) unsigned DEFAULT '0' COMMENT '是否删除 0 未删除 1 删除',
  `state` tinyint(1) unsigned DEFAULT '1' COMMENT '状态 0 禁用 1 启用',
  `createdat` datetime DEFAULT NULL COMMENT '创建时间',
  `createdby` varchar(50) DEFAULT NULL COMMENT '创建人',
  `updatedat` datetime DEFAULT NULL COMMENT '更新时间',
  `updatedby` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`menuid`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='系统菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', null, '系统管理', '', '', 'fa fa-desktop', '0', '0', '0', '1', '2019-06-26 06:47:58', null, '2019-06-27 03:10:34', null);
INSERT INTO `sys_menu` VALUES ('2', '1', '部门管理', 'sys/dept', '', 'fa fa-users', '1', '0', '0', '1', '2019-06-26 06:53:49', null, '2019-06-27 03:11:24', null);
INSERT INTO `sys_menu` VALUES ('3', '1', '用户管理', 'sys/user', '', 'fa fa-user', '1', '0', '0', '1', '2019-06-26 07:11:14', null, '2019-07-11 02:10:39', null);
INSERT INTO `sys_menu` VALUES ('4', '3', '新增', '', 'sys:user:add', '', '2', '0', '0', '1', '2019-06-27 03:33:13', null, null, null);
INSERT INTO `sys_menu` VALUES ('5', '3', '编辑', '', 'sys:user:edit', '', '2', '0', '0', '1', '2019-06-27 03:49:34', null, null, null);
INSERT INTO `sys_menu` VALUES ('6', '3', '删除', '', 'sys:user:remove', '', '2', '0', '0', '1', '2019-06-27 03:49:59', null, '2019-06-27 03:50:09', null);
INSERT INTO `sys_menu` VALUES ('7', '1', '菜单管理', 'sys/menu', '', 'fa fa-th-list', '1', null, null, null, '2019-06-28 07:59:21', null, '2019-06-28 08:01:46', null);
INSERT INTO `sys_menu` VALUES ('9', null, '系统监控', '', '', 'fa fa-dashboard', '0', null, null, null, '2019-06-28 08:04:27', null, null, null);
INSERT INTO `sys_menu` VALUES ('10', '9', '请求追踪', '/sys/monitor/httptrace', '', 'fa fa-bars', '1', null, null, null, '2019-06-28 08:05:32', null, '2019-06-28 09:14:16', null);
INSERT INTO `sys_menu` VALUES ('11', '9', 'JVM信息', '/sys/monitor/jvminfo', '', 'fa fa-bar-chart', '1', null, null, null, '2019-06-28 08:07:15', null, '2019-06-28 09:13:53', null);
INSERT INTO `sys_menu` VALUES ('12', '9', '服务器信息', '/sys/monitor/systeminfo', '', 'fa fa-gears', '1', null, null, null, '2019-06-28 08:07:49', null, '2019-06-28 09:13:42', null);
INSERT INTO `sys_menu` VALUES ('13', '9', 'Tomcat信息', '/sys/monitor/tomcatinfo', '', 'fa fa-cog', '1', null, null, null, '2019-06-28 08:08:24', null, '2019-06-28 09:12:57', null);
INSERT INTO `sys_menu` VALUES ('14', '1', '角色管理', 'sys/role', '', 'fa fa-vcard-o', '1', null, null, null, '2019-06-28 09:09:47', null, '2019-06-28 09:10:31', null);
INSERT INTO `sys_menu` VALUES ('15', '9', 'druid监控', 'druid', '', 'fa fa-wrench', '1', null, null, null, '2019-07-01 02:17:18', null, '2019-07-01 02:22:56', null);
INSERT INTO `sys_menu` VALUES ('16', '9', 'swagger2', 'swagger-ui.html', '', 'fa fa-server', '1', null, null, null, '2019-07-01 02:21:54', null, null, null);
INSERT INTO `sys_menu` VALUES ('17', '1', '在线用户', 'sys/online', '', 'fa fa-upload', '1', null, null, null, '2019-07-12 09:30:40', null, '2019-08-16 03:55:39', null);
INSERT INTO `sys_menu` VALUES ('18', '1', '代码生成', 'sys/code', '', 'fa fa-gears', '1', null, null, null, '2019-07-26 09:40:27', null, null, null);
INSERT INTO `sys_menu` VALUES ('19', '1', '字典管理', 'sys/dict', '', 'fa fa-database', '1', null, null, null, '2019-08-16 03:57:47', null, null, null);

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `roleid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `rolename` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `rolecode` varchar(50) DEFAULT NULL COMMENT '角色编码',
  `sort` int(11) unsigned DEFAULT NULL COMMENT '序号',
  `del` tinyint(1) unsigned DEFAULT '0' COMMENT '是否删除 0 未删除 1 删除',
  `state` tinyint(1) unsigned DEFAULT '1' COMMENT '状态 0 禁用 1 启用',
  `remark` varchar(50) DEFAULT NULL COMMENT '角色备注',
  `createdat` datetime DEFAULT NULL COMMENT '创建时间',
  `createdby` varchar(50) DEFAULT NULL COMMENT '创建人',
  `updatedat` datetime DEFAULT NULL COMMENT '更新时间',
  `updatedby` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`roleid`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('12', '超级管理员', 'super', null, null, null, '', '2019-07-03 02:03:24', null, '2019-08-16 03:58:24', null);
INSERT INTO `sys_role` VALUES ('13', '系统管理员', 'admin', null, null, null, '', '2019-07-05 07:50:28', null, '2019-08-16 03:58:19', null);

-- ----------------------------
-- Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `roleid` bigint(20) unsigned DEFAULT NULL COMMENT '角色id',
  `menuid` bigint(20) unsigned DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=389 DEFAULT CHARSET=utf8 COMMENT='角色菜单关联表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('351', '13', '0');
INSERT INTO `sys_role_menu` VALUES ('352', '13', '1');
INSERT INTO `sys_role_menu` VALUES ('353', '13', '2');
INSERT INTO `sys_role_menu` VALUES ('354', '13', '3');
INSERT INTO `sys_role_menu` VALUES ('355', '13', '4');
INSERT INTO `sys_role_menu` VALUES ('356', '13', '5');
INSERT INTO `sys_role_menu` VALUES ('357', '13', '6');
INSERT INTO `sys_role_menu` VALUES ('358', '13', '7');
INSERT INTO `sys_role_menu` VALUES ('359', '13', '9');
INSERT INTO `sys_role_menu` VALUES ('360', '13', '10');
INSERT INTO `sys_role_menu` VALUES ('361', '13', '11');
INSERT INTO `sys_role_menu` VALUES ('362', '13', '12');
INSERT INTO `sys_role_menu` VALUES ('363', '13', '13');
INSERT INTO `sys_role_menu` VALUES ('364', '13', '14');
INSERT INTO `sys_role_menu` VALUES ('365', '13', '15');
INSERT INTO `sys_role_menu` VALUES ('366', '13', '16');
INSERT INTO `sys_role_menu` VALUES ('367', '13', '17');
INSERT INTO `sys_role_menu` VALUES ('368', '13', '18');
INSERT INTO `sys_role_menu` VALUES ('369', '13', '19');
INSERT INTO `sys_role_menu` VALUES ('370', '12', '0');
INSERT INTO `sys_role_menu` VALUES ('371', '12', '1');
INSERT INTO `sys_role_menu` VALUES ('372', '12', '2');
INSERT INTO `sys_role_menu` VALUES ('373', '12', '3');
INSERT INTO `sys_role_menu` VALUES ('374', '12', '4');
INSERT INTO `sys_role_menu` VALUES ('375', '12', '5');
INSERT INTO `sys_role_menu` VALUES ('376', '12', '6');
INSERT INTO `sys_role_menu` VALUES ('377', '12', '7');
INSERT INTO `sys_role_menu` VALUES ('378', '12', '9');
INSERT INTO `sys_role_menu` VALUES ('379', '12', '10');
INSERT INTO `sys_role_menu` VALUES ('380', '12', '11');
INSERT INTO `sys_role_menu` VALUES ('381', '12', '12');
INSERT INTO `sys_role_menu` VALUES ('382', '12', '13');
INSERT INTO `sys_role_menu` VALUES ('383', '12', '14');
INSERT INTO `sys_role_menu` VALUES ('384', '12', '15');
INSERT INTO `sys_role_menu` VALUES ('385', '12', '16');
INSERT INTO `sys_role_menu` VALUES ('386', '12', '17');
INSERT INTO `sys_role_menu` VALUES ('387', '12', '18');
INSERT INTO `sys_role_menu` VALUES ('388', '12', '19');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `userid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `deptid` bigint(20) DEFAULT NULL COMMENT '部门id',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `realname` varchar(50) DEFAULT NULL COMMENT '姓名',
  `sex` int(11) DEFAULT NULL COMMENT '性别 0 未知的性别 1 男性 2 女性 9 未说明的性别',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `moblie` varchar(50) DEFAULT NULL COMMENT '电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `job` varchar(50) DEFAULT NULL COMMENT '职务',
  `sort` int(11) DEFAULT NULL COMMENT '序号',
  `del` int(11) DEFAULT NULL COMMENT '是否删除 0 否 1 是',
  `state` int(11) DEFAULT NULL COMMENT '状态 0 禁用 1 启用',
  `createdat` datetime DEFAULT NULL COMMENT '创建时间',
  `createdby` varchar(50) DEFAULT NULL COMMENT '创建人',
  `updatedat` datetime DEFAULT NULL COMMENT '更新时间',
  `updatedby` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('2', '18', 'super', '0f69d66b6772e3d3e91626f95c29c076', '', '9', null, '', '', '总监', null, null, '1', null, null, '2019-07-12 02:01:27', 'admin');
INSERT INTO `sys_user` VALUES ('4', '18', 'dossier', '2222', '发士大夫', '0', null, '', '', '', null, null, '1', null, null, '2019-07-05 06:52:38', null);
INSERT INTO `sys_user` VALUES ('7', '18', 'admin', 'f6fdffe48c908deb0f4c3bd36c032e72', '123', '1', null, '111', '111', '', null, null, '1', null, null, '2019-07-15 07:11:11', 'admin');
INSERT INTO `sys_user` VALUES ('8', '5', 'gl1', '', '士大夫', '2', null, '18811111111', '111', '', null, null, '0', null, null, '2019-07-05 06:53:30', null);
INSERT INTO `sys_user` VALUES ('15', '16', 'test', '', '', '1', null, '', '', '', null, null, '0', null, null, '2019-07-05 06:13:44', null);

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `userid` bigint(20) unsigned DEFAULT NULL COMMENT '用户id',
  `roleid` bigint(20) unsigned DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('29', '2', '12');
INSERT INTO `sys_user_role` VALUES ('31', '7', '13');
