package com.wcl.service;

import com.wcl.dao.EquipmentDao;
import com.wcl.dao.LoanDao;
import com.wcl.domain.Equipment;
import com.wcl.domain.Loan;

import java.sql.SQLException;
import java.util.List;

public class LoanService {
    private LoanDao loanDao = new LoanDao();

    public List<Loan> getAllLoans() throws SQLException {
        List<Loan> allLoans = loanDao.getAllLoans();
        return allLoans;
    }

    //根据状态查找
    public List<Loan> getLoansByState() throws SQLException {
        List<Loan> allLoans = loanDao.getLoansByState();
        return allLoans;
    }

    //根据用户查找
    public List<Loan> getLoansByUser(String user) throws SQLException {
        List<Loan> allLoans = loanDao.getLoansByUser(user);
        return allLoans;
    }

    public Loan getLoanByEid(String loan_eid) throws SQLException {
        Loan loan = loanDao.getLoanByEid(loan_eid);
        return loan;
    }

    public Loan getLoanById(String id) throws SQLException {
        Loan loan = loanDao.getLoanById(id);
        return loan;
    }

    public void addLoan(Loan loan) throws Exception {
        if (loan.getLoan_eid() == ""){
            throw new Exception("设备编号不可为空！");
        }else {
            Loan loanByEid = loanDao.getLoanByEid(loan.getLoan_eid());
            Equipment equipment = new EquipmentDao().getEquipmentWithEid(loan.getLoan_eid());
            if (equipment != null) {
                if (loanByEid == null) {
                    loanDao.addLoan(loan);
                } else {
//            System.out.println(loanByEid.getLoan_eid());
                    throw new Exception("该设备已被申请");
                }
            }else {
                throw new Exception("该设备不存在！");
            }
        }
    }

    public void updateLoan(Loan loan) throws Exception {
        Integer id = loan.getId();
        Loan loanById = loanDao.getLoanById(id.toString());
        Loan loanByEid = loanDao.getLoanByEid(loan.getLoan_eid());
        if (loanByEid == null){
            loanDao.updateLoan(loan);
        }else if (loanById.getLoan_eid().equals(loanByEid.getLoan_eid())){
            loanDao.updateLoan(loan);
        } else {
            throw  new Exception("该设备已被申请,无法申请！");
        }
    }

    public void delLoan(String id) throws SQLException {
        loanDao.delLoan(id);
    }

    //改状态
    public void updateState(String state,String id) throws SQLException {
        loanDao.updateState(state,id);
    }
}
