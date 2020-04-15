package dev.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import dev.config.DataSourceH2TestConfig;
import dev.config.JpaConfig;
import dev.dao.PlatDaoJpa;
import dev.entite.Plat;

@SpringJUnitConfig({ PlatDaoJpa.class, JpaConfig.class, DataSourceH2TestConfig.class })
@ActiveProfiles("PlatDaoJpa")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PlatRepositoryIntegrationTest {

	// injection du bean à tester
	@Autowired
	private PlatRepository platRepository;

	@Test
	void testFindAll() {
		assertFalse(platRepository.findAll().isEmpty());
	}

	@Test
	void testFindAllSortAsc() {
		Sort sort = Sort.by("prixEnCentimesEuros").ascending();
		assertThat(platRepository.findAll(sort).get(0).getNom()).isEqualTo("Côte de boeuf");
	}

	@Test
	void testFindAllSortDesc() {
		Sort sort = Sort.by("prixEnCentimesEuros").descending();
		assertThat(platRepository.findAll(sort).get(0).getNom()).isEqualTo("Gigot d'agneau");
	}

	@Test
	void testFindAllPageable() {
		Pageable p = PageRequest.of(0, 2, Sort.by("nom").ascending());
		assertThat(platRepository.findAll(p).toList().get(0).getNom()).isEqualTo("Blanquette de veau");
		assertThat(platRepository.findAll(p).toList().get(1).getNom()).isEqualTo("Couscous");
	}

	@Test
	void testFindById() {
		assertThat(platRepository.findById(2).get().getNom().equals("Moules-frites"));
	}

	@Test
	@Transactional
	void testGetOne() {
		assertThat(platRepository.getOne(5).getId()).isEqualTo(5);
	}

	@Test
	void testCount() {
		assertThat(platRepository.count()).isEqualTo(6);
	}

	@Test
	void testFindByPrix() {
		assertThat(platRepository.findByPrixEnCentimesEuros(1300).contains(new Plat("Magret de canard", 1300)));
	}

	@Test
	void testAvgPrix() {
		assertThat(platRepository.avgPrix(1500)).isEqualTo(2050);
	}

	@Test
	void testFindByNomWithIngredients() {
		assertThat(platRepository.findByNomWithIngredients("Moules-frites")).asList().hasSize(6);
	}

	@Test
	void testSave() {
		Plat myPlat = new Plat("Riz cantonais", 1100);
		platRepository.save(myPlat);
		assertThat(platRepository.findAll().contains(myPlat));
	}

	@Test
	@Transactional
	void testChangerNom() {
		platRepository.changerNom("Magret de canard", "Riz pilaf");
		assertThat(platRepository.findByNom("Riz pilaf")).contains(new Plat("Riz pilaf", 1300));
	}

}
