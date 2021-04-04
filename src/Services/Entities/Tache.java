package Services.Entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tache")
public class Tache {
	//Variables
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int numero;
	private String description;
	private int duree;
	private String status;
	private Date date_debut;
	private Date date_fin;
	
	//Constructor
	public Tache() {}
	public Tache(String description, int duree, String status, Date date_debut, Date date_fin) {
		super();
		this.description = description;
		this.duree = duree;
		this.status = status;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
	}
	
	
	//Getters & setters
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDuree() {
		return duree;
	}
	public void setDuree(int duree) {
		this.duree = duree;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getDate_debut() {
		return date_debut;
	}
	public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}
	public Date getDate_fin() {
		return date_fin;
	}
	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}

}
