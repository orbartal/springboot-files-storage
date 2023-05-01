package orbartal.demo.springboot.files.storage.model;

import java.io.InputStream;

public class DownloadFile {

	private String name;
	private InputStream body;
	private Long sizeInBytes;

	public DownloadFile() {}

	public DownloadFile(String name, InputStream body, Long sizeInBytes) {
		this.name = name;
		this.body = body;
		this.sizeInBytes = sizeInBytes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public InputStream getBody() {
		return body;
	}

	public void setBody(InputStream body) {
		this.body = body;
	}

	public Long getSizeInBytes() {
		return sizeInBytes;
	}

	public void setSizeInBytes(Long sizeInBytes) {
		this.sizeInBytes = sizeInBytes;
	}

}
