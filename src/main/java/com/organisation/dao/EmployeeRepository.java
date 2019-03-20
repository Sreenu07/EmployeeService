package com.organisation.dao;

import org.springframework.data.repository.CrudRepository;

import com.organisation.entities.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {	
	Employee findByUuid(String uuid);
}
