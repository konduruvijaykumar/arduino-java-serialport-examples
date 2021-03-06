/**
 * 
 */
package org.pjay.sensors.test;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * @author Vijay
 *
 */
public class TestRestfulWSWithJAXRS {
	public static void main(String[] args) {
		//http://localhost:8080/ArduinoTemperatureHumidityApp/services/rswithJAXRS/getSensorData
		ClientConfig config = new DefaultClientConfig();
	    Client client = Client.create(config);
	    WebResource service = client.resource(getBaseURI());
	    // Fluent interfaces
	    System.out.println(service.path("services").path("rswithJAXRS").path("getSensorData").accept(MediaType.APPLICATION_JSON).get(ClientResponse.class).toString());
	    System.out.println(service.path("services").path("rswithJAXRS").path("getSensorData").accept(MediaType.APPLICATION_JSON).get(String.class));
	}
	
	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/ArduinoTemperatureHumidityApp").build();
	}
}
