package fr.esigelec.quiz.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Guillaume SAUVAGE
 *
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "question")
public class Question implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "libelle", unique = true, nullable = false)
	private String libelle;

	@OneToMany(fetch = FetchType.EAGER)
	private List<Proposition> Listproposition = new ArrayList<Proposition>();

	public Question() {
		super();
	}

	public Question(int id, String libelle, List<Proposition> listproposition) {
		super();
		this.id = id;
		this.libelle = libelle;
		Listproposition = listproposition;
	}

	public Question(String libelle, List<Proposition> lp) {
		super();

		this.libelle = libelle;
		Listproposition = lp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public List<Proposition> getListproposition() {
		return Listproposition;
	}

	public void setListproposition(ArrayList<Proposition> listproposition) {
		Listproposition = listproposition;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", libelle=" + libelle + ", Listproposition=" + Listproposition + "]";
	}

}