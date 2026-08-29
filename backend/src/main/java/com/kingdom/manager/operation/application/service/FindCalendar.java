package com.kingdom.manager.operation.application.service;

import com.kingdom.manager.operation.application.port.in.CalendarIn;
import com.kingdom.manager.operation.domain.model.Calendar;

import java.time.LocalDate;

public class FindCalendar {

	private final CalendarIn in;

	public FindCalendar(CalendarIn in) {
		this.in = in;
	}

	Calendar resolve(Integer year, Integer month) {
		boolean isValidYear = year < 1760 || year > LocalDate.now().getYear();
		boolean isValidMonth = month < 1 || month > 12;

		if (isValidYear || isValidMonth)
			throw new RuntimeException("The specified year or month are invalid");

		return in.find(year, month)
				.orElseThrow(() -> new RuntimeException("The calendar for the specified year and month was not found"));
	}

}
