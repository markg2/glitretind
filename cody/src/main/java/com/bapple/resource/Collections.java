package com.bapple.resource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bapple.ConnectionManagerFactory;
import com.bapple.QueryCriteria;
import com.bapple.Server;
import com.bapple.TableName;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@Path("/collections")
public class Collections {
	private static String USER_PAULA = "Paula";
	private static String USER_ID = null;
	
	/**
	 * This method returns an array of collections for the specified user.  Each
	 * item in the array contains a document specifying the name of the collection
	 * and how many items are in it.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getListByUser() {
		AggregationOutput agg = getCollections(null);
		HashMap<String, DBObject> collectionsNames = getCollectionNames();
		
		String strDocs = "[";
		String strComma = "";

		for (DBObject result : agg.results()) {
			Object obj = result.get("_id");
			result.put("name", collectionsNames.get(obj.toString()).get("name"));
			result.put("href", Server.getBaseUrl() +"/collections/" + obj.toString());
			strDocs += strComma;
			strDocs += result;
			strComma = ",";
		}
		
		strDocs += "]";

		return strDocs;
	}

	/**
	 * This method returns the documents for the specified user
	 * and collection.  The number of items in the collection is a part of the
	 * response too.
	 * 
	 * @param strCollection a String containing the id of the collection to query
	 * @return a String in JSON format containing all of the documents to be returned
	 */
	@GET
	@Path("/{collectionuuid:[A-Za-z0-9\\-]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getListByUserCollection(@PathParam("collectionuuid") final String strUuid) {
		List<DBObject> queryResults = getItems(QueryCriteria.getByUserCollection(getUserUuid(), strUuid));
		addHrefToItems(queryResults);

		AggregationOutput agg = getCollections(strUuid);
		HashMap<String, DBObject> collectionsNames = getCollectionNames();
		
		String strDocs = "";
		String strComma = "";

		for (DBObject result : agg.results()) {
			result.put("name", collectionsNames.get(strUuid).get("name"));
			result.put("href", Server.getBaseUrl() +"/collections/" + strUuid);
			result.put("items", queryResults);
			strDocs += strComma;
			strDocs += result;
			strComma = ",";
		}
		
		return strDocs;
	}

	/**
	 * This method performs an aggregation to find what collections a user has.
	 * With each collection, the number of items in each colleciton, 'numitems', 
	 * is also determined.
	 * 
	 * Sample Syntax:
	 * db.items.aggregate([
	 * {"$unwind":"$collections"},
	 * {"$match":{"user":"2a7b4099-684e-4b6e-80b8-319772d99fff", "collections":"4c20201a-a237-4d2c-82df-c3188bcd2c5c"}},
	 * {"$group":{"_id":"$collections", "count":{"$sum":1}}}
	 * ])
	 *  
	 * @param strCollectionUuid if not specified, then get all collections
	 * @return
	 */
	private AggregationOutput getCollections(String strCollectionUuid) {
		DB db = ConnectionManagerFactory.getFactory().getConnection();
		DBCollection coll = db.getCollection(TableName.ITEMS);
		
		// $unwind
		DBObject unwind = new BasicDBObject("$unwind", "$collections");
		
		// $match
		DBObject filter = new BasicDBObject("user", getUserUuid());
		if (strCollectionUuid != null)
			filter.put("collections", strCollectionUuid);
		DBObject match = new BasicDBObject("$match", filter);

		// $group
		BasicDBObject groupFields = new BasicDBObject( "_id", "$collections");
		groupFields.append("count", new BasicDBObject( "$sum", 1));
		DBObject group = new BasicDBObject("$group", groupFields);
		
		// run the aggregation
		List<DBObject> pipeline = Arrays.asList(unwind, match, group);
		AggregationOutput agg = coll.aggregate(pipeline);

		return agg;
	}
	
	/**
	 * This method returns a list of 'item' objects in a List.
	 * @param queryCriteria
	 * @return a list of 'item' objects
	 */
	private List<DBObject> getItems(DBObject queryCriteria) {
		DB db = ConnectionManagerFactory.getFactory().getConnection();
		DBCollection coll = db.getCollection(TableName.ITEMS);
		DBCursor cursor = coll.find(queryCriteria);
		List<DBObject> queryResults = cursor.toArray();
		
		return queryResults;
	}
	
	/**
	 * This method adds an href field to each 'item' which contains the URL for
	 * the individual item.
	 * @param coll
	 */
	private void addHrefToItems(List<DBObject> coll) {
		Iterator<DBObject> i = coll.iterator();
		while (i.hasNext()) {
			DBObject obj = i.next();
			obj.put("href", Server.getBaseUrl() +"/items/" + obj.get("_id").toString());
		}
	}

	/**
	 * This method gets all of the Collections documents for a user
	 * and returns them.
	 * 
	 * @return List<DBObject> containing the collection documents for a specific user
	 */
	private HashMap<String, DBObject> getCollectionNames() {
		DB db = ConnectionManagerFactory.getFactory().getConnection();
		DBCollection coll = db.getCollection(TableName.COLLECTIONS);
		DBCursor cursor = coll.find(QueryCriteria.getByUser(getUserUuid()));
		List<DBObject> results = cursor.toArray();
		Iterator<DBObject>i = results.iterator();
		HashMap<String, DBObject> hm = new HashMap<String, DBObject>();
		
		while (i.hasNext()) {
			DBObject o = i.next();
			hm.put(o.get("_id").toString(), o);
		}
		
		return hm;
	}
	
	/**
	 * TODO: this method goes away once OAuth is working.  It is here merely to
	 * provide automatic "authentication" until the OAuth solution is in place.
	 */
	private String getUserUuid() {
		if (USER_ID == null) {
			DB db = ConnectionManagerFactory.getFactory().getConnection();
			DBCollection coll = db.getCollection(TableName.USERS);
			DBObject obj = coll.findOne(QueryCriteria.getByName(USER_PAULA));
			USER_ID = obj.get("_id").toString();
		}
		
		return USER_ID;
	}
}

