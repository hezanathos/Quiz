package fr.esigelec.quiz.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.esigelec.quiz.model.Proposition;

/**
 * :
 * @author Sahobau
 * 
 * */

/**
 * 
 * Classe d'implementation des méthodes contenues dans PropagationDAO
 *
 */
@Repository("propositionDAOImpl") 
@Transactional(propagation = Propagation.SUPPORTS)
public class PropositionDAOImpl implements PropositionDAO {
	
	//injection du bean sessionFactory
	@Autowired
	 private SessionFactory sessionFactory;

	
	/**
	 *  méthode Ajouter une proposition
	 * @param proposition , représente la proposition à enregistrer
	 * 
	 */
	@Override
	public void ajouterProposition(Proposition proposition) {
		   sessionFactory.getCurrentSession().saveOrUpdate(proposition);		
		
	}

	
	/**
	 * méthode pour récupérer une liste des propositions 
	 * @return la liste des propositions de la bdd
	 */
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Proposition> getListePropositions() {
		
		return (List<Proposition>) sessionFactory.getCurrentSession().createCriteria(Proposition.class).list();

	}
	
	/**
	 * méthode pour récupérer une proposition 
	 * @param id, représente l'identitifiant de la proposition à récupérer 
	 * @return la proposition désirée
	 */

	@Override
	@Transactional(readOnly=true)
	public Proposition getProposition(int id) {
		
		  return (Proposition) sessionFactory.getCurrentSession().get(Proposition.class, id);
	}


	

	/* @Override
	 public void supprimerProposition(int id) {
		
		Session session = this.sessionFactory.getCurrentSession();
		Proposition p = (Proposition) session.load(Proposition.class, new Integer(id));
		if(null != p){
			session.delete(p);	
	}
	}
	*/

}
