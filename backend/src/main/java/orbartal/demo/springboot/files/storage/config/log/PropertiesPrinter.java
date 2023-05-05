package orbartal.demo.springboot.files.storage.config.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class PropertiesPrinter {

	private static final Logger logger = LoggerFactory.getLogger(EnvironmentPropertiesPrinter.class);

	private final SpringPropertiesNames springPropertiesNames;

	public PropertiesPrinter(SpringPropertiesNames springPropertiesNames) {
		this.springPropertiesNames = springPropertiesNames;
	}

	public void logEnvironment(Environment env, String title) {
		logger.info("************************* Start env " + title + " ******************************");
		springPropertiesNames.getAll().forEach(p->logProperty(env, p));
		logger.info("************************* end env " + title + " ******************************");
	}

	private static void logProperty(Environment env, String property) {
		logger.info("{}={}", property, env.getProperty(property));
	}

}
