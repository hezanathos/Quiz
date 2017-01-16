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


/**
 * 
 * Classe d'implementation des méthodes contenues dans ChosirDAO
 *
 */

@Repository("choisirDAOImpl")
@Transactional(propagation = Propagation.SUPPORTS)
public class ChoisirDAOImpl implements ChoisirDAO {

	@Autowired
	private SessionFactory sessionFactory;

	
	/**
	 * méthode Ajouter un choix
	 * 
	 * @param choix , représente le choix à enregistrer
	 * 
	 */
	@Override
	public void ajouterChoix(Choisir choix) {

		sessionFactory.getCurrentSession().saveOrUpdate(choix);
		
	

	}

	
	/**
	 * méthode pour récupérer un choix 
	 * @param id, représente l'identitifiant du choix à récupérer 
	 * @return le choix désirer
	 */
	
	@Override
	@Transactional(readOnly = true)
	public Choisir getChoix(int id) {
		return (Choisir) sessionFactory.getCurrentSession().get(Choisir.class, id);
	}

	/**
	 * méthode pour récupérer une liste des choix 
	 * @return la liste des choix dans la bdd
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Choisir> getListeChoix() {
		return (List<Choisir>) sessionFactory.getCurrentSession().createCriteria(Choisir.class).list();
	}

	
	/**
	 * méthode de suppression d'un choix
	 * @param id, représente l'identifiant du choix à supprimer 
	 */
	@Override
	@Transactional(readOnly = true)
	public void supprimerChoix(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Choisir chx = (Choisir) session.load(Choisir.class, new Integer(id));
		if (null != chx) {
			session.delete(chx);

		}

	}

	/**
	 * Méthode de récupération du nombre de bonnes réponses à une question d'un quiz
	 * 
	 * @param idQuiz, représente l'identifiant du Quiz dans lequel se trouve la question
	 * @param idQuestion, représente l'idention de la question dont on veut le nombre de bonnes réponses
	 * 
	 * @return le nombre de bonnes réponses correspondant à la question d'un quiz précis 
	 */

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

	
	/**
	 * méthode de récupération du nombre de réponses d'une question d'un Quiz 
	 * @param idQuiz, représente l'identifiant du Quiz dans lequel se trouve la question
	 * @param idQuestion, représente l'idention de la question dont on veut le nombre de bonnes réponses
	 * @return le nombre de réponses à la question d'un quiz précis 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int getNbReponses(int idQuiz, int idQuestion) {
		List<Choisir> listeDesChoix = new ArrayList<Choisir>();

		listeDesChoix = (List<Choisir>) sessionFactory.getCurrentSession()
				.createQuery("from Choisir where idquiz=?") 
				.setParameter(0, idQuiz);
		
		return listeDesChoix.size();
	}

	
	/**
	 * méthode de récupération du nombre de bonnes réponses d'un participant  dans un Quiz
	 * @param idQuiz, représente l'identifiant du Quiz 
	 * @param idParticipant, représente l'identifiant de l'utilisateur pour lequel on cherche le nombre de bonnes réponses	
	 * @return le nombre de bonnes réponses du participant. 
	 */
	
	
	@SuppressWarnings("unchecked")
	@Override
	public int getNbBonnesReponseDunParticipantAuQuiz(int idQuiz, int idParticipant) {
		
		int nbBonnesResponses=0;
		
		List<Choisir> listeDesChoix = new ArrayList<Choisir>();

		listeDesChoix = (List<Choisir>) sessionFactory.getCurrentSession()
				.createQuery("from Choisir where idquiz=? and idpersonne=?") 
				.setParameter(0, idQuiz)
				.setParameter(1, idParticipant);
		
		
			for(Choisir chx : listeDesChoix ){
			
			
			if(chx.getProposition().isBonneReponse()==1)
			{
				nbBonnesResponses++;
			}
			else{
				//nothing
			}
			
		}
		
		return nbBonnesResponses;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Personne> getParticipantsQuiz(int idQuiz) {
		List<Choisir> listeDesParticipants = new ArrayList<Choisir>();
		List<Personne> listeDesPersonne = new ArrayList<Personne>();
		listeDesParticipants = (List<Choisir>) sessionFactory.getCurrentSession()
				.createQuery("from Choisir where idquiz=? group by idpersonne").setParameter(0, idQuiz);
		
		for (Choisir choisir : listeDesParticipants) {
		  listeDesPersonne.add( choisir.getPersonne());
		}

		return listeDesPersonne;
	}


	@Override
	public int getNbChoixDunProposition(int idProposition) {
		
		
		@SuppressWarnings("unchecked")
	List<Choisir> qqch = (List<Choisir>) sessionFactory.getCurrentSession()
	.createQuery("from Choisir where idproposition=?") 
	.setParameter(0, idProposition).list();
		
		return qqch.size();
	}

	
	
	
}
