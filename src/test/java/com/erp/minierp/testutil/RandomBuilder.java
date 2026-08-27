package com.erp.minierp.testutil;

import com.erp.minierp.datasource.entity.Depot;
import com.erp.minierp.datasource.entity.Material;
import com.erp.minierp.datasource.entity.MaterialCategory;
import com.erp.minierp.datasource.entity.Partner;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

public class RandomBuilder {

    /**
     * 生成纯粹的随机大写字母数字字符串
     */
    private static String randomString(int length) {
        return RandomStringUtils.randomAlphanumeric(length).toUpperCase();
    }

    /**
     * 随机生成一个 Material 实体对象
     */
    public static Material randomMaterial() {
        Material material = new Material();

        // 基础信息与外键 (增加实体的 ID 初始化)
        material.setId(ThreadLocalRandom.current().nextLong(1, 100000L));
        material.setCategoryId(ThreadLocalRandom.current().nextLong(1, 100));
        material.setUnitId(ThreadLocalRandom.current().nextLong(1, 10));
        material.setTenantId(ThreadLocalRandom.current().nextLong(1, 100000L));

        // 字符串字段全部走随机生成
        material.setName("N_" + randomString(8));
        material.setBrand(randomString(6));
        material.setMfrs(randomString(6));
        material.setModel("M-" + randomString(6));
        material.setStandard(randomString(6));
        material.setMnemonic(randomString(5));
        material.setColor(randomString(4));
        material.setPosition("POS-" + randomString(5));
        material.setRemark("REM-" + randomString(15));

        // 数值与开关字段随机化
        material.setWeight(BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(0.1, 50.0)).setScale(4, RoundingMode.HALF_UP));
        material.setExpiryNum(ThreadLocalRandom.current().nextInt(1, 1000));

        material.setEnabled((byte) ThreadLocalRandom.current().nextInt(0, 2));
        material.setEnableSerialNumber((byte) ThreadLocalRandom.current().nextInt(0, 2));
        material.setEnableBatchNumber((byte) ThreadLocalRandom.current().nextInt(0, 2));

        return material;
    }

    /**
     * 支持链式或特定字段覆盖（用于测试边界/异常分支）
     */
    public static Material randomMaterial(Consumer<Material> customizer) {
        Material material = randomMaterial();
        customizer.accept(material);
        return material;
    }

    /**
     * 随机生成一个 Depot 实体对象
     */
    public static Depot randomDepot() {
        Depot depot = new Depot();

        // 基础信息与主外键
        depot.setId(ThreadLocalRandom.current().nextLong(1, 100000L));
        depot.setTenantId(1L); // 默认给个租户 1
        depot.setParentId(0L); // 默认生成顶级仓库
        depot.setPrincipalId(ThreadLocalRandom.current().nextLong(1, 100L));

        // 字符串字段
        depot.setCode("WH-" + randomString(6));
        depot.setName("DEP_" + randomString(8));
        depot.setAddress("ADDR_" + randomString(12));
        depot.setRemark("REM_" + randomString(15));

        // 费率金额字段
        depot.setWarehousing(BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(10.0, 500.0)).setScale(2, RoundingMode.HALF_UP));
        depot.setTruckage(BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(5.0, 200.0)).setScale(2, RoundingMode.HALF_UP));

        // 状态位与整型字段 (Byte 类型的赋值需要显式强转)
        // 仓库类型：1-普通仓，2-冷藏仓，3-虚拟仓，4-中转仓
        depot.setType((byte) ThreadLocalRandom.current().nextInt(1, 5));
        depot.setSort(ThreadLocalRandom.current().nextInt(1, 100));
        depot.setIsDefault((byte) 0); // 默认不是默认仓库，避免冲突
        depot.setEnabled((byte) 1);   // 默认启用
        depot.setDeleteFlag((byte) 0); // 默认未删除

        // 注意：createTime 和 updateTime 建议交由 MyBatis-Plus 的自动填充机制(@TableField(fill = ...)) 或数据库自身(ON UPDATE)处理，
        // 测试数据构造中通常不需要显式 set，除非你想刻意测试时间范围相关的逻辑。

        return depot;
    }

    /**
     * 支持链式或特定字段定制（用于测试边界/异常分支）
     */
    public static Depot randomDepot(Consumer<Depot> customizer) {
        Depot depot = randomDepot();
        customizer.accept(depot);
        return depot;
    }

    /**
     * 随机生成一个 MaterialCategory 实体对象。
     */
    public static MaterialCategory randomMaterialCategory() {
        MaterialCategory materialCategory = new MaterialCategory();
        materialCategory.setTenantId(1L);
        materialCategory.setParentId(0L);
        materialCategory.setName("CAT_" + randomString(8));
        materialCategory.setSerialNo("CAT-" + randomString(8));
        materialCategory.setSort(ThreadLocalRandom.current().nextInt(0, 100));
        materialCategory.setRemark("REM_" + randomString(15));
        materialCategory.setDeleteFlag((byte) 0);
        return materialCategory;
    }

    public static MaterialCategory randomMaterialCategory(Consumer<MaterialCategory> customizer) {
        MaterialCategory materialCategory = randomMaterialCategory();
        customizer.accept(materialCategory);
        return materialCategory;
    }

    public static Partner randomPartner() {
        Partner partner = new Partner();
        partner.setTenantId(1L);
        partner.setName("PARTNER_" + randomString(8));
        partner.setType((byte) ThreadLocalRandom.current().nextInt(1, 4));
        partner.setContactPerson("CONTACT_" + randomString(6));
        partner.setContactPhone("138" + ThreadLocalRandom.current().nextLong(10000000L, 99999999L));
        partner.setEmail(randomString(8).toLowerCase() + "@example.com");
        partner.setAddress("ADDR_" + randomString(12));
        partner.setTaxNum("TAX_" + randomString(12));
        partner.setBankName("BANK_" + randomString(8));
        partner.setAccountNumber("ACCOUNT_" + randomString(12));
        partner.setTaxRate(BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(0, 100)).setScale(2, RoundingMode.HALF_UP));
        partner.setAdvanceIn(BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(0, 10000)).setScale(4, RoundingMode.HALF_UP));
        partner.setBeginNeedGet(BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(0, 10000)).setScale(4, RoundingMode.HALF_UP));
        partner.setBeginNeedPay(BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(0, 10000)).setScale(4, RoundingMode.HALF_UP));
        partner.setSort(ThreadLocalRandom.current().nextInt(0, 100));
        partner.setEnabled(true);
        partner.setIsSystem(false);
        partner.setRemark("REM_" + randomString(15));
        return partner;
    }

    public static Partner randomPartner(Consumer<Partner> customizer) {
        Partner partner = randomPartner();
        customizer.accept(partner);
        return partner;
    }
}
