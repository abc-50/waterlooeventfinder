package com.waterlooeventfinder2.shared;

public class User {
	private int userId;
	private String loginId;
	private String password;
	private String displayName;
	private String description;
	private String phoneNumber;
	private int userTypeId;

	public User(String login, String password) {
		loginId = login;
		this.password = password;
	}

	public String DisplayName() {
		return displayName;
	}

	public void SetDisplayName(String name) {
		displayName = name;
	}

	public String Description() {
		return description;
	}

	public void SetDescription(String desc) {
		description = desc;
	}

}
