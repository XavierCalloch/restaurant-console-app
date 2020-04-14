package dev;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import dev.config.DataSourceConfig;
import dev.dao.PlatDaoJdbc;
import dev.entite.Plat;

public class JdbcApp {

	private static final Logger LOGGER = Logger.getLogger(JdbcApp.class.getName());

	public static void main(String[] args) {

		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				DataSourceConfig.class)) {

			PlatDaoJdbc platDaoJdbc = context.getBean(PlatDaoJdbc.class);

			Integer nbElements = platDaoJdbc.compter();

			LOGGER.info("NB ELEMENTS=" + nbElements);

			List<Plat> plats = platDaoJdbc.listerPlats();

			for (Plat art : plats) {
				LOGGER.info(art.getNom());
			}

		}

	}

}
