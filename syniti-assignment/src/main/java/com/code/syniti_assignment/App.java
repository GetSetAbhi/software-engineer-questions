package com.code.syniti_assignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.code.model.Employee;
import com.google.gson.Gson;

public class App {
	
	/**
	 * Checks whether a zip code is
	 * valid USA Zip Code
	 * 
	 * A REGEX is used to identify valid zip code
	 * **/
	public boolean isValidZip(String zipCode) {
		String USA_CODE_EXP = "^[0-9]{5}(?:-[0-9]{4})?$";
		Pattern pattern = Pattern.compile(USA_CODE_EXP);
		Matcher matcher = pattern.matcher(zipCode);
		return matcher.matches();
	}
	
	public boolean checkZipCode(String zipCode) {
		if (!checkValidity(zipCode) || !isValidZip(zipCode)) {
			return false;
		}
		return true;
	}
	
	/**
	 * Common method to check validity of Employee Name and Address
	 * **/
	public boolean checkValidity(String input) {
		if (input == null || input.equals("")) {
			return false;
		}
		return true;
	}

	public void execute() throws IOException, Exception {
		Gson gson = new Gson();

		BufferedReader br = new BufferedReader(new FileReader("data.json"));

		Employee[] employees = gson.fromJson(br, Employee[].class);
		
		List<Employee> list = new ArrayList<>();
		for (Employee emp : employees) {
			
			/**
			 * First check if employee details are valid.
			 * If any of the details are invalid then print
			 * the employee id.
			 * 
			 * If employee details are valid but the employee is 
			 * already present in the list, then the current employee object
			 * is duplicate and then print its id.
			 * 
			 * equals() method is overridden in Employee.class
			 * to check if two employees are equal
			 * **/
			if (checkValidity(emp.getName()) 
					&& checkValidity(emp.getAddress()) 
					&& checkZipCode(emp.getZip())) {
				if (!list.contains(emp)) {
					list.add(emp);
				} else {
					System.out.println(emp.getId());
				}
			} else {
				System.out.println(emp.getId());
			}
		}
	}

	public static void main(String[] args) {
		try {
			new App().execute();
		} catch (IOException e) {
			System.out.println(e);
		} catch (Exception e1) {
			System.out.println(e1);
		}
	}
}
