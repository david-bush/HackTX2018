package com.hacktx.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "flight")
public class Flight {
	private String depatureTime;
	private String flightNumber;
	private String origin;
	private String aircraftType;
	private String arrivalTime;
	private String cost;
	private String destination;
	private String flightStatus;
	
	public Flight(String depatureTime, String flightNumber, String origin, String aircraftType, String arrivalTime,
			String cost, String destination, String flightStatus) {
		super();
		this.depatureTime = depatureTime;
		this.flightNumber = flightNumber;
		this.origin = origin;
		this.aircraftType = aircraftType;
		this.arrivalTime = arrivalTime;
		this.cost = cost;
		this.destination = destination;
		this.flightStatus = flightStatus;
	}

	public String getDepatureTime() {
		return depatureTime;
	}

	public void setDepatureTime(String depatureTime) {
		this.depatureTime = depatureTime;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getAircraftType() {
		return aircraftType;
	}

	public void setAircraftType(String aircraftType) {
		this.aircraftType = aircraftType;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getFlightStatus() {
		return flightStatus;
	}

	public void setFlightStatus(String flightStatus) {
		this.flightStatus = flightStatus;
	}
	
}
