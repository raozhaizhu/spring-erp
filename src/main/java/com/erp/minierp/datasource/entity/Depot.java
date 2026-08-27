package com.erp.minierp.datasource.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 仓库表
 * </p>
 *
 * @author raozhaizhu
 * @since 2026-08-27
 */
@Getter
@Setter
@TableName("depot")
public class Depot implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 租户 ID
     */
    @TableField("tenant_id")
    private Long tenantId;

    /**
     * 父仓库 ID（0表示顶级仓库）
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 树形节点路径（如: 0,1,2,）
     */
    @TableField("tree_path")
    private String treePath;

    /**
     * 仓库编码（如 WH-001）
     */
    @TableField("code")
    private String code;

    /**
     * 仓库名称
     */
    @TableField("name")
    private String name;

    /**
     * 仓库类型：1-普通仓，2-冷藏仓，3-虚拟仓，4-中转仓
     */
    @TableField("type")
    private Byte type;

    /**
     * 仓库地址
     */
    @TableField("address")
    private String address;

    /**
     * 仓储费率/单价
     */
    @TableField("warehousing")
    private BigDecimal warehousing;

    /**
     * 搬运费率/单价
     */
    @TableField("truckage")
    private BigDecimal truckage;

    /**
     * 负责人 ID（关联用户表）
     */
    @TableField("principal_id")
    private Long principalId;

    /**
     * 显示顺序（数值越小越靠前）
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 是否默认仓库：0-否，1-是
     */
    @TableField("is_default")
    private Byte isDefault;

    /**
     * 启用状态：0-禁用，1-启用
     */
    @TableField("enabled")
    private Byte enabled;

    /**
     * 描述/备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 逻辑删除标记：0-未删除，1-已删除
     */
    @TableField("delete_flag")
    private Byte deleteFlag;

    /**
     * 创建人 ID
     */
    @TableField("create_by")
    private Long createBy;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新人 ID
     */
    @TableField("update_by")
    private Long updateBy;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;
}
