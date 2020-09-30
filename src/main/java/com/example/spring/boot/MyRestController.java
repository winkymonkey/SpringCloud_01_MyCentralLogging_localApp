package com.example.spring.boot;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/v1/main")
public class MyRestController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	private static final String REMOTE_URL = "http://localhost:8091/remoteApp/person";
	
	private static final Logger LOGGER = LogManager.getLogger(MyRestController.class);
	
	
	/**
	 * ----------------------------------------------------
	 * URL: http://localhost:8080/v1/main/endpoint0
	 * ----------------------------------------------------
	 */
	@GetMapping("/endpoint0")
	public List<Person> endpoint0() {
		LOGGER.info("entering endpoint0");
		ResponseEntity<Person[]> response = restTemplate.getForEntity(REMOTE_URL, Person[].class);
		List<Person> personList = Arrays.asList(response.getBody());
		personList.forEach(person -> LOGGER.info("person="+person));
		return personList;
	}
	
}
