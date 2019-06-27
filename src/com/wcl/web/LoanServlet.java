package com.wcl.web;

import com.wcl.domain.Admin;
import com.wcl.domain.Equipment;
import com.wcl.domain.Loan;
import com.wcl.service.EquipmentService;
import com.wcl.service.LoanService;
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

@WebServlet("/LoanServlet")
public class LoanServlet extends BaseServlet {
    //添加
    public String add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //获取所有参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        System.out.println(parameterMap);
        //把参数封装对象
        Loan loan = new Loan();
        try {
            BeanUtils.populate(loan, parameterMap);
            //调用服务层
            LoanService loanService = new LoanService();
            //添加用户名
            Admin admin = (Admin) request.getSession().getAttribute("admin");
            loan.setLoan_user(admin.getUsername());
            loanService.addLoan(loan);
            //跳转列表
            return "/LoanServlet?action=getListLoans2";
        }  catch (Exception e) {
            if (e.getMessage().equals("该设备已被申请")) {
                request.setAttribute("err", e.getMessage());
                request.getRequestDispatcher("/admin/loan.jsp").forward(request, response);
            }else if (e.getMessage().equals("该设备不存在！")){
                request.setAttribute("err", e.getMessage());
                request.getRequestDispatcher("/admin/loan.jsp").forward(request, response);
            } else if (e.getMessage().equals("设备编号不可为空！")){
                request.setAttribute("err", e.getMessage());
                request.getRequestDispatcher("/admin/loan.jsp").forward(request, response);
            } else {
                e.printStackTrace();
            }
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
            LoanService loanService = new LoanService();
            Loan loan = loanService.getLoanById(id);
            // 写到域
            request.setAttribute("loan", loan);
            // 转发到edit.jsp
            return "/admin/user_apply_edit.jsp";

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
        Loan loan = new Loan();
        // 2.封装成  对象
        try {
            BeanUtils.populate(loan, parameterMap);
            LoanService loanService = new LoanService();
            //添加用户名
            Admin admin = (Admin) request.getSession().getAttribute("admin");
            loan.setLoan_user(admin.getUsername());
            loanService.updateLoan(loan);
            return "/LoanServlet?action=getListLoans2";
        } catch (Exception e) {
            if (e.getMessage().equals("该设备已被申请,无法申请！")) {
                request.setAttribute("err", e.getMessage());
                request.getRequestDispatcher("/LoanServlet?action=editUI").forward(request, response);
            } else {
                e.printStackTrace();
            }
        }
        return null;
    }

    // 删除
    public String delLoan(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1.接收参数 id
        String id = request.getParameter("id");
        // 2.调用服务层，让其删除
        LoanService loanService = new LoanService();
        try {
            loanService.delLoan(id);
            return "/LoanServlet?action=getListLoans2";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 获取所有  管理员
    public String getListLoans(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1.调用服务层
        LoanService loanService = new LoanService();
        try {
            List<Loan> allLoans = loanService.getLoansByState();
            // 对集合进行反转
//            Collections.reverse(allLoans);
            // 把数据写到request域
            request.setAttribute("allLoans", allLoans);
            // 转发
            /*Admin admin = (Admin) request.getSession().getAttribute("admin");
            if (admin.getType().equals("user")){
                return "admin/user_message.jsp";
            }else if (admin.getType().equals("equit_manager")){
                return "admin/loan_manage.jsp";
            }*/
            return "admin/loan_manage.jsp";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 获取所有  访客
    public String getListLoans2(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1.调用服务层
        LoanService loanService = new LoanService();
       /* Admin admin = (Admin) request.getSession().getAttribute("admin");
        System.out.println("admin==="+admin);*/
        try {
//            List<Loan> allLoans = loanService.getAllLoans();

            //获取用户
            Admin admin = (Admin) request.getSession().getAttribute("admin");
            List<Loan> allLoans = loanService.getLoansByUser(admin.getUsername());
            // 对集合进行反转
//            Collections.reverse(allLoans);
            // 把数据写到request域
            request.setAttribute("allLoans", allLoans);
            // 转发
            return "admin/user_message.jsp";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //管理员同意申请
    public String agree(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String id = request.getParameter("id");
            LoanService loanService = new LoanService();
            loanService.updateState("审核通过",id);
            return "/LoanServlet?action=getListLoans";
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
            LoanService loanService = new LoanService();
            loanService.updateState("审核未通过",id);
            return "/LoanServlet?action=getListLoans";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
