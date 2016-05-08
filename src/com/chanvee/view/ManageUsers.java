package com.chanvee.view;

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

/**
 * Servlet implementation class ManageUsers
 */
@WebServlet("/ManageUsers")
public class ManageUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageUsers() {
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
		out.println("<h1>管理用户</h1>");
		//数据库验证
				Connection ct = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				try {
					//1.加载驱动
					Class.forName("com.mysql.jdbc.Driver");
					//2.得到链接
					ct = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");		
					//3.创建PreparedStatement
					ps = ct.prepareStatement("select * from users");
					//4.执行操作
					rs = ps.executeQuery();
					//5.根据结果处理
					out.println("<table border = 2 width = 50px>");
					out.println("<tr><th>用户名</th><th>密码</th></tr>");
					while (rs.next()){
						out.println("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td></tr>");
					}
					out.println("</table>");
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
