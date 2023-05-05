package orbartal.demo.springboot.files.storage.redis.file.storage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import orbartal.demo.springboot.files.storage.file.model.DownloadFileResult;
import orbartal.demo.springboot.files.storage.file.storage.FileStorageApi;

@Service
@Transactional
@ConditionalOnProperty(value = "file.storage.service", havingValue = "redis")
public class RedisFileStorageApi implements FileStorageApi {

	@Autowired
	private RedisFileContentRepository redisRepository;

	@Override
	public String writeFile(MultipartFile file) throws IOException {
		UUID uuid = UUID.randomUUID();
		byte[] value = file.getBytes();
		Optional<RedisFileContentEntity> pOldEntitiy = redisRepository.findById(uuid);
		if (pOldEntitiy != null && pOldEntitiy.isPresent()) {
			throw new RuntimeException("Duplicate uuid: " + uuid); // Very rare bug
		}
		RedisFileContentEntity newEntitiy = new RedisFileContentEntity();
		newEntitiy.setUid(uuid);
		newEntitiy.setName(file.getOriginalFilename());
		newEntitiy.setValue(value);
		redisRepository.save(newEntitiy);
		return uuid.toString();
	}

	@Override
	public DownloadFileResult readFile(String suid) throws IOException {
		UUID uuid = UUID.fromString(suid);
		Optional<RedisFileContentEntity> opt = redisRepository.findById(uuid);
		if (opt.isPresent()) {
			RedisFileContentEntity e = opt.get();
			String name = e.getName();
			Long sizeInBytes = (long) e.getValue().length;
			InputStream body = new ByteArrayInputStream(e.getValue());
			return new DownloadFileResult(name, body, sizeInBytes);
		}
		return null;
	}

}
