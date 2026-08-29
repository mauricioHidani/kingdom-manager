package com.kingdom.manager.operation.application.service;

import com.kingdom.manager.operation.domain.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CreateTimesheet {

	private final FindActiveEmployee findActiveEmployee;
	private final FindCalendar findCalendar;

	public CreateTimesheet(FindActiveEmployee findActiveEmployee, FindCalendar findCalendar) {
		this.findActiveEmployee = findActiveEmployee;
		this.findCalendar = findCalendar;
	}

	public List<Timesheet> resolve(Integer year, Integer month, Employee adm) {
		Calendar calendar = null;
		List<Timesheet> timesheets = null;
		List<Employee> employees = findActiveEmployee.resolve();

		if (employees.isEmpty())
			throw new RuntimeException("There are no employee to create the timesheet");

		timesheets = new ArrayList<>();
		calendar = findCalendar.resolve(year, month);

		for (Employee employee : employees) {
			Timesheet sheet = new Timesheet(year, month, employee, adm);

			populateWithCalendar(calendar, sheet);
			putBirthDate(employee, sheet, month);

			timesheets.add(sheet);
		}

		return timesheets;
	}

	private LocalDateTime toLocalDateTime(LocalDate date) {
		return LocalDateTime.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), 0, 0, 0);
	}

	private void populateWithCalendar(Calendar calendar, Timesheet sheet) {
		for (CalendarDate d : calendar.dates()) {
			TimesheetRegister register = TimesheetRegister.builder()
					.title(d.title())
					.note(d.description())
					.document("calendar")
					.start(toLocalDateTime(d.date()))
					.end(toLocalDateTime(d.date()))
					.build();
			sheet.addRegister(register);
		}
	}

	private void putBirthDate(Employee employee, Timesheet sheet, Integer month) {
		if (employee.birthDate().getMonthValue() == month)  {
			TimesheetRegister birth = TimesheetRegister.builder()
					.title("Aniversário %s".formatted(employee.fullname()))
					.note("birth")
					.document("")
					.start(toLocalDateTime(employee.birthDate()))
					.end(toLocalDateTime(employee.birthDate()))
					.build();
			sheet.addRegister(birth);
		}
	}

}
