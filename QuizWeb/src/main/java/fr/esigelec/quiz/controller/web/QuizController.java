package fr.esigelec.quiz.controller.web;

import org.springframework.stereotype.Controller;

import fr.esigelec.quiz.model.Quiz;
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
public class QuizController {
	
	public void startQuiz(int idQuiz){
		
	}

	@RequestMapping(value = "/ajouterQuiz", method = RequestMethod.POST)
	public void ajouterQuiz(HttpServletRequest request){
		HttpSession session = request.getSession();
		String courriel = (String) session.getAttribute("courriel");
		String libelle = request.getParameter("libelle");
		Timestamp dateDebutQuiz = (Timestamp) request.getParameter("dateDebutQuiz");
		int noQuestionCourant = (Timestamp) request.getParameter("noQuestionCourant");

		Quiz quiz = new Quiz(libelle, dateDebutQuiz, noQuestionCourant, etape, dateDebutQuestion, listeQuestion);
	}
}
