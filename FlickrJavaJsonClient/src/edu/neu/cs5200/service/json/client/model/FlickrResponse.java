package edu.neu.cs5200.service.json.client.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FlickrResponse {
	Rsp rsp;
	public Rsp getRsp() {
		return rsp;
	}
	public void setRsp(Rsp rsp) {
		this.rsp = rsp;
	}
}
