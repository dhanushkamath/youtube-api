package com.dhanushkamath.youtubeapi.info;

public class Info {
	String name;
	String description;
	String status;
	
	public Info() {
		status = Constants.INFO_DEFAULT_STATUS;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
