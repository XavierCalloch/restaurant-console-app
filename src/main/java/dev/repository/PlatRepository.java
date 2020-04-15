package dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.entite.Ingredient;
import dev.entite.Plat;

public interface PlatRepository extends JpaRepository<Plat, Integer> {

	List<Plat> findByPrixEnCentimesEuros(Integer prix);

	@Query("select avg(p.prixEnCentimesEuros) from Plat p where p.prixEnCentimesEuros > :prixPlat")
	Integer avgPrix(@Param("prixPlat") Integer prix);

	@Query("select i from Ingredient i join i.plats p where p.nom = :nom")
	List<Ingredient> findByNomWithIngredients(@Param("nom") String nom);

	List<Plat> findByNom(String nom);

	@Modifying
	@Query("update Plat set nom = :nouveauNom where nom = :ancienNom")
	void changerNom(@Param("ancienNom") String ancienNom, @Param("nouveauNom") String nouveauNom);

}
