package fr.esigelec.quiz.controller.web;

import fr.esigelec.quiz.dao.QuestionDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import fr.esigelec.quiz.dao.QuizDAOImpl;
import fr.esigelec.quiz.model.Question;
import fr.esigelec.quiz.model.Quiz;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

/**
 * Created by Edouard on 04/01/2017.
 * 
 * @author - Edouard PETIT Matthieu Munhoven
 */

@Controller
@RequestMapping("Quiz")
public class QuizController {

	@Autowired
	private QuizDAOImpl service;
	@Autowired
    private QuestionDAOImpl service2;

	@RequestMapping(value = "/ajouterQuiz", method = RequestMethod.POST)
	public String ajouterLeQuiz(@ModelAttribute(value="quiz") final Quiz q,
							 final ModelMap pModel){

		

		return "animateur";
		/*HttpSession session = request.getSession();
		String courriel = (String) session.getAttribute("courriel");
		String libelle = request.getParameter("libelle");
		Timestamp dateDebutQuiz = (Timestamp) request.getParameter("dateDebutQuiz");
		int noQuestionCourant = (Timestamp) request.getParameter("noQuestionCourant");

		Quiz quiz = new Quiz(libelle, dateDebutQuiz, noQuestionCourant, etape, dateDebutQuestion, listeQuestion);*/

	}

	@RequestMapping(value = "/ajouterQuestion", method = RequestMethod.POST)
	public String ajouterQuestion(@ModelAttribute(value="question") final Question question,
								 final ModelMap pModel){

		
        return "ajouterquestionadmin";
	}

	@RequestMapping(value = "/demarrerQuiz", method = RequestMethod.GET)
	public void startQuiz(final ModelMap pModel){

	}
	@RequestMapping(value = "/index")
	public String accueilclient() {
		return "index";
	}
	@RequestMapping(value="/indexadmin")
	public String accueiladmin(){
		return "indexadmin";
	}
}
