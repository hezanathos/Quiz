package fr.esigelec.quiz.dao;

import java.util.ArrayList;

/**
 * @author Sahobau
 */

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.esigelec.quiz.model.Choisir;
import fr.esigelec.quiz.model.Personne;
import fr.esigelec.quiz.model.Question;
import fr.esigelec.quiz.model.Quiz;

@Repository("choisirDAOImpl")
@Transactional(propagation = Propagation.SUPPORTS)
public class ChoisirDAOImpl implements ChoisirDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void ajouterChoix(Choisir choix) {

		sessionFactory.getCurrentSession().saveOrUpdate(choix);
		
	

	}

	@Override
	@Transactional(readOnly = true)
	public Choisir getChoix(int id) {
		return (Choisir) sessionFactory.getCurrentSession().get(Choisir.class, id);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Choisir> getListeChoix() {
		return (List<Choisir>) sessionFactory.getCurrentSession().createCriteria(Choisir.class).list();
	}

	@Override
	@Transactional(readOnly = true)
	public void supprimerChoix(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Choisir chx = (Choisir) session.load(Choisir.class, new Integer(id));
		if (null != chx) {
			session.delete(chx);

		}

	}


	@SuppressWarnings("unchecked")
	@Override
	public int getNbBonnesReponses(int idQuiz, int idQuestion) {
		
		List<Choisir> listeDesChoix = new ArrayList<Choisir>();

		int nbBonnesResponses=0;
		listeDesChoix = (List<Choisir>) sessionFactory.getCurrentSession()
				.createQuery("from Choisir where idquiz=?") 
				.setParameter(0, idQuiz);
		
		for(Choisir chx : listeDesChoix ){
			
			
			if(chx.getProposition().isBonneReponse()==1 && chx.getProposition().getQuestion().getId()==idQuestion){
				nbBonnesResponses++;
			}
			else{
				//nothing
			}
			
		}
		
		
		return nbBonnesResponses;
	}

	@Override
	public int getNbReponses(int idQuiz, int idQuestion) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNbBonnesReponseDunParticipantAuQuiz(int idQuiz, int idParticipant) {
		// TODO Auto-generated method stub
		return 0;
	}

}
