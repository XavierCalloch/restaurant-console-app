package dev.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.dao.IPlatDao;

public class PlatServiceVersion1Test {
	
	PlatServiceVersion1 platServiceVersion1;
	IPlatDao platDao;
	
	@BeforeEach
	void setUp() {
		platDao = mock(IPlatDao.class);
		platServiceVersion1 = new PlatServiceVersion1(platDao);
	}
	
	@Test
    void testAjouterPlatAvecNomInvalide() {

        assertThatThrownBy(() -> {
        	platServiceVersion1.ajouterPlat("p", 900);
        }).hasMessage("un plat doit avoir un nom de plus de 3 caractères");

    }
	
	@Test
    void testAjouterPlatAvecPrixInvalide() {

        assertThatThrownBy(() -> {
        	platServiceVersion1.ajouterPlat("unPlat", 100);
        }).hasMessage("le prix d'un plat doit être supérieur à 5 €");

    }
	
	@Test
    void testAjouterPlatAvecNomEtPrixInvalide() {
        platServiceVersion1.ajouterPlat("Tajine", 1500);
        verify(platDao).ajouterPlat("Tajine", 1500);
    }
	
}
