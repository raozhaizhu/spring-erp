package com.erp.minierp.controller;

import com.erp.minierp.datasource.entity.MaterialCategory;
import com.erp.minierp.service.IMaterialCategoryService;
import com.erp.minierp.testutil.RandomBuilder;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.matchesPattern;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class MaterialCategoryE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private IMaterialCategoryService materialCategoryService;

    @Test
    @DisplayName("GET /materialCategory/list - 真实数据库成功返回分页数据")
    void testList_Success() throws Exception {
        mockMvc.perform(get("/materialCategory/list")
                        .param("pageNum", "1")
                        .param("pageSize", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.data.records").isArray())
                .andExpect(jsonPath("$.data.records").value(hasSize(5)))
                .andExpect(jsonPath("$.data.total").value(5));
    }

    @Test
    @DisplayName("GET /materialCategory/{id} - 真实数据库成功获取详情")
    void testGetInfo_Success() throws Exception {
        mockMvc.perform(get("/materialCategory/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.name").value("电子配件"));
    }

    @Test
    @DisplayName("GET /materialCategory/{id} - 失败：数据不存在返回 404")
    void testGetInfo_NotFound() throws Exception {
        mockMvc.perform(get("/materialCategory/999999"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(404))
                .andExpect(jsonPath("$.msg").value("产品分类不存在"));
    }

    @Test
    @DisplayName("POST /materialCategory - 真实新增产品分类（执行后自动回滚）")
    void testAdd_Success() throws Exception {
        MaterialCategory newMaterialCategory = RandomBuilder.randomMaterialCategory();

        mockMvc.perform(post("/materialCategory")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newMaterialCategory)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.data.treePath").value(matchesPattern("0,\\d+,")));

        MaterialCategory savedCategory = materialCategoryService.getOne(
                new LambdaQueryWrapper<MaterialCategory>()
                        .eq(MaterialCategory::getSerialNo, newMaterialCategory.getSerialNo()));
        assertThat(savedCategory.getTreePath(), matchesPattern("0,\\d+,"));
    }

    @Test
    @DisplayName("PUT /materialCategory - 真实修改产品分类（执行后自动回滚）")
    void testUpdate_Success() throws Exception {
        MaterialCategory updateParam = RandomBuilder.randomMaterialCategory();
        updateParam.setId(1L);

        mockMvc.perform(put("/materialCategory")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateParam)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));
    }

    @Test
    @DisplayName("PUT /materialCategory - 失败：修改不存在的目标返回 404")
    void testUpdate_NotFound() throws Exception {
        MaterialCategory updateParam = RandomBuilder.randomMaterialCategory();
        updateParam.setId(999999L);

        mockMvc.perform(put("/materialCategory")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateParam)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(404))
                .andExpect(jsonPath("$.msg").value("修改失败：目标产品分类不存在"));
    }

    @Test
    @DisplayName("DELETE /materialCategory/{ids} - 真实删除产品分类（执行后自动回滚）")
    void testDelete_Success() throws Exception {
        mockMvc.perform(delete("/materialCategory/1,2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));
    }

    @Test
    @DisplayName("DELETE /materialCategory/{ids} - 存在子分类时禁止删除")
    void testDelete_WithChildren_Failed() throws Exception {
        MaterialCategory parent = RandomBuilder.randomMaterialCategory();
        materialCategoryService.createMaterialCategory(parent);
        MaterialCategory child = RandomBuilder.randomMaterialCategory();
        child.setParentId(parent.getId());
        materialCategoryService.createMaterialCategory(child);

        mockMvc.perform(delete("/materialCategory/{ids}", parent.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(400))
                .andExpect(jsonPath("$.msg").value("产品分类 [" + parent.getName()
                        + "] 下存在子分类，无法直接删除！请先处理下级分类。"));
    }
}
