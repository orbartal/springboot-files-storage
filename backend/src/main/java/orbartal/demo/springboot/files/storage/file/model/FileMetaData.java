package orbartal.demo.springboot.files.storage.file.model;

import java.util.UUID;

public class FileMetaData {

	private final UUID uid;
	private final String key;
	private final String fileName;

	public FileMetaData(UUID uid, String key, String fileName) {
		this.uid = uid;
		this.key = key;
		this.fileName = fileName;
	}

	public UUID getUid() {
		return uid;
	}

	public String getKey() {
		return key;
	}

	public String getFileName() {
		return fileName;
	}

}
