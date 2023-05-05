package orbartal.demo.springboot.files.storage.redis.file.storage;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

public interface RedisFileContentRepository extends CrudRepository<RedisFileContentEntity, UUID> {

}
