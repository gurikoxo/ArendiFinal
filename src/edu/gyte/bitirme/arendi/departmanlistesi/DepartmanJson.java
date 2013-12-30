package edu.gyte.bitirme.arendi.departmanlistesi;

import java.io.Serializable;
import java.util.ArrayList;

public class DepartmanJson implements Serializable {
	private ArrayList<Departman> departmanlist ; 
	private Integer success;
	
	public ArrayList<Departman> getDepartmanlist() {
		return departmanlist;
	}
	public void setDepartmanlist(ArrayList<Departman> departmanlist) {
		this.departmanlist = departmanlist;
	}
	public Integer getSuccess() {
		return success;
	}
	public void setSuccess(Integer success) {
		this.success = success;
	}
	
	
}
