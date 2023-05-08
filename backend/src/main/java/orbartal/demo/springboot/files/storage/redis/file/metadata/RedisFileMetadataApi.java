package orbartal.demo.springboot.files.storage.redis.file.metadata;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import orbartal.demo.springboot.files.storage.file.metadata.FileMetadataApi;
import orbartal.demo.springboot.files.storage.file.model.FileMetaData;

@Service
@Transactional
@ConditionalOnProperty(value = "file.metadata.service", havingValue = "redis")
public class RedisFileMetadataApi implements FileMetadataApi {

	@Autowired
	private RedisFileMetadataRepository redisRepository;

	@Transactional(readOnly = false)
	@Override
	public void writeFileMetaData(FileMetaData metaData) {
		UUID uuid = metaData.getUid();
		String key = metaData.getKey();
		Optional<RedisFileMetadataEntity> pOldEntitiy = redisRepository.findById(uuid);
		if (pOldEntitiy != null && pOldEntitiy.isPresent()) {
			throw new RuntimeException("Duplicate uuid: " + metaData.getUid());
		}
		RedisFileMetadataEntity newEntitiy = new RedisFileMetadataEntity();
		newEntitiy.setUid(uuid);
		newEntitiy.setFileKey(key);
		newEntitiy.setFileName(key);
		redisRepository.save(newEntitiy);
	}

	@Transactional(readOnly = true)
	@Override
	public FileMetaData readFileMetaDataByUid(UUID uid) {
		Optional<RedisFileMetadataEntity> opt = redisRepository.findById(uid);
		if (opt.isPresent()) {
			RedisFileMetadataEntity e = opt.get();
			return new FileMetaData(e.getUid(), e.getFileKey(), e.getFileName());
		}
		return null;
	}

}
