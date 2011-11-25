package com.waterlooeventfinder2.shared;

import java.io.Serializable;

public class Category implements Serializable{
	private int categoryId;
	private String categoryName;
	
	public Category(){
		
	}
	
	public Category(int id, String name) {
		this.categoryId = id;
		this.categoryName = name;
	}
	
	
	public int getCategoryId() {
		return categoryId;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	
}
