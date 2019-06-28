package com.wcl.web;

import com.wcl.dao.AdminDao;
import com.wcl.domain.Admin;
import com.wcl.service.AdminService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
    public String getAllAdmins(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AdminService adminService = new AdminService();
        try {
            List<Admin> allAdmins = adminService.getAllAdmins();
            request.setAttribute("allAdmins",allAdmins);
            return "/admin/account.jsp";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //添加
    public String add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //获取所有参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        //把参数封装对象
        Admin admin = new Admin();
        try {
            BeanUtils.populate(admin, parameterMap);
            //调用服务层
            AdminService adminService = new AdminService();
            adminService.addAdmin(admin);
            //跳转列表，
            return "/UserServlet?action=getAllAdmins";
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //修改
    public String edit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = (String) request.getSession().getAttribute("id");
        String pwd = request.getParameter("pwd");
        try {
            new AdminDao().updatePwd(pwd,id);
            return "/UserServlet?action=getAllAdmins";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //删除
    public String delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        AdminService adminService = new AdminService();
        try {
            adminService.delAdmin(Integer.parseInt(id));
            return "/UserServlet?action=getAllAdmins";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
