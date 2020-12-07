package fr.orsys.metier;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import fr.orsys.dao.CentreRepository;
import fr.orsys.dao.FormateurRepository;
import fr.orsys.dao.FormationRepository;
import fr.orsys.dao.ParticipantRepository;
import fr.orsys.dao.RoleRepository;
import fr.orsys.dao.UserRepository;
import fr.orsys.entities.AppRole;
import fr.orsys.entities.AppUser;
import fr.orsys.entities.Centre;
import fr.orsys.entities.Formateur;
import fr.orsys.entities.Formation;
import fr.orsys.entities.FormationIntra;
import fr.orsys.entities.Participant;

@Service
public class OrsysMetierImpl implements OrsysMetier {
	@Autowired
	public FormationRepository formationRepository;
	@Autowired
	public CentreRepository centreRepository;
	@Autowired
	public FormateurRepository formateurRepository;
	@Autowired
	public ParticipantRepository participantRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Formation findByCode(String code) {
		return formationRepository.findByCode(code);
	}

	@Override
	public List<Formation> getAllFormation() {
		return formationRepository.findAll();
	}

	@Override
	public Page<Formation> getAllFormationsPageable(int page, int size) {
		PageRequest pageable = PageRequest.of(page, size, Sort.by("id").descending());
		return formationRepository.findAll(pageable);
	}

	@Override
	public List<Centre> getAllCentres() {
		return centreRepository.findAll();
	}

	@Override
	public List<Formateur> getAllFormateurs() {
		return formateurRepository.findAll();
	}

	@Override
	public void saveFormation(@Valid Formation formFormation) {
		formationRepository.save(formFormation);

	}

	@Override
	public boolean existsFormation(String code) {
		return formationRepository.existsByCode(code);
	}

	@Override
	public Optional<Formation> getFormation(Long id) {
		return formationRepository.findById(id);
	}

	@Override
	public Page<Participant> getAllParticipantsByFormation(Formation f, int page, int size) {
		PageRequest pageable = PageRequest.of(page, size, Sort.by("id").ascending());
		return participantRepository.findAllParticipantByFormation(f, pageable);
	}

	@Override
	public void deleteFormation(Formation f) {
		formationRepository.delete(f);

	}

	@Override
	public Page<Centre> getAllCentresPageable(int page, int size) {
		PageRequest pageable = PageRequest.of(page, size, Sort.by("id").descending());
		return centreRepository.findAll(pageable);
	}

	@Override
	public boolean existsCentre(String nom) {
		return centreRepository.existsByNom(nom);
	}

	@Override
	public void saveCentre(@Valid Centre formCentre) {
		centreRepository.save(formCentre);
	}

	@Override
	public Centre getCentreById(Long id) {
		return centreRepository.getOne(id);
	}

	@Override
	public void deleteCentre(Centre c) {
		centreRepository.delete(c);
	}

	@Override
	public Page<Formateur> getAllFormateursPageable(int page, int size) {
		PageRequest pageable = PageRequest.of(page, size, Sort.by("id").descending());
		return formateurRepository.findAll(pageable);
	}

	@Override
	public void saveFormateur(@Valid Formateur formFormateur) {
		formateurRepository.save(formFormateur);
	}

	@Override
	public Formateur getFormateurById(Long id) {
		return formateurRepository.getOne(id);
	}

	@Override
	public void deleteFormateur(Formateur f) {
		formateurRepository.delete(f);
	}

	@Override
	public Page<Participant> getAllParticipantsPageable(int page, int size) {
		PageRequest pageable = PageRequest.of(page, size, Sort.by("id").descending());
		return participantRepository.findAll(pageable);
	}

	@Override
	public void saveParticipant(@Valid Participant formParticipant) {
		participantRepository.save(formParticipant);
	}

	@Override
	public boolean existsParticipant(String nom, String prenom) {
		return participantRepository.existsByNomAndPrenom(nom, prenom);
	}

	@Override
	public Participant getParticipantById(Long id) {
		return participantRepository.getOne(id);
	}

	@Override
	public AppUser saveUser(AppUser user) {
		String hashPW = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(hashPW);
		return userRepository.save(user);
	}

	@Override
	public AppRole saveRole(AppRole role) {
		return roleRepository.save(role);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		AppRole role = roleRepository.findByRoleName(roleName);
		AppUser user = userRepository.findByUsername(username);
		user.getRoles().add(role);
		userRepository.save(user);

	}

	@Override
	public AppUser findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
