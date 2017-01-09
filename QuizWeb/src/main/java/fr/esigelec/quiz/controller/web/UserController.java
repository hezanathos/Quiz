package fr.esigelec.quiz.controller.web;


import fr.esigelec.quiz.dao.*;
import fr.esigelec.quiz.model.Quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.*;

import fr.esigelec.quiz.model.Personne;

import java.sql.Timestamp;

/**
 * Created by Edouard on 04/01/2017.
 *
 * @author - Edouard PETIT Matthieu Munhoven
 */

@Controller
public class UserController {
	
	@Autowired
	private PersonneDAOImpl service;

	@RequestMapping(value = "/inscription", method = RequestMethod.POST)
	public String inscription(@Valid @ModelAttribute(value="personne") final Personne p,
							final BindingResult pBindingResult, final ModelMap pModel){

		if (!pBindingResult.hasErrors()) {
			service.ajouterPersonne(p);
		}
		/*String nom = request.getParameter("nom");
		String motDePasse = request.getParameter("motDePasse");
		String prenom = request.getParameter("prenom");
		String courriel = request.getParameter("courriel");
		Personne p = new Personne(1000,nom,prenom,courriel,motDePasse,0);
		PersonneDAOImpl pdao = new PersonneDAOImpl();
		pdao.ajouterPersonne(p);*/

		return "jecpaskoi";
	}
	
	@RequestMapping(value = "/connexion", method = RequestMethod.POST)
	public String connexion(@Valid @RequestParam String courriel, @RequestParam String mdp,
						  final BindingResult pBindingResult, final ModelMap pModel){

		if (!pBindingResult.hasErrors()) {
			Personne pTemp = service.getPersonne(courriel);
			if(service.verifPersonne(courriel,mdp)){
				HttpSession session = request.getSession();
				session.setAttribute("courriel", pTemp.getMail());
				session.setAttribute("nom", pTemp.getNom());
				session.setAttribute("prenom", pTemp.getPrenom());
			}
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

		return "jecpaskoi";
	}

	@RequestMapping(value = "/deconnexion", method = RequestMethod.GET)
	public String deconnexion(final ModelMap pModel){
		HttpSession session = pModel.getSession();
		session.invalidate();

		return "jecpaskoi";
	}

}
