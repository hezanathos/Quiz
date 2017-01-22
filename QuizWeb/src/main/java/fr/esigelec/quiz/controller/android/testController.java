package fr.esigelec.quiz.controller.android;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;

import fr.esigelec.quiz.dao.QuizDAO;
import fr.esigelec.quiz.model.Proposition;
import fr.esigelec.quiz.model.Question;
import fr.esigelec.quiz.model.Quiz;

@Controller
public class testController {

	@Autowired
	QuizDAO quizDAO;

	@Autowired
	AndroidQuizController androidQuizController;

	@RequestMapping(value = "/sendnextquestion", method = RequestMethod.GET)
	public String sendNextQuestion() throws JsonProcessingException {

		try{
			Quiz quiz = quizDAO.getDernierQuiz();
			int idQuestionCourante = quizDAO.getQuestionCourrante(quiz.getId()).getId();
			quiz.setDateDebutQuestion(new Timestamp(System.currentTimeMillis()));
			quizDAO.ajouterQuiz(quiz);
			
			androidQuizController.sendQuestion(idQuestionCourante, quiz.getId());
		}catch(Exception e){
			e.printStackTrace();
		}

		return "index";
	}

	@RequestMapping(value = "/sendstatus", method = RequestMethod.GET)
	public String sendStatus() throws JsonProcessingException {

		try{
			Quiz quiz = quizDAO.getDernierQuiz();
			int idQuestionCourante = quizDAO.getQuestionCourrante(quiz.getId()).getId();
			
			quizDAO.ajouterQuiz(quiz);
			
			androidQuizController.sendStatus(idQuestionCourante, quiz.getId());
		}catch(Exception e){
			e.printStackTrace();
		}
		return "index";
	}

	@RequestMapping(value = "/sendresults", method = RequestMethod.GET)
	public String sendResults() throws JsonProcessingException {
		Quiz quiz = quizDAO.getDernierQuiz();
		
		List<Question> questions = quiz.getListeQuestion();

		try{
			int idCourantQuestion = quizDAO.getQuestionCourrante(quiz.getId()).getId();
			if(questions.get(questions.size()-1).getId() == idCourantQuestion){
				androidQuizController.sendLastResult(idCourantQuestion, quiz.getId());
				quiz.setNoQuestionCourant(0);
			}else{
				androidQuizController.sendResult(quizDAO.getQuestionCourrante(quiz.getId()).getId(), quiz.getId());	
				quiz.setNoQuestionCourant(quiz.getNoQuestionCourant()+1);
			}
			quizDAO.ajouterQuiz(quiz);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "index";
	}
}
