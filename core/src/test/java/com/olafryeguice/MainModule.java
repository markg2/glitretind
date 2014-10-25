package com.olafryeguice;

import com.google.inject.AbstractModule;

public class MainModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(ConnectionManager.class).to(MongoConnectionManager.class);
	}
}
