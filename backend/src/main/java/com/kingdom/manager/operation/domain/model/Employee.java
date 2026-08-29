package com.kingdom.manager.operation.domain.model;

import java.time.LocalDate;

public class Employee {

	private final String fullname;
	private final String cpf;
	private final String ctps;
	private final String pis;
	private final String esocial;
	private final String position;
	private final LocalDate birthDate;
	private final LocalDate hiredIn;
	private final Boolean isActive;

	protected Employee() {
		this.fullname = String.valueOf("");
		this.cpf = String.valueOf("");
		this.ctps = String.valueOf("");
		this.pis = String.valueOf("");
		this.esocial = String.valueOf("");
		this.position = String.valueOf("");
		this.birthDate = null;
		this.hiredIn = null;
		this.isActive = false;
	}

	public Employee(String fullname, String cpf, String ctps, String pis, String esocial, String position,
					LocalDate birthDate, LocalDate hiredIn, Boolean isActive) {
		this.fullname = fullname;
		this.cpf = cpf;
		this.ctps = ctps;
		this.pis = pis;
		this.esocial = esocial;
		this.position = position;
		this.birthDate = birthDate;
		this.hiredIn = hiredIn;
		this.isActive = isActive;
	}

	public static EmployeeBuilder builder() {
		return new EmployeeBuilder();
	}

	public String fullname() {
		return fullname;
	}

	public String cpf() {
		return cpf;
	}

	public String ctps() {
		return ctps;
	}

	public String pis() {
		return pis;
	}

	public String esocial() {
		return esocial;
	}

	public String position() {
		return position;
	}

	public LocalDate birthDate() {
		return birthDate;
	}

	public LocalDate hiredIn() {
		return hiredIn;
	}

	public Boolean isActive() {
		return isActive;
	}

	public static class EmployeeBuilder {

		private String fullname;
		private String cpf;
		private String ctps;
		private String pis;
		private String esocial;
		private String position;
		private LocalDate birthDate;
		private LocalDate hiredIn;
		private Boolean isActive;

		public EmployeeBuilder fullname(String fullname) {
			this.fullname = fullname;
			return this;
		}

		public EmployeeBuilder cpf(String cpf) {
			this.cpf = cpf;
			return this;
		}

		public EmployeeBuilder ctps(String ctps) {
			this.ctps = ctps;
			return this;
		}

		public EmployeeBuilder pis(String pis) {
			this.pis = pis;
			return this;
		}

		public EmployeeBuilder esocial(String esocial) {
			this.esocial = esocial;
			return this;
		}

		public EmployeeBuilder position(String position) {
			this.position = position;
			return this;
		}

		public EmployeeBuilder birthDate(LocalDate birthDate) {
			this.birthDate = birthDate;
			return this;
		}

		public EmployeeBuilder hiredIn(LocalDate hiredIn) {
			this.hiredIn = hiredIn;
			return this;
		}

		public EmployeeBuilder isActive(Boolean isActive) {
			this.isActive = isActive;
			return this;
		}

		public Employee build() {
			return new Employee(fullname, cpf, ctps, pis, esocial, position, birthDate, hiredIn, isActive);
		}

	}

}
