package com.meeting.planner.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Equipement {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String nom;
	
	@ManyToOne
	@JoinColumn(name = "salle_id")
	private Salle salle;
	
	@ManyToOne
	@JoinColumn(name = "type_reunion_id")
	private TypeReunion typeReunion;
	
	@OneToMany(mappedBy = "equipement", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ReserverEquipementParHeure> equipementParHeures;
	

	public Equipement() {
		super();
	}

	public Equipement(long id, String nom, Salle salle, TypeReunion typeReunion,
			List<ReserverEquipementParHeure> equipementParHeures) {
		super();
		this.id = id;
		this.nom = nom;
		this.salle = salle;
		this.typeReunion = typeReunion;
		this.equipementParHeures = equipementParHeures;
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

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}

	public TypeReunion getTypeReunion() {
		return typeReunion;
	}

	public void setTypeReunion(TypeReunion typeReunion) {
		this.typeReunion = typeReunion;
	}

	public List<ReserverEquipementParHeure> getEquipementParHeures() {
		return equipementParHeures;
	}

	public void setEquipementParHeures(List<ReserverEquipementParHeure> equipementParHeures) {
		this.equipementParHeures = equipementParHeures;
	}
		
}