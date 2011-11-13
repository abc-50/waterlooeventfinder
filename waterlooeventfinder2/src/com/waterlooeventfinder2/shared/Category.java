package com.waterlooeventfinder2.shared;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Category {
	private int categoryId;
	private String categoryName;
	
	public Category(int id, String name) {
		this.categoryId = id;
		this.categoryName = name;
	}
	
//	public Category(ResultSet rs) {
//		try {
//			categoryId = rs.getInt("categoryId");
//			categoryName = rs.getString("categoryName");
//
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	public int getCategoryId() {
		return categoryId;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	
}
