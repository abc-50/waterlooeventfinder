/**
 * 
 */
package com.waterlooeventfinder2.shared;

import java.io.Serializable;

/**
 * @author Mike
 *
 */
public class Time implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int timeId;
	private String timeDisplayValue;
	
	public Time(){
		
	}
	
	public Time(int timeId, String timeDisplayValue) {
		this.timeId = timeId;
		this.timeDisplayValue = timeDisplayValue;
	}

	public String getTimeDisplayValue() {
		return timeDisplayValue;
	}



	public int getTimeId() {
		return timeId;
	}

}
