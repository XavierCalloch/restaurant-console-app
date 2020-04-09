package dev.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.entite.Plat;

public class PlatDaoMemoireTest {
	
	private PlatDaoMemoire platDaoMemoire;
	
	@BeforeEach
	void setUp() {
		this.platDaoMemoire = new PlatDaoMemoire();
	}
	
	@Test
	void testListerPlatsVideALInitialisation() {
		List<Plat> resultat = platDaoMemoire.listerPlats();
		
		assertThat(resultat).isEmpty();
	}
	
	@Test
	void testAjouterPlatCasPassants() {
		platDaoMemoire.ajouterPlat("Risotto", 900);
		
		assertFalse(platDaoMemoire.listerPlats().isEmpty());
	}

}
