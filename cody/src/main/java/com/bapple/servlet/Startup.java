package com.bapple.servlet;

import java.net.UnknownHostException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.bapple.ConnectionManagerFactory;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/**
 * Servlet implementation class Startup
 */
public class Startup extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String DATABASE_URI = "mongodb://bworm:ouat@ds033828.mongolab.com:33828/glitretind";
	public static final String DATABASE_NAME = "glitretind";

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Startup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * This method establishes a connection to the Mongo database.
	 * 
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		
		MongoClient mongoClient = null;
		try {
        	MongoClientURI uri = new MongoClientURI(DATABASE_URI);
			mongoClient = new MongoClient(uri);
		} catch (UnknownHostException e) {
			// TODO log the error.  Jay - log4j I assume??
			// logIt()
			
			throw new ServletException(e);
		}
		
		if (mongoClient != null) {
			DB db = mongoClient.getDB(DATABASE_NAME);
			ConnectionManagerFactory.getFactory().register(db);
			System.out.println("Startup servlet invoked successfully.");
		}
	}

}
