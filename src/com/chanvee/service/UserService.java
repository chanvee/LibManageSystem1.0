package com.chanvee.service;

import java.sql.ResultSet;

import com.chanvee.domain.User;
import com.chanvee.utils.SqlHelper;

public class UserService {
	// ��֤�û��Ƿ�Ϸ�
	public boolean checkUser(User user){
		boolean result = false;
		
		// ʹ��SqlHelper��ɲ�ѯ����
		String sql = "select * from users where username=? and password=?";
		String parameters[] = {user.getName()+"",user.getPwd()};
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

}
