package com.example.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.db.DBConnections;
import com.example.entityBeans.Employee;

public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	public DBConnections h2Connection;

	@Override
	public void insertEmployee(Employee employee) {
		Connection connection = h2Connection.getDBConnection();
		Statement stmt = null;
		
		try {
			stmt = connection.createStatement();
			stmt.execute("INSERT INTO EMPLOYEE"
	        		+ "("
	        		+ "EMPID, "
	        		+ "USERNAME, "
	        		+ "PASSWORD, "
	        		+ "FIRSTNAME, "
	        		+ "LASTNAME, "
	        		+ "ADDRESS"
	        		+ ") "
	        		+ "VALUES"
	        		+ "("
	        		+ "10"
	        		+ ", '"
	        		+employee.getUserName()
	        		+ "','"
	        		+employee.getPassword()
	        		+ "','"
	        		+employee.getFirstName()
	        		+ "','"
	        		+employee.getLastName()
	        		+ "','"
	        		+employee.getAddress()
	        		+ "')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				connection.commit();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Employee getEmployee(Integer empId) {
		Employee employee = new Employee();
		Connection connection = h2Connection.getDBConnection();
		Statement stmt = null;
		
		try {
			stmt = connection.createStatement();
			ResultSet resultSet = stmt.executeQuery("SELECT * FROM EMPLOYEE WHERE EMPID = " + empId);
			while (resultSet.next()) {
				employee.setUserName(resultSet.getString("USERNAME"));
			}
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			
		return employee;
	}

	@Override
	public void updateEmployee(Employee employee) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteEmployee(Integer empId) {
		// TODO Auto-generated method stub

	}

}
