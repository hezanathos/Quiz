package fr.esigelec.quiz.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.esigelec.quiz.model.Proposition;

/**
 * 
 * @author Sahobau
 * 
 * */


@Repository("PropositionDAO") 

public class PropositionDAOImpl implements PropositionDAO {
	@Autowired
	 private SessionFactory sessionFactory;

	@Override
	public void ajouterProposition(Proposition proposition) {
		   sessionFactory.getCurrentSession().save(proposition);		
		
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Proposition> getListePropositions() {
		
		return (List<Proposition>) sessionFactory.getCurrentSession().createCriteria(Proposition.class).list();

	}

	@Override
	@Transactional(readOnly=true)
	public Proposition getProposition(int id) {
		
		  return (Proposition) sessionFactory.getCurrentSession().get(Proposition.class, id);
	}

	@Override
	public void supprimerProposition(int id) {
		
		Session session = this.sessionFactory.getCurrentSession();
		Proposition p = (Proposition) session.load(Proposition.class, new Integer(id));
		if(null != p){
			session.delete(p);	
	}
	}

}
