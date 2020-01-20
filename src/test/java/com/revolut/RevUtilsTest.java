/**
 * 
 */
package com.revolut;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



/**
 * @author navedshah
 *
 */
class RevUtilsTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	
	
	@Test
	void testValidateDate() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		String date = "1999-10-10";
	
		LocalDate localDate = LocalDate.parse(date, formatter);
		Person person = new Person("john", localDate);
		RevUtils utils = new RevUtils();
		assertEquals("None", utils.validate(person));
		
	
	}
	@Test
	void testValidateBirthYear() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		String date = "2021-10-10";
	
		LocalDate localDate = LocalDate.parse(date, formatter);
		Person person = new Person("john", localDate);
		RevUtils utils = new RevUtils();
		assertEquals("Not a valid birth date", utils.validate(person));
		
	
	}
	@Test
	void testValidateBirthMonth() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		String date = "2021-02-10";
	
		LocalDate localDate = LocalDate.parse(date, formatter);
		Person person = new Person("john", localDate);
		RevUtils utils = new RevUtils();
		assertEquals("Not a valid birth date", utils.validate(person));
		
	
	}
	
	@Test
	void testValidateBirthDay() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		String date = "2021-02-20";
	
		LocalDate localDate = LocalDate.parse(date, formatter);
		Person person = new Person("john", localDate);
		RevUtils utils = new RevUtils();
		assertEquals("Not a valid birth date", utils.validate(person));
		
	
	}

}
