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
 * 往来单位(客户/供应商)表
 * </p>
 *
 * @author raozhaizhu
 * @since 2026-08-27
 */
@Getter
@Setter
@TableName("partner")
public class Partner implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 单位名称(客户/供应商)
     */
    @TableField("name")
    private String name;

    /**
     * 单位类型 (1:供应商, 2:客户, 3:既是客户又是供应商)
     */
    @TableField("type")
    private Byte type;

    /**
     * 联系人
     */
    @TableField("contact_person")
    private String contactPerson;

    /**
     * 联系电话(手机/座机)
     */
    @TableField("contact_phone")
    private String contactPhone;

    /**
     * 电子邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 传真
     */
    @TableField("fax")
    private String fax;

    /**
     * 详细地址
     */
    @TableField("address")
    private String address;

    /**
     * 纳税人识别号
     */
    @TableField("tax_num")
    private String taxNum;

    /**
     * 开户行
     */
    @TableField("bank_name")
    private String bankName;

    /**
     * 银行账号
     */
    @TableField("account_number")
    private String accountNumber;

    /**
     * 默认税率(%)
     */
    @TableField("tax_rate")
    private BigDecimal taxRate;

    /**
     * 期初预收款
     */
    @TableField("advance_in")
    private BigDecimal advanceIn;

    /**
     * 期初应收
     */
    @TableField("begin_need_get")
    private BigDecimal beginNeedGet;

    /**
     * 期初应付
     */
    @TableField("begin_need_pay")
    private BigDecimal beginNeedPay;

    /**
     * 排序号(升序)
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 状态 (1:启用, 0:禁用)
     */
    @TableField("enabled")
    private Boolean enabled;

    /**
     * 是否系统内置 (1:是, 0:否，内置不可删除)
     */
    @TableField("is_system")
    private Boolean isSystem;

    /**
     * 备注说明
     */
    @TableField("remark")
    private String remark;

    /**
     * 租户ID
     */
    @TableField("tenant_id")
    private Long tenantId;

    /**
     * 创建人ID
     */
    @TableField("create_by")
    private Long createBy;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新人ID
     */
    @TableField("update_by")
    private Long updateBy;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 逻辑删除 (0:正常, 1:删除)
     */
    @TableField("delete_flag")
    private Boolean deleteFlag;
}
