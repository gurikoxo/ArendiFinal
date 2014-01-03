package edu.gyte.bitirme.arendi.projeler;

import java.io.Serializable;
import java.util.ArrayList;

public class ProjeJson implements Serializable{
	private ArrayList<Proje> projelist;
	private Integer success;
	
	public ArrayList<Proje> getProjelist() {
		return projelist;
	}
	public void setProjelist(ArrayList<Proje> projelist) {
		this.projelist = projelist;
	}
	public Integer getSuccess() {
		return success;
	}
	public void setSuccess(Integer success) {
		this.success = success;
	}
	
	
}
