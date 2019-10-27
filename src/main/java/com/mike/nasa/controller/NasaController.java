package com.mike.nasa.controller;

import java.util.Set;

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
	private PhotoService photoService;

	@Autowired
	private DateService dateService;

	private Set<String> dates;

	/**
	 * Both download and display photos
	 */
	@GetMapping("/photo")
	public void downloadAndDisplayRandomPhoto() {
		dates = dateService.readDateFile();
		for (String date : dates) {
			Photo photo = photoService.getRandomPhoto(date);
			DownloadService.download(photo);
			Utils.displayInBrowser(photo);
		}
	}

	/**
	 * Only download photos
	 */
	@GetMapping("/photo/download")
	public void downloadRandomPhoto() {
		dates = dateService.readDateFile();
		for (String date : dates) {
			Photo photo = photoService.getRandomPhoto(date);
			DownloadService.download(photo);
		}
	}

	/**
	 * Only display photos
	 * 
	 * @return
	 */
	@GetMapping("/photo/display")
	public void displayRandomPhoto() {
		dates = dateService.readDateFile();
		for (String date : dates) {
			Photo photo = photoService.getRandomPhoto(date);
			Utils.displayInBrowser(photo);
		}
	}
}
