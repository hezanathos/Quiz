package fr.esigelec.quiz.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

/**
 * 
 * @author Guillaume Sauvage
 *
 */

@Entity
@Table(name = "choisir")
public class Choisir implements Serializable {

	@Column(name = "date", unique = true, nullable = false)
	private Timestamp date;
	
	
}
