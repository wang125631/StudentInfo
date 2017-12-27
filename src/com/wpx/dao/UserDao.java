package com.wpx.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.wpx.model.User;

public class UserDao {

	public User login(Connection con,User user)throws Exception{
		User resultUser=null;
		String sql="select * from t_user where userName=? and password=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassword());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			resultUser=new User();
			resultUser.setUserId(rs.getInt("userId"));
			resultUser.setUserName(rs.getString("userName"));
			resultUser.setPassword(rs.getString("password"));
		}
		return resultUser;
	}
	
	public int modifyPassword(Connection con,User user)throws Exception{
		String sql="update t_user set password=? where userId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, user.getPassword());
		pstmt.setInt(2, user.getUserId());
		return pstmt.executeUpdate();
	}

	public User getByName(Connection con,String username) throws Exception {
		String sql="select * from t_user where username=?" ;
		PreparedStatement pstmt=con.prepareStatement(sql);
		System.out.println(username);
		pstmt.setString(1, username);
		ResultSet rs=pstmt.executeQuery();
		User resultUser=null;
		while(rs.next()){
			resultUser=new User();
			resultUser.setUserId(rs.getInt("userId"));
			resultUser.setUserName(rs.getString("userName"));
			resultUser.setPassword(rs.getString("password"));
		}
		
		return resultUser;
	}
	
	
	
}