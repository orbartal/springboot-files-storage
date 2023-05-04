package orbartal.demo.springboot.files.storage.redis;

import org.springframework.boot.test.context.TestConfiguration;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import orbartal.demo.springboot.files.storage.redis.config.RedisDbProperties;
import redis.embedded.RedisServer;

@TestConfiguration
public class TestRedisConfiguration {

    private final RedisServer redisServer;

    public TestRedisConfiguration(final RedisDbProperties properties) {
        this.redisServer = new RedisServer(properties.getRedisPort());
//		Uncomment below if running on windows and can't start redis server
//        this.redisServer = RedisServer.builder().setting("maxheap 200m").port(6379).setting("bind localhost").build();
//        redisServer.start();
    }

    @PostConstruct
    public void postConstruct() {
        redisServer.start();
    }

    @PreDestroy
    public void preDestroy() {
        redisServer.stop();
    }
}
