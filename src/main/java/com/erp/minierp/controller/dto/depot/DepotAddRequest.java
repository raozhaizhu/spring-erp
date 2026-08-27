package com.erp.minierp.controller.dto.depot;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DepotAddRequest extends DepotBaseRequest {

    /**
     * 父仓库ID（0表示顶级仓库）
     */
    private Long parentId = 0L;
}