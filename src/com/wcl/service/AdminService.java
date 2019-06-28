package com.wcl.service;

import com.wcl.dao.AdminDao;
import com.wcl.domain.Admin;

import java.sql.SQLException;
import java.util.List;


public class AdminService {
	//调用dao层到数据库当中查询
	private AdminDao adminDao = new AdminDao();

	public Admin login(String name, String pwd ,String type) throws Exception {
		Admin admin = adminDao.checkAdmin(name,pwd,type);
		if(admin != null) {
			return admin;
		}else {
			throw new Exception("用户名或密码错误");
		}
	}

	public List<Admin> getAllAdmins() throws SQLException {
		List<Admin> allAdmins = adminDao.getAllAdmins();
		return allAdmins;
	}

	public void addAdmin(Admin admin) throws SQLException {
		adminDao.addAdmin(admin);
	}

	public void delAdmin(int id) throws SQLException {
		adminDao.delAdmin(id);
	}

	public void updateAdmin(Admin admin) throws SQLException {
		adminDao.addAdmin(admin);
	}

}
