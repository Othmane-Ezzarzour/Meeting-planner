package com.meeting.planner.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.meeting.planner.model.Equipement;

@Repository
public interface EquipementRepository extends JpaRepository<Equipement, Long> {
	
    @Query(value = "SELECT * FROM equipement e left JOIN reserver_equipement_par_heure r "
    		+ "ON e.id = r.equipement_id WHERE e.salle_id is NULL AND e.type_reunion_id is NULL "
    		+ "AND e.nom =:nom AND r.heure_debut_reservation is null" , nativeQuery = true)
	public List<Equipement> findByNomParamsNative(@Param("nom") String nom);
    
    public void deleteBySalleId(long salleId);

}
