-- V1.0.0__create_material_table.sql
-- Author: raozhaizhu
-- Date: 2026/8/24 16:48

CREATE TABLE `material` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category_id` bigint DEFAULT NULL COMMENT '产品类型id',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `mfrs` varchar(50) DEFAULT NULL COMMENT '制造商',
  `model` varchar(100) DEFAULT NULL COMMENT '型号',
  `standard` varchar(100) DEFAULT NULL COMMENT '规格',
  `brand` varchar(100) DEFAULT NULL COMMENT '品牌',
  `mnemonic` varchar(100) DEFAULT NULL COMMENT '助记码',
  `color` varchar(50) DEFAULT NULL COMMENT '颜色',
  `unit` varchar(50) DEFAULT NULL COMMENT '单位-单个',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `img_name` varchar(1000) DEFAULT NULL COMMENT '图片名称',
  `unit_id` bigint DEFAULT NULL COMMENT '单位Id',
  `expiry_num` int DEFAULT NULL COMMENT '保质期天数',
  `weight` decimal(24, 6) DEFAULT NULL COMMENT '基础重量(kg)',
  `enabled` bit(1) DEFAULT NULL COMMENT '启用 0-禁用 1-启用',
  `other_field1` varchar(500) DEFAULT NULL COMMENT '自定义1',
  `other_field2` varchar(500) DEFAULT NULL COMMENT '自定义2',
  `other_field3` varchar(500) DEFAULT NULL COMMENT '自定义3',
  `enable_serial_number` varchar(1) DEFAULT '0' COMMENT '是否开启序列号，0否，1是',
  `enable_batch_number` varchar(1) DEFAULT '0' COMMENT '是否开启批号，0否，1是',
  `position` varchar(100) DEFAULT NULL COMMENT '仓位货架',
  `attribute` varchar(1000) DEFAULT NULL COMMENT '多属性信息',
  `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  `delete_flag` varchar(1) DEFAULT '0' COMMENT '删除标记，0未删除，1删除',
  PRIMARY KEY (`id`),
  KEY `idx_category_id` (`category_id`),
  KEY `idx_unit_id` (`unit_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE = utf8mb4_general_ci COMMENT='产品表';

