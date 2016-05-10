package com.chanvee.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
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
		out.println("<img src='Images/uestc.jpg'/>欢迎 xx 登录<hr/>");
		out.println("<h1>图书馆管理系统</h1>");
		out.println("<h3>请选择您要进行的操作</h3>");
		out.println("<a href = '/LibManageSystem1.0/ManageUsers'>管理用户</a></br>");
		out.println("<a href = '/LibManageSystem1.0/UserCtrlServlet?type=gotoAddUser'>添加用户</a><br/>");
		out.println("<a href = '/LibManageSystem1.0/UserCtrlServlet?type=gotoAccessUser'>查找用户</a><br/>");
		out.println("<a href = '/LibManageSystem1.0/LoginServlet'>退出系统</a><br/>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
