package com.erp.minierp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.erp.minierp.controller.param.PartnerListParam;
import com.erp.minierp.datasource.entity.Partner;

import java.util.List;

public interface IPartnerService extends IService<Partner> {

    Page<Partner> selectPartnerPage(PartnerListParam param);

    void removePartners(List<Long> ids);
}
