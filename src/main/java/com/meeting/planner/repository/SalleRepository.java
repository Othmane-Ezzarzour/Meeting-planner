package com.meeting.planner.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meeting.planner.model.Salle;

@Repository
public interface SalleRepository extends JpaRepository<Salle, Long> {
	
	public List<Salle> findByNombreDePlaceGreaterThanEqualOrderByNombreDePlaceAsc(Integer id);


}
