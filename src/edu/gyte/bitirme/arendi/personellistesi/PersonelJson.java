package edu.gyte.bitirme.arendi.personellistesi;

import java.io.Serializable;
import java.util.ArrayList;

import edu.gyte.bitirme.arendi.login.User;

public class PersonelJson implements Serializable {
	private ArrayList<User> userlist ;
	private Integer success;
	
	public ArrayList<User> getUserlist() {
		return userlist;
	}
	public void setUserlist(ArrayList<User> userlist) {
		this.userlist = userlist;
	}
	public Integer getSuccess() {
		return success;
	}
	public void setSuccess(Integer success) {
		this.success = success;
	}
	
	
}
