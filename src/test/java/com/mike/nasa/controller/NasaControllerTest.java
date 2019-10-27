package com.mike.nasa.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.mike.nasa.service.DateService;
import com.mike.nasa.service.DownloadService;
import com.mike.nasa.service.PhotoService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class NasaControllerTest {
	@InjectMocks
	NasaController nasaControllerMock;

	@Mock
	DateService dateServiceMock;

	@Mock
	DownloadService downloadServiceMock;

	@Mock
	PhotoService photoServiceMock;

	@Test
	public void testDownloadRandomPhoto() {

	}

	@Test
	public void testDisplayRandomPhoto() {

	}

	@Test
	public void testDownloadAndDisplayRandomPhoto() {

	}
}
