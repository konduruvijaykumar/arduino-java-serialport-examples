/**
 * 
 */
package org.pjay.serialportreading.arduino.savedata;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Vijay
 * 
 */
@Entity
@Table(name="SENSOR_DATA")
public class SensorData {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int id;
	@Column(name="TEMPERATURE")
	private float temperature;
	@Column(name="HUMIDITY")
	private float humidity;
	@Column(name="READING_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date readingDateTime;
	
	public SensorData() {
	}
	
	public SensorData(float humidity, float temperature, Date readingDateTime){
		this.humidity = humidity;
		this.temperature = temperature;
		this.readingDateTime = readingDateTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public float getHumidity() {
		return humidity;
	}

	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}

	public Date getReadingDateTime() {
		return readingDateTime;
	}

	public void setReadingDateTime(Date readingDateTime) {
		this.readingDateTime = readingDateTime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(humidity);
		result = prime * result
				+ ((readingDateTime == null) ? 0 : readingDateTime.hashCode());
		result = prime * result + Float.floatToIntBits(temperature);
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof SensorData)) {
			return false;
		}
		SensorData other = (SensorData) obj;
		if (Float.floatToIntBits(humidity) != Float
				.floatToIntBits(other.humidity)) {
			return false;
		}
		if (readingDateTime == null) {
			if (other.readingDateTime != null) {
				return false;
			}
		} else if (!readingDateTime.equals(other.readingDateTime)) {
			return false;
		}
		if (Float.floatToIntBits(temperature) != Float
				.floatToIntBits(other.temperature)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SensorData [temperature=" + temperature + ", humidity="
				+ humidity + ", readingDateTime=" + readingDateTime + "]";
	}
	
}
