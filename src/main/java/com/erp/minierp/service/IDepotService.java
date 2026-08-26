package com.erp.minierp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.minierp.controller.param.DepotListParam;
import com.erp.minierp.datasource.entity.Depot;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 仓库表 服务类
 * </p>
 *
 * @author raozhaizhu
 * @since 2026-08-26
 */
public interface IDepotService extends IService<Depot> {

    Page<Depot> selectDepotPage(DepotListParam depotListParam);
}
