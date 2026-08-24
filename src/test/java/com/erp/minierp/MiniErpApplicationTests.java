package com.erp.minierp;

import com.erp.minierp.datasource.entity.Material;
import com.erp.minierp.service.IMaterialService;
import tools.jackson.databind.ObjectMapper; // 适配 Jackson 3.x
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional // 🌟 核心：为每个测试方法开启事务，测试完自动回滚，对数据库零污染！
class MaterialControllerE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private IMaterialService materialService;

    // ==================== 1. 分页列表查询测试 ====================

    @Test
    @DisplayName("GET /material/list - 真实数据库成功返回分页数据")
    void testList_Success() throws Exception {
        mockMvc.perform(get("/material/list")
                        .param("pageNum", "1")
                        .param("pageSize", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.data.records").isArray())
                .andExpect(jsonPath("$.data.total").value(25)); // 对应前面插入的25条数据
    }

    // ==================== 2. 根据 ID 查询详情测试 ====================

    @Test
    @DisplayName("GET /material/{id} - 真实数据库成功获取详情")
    void testGetInfo_Success() throws Exception {
        // 查数据库里真实存在的 ID 为 1 的数据（精密螺丝）
        mockMvc.perform(get("/material/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.name").value("精密螺丝"));
    }

    @Test
    @DisplayName("GET /material/{id} - 失败：数据不存在返回 404")
    void testGetInfo_NotFound() throws Exception {
        mockMvc.perform(get("/material/999999"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(404))
                .andExpect(jsonPath("$.msg").value("材料不存在"));
    }

    // ==================== 3. 新增商品测试 ====================

    @Test
    @DisplayName("POST /material - 真实新增商品（执行后自动回滚）")
    void testAdd_Success() throws Exception {
        Material newMaterial = new Material();
        newMaterial.setName("E2E测试自动化螺丝");
        newMaterial.setCategoryId(1L);

        mockMvc.perform(post("/material")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newMaterial)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));

    }

    // ==================== 4. 修改商品测试 ====================

    @Test
    @DisplayName("PUT /material - 真实修改商品（执行后自动回滚）")
    void testUpdate_Success() throws Exception {
        Material updateParam = new Material();
        updateParam.setId(1L);
        updateParam.setName("修改后的精密螺丝-E2E");

        mockMvc.perform(put("/material")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateParam)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));
    }

    @Test
    @DisplayName("PUT /material - 失败：修改不存在的目标返回 404")
    void testUpdate_NotFound() throws Exception {
        Material updateParam = new Material();
        updateParam.setId(999999L);
        updateParam.setName("不存在的商品");

        mockMvc.perform(put("/material")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateParam)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(404))
                .andExpect(jsonPath("$.msg").value("修改失败：目标材料不存在"));
    }

    // ==================== 5. 删除商品测试 ====================

    @Test
    @DisplayName("DELETE /material/{ids} - 真实删除商品（执行后自动回滚）")
    void testDelete_Success() throws Exception {
        // 删除真实存在的 ID 1 和 2
        mockMvc.perform(delete("/material/1,2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));
    }

    @Test
    @DisplayName("DELETE /material/{ids} - 失败：目标已不存在返回 404")
    void testDelete_NotFound() throws Exception {
        mockMvc.perform(delete("/material/999999"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(404))
                .andExpect(jsonPath("$.msg").value("删除失败：目标材料不存在"));
    }
}