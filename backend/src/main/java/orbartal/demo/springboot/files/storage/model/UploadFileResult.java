package orbartal.demo.springboot.files.storage.model;

public class UploadFileResult {

	private String uid;

	public UploadFileResult(String uid) {
		this.uid = uid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

}
