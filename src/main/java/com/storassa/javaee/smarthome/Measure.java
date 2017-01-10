package com.storassa.javaee.smarthome;

import java.io.Serializable;
import javax.persistence.*;

/**d
 * Entity implementation class for Entity: Measure
 *
 */
@Entity
public class Measure implements Serializable {

	@Id @GeneratedValue (strategy = GenerationType.AUTO)
	private long id;
	
	private String time;
	
	private double[] temp;
	
	private double[] humidity;
	    
	public double[] getTemp() {
		return temp;
	}

	public void setTemp(double[] temp) {
		this.temp = temp;
	}

	public double[] getHumidity() {
		return humidity;
	}

	public void setHumidity(double[] humidity) {
		this.humidity = humidity;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}


	private static final long serialVersionUID = 1L;

	public Measure() {
		super();
	}
	
	public Measure(double[] _temp, double[] _humidity) {
		temp = _temp;
		humidity = _humidity;
		time = "";
	}
   
}
