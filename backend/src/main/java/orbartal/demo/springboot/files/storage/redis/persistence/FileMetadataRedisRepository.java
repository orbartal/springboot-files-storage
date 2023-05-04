package orbartal.demo.springboot.files.storage.redis.persistence;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

public interface FileMetadataRedisRepository extends CrudRepository<FileMetadataRedisEntity, UUID> {

}
