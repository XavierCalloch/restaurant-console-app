package dev.dao;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.config.DataSourceH2TestConfig;
import dev.entite.Plat;

@SpringJUnitConfig({ PlatDaoJdbc.class, DataSourceH2TestConfig.class })
@TestPropertySource("classpath:test.properties")
@ActiveProfiles({ "PlatDaoJdbc", "service1" })
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PlatDaoJdbcIntegrationTest {

	// injection du bean à tester
	@Autowired
	private PlatDaoJdbc platDaoJdbc;

	private JdbcTemplate jdbcTemplate;

	@Test
	void testListerPlatsNonVide() {
		assertFalse(platDaoJdbc.listerPlats().isEmpty());
	}

	@Test
	void testAjouterPlat() {
		platDaoJdbc.ajouterPlat("RizCantonais", 1100);
		assertTrue(platDaoJdbc.listerPlats().contains(new Plat("RizCantonais", 1100)));
//		assertTrue(
//				jdbcTemplate.query("select * from plat", new PlatRowMapper()).contains(new Plat("RizCantonais", 1100)));

	}

}
