package com.alsoft27.kafka.message;

public class SampleMessage {

	private int id;

	private String name;

	private String lastname;

	private double cant;

	public SampleMessage() {
	}

	public SampleMessage(int id, String name, String lastname, double cant) {
		super();
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.cant = cant;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public double getCant() {
		return cant;
	}

	public void setCant(double cant) {
		this.cant = cant;
	}

	@Override
	public String toString() {
		return "SampleMessage{id=" + this.id + ", name='" + this.name + "', lastname='" + this.lastname + "', cant="
				+ this.cant + "}";
	}

}
