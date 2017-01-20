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
import java.util.*;

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

	@RequestMapping(value = "/ajouterQuiz.do", method = RequestMethod.POST)
	public String ajouterLeQuiz(@ModelAttribute(value="quiz") final Quiz q,
							 final ModelMap pModel){
			serviceQuizDAO.ajouterQuiz(q);
		List<Quiz> listQuiz = serviceQuizDAO.getListeQuizzes();
		pModel.addAttribute("listQuiz", listQuiz);
		return "home_admin";

	}

	@RequestMapping(value = "/ajouterQuiz", method = RequestMethod.GET)
	public String ajouterLeQuiz(final ModelMap pModel){
		return "create_quiz";

	}

	@RequestMapping(value = "/ajouterQuestion.do", method = RequestMethod.POST)
	public String ajouterQuestion(@ModelAttribute(value="question") final Question question, @ModelAttribute(value="proposition") final List<Proposition> propositions,
								 final ModelMap pModel){

			serviceQuestionDAO.ajouterQuestion(question);
			int compteur=0;
			for (Proposition proposition : propositions) {
				if (compteur == 0)
					proposition.setBonneReponse(1);
				else
					proposition.setBonneReponse(0);
				proposition.setQuestion(question);
				servicePropositionDAO.ajouterProposition(proposition);
			}
			return "add_question";
	}

	@RequestMapping(value = "/ajouterQuestion", method = RequestMethod.GET)
	public String ajouterQuestion(
								  final ModelMap pModel){
		return "add_question";
	}
	
	@RequestMapping(value = "/repondreQuestion.do", method = RequestMethod.GET)
	public String repondreQuestion(@ModelAttribute(value="choisir") final Choisir choisir,
			 final ModelMap pModel){
		serviceChoisirDAO.ajouterChoix(choisir);
		return "ingame";
	}
	
	@RequestMapping(value = "/demarrerQuiz", method = RequestMethod.GET)
	public String demarrerQuiz(HttpServletRequest request,  ModelMap modelMap, HttpSession session){

		if(request.getParameter("idQuiz") == null)
			return "index";
		int idQuiz = Integer.parseInt(request.getParameter("idQuiz"));
		Quiz quiz = serviceQuizDAO.getQuiz(idQuiz);
		quiz.setEtat(1);
		serviceQuizDAO.ajouterQuiz(quiz);
		List<Proposition> listProposition = servicePropositionDAO.getListePropositions();
		//session = request.getSession();
		if(servicePersonneDAO.getPersonneByEmail(session.getAttribute("courriel").toString()).getDroits() == 0) {
			modelMap.addAttribute("quiz", quiz);
			modelMap.addAttribute("listProp", listProposition);
			return "ingame";
		}
		
		return "ingame_admin";
		
	}

	@RequestMapping(value = "/listQuiz", method = RequestMethod.GET)
	public String listQuiz(HttpServletRequest request,  ModelMap modelMap, HttpSession session){

		List<Quiz> listQuiz = serviceQuizDAO.getListeQuizzes();
		//session = request.getSession();
		modelMap.addAttribute("listQuiz", listQuiz);

		return "home_user";

	}

	@RequestMapping(value = "/afficherStats", method = RequestMethod.GET)
	public String afficherStats(HttpServletRequest request, ModelMap modelMap){

		if(request.getParameter("idQuiz") == null || request.getParameter("idQuestion") == null)
			return "index";
		int idQuiz = Integer.parseInt(request.getParameter("idQuiz"));
		int idQuestion =  Integer.parseInt(request.getParameter("idQuestion"));
		int nbReponses = serviceChoisirDAO.getNbReponses(idQuiz, idQuestion);
		int nbBonnesReponses = serviceChoisirDAO.getNbBonnesReponses(idQuiz, idQuestion);
		modelMap.addAttribute("nbReponses", nbReponses);
		modelMap.addAttribute("nbBonnesReponses", nbBonnesReponses);
		modelMap.addAttribute("idQuiz", idQuiz);
		
		List<Personne> participants = serviceChoisirDAO.getParticipantsQuiz(idQuiz);
		HashMap classement = new HashMap<String,Integer>();
		for(int i=0; i< participants.size();i++){
			classement.put(participants.get(i).getNom(), serviceChoisirDAO.getNbBonnesReponseDunParticipantAuQuiz(idQuiz, participants.get(i).getId()));
		}
		
		modelMap.addAttribute("stats", classement);
		
		return "stats";
	}

	@RequestMapping(value = "/questionCourrante", method = RequestMethod.GET)
	public String questionCourrante(HttpServletRequest request, ModelMap modelMap){
		if(request.getAttribute("idQuestion") == null)
			return "index";
		int idQuiz = Integer.parseInt(request.getParameter("idQuiz"));
		Question question = serviceQuizDAO.getQuestionCourrante(idQuiz);
		if (question != null){
			Quiz quiz = serviceQuizDAO.getQuiz(idQuiz);
			Calendar cal = Calendar.getInstance();
			Date date = cal.getTime();
			Timestamp timestamp = new Timestamp(date.getTime());
			quiz.setDateDebutQuestion(timestamp);
			quiz.setNoQuestionCourant(quiz.getNoQuestionCourant()+1);
			modelMap.addAttribute("idQuiz", idQuiz);
			serviceQuizDAO.ajouterQuiz(quiz);
			return "ingame";
		} else {
			return "index";
		}
	}


}