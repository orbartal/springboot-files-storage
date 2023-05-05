package orbartal.demo.springboot.files.storage.redis.file.metadata;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import orbartal.demo.springboot.files.storage.file.metadata.FileMetadataApi;
import orbartal.demo.springboot.files.storage.file.model.FileMetaData;
import orbartal.demo.springboot.files.storage.redis.persistence.FileMetadataRedisEntity;
import orbartal.demo.springboot.files.storage.redis.persistence.FileMetadataRedisRepository;

@Service
@Transactional
@ConditionalOnProperty(value = "file.metadata.service", havingValue = "redis")
public class FileMetadataRedis implements FileMetadataApi {

	@Autowired
	private FileMetadataRedisRepository redisRepository;

	@Transactional(readOnly = false)
	@Override
	public void writeFileMetaData(FileMetaData metaData) {
		UUID uuid = metaData.getUid();
		String value = metaData.getKey();
		Optional<FileMetadataRedisEntity> pOldEntitiy = redisRepository.findById(uuid);
		if (pOldEntitiy != null && pOldEntitiy.isPresent()) {
			throw new RuntimeException("Duplicate uuid: " + metaData.getUid());
		}
		FileMetadataRedisEntity newEntitiy = new FileMetadataRedisEntity();
		newEntitiy.setUid(uuid);
		newEntitiy.setValue(value);
		redisRepository.save(newEntitiy);
	}

	@Transactional(readOnly = true)
	@Override
	public FileMetaData readFileMetaDataByUid(UUID uid) {
		Optional<FileMetadataRedisEntity> opt = redisRepository.findById(uid);
		if (opt.isPresent()) {
			FileMetadataRedisEntity e = opt.get();
			return new FileMetaData(e.getUid(), e.getValue());
		}
		return null;
	}

}
