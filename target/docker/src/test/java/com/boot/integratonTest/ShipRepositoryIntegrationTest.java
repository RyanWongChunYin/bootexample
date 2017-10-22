package com.boot.integratonTest;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.boot.model.Shipwreck;
import com.boot.repository.ShipwreckRepository;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ShipRepositoryIntegrationTest {

	@Resource
	private ShipwreckRepository shipwreckRepository;
	
	@Test
	public void repo_test_findAll() {
		List<Shipwreck> wrecks = shipwreckRepository.findAll();
		assertThat(wrecks.size(), is(greaterThanOrEqualTo(0)));
		assertThat(wrecks, is(notNullValue()));
	}
}
