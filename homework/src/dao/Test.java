package dao;
import java.util.List;

import model.Employee;
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Employee emp = new Employee();
		emp.setEmpName("james");
		emp.setEmpAge(25);
		emp.setEmpSex("man");
		emp.setEmpDuty("ASP.Net Programmer");
		boolean res = EmployeeDao.getInstance().saveEmployee(emp);
		if(res==true){
			System.out.println("插入数据成功！");
		}
		else{
			System.out.println("插入数据失败！");
		}
		
		List<Employee> emplist = EmployeeDao.getInstance().selectEmployee();
		for (Employee employee : emplist) {
			System.out.println(employee.getEmpName());
		}
	}

}
