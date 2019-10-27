package com.mike.nasa.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mike.nasa.model.Photo;
import com.mike.nasa.model.PhotoList;

@RunWith(MockitoJUnitRunner.class)
public class PhotoServiceTest {
	@InjectMocks
	private PhotoService photoServiceMock;

	@Mock
	private ObjectMapper objectMapperMock;

	@Mock
	private RestTemplate restTemplateMock;

	private static final String TEST_DATE = "2040-01-01";

	private static final Photo TEST_PHOTO_1 = new Photo();

	private static final Photo TEST_PHOTO_2 = new Photo();

	private static final Photo TEST_PHOTO_3 = new Photo();

	private PhotoList testPhotoList;

	private Photo[] testPhotos;

	@Before
	public void setUp() throws JsonMappingException, JsonProcessingException {
		testPhotoList = new PhotoList();
		testPhotos = new Photo[3];
		testPhotos[0] = TEST_PHOTO_1;
		testPhotos[1] = TEST_PHOTO_2;
		testPhotos[2] = TEST_PHOTO_3;
		testPhotoList.setPhotos(testPhotos);

		ResponseEntity<String> testResponse = new ResponseEntity<>(
				"Test Response", HttpStatus.OK);
		when(objectMapperMock.readValue(anyString(), eq(PhotoList.class)))
				.thenReturn(testPhotoList);
		when(restTemplateMock.exchange(anyString(), any(),
				any(HttpEntity.class), eq(String.class)))
						.thenReturn(testResponse);
	}

	@Test
	public void testGetRandomPhoto() {
		Photo photo = photoServiceMock.getRandomPhoto(TEST_DATE);
		assertNotNull(photo);
	}

	@Test
	public void testGetAllPhotos() {
		PhotoList testPhotoList = photoServiceMock.getAllPhotos(TEST_DATE);
		assertNotNull(testPhotoList.getPhotos());
		assertTrue(testPhotoList.getPhotos().length == 3);
	}
}
