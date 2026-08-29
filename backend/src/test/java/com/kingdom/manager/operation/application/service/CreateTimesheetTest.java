package com.kingdom.manager.operation.application.service;

import com.kingdom.manager.operation.domain.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateTimesheetTest {

	@Mock
	private FindActiveEmployee findActiveEmployee;

	@Mock
	private FindCalendar findCalendar;

	@InjectMocks
	private CreateTimesheet createTimesheet;

	@Test
	@DisplayName("Create Timesheet When Successful Creating The Timesheet With A Special Date Should Return The Created Timesheet List")
	void createTimesheet_whenSuccessfulCreatingTheTimesheetWithASpecialDate_shouldReturnTheCreatedTimesheetList() {
		Integer year = 2026;
		Integer month = 8;
		Employee adm = buildAdm();
		Calendar calendar = buildCalendar(year, month);
		List<Employee> employees = new ArrayList<>();
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

		when(findActiveEmployee.resolve()).thenReturn(employees);
		when(findCalendar.resolve(year, month)).thenReturn(calendar);

		List<Timesheet> result = createTimesheet.resolve(year, month, adm);

		assertFalse(result.isEmpty());
		assertThat(result)
				.filteredOn("employee.fullname", employees.get(0).fullname())
				.flatExtracting(Timesheet::registers)
				.extracting(TimesheetRegister::title, TimesheetRegister::start)
				.contains(tuple("PRD Alignment Meeting", LocalDateTime.of(year, month, 10, 0, 0)));
	}

	@Test
	@DisplayName("Create Timesheet When Successful Creating The Timesheet With A Birth Date Should Return The Created Timesheet List")
	void createTimesheet_whenSuccessfulCreatingTheTimesheetWithABirthDate_shouldReturnTheCreatedTimesheetList() {
		Integer year = 2026;
		Integer month = 8;
		Employee adm = buildAdm();
		Calendar calendar = buildCalendar(year, month);
		List<Employee> employees = new ArrayList<>();
		employees.add(Employee.builder()
				.fullname("Sebastiana Marcela Mirella Teixeira")
				.cpf("543.522.600-70")
				.ctps("240.288.5-3-14")
				.pis("529.92339.74-2")
				.esocial("e4200")
				.position("Director")
				.birthDate(LocalDate.of(1988, 8, 22))
				.hiredIn(LocalDate.of(2021, 1, 3))
				.isActive(true)
				.build());

		when(findActiveEmployee.resolve()).thenReturn(employees);
		when(findCalendar.resolve(year, month)).thenReturn(calendar);

		List<Timesheet> result = createTimesheet.resolve(year, month, adm);

		assertFalse(result.isEmpty());
		assertThat(result)
				.filteredOn("employee.fullname", employees.get(0).fullname())
				.flatExtracting(t -> t.registers().stream()
						.filter(r -> r.start().getDayOfMonth() == employees.get(0).birthDate().getDayOfMonth())
						.toList())
				.extracting(TimesheetRegister::title)
				.contains("Aniversário %s".formatted(employees.get(0).fullname()));
	}

	@Test
	@DisplayName("Create Timesheet When Active Employees Does Not Exists Should Throw Exception")
	void createTimesheet_whenActiveEmployeesDoesNotExists_shouldThrowException() {
		Integer year = 2026;
		Integer month = 8;
		Employee adm = buildAdm();
		String throwMsg = "There are no employee to create the timesheet";

		when(findActiveEmployee.resolve()).thenReturn(List.of());

		RuntimeException e = assertThrows(RuntimeException.class, () -> createTimesheet.resolve(year, month, adm));

		assertEquals(throwMsg, e.getMessage());
	}

	private Employee buildAdm() {
		return Employee.builder()
				.fullname("Evelyn Jennifer Jaqueline Baptista")
				.cpf("505.647.050-39")
				.ctps("528.735.4-3-24")
				.pis("234.60512.03-9")
				.esocial("e3342")
				.position("Teacher")
				.birthDate(LocalDate.of(1994, 5, 10))
				.hiredIn(LocalDate.of(2025, 11, 12))
				.isActive(true)
				.build();
	}

	private Calendar buildCalendar(Integer year, Integer month) {
		CalendarDate prdAlignmentMeeting = CalendarDate.builder()
				.title("PRD Alignment Meeting")
				.description("Product scope refinement assessment")
				.date(LocalDate.of(year, month, 10))
				.dayOff(false)
				.build();
		CalendarDate offsettingOfHours = CalendarDate.builder()
				.title("Offsetting of hours")
				.description("Work break established by Manager XPTO")
				.date(LocalDate.of(year, month, 21))
				.dayOff(true)
				.build();
		Calendar calendar = Calendar.builder()
				.year(year)
				.month(month)
				.addDate(prdAlignmentMeeting)
				.addDate(offsettingOfHours)
				.build();

		return calendar;
	}

}