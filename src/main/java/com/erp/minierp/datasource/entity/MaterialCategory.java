package com.erp.minierp.datasource.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 产品类型表
 * </p>
 *
 * @author raozhaizhu
 * @since 2026-08-27
 */
@Getter
@Setter
@TableName("material_category")
public class MaterialCategory implements Serializable {

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
     * 上级 ID（0表示顶级分类）
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 树形节点路径（如: 0,1,5,）
     */
    @TableField("tree_path")
    private String treePath;

    /**
     * 分类名称
     */
    @TableField("name")
    private String name;

    /**
     * 分类编号（由业务规则自动生成）
     */
    @TableField("serial_no")
    private String serialNo;

    /**
     * 显示顺序（数字越小越靠前）
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 备注
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
