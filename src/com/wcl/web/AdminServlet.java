package com.wcl.web;

import com.wcl.domain.Admin;
import com.wcl.service.AdminService;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收用户名密码
		String name = request.getParameter("username");
		String pwd = request.getParameter("password");
		String type1 = request.getParameter("type1");
		//调用 登录业务
		AdminService adminService = new AdminService();
		if (type1 == null){
			request.setAttribute("err","请选择用户类型");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else if (type1.equals("user")) {
			//用户
			try {
				String type = "user";
				Admin admin = adminService.login(name, pwd, type);
				//1.把用户保存到session
				HttpSession session = request.getSession();
				session.setAttribute("admin", admin);
				session.setAttribute("userType","访客");
				response.sendRedirect(request.getContextPath() + "/admin/admin_index2.jsp");
			} catch (Exception e) {
				if (e.getMessage().equals("用户名或密码错误")) {
					//跳转回登录页，回显错误信息
					request.setAttribute("err", e.getMessage());
					//转发,服务器内部的转发
					request.getRequestDispatcher("index.jsp").forward(request, response);
				} else {
					e.printStackTrace();
				}
			}
		} else {
			//管理员
			try {
				String type = request.getParameter("type2");
				Admin admin = adminService.login(name, pwd, type);
				HttpSession session = request.getSession();
				session.setAttribute("admin", admin);
				if (type.equals("equit_manager")){
					session.setAttribute("userType","设备管理员");
					response.sendRedirect(request.getContextPath() + "/admin/admin_index.jsp");
				} else if (type.equals("buyer_manager")){
					session.setAttribute("userType","采购员");
					response.sendRedirect(request.getContextPath() + "/admin/admin_index3.jsp");
				} else if (type.equals("analy_manager")){
					session.setAttribute("userType","分析员");
					response.sendRedirect(request.getContextPath() + "/admin/admin_index4.jsp");
				} else if (type.equals("repair_manager")){
					session.setAttribute("userType","维修员");
					response.sendRedirect(request.getContextPath() + "/admin/admin_index5.jsp");
				}else if (type.equals("maintain_manager")){
					session.setAttribute("userType","养护员");
					response.sendRedirect(request.getContextPath() + "/admin/admin_index6.jsp");
				}

			} catch (Exception e) {
				if (e.getMessage().equals("用户名或密码错误")) {
					request.setAttribute("err", e.getMessage());
					request.getRequestDispatcher("index.jsp").forward(request, response);
				} else {
					e.printStackTrace();
				}
			}
		}
	}
}
