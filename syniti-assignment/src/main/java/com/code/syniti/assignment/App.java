package com.code.syniti.assignment;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.code.model.Employee;
import com.google.gson.Gson;

/**
 * Test cases for this file are in AppTest.java file
 * **/
public class App {
	
	/**
	 * Checks whether a zip code is
	 * valid USA Zip Code
	 * 
	 * A REGEX is used to identify valid zip code
	 * **/
	protected boolean isValidZip(String zipCode) {
		String USA_CODE_EXP = "^[0-9]{5}(?:-[0-9]{4})?$";
		Pattern pattern = Pattern.compile(USA_CODE_EXP);
		Matcher matcher = pattern.matcher(zipCode);
		return matcher.matches();
	}
	
	protected boolean checkZipCode(String zipCode) {
		if (!checkValidity(zipCode) || !isValidZip(zipCode)) {
			return false;
		}
		return true;
	}
	
	/**
	 * Common method to check validity of Employee Name and Address
	 * **/
	protected boolean checkValidity(String input) {
		if (input == null || input.equals("")) {
			return false;
		}
		return true;
	}
	
	protected Employee[] getEmployeesFromFile(String fileName) throws FileNotFoundException, NullPointerException {
		Gson gson = new Gson();

		BufferedReader br = new BufferedReader(new FileReader(fileName));

		Employee[] employees = gson.fromJson(br, Employee[].class);
		
		if (employees == null) {
			throw new NullPointerException("File has no content");
		}
		
		return employees;
	}
	
	protected List<String> invalidEmployeeIds(Employee[] employees) {
		List<String> invalidList = new ArrayList<>();
		
		if (employees != null) {
			List<Employee> emplist = new ArrayList<>();
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
					if (!emplist.contains(emp)) {
						emplist.add(emp);
					} else {
						invalidList.add(emp.getId());
					}
				} else {
					invalidList.add(emp.getId());
				}
			}
		}
		
		return invalidList;
	}

	protected void execute() {

		String file = "data.json";
		Employee[] employees = null;
		
		try {
			employees = getEmployeesFromFile(file);
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (NullPointerException e1) {
			System.out.println(e1);
		}
		
		if (employees != null) {
			List<String> invalidList = invalidEmployeeIds(employees);
			for (String id : invalidList) {
				System.out.println(id);
			}
		}
	}

	public static void main(String[] args) {
		new App().execute();
	}
}
