package com.example.dao;

import com.example.entityBeans.Employee;

public interface EmployeeDAO {

	public void insertEmployee(Employee employee);
	
	public Employee getEmployee(Integer empId);
	
	public void updateEmployee(Employee employee);
	
	public void deleteEmployee(Integer empId);
	
}
