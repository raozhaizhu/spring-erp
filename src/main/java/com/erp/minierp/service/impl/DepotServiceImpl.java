package com.erp.minierp.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.erp.minierp.controller.param.DepotListParam;
import com.erp.minierp.datasource.entity.Depot;
import com.erp.minierp.exception.BusinessException;
import com.erp.minierp.mapper.DepotMapper;
import com.erp.minierp.service.IDepotService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 仓库表 服务实现类
 * </p>
 *
 * @author raozhaizhu
 * @since 2026-08-26
 */
@Service
public class DepotServiceImpl extends ServiceImpl<DepotMapper, Depot> implements IDepotService {
    public Page<Depot> selectDepotPage(DepotListParam param) {
        // 1. 构建分页对象
        Page<Depot> page = new Page<>(param.getPageNum(), param.getPageSize());

        // 2. 构建动态查询条件
        LambdaQueryWrapper<Depot> wrapper = new LambdaQueryWrapper<Depot>()
                .like(StrUtil.isNotBlank(param.getName()), Depot::getName, param.getName())
                .eq(param.getType() != null, Depot::getType, param.getType())
                .eq(param.getEnabled() != null, Depot::getEnabled, param.getEnabled())
//                .eq(param.getPrincipal() != null, Depot::getPrincipal, param.getPrincipal())
                .eq(param.getParentId() != null, Depot::getParentId, param.getParentId())
                .orderByDesc(Depot::getId);
        // 3. 执行 MyBatis-Plus 自带的分页查询
        return this.page(page, wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Depot createDepot(Depot depot) {
        super.save(depot);
        Long currId = depot.getId();

        String treePath;
        if (depot.getParentId() == 0) {
            treePath = "0," + currId + ",";
        } else {
            Depot parent = super.getById(depot.getParentId());
            treePath = parent.getTreePath() + currId + ",";
        }

        Depot updateObj = new Depot();
        updateObj.setId(currId);
        updateObj.setTreePath(treePath);
        super.updateById(updateObj);
        return super.getById(currId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeDepots(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        }

        // 1. 查询这批 ids 内是否有人非叶子节点(拥有子节点), 若有, 将其儿子统计出来
        List<Depot> childrenWhoseParentInIds = this.list(new LambdaQueryWrapper<Depot>()
                .in(Depot::getParentId, ids)
                .select(Depot::getParentId)
        );

        if (!childrenWhoseParentInIds.isEmpty()) {
            // 2. 将查到的 id 收集到 set 内去重
            Set<Long> parentIds = childrenWhoseParentInIds.stream()
                    .map(Depot::getParentId)
                    .collect(Collectors.toSet());

            // 3. 遍历传入的 ids, 统计谁在该 set 内
            for (Long id : ids) {
                if (parentIds.contains(id)) {
                    Depot depot = this.getById(id);
                    String name = (depot != null) ? (depot.getName()) : "ID: " + id;
                    throw new BusinessException(400, "仓库 [" + name + "] 下存在子仓库，无法直接删除！请先处理下级仓库。");
                }
            }
        }

        // 4. 校验全部通过，安全删除
        this.removeByIds(ids);
    }
}
