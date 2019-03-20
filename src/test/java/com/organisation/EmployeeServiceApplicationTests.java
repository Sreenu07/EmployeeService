package com.organisation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.organisation.beans.EmployeeBean;
import com.organisation.entities.Employee;
import com.organisation.services.EmployeeServiceImpl;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeServiceApplicationTests {
	
	@Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private EmployeeServiceImpl employeeService;
    
    protected MockMvc mvc;
 
    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
     }
    
    
	@Test
	public void whenFindByUUid_thenReturnEmployee() {
		
		//given
		Employee emp = new Employee();
		emp.setFirstName("Talapa");
		emp.setLastName("Srini");
		emp.setUuid("TA-"+employeeService.getRandomNumber());
		emp.setEmail("srinihyd@gmail.com");
		emp.setCreationTime(new Date());
		emp.setDateOfBirth(new Date(1989-11-07));
		entityManager.persist(emp);
		
		//when	
		EmployeeBean found = employeeService.getEmployeeByUuid(emp.getUuid());
		
		//then
		assertThat(found.getEmail()).isNotNull();
		
		//then
		assertThat(found.getEmail()).isEqualTo(emp.getEmail());
		
	}
	
	@Test
	public void getDepartmentsList() throws Exception {
	   String uri = "/Departments";
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();  
	   int status = mvcResult.getResponse().getStatus();	   
	   //then
	   assertEquals(200, status);
	}
	
	@Test
	public void updateEmployee() throws Exception {
	   String uri = "/employees/TA-297774";
	   Employee employee = new Employee();
	   employee.setFirstName("TestSrini");   
	   String inputJson = mapToJson(employee);
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
	      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
	   int status = mvcResult.getResponse().getStatus();
	   System.out.println("Update status code"+status);
	   assertEquals(200, status);
	}
	
	@Test
	public void deleteEmployee() throws Exception {
	   String uri = "/employees/TA-655387";
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
	   int status = mvcResult.getResponse().getStatus();
	   System.out.println("Delete status code"+status);
	   assertEquals(200, status);
	}

}
