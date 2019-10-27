package com.mike.nasa.service;

import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class DateServiceTest {
	private String testFilePath = "";

	@InjectMocks
	private DateService dateService;

	@Test
	public void testReadDateFile() throws ParseException {

	}

	@Test
	public void testformatDate() {

	}

}
