package com.mike.nasa.util;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.lang3.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mike.nasa.model.Photo;

public class Utils {
	private static final Logger LOGGER = LoggerFactory.getLogger(Utils.class);

	public static void displayInBrowser(Photo photo) {
		Runtime runtime;
		String os = SystemUtils.OS_NAME;
		URI uri = null;
		try {
			uri = photo.getImgSrc().toURI();
		} catch (URISyntaxException e1) {
			LOGGER.debug("Failed to parse image URI :: {}", e1);
		}
		LOGGER.debug("Operating System :: {}", os);

		if (Desktop.isDesktopSupported()) {
			Desktop d = Desktop.getDesktop();
			try {
				d.browse(uri);
			} catch (IOException e) {
				LOGGER.debug("Failed to open browser :: {}", e);
			}
		} else if (os.contains("Windows")) {
			runtime = Runtime.getRuntime();
			try {
				runtime.exec("start " + uri);
			} catch (IOException e) {
				LOGGER.debug("Failed to execute command 'start uri':: {}", e);
			}
		} else if (os.contains("Mac")) {
			runtime = Runtime.getRuntime();
			try {
				runtime.exec("open " + uri);
			} catch (IOException e) {
				LOGGER.debug("Failed to execute command 'open uri':: {}", e);
			}
		} else {
			LOGGER.debug("OS is not supported.");
		}
	}
}
