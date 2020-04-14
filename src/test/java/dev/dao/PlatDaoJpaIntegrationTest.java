package dev.dao;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.config.DataSourceH2TestConfig;
import dev.config.JpaConfig;
import dev.entite.Plat;

@SpringJUnitConfig({ PlatDaoJpa.class, JpaConfig.class, DataSourceH2TestConfig.class })
@TestPropertySource("classpath:test.properties")
@ActiveProfiles({ "PlatDaoJpa", "service1" })
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PlatDaoJpaIntegrationTest {

	private static final Logger LOGGER = Logger.getLogger(PlatDaoJdbcIntegrationTest.class.getName());

	// injection du bean à tester
	@Autowired
	private IPlatDao platDaoJpa;

	@Test
	void testListerPlatsNonVide() {
		assertFalse(platDaoJpa.listerPlats().isEmpty());
		LOGGER.info("Etant donné que la table de type H2 est alimentée au lancement de la configuration");
		LOGGER.info("Lorsqu'on liste les plats de la table");
		List<Plat> listePlats = platDaoJpa.listerPlats();
		LOGGER.info("Alors la liste n'est pas vide");
		assertFalse(listePlats.isEmpty());
	}

	@Test
	void testAjouterPlat() {
		platDaoJpa.ajouterPlat("RizCantonais", 1100);
		assertTrue(platDaoJpa.listerPlats().contains(new Plat("RizCantonais", 1100)));
	}

}
