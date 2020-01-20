/**
 * 
 */
package com.revolut.impl;

import java.sql.SQLException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.revolut.Person;

/**
 * @author navedshah
 *
 */
public class PersonImpl implements PersonService{
	
	Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
	private final JdbcTemplate jdbcTemplate;
	private final String GET_PERSON_SQL = "SELECT dateofbirth FROM person where first_name=?";
	private final String UPDATE_PERSON_SQL = "UPDATE Person SET dateOfBirth = ? WHERE first_name = ?";
	private final String INSERT_PERSON_SQL = "INSERT INTO PERSON (first_name, dateOfBirth) VALUES (?, ?);";
   
    private final String SELECT_COUNT = "SELECT COUNT(dateOfBirth) FROM PERSON WHERE first_name= ?";


	public PersonImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	 /**
	 * @param person
	 * @return
	 */
	public int insertDateOfBirth(Person person) throws SQLException
	 {
		LOGGER.info("IN:: insertDateOfBirth() : for name : "+person.getFirst_name());
		int update =  jdbcTemplate.update(INSERT_PERSON_SQL, new Object[]{person.getFirst_name(), person.getDateOfBirth()});
//		int  update = 0;
//		try {
//			 update =  jdbcTemplate.update(INSERT_PERSON_SQL, new Object[]{person.getFirst_name(), new SimpleDateFormat("yyyy-MM-dd").parse(person.getDateOfBirth())});
//		} catch (DataAccessException e) {
//		    LOGGER.error(e.getMessage());
//			
//		} catch (ParseException e) {
//			
//			LOGGER.error(e.getMessage());
//			
//		}
		return update;
		

	 }
	 
	/**
	 * @param person
	 * @return
	 */
	public int updateDateOfBirth(Person person) throws SQLException{

	
		int  insert =  jdbcTemplate.update(UPDATE_PERSON_SQL, new Object[] {person.getDateOfBirth(), person.getFirst_name() });
//		try {
//			 insert =  jdbcTemplate.update(UPDATE_PERSON_SQL, new Object[] {new SimpleDateFormat("yyyy-MM-dd").parse(person.getDateOfBirth()), person.getFirst_name() });
//		} catch (DataAccessException e) {
//		    LOGGER.error(e.getMessage());
//			
//		} catch (ParseException e) {
//			
//			LOGGER.error(e.getMessage());
//			
//		}
		return insert;
		

	}
	 
	 /**
	 * @param name
	 * @return
	 */
	public String getBDay(String name) throws SQLException, EmptyResultDataAccessException{
		 
		 return (String) jdbcTemplate.queryForObject(GET_PERSON_SQL, new Object[] { name }, String.class);
		 
	 }

	
	/**
	 *
	 */
	public int getCount(String name) throws SQLException {
		
		return Integer.parseInt((String)jdbcTemplate.queryForObject(SELECT_COUNT, new Object[]{name}, String.class));
		
	}

}
