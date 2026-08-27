package com.erp.minierp.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.minierp.common.Result;
import com.erp.minierp.controller.dto.material.MaterialAddRequest;
import com.erp.minierp.controller.dto.material.MaterialUpdateRequest;
import com.erp.minierp.controller.param.MaterialListParam;
import com.erp.minierp.datasource.entity.Material;
import com.erp.minierp.service.IMaterialService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 产品表 前端控制器
 * </p>
 *
 * @author raozhaizhu
 * @since 2026-08-24
 */
@RestController
@RequestMapping("/material")
public class MaterialController {

    @Autowired
    private IMaterialService materialService;


    @GetMapping("/list")
    public Result<Page<Material>> list(@Valid MaterialListParam param) {
        Page<Material> page = materialService.selectMaterialPage(param);
        return Result.success(page);
    }

    @GetMapping("/{id}")
    public Result<Material> getInfo(@PathVariable Long id) {
        Material material = materialService.getById(id);
        if (material == null) {
            return Result.error(404, "材料不存在");
        }
        return Result.success(material);
    }

    @PostMapping
    public Result<Material> add(@RequestBody @Valid MaterialAddRequest materialAddRequest) {
        Material material = new Material();
        BeanUtils.copyProperties(materialAddRequest, material);

        materialService.save(material);
        return Result.success(material);
    }

    @PutMapping
    public Result<Void> update(@RequestBody @Valid MaterialUpdateRequest materialUpdateRequest) {
        Material material = new Material();
        BeanUtils.copyProperties(materialUpdateRequest, material);

        boolean success = materialService.updateById(material);
        if (!success) {
            return Result.error(404, "修改失败：目标材料不存在");
        }
        return Result.success();
    }


    @DeleteMapping("/{ids}")
    public Result<Void> delete(@PathVariable List<Long> ids) {
        materialService.removeByIds(ids);
        return Result.success();
    }
}