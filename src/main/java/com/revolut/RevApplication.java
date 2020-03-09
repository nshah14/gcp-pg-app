package com.revolut;

import java.sql.SQLException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revolut.impl.PersonImpl;
import com.revolut.impl.PersonService;

@SpringBootApplication
@RestController
public class RevApplication {
	Logger LOGGER = LoggerFactory.getLogger(RevApplication.class);

	private final JdbcTemplate jdbcTemplate;

	public RevApplication(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(RevApplication.class, args);
	}

	/**
	 * @param name
	 * @param person
	 * @return
	 */
	@PutMapping("/hello/{id}")
	public String insertAndUpdateDateOfBirth(@PathVariable(value = "id") String name,
			@Valid @RequestBody Person person) {
		System.out.println("bodyy  " + person.getDateOfBirth());

		LOGGER.info("IN:: insertAndUpdateDateOfBirth() : for name : " + name + " for date of birth :"
				+ person.getDateOfBirth());
		PersonService pService = new PersonImpl(jdbcTemplate);
		person.setFirst_name(name);

		RevUtils vUtils = new RevUtils();
		String vmsg = vUtils.validate(person);
		int count = 0;
		if (vmsg != "None") {
			return vmsg;
		}
		try {

			if (pService.getCount(name) == 0) {

				count = pService.insertDateOfBirth(person);

			} else {

				count = pService.updateDateOfBirth(person);

			}

		} catch (SQLException e) {

			e.printStackTrace();
			LOGGER.error("ERROR :: insertAndUpdateDateOfBirth() : for name : " + e.getMessage());
		}

		if (count == 0) {
			return HttpStatus.NOT_ACCEPTABLE.toString();
		}
		return HttpStatus.NO_CONTENT.toString();
	}

	/**
	 * @param name
	 * @return
	 */
	@GetMapping("/hello/{id}")
	private String getDateOfBirth(@PathVariable(value = "id") String name) throws SQLException {

		LOGGER.info("IN:: getDays()");

		String birthday = "";
		PersonService pService = new PersonImpl(jdbcTemplate);
		try {
			birthday = pService.getBDay(name);
		} catch (EmptyResultDataAccessException e) {

			LOGGER.error("ERROR: getDateOfBirth :: error msg : " + e.getMessage());
			return "INVALID REQUEST :: User doesnt exist";
		} catch (SQLException e) {
			LOGGER.error("ERROR: getDateOfBirth :: error msg : " + e.getMessage());
			throw new SQLException(e.getMessage());
		}

		RevUtils vUtils = new RevUtils();
		if (vUtils.isString(name))
			return vUtils.getBirthDayMsg(birthday, name);
		else
			return "Accepts only String";
	}
}