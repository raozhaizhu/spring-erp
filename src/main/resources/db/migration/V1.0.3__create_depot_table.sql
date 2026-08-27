-- V1.0.3__create_depot_table.sql
-- Author: raozhaizhu
-- Date: 2026/8/26

DROP TABLE IF EXISTS `depot`;

CREATE TABLE `depot` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `tenant_id` bigint NOT NULL COMMENT '租户 ID',
  `parent_id` bigint NOT NULL DEFAULT 0 COMMENT '父仓库 ID（0表示顶级仓库）',

  -- 树形路径字段
  `tree_path` varchar(255) DEFAULT NULL COMMENT '树形节点路径（如: 0,1,2,）',

  `code` varchar(64) NOT NULL COMMENT '仓库编码（如 WH-001）',
  `name` varchar(50) NOT NULL COMMENT '仓库名称',
  `type` tinyint DEFAULT 1 COMMENT '仓库类型：1-普通仓，2-冷藏仓，3-虚拟仓，4-中转仓',
  `address` varchar(200) DEFAULT NULL COMMENT '仓库地址',
  `warehousing` decimal(18, 4) DEFAULT 0.0000 COMMENT '仓储费率/单价',
  `truckage` decimal(18, 4) DEFAULT 0.0000 COMMENT '搬运费率/单价',
  `principal_id` bigint DEFAULT NULL COMMENT '负责人 ID（关联用户表）',
  `sort` int NOT NULL DEFAULT 0 COMMENT '显示顺序（数值越小越靠前）',
  `is_default` tinyint NOT NULL DEFAULT 0 COMMENT '是否默认仓库：0-否，1-是',
  `enabled` tinyint NOT NULL DEFAULT 1 COMMENT '启用状态：0-禁用，1-启用',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述/备注',

  -- 审计与逻辑删除
  `delete_flag` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除标记：0-未删除，1-已删除',
  `create_by` bigint DEFAULT NULL COMMENT '创建人 ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人 ID',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_code` (`tenant_id`, `code`),
  UNIQUE KEY `uk_tenant_parent_name` (`tenant_id`, `parent_id`, `name`),
  KEY `idx_parent_id` (`parent_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='仓库表';