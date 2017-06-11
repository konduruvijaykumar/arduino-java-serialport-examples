/**
 * 
 */
package org.pjay.sensors;

import java.math.BigDecimal;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Vijay
 *
 */
@Path(value="/rswithJAXRS")
public class RestFullWSWithJAXRS {
	
	@GET
	@Path("/getSensorData")
	@Produces(MediaType.APPLICATION_JSON)
	public SensorDataOld getSensorData(){
		SensorDataOld data = new SensorDataOld();
		data.setLabel("Temperature");
		//String[][] sensorData = {{"03:29:05", "24.5"}, {"03:29:10", "27"}, {"03:29:15", "25"}, {"03:29:20", "24"}, {"03:29:25", "26"}};
		//double[][] sensorData = {{new Date().getTime(), 24.5}, {new Date().getTime(), 27}, {new Date().getTime(), 25}, {new Date().getTime(), 24}, {new Date().getTime(), 26}};
		BigDecimal[][] sensorData = {{new BigDecimal(new Date().getTime()), new BigDecimal(24.5)}, {new BigDecimal(new Date().getTime()), new BigDecimal(27)}, {new BigDecimal(new Date().getTime()), new BigDecimal(25)}, {new BigDecimal(new Date().getTime()), new BigDecimal(24)}, {new BigDecimal(new Date().getTime()), new BigDecimal(26)}};
		data.setData(sensorData);
		return data;
	}
	
	// Received the following error
	// Tried this solution
	// http://stackoverflow.com/questions/13108161/a-message-body-writer-for-java-class-not-found
	// https://jersey.java.net/documentation/1.18/json.html
	/**
			SEVERE: A message body writer for Java class com.vijay.hellouser.SensorDataOld, and Java type class com.vijay.hellouser.SensorDataOld, and MIME media type application/json was not found
			Dec 16, 2015 3:39:19 PM com.sun.jersey.spi.container.ContainerResponse write
			SEVERE: The registered message body writers compatible with the MIME media type are:
			application/json ->
			  com.sun.jersey.json.impl.provider.entity.JSONJAXBElementProvider$App
			  com.sun.jersey.json.impl.provider.entity.JSONArrayProvider$App
			  com.sun.jersey.json.impl.provider.entity.JSONObjectProvider$App
			  com.sun.jersey.json.impl.provider.entity.JSONRootElementProvider$App
			  com.sun.jersey.json.impl.provider.entity.JSONListElementProvider$App
			 ->
			  com.sun.jersey.core.impl.provider.entity.FormProvider
			  com.sun.jersey.core.impl.provider.entity.StringProvider
			  com.sun.jersey.core.impl.provider.entity.ByteArrayProvider
			  com.sun.jersey.core.impl.provider.entity.FileProvider
			  com.sun.jersey.core.impl.provider.entity.InputStreamProvider
			  com.sun.jersey.core.impl.provider.entity.DataSourceProvider
			  com.sun.jersey.core.impl.provider.entity.XMLJAXBElementProvider$General
			  com.sun.jersey.core.impl.provider.entity.ReaderProvider
			  com.sun.jersey.core.impl.provider.entity.DocumentProvider
			  com.sun.jersey.core.impl.provider.entity.StreamingOutputProvider
			  com.sun.jersey.core.impl.provider.entity.SourceProvider$SourceWriter
			  com.sun.jersey.json.impl.provider.entity.JSONJAXBElementProvider$General
			  com.sun.jersey.json.impl.provider.entity.JSONArrayProvider$General
			  com.sun.jersey.json.impl.provider.entity.JSONObjectProvider$General
			  com.sun.jersey.json.impl.provider.entity.JSONWithPaddingProvider
			  com.sun.jersey.server.impl.template.ViewableMessageBodyWriter
			  com.sun.jersey.core.impl.provider.entity.XMLRootElementProvider$General
			  com.sun.jersey.core.impl.provider.entity.XMLListElementProvider$General
			  com.sun.jersey.json.impl.provider.entity.JSONRootElementProvider$General
			  com.sun.jersey.json.impl.provider.entity.JSONListElementProvider$General
			  com.sun.jersey.json.impl.provider.entity.JacksonProviderProxy
	 */
	
}
