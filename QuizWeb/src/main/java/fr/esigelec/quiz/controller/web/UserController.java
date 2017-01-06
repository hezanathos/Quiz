package fr.esigelec.quiz.controller.web;


import fr.esigelec.quiz.dao.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.*;

import fr.esigelec.quiz.model.Personne;

/**
 * Created by Edouard on 04/01/2017.
 *
 * @author - Edouard PETIT Matthieu Munhoven
 */

@Controller
public class UserController {
	
	
	@RequestMapping(value = "/inscription", method = RequestMethod.POST)
	public void inscription(HttpServletRequest request){
		String nom = request.getParameter("nom");
		String motDePasse = request.getParameter("motDePasse");
		String prenom = request.getParameter("prenom");
		String courriel = request.getParameter("courriel");
		Personne p = new Personne(1000,nom,prenom,courriel,motDePasse,0);
		PersonneDAOImpl pdao = new PersonneDAOImpl();
		pdao.addPersonne(p);
		
	}
	
	@RequestMapping(value = "/connexion", method = RequestMethod.POST)
	public void connexion(HttpServletRequest request){
		String motDePasse = request.getParameter("motDePasse");
		String courriel = request.getParameter("courriel");
		Personne p = new Personne();
		p.setMail(courriel);
		p.setMdp(motDePasse);
		PersonneDAOImpl pdao = new PersonneDAOImpl();
		Personne pTemp = pdao.getPersonne(courriel);
		if(pTemp.getMdp().equals(p.getMdp())){
			HttpSession session = request.getSession();
			session.setAttribute("courriel", pTemp.getMail());
			session.setAttribute("nom", pTemp.getNom());
			session.setAttribute("prenom", pTemp.getPrenom());
		}
	}
}
