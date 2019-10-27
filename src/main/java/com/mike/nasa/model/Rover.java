package com.mike.nasa.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rover {
	private int id;
	private String name;
	@JsonProperty("landing_date")
	private String landingDate;

	@JsonProperty("launch_date")
	private String launchDate;

	private String status;

	@JsonProperty("max_sol")
	private long maxSol;

	@JsonProperty("max_date")
	private String maxDate;

	@JsonProperty("total_photos")
	private long totalPhotos;

	private Camera[] cameras;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLandingDate() {
		return landingDate;
	}

	public void setLandingDate(String landingDate) {
		this.landingDate = landingDate;
	}

	public String getLaunchDate() {
		return launchDate;
	}

	public void setLaunchDate(String launchDate) {
		this.launchDate = launchDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getMaxSol() {
		return maxSol;
	}

	public void setMaxSol(long maxSol) {
		this.maxSol = maxSol;
	}

	public String getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(String maxDate) {
		this.maxDate = maxDate;
	}

	public long getTotalPhotos() {
		return totalPhotos;
	}

	public void setTotalPhotos(long totalPhotos) {
		this.totalPhotos = totalPhotos;
	}

	public Camera[] getCameras() {
		return cameras;
	}

	public void setCameras(Camera[] cameras) {
		this.cameras = cameras;
	}
}
