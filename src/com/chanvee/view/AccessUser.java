package com.chanvee.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AccessUser
 */
@WebServlet("/AccessUser")
public class AccessUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccessUser() {
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
		
		out.println("<img src='Images/uestc.jpg'/>");
		out.println("��ӭ����xx!  <a href = '/LibManageSystem1.0/MainServlet'>���������� </a>  <a href = '/LibManageSystem1.0/LoginServlet'>��ȫ�˳�</a><hr/>");
		out.println("<h1>�����û�</h1>");
		out.println("<form action='/LibManageSystem1.0/UserCtrlServlet?type=access' method ='post'>");
		out.println("<input type='text' name = 'username' value=''/>");
		out.println("<input type='submit' value='����'><br/>");		
		out.println("</form>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
