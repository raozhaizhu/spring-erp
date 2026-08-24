package com.erp.minierp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.minierp.controller.param.MaterialQueryParam;
import com.erp.minierp.datasource.entity.Material;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 产品表 服务类
 * </p>
 *
 * @author raozhaizhu
 * @since 2026-08-24
 */
public interface IMaterialService extends IService<Material> {
    Page<Material> selectMaterialPage(MaterialQueryParam param);
}
