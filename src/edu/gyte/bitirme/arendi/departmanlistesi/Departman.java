package edu.gyte.bitirme.arendi.departmanlistesi;

import java.io.Serializable;

public class Departman implements Serializable{
	private int id ;
	private String departmanAdi = null;
	private Integer firmaid = null;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDepartmanAdi() {
		return departmanAdi;
	}
	public void setDepartmanAdi(String departmanAdi) {
		this.departmanAdi = departmanAdi;
	}
	public Integer getFirmaid() {
		return firmaid;
	}
	public void setFirmaid(Integer firmaid) {
		this.firmaid = firmaid;
	}
	
	
	
	
}
