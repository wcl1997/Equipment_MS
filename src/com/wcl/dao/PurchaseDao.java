package com.wcl.dao;

import com.wcl.domain.Equipment;
import com.wcl.domain.Loan;
import com.wcl.domain.Purchase;
import com.wcl.utils.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class PurchaseDao {
    private QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
    //查找所有
    public List<Purchase> getAllPurchases() throws SQLException {
        String sql = "select * from purchase";
        List<Purchase> purchases = qr.query(sql, new BeanListHandler<Purchase>(Purchase.class));
        return purchases;
    }

    //根据id查询
    public Purchase getPurchaseByEid(String id) throws SQLException {
        String sql = "select * from purchase where id = ?";
        Purchase purchase = qr.query(sql, new BeanHandler<Purchase>(Purchase.class),id);
        return purchase;
    }

    //增加
    public void addPurchase(Purchase purchase) throws Exception {
        String sql = "insert into purchase(p_ename,p_price,p_manufacture,p_amount) value (?,?,?,?)";
        qr.update(sql,purchase.getP_ename(),purchase.getP_price(),purchase.getP_manufacture(),purchase.getP_amount());
    }

    //删除
    public void delPurchase(String id) throws SQLException {
        String sql = "delete from purchase where id=?";
        qr.update(sql,id);
    }

    //修改
    public void updatePurchase(Purchase purchase) throws Exception {
        String sql = "update purchase set p_ename=?,p_price=?,p_manufacture=?,p_amount=? where id=?";
        qr.update(sql,purchase.getP_ename(),purchase.getP_price(),purchase.getP_manufacture(),purchase.getP_amount(),purchase.getId());
    }

    //修改状态
    public void updateState(String state,String id) throws SQLException {
        String sql = "update purchase set p_state = ? where id = ?";
        qr.update(sql,state,id);
    }

}
