package com.fx.style;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fx.db.BaseDao;

public class RegisterServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			this.doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Object[] obj = request.getParameterValues("arr");
		String sql = "insert into info(name,sex,age,phoneNumber,post,section,pay,hiredate,share) values(?,?,?,?,?,?,?,?,?)";
//		String[] obj = new String[]{"幸福一生dsfsfa","0","12","164846389473","Web软件工程师","研发部","9000","2014-4-5","0"}; 
 		boolean flag = BaseDao.getDao().updataFunction(sql, obj);
 		System.out.println(flag);
		out.flush();
		out.close();
	}

}
