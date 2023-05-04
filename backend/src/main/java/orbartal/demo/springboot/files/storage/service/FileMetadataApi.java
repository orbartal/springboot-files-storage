package orbartal.demo.springboot.files.storage.service;

import java.util.UUID;

import orbartal.demo.springboot.files.storage.model.FileMetaData;

public interface FileMetadataApi {

	void writeFileMetaData(FileMetaData metaData);

	FileMetaData readFileMetaDataByUid(UUID uuid);

}