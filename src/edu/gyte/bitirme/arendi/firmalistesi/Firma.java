package edu.gyte.bitirme.arendi.firmalistesi;

public class Firma {
	private Integer id = null;
	private String firmaAdi = null;
	private String telNo = null;
	private String ePosta = null;
	private String webAdres = null;
	private FirmaOlcegi firmaOlcegi = null;
	private String adres = null;
	private FirmaDurum firmaDurum = null;
	private static Integer  idCounter = 0;
	
	
	public Firma( String firmaAdi, String telNo, String ePosta,
			String webAdres, FirmaOlcegi firmaOlcegi, String adres,
			FirmaDurum firmaDurum) {
		this.id = idCounter++;
		this.firmaAdi = firmaAdi;
		this.telNo = telNo;
		this.ePosta = ePosta;
		this.webAdres = webAdres;
		this.firmaOlcegi = firmaOlcegi;
		this.adres = adres;
		this.firmaDurum = firmaDurum;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirmaAdi() {
		return firmaAdi;
	}
	public void setFirmaAdi(String firmaAdi) {
		this.firmaAdi = firmaAdi;
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	public String getePosta() {
		return ePosta;
	}
	public void setePosta(String ePosta) {
		this.ePosta = ePosta;
	}
	public String getWebAdres() {
		return webAdres;
	}
	public void setWebAdres(String webAdres) {
		this.webAdres = webAdres;
	}
	public FirmaOlcegi getFirmaOlcegi() {
		return firmaOlcegi;
	}
	public void setFirmaOlcegi(FirmaOlcegi firmaOlcegi) {
		this.firmaOlcegi = firmaOlcegi;
	}
	public String getAdres() {
		return adres;
	}
	public void setAdres(String adres) {
		this.adres = adres;
	}
	public FirmaDurum getFirmaDurum() {
		return firmaDurum;
	}
	public void setFirmaDurum(FirmaDurum firmaDurum) {
		this.firmaDurum = firmaDurum;
	}
	@Override
	public String toString() {
		return firmaAdi;
	}
	
	
	
}
