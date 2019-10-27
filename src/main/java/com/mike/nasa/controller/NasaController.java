package com.mike.nasa.controller;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mike.nasa.model.Photo;
import com.mike.nasa.service.DateService;
import com.mike.nasa.service.DownloadService;
import com.mike.nasa.service.PhotoService;
import com.mike.nasa.util.Utils;

@RestController
@RequestMapping("/api")
public class NasaController {
	@Autowired
	private DateService dateService;

	@Autowired
	private DownloadService downloadService;

	@Autowired
	private PhotoService photoService;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(NasaController.class);

	private static final String FILE_PATH = System.getProperty("user.home")
			+ "/Downloads/imageDates.txt";

	Set<String> dates;

	/**
	 * Both download and display photos
	 */
	@GetMapping("/photo")
	public void downloadAndDisplayRandomPhoto() {
		dates = dateService.readDateFile(FILE_PATH);
		processRequest(dates, true, true);
	}

	/**
	 * Only download photos
	 */
	@GetMapping("/photo/download")
	public void downloadRandomPhoto() {
		dates = dateService.readDateFile(FILE_PATH);
		processRequest(dates, true, false);
	}

	/**
	 * Only display photos
	 * 
	 * @return
	 */
	@GetMapping("/photo/display")
	public void displayRandomPhoto() {
		dates = dateService.readDateFile(FILE_PATH);
		processRequest(dates, false, true);
	}

	private void processRequest(Set<String> dates, boolean download,
			boolean display) {
		for (String date : dates) {
			LOGGER.debug("Processing request for photo taken on {}", date);
			Photo photo = photoService.getRandomPhoto(date);
			LOGGER.debug("Random photo selected :: {}", photo);
			if (download)
				downloadService.downloadPhoto(photo);
			if (display)
				Utils.displayInBrowser(photo);
		}
	}
}
