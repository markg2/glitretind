package com.olafryeguice;

import com.mongodb.DB;

public interface ConnectionManager {
	public DB getConnection();
}
