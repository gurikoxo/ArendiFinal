package edu.gyte.bitirme.arendi.projekategorilistesi;

import java.io.Serializable;

public class ProjeKategori implements Serializable {
	private int id;
	private String kategoriadi = null;
	private Integer firmaid = null;
 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKategoriadi() {
		return kategoriadi;
	}
	public void setKategoriadi(String kategoriadi) {
		this.kategoriadi = kategoriadi;
	}
	public Integer getFirmaid() {
		return firmaid;
	}
	public void setFirmaid(Integer firmaid) {
		this.firmaid = firmaid;
	}
	
	
}
