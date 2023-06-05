package com.meeting.planner.dto;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReunionDto {
	
	private String nom;
	
	@JsonFormat(pattern = "HH:mm")
	private LocalTime heureDebut;
	
	private int nombreDePersonne;
	private TypeReunionDto typeReunion;
	
	public ReunionDto() {
		super();
	}

	public ReunionDto(String nom, LocalTime heureDebut, int nombreDePersonne,
			TypeReunionDto typeReunion) {
		super();
		this.nom = nom;
		this.heureDebut = heureDebut;
		this.nombreDePersonne = nombreDePersonne;
		this.typeReunion = typeReunion;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public LocalTime getHeureDebut() {
		return heureDebut;
	}

	public void setHeureDebut(LocalTime heureDebut) {
		this.heureDebut = heureDebut;
	}

	public int getNombreDePersonne() {
		return nombreDePersonne;
	}

	public void setNombreDePersonne(int nombreDePersonne) {
		this.nombreDePersonne = nombreDePersonne;
	}

	public TypeReunionDto getTypeReunion() {
		return typeReunion;
	}

	public void setTypeReunion(TypeReunionDto typeReunion) {
		this.typeReunion = typeReunion;
	}
	
}
