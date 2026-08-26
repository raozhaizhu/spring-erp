package com.erp.minierp.controller.param;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DepotListParam extends BasePageParam{
    private String name;

    private Integer type;

    private Boolean enabled;

    private Long principal;

    private Long parentId;
}
