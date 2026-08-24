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
 * @since 2026-08-24
 */
@Getter
@Setter
@TableName("material_category")
public class MaterialCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 等级
     */
    @TableField("category_level")
    private Short categoryLevel;

    /**
     * 上级id
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 显示顺序
     */
    @TableField("sort")
    private String sort;

    /**
     * 编号
     */
    @TableField("serial_no")
    private String serialNo;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

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
}
