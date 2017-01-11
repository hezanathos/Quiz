package fr.esigelec.quiz.controller.android;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import fr.esigelec.quiz.model.Choisir;
import fr.esigelec.quiz.model.Proposition;
import fr.esigelec.quiz.model.Question;

/**
 * 
 * @author wangxi
 *
 */

@Controller
public class AndroidQuizController {

	private SimpMessagingTemplate template;

	@Autowired
	public AndroidQuizController(SimpMessagingTemplate template) {
		this.template = template;
	}

	@MessageMapping("/choisir")
	@SendTo("/topic/questions")
	public void getAnswer(String json) throws Exception {
		// {"idperson":1, "idquiz": 1, "idproposition": 1}
		
	}

	// send new question to broker
	public void sendQuestion(Question question, int idQuiz) throws JsonProcessingException {
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
			Proposition propositionTemp = question.getListproposition().get(i);
			propositionArray.add(mapper.createObjectNode().put("id", propositionTemp.getId()).put("libelle",
					propositionTemp.getLibelle()));
		}
		objectNode.set("propositions", propositionArray);
		objectNode.put("timeRemaining", 30000);
		
		String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(objectNode);
		System.out.println("sendQuestion" + json);
		this.template.convertAndSend("/topic/questions", json);
	}

	public void sendStatus(Question question, int idQuiz) throws JsonProcessingException {
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

		// TODO: Change value of stat in real
		for (int i = 0; i < 4; i++) {
			Proposition propositionTemp = question.getListproposition().get(i);
			propositionArray.add(mapper.createObjectNode().put("id", propositionTemp.getId())
					.put("libelle", propositionTemp.getLibelle()).put("stat", 25));
		}
		objectNode.set("propositions", propositionArray);
		String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(objectNode);
		System.out.println("sendStatus:" + json);
		this.template.convertAndSend("/topic/questions", json);
	}

	public void sendResult(Question question, int idQuiz) throws JsonProcessingException {
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

		// TODO: Change value of stat in real
		for (int i = 0; i < 4; i++) {
			Proposition propositionTemp = question.getListproposition().get(i);
			propositionArray.add(mapper.createObjectNode().put("id", propositionTemp.getId())
					.put("libelle", propositionTemp.getLibelle()).put("stat", 25));
		}
		objectNode.set("propositions", propositionArray);
		
		//TODO: dynamiquely add the correcte answer
		reponseNode.put("id", 4);
		reponseNode.put("libelle", "test proposition 4");
		objectNode.set("reponse", reponseNode);
		
		//TODO: dynamiquely generate the classments
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
		System.out.println("sendResult:"+ json);
		this.template.convertAndSend("/topic/questions", json);
	}

}
