package orbartal.demo.springboot.files.storage.model;

public class UploadFileResult {

	private String key;

	public UploadFileResult(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
