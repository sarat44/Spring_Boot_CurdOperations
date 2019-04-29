package com.example.demo.entitys;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "Employee_Tab")
@Entity
public class Employee {

	@Id
	private Integer id;
	private String name;
	private String place;

	public Employee(Integer id, String name, String place) {
		this.id = id;
		this.name = name;
		this.place = place;
	}

	public Employee() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", place=" + place + "]";
	}
}
