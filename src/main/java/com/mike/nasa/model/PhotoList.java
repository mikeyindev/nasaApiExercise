package com.mike.nasa.model;

import java.util.Arrays;

public class PhotoList {
	public PhotoList() {
	}

	private Photo[] photos;

	public Photo[] getPhotos() {
		return photos;
	}

	public void setPhotos(Photo[] photos) {
		this.photos = photos;
	}

	@Override
	public String toString() {
		return "PhotoList [photos=" + Arrays.toString(photos) + "]";
	}
}
