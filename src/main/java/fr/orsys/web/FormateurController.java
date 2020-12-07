package fr.orsys.web;

import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import fr.orsys.entities.Formateur;
import fr.orsys.metier.OrsysMetier;

@Controller
public class FormateurController {


	@Autowired
	OrsysMetier orsysMetier;

	@RequestMapping("/formateurs" )
	public String formateurs(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size,
			@RequestParam(name = "errorMessage", defaultValue = "") String errorMessage) {
		Page<Formateur> listFormateurs = orsysMetier.getAllFormateursPageable(page, size);
		model.addAttribute("activePage", page);
		model.addAttribute("size", size);
		int[] taillePagination = IntStream.range(0, listFormateurs.getTotalPages()).toArray();
		model.addAttribute("taillePagination", taillePagination);
		model.addAttribute("listeFormateurs", listFormateurs);
		model.addAttribute("errorMessage", errorMessage);
		model.addAttribute("formFormateur", new Formateur());
		return "formateurs";
	}
/******************************************************************/	
	@RequestMapping(value = "/ajouterformateur")
	public String ajouterformateur(Model model) {
		model.addAttribute("formFormateur", new Formateur());
		return "ajouterformateur";
	}
	@PostMapping("/ajouterformateur")
	public String ajouterformateur(@Valid @ModelAttribute(value = "formFormateur") Formateur formFormateur,
			BindingResult result, Model model) {
		if (orsysMetier.existsCentre(formFormateur.getNom()) && orsysMetier.existsCentre(formFormateur.getPrenom())) {
			result.rejectValue("nom", "error.formFormateur", "Le formateur existe déja.");
			//result.rejectValue("prenom", "error.formFormateur", "Le prénom existe déja.");
		}
		if (result.hasErrors()) {
			return "ajouterformateur";
		} else {
			orsysMetier.saveFormateur(formFormateur);
			return "redirect:/formateurs";
		}
	}
/******************************************************************************/
	@RequestMapping(value = "/modifierformateur", method = RequestMethod.GET)
	public String modifierformateur(Model model, @RequestParam(name = "id", defaultValue = "0") Long id) {
		    Formateur formFormateur = orsysMetier.getFormateurById(id);
			model.addAttribute("formFormateur", formFormateur);
			return "modifierformateur";
	}

	@PostMapping("/modifierformateur")
	public String modifierformateur(@Valid @ModelAttribute(value = "formFormateur") Formateur formFormateur,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "modifierformateur";
		} else {
			orsysMetier.saveFormateur(formFormateur);
			return "redirect:/formateurs";
		}
	}
/******************************************************************************************/
	@RequestMapping(value = "/supprimerformateur")
	public String supprimerformateur(@RequestParam(name = "id", defaultValue = "0") Long id) {
		 Formateur f = orsysMetier.getFormateurById(id);
		try {
			orsysMetier.deleteFormateur(f);
			return "redirect:/formateurs";
		} catch (Exception e) {
			String msg = "Vous ne pouvez pas supprimer un formateur lié à une formation!";
			return "redirect:/formateurs?errorMessage=" + msg;
		}
	}

}
