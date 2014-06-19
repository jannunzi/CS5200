package com.atc.siterra.bup.sharegen.model;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/image")
public class ImageService {
	@POST
	@Path("/upload")
	public void upload()
	{
		System.out.println("got it");
	}
}
