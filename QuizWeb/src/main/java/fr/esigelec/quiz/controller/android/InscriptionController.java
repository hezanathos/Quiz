package fr.esigelec.quiz.controller.android;

/**
 * @author Simon
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.esigelec.quiz.dao.PersonneDAOImpl;
import fr.esigelec.quiz.model.Personne;

@Controller
public class InscriptionController {

	@RequestMapping(value = "/android/inscription", method = RequestMethod.POST)
	public String inscription(@RequestParam("name") String name, @RequestParam("fullname") String fullname,
			@RequestParam("email") String email, @RequestParam("password") String password) {
		
		String retourJson = "";
		Personne p = new Personne();
		PersonneDAOImpl pDao = new PersonneDAOImpl();
		
		p.setNom(name);
		p.setPrenom(fullname);
		p.setMail(email);
		p.setMdp(password);
		p.setDroits(0);
		
		//TODO mettre a jour avec les méthodes de PersonneDAO
		//Test si l'utilisateur n'est pas déjà enregistré dans la base de donnée
		if(pDao.ajouterPersonne(p) == 1){
			retourJson = "{'status':'OK', 'message':'inscription réussie', 'id':" + p.getId() + "}";
		} else if(pDao.ajouterPersonne(p) == -1) {
			retourJson = "{'status':'KO', 'message':'email déjà utilisé'}";
		}
		
		return retourJson;
	}
	
}
