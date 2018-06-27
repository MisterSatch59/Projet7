package org.biblio.biblioBatch.livre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:config.properties")
public class SpringConfiguration {

	@Autowired
	private Environment env;

	@Bean
	public Config config() {
		Config config = new Config();
		config.setSujet(env.getProperty("message.sujet"));
		config.setPresentation(env.getProperty("message.presentation"));
		config.setDemande(env.getProperty("message.demande"));
		config.setFormulePolitesse(env.getProperty("message.formule.politesse"));
		config.setSignature(env.getProperty("message.signature"));

		return config;
	}
}