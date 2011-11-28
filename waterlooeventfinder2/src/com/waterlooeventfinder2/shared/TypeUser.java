package com.waterlooeventfinder2.shared;

import java.io.Serializable;

public class TypeUser implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int typeId;
	private String userType;
	
	
	public TypeUser(){
		
	}
	
	public TypeUser(int typeIdValue, String userTypeValue){
		this.typeId = typeIdValue;
		this.userType = userTypeValue;
	}

	/**
	 * @param typeId the typeId to set
	 */
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	/**
	 * @return the typeId
	 */
	public int getTypeId() {
		return typeId;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}
	
	
}
