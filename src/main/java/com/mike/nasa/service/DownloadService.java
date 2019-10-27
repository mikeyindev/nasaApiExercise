package com.mike.nasa.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mike.nasa.model.Photo;

@Service
public class DownloadService {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(DownloadService.class);
	private static final String DOWNLOAD_FOLDER = System
			.getProperty("user.home") + "/Downloads/";

	public void downloadPhoto(Photo photo) {
		if (photo != null && photo.getImgSrc() != null
				&& photo.getImgSrc().getFile() != null) {
			String temp = photo.getImgSrc().getFile();
			// Get image name from last segment in URL
			String fileName = temp.substring(temp.lastIndexOf("/") + 1);
			String filePath = DOWNLOAD_FOLDER + fileName;
			LOGGER.debug("File path :: {}", filePath);
			BufferedImage image = null;
			try {
				// Doesn't work
//				FileUtils.copyURLToFile(httpsUrl, new File(filePath), 10000,
//						10000);
//				FileUtils.copyURLToFile(new URL(
//						"http://mars.nasa.gov/mer/gallery/all/1/p/4037/1P486568809EFFCNJDP2407L7M1-BR.JPG"),
//						new File(filePath), 10000, 10000);
				image = ImageIO.read(photo.getImgSrc());
				ImageIO.write(image, "jpg", new File(filePath));
				LOGGER.debug(image.toString());

//				InputStream in = new URL(
//						"https://mars.nasa.gov/mer/gallery/all/1/p/4037/1P486568809EFFCNJDP2407L7M1-BR.JPG")
//								.openStream();
//				Files.copy(in, Paths.get(filePath),
//						StandardCopyOption.REPLACE_EXISTING);
			} catch (IIOException e) {
				LOGGER.debug(e.getMessage());
				throw new IllegalArgumentException();
			} catch (IOException e1) {
				LOGGER.debug(e1.getMessage());
				throw new IllegalArgumentException();
			}
		}
	}
}
