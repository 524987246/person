/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.1.49-community-log : Database - db_person
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_person` /*!40100 DEFAULT CHARACTER SET utf8 */;

/*Table structure for table `sys_menu` */

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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='菜单表';

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`name`,`url`,`parent_id`,`perms`,`type`,`icon`,`order_num`,`create_by`,`create_date`,`update_by`,`update_date`,`remarks`,`isemploy`) values (1,'平台','',0,'',1,'1',1,0,'2017-10-11 14:44:03',0,'2017-10-11 14:44:06',NULL,'1'),(2,'编程辅助','2',0,'',1,'1',1,0,'2017-10-11 14:44:03',0,'2017-10-11 14:44:06',NULL,'1'),(3,'系统功能','3',0,'',1,'1',1,0,'2017-10-11 14:44:03',0,'2017-10-11 14:44:06',NULL,'1'),(4,'测试菜单4','4',0,'',1,'1',1,0,'2017-10-11 14:44:03',0,'2017-10-11 14:44:06',NULL,'1'),(5,'子菜单1-1','5',1,'',1,'1',1,0,'2017-10-11 14:44:03',0,'2017-10-11 14:44:06',NULL,'1'),(6,'子菜单1-2','6',1,'',1,'1',1,0,'2017-10-11 14:44:03',0,'2017-10-11 14:44:06',NULL,'1'),(7,'子菜单1-3','7',1,'',1,'1',1,0,'2017-10-11 14:44:03',0,'2017-10-11 14:44:06',NULL,'1'),(8,'子菜单1-1-1','8',5,'',1,'1',1,0,'2017-10-11 14:44:03',0,'2017-10-11 14:44:06',NULL,'1'),(9,'子菜单1-1-2','9',5,'',1,'1',1,0,'2017-10-11 14:44:03',0,'2017-10-11 14:44:06',NULL,'1'),(10,'子菜单1-2-1','10',6,'',1,'1',1,0,'2017-10-11 14:44:03',0,'2017-10-11 14:44:06',NULL,'1'),(11,'子菜单1-2-2','11',6,'',1,'1',1,0,'2017-10-11 14:44:03',0,'2017-10-11 14:44:06',NULL,'1'),(12,'开发辅助','',3,'',1,'1',1,0,'2017-10-11 14:44:03',0,'2017-10-11 14:44:06',NULL,'1'),(13,'代码生成','Reception/manage/generate.html',12,'',1,'1',1,0,'2017-10-11 14:44:03',0,'2017-10-11 14:44:06',NULL,'1'),(14,'子菜单1-1-2-1','14',9,'',1,'1',1,0,'2017-10-11 14:44:03',0,'2017-10-11 14:44:06',NULL,'1'),(15,'子菜单1-1-1-1','15',8,'',1,'1',1,0,'2017-10-11 14:44:03',0,'2017-10-11 14:44:06',NULL,'1'),(16,'web功能','',2,'',1,'&#xe69f;',1,0,'2017-10-11 14:44:03',0,'2017-10-11 14:44:06',NULL,'1'),(17,'web常见错误','Reception/weberror/weberror.html',16,'buz:weberror:view',1,'&#xe69f;',1,0,'2017-10-11 14:44:03',0,'2017-10-11 14:44:06',NULL,'1'),(18,'系统设置','',3,'',1,'1',1,0,'2017-10-11 14:44:03',0,'2017-10-11 14:44:06',NULL,'1'),(19,'菜单管理','/Reception/sys/menu/to.html',18,'',1,'1',1,0,'2017-10-11 14:44:03',0,'2017-10-11 14:44:06',NULL,'1'),(20,'查看','',19,'sys:menu:view',3,'1',1,0,'2017-10-11 14:44:03',0,'2017-10-11 14:44:06',NULL,'1'),(21,'增加','',19,'sys:menu:save',3,'1',1,0,'2017-10-11 14:44:03',0,'2017-10-11 14:44:06',NULL,'1'),(22,'修改','',19,'sys:menu:update',3,'1',1,0,'2017-10-11 14:44:03',0,'2017-10-11 14:44:06',NULL,'1'),(23,'删除','',19,'sys:menu:delete',3,'1',1,0,'2017-10-11 14:44:03',0,'2017-10-11 14:44:06',NULL,'1'),(24,'批量删除','',19,'sys:menu:batchdelete',3,'1',1,0,'2017-10-11 14:44:03',0,'2017-10-11 14:44:06',NULL,'1'),(25,'顺序调整','',19,'sys:menu:ordersave',3,'1',1,0,'2017-10-11 14:44:03',0,'2017-10-11 14:44:06',NULL,'1');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
