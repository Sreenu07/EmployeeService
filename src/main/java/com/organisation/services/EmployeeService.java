package com.organisation.services;

import java.util.List;

import com.organisation.beans.EmployeeBean;
import com.organisation.entities.Department;
import com.organisation.entities.Employee;

public interface EmployeeService {
	public Employee createEmployee(Employee emp);
	public Department createDepartment(Department dpt);
	public List<Department> getallDepartments();
	public void updateEmployee(String uuid,EmployeeBean empBean);
	public EmployeeBean getEmployeeByUuid(String uuid);
	public void removeEmployee(String uuid);
}
