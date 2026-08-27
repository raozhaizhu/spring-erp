package com.erp.minierp.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erp.minierp.controller.param.MaterialCategoryListParam;
import com.erp.minierp.datasource.entity.MaterialCategory;
import com.erp.minierp.exception.BusinessException;
import com.erp.minierp.mapper.MaterialCategoryMapper;
import com.erp.minierp.service.IMaterialCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 产品类型表 服务实现类
 * </p>
 *
 * @author raozhaizhu
 * @since 2026-08-24
 */
@Service
public class MaterialCategoryServiceImpl extends ServiceImpl<MaterialCategoryMapper, MaterialCategory> implements IMaterialCategoryService {

    @Override
    public Page<MaterialCategory> selectMaterialCategoryPage(MaterialCategoryListParam param) {
        Page<MaterialCategory> page = new Page<>(param.getPageNum(), param.getPageSize());
        LambdaQueryWrapper<MaterialCategory> wrapper = new LambdaQueryWrapper<MaterialCategory>()
                .eq(param.getTenantId() != null, MaterialCategory::getTenantId, param.getTenantId())
                .like(StrUtil.isNotBlank(param.getName()), MaterialCategory::getName, param.getName())
                .eq(param.getParentId() != null, MaterialCategory::getParentId, param.getParentId())
                .orderByAsc(MaterialCategory::getSort)
                .orderByDesc(MaterialCategory::getId);
        return this.page(page, wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MaterialCategory createMaterialCategory(MaterialCategory materialCategory) {
        super.save(materialCategory);
        Long currId = materialCategory.getId();

        String treePath;
        if (materialCategory.getParentId() == 0) {
            treePath = "0," + currId + ",";
        } else {
            MaterialCategory parent = super.getById(materialCategory.getParentId());
            treePath = parent.getTreePath() + currId + ",";
        }

        MaterialCategory updateObj = new MaterialCategory();
        updateObj.setId(currId);
        updateObj.setTreePath(treePath);
        super.updateById(updateObj);
        return super.getById(currId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeMaterialCategories(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        }

        // 1. 查询这批 ids 内是否有人非叶子节点(拥有子节点), 若有, 将其儿子统计出来
        List<MaterialCategory> childrenWhoseParentInIds = this.list(new LambdaQueryWrapper<MaterialCategory>()
                .in(MaterialCategory::getParentId, ids)
                .select(MaterialCategory::getParentId)
        );

        if (!childrenWhoseParentInIds.isEmpty()) {
            // 2. 将查到的 id 收集到 set 内去重
            Set<Long> parentIds = childrenWhoseParentInIds.stream()
                    .map(MaterialCategory::getParentId)
                    .collect(Collectors.toSet());

            // 3. 遍历传入的 ids, 统计谁在该 set 内
            for (Long id : ids) {
                if (parentIds.contains(id)) {
                    MaterialCategory materialCategory = this.getById(id);
                    String name = (materialCategory != null) ? (materialCategory.getName()) : "ID: " + id;
                    throw new BusinessException(400, "产品分类 [" + name + "] 下存在子分类，无法直接删除！请先处理下级分类。");
                }
            }
        }

        this.removeByIds(ids);
    }
}
