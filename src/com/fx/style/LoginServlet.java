package com.fx.style;

import java.io.IOException;
import java.io.PrintWriter;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.fx.db.BaseDao;
import com.style.bean.LoginBean;

public class LoginServlet extends HttpServlet {

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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String name = request.getParameter("name") ;
		String password = request.getParameter("password");
		PrintWriter out = response.getWriter();
		BaseDao dao = BaseDao.getDao();
		LoginBean bean = dao.isHave(name, password) ; 
		if(bean != null){
			String json = JSON.toJSONString(bean);
//			System.out.println(json);
			out.print("{\"succeed\":\"yes\",\"bean\":"+json+"}");
			HttpSession session = request.getSession() ;
			session.setAttribute("userId", bean.getUserId());
			response.sendRedirect("oneselfInfo.do");
		}else{
			out.print("{\"succeed\":\"no\"}");
		}
		out.flush();
		out.close();
	}

}
