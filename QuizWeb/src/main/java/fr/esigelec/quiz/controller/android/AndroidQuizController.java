package fr.esigelec.quiz.controller.android;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import fr.esigelec.quiz.dao.PersonneDAO;
import fr.esigelec.quiz.dao.PropositionDAO;
import fr.esigelec.quiz.dao.QuestionDAO;
import fr.esigelec.quiz.dao.QuizDAO;
import fr.esigelec.quiz.model.Choisir;
import fr.esigelec.quiz.model.Personne;
import fr.esigelec.quiz.model.Proposition;
import fr.esigelec.quiz.model.Question;
import fr.esigelec.quiz.model.Quiz;

/**
 * 
 * @author wangxi 
 * @see https://spring.io/guides/gs/messaging-stomp-websocket/
 */

@Controller
public class AndroidQuizController {

	@Autowired
	@Qualifier("questionDAOImpl")
	private QuestionDAO questionDAO;
	@Autowired
	@Qualifier("choisirDAOImpl")
	private ChoisirDAO choisirDAO;
	@Autowired
	@Qualifier("personneDAOImpl")
	private PersonneDAO personneDAO;
	@Autowired
	@Qualifier("quizDAOImpl")
	private QuizDAO quizDAO;
	@Autowired
	@Qualifier("propositionDAOImpl")
	private PropositionDAO propositionDAO;

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
	@MessageMapping("/choisir")
	@SendTo("/topic/questions")
	public void getAnswer(String json) throws Exception {

		JsonElement jsonElement = new JsonParser().parse(json);
		JsonObject jsonObject = jsonElement.getAsJsonObject();

		int idPerson = jsonObject.getAsJsonPrimitive("idperson").getAsInt();
		int idQuiz = jsonObject.getAsJsonPrimitive("idquiz").getAsInt();
		int idproposition = jsonObject.getAsJsonPrimitive("idproposition").getAsInt();

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		Personne personne = personneDAO.getPersonne(idPerson);
		Quiz quiz = quizDAO.getQuiz(idQuiz);
		Proposition proposition = propositionDAO.getProposition(idproposition);

		Choisir choisir = new Choisir(timestamp, personne, quiz, proposition);

		long chrono = System.currentTimeMillis() - quiz.getDateDebutQuestion().getTime();
		if (chrono <= 30000) {
			choisirDAO.ajouterChoix(choisir);
		}

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
	public void sendQuestion(int idQuestion, int idQuiz) throws JsonProcessingException {
		// ApplicationContext context = new
		// ClassPathXmlApplicationContext("dispatcher-servlet.xml");
		// QuestionDAO questionDAO = (QuestionDAO)
		// context.getBean("questionDAOImpl");

		Question question = questionDAO.getQuestion(idQuestion);
		Quiz quiz = quizDAO.getQuiz(idQuiz);
		ArrayList<Proposition> propositions = (ArrayList<Proposition>) questionDAO.getListePropositions(idQuestion);

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode objectNode = mapper.createObjectNode();
		ObjectNode questionNode = mapper.createObjectNode();
		ArrayNode propositionArray = mapper.createArrayNode();

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

		long chrono = System.currentTimeMillis() - quiz.getDateDebutQuestion().getTime();

		objectNode.put("timeRemaining", 30000 - chrono);

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
	public void sendStatus(int idQuestion, int idQuiz) throws JsonProcessingException {

		Question question = questionDAO.getQuestion(idQuestion);

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

		ArrayList<Proposition> propositions = (ArrayList<Proposition>) questionDAO.getListePropositions(idQuestion);

		for (int i = 0; i < 4; i++) {
			Proposition propositionTemp = propositions.get(i);
			int idPropositionTemp = propositionTemp.getId();
			int stat = choisirDAO.getNbChoixDunProposition(idPropositionTemp);
			propositionArray.add(mapper.createObjectNode().put("id", propositionTemp.getId())
					.put("libelle", propositionTemp.getLibelle()).put("stat", stat));
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
	public void sendResult(int idQuestion, int idQuiz) throws JsonProcessingException {

		Question question = questionDAO.getQuestion(idQuestion);
		ArrayList<Proposition> propositions = (ArrayList<Proposition>) questionDAO.getListePropositions(idQuestion);

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
			if (propositionTemp.isBonneReponse() == 1) {
				reponseNode.put("id", propositionTemp.getId());
				reponseNode.put("libelle", propositionTemp.getLibelle());
			}
			int idPropositionTemp = propositionTemp.getId();
			int stat = choisirDAO.getNbChoixDunProposition(idPropositionTemp);
			propositionArray.add(mapper.createObjectNode().put("id", propositionTemp.getId())
					.put("libelle", propositionTemp.getLibelle()).put("stat", stat));
		}
		objectNode.set("propositions", propositionArray);

		objectNode.set("reponse", reponseNode);

		// begin
		List<Personne> participants = choisirDAO.getParticipantsQuiz(idQuiz);

		class Classement {
			Personne personne;
			int point;

			public Classement(Personne personne, int point) {
				this.personne = personne;
				this.point = point;
			}
		}

		Comparator<Classement> comparator = new Comparator<Classement>() {
			public int compare(Classement c1, Classement c2) {
				if (c1.point != c2.point) {
					return c2.point - c1.point;
				} else {
					if (!c1.personne.getNom().equals(c2.personne.getNom())) {
						return c1.personne.getNom().compareTo(c2.personne.getNom());
					} else {
						return c1.personne.getId() - c2.personne.getId();
					}
				}
			}
		};

		ArrayList<Classement> classements = new ArrayList();

		for (Personne participant : participants) {
			int points = choisirDAO.getNbBonnesReponseDunParticipantAuQuiz(idQuiz, participant.getId());
			Classement classementTemp = new Classement(participant, points);
			classements.add(classementTemp);
		}

		Collections.sort(classements, comparator);
		ArrayNode classementsArray = mapper.createArrayNode();
		for (int i = 0; i < classements.size(); i++) {
			ObjectNode user = mapper.createObjectNode();
			user.put("position", i + 1);
			user.put("nom", classements.get(i).personne.getNom());
			user.put("points", classements.get(i).point);
			classementsArray.add(user);
		}

		// ArrayNode classementsArray = mapper.createArrayNode();
		// ObjectNode user = mapper.createObjectNode();
		// user.put("position", 1);
		// user.put("nom", "william");
		// user.put("points", 10);
		//
		// ObjectNode user2 = mapper.createObjectNode();
		// user2.put("position", 2);
		// user2.put("nom", "Xi");
		// user2.put("points", 5);
		// classementsArray.add(user);
		// classementsArray.add(user2);

		objectNode.putPOJO("classement", classementsArray);

		String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(objectNode);
		System.out.println("sendResult:" + json);
		this.template.convertAndSend("/topic/questions", json);
	}

}
