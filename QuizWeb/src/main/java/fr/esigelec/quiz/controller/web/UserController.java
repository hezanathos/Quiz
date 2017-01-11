package fr.esigelec.quiz.controller.web;
import fr.esigelec.quiz.model.*;


import fr.esigelec.quiz.dao.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	private PersonneDAOImpl service;

	@RequestMapping(value = "/inscription", method = RequestMethod.POST)
	public String inscription( @ModelAttribute(value="creation") final Personne p,
							   final ModelMap pModel){

		
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
	public String connexion( @ModelAttribute(value="connexion") final String courriel, final String mdp,
						    final ModelMap pModel, HttpServletRequest request){

	
			Personne pTemp = service.getPersonneByEmail(courriel);
			if(service.verifPersonne(courriel,mdp)!=(-1)){
				HttpSession session = request.getSession();
				session.setAttribute("courriel", pTemp.getMail());
				session.setAttribute("nom", pTemp.getNom());
				session.setAttribute("prenom", pTemp.getPrenom());
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
	public String deconnexion(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.invalidate();

		return "jecpaskoi";
	}

}
