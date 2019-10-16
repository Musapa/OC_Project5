package com.openclassrooms.project5.domain;


import com.jsoniter.annotation.JsonWrapper;
import com.jsoniter.annotation.JsonProperty;

public class Person {
	private String  firstName;
	
	@JsonWrapper
    public void setFirstName(@JsonProperty("firstName") String firstName) {
		this.firstName = firstName;
    }
    public String getFirstName() {
		return this.firstName;
    }
}
