package com.organisation.dao;
import org.springframework.data.repository.CrudRepository;

import com.organisation.entities.Department;


public interface DepartmentRepository extends CrudRepository<Department,Integer>{
	
}
