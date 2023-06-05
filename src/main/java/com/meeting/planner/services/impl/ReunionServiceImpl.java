package com.meeting.planner.services.impl;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.meeting.planner.dto.ReunionDto;
import com.meeting.planner.dto.mapper.MapClassWithDto;
import com.meeting.planner.exception.CustomException;
import com.meeting.planner.model.Equipement;
import com.meeting.planner.model.ReserverEquipementParHeure;
import com.meeting.planner.model.Reunion;
import com.meeting.planner.model.Salle;
import com.meeting.planner.model.TypeReunion;
import com.meeting.planner.repository.EquipementRepository;
import com.meeting.planner.repository.ReunionRepository;
import com.meeting.planner.repository.SalleRepository;
import com.meeting.planner.repository.TypeReunionRepository;
import com.meeting.planner.service.ReunionService;

@Service
public class ReunionServiceImpl implements ReunionService {

	@Autowired
	private ReunionRepository reunionRepository;

	@Autowired
	private SalleRepository salleRepository;

	@Autowired
	private TypeReunionRepository typeReunionRepository;

	@Autowired
	private EquipementRepository equipementRepository;

	@Autowired
	private MapClassWithDto<Reunion, ReunionDto> reunionMapper;

	@JsonFormat(pattern = "HH:mm")
	private LocalTime dateDebutReunion = LocalTime.parse("08:00");

	@JsonFormat(pattern = "HH:mm")
	private LocalTime dateFinReunion = LocalTime.parse("20:00");

	@Override
	public ReunionDto ajouterReunion(ReunionDto reunionDto) throws Exception {

		List<Salle> salleAvecNombePlacePlusOuEqual = salleRepository
				.findByNombreDePlaceGreaterThanEqualOrderByNombreDePlaceAsc(reunionDto.getNombreDePersonne());
		TypeReunion typeReunion = typeReunionRepository.findByNom(reunionDto.getTypeReunion().getNom());

		List<String> equipementInTypeReunion = typeReunion.getEquipments().stream().map(e -> e.getNom())
				.collect(Collectors.toList());
		ReunionDto newReunionDto = null;
		for (Salle salle : salleAvecNombePlacePlusOuEqual) {
			List<String> equipementInSalle = salle.getEquipments().stream().map(n -> n.getNom())
					.collect(Collectors.toList());

			if (Arrays.asList(equipementInSalle).containsAll(Arrays.asList(equipementInTypeReunion))) {
				Reunion reunion = reunionRepository.findById(salle.getId()).get();

				if (reunionDto.getHeureDebut().equals(reunion.getHeureDebut())
						|| reunionDto.getHeureDebut().minusHours(1).equals(reunion.getHeureDebut())
						|| reunionDto.getHeureDebut().isBefore(dateDebutReunion)
						|| reunionDto.getHeureDebut().isAfter(dateFinReunion)) {
					throw new CustomException(" tu peux pas reserver dans ce creneaux, merci de choisir une autre heure");
				} else {
					Reunion newReunion = new Reunion();
					newReunion.setNom(reunionDto.getNom());
					newReunion.setHeureDebut(reunionDto.getHeureDebut());
					newReunion.setHeureFin(reunionDto.getHeureDebut().plusHours(1));
					newReunion.setNombreDePersonne(reunionDto.getNombreDePersonne());
					newReunion.setSalle(salle);
					newReunion.setTypeReunion(typeReunion);
					Reunion savedReunion = reunionRepository.save(newReunion);
					newReunionDto = reunionMapper.convertToDto(savedReunion, ReunionDto.class);
					break;
				}
			}
		}

		if (newReunionDto != null) {
			return newReunionDto;

		} else {
			Reunion newReunion = new Reunion();
			for (Salle salle : salleAvecNombePlacePlusOuEqual) {
				List<String> equipementInSalle = salle.getEquipments().stream().map(n -> n.getNom())
						.collect(Collectors.toList());
				List<String> copyEquipementInTypeReunion = new ArrayList<String>(equipementInTypeReunion);
				copyEquipementInTypeReunion.removeAll(equipementInSalle);
				
				for (String nom : copyEquipementInTypeReunion) {
					List<Equipement> equipements = equipementRepository.findByNomParamsNative(nom);				
					List<ReserverEquipementParHeure> equipementsAReserver = new ArrayList<ReserverEquipementParHeure>();
					if (equipements.size() != 0) {
						// set the equipement to reservation and then save the meeting						

						newReunion.setNom(reunionDto.getNom());
						newReunion.setHeureDebut(reunionDto.getHeureDebut());
						newReunion.setHeureFin(reunionDto.getHeureDebut().plusHours(1));
						newReunion.setNombreDePersonne(reunionDto.getNombreDePersonne());
						newReunion.setSalle(salle);
						ReserverEquipementParHeure equipementParHeure = new ReserverEquipementParHeure();
						equipementParHeure.setEquipement(equipements.get(0));
						equipementParHeure.setHeureDebutReservation(reunionDto.getHeureDebut());
						equipementParHeure.setHeureFinReservation(reunionDto.getHeureDebut().plusHours(1));
						equipementsAReserver.add(equipementParHeure);							
						newReunion.setEquipementParHeures(equipementsAReserver);
						newReunion.setTypeReunion(typeReunion);
						
					} else {
						throw new CustomException("Aucun equipement n'est disponible");
					}
				}
			}
			Reunion savedReunion = reunionRepository.save(newReunion);
			newReunionDto = reunionMapper.convertToDto(savedReunion, ReunionDto.class);
			return newReunionDto;
		}
	}

}
