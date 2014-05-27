package com.bapple.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bapple.ConnectionManagerFactory;
import com.bapple.QueryCriteria;
import com.bapple.Server;
import com.bapple.TableName;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

@Path("/items")
public class Items {

	/**
	 * This method returns all of the fields for the specified item except for
	 * the database '_id' field and the 'image' field.
	 * @param dbItemId the database id of the document, not to be confused with the manufacturer id
	 * @return a String containing the items fields formatted as JSON
	 */
	@GET
	@Path("/{id:[a-f0-9\\-]+}")
	@Produces(MediaType.APPLICATION_JSON)
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
		DBCollection coll = db.getCollection(TableName.ITEMS);
		DBObject obj = coll.findOne(QueryCriteria.getById(dbItemId));
		
		obj.put("href", Server.getBaseUrl() +"/items/" + dbItemId);

		return obj != null ? obj.toString() : "{'notFound':'" + dbItemId + "'}";
	}
}
