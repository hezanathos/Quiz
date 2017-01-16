package fr.esigelec.quiz.controller.web;

import fr.esigelec.quiz.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import fr.esigelec.quiz.model.Choisir;
import fr.esigelec.quiz.model.Personne;
import fr.esigelec.quiz.model.Proposition;
import fr.esigelec.quiz.model.Question;
import fr.esigelec.quiz.model.Quiz;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.annotation.SessionScope;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Edouard on 04/01/2017.
 * 
 * @author - Edouard PETIT Matthieu Munhoven
 */

@Controller
public class QuizController {

	@Autowired
	private QuizDAO serviceQuizDAO;
	@Autowired
    private QuestionDAO serviceQuestionDAO;
	@Autowired
	private PersonneDAO servicePersonneDAO;
	@Autowired
	private ChoisirDAO serviceChoisirDAO;
	@Autowired
	private PropositionDAO servicePropositionDAO;

	@RequestMapping(value = "/ajouterQuiz", method = RequestMethod.POST)
	public String ajouterLeQuiz(@ModelAttribute(value="quiz") final Quiz q,
							 final ModelMap pModel){
			serviceQuizDAO.ajouterQuiz(q);
		return "ajouterquestionadmin";

	}

	@RequestMapping(value = "/ajouterQuestion", method = RequestMethod.POST)
	public String ajouterQuestion(@ModelAttribute(value="question") final Question question, @ModelAttribute(value="proposition") final List<Proposition> propositions,
								 final ModelMap pModel){

			serviceQuestionDAO.ajouterQuestion(question);
			for (Proposition proposition : propositions) {
				proposition.setQuestion(question);
				servicePropositionDAO.ajouterProposition(proposition);
			}
			
			
        return "ajouterquestionadmin";
	}
	
	@RequestMapping(value = "/repondreQuestion", method = RequestMethod.GET)
	public String repondreQuestion(@ModelAttribute(value="choisir") final Choisir choisir,
			 final ModelMap pModel){
		serviceChoisirDAO.ajouterChoix(choisir);
		return "ingame";
	}
	
	@RequestMapping(value = "/demarrerQuiz", method = RequestMethod.GET)
	public String demarrerQuiz(HttpServletRequest request,  ModelMap modelMap, HttpSession session){

		int idQuiz = Integer.parseInt(request.getParameter("idQuiz"));
		Quiz quiz = serviceQuizDAO.getQuiz(idQuiz);
		//session = request.getSession();
		if(servicePersonneDAO.getPersonneByEmail(session.getAttribute("courriel").toString()).getDroits() == 0) {
			modelMap.addAttribute("quiz", quiz);
			return "quiz";
		}
		
		return "ingame";
		
	}

	@RequestMapping(value = "/listQuiz", method = RequestMethod.GET)
	public String listQuiz(HttpServletRequest request,  ModelMap modelMap, HttpSession session){

		List<Quiz> listQuiz = serviceQuizDAO.getListeQuizzes();
		//session = request.getSession();
		modelMap.addAttribute("listQuiz", listQuiz);

		return "ingame";

	}
	
	@RequestMapping(value = "/afficherStats", method = RequestMethod.GET)
	public String afficherStats(HttpServletRequest request, ModelMap modelMap){
		int idQuiz = (int) request.getAttribute("idQuiz");
		int idQuestion = (int) request.getAttribute("idQuestion");
		int nbReponses = serviceChoisirDAO.getNbReponses(idQuiz, idQuestion);
		int nbBonnesReponses = serviceChoisirDAO.getNbBonnesReponses(idQuiz, idQuestion);
		modelMap.addAttribute("nbReponses", nbReponses);
		modelMap.addAttribute("nbBonnesReponses", nbBonnesReponses);
		
		List<Personne> participants = serviceChoisirDAO.getParticipantsQuiz(idQuiz);
		HashMap classement = new HashMap<String,Integer>();
		for(int i=0; i< participants.size();i++){
			classement.put(participants.get(i).getNom(), serviceChoisirDAO.getNbBonnesReponseDunParticipantAuQuiz(idQuiz, participants.get(i).getId()));
		}
		
		modelMap.addAttribute("stats", classement);
		
		return "stats";
	}


}