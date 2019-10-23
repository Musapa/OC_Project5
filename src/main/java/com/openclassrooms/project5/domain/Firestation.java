package com.openclassrooms.project5.domain;

import com.jsoniter.annotation.JsonProperty;
import com.jsoniter.annotation.JsonWrapper;

public class Firestation {

	private String address;
	private int station;

	@JsonWrapper
	public void setAddress(@JsonProperty("address") String address) {
		this.address = address;
	}

	public String getAddress() {
		return this.address;
	}

	@JsonWrapper
	public void setStation(@JsonProperty("station") int station) {
		this.station = station;
	}

	public int getStation() {
		return this.station;
	}
}
