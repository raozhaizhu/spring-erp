package com.erp.minierp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erp.minierp.controller.param.MaterialCategoryListParam;
import com.erp.minierp.datasource.entity.MaterialCategory;

import java.util.List;

/**
 * <p>
 * 产品类型表 服务类
 * </p>
 *
 * @author raozhaizhu
 * @since 2026-08-24
 */
public interface IMaterialCategoryService extends IService<MaterialCategory> {

    Page<MaterialCategory> selectMaterialCategoryPage(MaterialCategoryListParam materialCategoryListParam);

    MaterialCategory createMaterialCategory(MaterialCategory materialCategory);

    void removeMaterialCategories(List<Long> ids);
}
