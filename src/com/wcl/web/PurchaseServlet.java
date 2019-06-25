package com.wcl.web;

import com.wcl.domain.Admin;
import com.wcl.domain.Loan;
import com.wcl.domain.Purchase;
import com.wcl.service.LoanService;
import com.wcl.service.PurchaseService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@WebServlet("/PurchaseServlet")
public class PurchaseServlet extends BaseServlet {
    //添加
    public String add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //获取所有参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        //把参数封装对象
        Purchase purchase = new Purchase();
        try {
            BeanUtils.populate(purchase, parameterMap);
            //调用服务层
            PurchaseService purchaseService = new PurchaseService();
            purchaseService.addPurchase(purchase);
            //跳转列表
            return "/PurchaseServlet?action=getListPurchases";
        }  catch (Exception e) {
                e.printStackTrace();
        }
        return null;
    }

    // 获取所有
    public String getListPurchases(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1.调用服务层
        PurchaseService purchaseService = new PurchaseService();
        try {
            List<Purchase> allPurchases = purchaseService.getAllPurchases();
            // 对集合进行反转
//            Collections.reverse(allPurchases);
            // 把数据写到request域
            request.setAttribute("allPurchases", allPurchases);
            // 转发
            Admin admin = (Admin) request.getSession().getAttribute("admin");
            if (admin.getType().equals("buyer_manager")){
                return "admin/buyer_message.jsp";
            }else if (admin.getType().equals("equit_manager")){
                return "admin/purchase_manage.jsp";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 编辑  UI
    public String editUI(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取当前 的id
        String id = request.getParameter("id");
        try {
            // 1.获取当前
            PurchaseService purchaseService = new PurchaseService();
            Purchase purchase = purchaseService.getPurchaseById(id);
            // 写到域
            request.setAttribute("purchase", purchase);
            // 转发到edit.jsp
            return "/admin/buyer_apply_edit.jsp";

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //编辑
    public String edit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1.获取所有的参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        Purchase purchase = new Purchase();
        // 2.封装成  对象
        try {
            BeanUtils.populate(purchase, parameterMap);
            System.out.println(purchase);
            PurchaseService purchaseService = new PurchaseService();
            purchaseService.updatePurchase(purchase);
            return "/PurchaseServlet?action=getListPurchases";
        } catch (Exception e) {
                e.printStackTrace();
        }
        return null;
    }

    // 删除
    public String delPurchase(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1.接收参数 id
        String id = request.getParameter("id");
        // 2.调用服务层，让其删除
        PurchaseService purchaseService = new PurchaseService();
        try {
            purchaseService.delPurchase(id);
            return "/PurchaseServlet?action=getListPurchases";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //管理员同意申请
    public String agree(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String id = request.getParameter("id");
            PurchaseService purchaseService = new PurchaseService();
            purchaseService.updateState("审核通过",id);
            return "/PurchaseServlet?action=getListPurchases";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //管理员驳回申请
    public String disAgree(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String id = request.getParameter("id");
            PurchaseService purchaseService = new PurchaseService();
            purchaseService.updateState("审核未通过",id);
            return "/PurchaseServlet?action=getListPurchases";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
