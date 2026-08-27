package com.erp.minierp.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.minierp.common.Result;
import com.erp.minierp.controller.dto.materialcategory.MaterialCategoryAddRequest;
import com.erp.minierp.controller.dto.materialcategory.MaterialCategoryUpdateRequest;
import com.erp.minierp.controller.param.MaterialCategoryListParam;
import com.erp.minierp.datasource.entity.MaterialCategory;
import com.erp.minierp.service.IMaterialCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/** 产品分类管理接口。 */
@RestController
@RequestMapping("/materialCategory")
public class MaterialCategoryController {

    @Autowired
    private IMaterialCategoryService materialCategoryService;

    @GetMapping("/list")
    public Result<Page<MaterialCategory>> list(@Valid MaterialCategoryListParam param) {
        return Result.success(materialCategoryService.selectMaterialCategoryPage(param));
    }

    @GetMapping("/{id}")
    public Result<MaterialCategory> getInfo(@PathVariable Long id) {
        MaterialCategory materialCategory = materialCategoryService.getById(id);
        if (materialCategory == null) {
            return Result.error(404, "产品分类不存在");
        }
        return Result.success(materialCategory);
    }

    @PostMapping
    public Result<MaterialCategory> add(@RequestBody @Valid MaterialCategoryAddRequest request) {
        MaterialCategory materialCategory = new MaterialCategory();
        BeanUtils.copyProperties(request, materialCategory);
        MaterialCategory createdMaterialCategory = materialCategoryService.createMaterialCategory(materialCategory);
        return Result.success(createdMaterialCategory);
    }

    @PutMapping
    public Result<Void> update(@RequestBody @Valid MaterialCategoryUpdateRequest request) {
        MaterialCategory materialCategory = new MaterialCategory();
        BeanUtils.copyProperties(request, materialCategory);
        if (!materialCategoryService.updateById(materialCategory)) {
            return Result.error(404, "修改失败：目标产品分类不存在");
        }
        return Result.success();
    }

    @DeleteMapping("/{ids}")
    public Result<Void> delete(@PathVariable List<Long> ids) {
        materialCategoryService.removeMaterialCategories(ids);
        return Result.success();
    }
}
