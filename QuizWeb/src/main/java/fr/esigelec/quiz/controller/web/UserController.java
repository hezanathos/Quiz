package fr.esigelec.quiz.controller.web;

import fr.esigelec.quiz.model.*;

import fr.esigelec.quiz.dao.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.*;

/**
 * Created by Edouard on 04/01/2017.
 *
 * @author - Edouard PETIT Matthieu Munhoven
 * 
 * Merci de respecter les auteurs des Controller : DEMANDEZ UNE FONCTIONNALITE, NE LA CODEZ PAS.
 */

@Controller
public class UserController {

	@Autowired
	@Qualifier("personneDAOImpl")
	private PersonneDAO service;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String accueilConnexion(Model model) {
		return "login";
	}

	@RequestMapping(value = "/inscription", method = RequestMethod.GET)
	public String accueilInscription(Model model) {
		return "register";
	}

	@RequestMapping(value = "/inscription.do", method = RequestMethod.POST)
	public String inscription(@RequestParam("mail") String mail, @RequestParam("mdp") String mdp,
			@RequestParam("nom") String nom, @RequestParam("prenom") String prenom, Model model) {

		Personne p = new Personne();
		p.setNom(nom);
		p.setPrenom(prenom);
		p.setMail(mail);
		p.setMdp(mdp);
		p.setDroits(0);

		if (service.ajouterPersonne(p) == 1) {

			return "index";
		} else if (service.ajouterPersonne(p) == -1) {
			model.addAttribute("erreurPersonneExiste", "Email d�j� utilis�");
			return "register";
		}
		return "register";


	}
	@RequestMapping(value = "/connexion.do", method = RequestMethod.POST)
	public String connexion(@RequestParam("mail") String mail, @RequestParam("mdp") String mdp, Model model) {

		Personne pTemp = service.getPersonneByEmail(mail);
		if (service.verifPersonne(mail, mdp) != (-1)) {
			if(pTemp.getDroits() == 0)
				return "home_user";
			if(pTemp.getDroits() == 1000)
				return "home_admin";
			else
				return "index";
		} else {
			model.addAttribute("erreurUtilisateurInconnu", "Utilisateur inconnu");
			return "index";
		}	

	}


	@RequestMapping(value = "/deconnexion", method = RequestMethod.GET)
	public String deconnexion(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.invalidate();

		return "index";
	}
}
