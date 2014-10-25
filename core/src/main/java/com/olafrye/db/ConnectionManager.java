package com.olafrye.db;

import com.mongodb.DB;

public interface ConnectionManager {
	public DB getConnection();
	public String getDatabaseUri();
}
