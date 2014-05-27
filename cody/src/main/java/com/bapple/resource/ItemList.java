package com.bapple.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bapple.ConnectionManagerFactory;
import com.bapple.QueryCriteria;
import com.bapple.TableName;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@Path("/itemlist")
public class ItemList {
	/**
	 * This method returns some number of documents for the specified user
	 * 
	 * @param strCollection a String containing the id of the collection to query
	 * @return a String in JSON format containing all of the documents to be returned
	 */
	@GET
	@Path("/{userid:[a-f0-9\\-]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getListByUser(@PathParam("userid") final String strUser) {
		return buildResponse(QueryCriteria.getByUser(strUser));
	}

	/**
	 * This method returns a json document that indicates the number of item documents
	 * for a user.
	 * @param strUser
	 * @return
	 */
	@GET
	@Path("/{userid:[a-f0-9\\-]+}/count")
	@Produces(MediaType.APPLICATION_JSON)
	public String getListByUserCount(@PathParam("userid") final String strUser) {
		System.out.println("found the OLD method");
		DB db = ConnectionManagerFactory.getFactory().getConnection();
		DBCollection coll = db.getCollection("item");
		int itemCount = coll.find(QueryCriteria.getByUser(strUser)).count();

		return "{\"count\":" + Integer.valueOf(itemCount).toString() + "}";
	}

	/**
	 * This method returns some number of documents for the specified user
	 * and collection.
	 * 
	 * @param strCollection a String containing the id of the collection to query
	 * @return a String in JSON format containing all of the documents to be returned
	 */
	@GET
	@Path("/{userid:[a-f0-9\\-]+}/{collectionname:[a-f0-9\\- ]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getListByUserCollection(@PathParam("userid") final String strUser,
			@PathParam("collectionname") final String strCollectionName) {
		System.out.println("found the new method");
		return buildResponse(QueryCriteria.getByUserCollection(strUser, strCollectionName));
	}

	/**
	 * This method returns a json document that indicates the number of item documents
	 * for a user and collection
	 * @param strUser
	 * @return
	 */
	@GET
	@Path("/{userid:[a-f0-9\\-]+}/{collectionname:[a-f0-9\\-]+}/count")
	@Produces(MediaType.APPLICATION_JSON)
	public String getListByUserCollectionCount(@PathParam("userid") final String strUser,
			@PathParam("collectionname") final String strCollectionName) {
		DB db = ConnectionManagerFactory.getFactory().getConnection();
		
		DBCollection coll = db.getCollection("item");
		int itemCount = coll.find(QueryCriteria.getByUserCollection(strUser, strCollectionName)).count();

		return "{\"count\":" + Integer.valueOf(itemCount).toString() + "}";
	}
	
	/**
	 * This method builds a response containing several item documents.  The
	 * documents returned from the item collection is determined by query Criteria
	 * @param queryCriteria an object containing information for filtering documents
	 * @return a string containing all documents found
	 */
	private String buildResponse(DBObject queryCriteria) {
		DB db = ConnectionManagerFactory.getFactory().getConnection();
		DBCollection coll = db.getCollection(TableName.ITEMS);
		DBCursor cursor = coll.find(queryCriteria);
		
		String strDocs = "[";
		String strComma = "";

		while (cursor.hasNext()) {
			DBObject obj = cursor.next();
			obj.put("href", "localhost:8080/cody/resource/items/" + obj.get("_id").toString());
			strDocs += strComma;
			strDocs += obj;
			strComma = ",";
		}
		
		strDocs += "]";

		return strDocs;
	}
	
}
