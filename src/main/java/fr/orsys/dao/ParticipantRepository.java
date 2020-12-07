package fr.orsys.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.orsys.entities.Formation;
import fr.orsys.entities.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long>{


	Page<Participant> findAllParticipantByFormation(Formation f, Pageable pageable);

	boolean existsByNom(String nom);

	boolean existsByPrenom(String prenom);

	boolean existsByNomAndPrenom(String nom, String prenom);



}
