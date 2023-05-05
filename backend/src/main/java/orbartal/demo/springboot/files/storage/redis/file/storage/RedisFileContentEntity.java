package orbartal.demo.springboot.files.storage.redis.file.storage;

import java.util.UUID;
import org.springframework.data.annotation.Id;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("file_content")
public class RedisFileContentEntity {

	@Id
	// universally unique identifier used in the api
	private UUID uid;
	
	//The value is a byte array of file content. Please notice this can only work for small files.
	private byte[] value;

	public UUID getUid() {
		return uid;
	}

	public void setUid(UUID uid) {
		this.uid = uid;
	}

	public byte[] getValue() {
		return value;
	}

	public void setValue(byte[] value) {
		this.value = value;
	}

	@Override
	public String toString() {
		int valueSize = (value!=null)? value.length : 0;
		return "FileMetadataRedisEntity [uid=" + uid + ", valueSize =" + valueSize + "]";
	}

}
