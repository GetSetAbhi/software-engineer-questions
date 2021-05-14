package com.code.model;

public class Employee {

	private String id;
	
	private String name;
	
	private String address;
	
	private String zip;

	public Employee(String id, String name, String address, String zip) {
		this.id = id;
		this.setName(name);
		this.setAddress(address);
		this.setZip(zip);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	
	/**
	 * Custom equals method to identify duplicate objects
	 * 
	 * **/
	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Employee) {
			Employee e2 = (Employee) obj;
			if (e2.getName().equals(this.name) 
					&& e2.getAddress().equals(this.address)
					&& e2.getZip().equals(this.zip)) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return (this.name + this.address + this.zip).hashCode();
	}
	
}
