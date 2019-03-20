package com.organisation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.organisation.beans.EmployeeBean;
import com.organisation.entities.Department;
import com.organisation.entities.Employee;
import com.organisation.services.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping({"/employees"})
@Api(value="/employees",produces ="application/json")
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	
	@ApiOperation(value = "Add Department")
	@PostMapping("/departments")
	public Department createDepartment(@RequestBody Department dpt) {	
		return service.createDepartment(dpt);
	}

	@ApiOperation(value = "Add Employee")
	@PostMapping
	public Employee createEmployee(@RequestBody Employee emp) {	
		return service.createEmployee(emp);
	}
	
	@ApiOperation(value="Get employee",response=EmployeeBean.class)
    @ApiResponses(value={
    @ApiResponse(code=200,message="employee Details Retrieved",response=EmployeeBean.class),
    @ApiResponse(code=500,message="Internal Server Error"),
    @ApiResponse(code=404,message="Employee not found")
    })
	@GetMapping("/{uuid}")
	public EmployeeBean getEmployeeByUuid(@PathVariable("uuid") String uuid) {
		return service.getEmployeeByUuid(uuid);
	}
	
	@ApiOperation(value = "Update employee")
	@PutMapping("/{uuid}")
	public void updateEmployee(@RequestBody EmployeeBean empBean, @PathVariable("uuid") String uuid) {
		service.updateEmployee(uuid,empBean);
	}
	
	@ApiOperation(value = "Delete employee")
	@DeleteMapping("/{uuid}")
	public void deleteEmployeeByID(@PathVariable("uuid") String uuid) {
		 service.removeEmployee(uuid);
	}
	
	@ApiOperation(value = "All Departments")
	@GetMapping("/departments")
	public List<Department> getAllDepartments(){
		return service.getallDepartments();
	}	
}
