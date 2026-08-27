package com.erp.minierp.controller;

import com.erp.minierp.datasource.entity.Partner;
import com.erp.minierp.service.IPartnerService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class PartnerE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private IPartnerService partnerService;

    @Test
    @DisplayName("GET /partner/list - 按单位类型返回分页数据")
    void testList_ByType_Success() throws Exception {
        mockMvc.perform(get("/partner/list")
                        .param("pageNum", "1")
                        .param("pageSize", "5")
                        .param("type", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.data.records").value(hasSize(2)))
                .andExpect(jsonPath("$.data.total").value(2))
                .andExpect(jsonPath("$.data.records[0].type").value(1));
    }

    @Test
    @DisplayName("GET /partner/{id} - 成功获取真实初始化数据")
    void testGetInfo_Success() throws Exception {
        mockMvc.perform(get("/partner/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.name").value("瑞安市通达五金制造厂"));
    }

    @Test
    @DisplayName("GET /partner/{id} - 不存在时返回 404")
    void testGetInfo_NotFound() throws Exception {
        mockMvc.perform(get("/partner/999999"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(404))
                .andExpect(jsonPath("$.msg").value("往来单位不存在"));
    }

    @Test
    @DisplayName("POST /partner - 成功新增往来单位")
    void testAdd_Success() throws Exception {
        Partner partner = RandomBuilder.randomPartner();

        mockMvc.perform(post("/partner")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(partner)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.data.id").isNumber())
                .andExpect(jsonPath("$.data.name").value(partner.getName()));
    }

    @Test
    @DisplayName("PUT /partner - 成功修改往来单位")
    void testUpdate_Success() throws Exception {
        Partner partner = RandomBuilder.randomPartner();
        partner.setId(1L);

        mockMvc.perform(put("/partner")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(partner)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));
    }

    @Test
    @DisplayName("PUT /partner - 不存在时返回 404")
    void testUpdate_NotFound() throws Exception {
        Partner partner = RandomBuilder.randomPartner();
        partner.setId(999999L);

        mockMvc.perform(put("/partner")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(partner)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(404))
                .andExpect(jsonPath("$.msg").value("修改失败：目标往来单位不存在"));
    }

    @Test
    @DisplayName("DELETE /partner/{ids} - 成功删除非系统往来单位")
    void testDelete_Success() throws Exception {
        mockMvc.perform(delete("/partner/1,2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));
    }

    @Test
    @DisplayName("DELETE /partner/{ids} - 包含系统数据时全盘拒绝")
    void testDelete_SystemPartner_FailedWithoutPartialDelete() throws Exception {
        mockMvc.perform(delete("/partner/1,6"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(400))
                .andExpect(jsonPath("$.msg").value("系统内置往来单位不可删除"));

        org.junit.jupiter.api.Assertions.assertNotNull(partnerService.getById(1L));
    }
}
