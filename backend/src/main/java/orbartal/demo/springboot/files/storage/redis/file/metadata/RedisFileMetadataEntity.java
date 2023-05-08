package orbartal.demo.springboot.files.storage.redis.file.metadata;

import java.util.UUID;
import org.springframework.data.annotation.Id;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("file_metadata")
public class RedisFileMetadataEntity {

	@Id
	// universally unique identifier used in the api
	private UUID uid;

	//The value is a string but can easily modify to any other type of data we want to store.
	private String value;
	
	//TODO: Modify value into key
	private String fileName;

	public UUID getUid() {
		return uid;
	}

	public void setUid(UUID uid) {
		this.uid = uid;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "RedisFileMetadataEntity [uid=" + uid + ", value=" + value + ", fileName=" + fileName + "]";
	}

}
