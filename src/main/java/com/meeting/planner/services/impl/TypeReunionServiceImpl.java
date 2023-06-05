package com.meeting.planner.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meeting.planner.dto.TypeReunionDto;
import com.meeting.planner.dto.mapper.MapClassWithDto;
import com.meeting.planner.exception.CustomException;
import com.meeting.planner.model.TypeReunion;
import com.meeting.planner.repository.TypeReunionRepository;
import com.meeting.planner.service.TypeReunionService;

@Service
public class TypeReunionServiceImpl implements TypeReunionService {

	@Autowired
	private TypeReunionRepository typeReunionRepository;

	@Autowired
	private MapClassWithDto<TypeReunion, TypeReunionDto> typeReunionMapper;

	@Override
	public TypeReunionDto ajouterTypeReunion(TypeReunionDto typeReunionDto) {

		TypeReunion typeReunion = typeReunionMapper.convertToEntity(typeReunionDto, TypeReunion.class);
		TypeReunion newTypeReunion = typeReunionRepository.save(typeReunion);
		TypeReunionDto newReunionDto = typeReunionMapper.convertToDto(newTypeReunion, TypeReunionDto.class);
		return newReunionDto;
	}

	@Override
	public TypeReunionDto modifierTypeReunion(long typeReunionId, TypeReunionDto typeReunionDto) throws Exception {
		TypeReunion typeReunion = typeReunionRepository.findById(typeReunionId)
				.orElseThrow(() -> new CustomException("Id not found " + typeReunionId));
		typeReunion.setNom(typeReunionDto.getNom());
		TypeReunion newTypeReunion = typeReunionRepository.save(typeReunion);
		TypeReunionDto newTypeReunionDto = typeReunionMapper.convertToDto(newTypeReunion, TypeReunionDto.class);

		return newTypeReunionDto;
	}

	@Override
	public void supprimerTypeReunion(long typeReunionId) throws Exception {
		TypeReunion typeReunion = typeReunionRepository.findById(typeReunionId)
				.orElseThrow(() -> new CustomException("Id not found " + typeReunionId));

		typeReunionRepository.delete(typeReunion);
	}

	@Override
	public List<TypeReunionDto> typeReunionList() {
		List<TypeReunion> typeReunions = typeReunionRepository.findAll();
		List<TypeReunionDto> typeReunionDto = typeReunionMapper.convertListToListDto(typeReunions,
				TypeReunionDto.class);
		return typeReunionDto;
	}

}
