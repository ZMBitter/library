package com.zm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.zm.bean.User;
import com.zm.dao.UserDAO;
import com.zm.util.JDBCUtil;

public class UserDAOImpl implements UserDAO{

	@Override
	public User loginUser(String username, String password) {
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		User user = null;
		try {
			conn = JDBCUtil.getConn();
			String sql = "select * from user where username=? and password=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			rs = pst.executeQuery();
			while(rs.next()){
				user = new User();
				user.setUserId(rs.getInt("userId"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null){
					rs.close();
					}
					if(pst!=null){
					pst.close();
					}
					
					if(conn!=null){
						conn.close();
					}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return user;
	}

	//根据用户名查询用户信息
	@Override
	public List<User> getUserByName(String username) {
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		User user = null;
		List<User> list = null;
		try {
			conn = JDBCUtil.getConn();
			String sql = "select * from user where username=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			rs = pst.executeQuery();
			
			list = new ArrayList<User>();
			
			while(rs.next()){
				user = new User();
				user.setUserId(rs.getInt("userId"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null){
					rs.close();
					}
					if(pst!=null){
					pst.close();
					}
					
					if(conn!=null){
						conn.close();
					}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return list;
	}

	/*向用户表插入数据*/
	@Override
	public boolean addUser(User user) {
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		boolean flag = false;
		try {
			conn = JDBCUtil.getConn();
			String sql = "insert into user(userId,username,password,email) values(null,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getEmail());
			int add = pst.executeUpdate();
			if(add>0){
				flag=true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null){
					rs.close();
					}
					if(pst!=null){
					pst.close();
					}
					
					if(conn!=null){
						conn.close();
					}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return flag;
	}
}
