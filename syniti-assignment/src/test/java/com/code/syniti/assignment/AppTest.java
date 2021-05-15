package com.code.syniti.assignment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.code.model.Employee;

/**
 * Unit test for simple App.
 */

public class AppTest 
{
	
	private App app;
	
	@Before
    public void setUp() throws Exception {
        app = new App();
    }
	
	@Test
	public void testIsValidZip_validValue() {
		String zipCode = "99501";
		assertTrue(app.isValidZip(zipCode));
	}
	
	@Test
	public void testIsValidZip_invalidValue() {
		String zipCode = "123456789";
		assertFalse(app.isValidZip(zipCode));
	}
	
	@Test
	public void testCheckZipCode_validValue() {
		String zipCode = "99501";
		assertTrue(app.checkZipCode(zipCode));
	}
	
	@Test
	public void testCheckZipCode_withNullValue() {
		String zipCode = null;
		assertFalse(app.checkZipCode(zipCode));
	}
	
	@Test
	public void testCheckZipCode_withEmptyValue() {
		String zipCode = "";
		assertFalse(app.checkZipCode(zipCode));
	}
	
	@Test
	public void testCheckValidity_validInput() {
		String input = "abcde";
		assertTrue(app.checkValidity(input));
	}
	
	@Test
	public void testCheckValidity_nullInput() {
		String input = null;
		assertFalse(app.checkValidity(input));
	}
	
	@Test
	public void testCheckValidity_emptyInput() {
		String input = "";
		assertFalse(app.checkValidity(input));
	}
	
	@Test(expected = FileNotFoundException.class)	
	public void testGetEmployeesFromFile_NoExistingFile() throws FileNotFoundException {
		String fileName = "nofile.json";
		app.getEmployeesFromFile(fileName);
	}
	
	@Test(expected = NullPointerException.class)
	public void testGetEmployeesFromFile_EmptyFile() throws FileNotFoundException {
		String fileName = "empty-file-test.json";
		app.getEmployeesFromFile(fileName);
	}
	
	@Test
	public void testGetEmployeesFromFile_ValidFile() throws FileNotFoundException {
		String fileName = "test-data.json";
		Employee[] employees = app.getEmployeesFromFile(fileName);
		assertNotNull(employees);
		assertEquals(7, employees.length);
	}
	
	@Test
	public void testInvalidEmployeeIds_NullInput() {
		List<String> output = app.invalidEmployeeIds(null);
		assertEquals(0, output.size());
	}
	
	@Test
	public void testInvalidEmployeeIds_ValidInput() throws FileNotFoundException, NullPointerException {
		String fileName = "test-data.json";
		Employee[] employees = app.getEmployeesFromFile(fileName);
		List<String> output = app.invalidEmployeeIds(employees);
		assertEquals(5, output.size());
	}
}
