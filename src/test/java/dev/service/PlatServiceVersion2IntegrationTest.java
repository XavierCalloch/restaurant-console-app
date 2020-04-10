package dev.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import dev.dao.PlatDaoMemoire;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { PlatServiceVersion2.class, PlatDaoMemoire.class })
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles({ "service2", "memoire" })
public class PlatServiceVersion2IntegrationTest {

	// injection du bean à tester
	@Autowired
	PlatServiceVersion2 platServiceVersion2;

	@Test
	void testAjouterPlatCasPassant() {
		platServiceVersion2.ajouterPlat("Riz cantonais", 1100);

		assertFalse(platServiceVersion2.listerPlats().isEmpty());
	}

	@Test
	void testAjouterPlatPrixInvalide() {

		assertThatThrownBy(() -> {
			platServiceVersion2.ajouterPlat("Bol renversé", 500);
		}).hasMessage("le prix d'un plat doit être supérieur à 10 €");
	}

}
