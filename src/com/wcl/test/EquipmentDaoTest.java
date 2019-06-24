package com.wcl.test;

import java.sql.SQLException;
import java.util.List;
import com.wcl.dao.EquipmentDao;
import com.wcl.domain.Equipment;
import org.junit.Test;


public class EquipmentDaoTest {
	private EquipmentDao equipmentDao = new EquipmentDao();
	@Test
	public void testGetAll() throws SQLException {

		List<Equipment> allEquipments = equipmentDao.getAllEquipments();
		System.out.println(allEquipments);
	}

	@Test
	public void testAdd() throws SQLException {
		Equipment equipment = new Equipment();
		equipment.setEquip_id("666666");
		equipment.setEquip_name("设备1");
		equipment.setEquip_state("ok");
		equipment.setEquip_manufacturer("郑州");
		equipmentDao.addEquipment(equipment);
	}

	@Test
	public void testDel() throws SQLException {

		equipmentDao.delEquipment(9);
	}


	@Test
	public void testUpdate() throws SQLException {
		Equipment equipment = new Equipment();
		equipment.setId(10);
		equipment.setEquip_id("55555");
		equipment.setEquip_name("设备2");
		equipmentDao.updateEquipment(equipment);
	}

	@Test
	public void testPage() throws SQLException {
		Long count = equipmentDao.getCount();
		System.out.println(count);
		/*Long count = goodsDao.getCount();
		System.out.println(count);
		List<Goods> pageData = goodsDao.getPageData(3, 5);
		System.out.println(pageData);*/
		/*GoodsService goodsService = new GoodsService();
		Page page = goodsService.getPage(2);
		System.out.println(page.getGoodsList());*/
	}
}
