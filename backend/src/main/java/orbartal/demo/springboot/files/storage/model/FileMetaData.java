package orbartal.demo.springboot.files.storage.model;

import java.util.UUID;

public class FileMetaData {

	private final UUID uid;
	private final String key;

	public FileMetaData(UUID uid, String key) {
		this.uid = uid;
		this.key = key;
	}

	public UUID getUid() {
		return uid;
	}

	public String getKey() {
		return key;
	}

}
