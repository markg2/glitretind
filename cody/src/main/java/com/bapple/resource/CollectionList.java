package com.bapple.resource;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bapple.ConnectionManagerFactory;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

@Path("/collectionlist")
public class CollectionList {

	/**
	 * This method returns an array of collections for the specified user.  Each
	 * item in the array contains a document specifying the name of the collection
	 * and how many items are in it.
	 */
	@GET
	@Path("/{userid:[a-f0-9\\-]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getListByUser(@PathParam("userid") final String strUser) {
		DB db = ConnectionManagerFactory.getFactory().getConnection();
		DBCollection coll = db.getCollection("item");
		
		
		// $match
		DBObject match = new BasicDBObject("$match", new BasicDBObject("user", strUser));

		// $group
		BasicDBObject groupFields = new BasicDBObject( "_id", "$collection");
		groupFields.append("numitems", new BasicDBObject( "$sum", 1));
		DBObject group = new BasicDBObject("$group", groupFields);
		
		// run the aggregation
		List<DBObject> pipeline = Arrays.asList(match, group);
		AggregationOutput agg = coll.aggregate(pipeline);
		
		String strDocs = "[";
		String strComma = "";

		for (DBObject result : agg.results()) {
			strDocs += strComma;
			strDocs += result;
			strComma = ",";
		}
		
		strDocs += "]";

		return strDocs;
	}

}

