package com.bapple;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/itemdetail")
public class ItemDetail {

	/**
	 * This method returns all of the fields for the specified item except for
	 * the database '_id' field and the 'image' field.
	 * @param dbItemId the database id of the document, not to be confused with the manufacturer id
	 * @return a String containing the items fields formatted as JSON
	 */
	@GET
//	@Path("/{id:[a-f0-9\\-]+}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getAsJSON(@PathParam("id") final String dbItemId) {
		return getDetail(dbItemId);
	}

	/**
	 * This method is for
	 * @return
	 */
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getAsHtml() {
		return getDetail(null);
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String postItem() {
		return "{\"message\": \"an Item Detail was posted to Cody.\"}\n";
	}
	
	private String getDetail(final String dbItemId) {
		return "{" +
				"\"name\": \"Das Parka\", " +
				"\"itemId\": \"84102\", " +
				"\"state\": \"own|want\", " +
				"\"privacy\": \"public|private\", " +
				"\"manufacturer\": \"patagonia.com\", " +
				"\"notes\": \"orange, medium, purchased directly from Patagonia\", " +
				"\"tags\": [\"climbing\", \"insulation\"]}";
	}
}
