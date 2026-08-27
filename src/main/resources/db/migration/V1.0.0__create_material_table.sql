-- V1.0.0__create_material_table.sql
-- Author: raozhaizhu
-- Date: 2026/8/26

DROP TABLE IF EXISTS `material`;

CREATE TABLE `material` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `tenant_id` bigint NOT NULL COMMENT '租户 ID',
  `category_id` bigint NOT NULL COMMENT '产品类型 ID',
  `name` varchar(100) NOT NULL COMMENT '产品名称',
  `brand` varchar(100) DEFAULT NULL COMMENT '品牌',
  `mfrs` varchar(50) DEFAULT NULL COMMENT '制造商',
  `model` varchar(100) DEFAULT NULL COMMENT '型号',
  `standard` varchar(100) DEFAULT NULL COMMENT '规格',
  `mnemonic` varchar(100) DEFAULT NULL COMMENT '助记码（用于快速检索）',
  `color` varchar(50) DEFAULT NULL COMMENT '颜色',
  `unit_id` bigint NOT NULL COMMENT '单位 ID（关联计量单位表）',
  `weight` decimal(24, 6) DEFAULT NULL COMMENT '基础重量 (kg)',
  `expiry_num` int DEFAULT NULL COMMENT '保质期天数',
  `position` varchar(100) DEFAULT NULL COMMENT '默认仓位货架',
  `enabled` tinyint NOT NULL DEFAULT 1 COMMENT '启用状态：0-禁用，1-启用',
  `enable_serial_number` tinyint NOT NULL DEFAULT 0 COMMENT '是否开启序列号管理：0-否，1-是',
  `enable_batch_number` tinyint NOT NULL DEFAULT 0 COMMENT '是否开启批号管理：0-否，1-是',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `extra_attributes` json DEFAULT NULL COMMENT '动态扩展属性',

  -- 审计与逻辑删除
  `delete_flag` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除标记：0-未删除，1-已删除',
  `create_by` bigint DEFAULT NULL COMMENT '创建人 ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人 ID',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

  PRIMARY KEY (`id`),
  KEY `idx_category_id` (`category_id`),
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='产品表';