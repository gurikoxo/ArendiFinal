package edu.gyte.bitirme.arendi.fikirlistesi;

import java.io.Serializable;

import edu.gyte.bitirme.arendi.fikirekle.FikirGizlilik;

public class Fikir implements Serializable {
	private int id ;
	private String baslik;
	private String aciklama;
	private FikirGizlilik gizlilik;
	private String image ; 
	private Double puan;
	private String username ;
	private int userid;
	private int firmaid;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBaslik() {
		return baslik;
	}
	public void setBaslik(String baslik) {
		this.baslik = baslik;
	}
	public String getAciklama() {
		return aciklama;
	}
	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}
	public FikirGizlilik getGizlilik() {
		return gizlilik;
	}
	public void setGizlilik(FikirGizlilik gizlilik) {
		this.gizlilik = gizlilik;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getFirmaid() {
		return firmaid;
	}
	public void setFirmaid(int firmaid) {
		this.firmaid = firmaid;
	}
	public Double getPuan() {
		return puan;
	}
	public void setPuan(Double puan) {
		this.puan = puan;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	} 
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	
}
