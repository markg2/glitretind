package com.bapple;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class ItemListData {
	private static final String DATABASE_URI = "mongodb://dbrex:lego2014@ds045679.mongolab.com:45679/rex";
	private static final String DATABASE_NAME = "rex";

	private static MongoClient _mongoClient = null;
	private static DB _db = null;

public static void create() {
		cleanUp();
		DBCollection coll = _db.getCollection(TableName.ITEMS);
		populateItemsWithBooks(coll);
		populateItemsWithEquipement(coll);

	}
	
	private static void cleanUp() {
		try {
			if (_mongoClient == null) {
				_mongoClient = new MongoClient(new MongoClientURI(DATABASE_URI));
				_db = _mongoClient.getDB(DATABASE_NAME);
			}
			
			Set<String> colls = _db.getCollectionNames();
			System.out.print("Mongo Collections: ");
			for (String s : colls) {
			    System.out.print(s + ", ");
			}
			System.out.println("");

			// Remove Users
			DBCollection coll = _db.getCollection(TableName.USERS);
			if (coll.count() > 0)
				coll.drop();

			// Remove Collections
			coll = _db.getCollection(TableName.COLLECTIONS);
			if (coll.count() > 0)
				coll.drop();
			
			// Remove Items
			coll = _db.getCollection(TableName.ITEMS);
			if (coll.count() > 0)
				coll.drop();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * WARNING!!! If you add or change this data, you need to check the tests in ItemListTest.java
	 * @param coll
	 */
    private static void populateItemsWithBooks(DBCollection coll) {
    	String strUserUuid = addToUsers("Paula");
    	
    	// Collection - One Upon A
    	String strCollectionUuid = addToCollections(strUserUuid, TableName.COLLECTIONS, "Once Upon A");
    	
		// Owned: series A
		coll.insert(populateBookDoc(UUID.randomUUID().toString(), "Once Upon a Kiss", "0515133868", "own", "", strUserUuid, "Nora Roberts", strCollectionUuid, "2002", "public", "Jove", "pocket"));
		coll.insert(populateBookDoc(UUID.randomUUID().toString(), "Once Upon a Dream", "051512947X", "own", "", strUserUuid, "Nora Roberts", strCollectionUuid, "2000", "public", "Jove", "pocket"));
		coll.insert(populateBookDoc(UUID.randomUUID().toString(), "Once Upon a Star", "0515127000", "own", "", strUserUuid, "Nora Roberts", strCollectionUuid, "1999", "public", "Jove", "pocket"));
		coll.insert(populateBookDoc(UUID.randomUUID().toString(), "Once Upon a Castle", "0515122416", "own", "", strUserUuid, "Nora Roberts", strCollectionUuid, "1998", "public", "Jove", "pocket"));

		// Wanted: series A
		coll.insert(populateBookDoc(UUID.randomUUID().toString(), "Once Upon A Midnight", "0515136190", "want", "false", strUserUuid, "Nora Roberts", strCollectionUuid, "2003", "public", "Jove", "pocket"));
		coll.insert(populateBookDoc(UUID.randomUUID().toString(), "Once Upon A Rose", "0515131660", "want", "false", strUserUuid, "Nora Roberts", strCollectionUuid, "2001", "public", "Jove", "pocket"));

    	// Collection - In Death
    	strCollectionUuid = addToCollections(strUserUuid, TableName.COLLECTIONS, "In Death");

		// Owned: series B
		coll.insert(populateBookDoc(UUID.randomUUID().toString(), "Silent Night", "0515123854", "own", "", strUserUuid, "JD Robb", strCollectionUuid, "1998", "public", "Jove", "pocket"));
		coll.insert(populateBookDoc(UUID.randomUUID().toString(), "Out of this World", "0515131091", "own", "", strUserUuid, "JD Robb", strCollectionUuid, "2001", "public", "Jove", "pocket"));
		coll.insert(populateBookDoc(UUID.randomUUID().toString(), "Bump in the Night", "0515141178", "own", "", strUserUuid, "JD Robb", strCollectionUuid, "2006", "public", "Jove", "pocket"));
		coll.insert(populateBookDoc(UUID.randomUUID().toString(), "Suite 606", "978-0425224441", "own", "", strUserUuid, "JD Robb", strCollectionUuid, "2008", "public", "Jove", "pocket"));
		coll.insert(populateBookDoc(UUID.randomUUID().toString(), "The Other Side", "978-0515148671", "own", "", strUserUuid, "JD Robb", strCollectionUuid, "2010", "public", "Jove", "pocket"));
		coll.insert(populateBookDoc(UUID.randomUUID().toString(), "The Unquiet", "978-0515149982", "own", "", strUserUuid, "JD Robb", strCollectionUuid, "2011", "public", "Jove", "pocket"));

		// Wanted: series B
		coll.insert(populateBookDoc(UUID.randomUUID().toString(), "Dead of Night", "978-0515143676", "want", "false", strUserUuid, "JD Robb", strCollectionUuid, "2007", "public", "Jove", "pocket"));
		coll.insert(populateBookDoc(UUID.randomUUID().toString(), "The Lost", "978-0515147186", "want", "false", strUserUuid, "JD Robb", strCollectionUuid, "2009", "public", "Jove", "pocket"));
		coll.insert(populateBookDoc(UUID.randomUUID().toString(), "Mirror Mirror", "978-0515154078", "want", "false", strUserUuid, "JD Robb", strCollectionUuid, "2013", "public", "Jove", "pocket"));
    	
    }
    
    private static BasicDBObject populateBookDoc(String id, String name, String itemId, 
    		String state, String found,
    		String user, String author, String collection, String sequence,
    		String privacy, String manufacturer, String format) {
    	// Make the Collections value an array
    	BasicDBList collectionsField = new BasicDBList();
    	collectionsField.add(collection);
    	
		BasicDBObject doc = new BasicDBObject("name", name)
				.append("itemId", itemId)
				.append("state", state)
				.append("found", found)
				.append("user", user)
				.append("author", author)
				.append("collections", collectionsField)
				.append("sequence", sequence)
				.append("privacy", privacy)
				.append("manufacturer", manufacturer)
				.append("format", format)
				.append("_id", id);
    	
		return doc;
    }
    
	/**
	 * WARNING!!! If you add or change this data, you need to check the tests in ItemListTest.java
	 * @param coll
	 */
    private static void populateItemsWithEquipement(DBCollection coll) {
    	String strUserUuid = addToUsers("Mark");

    	coll.insert(populateEquipmentDoc(UUID.randomUUID().toString(), strUserUuid, "Das Parka", "84102", "own", "public", "patagonia.com", new String[]{"climbing", "insulations"}, "orange, medium, purchased directly from Patagonia"));
		coll.insert(populateEquipmentDoc(UUID.randomUUID().toString(), strUserUuid, "Snowstorm 20 Backpack - 1220 cu in", "123456", "own", "public", "Lowe Alpine", new String[]{"climbing", "backpack", "skiing"}, "red, purchased on backcountry.com, $62.97, Jun 28 2010"));
		coll.insert(populateEquipmentDoc(UUID.randomUUID().toString(), strUserUuid, "Snowstorm 20 Backpack - 1220 cu in", "", "own", "public", "Lowe Alpine", new String[]{"climbing", "backpack", "skiing"}, "black, purchased on sunnysports.com, $64, 2013"));
		coll.insert(populateEquipmentDoc(UUID.randomUUID().toString(), strUserUuid, "Attack MX50+10 Pack - 3000 cu in", "", "own", "public", "Lowe Alpine", new String[]{"climbing", "backpack"}, "cobalt/slate gray, purchased on backcountry.com, $179.96, Feb 18 2009"));
		coll.insert(populateEquipmentDoc(UUID.randomUUID().toString(), strUserUuid, "Serendipity Jacket", "", "own", "public", "Cloudveil", new String[]{"climbing", "softshell", "skiing"}, "medium, Pompei, purchased on backcountry.com, $161.97, Jan 7 2009"));
		coll.insert(populateEquipmentDoc(UUID.randomUUID().toString(), strUserUuid, "Alpine Guide Jacket", "83940", "own", "public", "patagonia.com", new String[]{"climbing", "softshell", "skiing"}, "medium, Nickel, purchased direct, $137, reg $229, Jan 7 2009"));
		coll.insert(populateEquipmentDoc(UUID.randomUUID().toString(), strUserUuid, "Ascensionist Pack", "48000", "own", "public", "patagonia.com", new String[]{"climbing", "backpack"}, "Eclectic Orange, 2800 cu in, dividend purchase on rei.com, $179, full price, April 2014"));
		coll.insert(populateEquipmentDoc(UUID.randomUUID().toString(), strUserUuid, "Gritty Pack", "", "own", "public", "patagonia.com", new String[]{"climbing", "backpack"}, "red, 2200 cu in, purchased direct, $clearance, ~2007/2008"));
		coll.insert(populateEquipmentDoc(UUID.randomUUID().toString(), strUserUuid, "Sprint Action Sport Pack", "", "own", "public", "Lowe Alpine", new String[]{"climbing", "backpack", "hydration"}, "lime/black, <500 cu in, purchased REI store, ~$30, ~1999"));
		coll.insert(populateEquipmentDoc(UUID.randomUUID().toString(), strUserUuid, "Troposphere Jacket", "84540", "own", "public", "patagonia.com", new String[]{"climbing", "hardshell", "skiing"}, "medium, Paintbrush, backcountry.com discount site, <=$150, reg $299, April 2013"));
		coll.insert(populateEquipmentDoc(UUID.randomUUID().toString(), strUserUuid, "Mixed Guide Hoody (hybrid hard/softshell)", "", "own", "public", "patagonia.com", new String[]{"climbing", "softshell", "skiing"}, "medium, Paintbrush Red, purchased Patagonia store Heavenly Village, $225, reg $299, Dec 2012"));
		coll.insert(populateEquipmentDoc(UUID.randomUUID().toString(), strUserUuid, "Triolet Jacket", "", "own", "public", "patagonia.com", new String[]{"climbing", "hardshell", "skiing"}, "medium, purple, purchased Sierra Outfitters, ~$200, reg <$400, ~1997"));
		coll.insert(populateEquipmentDoc(UUID.randomUUID().toString(), strUserUuid, "Mountain Jacket", "", "own", "public", "TNF", new String[]{"climbing", "hardshell", "skiing", "mountaineering"}, "medium, Teal/black, purchased TNF Berkeley, $365, ~1993"));
    }

    private static BasicDBObject populateEquipmentDoc(String id, String user, String name, String itemId, String state,  
    		String privacy, String manufacturer, String[]tags, String notes) {
    	List<String> tagsArr = new ArrayList<String>();
    	for (String tag: tags)
    		tagsArr.add(tag);
    	
		BasicDBObject doc = new BasicDBObject("name", name)
				.append("itemId", itemId)
				.append("state", state)
				.append("privacy", privacy)
				.append("manufacturer", manufacturer)
				.append("notes", notes)
				.append("tags", tagsArr)
				.append("user", user)
				.append("_id", id);
		
    	
		return doc;
    }

    
    /**
//		Base64 b64 = new Base64(true);
		String s = UUID.randomUUID().toString();
		System.out.println(s);
//		String enc = Base64.encodeBase64URLSafe(s.getBytes()).toString();
		byte[] enc = Base64.encodeBase64URLSafe(s.getBytes());
		System.out.println(Arrays.toString(enc));
		String strEnc = new String(enc);
		System.out.println(strEnc);
		String decrypt = new String(Base64.decodeBase64(enc));
		System.out.println(decrypt);
     * @param strCollectionName
     * @return
     */
    private static String addToUsers(String strCollectionName){
		// Create Items
		String strUuid = UUID.randomUUID().toString();
		BasicDBObject doc = new BasicDBObject("name", strCollectionName).
				append("_id", strUuid);
		_db.getCollection(TableName.USERS).insert(doc);

    	return strUuid;
    }

    private static String addToCollections(String strUserUuid, String strTableName, String strCollectionName){
		// Create Items
		String strUuid = UUID.randomUUID().toString();
		BasicDBObject doc = new BasicDBObject("name", strCollectionName).
				append("_id", strUuid).append("user", strUserUuid);
		_db.getCollection(strTableName).insert(doc);

    	return strUuid;
    }
 	
}
