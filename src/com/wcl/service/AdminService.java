package com.wcl.service;

import com.wcl.dao.AdminDao;
import com.wcl.domain.Admin;

import java.sql.SQLException;


public class AdminService {

	public Admin login(String name, String pwd ,String type) throws Exception {
		//调用dao层到数据库当中查询
		AdminDao adminDao = new AdminDao();
		Admin admin = adminDao.checkAdmin(name,pwd,type);
		if(admin != null) {
			return admin;
		}else {
			throw new Exception("用户名或密码错误");
		}
	}

}
