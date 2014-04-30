package dao;

import java.sql.Connection;
import java.sql.DriverManager;

//import com.sun.corba.se.impl.orb.ParserTable.TestContactInfoListFactory;

public class DBCon {
	
	public static Connection getConn()
	{
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String user = "root";
			String pwd = "root";
			String url = "jdbc:mysql://localhost:3306/db_database07";
			con = DriverManager.getConnection(url, user, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(con != null)
		{
			System.out.println("连接成功");
		}
		return con;
	}

	

}
