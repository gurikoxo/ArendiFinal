package edu.gyte.bitirme.arendi.degerlendirmekriterleri;

import java.io.Serializable;

public class DegerlendirmeKriteri implements Serializable{
	private int id;
	private String kriterAdi;
	private int katsayi;
	private int firmaid;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKriterAdi() {
		return kriterAdi;
	}
	public void setKriterAdi(String kriterAdi) {
		this.kriterAdi = kriterAdi;
	}
	public int getKatsayi() {
		return katsayi;
	}
	public void setKatsayi(int katsayi) {
		this.katsayi = katsayi;
	}
	public int getFirmaid() {
		return firmaid;
	}
	public void setFirmaid(int firmaid) {
		this.firmaid = firmaid;
	}
	
	
}
