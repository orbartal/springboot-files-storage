package orbartal.demo.springboot.files.storage.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisDbProperties {

	@Value("${spring.redis.port}")
    private int redisPort;

    @Value("${spring.redis.host}")
    private String redisHost;

    public int getRedisPort() {
        return redisPort;
    }

    public String getRedisHost() {
        return redisHost;
    }

	@Override
	public String toString() {
		return "RedisDbProperties [redisPort=" + redisPort + ", redisHost=" + redisHost + "]";
	}

}
