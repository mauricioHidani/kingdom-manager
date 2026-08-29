package com.kingdom.manager.operation.application.service;

import com.kingdom.manager.operation.application.port.in.EmployeeIn;
import com.kingdom.manager.operation.domain.model.Employee;

import java.util.List;

public class FindActiveEmployee {

	private final EmployeeIn in;

	public FindActiveEmployee(EmployeeIn in) {
		this.in = in;
	}

	public List<Employee> resolve() {
		return in.findAll().stream()
				.filter(Employee::isActive)
				.toList();
	}

}
