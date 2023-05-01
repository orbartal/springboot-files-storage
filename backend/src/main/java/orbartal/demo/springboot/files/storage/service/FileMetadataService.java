package orbartal.demo.springboot.files.storage.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import orbartal.demo.springboot.files.storage.model.FileMetaData;

@Service
public class FileMetadataService {

	private Map<UUID, FileMetaData> uidToKey = new HashMap<>(); // Temp solution in memeory

	public void writeFileMetaData(FileMetaData metaData) {
		uidToKey.put(metaData.getUid(), metaData);
	}

	public FileMetaData readFileMetaDataByUid(UUID uuid) {
		return uidToKey.get(uuid);
	}

}
