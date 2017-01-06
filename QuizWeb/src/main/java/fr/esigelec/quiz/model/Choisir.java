package fr.esigelec.quiz.model;

import java.sql.Timestamp;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
