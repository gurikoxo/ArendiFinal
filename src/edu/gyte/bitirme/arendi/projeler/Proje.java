package edu.gyte.bitirme.arendi.projeler;

import java.io.Serializable;

public class Proje implements Serializable {
	
	private int id;
	private String projeadi;
	private int fikirid;
	private String fikirbaslik;
	private String fikiraciklama;
	private String sorumluadi;
	private int sorumluid;
	private String foto;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProjeadi() {
		return projeadi;
	}
	public void setProjeadi(String projeadi) {
		this.projeadi = projeadi;
	}
	public int getFikirid() {
		return fikirid;
	}
	public void setFikirid(int fikirid) {
		this.fikirid = fikirid;
	}
	public int getSorumluid() {
		return sorumluid;
	}
	public void setSorumluid(int sorumluid) {
		this.sorumluid = sorumluid;
	}
	public String getSorumluadi() {
		return sorumluadi;
	}
	public void setSorumluadi(String sorumluadi) {
		this.sorumluadi = sorumluadi;
	}
	public String getFikirbaslik() {
		return fikirbaslik;
	}
	public void setFikirbaslik(String fikirbaslik) {
		this.fikirbaslik = fikirbaslik;
	}
	public String getFikiraciklama() {
		return fikiraciklama;
	}
	public void setFikiraciklama(String fikiraciklama) {
		this.fikiraciklama = fikiraciklama;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	
	
}
