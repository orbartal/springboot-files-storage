package orbartal.demo.springboot.files.storage.file.manager;

import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import orbartal.demo.springboot.files.storage.file.metadata.FileMetadataApi;
import orbartal.demo.springboot.files.storage.file.model.DownloadFileResult;
import orbartal.demo.springboot.files.storage.file.model.FileMetaData;
import orbartal.demo.springboot.files.storage.file.model.UploadFileResult;
import orbartal.demo.springboot.files.storage.file.storage.FileStorageApi;

@Service
public class FileService {

	private final FileStorageApi fileStorage;
	private final FileMetadataApi fileMetadata;

	public FileService(FileStorageApi fileStorage, FileMetadataApi fileMetadata) {
		this.fileStorage = fileStorage;
		this.fileMetadata = fileMetadata;
	}

	public UploadFileResult writeFile(MultipartFile file) throws IOException {
		String key = fileStorage.writeFile(file);
		FileMetaData metaData = new FileMetaData(UUID.randomUUID(), key, file.getOriginalFilename());
		fileMetadata.writeFileMetaData(metaData);
		return new UploadFileResult(metaData.getUid().toString());
	}

	public DownloadFileResult readFile(String suid) throws IOException {
		UUID uuid = UUID.fromString(suid);
		FileMetaData metaData = fileMetadata.readFileMetaDataByUid(uuid);
		return fileStorage.readFile(metaData.getKey());
	}

}