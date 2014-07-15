package edu.neu.cs5200.service.json.client.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Rsp {
	String stat;
	Photos photos;
	public Photos getPhotos() {
		return photos;
	}
	public void setPhotos(Photos photos) {
		this.photos = photos;
	}
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
}
