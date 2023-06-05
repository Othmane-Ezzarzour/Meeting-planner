package com.meeting.planner.model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Reunion implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String nom;
	
	@Column(name = "date_reunion")
	private Date dateReunion;
	
	@Column(name = "heure_debut")
	@JsonFormat(pattern = "HH:mm")
	private LocalTime heureDebut;
	
	@Column(name = "heure_fin")
	@JsonFormat(pattern = "HH:mm")
	private LocalTime heureFin;
	
	@Column(name = "nombre_de_personne")
	private int nombreDePersonne;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "type_reunion_id")
	private TypeReunion typeReunion;
	
	@ManyToOne
	@JoinColumn(name = "salle_id")
	private Salle salle;
	
	@OneToMany(mappedBy = "reunion", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ReserverEquipementParHeure> equipementParHeures;
	
	public Reunion() {
		super();
	}
	
	public Reunion(long id, String nom, Date dateReunion, LocalTime heureDebut, LocalTime heureFin, int nombreDePersonne,
			TypeReunion typeReunion, Salle salle, List<ReserverEquipementParHeure> equipementParHeures) {
		super();
		this.id = id;
		this.nom = nom;
		this.dateReunion = dateReunion;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		this.nombreDePersonne = nombreDePersonne;
		this.typeReunion = typeReunion;
		this.salle = salle;
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

	public Date getDateReunion() {
		return dateReunion;
	}

	public void setDateReunion(Date dateReunion) {
		this.dateReunion = dateReunion;
	}

	public LocalTime getHeureDebut() {
		return heureDebut;
	}

	public void setHeureDebut(LocalTime heureDebut) {
		this.heureDebut = heureDebut;
	}

	public LocalTime getHeureFin() {
		return heureFin;
	}

	public void setHeureFin(LocalTime heureFin) {
		this.heureFin = heureFin;
	}

	public int getNombreDePersonne() {
		return nombreDePersonne;
	}

	public void setNombreDePersonne(int nombreDePersonne) {
		this.nombreDePersonne = nombreDePersonne;
	}

	public TypeReunion getTypeReunion() {
		return typeReunion;
	}

	public void setTypeReunion(TypeReunion typeReunion) {
		this.typeReunion = typeReunion;
	}

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}

	public List<ReserverEquipementParHeure> getEquipementParHeures() {
		return equipementParHeures;
	}

	public void setEquipementParHeures(List<ReserverEquipementParHeure> equipementParHeures) {
		this.equipementParHeures = equipementParHeures;
	}
	
}
