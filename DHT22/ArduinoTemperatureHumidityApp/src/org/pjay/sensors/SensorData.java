/**
 * 
 */
package org.pjay.sensors;

import java.math.BigDecimal;

/**
 * @author Vijay
 * 
 */
public class SensorData {

	private String temperaturelabel = "Temperature";
	private String humiditylabel = "Humidity";
	private BigDecimal time;
	private float temperature;
	private int humidity;

	public String getTemperaturelabel() {
		return temperaturelabel;
	}

	public void setTemperaturelabel(String temperaturelabel) {
		this.temperaturelabel = temperaturelabel;
	}

	public String getHumiditylabel() {
		return humiditylabel;
	}

	public void setHumiditylabel(String humiditylabel) {
		this.humiditylabel = humiditylabel;
	}

	public BigDecimal getTime() {
		return time;
	}

	public void setTime(BigDecimal time) {
		this.time = time;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

}
