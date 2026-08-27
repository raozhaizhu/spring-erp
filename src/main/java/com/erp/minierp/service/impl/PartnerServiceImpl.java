package com.erp.minierp.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erp.minierp.controller.param.PartnerListParam;
import com.erp.minierp.datasource.entity.Partner;
import com.erp.minierp.exception.BusinessException;
import com.erp.minierp.mapper.PartnerMapper;
import com.erp.minierp.service.IPartnerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PartnerServiceImpl extends ServiceImpl<PartnerMapper, Partner> implements IPartnerService {

    @Override
    public Page<Partner> selectPartnerPage(PartnerListParam param) {
        Page<Partner> page = new Page<>(param.getPageNum(), param.getPageSize());
        LambdaQueryWrapper<Partner> wrapper = new LambdaQueryWrapper<Partner>()
                .like(StrUtil.isNotBlank(param.getName()), Partner::getName, param.getName())
                .eq(param.getType() != null, Partner::getType, param.getType())
                .eq(param.getEnabled() != null, Partner::getEnabled, param.getEnabled())
                .orderByAsc(Partner::getSort)
                .orderByDesc(Partner::getId);
        return this.page(page, wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removePartners(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        }

        long systemPartnerCount = this.count(new LambdaQueryWrapper<Partner>()
                .in(Partner::getId, ids)
                .eq(Partner::getIsSystem, true));
        if (systemPartnerCount > 0) {
            throw new BusinessException(400, "系统内置往来单位不可删除");
        }

        this.removeByIds(ids);
    }
}
