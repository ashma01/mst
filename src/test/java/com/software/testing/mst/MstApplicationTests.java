package com.software.testing.mst;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.springframework.test.util.AssertionErrors.assertNotNull;

@SpringBootTest
class MstApplicationTests {

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	void contextLoads() {
		// Verify that the application context has loaded successfully
		assertNotNull(applicationContext, "The application context should have loaded.");
	}

}
