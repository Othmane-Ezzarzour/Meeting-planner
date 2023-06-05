package com.meeting.planner.service;

import java.util.List;

import com.meeting.planner.dto.EquipementDto;

public interface EquipementService {
	
	public EquipementDto ajouterEquipmentParSalle(long salleId, EquipementDto equipementDto) throws Exception;
	public EquipementDto ajouterEquipmentTypeReunion(long typeReunionId, EquipementDto equipementDto) throws Exception;
	public EquipementDto ajouterEquipment(EquipementDto equipementDto);
	public EquipementDto modifierEquipment(long id, EquipementDto equipementDto) throws Exception;
	public void supprimerEquipment(long id) throws Exception;
	public List<EquipementDto> allEquipments();

}
