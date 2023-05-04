package orbartal.demo.springboot.files.storage.redis;

import org.junit.jupiter.api.Assertions;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import orbartal.demo.springboot.files.storage.redis.persistence.FileMetadataRedisEntity;
import orbartal.demo.springboot.files.storage.redis.persistence.FileMetadataRedisRepository;

@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestRedisConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FileMetadataRedisRepositoryTest {

	private static final UUID UID = UUID.randomUUID();
	private static final String VALUE = UUID.randomUUID().toString().replaceAll("-", "");

	@Autowired
	private FileMetadataRedisRepository redisRepository;

	@Order(1)
	@Test
	public void readFromRedisNotFound() {
		Optional<FileMetadataRedisEntity> out = redisRepository.findById(UID);
		Assertions.assertNotNull(out);
		Assertions.assertFalse(out.isPresent());
	}

	@Order(2)
	@Test
	public void writeToRedis() {
		FileMetadataRedisEntity input = new FileMetadataRedisEntity();
		input.setUid(UID);
		input.setValue(VALUE);

		FileMetadataRedisEntity out = redisRepository.save(input);

		Assertions.assertNotNull(out);
		Assertions.assertEquals(UID, out.getUid());
		Assertions.assertEquals(VALUE, out.getValue());
	}

	@Order(3)
	@Test
	public void readFromRedisFound() {
		Optional<FileMetadataRedisEntity> out = redisRepository.findById(UID);
		Assertions.assertNotNull(out);
		Assertions.assertTrue(out.isPresent());
		Assertions.assertEquals(UID, out.get().getUid());
		Assertions.assertEquals(VALUE, out.get().getValue());
	}
}