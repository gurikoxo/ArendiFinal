package edu.gyte.bitirme.arendi.services;

import java.util.ArrayList;

import edu.gyte.bitirme.arendi.firmalistesi.Firma;
import edu.gyte.bitirme.arendi.firmalistesi.FirmaDurum;
import edu.gyte.bitirme.arendi.firmalistesi.FirmaOlcegi;

public class FirmaService {

	private static ArrayList<Firma> firmaList = new ArrayList<Firma>();

	public static void loadService(){
		fillFirmaList();
	}
	
	public static void addFirma(Firma firma){
		firmaList.add(firma);
	}
	public static void removeFirma(Firma firma){
		firmaList.remove(firma);
	}
	
	public static ArrayList<Firma> getFirmaList() {
		return firmaList;
	}
	
	private static void fillFirmaList() {
		Firma firma = new Firma( "Turkcell", "4441444",
				"turkcell@turkcell.com", "www.turkcell.com.tr",
				FirmaOlcegi.Büyük, "Istanbul Turkiye", FirmaDurum.Aktif);
		firmaList.add(firma);
		
		firma = new Firma( "Globit Global Bilgi Teknolojileri", "02162161234",
				"globit@globit.com", "www.globit.com.tr",
				FirmaOlcegi.Kobi, "Kocaeli Turkiye", FirmaDurum.Aktif);
		firmaList.add(firma);
		
		firma = new Firma( "Vodafone ", "4442123",
				"vodafone@vodafone.com", "www.vodafone.com.tr",
				FirmaOlcegi.Büyük, "Istanbul Turkiye", FirmaDurum.Aktif);
		firmaList.add(firma);
		
		firma = new Firma( "32Bit BiliÅŸim", "02622345432",
				"turkcell@turkcell.com", "www.32bit.com.tr",
				FirmaOlcegi.Kobi, "Istanbul Turkiye", FirmaDurum.Pasif);
		firmaList.add(firma);
		
		firma = new Firma( "Humenix", "03623259486",
				"humenix@humenix.com", "www.humenix.com.tr",
				FirmaOlcegi.Mikro, "Istanbul Turkiye", FirmaDurum.Aktif);
		firmaList.add(firma);
		
		firma = new Firma("Evoline TR", "02163258724",
				"evoline@evoline.com", "www.evoline.com.tr",
				FirmaOlcegi.Kobi, "Istanbul Turkiye", FirmaDurum.Pasif);
		firmaList.add(firma);
		
	}
	
	public static Firma getFirmaById(int firmaId){
		return firmaList.get(firmaId);
	}
	

}
