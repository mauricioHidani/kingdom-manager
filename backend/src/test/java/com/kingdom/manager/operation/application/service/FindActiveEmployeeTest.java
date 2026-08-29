package com.kingdom.manager.operation.application.service;

import com.kingdom.manager.operation.application.port.in.EmployeeIn;
import com.kingdom.manager.operation.domain.model.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindActiveEmployeeTest {

	@Mock
	private EmployeeIn in;

	@InjectMocks
	private FindActiveEmployee findActiveEmployee;

	@Test
	@DisplayName("Find Active Employee When Successful Find Employee Active Should Return A List Of Employee Founded")
	void findActiveEmployee_whenSuccessfulFindEmployeeActive_shouldReturnAListOfEmployeeFounded() {
		List<Employee> employees = new ArrayList<>();
		employees.add(Employee.builder()
				.fullname("Evelyn Jennifer Jaqueline Baptista")
				.cpf("505.647.050-39")
				.ctps("528.735.4-3-24")
				.pis("234.60512.03-9")
				.esocial("e3342")
				.position("Teacher")
				.birthDate(LocalDate.of(1994, 5, 10))
				.hiredIn(LocalDate.of(2025, 11, 12))
				.isActive(true)
				.build());
		employees.add(Employee.builder()
				.fullname("Sebastiana Marcela Mirella Teixeira")
				.cpf("543.522.600-70")
				.ctps("240.288.5-3-14")
				.pis("529.92339.74-2")
				.esocial("e4200")
				.position("Director")
				.birthDate(LocalDate.of(1988, 3, 22))
				.hiredIn(LocalDate.of(2021, 1, 3))
				.isActive(true)
				.build());

		when(in.findAll()).thenReturn(employees);

		List<Employee> result = findActiveEmployee.resolve();

		assertFalse(result.isEmpty());
		assertThat(result).extracting(Employee::isActive).containsOnly(true);
		assertIterableEquals(employees, result);
	}

	@Test
	@DisplayName("Find Active Employee When No Has Active Employee Should Return Empty List")
	void findActiveEmployee_whenNoHasActiveEmployee_shouldReturnEmptyList() {
		List<Employee> emptyList = new ArrayList<>();

		when(in.findAll()).thenReturn(emptyList);

		List<Employee> result = findActiveEmployee.resolve();

		assertTrue(result.isEmpty());
	}

}