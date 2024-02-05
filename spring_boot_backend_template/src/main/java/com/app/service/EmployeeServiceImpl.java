package com.app.service;

import java.util.List;

import javax.transaction.Transactional;
import com.app.customeexception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.app.dao.EmployeeDao;
import com.app.entities.Employee;


@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
@Autowired
	private EmployeeDao empDao;

public List<Employee> getAllEmps()
{
	return empDao.findAll();
}

public Employee addEmpDetails(Employee newEmp)
{
	return empDao.save(newEmp); //persistent
} //return detached entity to the caller

public String deleteEmp(Long empId)
{
	if(empDao.existsById(empId)) {
		empDao.deleteById(empId);
		return "Employee deleted";
	}
	return "employee not found";
}

public Employee getEmployee(Long empId)
{
	return empDao.findById(empId).orElseThrow(()-> new ResourceNotFoundException("Resource not found with empid"));
}
/*
 * in case of success : method res detached emp pojo
 * in case failure : throws : ResourceNotFoundException
 * */

public Employee updateEmpDetails(Employee emp)
{
	return empDao.save(emp);
}
}
