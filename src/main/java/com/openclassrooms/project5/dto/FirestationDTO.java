package com.openclassrooms.project5.dto;

public class FirestationDTO {

	private String address;
	private String station;

	public FirestationDTO() {

	}

	public FirestationDTO(String address, String station) {
		this.address = address;
		this.station = station;
	}

	public String getAddress() {
		return address;
	}

	public String getStation() {
		return station;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setStation(String station) {
		this.station = station;
	}

}
