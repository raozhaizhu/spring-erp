package com.erp.minierp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erp.minierp.controller.param.MaterialListParam;
import com.erp.minierp.datasource.entity.Material;

/**
 * <p>
 * 产品表 服务类
 * </p>
 *
 * @author raozhaizhu
 * @since 2026-08-24
 */
public interface IMaterialService extends IService<Material> {
    Page<Material> selectMaterialPage(MaterialListParam param);


}
