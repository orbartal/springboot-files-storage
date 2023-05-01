package orbartal.demo.springboot.files.storage.app;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import orbartal.demo.springboot.files.storage.model.DownloadFile;

@Component
public class ResponseEntityFactory {

	public ResponseEntity<Object> buildBadRequest() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> buildSuccess(MultipartFile file) {
		return new ResponseEntity<>("Successfully uploaded files: " + file.getName(), HttpStatus.OK);
	}

	public ResponseEntity<?> buildFileResponse(HttpHeaders headers, DownloadFile response) {
		return ResponseEntity.ok()
				.headers(headers)
				.contentLength(response.getSizeInBytes())
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				.body(new InputStreamResource(response.getBody()));
	}

}
