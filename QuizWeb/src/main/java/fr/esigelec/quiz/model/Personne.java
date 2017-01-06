package fr.esigelec.quiz.model;

/**
 * 
 * @author Guillaume SAUVAGE
 *
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "personne")
public class Personne {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "nom", unique = true, nullable = false)
	private String nom;
	
	@Column(name = "prenom", unique = true, nullable = false)
	private String prenom;
	
	@Column(name = "mail", unique = true, nullable = false)
	private String mail;
	
	@Column(name = "mdp", unique = true, nullable = false)
	private String mdp;
	
	@Column(name = "droits", unique = true, nullable = false)
	private int droits;

	
	public Personne(int id, String nom, String prenom, String mail, String mdp, int droits) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.mdp = mdp;
		this.droits = droits;
	}

	public Personne() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public int getDroits() {
		return droits;
	}

	public void setDroits(int droits) {
		this.droits = droits;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", mdp=" + mdp
				+ ", droits=" + droits + "]";
	}

}
