package com.kingdom.manager.operation.domain.model;

import java.time.LocalDateTime;

public class TimesheetRegister {

	private final String title;
	private final String note;
	private final String document;
	private final LocalDateTime start;
	private final LocalDateTime end;

	protected TimesheetRegister() {
		this.title = String.valueOf("");
		this.note = String.valueOf("");
		this.document = String.valueOf("");
		this.start = null;
		this.end = null;
	}

	public TimesheetRegister(String title, String note, String document, LocalDateTime start, LocalDateTime end) {
		this.title = title;
		this.note = note;
		this.document = document;
		this.start = start;
		this.end = end;
	}

	public static TimesheetRegisterBuilder builder() {
		return new TimesheetRegisterBuilder();
	}

	public String title() {
		return title;
	}

	public String note() {
		return note;
	}

	public String document() {
		return document;
	}

	public LocalDateTime start() {
		return start;
	}

	public LocalDateTime end() {
		return end;
	}

	public static class TimesheetRegisterBuilder {

		private String title;
		private String note;
		private String document;
		private LocalDateTime start;
		private LocalDateTime end;

		public TimesheetRegisterBuilder() {
			this.title = String.valueOf("");
			this.note = String.valueOf("");
			this.document = String.valueOf("");
			this.start = null;
			this.end = null;
		}

		public TimesheetRegisterBuilder title(String title) {
			this.title = title;
			return this;
		}

		public TimesheetRegisterBuilder note(String note) {
			this.note = note;
			return this;
		}

		public TimesheetRegisterBuilder document(String document) {
			this.document = document;
			return this;
		}

		public TimesheetRegisterBuilder start(LocalDateTime start) {
			this.start = start;
			return this;
		}

		public TimesheetRegisterBuilder end(LocalDateTime end) {
			this.end = end;
			return this;
		}

		public TimesheetRegister build() {
			return new TimesheetRegister(title, note, document, start, end);
		}

	}

}
