package com.kingdom.manager.operation.application.port.in;

import com.kingdom.manager.operation.domain.model.Employee;

import java.util.List;

public interface EmployeeIn {

	List<Employee> findAll();

}
