<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.util.Properties"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String user=request.getParameter("user");/*username comes through the post method in URL. request.getParameter() reads the field with 'user' as name*/
String pass=request.getParameter("pass");/*password comes through the post method in URL. request.getParameter() reads the field with 'pass' as name*/
String sql="";
Properties prop = new Properties();
	try {
		InputStream input = new FileInputStream("config.properties");/*config.properties contains the username, password of the mysql DBMS and database we are working on*/
		prop.load(input);
		Class.forName(prop.getProperty("classname"));
		Connection con = DriverManager.getConnection(prop.getProperty("databasepath"),
				prop.getProperty("dbuser"), prop.getProperty("dbpassword"));//gets database name, username and password.
		Statement stmt=con.createStatement();
		sql="SELECT * FROM login WHERE username='"+user+"' AND password='"+pass+"';";/*gets the username and password which is in the database that matches the username and password which are in the URL. If the result set is not empty, it means there exists a match. So, username and password are valid. If the result set is empty, it means there is no such username and password entry.*/
		ResultSet rs=stmt.executeQuery(sql);
		if(rs.next()){
			if(rs.getString(1).equals(user)&&rs.getString(2).equals(pass)){
				out.print(rs.getString(3)+","+rs.getString(4));/*If there exists a match, it fetches the type(Admin/general) and location of the user*/
			}else{
				out.println("invalid login,s");
			}
		}
		
	}catch(Exception e){
		out.println(e);
	}
%>

