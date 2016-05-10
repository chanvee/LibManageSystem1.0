package com.chanvee.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.chanvee.domain.User;
import com.chanvee.utils.SqlHelper;
import com.sun.crypto.provider.RSACipher;

public class UserService {
	// ��֤�û��Ƿ�Ϸ�
	public boolean checkUser(User user){
		boolean result = false;
		
		// ʹ��SqlHelper��ɲ�ѯ����
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
	// ���շ�ҳ��ȡ�û�
	public  ArrayList getUsersByPage(int pageNow, int pageSize){
		ArrayList<User> al = new ArrayList<User>();
		
		// ��ѯsql
		String sql="select * from users limit " +((pageNow-1)*pageSize)+ "," + pageSize;
		ResultSet rs = SqlHelper.executeQuery(sql, null);
		
		// ���η�װ
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
	
	// ��ȡpageCnt
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
	
	// ɾ���û�
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
	// ͨ�� username ��ȡ�û�����
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
	
	// �޸��û�
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
	
	// ����û�
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
