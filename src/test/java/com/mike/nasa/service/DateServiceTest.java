package com.mike.nasa.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DateServiceTest {
	private static final String TEST_FILE_PATH = "src/test/resources/imageDates.txt";
	private static final String TEST_DATE_1 = "2016-07-13";
	private static final String TEST_DATE_2 = "2017-02-27";
	private static final String TEST_DATE_3 = "2018-06-02";
	private static final String TEST_DATE_4 = "2018-05-01";

	@InjectMocks
	private DateService dateServiceMock;

	@Test
	public void testReadDateFile() {
		Set<String> dates = dateServiceMock.readDateFile(TEST_FILE_PATH);
		assertNotNull(dates);
		assertTrue(dates.size() == 4);
		assertTrue(dates.contains(TEST_DATE_1));
		assertTrue(dates.contains(TEST_DATE_2));
		assertTrue(dates.contains(TEST_DATE_3));
		assertTrue(dates.contains(TEST_DATE_4));
	}

	@Test
	public void testCorrectFormatDate() {
		String formattedDate = DateService.formatDate("Jan 1, 2040");
		assertEquals("2040-01-01", formattedDate);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIncorrectFormatDate() {
		DateService.formatDate("31-01-2040");
	}
}
