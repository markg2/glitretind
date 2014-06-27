package com.olafrye;

import com.google.inject.AbstractModule;
import com.olafrye.db.ConnectionManager;
import com.olafrye.db.MongoConnectionManager;

public class MainModule extends AbstractModule {

	@Override
	protected void configure() {
		// TODO Auto-generated method stub
		bind(ConnectionManager.class).to(MongoConnectionManager.class);
	}

}
