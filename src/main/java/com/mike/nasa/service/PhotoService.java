package com.mike.nasa.service;

import java.util.Arrays;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mike.nasa.model.Photo;
import com.mike.nasa.model.PhotoList;

@Service
public class PhotoService {
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	private static final Random RANDOM = new Random();

	private static final String URI = "https://api.nasa.gov/mars-photos/api/v1/rovers/opportunity/photos";
	private static final String PANCAM = "PANCAM";
	private static final String DEMO_KEY = "DEMO_KEY";
//	private static final String DATE = "2015-6-3";
	private static final Logger LOGGER = LoggerFactory
			.getLogger(PhotoService.class);

	private HttpHeaders headers;
	private HttpEntity<String> entity;
	private UriComponentsBuilder builder;
	private PhotoList photoList;

	public PhotoService() {
		headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		entity = new HttpEntity<>(headers);
	}

//	public Photo getPhoto(long id) {
//
//		return null;
//	}

	/**
	 * Return a random photo from a given date.
	 * 
	 * @return
	 */
	public Photo getRandomPhoto(String date) {
		photoList = getAllPhotos(date);
		Photo randomPhoto = null;
		if (photoList != null && photoList.getPhotos() != null) {
			Photo[] photos = photoList.getPhotos();
			if (photos.length == 0) {
				throw new IllegalArgumentException(
						"No photos available for the requested date " + date
								+ ". You may want to try other dates.");
			}
			randomPhoto = photos[RANDOM.nextInt(photos.length)];
			LOGGER.debug("Random photo picked :: {}", randomPhoto);
		}
		return randomPhoto;
	}

	/**
	 * Fetch all PANCAM photos from a given date.
	 * 
	 * @param date
	 * @return
	 */
	public PhotoList getAllPhotos(String date) {
		builder = UriComponentsBuilder.fromHttpUrl(URI)
				.queryParam("earth_date", date).queryParam("camera", PANCAM)
				.queryParam("api_key", DEMO_KEY);
		String uriString = builder.toUriString();
		LOGGER.debug("Fetching photos from URI :: {}", uriString);
		ResponseEntity<String> result = restTemplate.exchange(uriString,
				HttpMethod.GET, entity, String.class);
		if (result != null) {
			LOGGER.debug("Response body :: {}", result.getBody());
			try {
				photoList = objectMapper.readValue(result.getBody(),
						PhotoList.class);
				LOGGER.debug("PhotoList :: {}", photoList);
			} catch (Exception e) {
				LOGGER.debug("Exception :: {}", e);
			}
		}
		return photoList;
	}

	public PhotoList getPhotoList() {
		return photoList;
	}

	public void setPhotoList(PhotoList photoList) {
		this.photoList = photoList;
	}
}
