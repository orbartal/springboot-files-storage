package orbartal.demo.springboot.files.storage.hadoop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HadoopConfig {

	@Value("${hadoop.namenode.url}")
	private String nodeNameUrl;

	@Value("${hadoop.hdfs.dir}")
	private String hdfsDir;

	@Bean
	public HadoopPropties getHadoopPropties() {
		return new HadoopPropties(nodeNameUrl, hdfsDir);
	}

}
