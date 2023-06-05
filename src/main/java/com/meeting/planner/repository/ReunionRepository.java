package com.meeting.planner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meeting.planner.model.Reunion;

@Repository
public interface ReunionRepository extends JpaRepository<Reunion, Long> {
	
	public Reunion findByNomOrderByHeureDebut(String nom);

}
