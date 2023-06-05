package com.meeting.planner.service;

import java.util.List;

import com.meeting.planner.dto.SalleDto;

public interface SalleService {
	
	public SalleDto ajouterSalle(SalleDto salleDto);
	public SalleDto modifierSalle(long id, SalleDto salleDto) throws Exception;
	public void supprimerSalle(long id) throws Exception;
	public List<SalleDto> allSalles();

}
