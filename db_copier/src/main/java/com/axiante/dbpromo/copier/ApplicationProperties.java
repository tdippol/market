package com.axiante.dbpromo.copier;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ApplicationProperties {
	@Getter
	private final Properties properties = new Properties();
	@Getter
	private static final ApplicationProperties instance = new ApplicationProperties();

	private ApplicationProperties() {
		try (InputStream in = this.getClass().getClassLoader().getResourceAsStream("application.properties")) {
			log.info("initializing properties from " + this.getClass().getClassLoader().getResource("application.properties"));
			if (in != null) {
				properties.load(in);
				appendContextProperties((String) properties.get("database"));
			} else {
				log.error("cannot find configuration file ! Aborting !");
				System.exit(2);
			}
		} catch (IOException e) {
			log.error("error loading configuration ! ", e);

			log.info("aborting");
			System.exit(1);
		}
	}

	private void appendContextProperties(String context){
		if ( context == null ){
			context = "muipromo";
			log.warn("nessun database specificato! Default=muipromo");
		}
		if ("muipromo".equals(context) || "dbpromo".equals(context) ) {
			Properties properties = new Properties();
			try (InputStream in = this.getClass().getClassLoader().getResourceAsStream("application.properties." + context)) {
				if (in != null) {
					properties.load(in);
				} else {
					log.error("cannot find configuration file ! Aborting !");
					System.exit(2);
				}
				this.properties.putAll(properties);
			} catch (IOException e) {
				log.error("error loading configuration ! ", e);

				log.info("aborting");
				System.exit(1);
			}
		} else {
			log.fatal("database " + context + " non supportato");
			System.exit(2);
		}
	}

}
