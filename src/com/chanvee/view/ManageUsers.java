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
		out.println("<script type='text/javascript' language='javascript'>");
		out.println("function gotoPageNow(){"
				+ "var pageNow=document.getElementById('pageNow'); "
				+ "window.open('/LibManageSystem1.0/ManageUsers?pageNow='+pageNow.value,'_self');}");
		out.println("</script>");
		out.println("<img src='Images/uestc.jpg'/>");
		out.println("欢迎您，xx!  <a href = '/LibManageSystem1.0/MainServlet'>返回主界面 </a>  <a href = '/LibManageSystem1.0/LoginServlet'>安全退出</a><hr/>");
		out.println("<h1>管理用户</h1>");
		//数据库验证
		Connection ct = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		// 定义分页需要的数量
		int pageNow = 1;//当前页
		// 接受用户的pageNow
		if (request.getParameter("pageNow") == null){
			pageNow = 1;
		}else{
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}	
		
		int pageSize = 3;//制定每页显示的记录数
		int pageCnt = 1;//总页数，计算得到
		int rowCnt = 1;//总记录数，数据库查询得到
		
		//计算pageCnt
		//pageCnt = rowCnt % pageSize == 0?rowCnt/pageSize:rowCnt/pageSize+1;
		pageCnt = (rowCnt-1)/pageSize + 1;
		try {
			//1.加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//2.得到链接
			ct = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");		
			//算出有多少页
			//1.查询rowCnt
			ps = ct.prepareStatement("select count(*) from users");
			rs = ps.executeQuery();
			rs.next();// 把游标下移
			rowCnt = rs.getInt(1);
			pageCnt = (rowCnt-1)/pageSize + 1;
			//3.创建PreparedStatement
			String sql="select * from users limit " +((pageNow-1)*pageSize)+ "," + pageSize;
			ps = ct.prepareStatement(sql);
			//4.执行操作
			rs = ps.executeQuery();
			//5.根据结果处理
			out.println("<table border=1px bordercolor=red cellspacing=0 width=500px>");
			out.println("<tr><th>用户名</th><th>密码</th></tr>");
			while (rs.next()){
				out.println("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td></tr>");
			}
			out.println("</table><br/>");
			// 显示上一页
			if (pageNow != 1){
				out.println("<a href=/LibManageSystem1.0/ManageUsers?pageNow="+(pageNow-1)+">上一页</a>");
			}	
			// 显示分页
			for (int i = 1; i <= pageCnt; i++) {
				out.println("<a href=/LibManageSystem1.0/ManageUsers?pageNow="+i+"><"+i+"></a>");
			}
			// 显示下一页
			if (pageNow != pageCnt){
				out.println("<a href=/LibManageSystem1.0/ManageUsers?pageNow="+(pageNow+1)+">下一页</a>");
			}
			// 显示分页信息
			out.println("&nbsp;&nbsp;&nbsp;当前页"+pageNow+"/总页数"+pageCnt+"<br/>");
			out.println("跳转到  <input type='text' id='pageNow' name = 'pageNow'/> <input type='button' onClick='gotoPageNow()' value = '跳'/>");
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
