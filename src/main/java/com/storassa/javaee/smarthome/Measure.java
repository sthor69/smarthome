package com.storassa.javaee.smarthome;

import java.io.Serializable;
import javax.persistence.*;

/**d
 * Entity implementation class for Entity: Measure
 *
 */
@Entity
public class Measure implements Serializable {

	@Id @GeneratedValue
	private long id;
	
	private String time;
	
	private int[] temp;
	
	private boolean[] water;
    
	public int[] getTemp() {
		return temp;
	}

	public void setTemp(int[] temp) {
		this.temp = temp;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public boolean[] getWater() {
		return water;
	}

	public void setWater(boolean[] water) {
		this.water = water;
	}

	private static final long serialVersionUID = 1L;

	public Measure() {
		super();
	}
	
	public Measure(int[] _temp, boolean[] _water) {
		temp = _temp;
		water = _water;
		time = "";
	}
   
}
