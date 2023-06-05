package com.meeting.planner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meeting.planner.dto.EquipementDto;
import com.meeting.planner.dto.ReunionDto;
import com.meeting.planner.dto.SalleDto;
import com.meeting.planner.service.EquipementService;
import com.meeting.planner.service.ReunionService;
import com.meeting.planner.service.SalleService;

@RestController
@RequestMapping("/api/v1")
public class GlobalController {
	
	@Autowired
	private EquipementService equipementService;
	
	@Autowired
	private SalleService salleService;
	
	@Autowired
	private ReunionService reunionService;
	
	@PostMapping("/salle")
	public ResponseEntity<SalleDto> ajouterSalle(@RequestBody SalleDto salleDto) {
		
		SalleDto newSalle = salleService.ajouterSalle(salleDto);	
		return new ResponseEntity<>(newSalle, HttpStatus.CREATED);	
	}
	
	@PostMapping("/{salleId}/equipment")
	public ResponseEntity<EquipementDto> ajouterEquipmentParSalle(@PathVariable long salleId,
			@RequestBody EquipementDto equipementDto) throws Exception{
		
		EquipementDto equipmentCreer = equipementService.ajouterEquipmentParSalle(salleId, equipementDto);
		return new ResponseEntity<>(equipmentCreer, HttpStatus.CREATED);
	}
	
	@PostMapping("/{typeReunionId}/equipment")
	public ResponseEntity<EquipementDto> ajouterEquipmentTypeReunion(@PathVariable long typeReunionId,
			@RequestBody EquipementDto equipementDto) throws Exception {
		
		EquipementDto equipmentCreer = equipementService.ajouterEquipmentTypeReunion(typeReunionId, equipementDto);
		return new ResponseEntity<>(equipmentCreer, HttpStatus.CREATED);
	}
	
	@PostMapping("/equipment")
	public ResponseEntity<EquipementDto> ajouterEquipment(@RequestBody EquipementDto equipementDto) {
		
		EquipementDto equipmentCreer = equipementService.ajouterEquipment(equipementDto);
		return new ResponseEntity<>(equipmentCreer, HttpStatus.CREATED);
	}
	
	@PutMapping("/equipment")
	public ResponseEntity<EquipementDto> modifierEquipment(@PathVariable long id, @RequestBody EquipementDto equipementDto) throws Exception {
		
		EquipementDto equipmentModifier = equipementService.modifierEquipment(id, equipementDto);
		
		return new ResponseEntity<>(equipmentModifier, HttpStatus.OK);
	}
	
	@DeleteMapping("/equipment/{id}")
	public ResponseEntity<String> supprimerEquipment(@PathVariable long id) throws Exception {
		
		equipementService.supprimerEquipment(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/equipment")
	public ResponseEntity<List<EquipementDto>> allEquipment() {
		
		List<EquipementDto> equipementDto = equipementService.allEquipments();
		
		return new ResponseEntity<List<EquipementDto>>(equipementDto, HttpStatus.OK);
	}
	
	@PostMapping("/reunion")
	public ResponseEntity<ReunionDto> ajouterReunion(@RequestBody ReunionDto reunionDto) throws Exception {
		
		ReunionDto newReunionDto = reunionService.ajouterReunion(reunionDto);
		
		return new ResponseEntity<>(newReunionDto, HttpStatus.OK);
	}
	
	
}
