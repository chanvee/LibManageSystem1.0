package com.chanvee.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chanvee.domain.User;
import com.chanvee.service.UserService;

import sun.nio.cs.US_ASCII;

/**
 * Servlet implementation class UserCtrlServlet
 */
@WebServlet("/UserCtrlServlet")
public class UserCtrlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserCtrlServlet() {
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
		
		String type = request.getParameter("type");
		UserService userservice = new UserService();
		if ("del".equals(type)){
			// 删除用户
			String username = request.getParameter("username");
			if (userservice.delUser(username)){
				request.getRequestDispatcher("/OK").forward(request, response);
			}else{
				request.getRequestDispatcher("/Error").forward(request, response);
			}
		}else if("gotoUpdate".equals(type)){
			String username = request.getParameter("username");
			User user = userservice.getUserByName(username);
			//加user对象放入到request域对象之中
			request.setAttribute("userinfo", user);
			request.getRequestDispatcher("/UserUpdate").forward(request, response);
		}else if("update".equals(type)){
			String originname = request.getParameter("originusername");
			//String originname = request.getAttribute("originusername").toString();
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			// 修改用户
			User user = new User();
			user.setName(username);
			user.setPwd(password);
			if (userservice.updateUser(user, originname)){
				request.getRequestDispatcher("/OK").forward(request, response);
			}else{
				request.getRequestDispatcher("/Error").forward(request, response);
			}
		}else if("gotoAddUser".equals(type)){
			request.getRequestDispatcher("/AddUser").forward(request, response);
		}else if("add".equals(type)){
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			User user = new User();
			user.setName(username);
			user.setPwd(password);
			
			if(userservice.addUser(user)){
				request.getRequestDispatcher("/OK").forward(request, response);
			}else{
				request.getRequestDispatcher("/Error").forward(request, response);
			}
		}else if("gotoAccessUser".equals(type)){
			request.getRequestDispatcher("/AccessUser").forward(request, response);
		}else if("access".equals(type)){
			String username = request.getParameter("username");
			
			User user = userservice.getUserByName(username);
			//加user对象放入到request域对象之中
			request.setAttribute("userinfo", user);
			if (user.getName() != null){
				request.getRequestDispatcher("/Accessed").forward(request, response);
			}
			else{
				request.getRequestDispatcher("/Error").forward(request, response);
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
