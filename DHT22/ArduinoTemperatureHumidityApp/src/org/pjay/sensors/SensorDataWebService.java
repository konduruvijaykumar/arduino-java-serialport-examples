/**
 * 
 */
package org.pjay.sensors;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Vijay
 * Solving CORS(Cross-Origin Resource Sharing) issue
 * http://stackoverflow.com/questions/18234366/restful-webservice-how-to-set-headers-in-java-to-accept-xmlhttprequest-allowed
 * http://enable-cors.org/
 *
 */
@Path("/sensors")
public class SensorDataWebService {

	@GET
	@Path("/sensordata")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSensorData(){
		SensorData data = new SensorData();
		// TODO: Not a good practice to include JDBC code in controller, but to complete the coding faster i am following this
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String SELECT_SENSORS_DATA = "SELECT * FROM sensor_data ORDER BY READING_TIME DESC LIMIT 1";
		String COLUMN_HUMIDITY = "HUMIDITY";
		String COLUMN_TEMPERATURE = "TEMPERATURE";
		String COLUMN_READING_TIME = "READING_TIME";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sensors", "root", "");
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SELECT_SENSORS_DATA);
			for(;resultSet.next();){
				data.setHumidity(resultSet.getInt(COLUMN_HUMIDITY));
				data.setTemperature(resultSet.getFloat(COLUMN_TEMPERATURE));
				data.setTime(new BigDecimal(resultSet.getTimestamp(COLUMN_READING_TIME).getTime()));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {if(null != connection)connection.close();} catch (SQLException e) {e.printStackTrace();}
			try {if(null != statement)statement.close();} catch (SQLException e) {e.printStackTrace();}
			try {if(null != resultSet)resultSet.close();} catch (SQLException e) {e.printStackTrace();}
		}
		
		//return data;
		return Response.ok(data).build();
	}
	
	@GET
	@Path("/sensordataold")
	@Produces(MediaType.APPLICATION_JSON)
	// Still with below code i could not solve CORS issue, hence trying cors filter implementation
	public Response getSensorDataOld(){
		SensorData data = new SensorData();
		// TODO: Not a good practice to include JDBC code in controller, but to complete the coding faster i am following this
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String SELECT_SENSORS_DATA = "SELECT * FROM sensor_data ORDER BY READING_TIME DESC LIMIT 1";
		String COLUMN_HUMIDITY = "HUMIDITY";
		String COLUMN_TEMPERATURE = "TEMPERATURE";
		String COLUMN_READING_TIME = "READING_TIME";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sensors", "root", "");
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SELECT_SENSORS_DATA);
			for(;resultSet.next();){
				data.setHumidity(resultSet.getInt(COLUMN_HUMIDITY));
				data.setTemperature(resultSet.getFloat(COLUMN_TEMPERATURE));
				data.setTime(new BigDecimal(resultSet.getTimestamp(COLUMN_READING_TIME).getTime()));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {if(null != connection)connection.close();} catch (SQLException e) {e.printStackTrace();}
			try {if(null != statement)statement.close();} catch (SQLException e) {e.printStackTrace();}
			try {if(null != resultSet)resultSet.close();} catch (SQLException e) {e.printStackTrace();}
		}
		
		return Response.ok(data).header("Access-Control-Allow-Origin", "*").build();
	}

}
