package com.waterlooeventfinder2.client;

public class ButtonPressed {
	private String category;
	private String time;
	
	public ButtonPressed(){
		this.category = "All";
		this.time = "Upcoming";
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTime() {
		return time;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCategory() {
		return category;
	}
}


