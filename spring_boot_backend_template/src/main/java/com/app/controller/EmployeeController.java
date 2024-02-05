package com.app.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.Employee;
import com.app.service.EmployeeService;

@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {
	@Autowired
	private EmployeeService empService;
	public EmployeeController()
	{
		System.out.println("in ctor of "+getClass());
	}
	
	//REST API end point
//	url : http://localhost:8080/employees/
//	method:GET
//	resp:List<EMP>
	@GetMapping
	public List<Employee> listAllEmps(){
		System.out.println("in list of all emps");
		return empService.getAllEmps();
	}
	
	
//	url : http://localhost:8080/employees/
//		method:GET
//		resp:Detached Employee 
	@PostMapping
	public Employee addEmpDetails(@RequestBody Employee newEmp)
//	@RequestBody : method arg level annotation for un marshalling
//	de-ser(json--:> java obj) done by Jackson vendor
	{
		System.out.println("in add emp"+newEmp);
		return empService.addEmpDetails(newEmp);
	}
	
	@DeleteMapping("/{empId}")
	public String deleteEmpDetails(@PathVariable Long empId)
	{
		return empService.deleteEmp(empId);
		
	}
	
	@GetMapping("/{empId}")
	public Employee updateEmp(@PathVariable Long empId)
	{
		return empService.getEmployee(empId);
		
	}
	
	@PutMapping
	public Employee updateEmpDetails(@RequestBody Employee emp)
	{
		return empService.updateEmpDetails(emp);
	}
}


