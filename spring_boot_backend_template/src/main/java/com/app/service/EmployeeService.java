package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.app.entities.Employee;


public interface EmployeeService 
{
	List<Employee> getAllEmps();
	Employee addEmpDetails(Employee newEmp);
	String deleteEmp(Long empId);
	Employee getEmployee(Long empId);
	Employee updateEmpDetails(Employee emp);
}
