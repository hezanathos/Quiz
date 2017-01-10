package fr.esigelec.quiz.controller.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.esigelec.quiz.model.Personne;
import fr.esigelec.quiz.model.Question;

/**
 * Created by Congyi on 09/01/2017.
 * 
 * @author - Shi Congyi
 */
public class QuestionController {
	public QuestionController(){}
	
	@RequestMapping(value = "/ajouterQuestion", method = RequestMethod.POST)
	public void ajouterQuestion(@Valid @ModelAttribute(value="ajouterquestion") final Question q,
			final BindingResult pBindingResult, final ModelMap pModel){
		
		
	}

	

}
