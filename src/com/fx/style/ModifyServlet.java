package com.fx.style;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fx.db.BaseDao;

public class ModifyServlet extends HttpServlet {

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
		Object arr = request.getParameterValues("arr");
//		update info set name='何礼波w' , sex = 100 ,age = 11, phoneNumber = '15777728399' , post = '云架构' , section = '技术部' , pay = '9999' , hiredate = '1995-4-5' , share = '0.06%'  where userId = 1;
		String sql = "update info set name=? , sex = ? ,age = ?, phoneNumber = ? , post = ? , section = ? , pay = ? , hiredate = ? , share = ?  where userId = ?" ; 
		boolean flag = BaseDao.getDao().updataFunction(sql, arr) ;
		if(flag){
			out.print("{\"succeed\":\"yes\"}");
		}else{
			out.print("{\"succeed\":\"no\"}");
		}
		out.flush();
		out.close();
	}

}
