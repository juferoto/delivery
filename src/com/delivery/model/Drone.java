package com.delivery.model;

import java.util.List;

public class Drone
{
	private String droneNumber;
	private List<Position> dronePositions;
	private List<String> rawInformation;

	public Drone() {
		super();
	}

	public Drone(String droneNumber, List<Position> dronePositions, List<String> rawInformation) {
		super();
		this.droneNumber = droneNumber;
		this.dronePositions = dronePositions;
		this.rawInformation = rawInformation;
	}

	public String getDroneNumber() {
		return droneNumber;
	}

	public void setDroneNumber(String droneNumber2) {
		this.droneNumber = droneNumber2;
	}

	public List<Position> getDronePositions() {
		return dronePositions;
	}

	public void setDronePositions(List<Position> dronePositions) {
		this.dronePositions = dronePositions;
	}

	public List<String> getRawInformation() {
		return rawInformation;
	}

	public void setRawInformation(List<String> rawInformation) {
		this.rawInformation = rawInformation;
	}
	
}
