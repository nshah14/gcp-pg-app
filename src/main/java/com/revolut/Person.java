/**
 * 
 */
package com.revolut;


import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author navedshah
 *
 */
public class Person {
	
	private String first_name;

	@JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateOfBirth;
	
	public Person(String first_name, LocalDate dateOfBirth) {
		this.first_name = first_name;
		this.dateOfBirth = dateOfBirth;
	}

	
	
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public LocalDate getDateOfBirth() {
		
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		System.out.println("date of brith "+dateOfBirth);
		this.dateOfBirth = dateOfBirth;
	}
	
}
