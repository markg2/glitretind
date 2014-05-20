package com.bapple;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

@Path("/itemdetail")
public class ItemDetail {

	/**
	 * This method returns all of the fields for the specified item except for
	 * the database '_id' field and the 'image' field.
	 * @param dbItemId the database id of the document, not to be confused with the manufacturer id
	 * @return a String containing the items fields formatted as JSON
	 */
	@GET
	@Path("/{id:[a-f0-9\\-]+}")
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
	
	/**
	 * This method searches the item collection for the specified dbItemId
	 * @param dbItemId the itemId of the item to retrieve
	 * @return the json representation of the document
	 */
	private String getDetail(final String dbItemId) {
		DB db = ConnectionManagerFactory.getFactory().getConnection();
		
		DBCollection coll = db.getCollection("item");
		
		BasicDBObject objToFind = new BasicDBObject("itemId", dbItemId);
		DBObject obj = coll.findOne(objToFind);
		
		return obj.toString();
	}
}
