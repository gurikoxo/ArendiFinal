package edu.gyte.bitirme.arendi.fikirlistesi;

import java.util.ArrayList;

import edu.gyte.bitirme.arendi.degerlendirmekriterleri.DegerlendirmeKriteri;

public class FikirPuanItem {
	private ArrayList<DegerlendirmeKriteri> kriterList;
	private boolean checked;
	private double puan;
	
	public ArrayList<DegerlendirmeKriteri> getKriterList() {
		return kriterList;
	}
	public void setKriterList(ArrayList<DegerlendirmeKriteri> kriterList) {
		this.kriterList = kriterList;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public double getPuan() {
		return puan;
	}
	public void setPuan(double puan) {
		this.puan = puan;
	}
	
	
}
