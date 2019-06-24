package com.wcl.domain;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class Admin {
	private Integer id;
	private String username;
	private String password;
	private String type;

	@Override
	public String toString() {
		return "Admin{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", type='" + type + '\'' +
				'}';
	}
}
