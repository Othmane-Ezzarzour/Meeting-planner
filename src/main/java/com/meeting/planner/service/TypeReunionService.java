package com.meeting.planner.service;

import java.util.List;

import com.meeting.planner.dto.TypeReunionDto;

public interface TypeReunionService {
	
	public TypeReunionDto ajouterTypeReunion(TypeReunionDto typeReunion);
	public TypeReunionDto modifierTypeReunion(long typeReunionId, TypeReunionDto typeReunion) throws Exception;
	public void supprimerTypeReunion(long typeReunionId) throws Exception;
	public List<TypeReunionDto> typeReunionList();

}
