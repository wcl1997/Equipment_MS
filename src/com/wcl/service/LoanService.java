package com.wcl.service;

import com.wcl.dao.LoanDao;
import com.wcl.domain.Loan;

import java.sql.SQLException;
import java.util.List;

public class LoanService {
    private LoanDao loanDao = new LoanDao();

    public List<Loan> getAllLoans() throws SQLException {
        List<Loan> allLoans = loanDao.getAllLoans();
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
        Loan loanByEid = loanDao.getLoanByEid(loan.getLoan_eid());
        if (loanByEid == null){
            loanDao.addLoan(loan);
        } else {
//            System.out.println(loanByEid.getLoan_eid());
            throw  new Exception("该设备已被申请");
        }
    }

    public void updateLoan(Loan loan) throws Exception {
        Integer id = loan.getId();
        Loan loanById = loanDao.getLoanById(id.toString());
        Loan loanByEid = loanDao.getLoanByEid(loan.getLoan_eid());
        if (loanById.getLoan_eid().equals(loanByEid.getLoan_eid())){
            loanDao.updateLoan(loan);
        }else if (loanByEid == null){
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
