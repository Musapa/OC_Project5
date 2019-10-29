package com.openclassrooms.project5.domain;

import com.jsoniter.annotation.JsonProperty;

public class Firestation {

	private String address;
	private String station;

	public void setAddress(@JsonProperty("address") String address) {
		this.address = address;
	}

	public String getAddress() {
		return this.address;
	}

	public void setStation(@JsonProperty("station") String station) {
		this.station = station;
	}

	public String getStation() {
		return this.station;
	}
}
