package fr.esigelec.quiz.controller.android;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConnectionController {

	@RequestMapping(value = "/android/connection", method = RequestMethod.POST)
	public String connection(@RequestParam("mail") String mail, @RequestParam("mdp") String mdp) {
		String retourJson = "";
			
		//TODO mettre a jour avec les méthodes de PersonnesDAO
		//On test si l'utilisateur posséde un compte dans la base de données
		if(true){
			retourJson = "{'status':'OK', 'userId':'userId'}";
		} else {
			retourJson = "{'status':'KO', 'message':'utilisateur inconnu'}";
		}
		
		return retourJson;
	}

}
