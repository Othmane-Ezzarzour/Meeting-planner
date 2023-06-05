package com.meeting.planner.dto;

public class EquipementDto {

	private String nom;
	
	public EquipementDto() {
		super();
	}
			
	public EquipementDto(String nom) {
		super();
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
}
