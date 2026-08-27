package com.erp.minierp.controller;

import com.erp.minierp.datasource.entity.Depot;
import com.erp.minierp.service.IDepotService;
import com.erp.minierp.testutil.RandomBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class DepotE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private IDepotService depotService;

    // ==================== 1. 分页列表查询测试 ====================

    @Test
    @DisplayName("GET /depot/list - 真实数据库成功返回分页数据")
    void testList_Success() throws Exception {
        mockMvc.perform(get("/depot/list")
                        .param("pageNum", "1")
                        .param("pageSize", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.data.records").isArray())
                .andExpect(jsonPath("$.data.records").value(hasSize(5)))
                .andExpect(jsonPath("$.data.total").value(7));
    }

    // ==================== 2. 根据 ID 查询详情测试 ====================

    @Test
    @DisplayName("GET /depot/{id} - 真实数据库成功获取详情")
    void testGetInfo_Success() throws Exception {
        mockMvc.perform(get("/depot/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.data.id").value(1));
    }

    @Test
    @DisplayName("GET /depot/{id} - 失败：数据不存在返回 404")
    void testGetInfo_NotFound() throws Exception {
        mockMvc.perform(get("/depot/999999"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(404))
                .andExpect(jsonPath("$.msg").value("仓库不存在"));
    }

    // ==================== 3. 新增仓库测试 ====================

    @Test
    @DisplayName("POST /depot - 真实新增仓库（执行后自动回滚）")
    void testAdd_Success() throws Exception {
        Depot newDepot = RandomBuilder.randomDepot();

        mockMvc.perform(post("/depot")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newDepot)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.data.treePath")
                        .value(org.hamcrest.Matchers.matchesPattern("0,\\d+,")));
    }

    // ==================== 4. 修改仓库测试 ====================

    @Test
    @DisplayName("PUT /depot - 真实修改仓库（执行后自动回滚）")
    void testUpdate_Success() throws Exception {
        Depot updateParam = RandomBuilder.randomDepot();
        updateParam.setId(1L);

        mockMvc.perform(put("/depot")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateParam)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));
    }

    @Test
    @DisplayName("PUT /depot - 失败：修改不存在的目标返回 404")
    void testUpdate_NotFound() throws Exception {
        Depot updateParam = RandomBuilder.randomDepot();
        mockMvc.perform(put("/depot")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateParam)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(404))
                .andExpect(jsonPath("$.msg").value("修改失败：目标仓库不存在"));
    }

    // ==================== 5. 删除仓库测试 ====================

    @Test
    @DisplayName("DELETE /depot/{ids} - 真实删除仓库（执行后自动回滚）")
    void testDelete_Success() throws Exception {
        // ID 4、5 均是初始化数据中的叶子节点，可被安全删除。
        mockMvc.perform(delete("/depot/4,5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));
    }

    @Test
    @DisplayName("DELETE /depot/{ids} - 存在子仓库时禁止删除")
    void testDelete_WithChildren_Failed() throws Exception {
        Depot parent = depotService.createDepot(RandomBuilder.randomDepot());
        Depot child = RandomBuilder.randomDepot();
        child.setParentId(parent.getId());
        depotService.createDepot(child);

        mockMvc.perform(delete("/depot/{ids}", parent.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(400))
                .andExpect(jsonPath("$.msg").value("仓库 [" + parent.getName()
                        + "] 下存在子仓库，无法直接删除！请先处理下级仓库。"));
    }

}
