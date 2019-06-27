package com.wcl.service;

import com.wcl.dao.EquipmentDao;
import com.wcl.domain.Equipment;
import com.wcl.domain.Page;

import java.sql.SQLException;
import java.util.List;

public class EquipmentService {
    private EquipmentDao equipmentDao = new EquipmentDao();
    public List<Equipment> getAllEquipments() throws SQLException {
        //从数据库当中获取所有的数据
        List<Equipment> allEquipments = equipmentDao.getAllEquipments();
        return allEquipments;
    }

    public void deleteEquipment(String id) throws Exception {
        //调用dao让其删除
        equipmentDao.delEquipment(Integer.parseInt(id));
    }

    public void addEquipment(Equipment equipment) throws Exception {
        //调用dao 插入操作
        if (equipment.getEquip_id() == ""){
            throw new Exception("设备编号不可为空！");
        }else {
            Equipment equipmentWithEid = new EquipmentDao().getEquipmentWithEid(equipment.getEquip_id());
            if (equipmentWithEid != null){
                throw new Exception("该设备已存在!");
            } else {
                equipmentDao.addEquipment(equipment);
            }
        }
    }

    public Equipment getEquipmentWithId(String id) throws Exception {
        //调用dao 查询一个 根据id操作
        Equipment equipment = equipmentDao.getEquipmentWithId(id);
        return equipment;
    }

    public void updateEquipment(Equipment equipment) throws Exception {
        //调用dao 更新
        Integer id = equipment.getId();
        Equipment equipmentWithId = equipmentDao.getEquipmentWithId(id.toString());
        Equipment equipmentWithEid = equipmentDao.getEquipmentWithEid(equipment.getEquip_id());
        if (equipmentWithEid == null){
            equipmentDao.updateEquipment(equipment);
        }else if (equipmentWithEid.getEquip_id().equals(equipmentWithId.getEquip_id())){
            equipmentDao.updateEquipment(equipment);
        }else {
            throw new Exception("该设备已存在，无法修改！");
        }
    }


    public Page getPage(int currentPage) throws SQLException {
        Page page = new Page();
        page.setCurrentPage(currentPage);
        Long count = equipmentDao.getCount();
        page.setTotalCount(count.intValue());

        Integer pageCount = 5;
        //总页数
        double totalPage = Math.ceil(1.0 * page.getTotalCount() / pageCount);
        page.setTotalPage((int) totalPage);

        //查询角标
        Integer index = (page.getCurrentPage() - 1) * pageCount;

        page.setEquipmentList(equipmentDao.getPageData(index,pageCount));
        return page;
    }

    //通过编号
    public Page getDataById(String id) throws Exception {
        Equipment equipment = equipmentDao.getEquipmentWithEid(id);
        Page page = new Page();
        page.getEquipmentList().add(equipment);
        return page;
    }
}
