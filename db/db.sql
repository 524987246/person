/*
Navicat MySQL Data Transfer

Source Server         : localhost-mysql
Source Server Version : 50149
Source Host           : localhost:3306
Source Database       : db_person

Target Server Type    : MYSQL
Target Server Version : 50149
File Encoding         : 65001

Date: 2018-01-31 13:25:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for buz_linkman
-- ----------------------------
DROP TABLE IF EXISTS `buz_linkman`;
CREATE TABLE `buz_linkman` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(60) NOT NULL COMMENT '姓名',
  `dept` varchar(60) DEFAULT NULL COMMENT '部门',
  `phone` varchar(60) DEFAULT NULL COMMENT '手机',
  `age` varchar(60) DEFAULT NULL COMMENT '年龄',
  `create_by` int(10) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` int(10) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(2000) DEFAULT NULL COMMENT '备注',
  `isemploy` int(1) NOT NULL DEFAULT '1' COMMENT '状态:1启用2禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='联系人表';

-- ----------------------------
-- Records of buz_linkman
-- ----------------------------
INSERT INTO `buz_linkman` VALUES ('1', '123', '123', '123123', '123', '1', '2018-01-22 00:00:00', '1', '2018-01-22 00:00:00', null, '1');
INSERT INTO `buz_linkman` VALUES ('2', '321', '321', '123', '321', '1', '2018-01-22 00:00:00', '1', '2018-01-22 00:00:00', null, '1');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '名称',
  `parent_id` int(10) NOT NULL COMMENT '归属部门id',
  `create_by` int(10) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` int(10) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  `isemploy` char(1) COLLATE utf8_bin NOT NULL DEFAULT '1' COMMENT '删除标记 1启用 2禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='部门表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', '公司', '0', '0', '2017-09-29 14:05:07', '0', '2017-09-29 14:05:10', null, '1');
INSERT INTO `sys_dept` VALUES ('2', '技术部', '1', '0', '2017-09-29 14:05:07', '0', '2017-09-29 14:05:10', null, '1');
INSERT INTO `sys_dept` VALUES ('3', '研发部', '1', '0', '2017-09-29 14:05:07', '0', '2017-09-29 14:05:10', null, '1');
INSERT INTO `sys_dept` VALUES ('4', '市场部', '1', '0', '2017-09-29 14:05:07', '0', '2017-09-29 14:05:10', null, '1');
INSERT INTO `sys_dept` VALUES ('5', '客服部', '1', '0', '2017-09-29 14:05:07', '0', '2017-09-29 14:05:10', null, '1');
INSERT INTO `sys_dept` VALUES ('6', '人事部', '1', '0', '2017-09-29 14:05:07', '0', '2017-09-29 14:05:10', null, '1');
INSERT INTO `sys_dept` VALUES ('7', '财务部', '1', '0', '2017-09-29 14:05:07', '0', '2017-09-29 14:05:10', null, '1');
INSERT INTO `sys_dept` VALUES ('8', '仓管部', '1', '0', '2017-09-29 14:05:07', '0', '2017-09-29 14:05:10', null, '1');
INSERT INTO `sys_dept` VALUES ('9', '9', '2', '0', '2017-09-29 14:05:07', '0', '2017-09-29 14:05:10', null, '1');
INSERT INTO `sys_dept` VALUES ('10', '10', '3', '0', '2017-09-29 14:05:07', '0', '2017-09-29 14:05:10', null, '1');
INSERT INTO `sys_dept` VALUES ('11', '11', '4', '0', '2017-09-29 14:05:07', '0', '2017-09-29 14:05:10', null, '1');
INSERT INTO `sys_dept` VALUES ('12', '12', '5', '0', '2017-09-29 14:05:07', '0', '2017-09-29 14:05:10', null, '1');
INSERT INTO `sys_dept` VALUES ('13', '13', '6', '0', '2017-09-29 14:05:07', '0', '2017-09-29 14:05:10', null, '1');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '编号',
  `value` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '数据值',
  `label` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '标签名',
  `type` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '类型',
  `description` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '描述',
  `sort` decimal(10,0) NOT NULL COMMENT '排序（升序）',
  `parent_id` varchar(64) COLLATE utf8_bin DEFAULT '0' COMMENT '父级编号',
  `create_by` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_dict_value` (`value`),
  KEY `sys_dict_label` (`label`),
  KEY `sys_dict_del_flag` (`del_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('1', '0', '正常', 'del_flag', '删除标记', '10', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('10', 'yellow', '黄色', 'color', '颜色值', '40', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('100', 'java.util.Date', 'Date', 'gen_java_type', 'Java类型', '50', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('101', 'com.thinkgem.jeesite.modules.sys.entity.User', 'User', 'gen_java_type', 'Java类型', '60', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('102', 'com.thinkgem.jeesite.modules.sys.entity.Office', 'Office', 'gen_java_type', 'Java类型', '70', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('103', 'com.thinkgem.jeesite.modules.sys.entity.Area', 'Area', 'gen_java_type', 'Java类型', '80', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('104', 'Custom', 'Custom', 'gen_java_type', 'Java类型', '90', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('105', '1', '会议通告\0\0', 'oa_notify_type', '通知通告类型', '10', '0', '1', '2013-11-08 08:00:00', '1', '2013-11-08 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('106', '2', '奖惩通告\0\0', 'oa_notify_type', '通知通告类型', '20', '0', '1', '2013-11-08 08:00:00', '1', '2013-11-08 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('107', '3', '活动通告\0\0', 'oa_notify_type', '通知通告类型', '30', '0', '1', '2013-11-08 08:00:00', '1', '2013-11-08 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('108', '0', '草稿', 'oa_notify_status', '通知通告状态', '10', '0', '1', '2013-11-08 08:00:00', '1', '2013-11-08 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('109', '1', '发布', 'oa_notify_status', '通知通告状态', '20', '0', '1', '2013-11-08 08:00:00', '1', '2013-11-08 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('11', 'orange', '橙色', 'color', '颜色值', '50', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('110', '0', '未读', 'oa_notify_read', '通知通告状态', '10', '0', '1', '2013-11-08 08:00:00', '1', '2013-11-08 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('111', '1', '已读', 'oa_notify_read', '通知通告状态', '20', '0', '1', '2013-11-08 08:00:00', '1', '2013-11-08 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('114f9398fec9490882f4c58dbaf974d6', '0', '未提交', 'status', '未提交', '10', '0', '1', '2017-02-05 16:55:02', '1', '2017-02-05 16:55:02', '', '0');
INSERT INTO `sys_dict` VALUES ('12', 'default', '默认主题', 'theme', '主题方案', '10', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('13', 'cerulean', '天蓝主题', 'theme', '主题方案', '20', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('14', 'readable', '橙色主题', 'theme', '主题方案', '30', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('14266e57a6124dd5b6a46ae4626d367c', '1', '审核中', 'status', '审核中', '20', '0', '1', '2017-02-05 16:55:28', '1', '2017-02-05 16:55:28', '', '0');
INSERT INTO `sys_dict` VALUES ('15', 'united', '红色主题', 'theme', '主题方案', '40', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('16', 'flat', 'Flat主题', 'theme', '主题方案', '60', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('17', '1', '国家', 'sys_area_type', '区域类型', '10', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('18', '2', '省份、直辖市', 'sys_area_type', '区域类型', '20', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('19', '3', '地市', 'sys_area_type', '区域类型', '30', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('2', '1', '删除', 'del_flag', '删除标记', '20', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('20', '4', '区县', 'sys_area_type', '区域类型', '40', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('21', '1', '公司', 'sys_office_type', '机构类型', '60', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('22', '2', '部门', 'sys_office_type', '机构类型', '70', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('23', '3', '小组', 'sys_office_type', '机构类型', '80', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('24', '4', '其它', 'sys_office_type', '机构类型', '90', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('25', '1', '综合部', 'sys_office_common', '快捷通用部门', '30', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('26', '2', '开发部', 'sys_office_common', '快捷通用部门', '40', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('27', '3', '人力部', 'sys_office_common', '快捷通用部门', '50', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('28', '1', '一级', 'sys_office_grade', '机构等级', '10', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('29', '2', '二级', 'sys_office_grade', '机构等级', '20', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('3', '1', '显示', 'show_hide', '显示/隐藏', '10', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('30', '3', '三级', 'sys_office_grade', '机构等级', '30', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('31', '4', '四级', 'sys_office_grade', '机构等级', '40', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('32', '1', '所有数据', 'sys_data_scope', '数据范围', '10', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('33', '2', '所在公司及以下数据', 'sys_data_scope', '数据范围', '20', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('34', '3', '所在公司数据', 'sys_data_scope', '数据范围', '30', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('35', '4', '所在部门及以下数据', 'sys_data_scope', '数据范围', '40', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('36', '5', '所在部门数据', 'sys_data_scope', '数据范围', '50', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('37', '8', '仅本人数据', 'sys_data_scope', '数据范围', '90', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('38', '9', '按明细设置', 'sys_data_scope', '数据范围', '100', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('39', '1', '系统管理', 'sys_user_type', '用户类型', '10', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('4', '0', '隐藏', 'show_hide', '显示/隐藏', '20', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('40', '2', '部门经理', 'sys_user_type', '用户类型', '20', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('41', '3', '普通用户', 'sys_user_type', '用户类型', '30', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('42', 'basic', '基础主题', 'cms_theme', '站点主题', '10', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('43', 'blue', '蓝色主题', 'cms_theme', '站点主题', '20', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('44', 'red', '红色主题', 'cms_theme', '站点主题', '30', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('45', 'article', '文章模型', 'cms_module', '栏目模型', '10', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('46', 'picture', '图片模型', 'cms_module', '栏目模型', '20', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('47', 'download', '下载模型', 'cms_module', '栏目模型', '30', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('48', 'link', '链接模型', 'cms_module', '栏目模型', '40', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('49', 'special', '专题模型', 'cms_module', '栏目模型', '50', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('5', '1', '是', 'yes_no', '是/否', '10', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('50', '0', '默认展现方式', 'cms_show_modes', '展现方式', '10', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('51', '1', '首栏目内容列表', 'cms_show_modes', '展现方式', '20', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('52', '2', '栏目第一条内容', 'cms_show_modes', '展现方式', '30', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('53', '0', '发布', 'cms_del_flag', '内容状态', '10', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('54', '1', '删除', 'cms_del_flag', '内容状态', '20', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('55', '2', '审核', 'cms_del_flag', '内容状态', '15', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('56', '1', '首页焦点图', 'cms_posid', '推荐位', '10', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('57', '2', '栏目页文章推荐', 'cms_posid', '推荐位', '20', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('58', '1', '咨询', 'cms_guestbook', '留言板分类', '10', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('59', '2', '建议', 'cms_guestbook', '留言板分类', '20', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('6', '0', '否', 'yes_no', '是/否', '20', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('60', '3', '投诉', 'cms_guestbook', '留言板分类', '30', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('61', '4', '其它', 'cms_guestbook', '留言板分类', '40', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('62', '1', '公休', 'oa_leave_type', '请假类型', '10', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('63', '2', '病假', 'oa_leave_type', '请假类型', '20', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('64', '3', '事假', 'oa_leave_type', '请假类型', '30', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('65', '4', '调休', 'oa_leave_type', '请假类型', '40', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('66', '5', '婚假', 'oa_leave_type', '请假类型', '60', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('67', '1', '接入日志', 'sys_log_type', '日志类型', '30', '0', '1', '2013-06-03 08:00:00', '1', '2013-06-03 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('68', '2', '异常日志', 'sys_log_type', '日志类型', '40', '0', '1', '2013-06-03 08:00:00', '1', '2013-06-03 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('69', 'leave', '请假流程', 'act_type', '流程类型', '10', '0', '1', '2013-06-03 08:00:00', '1', '2013-06-03 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('7', 'red', '红色', 'color', '颜色值', '10', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('70', 'test_audit', '审批测试流程', 'act_type', '流程类型', '20', '0', '1', '2013-06-03 08:00:00', '1', '2013-06-03 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('71', '1', '分类1', 'act_category', '流程分类', '10', '0', '1', '2013-06-03 08:00:00', '1', '2013-06-03 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('72', '2', '分类2', 'act_category', '流程分类', '20', '0', '1', '2013-06-03 08:00:00', '1', '2013-06-03 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('73', 'crud', '增删改查', 'gen_category', '代码生成分类', '10', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('74', 'crud_many', '增删改查（包含从表）', 'gen_category', '代码生成分类', '20', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('75', 'tree', '树结构', 'gen_category', '代码生成分类', '30', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('76', '=', '=', 'gen_query_type', '查询方式', '10', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('77', '!=', '!=', 'gen_query_type', '查询方式', '20', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('78', '&gt;', '&gt;', 'gen_query_type', '查询方式', '30', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('79', '&lt;', '&lt;', 'gen_query_type', '查询方式', '40', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('8', 'green', '绿色', 'color', '颜色值', '20', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('80', 'between', 'Between', 'gen_query_type', '查询方式', '50', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('81', 'like', 'Like', 'gen_query_type', '查询方式', '60', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('82', 'left_like', 'Left Like', 'gen_query_type', '查询方式', '70', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('83', 'right_like', 'Right Like', 'gen_query_type', '查询方式', '80', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('84', 'input', '文本框', 'gen_show_type', '字段生成方案', '10', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('85', 'textarea', '文本域', 'gen_show_type', '字段生成方案', '20', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('86', 'select', '下拉框', 'gen_show_type', '字段生成方案', '30', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('87', 'checkbox', '复选框', 'gen_show_type', '字段生成方案', '40', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('88', 'radiobox', '单选框', 'gen_show_type', '字段生成方案', '50', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('89', 'dateselect', '日期选择', 'gen_show_type', '字段生成方案', '60', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('9', 'blue', '蓝色', 'color', '颜色值', '30', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('90', 'userselect', '人员选择', 'gen_show_type', '字段生成方案', '70', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('91', 'officeselect', '部门选择', 'gen_show_type', '字段生成方案', '80', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('92', 'areaselect', '区域选择', 'gen_show_type', '字段生成方案', '90', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('93', 'String', 'String', 'gen_java_type', 'Java类型', '10', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('94', 'Long', 'Long', 'gen_java_type', 'Java类型', '20', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('95', 'dao', '仅持久层', 'gen_category', '代码生成分类\0\0\0\0', '40', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('96', '1', '男', 'sex', '性别', '10', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('97', '2', '女', 'sex', '性别', '20', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('98', 'Integer', 'Integer', 'gen_java_type', 'Java类型', '30', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('99', 'Double', 'Double', 'gen_java_type', 'Java类型', '40', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('b51bcc69eec64f5fa911b8d4d0a44c96', '2', '已审核', 'status', '已审核', '30', '0', '1', '2017-02-05 16:55:49', '1', '2017-02-05 16:56:36', '', '0');
INSERT INTO `sys_dict` VALUES ('bbc9391ddec041a58f4a6e6a3549ad1d', '9', '审核不通过', 'status', '审核不通过', '40', '0', '1', '2017-02-05 16:56:09', '1', '2017-02-05 16:56:44', '', '0');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '菜单URL',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `perms` varchar(2000) COLLATE utf8_bin DEFAULT NULL COMMENT '授权(多个用逗号分隔如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型 0：目录 1：菜单 2：按钮',
  `icon` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `create_by` int(10) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` int(10) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(765) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `isemploy` char(3) COLLATE utf8_bin NOT NULL DEFAULT '1' COMMENT '删除标记 1正常2删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '平台', '', '0', '', '1', '1', '0', '0', '2017-10-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('2', '编程辅助', '2', '0', '', '1', '1', '20', '0', '2017-10-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('3', '系统功能', '3', '0', '', '1', '1', '30', '0', '2017-10-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('4', '测试菜单4', '4', '3', '', '1', '1', '10', '0', '2017-10-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('5', '子菜单1-1', '5', '1', '', '1', '&#xe667;', '10', '0', '2017-10-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('6', '子菜单1-2', '6', '1', '', '1', '1', '30', '0', '2017-10-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('7', '子菜单1-3', '7', '1', '', '1', '1', '40', '0', '2017-10-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('8', '子菜单1-1-1', '8', '6', '', '1', '1', '30', '0', '2017-10-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('9', '子菜单1-1-2', '9', '6', '', '1', '1', '10', '0', '2017-10-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('10', '子菜单1-2-1', '10', '5', '', '1', '3', '10', '0', '2017-10-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('11', '子菜单1-2-2', '11', '1', '', '1', '1', '20', '0', '2017-10-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('12', '开发辅助', '', '3', '', '1', '1', '20', '0', '2017-10-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('13', '代码生成', 'Reception/manage/generate.html', '12', '', '1', '1', '10', '0', '2017-10-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('14', '子菜单1-1-2-1', '14', '6', '', '1', '1', '20', '0', '2017-10-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('15', '子菜单1-1-1-1', '15', '8', '', '1', '1', '10', '0', '2017-10-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('16', 'web功能', '', '2', '', '1', '&#xe69f;', '10', '0', '2017-10-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('17', 'web常见错误', 'Reception/weberror/weberror.html', '16', 'buz:weberror:view', '1', '&#xe69f;', '10', '0', '2017-10-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('18', '系统设置', '', '3', '', '1', '&#xe61d;', '30', '0', '2017-10-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('19', '菜单管理', '/Reception/sys/menu/to.html', '18', '', '1', '1', '10', '0', '2017-10-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('20', '查看', '', '19', 'sys:menu:view', '3', '1', '10', '0', '2017-10-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('21', '增加', '', '19', 'sys:menu:save', '3', '1', '20', '0', '2017-10-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('22', '修改', '', '19', 'sys:menu:update', '3', '1', '30', '0', '2017-10-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('23', '删除', '', '19', 'sys:menu:delete', '3', '1', '40', '0', '2017-10-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('24', '批量删除', '', '19', 'sys:menu:batchdelete', '3', '1', '60', '0', '2017-10-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('25', '顺序调整', '', '19', 'sys:menu:ordersave', '3', '1', '50', '0', '2017-10-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('29', '用户管理', '/Reception/sys/user/to.html', '18', '', '1', '1', '20', '0', '2017-10-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('30', '查看', '', '29', 'sys:user:view', '3', '1', '10', '0', '2017-10-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('31', '增加', '', '29', 'sys:user:save', '3', '1', '20', '0', '2017-10-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('32', '修改', '', '29', 'sys:user:update', '3', '1', '30', '0', '2017-10-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('33', '删除', '', '29', 'sys:user:delete', '3', '1', '40', '0', '2017-10-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('34', '批量删除', '', '29', 'sys:user:batchdelete', '3', '1', '50', '0', '2017-10-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('37', '角色管理', '/Reception/sys/role/to.html', '18', null, '2', null, '30', '1', '2018-01-05 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('39', '查看', null, '37', 'sys:role:view', '3', null, '10', '1', '2018-01-05 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('40', '修改', null, '37', 'sys:role:update', '3', null, '20', '1', '2018-01-05 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('41', '新增', null, '37', 'sys:role:save', '3', null, '30', '1', '2018-01-05 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('42', '删除', null, '37', 'sys:role:delete', '3', '', '40', '1', '2018-01-05 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('43', '批量删除', null, '37', 'sys:role:batchdelete', '3', '', '50', '1', '2018-01-05 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('44', '业务功能', null, '0', null, '1', null, '10', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('45', '联系人管理', '/Reception/buz/linkman/to.html', '50', null, '1', null, '0', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('46', '查看', null, '45', 'buz:linkman:view', '3', null, '0', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('47', '增加', null, '45', 'buz:linkman:save', '3', null, '1', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('48', '修改', null, '45', 'buz:linkman:update', '3', null, '2', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('49', '删除', null, '45', 'buz:linkman:delete', '3', null, '3', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('50', '基础信息', null, '44', null, '2', null, '1', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(192) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '名称',
  `create_by` int(10) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` int(10) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(765) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `isemploy` char(3) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '1' COMMENT '删除标记 1正常2删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '系统管理员', '0', '2017-10-11 14:09:38', '1', '2018-01-11 00:00:00', '', '1');
INSERT INTO `sys_role` VALUES ('2', '经理', '1', '2017-10-12 14:09:38', '1', '2017-10-12 14:09:38', '1', '1');
INSERT INTO `sys_role` VALUES ('3', '员工', '1', '2017-10-13 14:09:38', '1', '2017-10-13 14:09:38', '1', '1');
INSERT INTO `sys_role` VALUES ('4', '测试1', '1', '2018-01-05 00:00:00', '1', '2018-01-08 00:00:00', null, '1');
INSERT INTO `sys_role` VALUES ('5', '测试2', '1', '2018-01-05 00:00:00', '1', '2018-01-08 00:00:00', null, '1');
INSERT INTO `sys_role` VALUES ('6', '测试3', '1', '2018-01-05 00:00:00', '1', '2018-01-08 00:00:00', null, '1');
INSERT INTO `sys_role` VALUES ('7', '测试4', '1', '2018-01-05 00:00:00', '1', '2018-01-08 00:00:00', null, '1');
INSERT INTO `sys_role` VALUES ('8', '测试5', '1', '2018-01-05 00:00:00', '1', '2018-01-08 00:00:00', null, '1');
INSERT INTO `sys_role` VALUES ('9', '测试6', '1', '2018-01-05 00:00:00', '1', '2018-01-08 00:00:00', null, '1');
INSERT INTO `sys_role` VALUES ('10', '测试7', '1', '2018-01-05 00:00:00', '1', '2018-01-08 00:00:00', null, '1');
INSERT INTO `sys_role` VALUES ('11', '测试8', '1', '2018-01-05 00:00:00', '1', '2018-01-08 00:00:00', null, '1');
INSERT INTO `sys_role` VALUES ('12', '测试9', '1', '2018-01-05 00:00:00', '1', '2018-01-08 00:00:00', null, '1');
INSERT INTO `sys_role` VALUES ('13', '员工2', '1', '2017-10-13 14:09:38', '1', '2017-10-13 14:09:38', '1', '1');
INSERT INTO `sys_role` VALUES ('14', '员工3', '1', '2017-10-13 14:09:38', '1', '2017-10-13 14:09:38', '1', '1');
INSERT INTO `sys_role` VALUES ('15', '员工6', '1', '2017-10-13 14:09:38', '1', '2017-10-13 14:09:38', '1', '1');
INSERT INTO `sys_role` VALUES ('16', '员工5', '1', '2017-10-13 14:09:38', '1', '2017-10-13 14:09:38', '1', '1');
INSERT INTO `sys_role` VALUES ('17', '员工4', '1', '2017-10-13 14:09:38', '1', '2017-10-13 14:09:38', '1', '1');
INSERT INTO `sys_role` VALUES ('18', '测试10', '1', '2018-01-05 00:00:00', '1', '2018-01-08 00:00:00', null, '1');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` int(10) NOT NULL COMMENT '角色id',
  `menu_id` int(10) NOT NULL COMMENT '菜单id',
  `create_by` int(10) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` int(10) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(765) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `isemploy` char(3) COLLATE utf8_bin NOT NULL DEFAULT '1' COMMENT '删除标记 1正常2删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=184 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色菜单关系表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1', '1', '0', '2017-10-11 14:35:45', '0', '2017-10-11 14:35:48', null, '2');
INSERT INTO `sys_role_menu` VALUES ('3', '1', '3', '0', '2017-10-11 14:35:45', '0', '2017-10-11 14:35:48', null, '2');
INSERT INTO `sys_role_menu` VALUES ('4', '2', '1', '0', '2017-10-11 14:35:45', '0', '2017-10-11 14:35:48', null, '1');
INSERT INTO `sys_role_menu` VALUES ('5', '2', '2', '0', '2017-10-11 14:35:45', '0', '2017-10-11 14:35:48', null, '1');
INSERT INTO `sys_role_menu` VALUES ('6', '3', '2', '0', '2017-10-11 14:35:45', '0', '2017-10-11 14:35:48', null, '1');
INSERT INTO `sys_role_menu` VALUES ('7', '3', '3', '0', '2017-10-11 14:35:45', '0', '2017-10-11 14:35:48', null, '1');
INSERT INTO `sys_role_menu` VALUES ('8', '1', '5', '0', '2017-10-11 14:35:45', '0', '2017-10-11 14:35:48', null, '2');
INSERT INTO `sys_role_menu` VALUES ('9', '1', '6', '0', '2017-10-11 14:35:45', '0', '2017-10-11 14:35:48', null, '2');
INSERT INTO `sys_role_menu` VALUES ('10', '1', '7', '0', '2017-10-11 14:35:45', '0', '2017-10-11 14:35:48', null, '2');
INSERT INTO `sys_role_menu` VALUES ('11', '1', '8', '0', '2017-10-11 14:35:45', '0', '2017-10-11 14:35:48', null, '2');
INSERT INTO `sys_role_menu` VALUES ('12', '1', '9', '0', '2017-10-11 14:35:45', '0', '2017-10-11 14:35:48', null, '2');
INSERT INTO `sys_role_menu` VALUES ('13', '1', '10', '0', '2017-10-11 14:35:45', '0', '2017-10-11 14:35:48', null, '2');
INSERT INTO `sys_role_menu` VALUES ('14', '1', '11', '0', '2017-10-11 14:35:45', '0', '2017-10-11 14:35:48', null, '2');
INSERT INTO `sys_role_menu` VALUES ('15', '1', '12', '0', '2017-10-11 14:35:45', '0', '2017-10-11 14:35:48', null, '2');
INSERT INTO `sys_role_menu` VALUES ('16', '1', '13', '0', '2017-10-11 14:35:45', '0', '2017-10-11 14:35:48', null, '2');
INSERT INTO `sys_role_menu` VALUES ('17', '1', '14', '0', '2017-10-11 14:35:45', '0', '2017-10-11 14:35:48', null, '2');
INSERT INTO `sys_role_menu` VALUES ('18', '1', '15', '0', '2017-10-11 14:35:45', '0', '2017-10-11 14:35:48', null, '2');
INSERT INTO `sys_role_menu` VALUES ('19', '1', '16', '0', '2017-10-11 14:35:45', '0', '2017-10-11 14:35:48', null, '2');
INSERT INTO `sys_role_menu` VALUES ('20', '3', '2', '0', '2017-10-11 14:35:45', '0', '2017-10-11 14:35:48', null, '1');
INSERT INTO `sys_role_menu` VALUES ('21', '1', '17', '0', '2017-10-11 14:35:45', '0', '2017-10-11 14:35:48', null, '2');
INSERT INTO `sys_role_menu` VALUES ('22', '1', '18', '0', '2017-10-11 14:35:45', '0', '2017-10-11 14:35:48', null, '2');
INSERT INTO `sys_role_menu` VALUES ('23', '1', '19', '0', '2017-10-11 14:35:45', '0', '2017-10-11 14:35:48', null, '2');
INSERT INTO `sys_role_menu` VALUES ('24', '1', '20', '0', '2017-10-11 14:35:45', '0', '2017-10-11 14:35:48', null, '2');
INSERT INTO `sys_role_menu` VALUES ('25', '1', '21', '0', '2017-10-11 14:35:45', '0', '2017-10-11 14:35:48', null, '2');
INSERT INTO `sys_role_menu` VALUES ('26', '1', '22', '0', '2017-10-11 14:35:45', '0', '2017-10-11 14:35:48', null, '2');
INSERT INTO `sys_role_menu` VALUES ('27', '1', '23', '0', '2017-10-11 14:35:45', '0', '2017-10-11 14:35:48', null, '2');
INSERT INTO `sys_role_menu` VALUES ('28', '1', '24', '0', '2017-10-11 14:35:45', '0', '2017-10-11 14:35:48', null, '2');
INSERT INTO `sys_role_menu` VALUES ('29', '1', '25', '0', '2017-10-11 14:35:45', '0', '2017-10-11 14:35:48', null, '2');
INSERT INTO `sys_role_menu` VALUES ('30', '1', '29', '0', '2017-10-11 14:35:45', '0', '2017-10-11 14:35:48', null, '2');
INSERT INTO `sys_role_menu` VALUES ('31', '1', '30', '0', '2017-10-11 14:35:45', '0', '2017-10-11 14:35:48', null, '2');
INSERT INTO `sys_role_menu` VALUES ('32', '1', '31', '0', '2017-10-11 14:35:45', '0', '2017-10-11 14:35:48', null, '2');
INSERT INTO `sys_role_menu` VALUES ('33', '1', '32', '0', '2017-10-11 14:35:45', '0', '2017-10-11 14:35:48', null, '2');
INSERT INTO `sys_role_menu` VALUES ('34', '1', '33', '0', '2017-10-11 14:35:45', '0', '2017-10-11 14:35:48', null, '2');
INSERT INTO `sys_role_menu` VALUES ('35', '1', '34', '0', '2017-10-11 14:35:45', '0', '2017-10-11 14:35:48', null, '2');
INSERT INTO `sys_role_menu` VALUES ('38', '1', '37', '0', '2017-10-11 14:35:45', '0', '2017-10-11 14:35:48', null, '2');
INSERT INTO `sys_role_menu` VALUES ('39', '1', '39', '0', '2017-10-11 14:35:45', '0', '2017-10-11 14:35:48', null, '2');
INSERT INTO `sys_role_menu` VALUES ('40', '1', '40', '0', '2017-10-11 14:35:45', '0', '2017-10-11 14:35:48', null, '2');
INSERT INTO `sys_role_menu` VALUES ('41', '1', '41', '0', '2017-10-11 14:35:45', '0', '2017-10-11 14:35:48', null, '2');
INSERT INTO `sys_role_menu` VALUES ('42', '1', '42', '0', '2017-10-11 14:35:45', '0', '2017-10-11 14:35:48', null, '2');
INSERT INTO `sys_role_menu` VALUES ('43', '1', '43', '0', '2017-10-11 14:35:45', '0', '2017-10-11 14:35:48', null, '2');
INSERT INTO `sys_role_menu` VALUES ('44', '4', '1', '1', '2018-01-08 00:00:00', '1', '2018-01-08 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('45', '4', '5', '1', '2018-01-08 00:00:00', '1', '2018-01-08 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('46', '4', '10', '1', '2018-01-08 00:00:00', '1', '2018-01-08 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('47', '4', '11', '1', '2018-01-08 00:00:00', '1', '2018-01-08 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('48', '4', '6', '1', '2018-01-08 00:00:00', '1', '2018-01-08 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('49', '4', '9', '1', '2018-01-08 00:00:00', '1', '2018-01-08 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('50', '4', '14', '1', '2018-01-08 00:00:00', '1', '2018-01-08 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('51', '4', '8', '1', '2018-01-08 00:00:00', '1', '2018-01-08 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('52', '4', '15', '1', '2018-01-08 00:00:00', '1', '2018-01-08 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('53', '4', '7', '1', '2018-01-08 00:00:00', '1', '2018-01-08 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('54', '1', '1', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('55', '1', '5', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('56', '1', '10', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('57', '1', '11', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('58', '1', '6', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('59', '1', '9', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('60', '1', '14', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('61', '1', '8', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('62', '1', '15', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('63', '1', '7', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('64', '1', '44', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('65', '1', '45', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('66', '1', '46', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('67', '1', '47', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('68', '1', '48', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('69', '1', '49', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('70', '1', '2', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('71', '1', '16', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('72', '1', '17', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('73', '1', '3', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('74', '1', '4', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('75', '1', '12', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('76', '1', '13', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('77', '1', '18', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('78', '1', '19', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('79', '1', '20', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('80', '1', '21', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('81', '1', '22', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('82', '1', '23', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('83', '1', '25', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('84', '1', '24', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('85', '1', '29', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('86', '1', '30', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('87', '1', '31', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('88', '1', '32', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('89', '1', '33', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('90', '1', '34', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('91', '1', '37', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('92', '1', '39', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('93', '1', '40', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('94', '1', '41', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('95', '1', '42', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('96', '1', '43', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('97', '1', '1', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('98', '1', '5', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('99', '1', '10', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('100', '1', '11', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('101', '1', '6', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('102', '1', '9', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('103', '1', '14', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('104', '1', '8', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('105', '1', '15', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('106', '1', '7', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('107', '1', '44', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('108', '1', '45', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('109', '1', '46', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('110', '1', '47', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('111', '1', '48', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('112', '1', '49', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('113', '1', '2', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('114', '1', '16', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('115', '1', '17', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('116', '1', '3', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('117', '1', '4', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('118', '1', '12', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('119', '1', '13', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('120', '1', '18', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('121', '1', '19', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('122', '1', '20', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('123', '1', '21', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('124', '1', '22', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('125', '1', '23', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('126', '1', '25', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('127', '1', '24', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('128', '1', '29', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('129', '1', '30', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('130', '1', '31', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('131', '1', '32', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('132', '1', '33', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('133', '1', '34', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('134', '1', '37', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('135', '1', '39', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('136', '1', '40', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('137', '1', '41', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('138', '1', '42', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('139', '1', '43', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '2');
INSERT INTO `sys_role_menu` VALUES ('140', '1', '1', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('141', '1', '5', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('142', '1', '10', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('143', '1', '11', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('144', '1', '6', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('145', '1', '9', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('146', '1', '14', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('147', '1', '8', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('148', '1', '15', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('149', '1', '7', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('150', '1', '44', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('151', '1', '50', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('152', '1', '45', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('153', '1', '46', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('154', '1', '47', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('155', '1', '48', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('156', '1', '49', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('157', '1', '2', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('158', '1', '16', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('159', '1', '17', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('160', '1', '3', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('161', '1', '4', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('162', '1', '12', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('163', '1', '13', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('164', '1', '18', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('165', '1', '19', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('166', '1', '20', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('167', '1', '21', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('168', '1', '22', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('169', '1', '23', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('170', '1', '25', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('171', '1', '24', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('172', '1', '29', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('173', '1', '30', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('174', '1', '31', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('175', '1', '32', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('176', '1', '33', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('177', '1', '34', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('178', '1', '37', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('179', '1', '39', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('180', '1', '40', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('181', '1', '41', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('182', '1', '42', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');
INSERT INTO `sys_role_menu` VALUES ('183', '1', '43', '1', '2018-01-11 00:00:00', '1', '2018-01-11 00:00:00', null, '1');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) DEFAULT '新用户' COMMENT '用户名称',
  `login_name` varchar(100) NOT NULL COMMENT '登录名',
  `password` varchar(100) NOT NULL COMMENT '用户密码',
  `phone` varchar(11) NOT NULL COMMENT '手机号码',
  `grade` int(1) DEFAULT '1' COMMENT '用户等级:0最高级',
  `dept` int(11) DEFAULT NULL COMMENT '部门',
  `create_by` int(10) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` int(10) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(2000) DEFAULT NULL COMMENT '备注',
  `isemploy` int(1) NOT NULL DEFAULT '1' COMMENT '状态1:正常2删除3审核',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('0', '系统', '0', '系统保留', '0', '0', '1', '0', '2017-10-25 11:22:49', '0', '2017-10-25 11:22:49', null, '1');
INSERT INTO `sys_user` VALUES ('1', '张三', 'zansan', '25D55AD283AA400AF464C76D713C07AD', '15345042356', '55', '11', '0', '2017-10-25 11:22:51', '1', '2018-01-08 00:00:00', null, '1');
INSERT INTO `sys_user` VALUES ('2', '李四', 'lisi', '25D55AD283AA400AF464C76D713C07AD', '15345042357', '11', '8', '1', '2017-10-17 00:00:00', '1', '2018-01-05 00:00:00', null, '1');
INSERT INTO `sys_user` VALUES ('3', '王五', 'wangwu', '25D55AD283AA400AF464C76D713C07AD', '15345042358', '11', '3', '1', '2017-09-04 00:00:00', '1', '2018-01-05 00:00:00', null, '2');
INSERT INTO `sys_user` VALUES ('4', '张三1', 'zansan1', '25D55AD283AA400AF464C76D713C07AD', '18259154429', '489', '4', '1', '2017-10-08 00:00:00', '1', '2018-01-05 00:00:00', null, '1');
INSERT INTO `sys_user` VALUES ('5', '张三2', 'zansan2', '25d55ad283aa400af464c76d713c07ad', '18259459599', '65', '7', '1', '2017-10-10 00:00:00', '1', '2018-01-05 00:00:00', null, '1');
INSERT INTO `sys_user` VALUES ('6', '张三2', 'zansan3', '25d55ad283aa400af464c76d713c07ad', '123', '1', '8', '1', '2017-10-31 00:00:00', '1', '2018-01-05 00:00:00', null, '1');
INSERT INTO `sys_user` VALUES ('7', '测试', 'cheshi', '25d55ad283aa400af464c76d713c07ad', '1825945658', '5', '7', '1', '2018-01-08 00:00:00', '1', '2018-01-08 00:00:00', null, '1');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(10) NOT NULL COMMENT '用户id',
  `role_id` int(10) NOT NULL COMMENT '角色id',
  `create_by` int(10) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` int(10) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(765) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `isemploy` char(3) COLLATE utf8_bin NOT NULL DEFAULT '1' COMMENT '删除标记 1正常2删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户角色关系表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1', '0', '2017-10-11 14:30:37', '0', '2017-10-11 14:30:40', '0', '1');
INSERT INTO `sys_user_role` VALUES ('2', '2', '2', '0', '2017-10-11 14:30:37', '0', '2017-10-11 14:30:40', '0', '1');
INSERT INTO `sys_user_role` VALUES ('3', '3', '3', '0', '2017-10-11 14:30:37', '0', '2017-10-11 14:30:40', '0', '1');
INSERT INTO `sys_user_role` VALUES ('4', '1', '2', '0', '2017-10-11 14:30:37', '0', '2017-10-11 14:30:40', '0', '1');
INSERT INTO `sys_user_role` VALUES ('5', '7', '3', '1', '2018-01-08 00:00:00', '1', '2018-01-08 00:00:00', null, '2');
INSERT INTO `sys_user_role` VALUES ('6', '7', '2', '1', '2018-01-08 00:00:00', '1', '2018-01-08 00:00:00', null, '2');
INSERT INTO `sys_user_role` VALUES ('7', '7', '8', '1', '2018-01-08 00:00:00', '1', '2018-01-08 00:00:00', null, '1');
INSERT INTO `sys_user_role` VALUES ('8', '7', '3', '1', '2018-01-08 00:00:00', '1', '2018-01-08 00:00:00', null, '1');
INSERT INTO `sys_user_role` VALUES ('9', '7', '2', '1', '2018-01-08 00:00:00', '1', '2018-01-08 00:00:00', null, '1');

-- ----------------------------
-- Table structure for sys_wx_token
-- ----------------------------
DROP TABLE IF EXISTS `sys_wx_token`;
CREATE TABLE `sys_wx_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `access_token` varchar(1024) NOT NULL,
  `expires_in` int(11) NOT NULL,
  `createTime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信token';

-- ----------------------------
-- Records of sys_wx_token
-- ----------------------------

-- ----------------------------
-- Table structure for tb_costinfo
-- ----------------------------
DROP TABLE IF EXISTS `tb_costinfo`;
CREATE TABLE `tb_costinfo` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `payname` varchar(255) NOT NULL,
  `paymoney` double NOT NULL,
  `createtime` date DEFAULT NULL,
  `endtime` date DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `income_expend` int(1) DEFAULT '1',
  `user` varchar(255) DEFAULT '军',
  `isemploy` int(1) DEFAULT '1' COMMENT '状态1启用,2删除',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消费记录';

-- ----------------------------
-- Records of tb_costinfo
-- ----------------------------

-- ----------------------------
-- Table structure for tb_css
-- ----------------------------
DROP TABLE IF EXISTS `tb_css`;
CREATE TABLE `tb_css` (
  `sid` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `sexample` varchar(255) DEFAULT NULL COMMENT '例子',
  `sexplain` varchar(255) DEFAULT NULL COMMENT '说明',
  `isemploy` int(1) DEFAULT NULL COMMENT '状态:1启用2禁用',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='css信息';

-- ----------------------------
-- Records of tb_css
-- ----------------------------
INSERT INTO `tb_css` VALUES ('2', '<span class=\"help-inline1\"><font color=\"red\">*</font></span>', '必填项提示(红色*号)', '1');
INSERT INTO `tb_css` VALUES ('3', 'style=\"cursor: pointer\"', '鼠标移动到标签上变手型', '1');

-- ----------------------------
-- Table structure for tb_dbname
-- ----------------------------
DROP TABLE IF EXISTS `tb_dbname`;
CREATE TABLE `tb_dbname` (
  `sid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `stype` varchar(255) NOT NULL COMMENT '数据库类型',
  `sdriver` varchar(2000) NOT NULL COMMENT '驱动地址',
  `isemploy` int(1) NOT NULL DEFAULT '1' COMMENT '状态:1启用2禁用',
  `surl` varchar(2000) NOT NULL COMMENT '路径',
  `sname` varchar(2000) DEFAULT NULL COMMENT '数据库名称或实例化名称',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='数据库信息';

-- ----------------------------
-- Records of tb_dbname
-- ----------------------------
INSERT INTO `tb_dbname` VALUES ('1', 'orcale', 'oracle.jdbc.driver.OracleDriver', '0', 'jdbc:oracle:thin:@ip:port:dbname', 'ORCL');
INSERT INTO `tb_dbname` VALUES ('2', 'mysql', 'com.mysql.jdbc.Driver', '1', 'jdbc:mysql://ip:port/dbname?useUnicode=true&amp;characterEncoding=UTF-8', 'db_Person');

-- ----------------------------
-- Table structure for tb_js
-- ----------------------------
DROP TABLE IF EXISTS `tb_js`;
CREATE TABLE `tb_js` (
  `sid` int(100) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sexample` varchar(255) DEFAULT NULL COMMENT '案列',
  `sexplain` varchar(255) DEFAULT NULL COMMENT '说明',
  `isemploy` int(1) DEFAULT '1' COMMENT '状态:1启用2禁用',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='js信息';

-- ----------------------------
-- Records of tb_js
-- ----------------------------
INSERT INTO `tb_js` VALUES ('1', '$(obj).parent()', '查找父标签', '1');
INSERT INTO `tb_js` VALUES ('2', '$(obj).children(\"input\").eq(0)', '查找第一个(input)子标签', '1');
INSERT INTO `tb_js` VALUES ('3', '$(obj).trigger(\"click\")', '执行点击事件', '1');
INSERT INTO `tb_js` VALUES ('4', '$(obj).removeClass(\"hidden\")', '移除hidden属性', '1');
INSERT INTO `tb_js` VALUES ('5', '$(obj).addClass(\"hidden\")', '添加hidden属性', '1');
INSERT INTO `tb_js` VALUES ('6', '$(obj).after(content)', '标签后添加内容(html格式)', '1');
INSERT INTO `tb_js` VALUES ('7', 'array.splice(x,y)', '数组array从下标x开始,删除y个', '1');
INSERT INTO `tb_js` VALUES ('8', 'str.substring(x,y)', '字符串截取从下标x开始到y结束', '1');
INSERT INTO `tb_js` VALUES ('9', '$(obj).remove()', '删除自身(删除标签)', '1');
INSERT INTO `tb_js` VALUES ('10', 'console.log(str)', '控制台输出str', '1');
INSERT INTO `tb_js` VALUES ('11', 'window.history.back(-1)', '返回上一页(保留上一页页面信息)', '1');
INSERT INTO `tb_js` VALUES ('12', '$(obj).prev()', '获取上一个兄弟标签', '1');
INSERT INTO `tb_js` VALUES ('13', '$(obj).next()', '获取下一个兄弟标签', '1');
INSERT INTO `tb_js` VALUES ('14', '$(\"#table tr:gt(0)\").remove()', '删除表格大于0的行', '1');
INSERT INTO `tb_js` VALUES ('15', 'str=str.replace(/\\_/g,\'\')', '将_替换为空(全部)', '1');
INSERT INTO `tb_js` VALUES ('16', 'onkeyup=\"this.value=this.value.replace(/,/g,\"\")\"', '输入,立即转换为空', '1');
INSERT INTO `tb_js` VALUES ('17', 'self.parent.frames[\"main\"].hint();', '执行name为main的frame执行hint方法', '1');
INSERT INTO `tb_js` VALUES ('18', '$(parent.document.getElementsByName(\"main\")).attr(\"src\",str);', '改变name为main的frame的src属性', '1');
INSERT INTO `tb_js` VALUES ('19', '$(obj).before(content)', '标签前添加内容(html格式)', '1');
INSERT INTO `tb_js` VALUES ('20', '$(obj).empty()', '清空节点的内容(保留节点)', '1');
INSERT INTO `tb_js` VALUES ('21', '$(\"#main > *\")', '选择id值为main的所有的子元素', '1');
INSERT INTO `tb_js` VALUES ('22', '$(\"form input\")', '选择所有的form元素中的input元素 ', '1');
INSERT INTO `tb_js` VALUES ('23', '$(\"div:contains(\'John\')\")', '选择所有div中含有John文本的元素', '1');
INSERT INTO `tb_js` VALUES ('24', '$(\"div:hidden\")', '选择所有的被hidden的div元素 ', '1');
INSERT INTO `tb_js` VALUES ('25', '$(\"div:visible\")', '选择所有的可视化的div元素', '1');
INSERT INTO `tb_js` VALUES ('26', 'console.clear();', '清空控制台', '1');
INSERT INTO `tb_js` VALUES ('27', 'console.dir(data);', '输出对象全部属性', '1');
INSERT INTO `tb_js` VALUES ('28', '$(\"#formid input\").attr(\"readonly\",true); ', 'form表单下所有input标签添加属性', '1');
INSERT INTO `tb_js` VALUES ('29', '$(\"#list\").find(\"option\").eq(0)', '找到id为list下的第一个option标签', '1');
INSERT INTO `tb_js` VALUES ('30', '$(this).hide();', 'this标签隐藏', '1');
INSERT INTO `tb_js` VALUES ('31', '$(this).show();', 'this标签显示', '1');
INSERT INTO `tb_js` VALUES ('32', '$(\"p\").removeAttr(\"id\");', '移除标签属性', '1');
INSERT INTO `tb_js` VALUES ('33', 'onkeyup=\"this.value=this.value.replace(/\\D/g,\'\')\"', '输入非数字,立即转换为空', '1');
INSERT INTO `tb_js` VALUES ('34', '$(\"#id\").after(\"<b>Hello</b>\");', '在标签后添加标签', '1');
INSERT INTO `tb_js` VALUES ('35', '$(\"#id\").before(\"<b>Hello</b>\");', '在每个匹配的元素之前插入内容。', '1');
INSERT INTO `tb_js` VALUES ('36', '$(\"p\").insertAfter(\"#foo\");', '把所有匹配的元素插入到另一个、指定的元素元素集合的后面', '1');
INSERT INTO `tb_js` VALUES ('37', '$(\"p\").insertBefore(\"#foo\");', '把所有匹配的元素插入到另一个、指定的元素元素集合的前面。', '1');

-- ----------------------------
-- Table structure for tb_linkman
-- ----------------------------
DROP TABLE IF EXISTS `tb_linkman`;
CREATE TABLE `tb_linkman` (
  `sid` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sname` varchar(60) NOT NULL COMMENT '姓名',
  `sdep` varchar(60) DEFAULT NULL COMMENT '部门',
  `sphone` varchar(60) DEFAULT NULL COMMENT '手机',
  `sage` varchar(60) DEFAULT NULL COMMENT '年龄',
  `sremarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `isemploy` int(1) DEFAULT '1' COMMENT '状态:1启用2禁用',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='联系人';

-- ----------------------------
-- Records of tb_linkman
-- ----------------------------
INSERT INTO `tb_linkman` VALUES ('1', '张三', '人事', '15345042356', '25', '无', '1');
INSERT INTO `tb_linkman` VALUES ('2', '李四', '市场', '15345042357', '30', '1月入职', '1');
INSERT INTO `tb_linkman` VALUES ('3', '王五', '研发', '15345042358', '35', '无', '1');
INSERT INTO `tb_linkman` VALUES ('4', '赵六', '产品', '15345042359', '29', null, '1');

-- ----------------------------
-- Table structure for tb_menus
-- ----------------------------
DROP TABLE IF EXISTS `tb_menus`;
CREATE TABLE `tb_menus` (
  `sid` int(10) NOT NULL AUTO_INCREMENT COMMENT '功能菜单id',
  `sname` varchar(100) NOT NULL COMMENT '功能名称',
  `spath` varchar(255) DEFAULT NULL COMMENT '功能路径',
  `nparentid` int(10) DEFAULT '0' COMMENT '父类功能id',
  `isemploy` int(1) DEFAULT '1' COMMENT '状态:1启用2禁用',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='菜单,已不用';

-- ----------------------------
-- Records of tb_menus
-- ----------------------------
INSERT INTO `tb_menus` VALUES ('1', '首页', '', '0', '1');
INSERT INTO `tb_menus` VALUES ('3', 'web功能', '', '0', '1');
INSERT INTO `tb_menus` VALUES ('4', '生活功能', '', '0', '1');
INSERT INTO `tb_menus` VALUES ('5', '功能3', '', '0', '1');
INSERT INTO `tb_menus` VALUES ('6', '系统管理', '', '0', '1');
INSERT INTO `tb_menus` VALUES ('7', 'css样式', '', '3', '1');
INSERT INTO `tb_menus` VALUES ('8', '常见网络错误', 'Reception/weberror/weberror.html', '3', '1');
INSERT INTO `tb_menus` VALUES ('9', '左侧功能13', '', '3', '1');
INSERT INTO `tb_menus` VALUES ('10', '左侧功能14', '', '3', '1');
INSERT INTO `tb_menus` VALUES ('11', '左侧功能15', '', '3', '1');
INSERT INTO `tb_menus` VALUES ('12', '费用管理', 'Reception/daily/costInfo.html', '4', '1');
INSERT INTO `tb_menus` VALUES ('13', '左侧功能22', '', '4', '1');
INSERT INTO `tb_menus` VALUES ('14', '左侧功能23', '', '4', '1');
INSERT INTO `tb_menus` VALUES ('15', '左侧功能24', '', '4', '1');
INSERT INTO `tb_menus` VALUES ('16', '左侧功能25', '', '4', '1');
INSERT INTO `tb_menus` VALUES ('17', '左侧功能31', '', '5', '1');
INSERT INTO `tb_menus` VALUES ('18', '左侧功能32', '', '5', '1');
INSERT INTO `tb_menus` VALUES ('19', '左侧功能33', '', '5', '1');
INSERT INTO `tb_menus` VALUES ('20', '左侧功能34', '', '5', '1');
INSERT INTO `tb_menus` VALUES ('21', '左侧功能35', '', '5', '1');
INSERT INTO `tb_menus` VALUES ('22', '代码生成', 'Reception/manage/generate.html', '6', '1');
INSERT INTO `tb_menus` VALUES ('23', '部门管理', 'Reception/sys/dept.html', '6', '1');
INSERT INTO `tb_menus` VALUES ('24', '左侧功能43', '', '6', '1');
INSERT INTO `tb_menus` VALUES ('25', '左侧功能44', '', '6', '1');
INSERT INTO `tb_menus` VALUES ('26', '左侧功能45', '', '6', '1');
INSERT INTO `tb_menus` VALUES ('27', '用户注册', 'Reception/user/login.html', '1', '1');
INSERT INTO `tb_menus` VALUES ('28', '首页左侧功能2', '', '1', '1');
INSERT INTO `tb_menus` VALUES ('29', '首页左侧功能3', '', '1', '1');
INSERT INTO `tb_menus` VALUES ('30', '首页左侧功能4', '', '1', '1');
INSERT INTO `tb_menus` VALUES ('31', '首页左侧功能5', '', '1', '1');

-- ----------------------------
-- Table structure for tb_weberror
-- ----------------------------
DROP TABLE IF EXISTS `tb_weberror`;
CREATE TABLE `tb_weberror` (
  `sid` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `serrorid` int(10) NOT NULL COMMENT '错误代码',
  `sname` varchar(255) DEFAULT NULL COMMENT '错误名称',
  `sreason` varchar(255) DEFAULT NULL COMMENT '错误原因',
  `sresolvent` varchar(255) DEFAULT NULL COMMENT '解决方案',
  `isemploy` int(1) DEFAULT '1' COMMENT '状态:1启用2禁用',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_weberror
-- ----------------------------
INSERT INTO `tb_weberror` VALUES ('1', '404', null, '页面找不到', '路径问题', '1');
INSERT INTO `tb_weberror` VALUES ('2', '404', null, '参数错误', '检查参数', '1');
INSERT INTO `tb_weberror` VALUES ('3', '500', null, '服务器错误', '查看编译器的错误提示', '1');
INSERT INTO `tb_weberror` VALUES ('4', '406', null, '数据无法接收', '服务器与客户端发送的数据编码模式不同', '1');
INSERT INTO `tb_weberror` VALUES ('5', '415', null, '媒体转换错误', '传输方式错误,检查传输方式(ajax传输,接收方式却不一样)', '1');
INSERT INTO `tb_weberror` VALUES ('6', '400', 'Bad Request', '前后台参数类型不一致', '检查参数类型123', '1');
