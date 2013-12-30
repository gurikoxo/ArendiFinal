package edu.gyte.bitirme.arendi.fikirlistesi;

import java.io.Serializable;
import java.util.ArrayList;

public class FikirJson implements Serializable {
	private ArrayList<Fikir> fikirlist ;
	private Integer success;
	
	public ArrayList<Fikir> getFikirlist() {
		return fikirlist;
	}
	public void setFikirlist(ArrayList<Fikir> fikirlist) {
		this.fikirlist = fikirlist;
	}
	public Integer getSuccess() {
		return success;
	}
	public void setSuccess(Integer success) {
		this.success = success;
	}
	
	
}

