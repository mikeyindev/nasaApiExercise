package com.mike.nasa.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.mike.nasa.model.Photo;
import com.mike.nasa.service.DateService;
import com.mike.nasa.service.DownloadService;
import com.mike.nasa.service.PhotoService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class NasaControllerTest {
	@LocalServerPort
	private int port;

	// This is so we can use Mockito without calling MockitoJUnitRunner in
	// RunWith()
	@Rule
	public MockitoRule rule = MockitoJUnit.rule();

	@Autowired
	private NasaController nasaController;

	@MockBean
	private DateService dateServiceMock;

	@MockBean
	private DownloadService downloadServiceMock;

	@Autowired
	private PhotoService photoService;

	@Autowired
	private TestRestTemplate testRestTemplate;

	private static final String HOST = "http://localhost:";
	private String downloadDisplayUrl;
	private String downloadUrl;
	private String displayUrl;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(NasaControllerTest.class);

	@Before
	public void setUp() {
		downloadDisplayUrl = HOST + port + "/api/photo";
		downloadUrl = HOST + port + "/api/photo/download";
		displayUrl = HOST + port + "/api/photo/display";
		LOGGER.debug("Port :: {}", port);
		LOGGER.debug("Download URL :: {}", downloadUrl);

		Set<String> testDates = new HashSet<>();
		testDates.add("2017-02-27");
		when(dateServiceMock.readDateFile(anyString())).thenReturn(testDates);
		doNothing().when(downloadServiceMock).downloadPhoto(any(Photo.class));
	}

	@Test
	public void testDownloadRandomPhoto() {
		ResponseEntity<String> testResponse = testRestTemplate
				.getForEntity(downloadUrl, String.class);
		assertTrue(testResponse.getStatusCode().is2xxSuccessful());
		assertNotNull(photoService.getPhotoList());
		verify(downloadServiceMock, times(1)).downloadPhoto(any(Photo.class));
	}

	/**
	 * NOTE: This test will actually open new tabs in the default browser.
	 */
	@Test
	public void testDisplayRandomPhoto() {
		ResponseEntity<String> testResponse = testRestTemplate
				.getForEntity(displayUrl, String.class);
		assertTrue(testResponse.getStatusCode().is2xxSuccessful());
		// Download service is not called
		verify(downloadServiceMock, times(0)).downloadPhoto(any(Photo.class));
	}

	/**
	 * NOTE: This test will actually open new tabs in the default browser.
	 */
	@Test
	public void testDownloadAndDisplayRandomPhoto() {
		ResponseEntity<String> testResponse = testRestTemplate
				.getForEntity(downloadDisplayUrl, String.class);
		assertTrue(testResponse.getStatusCode().is2xxSuccessful());
		verify(downloadServiceMock, times(1)).downloadPhoto(any(Photo.class));
	}
}
