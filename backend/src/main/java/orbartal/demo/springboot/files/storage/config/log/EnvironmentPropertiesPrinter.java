package orbartal.demo.springboot.files.storage.config.log;


import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class EnvironmentPropertiesPrinter {

	private final Environment env;
	private final PropertiesPrinter printer;

	public EnvironmentPropertiesPrinter(Environment env, PropertiesPrinter printer) {
		this.env = env;
		this.printer = printer;
	}

	@PostConstruct
	public void logApplicationProperties() {
		String title = "EnvironmentPropertiesPrinter.logApplicationProperties";
		printer.logEnvironment(env, title);
	}
}