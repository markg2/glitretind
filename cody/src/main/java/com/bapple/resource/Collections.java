package com.bapple.resource;

import java.util.Arrays;
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
	private static String USER_PAULA = "5376e5306283df5bc1fe74d9";
	
	/**
	 * This method returns an array of collections for the specified user.  Each
	 * item in the array contains a document specifying the name of the collection
	 * and how many items are in it.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getListByUser() {
		AggregationOutput agg = getCollections(null);
		
		String strDocs = "[";
		String strComma = "";

		for (DBObject result : agg.results()) {
			Object obj = result.get("_id");
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
	@Path("/{collectionname:[A-Za-z0-9\\-]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getListByUserCollection(@PathParam("collectionname") final String strCollectionName) {
		List<DBObject> queryResults = getItems(QueryCriteria.getByUserCollection(USER_PAULA, strCollectionName));
		addHrefToItems(queryResults);

		AggregationOutput agg = getCollections(strCollectionName);
		
		String strDocs = "";
		String strComma = "";

		for (DBObject result : agg.results()) {
			result.put("href", Server.getBaseUrl() +"/collections/" + strCollectionName);
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
	 * @param strCollectionName if not specified, then get all collections
	 * @return
	 */
	private AggregationOutput getCollections(String strCollectionName) {
		DB db = ConnectionManagerFactory.getFactory().getConnection();
		DBCollection coll = db.getCollection(TableName.ITEMS);
		
		
		// $match
		DBObject filter = new BasicDBObject("user", USER_PAULA);
		if (strCollectionName != null)
			filter.put("collection", strCollectionName);
		DBObject match = new BasicDBObject("$match", filter);

		// $group
		BasicDBObject groupFields = new BasicDBObject( "_id", "$collection");
		groupFields.append("numitems", new BasicDBObject( "$sum", 1));
		DBObject group = new BasicDBObject("$group", groupFields);
		
		// run the aggregation
		List<DBObject> pipeline = Arrays.asList(match, group);
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
	
}

