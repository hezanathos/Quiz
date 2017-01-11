package fr.esigelec.quiz.controller.android;

import org.springframework.stereotype.Controller;
/**
 * @author Simon
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.esigelec.quiz.dao.PersonneDAOImpl;
import fr.esigelec.quiz.model.Personne;

@RestController
public class ConnectionController {

	@RequestMapping(value = "/android/connexion", method = RequestMethod.POST)
	public String connection(@RequestParam("email") String email, @RequestParam("password") String password) {
		String retourJson = "";
		PersonneDAOImpl pDao = new PersonneDAOImpl();
		Personne p;
		
		System.out.println(email + " " + password);
		//On test si l'utilisateur existe dans la base de données
		if(pDao.verifPersonne(email, password) != -1){
			//Si l'utilisateur existe on récupére son id
			p = pDao.getPersonneByEmail(email);
			retourJson = "{'status':'OK', 'message':'utilisateur connecté', 'id':" + p.getId() + "}";
		} else {
			retourJson = "{'status':'KO', 'message':'utilisateur inconnu'}";
		}
		
		
		return retourJson;
	}

}
