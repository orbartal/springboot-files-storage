package orbartal.demo.springboot.files.storage.redis.file.metadata;

import java.util.UUID;
import org.springframework.data.annotation.Id;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("file_metadata")
public class RedisFileMetadataEntity {

	@Id
	// universally unique identifier used in the api
	private UUID uid;

	//The file key is the id of a file in the file storage. It enable to find a file in the storage using metadata.
	private String fileKey;

	private String fileName;

	public UUID getUid() {
		return uid;
	}

	public void setUid(UUID uid) {
		this.uid = uid;
	}

	public String getFileKey() {
		return fileKey;
	}

	public void setFileKey(String fileKeyInStorage) {
		this.fileKey = fileKeyInStorage;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "RedisFileMetadataEntity [uid=" + uid + ", fileKey=" + fileKey + ", fileName=" + fileName + "]";
	}

}
