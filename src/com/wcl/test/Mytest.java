package com.wcl.test;

import com.wcl.dao.AdminDao;
import com.wcl.dao.EquipmentDao;
import com.wcl.domain.Admin;
import com.wcl.domain.Page;
import com.wcl.service.EquipmentService;
import org.junit.Test;

import java.sql.SQLException;

public class Mytest {
    @Test
    public void test() throws Exception {
        EquipmentService equipmentService = new EquipmentService();
        Page page = equipmentService.getDataById("1");
        System.out.println(page.getEquipmentList());
    }
}
