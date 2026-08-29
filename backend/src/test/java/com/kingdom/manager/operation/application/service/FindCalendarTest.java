package com.kingdom.manager.operation.application.service;

import com.kingdom.manager.operation.application.port.in.CalendarIn;
import com.kingdom.manager.operation.domain.model.Calendar;
import com.kingdom.manager.operation.domain.model.CalendarDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.config.SpringDataJackson3Configuration;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindCalendarTest {

	@Mock
	private CalendarIn in;

	@InjectMocks
	private FindCalendar findCalendar;

	@Test
	@DisplayName("Find Calendar With Year And Month Should Return Calendar")
	void findCalendar_withYearAndMonth_shouldReturnCalendar() {
		Integer year = 2026;
		Integer month = 8;
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

		when(in.find(year, month))
				.thenReturn(Optional.of(Calendar.builder()
						.year(year)
						.month(month)
						.addDate(prdAlignmentMeeting)
						.addDate(offsettingOfHours)
					.build()));

		Calendar result = findCalendar.resolve(year, month);

		assertEquals(year, result.year());
		assertEquals(month, result.month());
		assertEquals(List.of(prdAlignmentMeeting), result.dateBy(year, month, 10));
		assertEquals(List.of(offsettingOfHours), result.dateBy(year, month, 21));
	}

	@Test
	@DisplayName("Find Calendar With Year Less Then 1760 Should Return An Exception")
	void findCalendar_withYearLessThen1760_shouldReturnAnException() {
		Integer year = 1759;
		Integer month = 12;
		String throwMsg = "The specified year or month are invalid";

		RuntimeException e = assertThrows(RuntimeException.class, () -> findCalendar.resolve(year, month));

		assertEquals(throwMsg, e.getMessage());
	}

	@Test
	@DisplayName("Find Calendar With Year Biggest The Current Year Should Return An Exception")
	void findCalendar_withYearBiggestTheCurrentYear_shouldReturnAnException() {
		Integer year = LocalDate.now().getYear() + 1;
		Integer month = 12;

		String throwMsg = "The specified year or month are invalid";

		RuntimeException e = assertThrows(RuntimeException.class, () -> findCalendar.resolve(year, month));

		assertEquals(throwMsg, e.getMessage());
	}

	@Test
	@DisplayName("Find Calendar With Month Less Then One Should Return An Exception")
	void findCalendar_withMonthLessThenOne_shouldReturnAnException() {
		Integer year = 2026;
		Integer month = 0;
		String throwMsg = "The specified year or month are invalid";

		RuntimeException e = assertThrows(RuntimeException.class, () -> findCalendar.resolve(year, month));

		assertEquals(throwMsg, e.getMessage());
	}

	@Test
	@DisplayName("Find Calendar With Month Biggest Then Twelve Should Return An Exception")
	void findCalendar_withMonthBiggestThenTwelve_shouldReturnAnException() {
		Integer year = 2026;
		Integer month = 13;
		String throwMsg = "The specified year or month are invalid";

		RuntimeException e = assertThrows(RuntimeException.class, () -> findCalendar.resolve(year, month));

		assertEquals(throwMsg, e.getMessage());
	}

	@Test
	@DisplayName("Find Calendar When No Exists Should Return An Exception")
	void findCalendar_whenNoExists_shouldReturnAnException() {
		Integer year = 2026;
		Integer month = 9;
		String throwMsg = "The calendar for the specified year and month was not found";

		when(in.find(year, month))
				.thenReturn(Optional.empty());

		RuntimeException e = assertThrows(RuntimeException.class, () -> findCalendar.resolve(year, month));

		assertEquals(throwMsg, e.getMessage());
	}

}