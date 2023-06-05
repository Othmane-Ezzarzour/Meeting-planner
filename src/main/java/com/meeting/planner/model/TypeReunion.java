package com.meeting.planner.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "type_reunion")
public class TypeReunion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String nom;

	@OneToMany(mappedBy = "typeReunion", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Equipement> equipments;
	
	@ManyToMany(mappedBy = "typeReunions")	
	private List<Salle> salles;
	
	@OneToMany(mappedBy = "typeReunion", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Reunion> reunions;
	
	public TypeReunion() {
		super();
	}

	public TypeReunion(long id, String nom, List<Equipement> equipments, List<Salle> salles, List<Reunion> reunions) {
		super();
		this.id = id;
		this.nom = nom;
		this.equipments = equipments;
		this.salles = salles;
		this.reunions = reunions;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Equipement> getEquipments() {
		return equipments;
	}

	public void setEquipments(List<Equipement> equipments) {
		this.equipments = equipments;
	}

	public List<Salle> getSalles() {
		return salles;
	}

	public void setSalles(List<Salle> salles) {
		this.salles = salles;
	}

	public List<Reunion> getReunions() {
		return reunions;
	}

	public void setReunions(List<Reunion> reunions) {
		this.reunions = reunions;
	}
		
}
