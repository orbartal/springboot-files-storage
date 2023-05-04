package orbartal.demo.springboot.files.storage.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import orbartal.demo.springboot.files.storage.model.DownloadFileResult;
import orbartal.demo.springboot.files.storage.model.FileMetaData;
import orbartal.demo.springboot.files.storage.model.UploadFileResult;

@Service
public class FileService {

	private final FileStorageService fileStorage;
	private final FileMetadataApi fileMetadata;

	public FileService(FileStorageService fileStorage, FileMetadataApi fileMetadata) {
		this.fileStorage = fileStorage;
		this.fileMetadata = fileMetadata;
	}

	public UploadFileResult writeFile(MultipartFile file) throws IOException {
		String key = fileStorage.writeFile(file);
		FileMetaData metaData = new FileMetaData(UUID.randomUUID(), key);
		fileMetadata.writeFileMetaData(metaData);
		return new UploadFileResult(metaData.getUid().toString());
	}

	public DownloadFileResult readFile(String suid) throws IOException {
		UUID uuid = UUID.fromString(suid);
		FileMetaData metaData = fileMetadata.readFileMetaDataByUid(uuid);
		return fileStorage.readFile(metaData.getKey());
	}

}