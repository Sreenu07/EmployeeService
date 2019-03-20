package com.organisation.services;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.organisation.beans.EmployeeBean;
import com.organisation.beans.EmployeeEvent;
import com.organisation.dao.DepartmentRepository;
import com.organisation.dao.EmployeeRepository;
import com.organisation.entities.Department;
import com.organisation.entities.Employee;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	RabbitMQSender rabbitMQSender;

	@Override
	public Employee createEmployee(Employee emp) {	
		if(emp!= null) {
			emp.setCreationTime(new Date());
			emp.setUuid("TA-"+getRandomNumber());
			sentEvent(emp.getUuid(),emp,"CREATION");		
		}
		return employeeRepository.save(emp);
	}
	
	@Override
	public Department createDepartment(Department dept) {
		return departmentRepository.save(dept);
	}	

	@Override
	public void updateEmployee(String uuid,EmployeeBean bean) {
		Employee employee=employeeRepository.findByUuid(uuid);	
		if (null!=employee){
				employee.setFirstName(bean.getFirstName());
				employee.setLastName(bean.getLastName());
				employee.setEmail(bean.getEmail());
				employeeRepository.save(employee);			
				sentEvent(employee.getUuid(),employee,"UPDATION");
		}			 
	}
	
	public EmployeeBean getEmployeeByUuid(String uuid) {
		EmployeeBean empBean=new EmployeeBean();
		Employee e=employeeRepository.findByUuid(uuid);
		if(null!=e) {
			empBean.setFirstName(e.getFirstName());
			empBean.setLastName(e.getLastName());
			empBean.setEmail(e.getEmail());
			empBean.setUuid(e.getUuid());
			empBean.setDateOfBirth(e.getDateOfBirth());
			empBean.setDeptname(e.getDepartment().getName());
		}	
		return empBean;
	}

	@Override
	public void removeEmployee(String uuid) {
		Employee e=employeeRepository.findByUuid(uuid);
		if(null!=e) {
			employeeRepository.delete(e);
			sentEvent(e.getUuid(),e,"DELETION");
		}
	}
	
	
	public static int getRandomNumber(){		
		Random random = new Random();
	    return random.nextInt(900000)+100000;
	}
	
	@Override
	public List<Department> getallDepartments() {
		return (List<Department>)departmentRepository.findAll();
	}
	
	public void sentEvent(String uuid,Employee e,String eventName) {
		EmployeeEvent empevent =  new EmployeeEvent();
		empevent.setUuid(e.getUuid());
		empevent.setEventName(eventName);
		rabbitMQSender.send(empevent);
	}
}
