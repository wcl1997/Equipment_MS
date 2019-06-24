package com.wcl.dao;

import com.wcl.domain.Equipment;
import com.wcl.utils.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class EquipmentDao {
    private QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
    //1.从数据库当中查询所有设备列表
    public List<Equipment> getAllEquipments() throws SQLException {

        //1.查询操作
        String sql = "select * from equipment";
        //2执行sql
        List<Equipment> equipments= null;
        equipments = qr.query(sql, new BeanListHandler<Equipment>(Equipment.class));
        return equipments;
    }
    //2.添加设备到数据库当中
    public void addEquipment(Equipment equipment) throws SQLException {
        //插入操作
        String sql = "insert into equipment(equip_id,equip_name,equip_state,equip_manufacturer) value (?,?,?,?)";
        qr.update(sql,equipment.getEquip_id(),equipment.getEquip_name(),equipment.getEquip_state(),equipment.getEquip_manufacturer());
    }

    //3.根据id从数据库当中删除一个
    public void delEquipment(int id) throws SQLException {
        //删除操作
        String sql = "delete from equipment where id=?";
        qr.update(sql,id);
    }

    //4.根据id更新
    public void updateEquipment(Equipment equipment) throws SQLException {
        //更新操作
        String sql = "update equipment set equip_id=?,equip_name=?,equip_state=?,equip_manufacturer=? where id=?";
        qr.update(sql,equipment.getEquip_id(),equipment.getEquip_name(),equipment.getEquip_state(),equipment.getEquip_manufacturer(),equipment.getId());
    }
    //根据id查询一个
    public Equipment getEquipmentWithId(String id) throws Exception {

        String sql = "select * from equipment where id=?";
        Equipment equipment = qr.query(sql, new BeanHandler<Equipment>(Equipment.class),Integer.parseInt(id));
        return equipment;

    }
    //根据编号查询一个
    public Equipment getEquipmentWithEid(String id) throws Exception {

        String sql = "select * from equipment where equip_id=?";
        Equipment equipment = qr.query(sql, new BeanHandler<Equipment>(Equipment.class),Integer.parseInt(id));
        return equipment;

    }

    public Long getCount() throws SQLException {
        String sql = "select count(*) from equipment";
        Long count = (Long) qr.query(sql, new ScalarHandler());
        return count;
    }

    public List<Equipment> getPageData(Integer index, Integer pageCount) throws SQLException {
        String sql = "select * from equipment limit ?,?";
        List<Equipment> equipments = qr.query(sql,new BeanListHandler<Equipment>(Equipment.class),index,pageCount);

        return equipments;
    }
}
