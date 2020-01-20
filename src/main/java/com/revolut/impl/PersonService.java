/**
 * 
 */
package com.revolut.impl;

import java.sql.SQLException;

import org.springframework.dao.EmptyResultDataAccessException;

import com.revolut.Person;

/**
 * @author navedshah
 *
 */
public interface PersonService{

	
	/**
	 * @param person
	 * @return
	 * @throws SQLException
	 */
	public int insertDateOfBirth(Person person) throws SQLException;
	/**
	 * @param person
	 * @return
	 * @throws SQLException
	 */
	public int updateDateOfBirth(Person person) throws SQLException;
	/**
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	public String getBDay(String name) throws SQLException;
	
	
	/**
	 * @return
	 * @throws SQLException, EmptyResultDataAccessException
	 */
	public int getCount(String name) throws SQLException, EmptyResultDataAccessException;
}
