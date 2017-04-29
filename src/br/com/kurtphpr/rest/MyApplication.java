package br.com.kurtphpr.rest;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Application;

public class MyApplication extends Application {

	public Map<String, Object> getProperties() {
		Map<String, Object> properties = new HashMap<>();
		// Configura o pagote para fazer scan das classes com anotações REST.
		properties.put("jersey.config.server.provider.packages", "br.com.kurtphpr");
		return properties;
	}
}
