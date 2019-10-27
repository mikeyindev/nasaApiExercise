package com.mike.nasa.model;

import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Photo {
	private long id;

	private long sol;

	private Camera camera;

	@JsonProperty("img_src")
	private URL imgSrc;

	@JsonProperty("earth_date")
	private String earthDate;

	private Rover rover;

	public Photo() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getSol() {
		return sol;
	}

	public void setSol(long sol) {
		this.sol = sol;
	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public URL getImgSrc() {
		return imgSrc;
	}

	public void setImgSrc(URL imgSrc) {
		this.imgSrc = replaceHttpWithHttps(imgSrc);
	}

	public String getEarthDate() {
		return earthDate;
	}

	public void setEarthDate(String earthDate) {
		this.earthDate = earthDate;
	}

	public Rover getRover() {
		return rover;
	}

	public void setRover(Rover rover) {
		this.rover = rover;
	}

	@Override
	public String toString() {
		return "Photo [id=" + id + ", sol=" + sol + ", camera=" + camera
				+ ", imgSrc=" + imgSrc + ", earthDate=" + earthDate + ", rover="
				+ rover + "]";
	}

	/**
	 * Replace http protocol with https. Download image over http will fail.
	 * 
	 * @param url
	 * @return
	 */
	private static URL replaceHttpWithHttps(URL url) {
		try {
			if (!url.toString().contains("https"))
				url = new URL(url.toString().replaceFirst("http", "https"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return url;
	}
}
