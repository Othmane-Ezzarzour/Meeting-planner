package com.meeting.planner.model;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "reserver_equipement_par_heure")
public class ReserverEquipementParHeure {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "heure_debut_reservation")
	@JsonFormat(pattern = "HH:mm")
	private LocalTime heureDebutReservation;	
	
	@Column(name = "heure_fin_reservation")
	@JsonFormat(pattern = "HH:mm")
	private LocalTime heureFinReservation;
	
	@ManyToOne
	@JoinColumn(name = "reunion_id")
	private Reunion reunion;
	
	@ManyToOne
	@JoinColumn(name = "equipement_id")
	private Equipement equipement;
	
	public ReserverEquipementParHeure() {
		super();
	}

	public ReserverEquipementParHeure(long id, LocalTime heureDebutReservation, LocalTime heureFinReservation,
			Equipement equipement) {
		super();
		this.id = id;
		this.heureDebutReservation = heureDebutReservation;
		this.heureFinReservation = heureFinReservation;
		this.equipement = equipement;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalTime getHeureDebutReservation() {
		return heureDebutReservation;
	}

	public void setHeureDebutReservation(LocalTime heureDebutReservation) {
		this.heureDebutReservation = heureDebutReservation;
	}

	public LocalTime getHeureFinReservation() {
		return heureFinReservation;
	}

	public void setHeureFinReservation(LocalTime heureFinReservation) {
		this.heureFinReservation = heureFinReservation;
	}

	public Equipement getEquipement() {
		return equipement;
	}

	public void setEquipement(Equipement equipement) {
		this.equipement = equipement;
	}
	
}
