package fr.esigelec.quiz.controller.android;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import fr.esigelec.quiz.dao.ChoisirDAO;
import fr.esigelec.quiz.dao.ChoisirDAOImpl;
import fr.esigelec.quiz.dao.PersonneDAO;
import fr.esigelec.quiz.dao.PersonneDAOImpl;
import fr.esigelec.quiz.dao.PropositionDAO;
import fr.esigelec.quiz.dao.PropositionDAOImpl;
import fr.esigelec.quiz.dao.QuizDAO;
import fr.esigelec.quiz.dao.QuizDAOImpl;
import fr.esigelec.quiz.model.Choisir;
import fr.esigelec.quiz.model.Personne;
import fr.esigelec.quiz.model.Proposition;
import fr.esigelec.quiz.model.Question;
import fr.esigelec.quiz.model.Quiz;

/**
 * 
 * @author wangxi Quiz controller for android
 * @see https://spring.io/guides/gs/messaging-stomp-websocket/
 */

@Controller
public class AndroidQuizController {

	private SimpMessagingTemplate template;

	/**
	 * 
	 * @param template
	 */
	@Autowired
	public AndroidQuizController(SimpMessagingTemplate template) {
		this.template = template;
	}

	/**
	 * Receive the reponse sended from Andorid
	 * 
	 * @param json
	 *            in format {"idperson":1, "idquiz": 1,"idquestion" : 1,
	 *            "idproposition": 1}
	 * @throws Exception
	 */
	@MessageMapping("/android")
	@SendTo("/topic/questions")
	public void getAnswer(String json) throws Exception {

		JsonElement jsonElement =  new JsonParser().parse(json);
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		
		int idPerson = jsonObject.getAsJsonObject("idperson").getAsInt();
		int idQuiz = jsonObject.getAsJsonObject("idquiz").getAsInt();
		int idQuestion = jsonObject.getAsJsonObject("idquestion").getAsInt();
		int idproposition = jsonObject.getAsJsonObject("idproposition").getAsInt();

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		PersonneDAO personneDAO = new PersonneDAOImpl();
		Personne personne = personneDAO.getPersonne(idPerson);
		QuizDAO quizDAO = new QuizDAOImpl();
		Quiz quiz = quizDAO.getQuiz(idQuiz);
		PropositionDAO propositionDAO = new PropositionDAOImpl();
		Proposition proposition = propositionDAO.getProposition(idproposition);

		Choisir choisir = new Choisir(timestamp, personne, quiz, proposition);
		ChoisirDAO choisirDAO = new ChoisirDAOImpl();
		choisirDAO.ajouterChoix(choisir);

	}

	/**
	 * send new question to broker
	 * 
	 * @param question
	 *            the question we are on, send to broker
	 * @param idQuiz
	 *            the id of the Quiz we are on
	 * @throws JsonProcessingException
	 */
	public void sendQuestion(Question question, int idQuiz, ArrayList<Proposition> propositions)
			throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode objectNode = mapper.createObjectNode();
		ObjectNode questionNode = mapper.createObjectNode();
		ArrayNode propositionArray = mapper.createArrayNode();

		// QuestionDAO questionDAO = new QuestionDAOImpl();
		// Question question = questionDAO.getQuestion(1);

		objectNode.put("status", 0);
		objectNode.put("numero", question.getId());
		objectNode.put("idquiz", idQuiz);

		questionNode.put("id", question.getId());
		questionNode.put("libelle", question.getLibelle());
		objectNode.set("question", questionNode);

		for (int i = 0; i < 4; i++) {
			Proposition propositionTemp = propositions.get(i);
			propositionArray.add(mapper.createObjectNode().put("id", propositionTemp.getId()).put("libelle",
					propositionTemp.getLibelle()));
		}
		objectNode.set("propositions", propositionArray);
		objectNode.put("timeRemaining", 30000);

		String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(objectNode);
		System.out.println("sendQuestion" + json);
		this.template.convertAndSend("/topic/questions", json);
	}

	/**
	 * Send the question status to broker
	 * 
	 * @param question
	 *            the question we are on
	 * @param idQuiz
	 *            the id of the quiz which we are on
	 * @throws JsonProcessingException
	 */
	public void sendStatus(Question question, int idQuiz, ArrayList<Proposition> propositions, int[] stats)
			throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode objectNode = mapper.createObjectNode();
		ObjectNode questionNode = mapper.createObjectNode();
		ArrayNode propositionArray = mapper.createArrayNode();

		objectNode.put("status", 1);
		objectNode.put("numero", question.getId());
		objectNode.put("idquiz", idQuiz);

		questionNode.put("id", question.getId());
		questionNode.put("libelle", question.getLibelle());
		objectNode.set("question", questionNode);

		for (int i = 0; i < 4; i++) {
			Proposition propositionTemp = propositions.get(i);
			propositionArray.add(mapper.createObjectNode().put("id", propositionTemp.getId())
					.put("libelle", propositionTemp.getLibelle()).put("stat", stats[i]));
		}
		objectNode.set("propositions", propositionArray);
		String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(objectNode);
		System.out.println("sendStatus:" + json);
		this.template.convertAndSend("/topic/questions", json);
	}

	/**
	 * Send the question result to broker
	 * 
	 * @param question
	 *            the question we are on
	 * @param idQuiz
	 *            the id of the Quiz we are on
	 * @throws JsonProcessingException
	 */
	public void sendResult(Question question, int idQuiz, ArrayList<Proposition> propositions, int[] stats)
			throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode objectNode = mapper.createObjectNode();
		ObjectNode questionNode = mapper.createObjectNode();
		ObjectNode reponseNode = mapper.createObjectNode();
		ArrayNode propositionArray = mapper.createArrayNode();

		objectNode.put("status", 2);
		objectNode.put("numero", 0);
		objectNode.put("idquiz", idQuiz);

		questionNode.put("id", question.getId());
		questionNode.put("libelle", question.getLibelle());
		objectNode.set("question", questionNode);

		for (int i = 0; i < 4; i++) {
			Proposition propositionTemp = propositions.get(i);
			if( propositionTemp.isBonneReponse() == 1){
				reponseNode.put("id", propositionTemp.getId());
				reponseNode.put("libelle", propositionTemp.getLibelle());
			}
			propositionArray.add(mapper.createObjectNode().put("id", propositionTemp.getId())
					.put("libelle", propositionTemp.getLibelle()).put("stat", stats[i]));
		}
		objectNode.set("propositions", propositionArray);

		objectNode.set("reponse", reponseNode);

		// TODO: dynamiquely generate the classments
		ArrayNode classementsArray = mapper.createArrayNode();
		ObjectNode user = mapper.createObjectNode();
		user.put("position", 1);
		user.put("nom", "william");
		user.put("points", 10);

		ObjectNode user2 = mapper.createObjectNode();
		user2.put("position", 2);
		user2.put("nom", "Xi");
		user2.put("points", 5);
		classementsArray.add(user);
		classementsArray.add(user2);

		objectNode.putPOJO("classement", classementsArray);

		String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(objectNode);
		System.out.println("sendResult:" + json);
		this.template.convertAndSend("/topic/questions", json);
	}

}
