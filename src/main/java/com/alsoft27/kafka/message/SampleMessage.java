package com.alsoft27.kafka.message;

public class SampleMessage {

	private int id;

	private String message;

	public SampleMessage() {
	}

	public SampleMessage(int id, String message) {
		this.id = id;
		this.message = message;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getId() {
		return this.id;
	}

	public String getMessage() {
		return this.message;
	}

	@Override
	public String toString() {
		return "SampleMessage{id=" + this.id + ", message='" + this.message + "'}";
	}

}
