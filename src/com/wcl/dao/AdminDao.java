package com.wcl.dao;

import java.sql.SQLException;
import java.util.List;

import com.wcl.domain.Admin;
import com.wcl.utils.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;


public class AdminDao {
	//到数据库当中查询
	//1.连接
	private QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());

	public Admin checkAdmin(String name, String pwd ,String type) throws SQLException {
		//2.查询
		String sql ="select * from admin where username=? and password=? and type = ?";
		//3.执行查询
		Admin admin = null;
		admin = qr.query(sql, new BeanHandler<Admin>(Admin.class),name,pwd,type);
		//返回查询结果
		return admin;
	}

	public List<Admin> getAllAdmins() throws SQLException {
		String sql ="select * from admin";
		List<Admin> admins = qr.query(sql, new BeanListHandler<Admin>(Admin.class));
		return admins;
	}

	public void addAdmin(Admin admin) throws SQLException {
		String sql = "insert into admin (username,password,type) value (?,?,?)";
		qr.update(sql,admin.getUsername(),admin.getPassword(),admin.getType());
	}

	public void delAdmin(int id) throws SQLException {
		String sql = "delete from admin where id=?";
		qr.update(sql,id);
	}

	public void updateAdmin(Admin admin) throws SQLException {
		String sql = "update admin set username=?,password=?,type=? where id=?";
		qr.update(sql,admin.getUsername(),admin.getPassword(),admin.getType(),admin.getId());
	}

	//修改密码
	public void updatePwd(String pwd,String id) throws SQLException {
		String sql = "update admin set password=? where id=?";
		qr.update(sql,pwd,id);
	}

}
