package com.kingdom.manager.operation.application.port.in;

import com.kingdom.manager.operation.domain.model.Calendar;

import java.util.Optional;

public interface CalendarIn {

	Optional<Calendar> find(Integer year, Integer month);

}
