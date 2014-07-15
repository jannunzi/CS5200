package edu.neu.cs5200.service.json.client.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class Photo {
	@XmlAttribute
	private String id;
	@XmlAttribute
	private String secret;
	@XmlAttribute
	private String server;
	@XmlAttribute
	private String title;
	@XmlAttribute
	private String farm;
	@XmlAttribute
	private String owner;
	@XmlAttribute
	private String ispublic;
	@XmlAttribute
	private String isfriend;
	@XmlAttribute
	private String isfamily;
		
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFarm() {
		return farm;
	}
	public void setFarm(String farm) {
		this.farm = farm;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getIspublic() {
		return ispublic;
	}
	public void setIspublic(String ispublic) {
		this.ispublic = ispublic;
	}
	public String getIsfriend() {
		return isfriend;
	}
	public void setIsfriend(String isfriend) {
		this.isfriend = isfriend;
	}
	public String getIsfamily() {
		return isfamily;
	}
	public void setIsfamily(String isfamily) {
		this.isfamily = isfamily;
	}
}
