package com.bapple.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/itemcount")
public class ItemCount {
	/**
	 * 
	 * @param strCollection a String containing the id of the collection to query
	 * @return a number representing the numnber of items in the collection
	 */
	@GET
//	@Path("/{id:[a-f0-9\\-]+}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getAsText(@PathParam("id") final String strCollection) {
		return "13";
	}

}
