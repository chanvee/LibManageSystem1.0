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
		out.println("��ӭ����xx!  <a href = '/LibManageSystem1.0/MainServlet'>���������� </a>  <a href = '/LibManageSystem1.0/LoginServlet'>��ȫ�˳�</a><hr/>");
		out.println("<h1>�����û�</h1>");
		//���ݿ���֤
		Connection ct = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		// �����ҳ��Ҫ������
		int pageNow = 1;//��ǰҳ
		// �����û���pageNow
		if (request.getParameter("pageNow") == null){
			pageNow = 1;
		}else{
			pageNow = Integer.parseInt(request.getParameter("pageNow"));
		}	
		
		int pageSize = 3;//�ƶ�ÿҳ��ʾ�ļ�¼��
		int pageCnt = 1;//��ҳ��������õ�
		int rowCnt = 1;//�ܼ�¼�������ݿ��ѯ�õ�
		
		//����pageCnt
		//pageCnt = rowCnt % pageSize == 0?rowCnt/pageSize:rowCnt/pageSize+1;
		pageCnt = (rowCnt-1)/pageSize + 1;
		try {
			//1.��������
			Class.forName("com.mysql.jdbc.Driver");
			//2.�õ�����
			ct = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");		
			//����ж���ҳ
			//1.��ѯrowCnt
			ps = ct.prepareStatement("select count(*) from users");
			rs = ps.executeQuery();
			rs.next();// ���α�����
			rowCnt = rs.getInt(1);
			pageCnt = (rowCnt-1)/pageSize + 1;
			//3.����PreparedStatement
			String sql="select * from users limit " +((pageNow-1)*pageSize)+ "," + pageSize;
			ps = ct.prepareStatement(sql);
			//4.ִ�в���
			rs = ps.executeQuery();
			//5.���ݽ������
			out.println("<table border=1px bordercolor=red cellspacing=0 width=500px>");
			out.println("<tr><th>�û���</th><th>����</th></tr>");
			while (rs.next()){
				out.println("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td></tr>");
			}
			out.println("</table><br/>");
			// ��ʾ��һҳ
			if (pageNow != 1){
				out.println("<a href=/LibManageSystem1.0/ManageUsers?pageNow="+(pageNow-1)+">��һҳ</a>");
			}	
			// ��ʾ��ҳ
			for (int i = 1; i <= pageCnt; i++) {
				out.println("<a href=/LibManageSystem1.0/ManageUsers?pageNow="+i+"><"+i+"></a>");
			}
			// ��ʾ��һҳ
			if (pageNow != pageCnt){
				out.println("<a href=/LibManageSystem1.0/ManageUsers?pageNow="+(pageNow+1)+">��һҳ</a>");
			}
			// ��ʾ��ҳ��Ϣ
			out.println("&nbsp;&nbsp;&nbsp;��ǰҳ"+pageNow+"/��ҳ��"+pageCnt+"<br/>");
			out.println("��ת��  <input type='text' id='pageNow' name = 'pageNow'/> <input type='button' onClick='gotoPageNow()' value = '��'/>");
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
