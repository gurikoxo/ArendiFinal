package edu.gyte.bitirme.arendi.degerlendirmekriterleri;

import java.io.Serializable;
import java.util.ArrayList;

public class DegerlendirmeKriterJson implements Serializable{
	private ArrayList<DegerlendirmeKriteri> kriterlist;
	private Integer success;
	
	public ArrayList<DegerlendirmeKriteri> getKriterlist() {
		return kriterlist;
	}
	public void setKriterlist(ArrayList<DegerlendirmeKriteri> kriterlist) {
		this.kriterlist = kriterlist;
	}
	public Integer getSuccess() {
		return success;
	}
	public void setSuccess(Integer success) {
		this.success = success;
	}
	
	
}
