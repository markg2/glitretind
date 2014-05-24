package com.bapple;

import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

@Path("/itemlist")
public class ItemList {
	/**
	 * This method returns some number of documents from the specified collection.
	 * 
	 * @param strCollection a String containing the id of the collection to query
	 * @return a String in JSON format containing all of the documents to be returned
	 */
	@GET
	@Path("/{id:[a-f0-9\\-]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getListByUser(@PathParam("id") final String strUser) {
		DB db = ConnectionManagerFactory.getFactory().getConnection();
		
		DBCollection coll = db.getCollection("item");
		BasicDBObject objToFind = new BasicDBObject("user", strUser);
		DBCursor cursor = coll.find(objToFind);
		String strDocs = "[";
		String strComma = "";

		while (cursor.hasNext()) {
			strDocs += strComma;
			strDocs += cursor.next();
			strComma = ",";
		}
		
		strDocs += "]";

		return strDocs;
	}

	
	@GET
	@Path("/{id:[a-f0-9\\-]+}/count")
	@Produces(MediaType.APPLICATION_JSON)
	public String getListByUserCount(@PathParam("id") final String strUser) {
		DB db = ConnectionManagerFactory.getFactory().getConnection();
		
		DBCollection coll = db.getCollection("item");
		BasicDBObject objToFind = new BasicDBObject("user", strUser);
		int itemCount = coll.find(objToFind).count();

		return "{\"count\":" + Integer.valueOf(itemCount).toString() + "}";
	}
	
}
