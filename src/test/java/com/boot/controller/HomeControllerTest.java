package com.boot.controller;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HomeControllerTest {

	private HomeController controller;
	
	@Before
	public void setup() {
		controller = new HomeController();
	}
	
	@Test
	public void homecontroller_home_should_return_index_html() {
		String result = controller.home();
		assertThat(result, is("index.html"));
	}
}
