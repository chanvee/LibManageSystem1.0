package com.chanvee.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chanvee.domain.User;

/**
 * Servlet implementation class Accessed
 */
@WebServlet("/Accessed")
public class Accessed extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Accessed() {
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
		
		User user = (User) request.getAttribute("userinfo");
		out.println("<img src='Images/uestc.jpg'/>");
		out.println("欢迎您，xx!  <a href = '/LibManageSystem1.0/MainServlet'>返回主界面 </a>  <a href = '/LibManageSystem1.0/LoginServlet'>安全退出</a><hr/>");
		out.println("<h3>用户 "+user.getName()+" 的信息：</h3>");

		out.println("<table border=1px bordercolor=red cellspacing=0 width=300px>");
		out.println("<tr><td>username</td><td>"+user.getName()+"</td></tr>");
		out.println("<tr><td>password</td><td>"+user.getPwd()+"</td></tr>");
		out.println("</table><br/>");		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
