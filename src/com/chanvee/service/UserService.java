package com.chanvee.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.chanvee.domain.User;
import com.chanvee.utils.SqlHelper;
import com.sun.crypto.provider.RSACipher;

public class UserService {
	// 验证用户是否合法
	public boolean checkUser(User user){
		boolean result = false;
		
		// 使用SqlHelper完成查询任务
		String sql = "select * from users where username=? and password=?";
		String parameters[] = {user.getName(),user.getPwd()};
		ResultSet rs = SqlHelper.executeQuery(sql, parameters);
		try {
			if (rs.next()){
				result = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());
		}
		return result;
	}
	// 按照分页获取用户
	public  ArrayList getUsersByPage(int pageNow, int pageSize){
		ArrayList<User> al = new ArrayList<User>();
		
		// 查询sql
		String sql="select * from users limit " +((pageNow-1)*pageSize)+ "," + pageSize;
		ResultSet rs = SqlHelper.executeQuery(sql, null);
		
		// 二次封装
		try {
			while(rs.next()){
				User user = new User();
				user.setName(rs.getString(1));
				user.setPwd(rs.getString(2));
				
				al.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());
		}
		return al;
	}
	
	// 获取pageCnt
	public int getPageCnt(int pageSize){
		String sql = "select count(*) from users";
		ResultSet rs = SqlHelper.executeQuery(sql, null);
		int rowCnt = 0;
		try {
			rs.next();
			rowCnt = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());
		}
		
		return ((rowCnt-1)/pageSize + 1);
	}
	
	// 删除用户
	public boolean delUser (String username){
		boolean result = true;
		String sql = "delete from users where username=?";
		String parameters[] = {username};

		try {
			SqlHelper.executeUpdate(sql, parameters);
		} catch (Exception e) {
			// TODO: handle exception
			result = false;
		}
		
		return result;
	}
	// 通过 username 获取用户数据
	public User getUserByName(String username){
		User user = new User();
		String sql = "select * from users where username=?";
		String parameters[] = {username};
		ResultSet rs = SqlHelper.executeQuery(sql, parameters); 
		try {
			if (rs.next()){
				user.setName(rs.getString(1));
				user.setPwd(rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());
		}
		return user;
	}
	
	// 修改用户
	public boolean updateUser(User user, String username){
		boolean result = true;
		String sql = "update users set username=?, password=? where username=?";
		
		try {
			if (username != null){
				String parameters[] = {user.getName(), user.getPwd(), username};
				SqlHelper.executeUpdate(sql, parameters);
			}
			else{
				String parameters[] = {user.getName(), user.getPwd(), user.getName()};
				SqlHelper.executeUpdate(sql, parameters);
			}
			//SqlHelper.executeUpdate(sql, parameters);
		} catch (Exception e) {
			// TODO: handle exception
			result = false;
		}
		return result;
	}
	
	// 添加用户
	public boolean addUser(User user){
		boolean result = true;
		String sql = "insert into users values(?,?)";
		String parameters[] = {user.getName(),user.getPwd()};
		try {
			SqlHelper.executeUpdate(sql, parameters);
		} catch (Exception e) {
			// TODO: handle exception
			result = false;
		}
		
		return result;
	}

}
