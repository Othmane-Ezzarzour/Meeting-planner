package com.meeting.planner.dto;

public class SalleDto {
	
	private String nom;
	private int nombreDePlace;
	
	public SalleDto() {
		super();
	}

	public SalleDto(String nom, int nombreDePlace) {
		super();
		this.nom = nom;
		this.nombreDePlace = nombreDePlace;
	}

	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public int getNombreDePlace() {
		return nombreDePlace;
	}
	
	public void setNombreDePlace(int nombreDePlace) {
		this.nombreDePlace = nombreDePlace;
	}
	
}
