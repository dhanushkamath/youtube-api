package com.dhanushkamath.youtubeapi.info;

import com.dhanushkamath.youtubeapi.constants.Constants;

public class Info {
	String name;
	String description;
	String status;
	
	public Info() {
		status = Constants.INFO_DEFAULTSTATUS;
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
