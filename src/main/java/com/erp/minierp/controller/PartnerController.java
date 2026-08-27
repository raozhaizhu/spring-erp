package com.erp.minierp.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.minierp.common.Result;
import com.erp.minierp.controller.dto.partner.PartnerAddRequest;
import com.erp.minierp.controller.dto.partner.PartnerUpdateRequest;
import com.erp.minierp.controller.param.PartnerListParam;
import com.erp.minierp.datasource.entity.Partner;
import com.erp.minierp.service.IPartnerService;
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

@RestController
@RequestMapping("/partner")
public class PartnerController {

    @Autowired
    private IPartnerService partnerService;

    @GetMapping("/list")
    public Result<Page<Partner>> list(@Valid PartnerListParam param) {
        return Result.success(partnerService.selectPartnerPage(param));
    }

    @GetMapping("/{id}")
    public Result<Partner> getInfo(@PathVariable Long id) {
        Partner partner = partnerService.getById(id);
        if (partner == null) {
            return Result.error(404, "往来单位不存在");
        }
        return Result.success(partner);
    }

    @PostMapping
    public Result<Partner> add(@RequestBody @Valid PartnerAddRequest request) {
        Partner partner = new Partner();
        BeanUtils.copyProperties(request, partner);
        partnerService.save(partner);
        return Result.success(partner);
    }

    @PutMapping
    public Result<Void> update(@RequestBody @Valid PartnerUpdateRequest request) {
        Partner partner = new Partner();
        BeanUtils.copyProperties(request, partner);
        if (!partnerService.updateById(partner)) {
            return Result.error(404, "修改失败：目标往来单位不存在");
        }
        return Result.success();
    }

    @DeleteMapping("/{ids}")
    public Result<Void> delete(@PathVariable List<Long> ids) {
        partnerService.removePartners(ids);
        return Result.success();
    }
}
