<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.*" %>



<%!
String str = "";

private void tree(Connection conn, int id, int level) {
	Statement stmt = null;
	ResultSet rs = null;
	String preStr = "";
	for(int i=0; i<level; i++) {
		preStr += "----";
	}
	try {
		stmt = conn.createStatement();
		String sql = "select * from article where pid = " + id;
		rs = stmt.executeQuery(sql);
		String strLogin = "";
		
		while(rs.next()) {
			
			str += "<tr><td>" + rs.getInt("id") + "</td><td>" +
			       preStr + "<a href='ShowArticleDetail.jsp?id=" + rs.getInt("id") + "'>" + 
			       rs.getString("title") + "</a></td>" +
			       strLogin +
			       "</td></tr>";
			if(rs.getInt("isleaf") != 0) {
				tree(conn, rs.getInt("id"), level+1);
			}
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			if(rs != null) {
				rs.close();
				rs = null;
			}
			if(stmt != null) {
				stmt.close();
				stmt = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>Insert title here</title>
</head>
<body>
<a href="Post.jsp">发表新帖</a>
<table border="1">



<%
Class.forName("com.mysql.jdbc.Driver");
String url = "jdbc:mysql://localhost:3306/bbs";
Connection conn = DriverManager.getConnection(url,"root","root");

Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery("select * from article where pid = 0");

if(rs!=null)
	System.out.println("连接成功");
String strLogin = "";

//%>sd<%

while(rs.next()) {
	
	str += "<tr><td>" + rs.getInt("id") + "</td><td>" +
    	   "<a href='ShowArticleDetail.jsp?id=" + rs.getInt("id") + "'>" + 
           rs.getString("title") + "</a></td>" +
           strLogin +
           "</td></tr>";
   	if(rs.getInt("isleaf") != 0) {
   		tree(conn, rs.getInt("id"), 1);
   	}
   	
   	
   	System.out.println(rs.getInt("id"));

	System.out.println(rs.next());
}


rs.close();
stmt.close();
conn.close();
%>
<%=str %>
<% 
str = ""; 

%>
</table>
</body>

</html>