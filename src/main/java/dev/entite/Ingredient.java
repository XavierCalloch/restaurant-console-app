package dev.entite;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 
 * Représente un ingrédient
 * 
 * @author Xavier CALLOCH
 *
 */
@Entity
@Table(name = "ingredient")
public class Ingredient {

	/** id */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/** nom */
	private String nom;

	/** plats associés */
	@ManyToMany(mappedBy = "ingredients")
	private List<Plat> plats = new ArrayList<>();

	/**
	 * Constructeur
	 * 
	 */
	public Ingredient() {
	}

	/**
	 * Constructeur
	 * 
	 * @param nom nom
	 */
	public Ingredient(String nom) {
		this.nom = nom;
	}

	/**
	 * Getter
	 * 
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Setter
	 * 
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Getter
	 * 
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Setter
	 * 
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

}
