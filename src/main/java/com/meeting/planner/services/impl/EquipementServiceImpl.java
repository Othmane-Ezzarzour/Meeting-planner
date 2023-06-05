package com.meeting.planner.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meeting.planner.dto.EquipementDto;
import com.meeting.planner.dto.mapper.MapClassWithDto;
import com.meeting.planner.model.Equipement;
import com.meeting.planner.model.Salle;
import com.meeting.planner.model.TypeReunion;
import com.meeting.planner.repository.EquipementRepository;
import com.meeting.planner.repository.SalleRepository;
import com.meeting.planner.repository.TypeReunionRepository;
import com.meeting.planner.service.EquipementService;

@Service
public class EquipementServiceImpl implements EquipementService {
	
	@Autowired
	private EquipementRepository equipementRepository;
	
	@Autowired
	private SalleRepository salleRepository;
	
	@Autowired
	private TypeReunionRepository typeReunionRepository;
	
	@Autowired
    private MapClassWithDto<Equipement, EquipementDto> mapEquipment;

	@Override
	public EquipementDto ajouterEquipmentParSalle(long salleId, EquipementDto equipementDto) throws Exception {
		Salle salle = salleRepository.findById(salleId).get();
		if(salle == null) {
			throw new Exception("Id not found" + salleId);
		}
		Equipement equipement = mapEquipment.convertToEntity(equipementDto, Equipement.class);
		equipement.setSalle(salle);
		Equipement newEquipment = equipementRepository.save(equipement);
		
        EquipementDto newEquipmentDto = mapEquipment.convertToDto(newEquipment, EquipementDto.class);

        return newEquipmentDto;
	}
	
	public EquipementDto ajouterEquipmentTypeReunion(long typeReunionId, EquipementDto equipementDto) throws Exception {
		TypeReunion typeReunion = typeReunionRepository.findById(typeReunionId).get();
		if(typeReunion == null) {
			throw new Exception("Id not found" + typeReunionId);
		}
		Equipement equipement = mapEquipment.convertToEntity(equipementDto, Equipement.class);
		equipement.setTypeReunion(typeReunion);
		Equipement newEquipment = equipementRepository.save(equipement);
		
        EquipementDto newEquipmentDto = mapEquipment.convertToDto(newEquipment, EquipementDto.class);

        return newEquipmentDto;
	}
	
	@Override
	public EquipementDto ajouterEquipment(EquipementDto equipementDto) {

		Equipement equipement = mapEquipment.convertToEntity(equipementDto, Equipement.class);
		Equipement newEquipment = equipementRepository.save(equipement);	
        EquipementDto newEquipmentDto = mapEquipment.convertToDto(newEquipment, EquipementDto.class);

        return newEquipmentDto;
	}

	@Override
	public EquipementDto modifierEquipment(long id, EquipementDto equipementDto) throws Exception {
		Equipement equipement = equipementRepository.findById(id).get();
		if (equipement == null) {
			throw new Exception("Id not found" + id);
		}
		equipement.setNom(equipementDto.getNom());
		Equipement equipmentModifier = equipementRepository.save(equipement);
		EquipementDto equipmentModifierDto = mapEquipment.convertToDto(equipmentModifier, EquipementDto.class);
		
		return equipmentModifierDto;
	}

	@Override
	public void supprimerEquipment(long id) throws Exception {
		Equipement equipement = equipementRepository.findById(id).get();	
		if (equipement == null) {
			throw new Exception("Id not found" + id);
		}
		equipementRepository.delete(equipement);
	}

	@Override
	public List<EquipementDto> allEquipments() {
		List<Equipement> equipement = equipementRepository.findAll();
		List<EquipementDto> equipementDto = mapEquipment.convertListToListDto(equipement, EquipementDto.class);
		
		return equipementDto;
	}

}
