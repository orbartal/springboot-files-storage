package orbartal.demo.springboot.files.storage.redis.file.metadata;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

public interface RedisFileMetadataRepository extends CrudRepository<RedisFileMetadataEntity, UUID> {

}
