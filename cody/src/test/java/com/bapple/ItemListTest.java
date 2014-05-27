package com.bapple;

import org.junit.*;

import static org.junit.Assert.*;

import java.net.UnknownHostException;
import java.util.*;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import java.util.List;
import java.util.Set;

public class ItemListTest {
	private static final String DATABASE_URI = "mongodb://dbrex:lego2014@ds045679.mongolab.com:45679/rex";
	private static final String DATABASE_NAME = "rex";
	
    private Collection<String> collection;
 
    @BeforeClass
    public static void oneTimeSetUp() {
        // one-time initialization code   
    	System.out.println("@BeforeClass - oneTimeSetUp");
    }
 
    @AfterClass
    public static void oneTimeTearDown() {
        // one-time cleanup code
    	System.out.println("@AfterClass - oneTimeTearDown");
    }
 
    @Before
    public void setUp() {
        collection = new ArrayList<String>();
        System.out.println("@Before - setUp");
    }
 
    @After
    public void tearDown() {
        collection.clear();
        System.out.println("@After - tearDown");
    }
 
    @Test
    public void testCreateCollection() {
        assertTrue(collection.isEmpty());
        System.out.println("@Test - testCreateCollection");
        try {
        	MongoClientURI uri = new MongoClientURI(DATABASE_URI);
			MongoClient mongoClient = new MongoClient(uri);
			DB db = mongoClient.getDB(DATABASE_NAME);
			
			// boolean auth = db.authenticate(myUserName, myPassword);
			Set<String> colls = db.getCollectionNames();
			for (String s : colls) {
			    System.out.println(s);
			}
			DBCollection coll = db.getCollection(TableName.ITEMS);
			if (coll.count() > 0)
				coll.drop();
			coll = db.getCollection(TableName.ITEMS);
			populateCollectionWithBooks(coll);
			populateCollectionWithEquipement(coll);
			
        } catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 
    private void populateCollectionWithBooks(DBCollection coll) {
		// Owned: series A
		coll.insert(populateBookDoc("Once Upon a Kiss", "0515133868", "own", "", "5376e5306283df5bc1fe74d9", "Nora Roberts", "OnceUponA", "2002", "public", "Jove", "pocket"));
		coll.insert(populateBookDoc("Once Upon a Dream", "051512947X", "own", "", "5376e5306283df5bc1fe74d9", "Nora Roberts", "OnceUponA", "2000", "public", "Jove", "pocket"));
		coll.insert(populateBookDoc("Once Upon a Star", "0515127000", "own", "", "5376e5306283df5bc1fe74d9", "Nora Roberts", "OnceUponA", "1999", "public", "Jove", "pocket"));
		coll.insert(populateBookDoc("Once Upon a Castle", "0515122416", "own", "", "5376e5306283df5bc1fe74d9", "Nora Roberts", "OnceUponA", "1998", "public", "Jove", "pocket"));

		// Wanted: series A
		coll.insert(populateBookDoc("Once Upon A Midnight", "0515136190", "want", "false", "5376e5306283df5bc1fe74d9", "Nora Roberts", "OnceUponA", "2003", "public", "Jove", "pocket"));
		coll.insert(populateBookDoc("Once Upon A Rose", "0515131660", "want", "false", "5376e5306283df5bc1fe74d9", "Nora Roberts", "OnceUponA", "2001", "public", "Jove", "pocket"));
		
		// Owned: series B
		coll.insert(populateBookDoc("Silent Night", "0515123854", "own", "", "5376e5306283df5bc1fe74d9", "JD Robb", "InDeath", "1998", "public", "Jove", "pocket"));
		coll.insert(populateBookDoc("Out of this World", "0515131091", "own", "", "5376e5306283df5bc1fe74d9", "JD Robb", "InDeath", "2001", "public", "Jove", "pocket"));
		coll.insert(populateBookDoc("Bump in the Night", "0515141178", "own", "", "5376e5306283df5bc1fe74d9", "JD Robb", "InDeath", "2006", "public", "Jove", "pocket"));
		coll.insert(populateBookDoc("Suite 606", "978-0425224441", "own", "", "5376e5306283df5bc1fe74d9", "JD Robb", "InDeath", "2008", "public", "Jove", "pocket"));
		coll.insert(populateBookDoc("The Other Side", "978-0515148671", "own", "", "5376e5306283df5bc1fe74d9", "JD Robb", "InDeath", "2010", "public", "Jove", "pocket"));
		coll.insert(populateBookDoc("The Unquiet", "978-0515149982", "own", "", "5376e5306283df5bc1fe74d9", "JD Robb", "InDeath", "2011", "public", "Jove", "pocket"));

		// Wanted: series B
		coll.insert(populateBookDoc("Dead of Night", "978-0515143676", "want", "false", "5376e5306283df5bc1fe74d9", "JD Robb", "InDeath", "2007", "public", "Jove", "pocket"));
		coll.insert(populateBookDoc("The Lost", "978-0515147186", "want", "false", "5376e5306283df5bc1fe74d9", "JD Robb", "InDeath", "2009", "public", "Jove", "pocket"));
		coll.insert(populateBookDoc("Mirror Mirror", "978-0515154078", "want", "false", "5376e5306283df5bc1fe74d9", "JD Robb", "InDeath", "2013", "public", "Jove", "pocket"));
    	
    }
    
    private BasicDBObject populateBookDoc(String name, String itemId, String state, String found,
    		String user, String author, String collection, String sequence,
    		String privacy, String manufacturer, String format) {
		BasicDBObject doc = new BasicDBObject("name", name)
				.append("itemId", itemId)
				.append("state", state)
				.append("found", found)
				.append("user", user)
				.append("author", author)
				.append("collection", collection)
				.append("sequence", sequence)
				.append("privacy", privacy)
				.append("manufacturer", manufacturer)
				.append("format", format);
    	
		return doc;
    }
    
    private void populateCollectionWithEquipement(DBCollection coll) {
		coll.insert(populateEquipmentDoc("5376e23c6283df5bc1fe74d8", "Das Parka", "84102", "own", "public", "patagonia.com", new String[]{"climbing", "insulations"}, "orange, medium, purchased directly from Patagonia"));
		coll.insert(populateEquipmentDoc("5376e23c6283df5bc1fe74d8", "Snowstorm 20 Backpack - 1220 cu in", "123456", "own", "public", "Lowe Alpine", new String[]{"climbing", "backpack", "skiing"}, "red, purchased on backcountry.com, $62.97, Jun 28 2010"));
		coll.insert(populateEquipmentDoc("5376e23c6283df5bc1fe74d8", "Snowstorm 20 Backpack - 1220 cu in", "", "own", "public", "Lowe Alpine", new String[]{"climbing", "backpack", "skiing"}, "black, purchased on sunnysports.com, $64, 2013"));
		coll.insert(populateEquipmentDoc("5376e23c6283df5bc1fe74d8", "Attack MX50+10 Pack - 3000 cu in", "", "own", "public", "Lowe Alpine", new String[]{"climbing", "backpack"}, "cobalt/slate gray, purchased on backcountry.com, $179.96, Feb 18 2009"));
		coll.insert(populateEquipmentDoc("5376e23c6283df5bc1fe74d8", "Serendipity Jacket", "", "own", "public", "Cloudveil", new String[]{"climbing", "softshell", "skiing"}, "medium, Pompei, purchased on backcountry.com, $161.97, Jan 7 2009"));
		coll.insert(populateEquipmentDoc("5376e23c6283df5bc1fe74d8", "Alpine Guide Jacket", "83940", "own", "public", "patagonia.com", new String[]{"climbing", "softshell", "skiing"}, "medium, Nickel, purchased direct, $137, reg $229, Jan 7 2009"));
		coll.insert(populateEquipmentDoc("5376e23c6283df5bc1fe74d8", "Ascensionist Pack", "48000", "own", "public", "patagonia.com", new String[]{"climbing", "backpack"}, "Eclectic Orange, 2800 cu in, dividend purchase on rei.com, $179, full price, April 2014"));
		coll.insert(populateEquipmentDoc("5376e23c6283df5bc1fe74d8", "Gritty Pack", "", "own", "public", "patagonia.com", new String[]{"climbing", "backpack"}, "red, 2200 cu in, purchased direct, $clearance, ~2007/2008"));
		coll.insert(populateEquipmentDoc("5376e23c6283df5bc1fe74d8", "Sprint Action Sport Pack", "", "own", "public", "Lowe Alpine", new String[]{"climbing", "backpack", "hydration"}, "lime/black, <500 cu in, purchased REI store, ~$30, ~1999"));
		coll.insert(populateEquipmentDoc("5376e23c6283df5bc1fe74d8", "Troposphere Jacket", "84540", "own", "public", "patagonia.com", new String[]{"climbing", "hardshell", "skiing"}, "medium, Paintbrush, backcountry.com discount site, <=$150, reg $299, April 2013"));
		coll.insert(populateEquipmentDoc("5376e23c6283df5bc1fe74d8", "Mixed Guide Hoody (hybrid hard/softshell)", "", "own", "public", "patagonia.com", new String[]{"climbing", "softshell", "skiing"}, "medium, Paintbrush Red, purchased Patagonia store Heavenly Village, $225, reg $299, Dec 2012"));
		coll.insert(populateEquipmentDoc("5376e23c6283df5bc1fe74d8", "Triolet Jacket", "", "own", "public", "patagonia.com", new String[]{"climbing", "hardshell", "skiing"}, "medium, purple, purchased Sierra Outfitters, ~$200, reg <$400, ~1997"));
		coll.insert(populateEquipmentDoc("5376e23c6283df5bc1fe74d8", "Mountain Jacket", "", "own", "public", "TNF", new String[]{"climbing", "hardshell", "skiing", "mountaineering"}, "medium, Teal/black, purchased TNF Berkeley, $365, ~1993"));
    }

    private BasicDBObject populateEquipmentDoc(String user, String name, String itemId, String state,  
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
				.append("user", user);
		
    	
		return doc;
    }
    
    @Test
    public void testOneItemCollection() {
        collection.add("itemA");
        assertEquals(1, collection.size());
        System.out.println("@Test - testOneItemCollection");
    }
}