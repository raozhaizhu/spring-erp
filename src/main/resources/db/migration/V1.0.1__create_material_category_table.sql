-- V1.0.1__create_material_category_table.sql
-- Author: raozhaizhu
-- Date: 2026/8/26

DROP TABLE IF EXISTS `material_category`;

CREATE TABLE `material_category` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `tenant_id` bigint NOT NULL COMMENT '租户 ID',
  `parent_id` bigint NOT NULL DEFAULT 0 COMMENT '上级 ID（0表示顶级分类）',
  `name` varchar(50) NOT NULL COMMENT '分类名称',
  `serial_no` varchar(64) NOT NULL COMMENT '分类编号（由业务规则自动生成）',
  `sort` int NOT NULL DEFAULT 0 COMMENT '显示顺序（数字越小越靠前）',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',

  -- 审计与逻辑删除
  `delete_flag` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除标记：0-未删除，1-已删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

  PRIMARY KEY (`id`),

  -- 同一个租户下，分类编号不能重复
  UNIQUE KEY `uk_tenant_serial` (`tenant_id`, `serial_no`),

  --同一个租户、同一个父节点下，不能出现同名的兄弟节点
  UNIQUE KEY `uk_tenant_parent_name` (`tenant_id`, `parent_id`, `name`),

  KEY `idx_parent_id` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='产品类型表';