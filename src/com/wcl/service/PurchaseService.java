package com.wcl.service;

import com.wcl.dao.PurchaseDao;
import com.wcl.domain.Purchase;

import java.sql.SQLException;
import java.util.List;

public class PurchaseService {
    private PurchaseDao purchaseDao = new PurchaseDao();

    public List<Purchase> getAllPurchases() throws SQLException {
        List<Purchase> allPurchases = purchaseDao.getAllPurchases();
        return allPurchases;
    }

    public Purchase getPurchaseById(String id) throws SQLException {
        Purchase purchase = purchaseDao.getPurchaseByEid(id);
        return purchase;
    }

    public void addPurchase(Purchase purchase) throws Exception {
        purchaseDao.addPurchase(purchase);
    }

    public void delPurchase(String id) throws SQLException {
        purchaseDao.delPurchase(id);
    }

    public void updatePurchase(Purchase purchase) throws Exception {
        purchaseDao.updatePurchase(purchase);
    }

    public void updateState(String state,String id) throws SQLException {
        purchaseDao.updateState(state,id);
    }
}
