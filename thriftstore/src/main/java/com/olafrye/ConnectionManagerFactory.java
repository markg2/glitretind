package com.olafrye;

import com.mongodb.DB;

public final class ConnectionManagerFactory {
	/** This member holds the singleton instance of the factory. */
	private static ConnectionManagerFactory m_this;
	
	/** This member provides access to the mongoDB database */
	private DB m_db;

	/**
	 * This method returns the Mongo connection
	 */
	public DB getConnection() {
		return m_db;
	}
	
	/**
	 * This method stores a Mongo connection
	 */
	public void register(DB db) {
			m_db = db;
	}
	
	/**
	 * This method returns the singleton factory instance.
	 */
	public static ConnectionManagerFactory getFactory() {
		if (m_this == null) {
			internalCreate();
		}

		return m_this;
	}

	/**
	 * This method creates the one instance of the factory.
	 */
	private static synchronized void internalCreate() {
		if (m_this == null) {
			m_this = new ConnectionManagerFactory();
		}
	}
	
}
