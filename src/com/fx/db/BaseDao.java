package com.fx.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.style.bean.InfoBean;
import com.style.bean.LoginBean;

import javassist.tools.reflect.Loader;
public class BaseDao {
	private final static String DRIVER = "com.mysql.jdbc.Driver";
	private final static String URL = "jdbc:mysql://127.0.0.1:3306/company_manager_stytem?characterEncoding=utf-8";
	private final static String PWD = "root";
	private final static String USER = "root";
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static private BaseDao dao ;
	private BaseDao() {
		super();
	}
	public static BaseDao getDao(){
		if(dao != null){
			return dao ;
		}else{
			return new BaseDao() ;
		}
	}
	//获取链接
	public Connection getConnection(){
		Connection conn = null ;
		try {
			conn = DriverManager.getConnection(URL,USER,PWD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	//增删改
	public  boolean updataFunction(String sql,Object... arr){
		boolean flag = false ;
		Connection conn = getConnection();
		PreparedStatement ps = null ;
		 int k = 0 ;
		try {
			 ps = conn.prepareStatement(sql);
			 if(arr!=null){
				 for (int i = 0; i < arr.length; i++) {
					ps.setObject(i+1, arr[i]);
				}
			 }
			 k = ps.executeUpdate();
			 if(k!=0){
				 flag = true ; 
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeConnect(null, ps, conn);
		}
		return flag ;
	}
	
	public LoginBean isHave(String name,String pwd){
		LoginBean bean = null;
		StringBuffer sql = new StringBuffer("select * from login where name = ? and password = ? ");
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null ;
		con = getConnection();
		try {
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, name);
			ps.setString(2, pwd);
			rs = ps.executeQuery();
			if(rs.next()){
				bean = new LoginBean() ;
				bean.setName(rs.getString("name"));
				bean.setLimits(rs.getInt("limits"));
				bean.setUserId(rs.getInt("userId"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeConnect(rs, ps, con);
		}
		
	  return bean ;
	}
	public InfoBean getOneselfInfo(String userID){
		InfoBean bean = null;
		StringBuffer sql = new StringBuffer("select * from info where userId = ?");
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null ;
		con = getConnection();
		try {
			ps = con.prepareStatement(sql.toString());
			ps.setString(1, userID);
			rs = ps.executeQuery();
			if(rs.next()){
				bean = new InfoBean() ;
				bean.setName(rs.getString("name"));
				bean.setUserId(rs.getInt("userId"));
				bean.setSex(rs.getInt("sex"));
				bean.setAge(rs.getInt("age"));
				bean.setPhoneNumber(rs.getString("phoneNumber"));
				bean.setPost(rs.getString("post"));
				bean.setSection(rs.getString("section"));
				bean.setPay(rs.getString("pay"));
				bean.setHiredate(rs.getString("hiredate"));
				bean.setShare(rs.getString("share"));
				bean.setAwardId(rs.getInt("awardId"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeConnect(rs, ps, con);
		}
		
	  return bean ;
	}
	
	//关闭链接
	public void closeConnect(ResultSet set ,Statement sta ,Connection conn){
		if(set!=null){
			try {
				set.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(sta!=null){
			try {
				sta.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
