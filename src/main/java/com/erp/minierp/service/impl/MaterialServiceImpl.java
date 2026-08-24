package com.erp.minierp.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.minierp.controller.param.MaterialQueryParam;
import com.erp.minierp.datasource.entity.Material;
import com.erp.minierp.mapper.MaterialMapper;
import com.erp.minierp.service.IMaterialService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品表 服务实现类
 * </p>
 *
 * @author raozhaizhu
 * @since 2026-08-24
 */
@Service
public class MaterialServiceImpl extends ServiceImpl<MaterialMapper, Material> implements IMaterialService {
    public Page<Material> selectMaterialPage(MaterialQueryParam param){
        // 1. 构建分页对象
        Page<Material> page = new Page<>(param.getPageNum(), param.getPageSize());

        // 2. 构建动态查询条件
        LambdaQueryWrapper<Material> wrapper = new LambdaQueryWrapper<Material>()
                .eq(param.getCategoryId() != null, Material::getCategoryId, param.getCategoryId())
                .like(StrUtil.isNotBlank(param.getName()), Material::getName, param.getName())
                .orderByDesc(Material::getId);
        // 3. 执行 MyBatis-Plus 自带的分页查询
        return this.page(page, wrapper);
    }
}
