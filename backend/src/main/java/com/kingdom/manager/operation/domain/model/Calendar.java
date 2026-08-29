package com.kingdom.manager.operation.domain.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Calendar {

	private final Integer year;
	private final Integer month;
	private final List<CalendarDate> dates = new ArrayList<>();

	protected Calendar() {
		this.year = 0;
		this.month = 0;
	}

	public Calendar(Integer year, Integer month) {
		this.year = year;
		this.month = month;
	}

	public static CalendarBuilder builder() {
		return new CalendarBuilder();
	}

	public Integer year() {
		return year;
	}

	public Integer month() {
		return month;
	}

	public List<CalendarDate> dates() {
		return dates;
	}

	public CalendarDate dateIndexOf(Integer index) {
		return this.dates.get(index);
	}

	public List<CalendarDate> dateBy(Integer year, Integer month, Integer day) {
		return this.dates.stream()
				.filter(d -> d.date().getYear() == year && d.date().getMonthValue() == month && d.date().getDayOfMonth() == day)
				.toList();
	}

	public void addDate(CalendarDate date) {
		this.dates.add(date);
	}

	public void addManyDates(Collection<? extends CalendarDate> dates) {
		this.dates.addAll(dates);
	}

	public static class CalendarBuilder {

		private Integer year;
		private Integer month;
		private List<CalendarDate> dates = new ArrayList<>();

		public CalendarBuilder() {
		}

		public CalendarBuilder year(Integer year) {
			this.year = year;
			return this;
		}

		public CalendarBuilder month(Integer month) {
			this.month = month;
			return this;
		}

		public CalendarBuilder addDate(CalendarDate date) {
			this.dates.add(date);
			return this;
		}

		public Calendar build() {
			Calendar res = new Calendar(year, month);
			res.addManyDates(this.dates);
			return res;
		}

	}

}
