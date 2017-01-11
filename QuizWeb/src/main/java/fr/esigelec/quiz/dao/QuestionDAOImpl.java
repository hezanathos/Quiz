package fr.esigelec.quiz.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.esigelec.quiz.model.Personne;
import fr.esigelec.quiz.model.Proposition;
import fr.esigelec.quiz.model.Question;

/**
 * 
 * @author Sahobau
 * 
 * */


@Repository("QuestionDAO") 
@Transactional(propagation = Propagation.SUPPORTS)
public class QuestionDAOImpl implements QuestionDAO{
	
	@Autowired
	 private SessionFactory sessionFactory;

	@Override
	public void ajouterQuestion(Question question) {
		   sessionFactory.getCurrentSession().saveOrUpdate(question);		
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	
	public List<Question> getListeQuestions() {
		
		return (List<Question>) sessionFactory.getCurrentSession().createCriteria(Question.class).list();

	}

	@Override
	@Transactional(readOnly=true)
	public Question getQuestion(int id) {
		
		  return (Question) sessionFactory.getCurrentSession().get(Question.class, id);

	}
/*
	@Override
	public void supprimerQuestion(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Question q = (Question) session.load(Question.class, new Integer(id));
		if(null != q){
			session.delete(q);		
	}
		
		
	}
	*/

	@SuppressWarnings("unchecked")
	@Override
	public List<Proposition> getListePropositions(int idQuestion) {
		
		List<Proposition> personnes = new ArrayList<Proposition>();

		personnes = (List<Proposition>)sessionFactory.getCurrentSession()
				.createQuery("from Proposition where id=?")
				.setParameter(0, idQuestion)
				.list();
		
		return personnes;
	}
	

}
