package com.chanvee.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chanvee.domain.User;
import com.chanvee.service.UserService;

/**
 * Servlet implementation class LoginCtrlServlet
 */
@WebServlet("/LoginCtrlServlet")
public class LoginCtrlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginCtrlServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//out.println(username + "  " + password);
		
		/*
		if ("chanvee".equals(username) && "123".equals(password)){
			// ����������
			response.sendRedirect("/LibManageSystem1.0/MainServlet");
		}
		else{
			//����
			response.sendRedirect("/LibManageSystem1.0/LoginServlet");
		}*/
		
		//���ݿ���֤1
		/*
		Connection ct = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//1.��������
			Class.forName("com.mysql.jdbc.Driver");
			//2.�õ�����
			ct = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");		
			//3.����PreparedStatement
			ps = ct.prepareStatement("select * from users where username=? and password=?");
			ps.setObject(1, username);
			ps.setObject(2, password);
			//4.ִ�в���
			rs = ps.executeQuery();
			//5.���ݽ������
			if (rs.next()){
				request.getRequestDispatcher("/MainServlet").forward(request, response);
			}
			else{
				request.setAttribute("err", "�û������������");
				request.getRequestDispatcher("/LoginServlet").forward(request, response);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			if (rs != null){
				try {
					rs.close();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();;
				}
			}
			if (ps != null){
				try {
					ps.close();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();;
				}
			}
			if (ct != null){
				try {
					ct.close();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();;
				}
			}
		}*/
		
		// ���ݿ���֤2
		UserService userService = new UserService();
		User user = new User();
		user.setName(username);
		user.setPwd(password);
		if (userService.checkUser(user)){
			request.getRequestDispatcher("/MainServlet").forward(request, response);
		}
		else{
			request.setAttribute("err", "�û������������");
			request.getRequestDispatcher("/LoginServlet").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
