package com.boot.integratonTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.boot.App;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;

/*
 *  this test is expensive to run, so run it on continuous server
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={App.class},webEnvironment = WebEnvironment.DEFINED_PORT)
public class ShipControllerWebIntegrationTest {
	@LocalServerPort
    int randomServerPort;
	
	//url pointed to the test environment
	private String testUrl = "http://localhost:8181";
	private RestTemplate restTemplate;
	private ObjectMapper objectMapper;
	@Before
	public void setup() {
		restTemplate = new RestTemplate();
		objectMapper = new ObjectMapper();
	}
	
	@Test
	public void Test_ListAll_is_called_api_and_status_is_200() {
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8181/api/v1/shipwrecks", String.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		
	}
	
	
}
