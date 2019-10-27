package com.mike.nasa.service;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.mike.nasa.model.Photo;

@RunWith(MockitoJUnitRunner.class)
public class DownloadServiceTest {
	@InjectMocks
	private DownloadService downloadServiceMock;

	private Photo photo;
	private static final String TEST_EARTH_DATE = "2019-10-27";
	private URL testImgSrc;

	@Before
	public void setUp() throws MalformedURLException {
		photo = new Photo();
		testImgSrc = new URL("https://test.net/test.jpg");
		photo.setImgSrc(testImgSrc);
		photo.setEarthDate(TEST_EARTH_DATE);
	}

	@Test
	public void testSuccessfulDownload() {
		// Tested by integration tests
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFailedDownload() {
		downloadServiceMock.downloadPhoto(photo);
	}
}
