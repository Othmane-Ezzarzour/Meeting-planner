package com.meeting.planner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meeting.planner.model.TypeReunion;

@Repository
public interface TypeReunionRepository extends JpaRepository<TypeReunion, Long> {
	
	public TypeReunion findByNom(String nom);

}
