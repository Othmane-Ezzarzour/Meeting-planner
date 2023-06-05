package com.meeting.planner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meeting.planner.model.ReserverEquipementParHeure;

@Repository
public interface ReserverEquipementParHeureRepository extends JpaRepository<ReserverEquipementParHeure, Long> {

}
