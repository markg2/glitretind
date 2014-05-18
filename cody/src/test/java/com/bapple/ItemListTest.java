package com.bapple;

import org.junit.*;

import static org.junit.Assert.*;

import java.net.UnknownHostException;
import java.util.*;

import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.BulkWriteResult;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ParallelScanOptions;

import java.util.List;
import java.util.Set;

import static java.util.concurrent.TimeUnit.SECONDS;
/**
 * @author mkyong
 *
 */
public class ItemListTest {
 
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
    public void testEmptyCollection() {
        assertTrue(collection.isEmpty());
        System.out.println("@Test - testEmptyCollection");
        try {
			MongoClient mongoClient = new MongoClient( "localhost" );
			DB db = mongoClient.getDB( "rex" );
			// boolean auth = db.authenticate(myUserName, myPassword);
			Set<String> colls = db.getCollectionNames();
			for (String s : colls) {
			    System.out.println(s);
			}
			DBCollection coll = db.getCollection("itemBook");
			if (coll.count() > 0)
				coll.drop();
			coll = db.getCollection("itemBook");
			coll.insert(populateDoc("Once Upon a Kiss", "0515133868", "own", "", "5376e5306283df5bc1fe74d9", "Nora Roberts", "a", "2002", "public", "Jove", "pocket"));
			coll.insert(populateDoc("Once Upon a Dream", "051512947X", "own", "", "5376e5306283df5bc1fe74d9", "Nora Roberts", "a", "2000", "public", "Jove", "pocket"));
			coll.insert(populateDoc("Once Upon a Star", "0515127000", "own", "", "5376e5306283df5bc1fe74d9", "Nora Roberts", "a", "1999", "public", "Jove", "pocket"));
			coll.insert(populateDoc("Once Upon a Castle", "0515122416", "own", "", "5376e5306283df5bc1fe74d9", "Nora Roberts", "a", "1998", "public", "Jove", "pocket"));

			coll.insert(populateDoc("Silent Night", "0515123854", "own", "", "5376e5306283df5bc1fe74d9", "JD Robb", "b", "1998", "public", "Jove", "pocket"));
			coll.insert(populateDoc("Out of this World", "0515131091", "own", "", "5376e5306283df5bc1fe74d9", "JD Robb", "b", "2001", "public", "Jove", "pocket"));
			coll.insert(populateDoc("Bump in the Night", "0515141178", "own", "", "5376e5306283df5bc1fe74d9", "JD Robb", "b", "2006", "public", "Jove", "pocket"));
			coll.insert(populateDoc("Suite 606", "978-0425224441", "own", "", "5376e5306283df5bc1fe74d9", "JD Robb", "b", "2008", "public", "Jove", "pocket"));
			coll.insert(populateDoc("The Other Side", "978-0515148671", "own", "", "5376e5306283df5bc1fe74d9", "JD Robb", "b", "2010", "public", "Jove", "pocket"));
			coll.insert(populateDoc("The Unquiet", "978-0515149982", "own", "", "5376e5306283df5bc1fe74d9", "JD Robb", "b", "2011", "public", "Jove", "pocket"));

			coll.insert(populateDoc("Dead of Night", "978-0515143676", "want", "false", "5376e5306283df5bc1fe74d9", "JD Robb", "b", "2007", "public", "Jove", "pocket"));
			coll.insert(populateDoc("The Lost", "978-0515147186", "want", "false", "5376e5306283df5bc1fe74d9", "JD Robb", "b", "2009", "public", "Jove", "pocket"));
			coll.insert(populateDoc("Mirror Mirror", "978-0515154078", "want", "false", "5376e5306283df5bc1fe74d9", "JD Robb", "b", "2013", "public", "Jove", "pocket"));
			
        } catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 
    /**
     * 
     * @param name
     * @return
     */
    private BasicDBObject populateDoc(String name, String itemId, String state, String found,
    		String user, String author, String series, String sequence,
    		String privacy, String manufacturer, String format) {
		BasicDBObject doc = new BasicDBObject("name", name)
				.append("itemId", itemId)
				.append("state", state)
				.append("found", found)
				.append("user", user)
				.append("author", author)
				.append("series", series)
				.append("sequence", sequence)
				.append("privacy", privacy)
				.append("manufacturer", manufacturer)
				.append("format", format);
    	
		return doc;
    }
    
    @Test
    public void testOneItemCollection() {
        collection.add("itemA");
        assertEquals(1, collection.size());
        System.out.println("@Test - testOneItemCollection");
    }
}