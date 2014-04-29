package com.bapple;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/itemlist")
public class ItemList {
	/**
	 * This method returns some number of documents from the specified collection.
	 * 
	 * @param strCollection a String containing the id of the collection to query
	 * @return a String in JSON format containing all of the documents to be returned
	 */
	@GET
//	@Path("/{id:[a-f0-9\\-]+}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getAsText(@PathParam("id") final String strCollection) {
		return "{ \"name\" : \"Das Parka\", \"itemId\" : \"84102\", \"manufacturer\" : \"patagonia.com\" }" +
			"{ \"name\" : \"Snowstorm 20 Backpack - 1220 cu in\", \"itemId\" : \"\", \"manufacturer\" : \"Lowe Alpine\" }" +
			"{ \"name\" : \"Snowstorm 20 Backpack - 1220 cu in\", \"itemId\" : \"123456\", \"manufacturer\" : \"Lowe Alpine\" }" +
			"{ \"name\" : \"Attack MX50+10 Pack - 3000 cu in\", \"itemId\" : \"\", \"manufacturer\" : \"Lowe Alpine\" }" +
			"{ \"name\" : \"Serendipity Jacket\", \"itemId\" : \"\", \"manufacturer\" : \"Cloudveil\" }" +
			"{ \"name\" : \"Alpine Guide Jacket\", \"itemId\" : \"83940\", \"manufacturer\" : \"patagonia.com\" }" +
			"{ \"name\" : \"Ascensionist Pack\", \"itemId\" : \"48000\", \"manufacturer\" : \"patagonia.com\" }" +
			"{ \"name\" : \"Gritty Pack\", \"itemId\" : \"\", \"manufacturer\" : \"patagonia.com\" }" +
			"{ \"name\" : \"Sprint Action Sport Pack\", \"itemId\" : \"\", \"manufacturer\" : \"Lowe Alpine\" }" +
			"{ \"name\" : \"Troposphere Jacket\", \"itemId\" : \"84540\", \"manufacturer\" : \"patagonia.com\" }" +
			"{ \"name\" : \"Mixed Guide Hoody (hybrid hard/softshell)\", \"itemId\" : \"\", \"manufacturer\" : \"patagonia.com\" }" +
			"{ \"name\" : \"Triolet Jacket\", \"itemId\" : \"\", \"manufacturer\" : \"patagonia.com\" }" +
			"{ \"name\" : \"Mountain Jacket\", \"itemId\" : \"\", \"manufacturer\" : \"TNF\" }";
	}

}
