package orbartal.demo.springboot.files.storage.config.log;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class SpringPropertiesNames {

	public List<String> getAll() {
		return Arrays.asList(
					"server.port", 
					"file.metadata.service", "file.storage.service", "file.storage.temp.dir",
					"spring.redis.host", "spring.redis.port"
				);
	}

}
