package fr.orsys.metier;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;

import fr.orsys.entities.AppRole;
import fr.orsys.entities.AppUser;
import fr.orsys.entities.Centre;
import fr.orsys.entities.Formateur;
import fr.orsys.entities.Formation;
import fr.orsys.entities.Participant;




public interface OrsysMetier {
	public Formation findByCode(String code);
	public List<Formation> getAllFormation();
	public Page<Formation> getAllFormationsPageable(int page, int size);
	public List<Centre> getAllCentres();
	public List<Formateur> getAllFormateurs();
	public void saveFormation(@Valid Formation formFormation);
	public boolean existsFormation(String code);
	public Optional<Formation> getFormation(Long id);
	public Page<Participant> getAllParticipantsByFormation(Formation f, int page, int size);
	public void deleteFormation(Formation f);
	public Page<Centre> getAllCentresPageable(int page, int size);
	public boolean existsCentre(String nom);
	public void saveCentre(@Valid Centre formCentre);
	public Centre getCentreById(Long id);
	public void deleteCentre(Centre c);
	public Page<Formateur> getAllFormateursPageable(int page, int size);
	public void saveFormateur(@Valid Formateur formFormateur);
	public Formateur getFormateurById(Long id);
	public void deleteFormateur(Formateur f);
	public Page<Participant> getAllParticipantsPageable(int page, int size);
	public void saveParticipant(@Valid Participant formParticipant);
	public boolean existsParticipant(String nom, String prenom);
	public Participant getParticipantById(Long id);
	/*********************Security**********************************/
	public AppUser saveUser(AppUser user);
	public AppRole saveRole(AppRole role);
	public void addRoleToUser(String username,String roleName);
	public AppUser findUserByUsername(String username);

	
}
