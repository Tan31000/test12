package fr.orsys.dao;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.orsys.entities.Formation;

public interface FormationRepository  extends JpaRepository<Formation, Long>{

	Formation findByCode(String code);

	boolean existsByCode(String code);

	Page<Formation> findByCodeStartingWith(String code, Pageable pageable);

}
