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
 * @since 2026-08-26
 */
@Getter
@Setter
@TableName("depot")
public class Depot implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父仓库ID（0表示顶级仓库）
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 仓库名称
     */
    @TableField("name")
    private String name;

    /**
     * 仓库地址
     */
    @TableField("address")
    private String address;

    /**
     * 仓储费
     */
    @TableField("warehousing")
    private BigDecimal warehousing;

    /**
     * 搬运费
     */
    @TableField("truckage")
    private BigDecimal truckage;

    /**
     * 类型
     */
    @TableField("type")
    private Integer type;

    /**
     * 排序
     */
    @TableField("sort")
    private String sort;

    /**
     * 描述
     */
    @TableField("remark")
    private String remark;

    /**
     * 负责人
     */
    @TableField("principal")
    private Long principal;

    /**
     * 启用
     */
    @TableField("enabled")
    private Boolean enabled;

    /**
     * 租户id
     */
    @TableField("tenant_id")
    private Long tenantId;

    /**
     * 删除标记，0未删除，1删除
     */
    @TableField("delete_flag")
    private String deleteFlag;

    /**
     * 是否默认
     */
    @TableField("is_default")
    private Boolean isDefault;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;
}
