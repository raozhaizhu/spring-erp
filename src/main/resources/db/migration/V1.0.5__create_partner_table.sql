-- V1.0.5__create_partner_table.sql
-- Author: raozhaizhu
-- Date: 2026/8/27 13:04

DROP TABLE IF EXISTS `partner`;

CREATE TABLE `partner` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(150) NOT NULL COMMENT '单位名称(客户/供应商)',
  `type` tinyint NOT NULL COMMENT '单位类型 (1:供应商, 2:客户, 3:既是客户又是供应商)',

  -- 联系方式
  `contact_person` varchar(50) DEFAULT NULL COMMENT '联系人',
  `contact_phone` varchar(30) DEFAULT NULL COMMENT '联系电话(手机/座机)',
  `email` varchar(50) DEFAULT NULL COMMENT '电子邮箱',
  `fax` varchar(30) DEFAULT NULL COMMENT '传真',
  `address` varchar(200) DEFAULT NULL COMMENT '详细地址',

  -- 财务与税务信息
  `tax_num` varchar(50) DEFAULT NULL COMMENT '纳税人识别号',
  `bank_name` varchar(100) DEFAULT NULL COMMENT '开户行',
  `account_number` varchar(50) DEFAULT NULL COMMENT '银行账号',
  `tax_rate` decimal(5, 2) DEFAULT '0.00' COMMENT '默认税率(%)',

  -- 期初财务数据 (仅在建账初期使用，后续变动走流水表)
  `advance_in` decimal(15, 4) DEFAULT '0.0000' COMMENT '期初预收款',
  `begin_need_get` decimal(15, 4) DEFAULT '0.0000' COMMENT '期初应收',
  `begin_need_pay` decimal(15, 4) DEFAULT '0.0000' COMMENT '期初应付',

  -- 系统控制列
  `sort` int DEFAULT '0' COMMENT '排序号(升序)',
  `enabled` tinyint(1) DEFAULT '1' COMMENT '状态 (1:启用, 0:禁用)',
  `is_system` tinyint(1) DEFAULT '0' COMMENT '是否系统内置 (1:是, 0:否，内置不可删除)',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注说明',

  -- 租户与审计字段
  `tenant_id` bigint DEFAULT NULL COMMENT '租户ID',
  `create_by` bigint DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_flag` tinyint(1) DEFAULT '0' COMMENT '逻辑删除 (0:正常, 1:删除)',

  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_type` (`type`) USING BTREE,
  KEY `idx_name` (`name`) USING BTREE,
  KEY `idx_tenant_id` (`tenant_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='往来单位(客户/供应商)表';