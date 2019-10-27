package com.mike.nasa.model;

import java.util.Arrays;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(cameras);
		result = prime * result + id;
		result = prime * result
				+ ((landingDate == null) ? 0 : landingDate.hashCode());
		result = prime * result
				+ ((launchDate == null) ? 0 : launchDate.hashCode());
		result = prime * result + ((maxDate == null) ? 0 : maxDate.hashCode());
		result = prime * result + (int) (maxSol ^ (maxSol >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + (int) (totalPhotos ^ (totalPhotos >>> 32));
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
		Rover other = (Rover) obj;
		if (!Arrays.equals(cameras, other.cameras))
			return false;
		if (id != other.id)
			return false;
		if (landingDate == null) {
			if (other.landingDate != null)
				return false;
		} else if (!landingDate.equals(other.landingDate))
			return false;
		if (launchDate == null) {
			if (other.launchDate != null)
				return false;
		} else if (!launchDate.equals(other.launchDate))
			return false;
		if (maxDate == null) {
			if (other.maxDate != null)
				return false;
		} else if (!maxDate.equals(other.maxDate))
			return false;
		if (maxSol != other.maxSol)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (totalPhotos != other.totalPhotos)
			return false;
		return true;
	}
}
