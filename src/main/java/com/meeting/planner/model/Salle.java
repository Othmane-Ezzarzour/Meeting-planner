package com.meeting.planner.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Salle implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String nom;
	
	@Column(name = "nombre_de_place")
	private int nombreDePlace;
	
	@OneToMany(mappedBy = "salle", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Equipement> equipments;
	
	@OneToMany(mappedBy = "salle", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Reunion> reunions;
	
	 @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	 @JoinTable(name = "salle_type_reunion", joinColumns = { @JoinColumn(name = "salle_id") },
		        inverseJoinColumns = { @JoinColumn(name = "type_reunion_id") })
	private List<TypeReunion> typeReunions;
	
	public Salle() {
		super();
	}

	public Salle(long id, String nom, int nombreDePlace, List<Equipement> equipments) {
		super();
		this.id = id;
		this.nom = nom;
		this.nombreDePlace = nombreDePlace;
		this.equipments = equipments;
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

	public int getNombreDePlace() {
		return nombreDePlace;
	}

	public void setNombreDePlace(int nombreDePlace) {
		this.nombreDePlace = nombreDePlace;
	}

	public List<Equipement> getEquipments() {
		return equipments;
	}

	public void setEquipments(List<Equipement> equipments) {
		this.equipments = equipments;
	}
		
}
