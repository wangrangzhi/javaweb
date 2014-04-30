package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Employee;

public class EmployeeDao {
	
	private static EmployeeDao instance = null;
	public static EmployeeDao getInstance()
	{
		if(instance == null)instance = new EmployeeDao();
		return instance;
	}
	
	public boolean saveEmployee(Employee emp)
	{
		boolean result = false;
		Connection con = null;
		try {
			con = DBCon.getConn();
			String sql = "insert into tb_employee(name,age,sex,duty) values(?,?,?,?)";
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, emp.getEmpName());
			stmt.setInt(2, emp.getEmpAge());
			stmt.setString(3, emp.getEmpSex());
			
			stmt.setString(4, emp.getEmpDuty());
			int i = stmt.executeUpdate();
			if( i==1 )result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			try {
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	
	public List<Employee> selectEmployee()
	{
		List<Employee> empList = new ArrayList<Employee>();
		Connection conn = null;
		try {
			conn = DBCon.getConn();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from tb_employee");
			while(rs.next())
			{
				Employee emp = new Employee();
				emp.setEmpId(rs.getInt("id"));
				emp.setEmpName(rs.getString("name"));
				emp.setEmpSex(rs.getString("sex"));
				emp.setEmpAge(rs.getInt("age"));
				emp.setEmpDuty(rs.getString("duty"));
				empList.add(emp);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally
		{
			try {
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return empList;
	}
	
}
