package com.wcl.test;

import com.wcl.dao.EquipmentDao;
import com.wcl.domain.Equipment;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class MyTest {
    @Test
    public void test() throws SQLException {
        EquipmentDao equipmentDao = new EquipmentDao();
        List<Equipment> equipmentsByRepair = equipmentDao.getEquipmentsByRepair();
        System.out.println(equipmentsByRepair);
    }
}
