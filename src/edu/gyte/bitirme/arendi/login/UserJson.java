package edu.gyte.bitirme.arendi.login;

import java.io.Serializable;

public class UserJson implements Serializable {
	private User user ;
	private Integer success;

	public Integer getSuccess() {
		return success;
	}
	public void setSuccess(Integer success) {
		this.success = success;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
