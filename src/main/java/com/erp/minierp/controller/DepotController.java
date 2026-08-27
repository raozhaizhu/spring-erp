package com.erp.minierp.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.minierp.common.Result;
import com.erp.minierp.controller.dto.depot.DepotAddRequest;
import com.erp.minierp.controller.dto.depot.DepotUpdateRequest;
import com.erp.minierp.controller.param.DepotListParam;
import com.erp.minierp.datasource.entity.Depot;
import com.erp.minierp.service.IDepotService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 仓库表 前端控制器
 * </p>
 *
 * @author raozhaizhu
 * @since 2026-08-26
 */
@RestController
@RequestMapping("/depot")
public class DepotController {
    @Autowired
    private IDepotService depotService;

    @GetMapping("/list")
    public Result<Page<Depot>> list(@Valid DepotListParam depotListParam) {
        Page<Depot> page = depotService.selectDepotPage(depotListParam);
        return Result.success(page);
    }

    @GetMapping("{id}")
    public Result<Depot> getInfo(@PathVariable Long id) {
        Depot depot = depotService.getById(id);
        if (depot == null) {
            return Result.error(404, "仓库不存在");
        }
        return Result.success(depot);
    }

    @PostMapping
    public Result<Depot> add(@RequestBody @Valid DepotAddRequest depotAddRequest) {
        Depot depot = new Depot();
        BeanUtils.copyProperties(depotAddRequest, depot);

        Depot createdDepot = depotService.createDepot(depot);
        return Result.success(createdDepot);
    }

    @PutMapping
    public Result<Void> update(@RequestBody @Valid DepotUpdateRequest depotUpdateRequest) {
        Depot depot = new Depot();
        BeanUtils.copyProperties(depotUpdateRequest, depot);

        boolean success = depotService.updateById(depot);
        if (!success) {
            return Result.error(404, "修改失败：目标仓库不存在");
        }
        return Result.success();
    }

    @DeleteMapping("/{ids}")
    public Result<Void> delete(@PathVariable List<Long> ids) {
        depotService.removeDepots(ids);

        return Result.success();
    }
}
