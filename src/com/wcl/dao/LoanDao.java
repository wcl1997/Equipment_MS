package com.wcl.dao;

import com.wcl.domain.Equipment;
import com.wcl.domain.Loan;
import com.wcl.utils.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class LoanDao {
    private QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
    //查找所有
    public List<Loan> getAllLoans() throws SQLException {
        String sql = "select * from loan";
        List<Loan> loans = qr.query(sql, new BeanListHandler<Loan>(Loan.class));
        return loans;
    }

    //根据状态查找
    public List<Loan> getLoansByState() throws SQLException {
        String sql = "select * from loan where loan_state = '待审核'";
        List<Loan> loans = qr.query(sql, new BeanListHandler<Loan>(Loan.class));
        return loans;
    }

    //根据用户查找
    public List<Loan> getLoansByUser(String user) throws SQLException {
        String sql = "select * from loan where loan_user = ?";
        List<Loan> loans = qr.query(sql, new BeanListHandler<Loan>(Loan.class),user);
        return loans;
    }

    //根据设备编号查询
    public Loan getLoanByEid(String loan_eid) throws SQLException {
        String sql = "select * from loan where loan_eid = ?";
        Loan loan = qr.query(sql, new BeanHandler<Loan>(Loan.class),loan_eid);
        return loan;
    }

    //根据id查询
    public Loan getLoanById(String id) throws SQLException {
        String sql = "select * from loan where id = ?";
        Loan loan = qr.query(sql, new BeanHandler<Loan>(Loan.class),id);
        return loan;
    }

    //增加
    public void addLoan(Loan loan) throws Exception {
        String sql = "insert into loan(loan_eid,loan_ename,loan_use,loan_time,loan_tel,loan_user) value (?,?,?,?,?,?)";
        Equipment equipment = new EquipmentDao().getEquipmentWithEid(loan.getLoan_eid());
        qr.update(sql,loan.getLoan_eid(),equipment.getEquip_name(),loan.getLoan_use(),loan.getLoan_time(),loan.getLoan_tel(),loan.getLoan_user());
    }

    //删除
    public void delLoan(String id) throws SQLException {
        String sql = "delete from loan where id=?";
        qr.update(sql,id);
    }

    //修改
    public void updateLoan(Loan loan) throws Exception {
        String sql = "update loan set loan_eid=?,loan_ename=?,loan_use=?,loan_time=?,loan_tel=?,loan_state=?,loan_user=? where id=?";
        Equipment equipment = new EquipmentDao().getEquipmentWithEid(loan.getLoan_eid());
        qr.update(sql,loan.getLoan_eid(),equipment.getEquip_name(),loan.getLoan_use(),loan.getLoan_time(),loan.getLoan_tel(),"待审核",loan.getLoan_user(),loan.getId());
    }

    //更改状态
    public void updateState(String state,String id) throws SQLException {
        String sql = "update loan set loan_state = ? where id = ?";
        qr.update(sql,state,id);
    }

}
