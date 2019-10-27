package com.mike.nasa.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DateService {
	private static final DateFormat OUTPUT_DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd");
	private static final String[] INPUT_DATE_FORMATS = { "MM/dd/yy",
			"MMM d, yyyy", "MMM-dd-yyyy" };
	private static final Logger LOGGER = LoggerFactory
			.getLogger(DateService.class);
	private static final String FILE_PATH = System.getProperty("user.home")
			+ "/Downloads/imageDates.txt";
	// Using set to avoid downloading/displaying duplicate photos
	private Set<String> dates = new HashSet<>();

	public Set<String> readDateFile() {

		try (Stream<String> stream = Files.lines(Paths.get(FILE_PATH))) {
			dates = stream.collect(Collectors.toSet());
		} catch (IOException e) {
			LOGGER.debug("Error reading date file :: {}", e);
		}
		dates = dates.stream().map(DateService::formatDate)
				.collect(Collectors.toSet());
		LOGGER.debug("Formatted dates :: {}", dates);
		return dates;
	}

	public static String formatDate(String dateStr) {
		String formattedDate = null;
		try {
			Date oldDate = DateUtils.parseDate(dateStr, INPUT_DATE_FORMATS);
			formattedDate = OUTPUT_DATE_FORMAT.format(oldDate);
		} catch (ParseException e) {
			LOGGER.debug("Error parsing date " + dateStr
					+ ". Input date may be invalid :: {}", e);
		}
		return formattedDate;
	}
}
