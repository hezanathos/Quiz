package fr.esigelec.quiz.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
	

}
