package com.bapple;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/item")
public class Item {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getAsJSON() {
		return "{   _id : ObjectId(\"535dda40665f110224b3e1c5\"), " +
				"name : \"Das Parka\", " +
				"itemId: \"84102\", " +
				"state: \"own|want\", " +
				"privacy: \"public|private\", " +
				"manufacturer: \"patagonia.com\", " +
				"notes: \"orange, medium, purchased directly from Patagonia\", " +
				"tags: [\"climbing\", \"insulation\"], " +
				"image: \"/9j/4AAQSkZJRgABAQEASABIAAD/7QCIUGhvdG9zaG9wIDMuMAA4QklNBAQAAAAAA...\"}";
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getAsHtml() {
		return "MediaType.TEXT_HTML\n";
	}

	@GET
	@Produces(MediaType.TEXT_XML)
	public String getAsXml() {
		return "MediaType.Text_XML\n";
	}

}
