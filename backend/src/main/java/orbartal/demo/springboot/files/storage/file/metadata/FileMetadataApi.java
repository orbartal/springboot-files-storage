package orbartal.demo.springboot.files.storage.file.metadata;

import java.util.UUID;

import orbartal.demo.springboot.files.storage.file.model.FileMetaData;

public interface FileMetadataApi {

	void writeFileMetaData(FileMetaData metaData);

	FileMetaData readFileMetaDataByUid(UUID uuid);

}