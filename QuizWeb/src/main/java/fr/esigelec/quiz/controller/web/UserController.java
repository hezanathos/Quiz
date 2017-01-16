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
 */

@Controller
public class UserController {

	@Autowired
	@Qualifier("personneDAOImpl")
	private PersonneDAO service;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String accueilConnexion(Model model) {
		Personne p = new Personne();
		p.setMail("inconnu"); // pour l'exemple
		p.setMdp("inconnu");
		model.addAttribute("formulaireConnexion", p);
		return "index";
	}

	@RequestMapping(value = "/inscription", method = RequestMethod.GET)
	public String accueilInscription(Model model) {
		Personne p = new Personne();
		p.setMail("inconnu"); // pour l'exemple
		p.setMdp("inconnu");
		p.setNom("inconnu");
		p.setPrenom("inconnu");
		model.addAttribute("formulaireInscription", p);
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
		/*
		 * String nom = request.getParameter("nom"); String motDePasse =
		 * request.getParameter("motDePasse"); String prenom =
		 * request.getParameter("prenom"); String courriel =
		 * request.getParameter("courriel"); Personne p = new
		 * Personne(1000,nom,prenom,courriel,motDePasse,0); PersonneDAOImpl pdao
		 * = new PersonneDAOImpl(); pdao.ajouterPersonne(p);
		 */
		return "register";


	}
	@RequestMapping(value = "/connexion.do", method = RequestMethod.POST)
	public String connexion(@RequestParam("mail") String mail, @RequestParam("mdp") String mdp, Model model) {

		Personne pTemp = service.getPersonneByEmail(mail);
		if (service.verifPersonne(mail, mdp) != (-1)) {// On verifie si la
														// personne existe

			return "index";
		} else {
			model.addAttribute("erreurUtilisateurInconnu", "Utilisateur inconnu");
			return "index";
		}
	/*String motDePasse = request.getParameter("motDePasse");
	String courriel = request.getParameter("courriel");
	PersonneDAOImpl pdao = new PersonneDAOImpl();
	Personne pTemp = pdao.getPersonne(courriel);
	if(pdao.verifPersonne(courriel,motDePasse)){
		HttpSession session = request.getSession();
		session.setAttribute("courriel", pTemp.getMail());
		session.setAttribute("nom", pTemp.getNom());
		session.setAttribute("prenom", pTemp.getPrenom());
	}*/

	}


	@RequestMapping(value = "/deconnexion", method = RequestMethod.GET)
	public String deconnexion(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.invalidate();

		return "index";
	}
}
