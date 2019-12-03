package com.openclassrooms.project5.dto;

import java.util.ArrayList;
import java.util.List;

import com.openclassrooms.project5.domain.Person;

public class ChildAlert {

	private List<Person> children;
	private List<Person> adults;

	public ChildAlert() {
		this.children = new ArrayList<>();
		this.adults = new ArrayList<>();
	}

	public List<Person> getChildren() {
		return children;
	}

	public List<Person> getAdults() {
		return adults;
	}

	public void setChildren(List<Person> children) {
		this.children = children;
	}

	public void setAdults(List<Person> adults) {
		this.adults = adults;
	}

}
