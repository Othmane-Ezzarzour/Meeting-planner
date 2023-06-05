package com.meeting.planner.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meeting.planner.dto.SalleDto;
import com.meeting.planner.dto.mapper.MapClassWithDto;
import com.meeting.planner.exception.CustomException;
import com.meeting.planner.model.Salle;
import com.meeting.planner.repository.SalleRepository;
import com.meeting.planner.service.SalleService;

@Service
public class SalleServiceImpl implements SalleService {
	
	@Autowired
	private SalleRepository salleRepository; 
	
	@Autowired
    private MapClassWithDto<Salle, SalleDto> mapSalle;

	@Override
	public SalleDto ajouterSalle(SalleDto salleDto) {

		Salle salle = mapSalle.convertToEntity(salleDto, Salle.class);
		Salle newSalle = salleRepository.save(salle);
		SalleDto newSalleDto = mapSalle.convertToDto(newSalle, SalleDto.class);
		return newSalleDto;
	}

	@Override
	public SalleDto modifierSalle(long id, SalleDto salleDto) throws Exception {
		Salle salle = salleRepository.findById(id).orElseThrow(() -> new CustomException("Id not found " + id));
		salle.setNom(salleDto.getNom());
		salle.setNombreDePlace(salleDto.getNombreDePlace());
		Salle newSalle = salleRepository.save(salle);
		SalleDto newSalleDto = mapSalle.convertToDto(newSalle, SalleDto.class);
				
		return newSalleDto;
	}

	@Override
	public void supprimerSalle(long id) throws Exception {
		Salle salle = salleRepository.findById(id).orElseThrow(() -> new CustomException("Id not found " + id));
		salleRepository.delete(salle);
	}

	@Override
	public List<SalleDto> allSalles() {
		List<Salle> salle = salleRepository.findAll();
		List<SalleDto> salleDto = mapSalle.convertListToListDto(salle, SalleDto.class);
		return salleDto;
	}

}
