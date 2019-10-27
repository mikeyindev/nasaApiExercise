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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((camera == null) ? 0 : camera.hashCode());
		result = prime * result
				+ ((earthDate == null) ? 0 : earthDate.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((imgSrc == null) ? 0 : imgSrc.hashCode());
		result = prime * result + ((rover == null) ? 0 : rover.hashCode());
		result = prime * result + (int) (sol ^ (sol >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Photo other = (Photo) obj;
		if (camera == null) {
			if (other.camera != null)
				return false;
		} else if (!camera.equals(other.camera))
			return false;
		if (earthDate == null) {
			if (other.earthDate != null)
				return false;
		} else if (!earthDate.equals(other.earthDate))
			return false;
		if (id != other.id)
			return false;
		if (imgSrc == null) {
			if (other.imgSrc != null)
				return false;
		} else if (!imgSrc.equals(other.imgSrc))
			return false;
		if (rover == null) {
			if (other.rover != null)
				return false;
		} else if (!rover.equals(other.rover))
			return false;
		if (sol != other.sol)
			return false;
		return true;
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
