package fr.orsys.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.orsys.entities.Centre;

public interface CentreRepository  extends JpaRepository<Centre, Long>{

	boolean existsByNom(String nom);

}
