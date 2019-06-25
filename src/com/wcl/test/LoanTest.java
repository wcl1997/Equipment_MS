package com.wcl.test;

import com.wcl.dao.AdminDao;
import com.wcl.dao.EquipmentDao;
import com.wcl.dao.LoanDao;
import com.wcl.domain.Admin;
import com.wcl.domain.Loan;
import com.wcl.domain.Page;
import com.wcl.domain.Purchase;
import com.wcl.service.EquipmentService;
import com.wcl.service.LoanService;
import com.wcl.service.PurchaseService;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class LoanTest {
    @Test
    public void test() throws Exception {
        EquipmentService equipmentService = new EquipmentService();
        Page page = equipmentService.getDataById("1");
        System.out.println(page.getEquipmentList());
    }

    private LoanDao loanDao = new LoanDao();
    @Test
    public void getAllLoans() throws SQLException {
        List<Loan> allLoans = loanDao.getAllLoans();
        System.out.println(allLoans);
    }

    @Test
    public void getLoanByEid() throws SQLException {
        Loan loan = loanDao.getLoanByEid("10010");
        System.out.println(loan);
    }

    @Test
    public void addLoan() throws Exception {
        Loan loan = new Loan();
        loan.setLoan_eid("10011");
        loan.setLoan_use("商用");
        loan.setLoan_tel(15226038452L);
        loanDao.addLoan(loan);
    }

    @Test
    public void delLoan() throws SQLException {
        loanDao.delLoan("3");
    }

    @Test
    public void updateLoan() throws Exception {
        Loan loan = new Loan();
        loan.setId(2);
        loan.setLoan_eid("10011");
        loan.setLoan_use("学习");
        loan.setLoan_time("8天");
        loan.setLoan_tel(15226038452L);
        loanDao.updateLoan(loan);
    }

    @Test
    public void test2() throws Exception {
        LoanService loanService = new LoanService();
        Loan loan = new Loan();
        loan.setLoan_eid("10011");
        loan.setLoan_use("学习");
        loan.setLoan_time("8天");
        loan.setLoan_tel(15226038452L);
        loanService.addLoan(loan);
    }

    @Test
    public void test3() throws Exception {
        PurchaseService purchaseService = new PurchaseService();
        Purchase purchase = new Purchase();
        purchase.setId(1);
        purchase.setP_ename("666");
        purchaseService.updatePurchase(purchase);
    }

    @Test
    public void test4() throws Exception {
        PurchaseService purchaseService = new PurchaseService();

        purchaseService.updateState("ddd","1");
    }
}
