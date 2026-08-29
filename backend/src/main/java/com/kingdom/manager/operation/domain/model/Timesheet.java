package com.kingdom.manager.operation.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Timesheet {

	private final Integer year;
	private final Integer month;
	private final Employee employee;
	private final Employee validatedBy;
	private final List<TimesheetRegister> registers = new ArrayList<>();

	public Timesheet(Integer year, Integer month, Employee employee, Employee validatedBy) {
		this.year = year;
		this.month = month;
		this.employee = employee;
		this.validatedBy = validatedBy;
	}

	public Integer year() {
		return year;
	}

	public Integer month() {
		return month;
	}

	public Employee employee() {
		return employee;
	}

	public Employee validatedBy() {
		return validatedBy;
	}

	public List<TimesheetRegister> registers() {
		return registers;
	}

	public TimesheetRegister registerIndexOf(Integer index) {
		return this.registers.get(index);
	}

	public void addRegister(TimesheetRegister register) {
		this.registers.add(register);
	}

	public void addManyRegisters(ArrayList<TimesheetRegister> registers) {
		this.registers.addAll(registers);
	}

}
