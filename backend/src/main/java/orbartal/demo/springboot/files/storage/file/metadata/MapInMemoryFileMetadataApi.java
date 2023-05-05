package orbartal.demo.springboot.files.storage.file.metadata;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import orbartal.demo.springboot.files.storage.file.model.FileMetaData;

@Service
@ConditionalOnProperty(value="file.metadata.service",havingValue = "java.in.memory.map")
public class MapInMemoryFileMetadataApi implements FileMetadataApi {

	private Map<UUID, FileMetaData> uidToKey = new HashMap<>(); // Temp solution in memeory

	@Override
	public void writeFileMetaData(FileMetaData metaData) {
		uidToKey.put(metaData.getUid(), metaData);
	}

	@Override
	public FileMetaData readFileMetaDataByUid(UUID uuid) {
		return uidToKey.get(uuid);
	}

}
