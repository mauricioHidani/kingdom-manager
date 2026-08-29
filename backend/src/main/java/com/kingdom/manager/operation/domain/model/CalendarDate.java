package com.kingdom.manager.operation.domain.model;

import java.time.LocalDate;

public class CalendarDate {

	private final String title;
	private final String description;
	private final LocalDate date;
	private final Boolean dayOff;

	protected CalendarDate() {
		this.title = String.valueOf("");
		this.description = String.valueOf("");
		this.date = null;
		this.dayOff = false;
	}

	public CalendarDate(String title, String description, LocalDate date, Boolean dayOff) {
		this.title = title;
		this.description = description;
		this.date = date;
		this.dayOff = dayOff;
	}

	public static CalendarDateBuilder builder() {
		return new CalendarDateBuilder();
	}

	public String title() {
		return title;
	}

	public String description() {
		return description;
	}

	public LocalDate date() {
		return date;
	}

	public Boolean dayOff() {
		return dayOff;
	}

	public static class CalendarDateBuilder {

		private String title;
		private String description;
		private LocalDate date;
		private Boolean dayOff;

		public CalendarDateBuilder() {
			this.title = String.valueOf("");
			this.description = String.valueOf("");
			this.date = null;
			this.dayOff = false;
		}

		public CalendarDateBuilder title(String title) {
			this.title = title;
			return this;
		}

		public CalendarDateBuilder description(String description) {
			this.description = description;
			return this;
		}

		public CalendarDateBuilder date(LocalDate date) {
			this.date = date;
			return this;
		}

		public CalendarDateBuilder dayOff(Boolean dayOff) {
			this.dayOff = dayOff;
			return this;
		}

		public CalendarDate build() {
			return new CalendarDate(title, description, date, dayOff);
		}

	}

}
