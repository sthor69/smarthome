package com.storassa.javaee.smarthome;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Monitor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int[] water;
	boolean alarm;
	
	@Id @GeneratedValue
	long Id;
	
	public int[] getWater() {
		return water;
	}
	public void setWater(int[] water) {
		this.water = water;
	}
	public boolean isAlarm() {
		return alarm;
	}
	public void setAlarm(boolean alarm) {
		this.alarm = alarm;
	}
	
}
