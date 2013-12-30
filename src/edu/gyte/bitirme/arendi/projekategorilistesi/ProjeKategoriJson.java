package edu.gyte.bitirme.arendi.projekategorilistesi;

import java.io.Serializable;
import java.util.ArrayList;

public class ProjeKategoriJson implements Serializable{

	private ArrayList<ProjeKategori> kategorilist ;
	private Integer success;
	
	public ArrayList<ProjeKategori> getKategorilist() {
		return kategorilist;
	}
	public void setKategorilist(ArrayList<ProjeKategori> kategorilist) {
		this.kategorilist = kategorilist;
	}
	public Integer getSuccess() {
		return success;
	}
	public void setSuccess(Integer success) {
		this.success = success;
	}
	
	
}
