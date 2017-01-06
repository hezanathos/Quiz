package fr.esigelec.quiz.controller.android;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InscriptionController {

	@RequestMapping(value = "/android/inscription", method = RequestMethod.POST)
	public String inscription(@RequestParam("name") String name, @RequestParam("fullname") String fullname,
			@RequestParam("email") String email, @RequestParam("password") String password) {
		
		String retourJson = "";
		
		//TODO mettre a jour avec les méthodes de PersonneDAO
		//Test si l'utilisateur n'est pas déjà enregistré dans la base de donnée
		if(true){
			retourJson = "{'status':'OK', 'message':'utilisateur inscrit'}";
		} else {
			retourJson = "{'status':'OK', 'message':'utilisateur déjà enregistré'}";
		}
		
		return retourJson;
	}
	
}
