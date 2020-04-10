package dev.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(PlatDaoFichier.class)
@TestPropertySource("classpath:test.properties")
@ActiveProfiles("fichier")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PlatDaoFichierTest {

	// injection du bean à tester
	@Autowired
	private PlatDaoFichier platDaoFichier;

	@Test
	void testAjouterPlat() {
		platDaoFichier.ajouterPlat("Riz cantonais", 1100);
		assertFalse(platDaoFichier.listerPlats().isEmpty());
		assertThat(platDaoFichier.listerPlats().size() == 1);
	}

	@Test
	void testAjouterPlatIndependant() {
		platDaoFichier.ajouterPlat("Riz cantonais", 1100);
		assertFalse(platDaoFichier.listerPlats().isEmpty());
		assertThat(platDaoFichier.listerPlats().size() == 1);
	}

}
