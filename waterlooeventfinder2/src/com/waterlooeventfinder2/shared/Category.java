package com.waterlooeventfinder2.shared;

public class Category {
	private int categoryId;
	private String categoryName;
	
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
