/**
 * 
 */
package org.pjay.sensors;

import java.math.BigDecimal;


/**
 * @author Vijay
 * 
 */
//@XmlRootElement(namespace="sensordata")
public class SensorDataOld {

	private String label;
	private BigDecimal[][] data;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public BigDecimal[][] getData() {
		return data;
	}

	public void setData(BigDecimal[][] data) {
		this.data = data;
	}

}
