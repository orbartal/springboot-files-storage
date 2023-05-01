package orbartal.demo.springboot.files.storage.model;

import org.springframework.core.io.Resource;

public class FileResponse {

	private String name;
	private Resource body;
	private Long sizeInBytes;

	public FileResponse() {}

	public FileResponse(String name, Resource body, Long sizeInBytes) {
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

	public Resource getBody() {
		return body;
	}

	public void setBody(Resource body) {
		this.body = body;
	}

	public Long getSizeInBytes() {
		return sizeInBytes;
	}

	public void setSizeInBytes(Long sizeInBytes) {
		this.sizeInBytes = sizeInBytes;
	}

}
