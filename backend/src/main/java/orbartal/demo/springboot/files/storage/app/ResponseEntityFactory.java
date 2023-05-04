package orbartal.demo.springboot.files.storage.app;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import orbartal.demo.springboot.files.storage.file.model.DownloadFileResult;
import orbartal.demo.springboot.files.storage.file.model.UploadFileResult;

@Component
public class ResponseEntityFactory {

	public ResponseEntity<Object> buildBadRequest() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<UploadFileResult> buildSuccess(UploadFileResult r) {
		return new ResponseEntity<>(r, HttpStatus.OK);
	}

	public ResponseEntity<?> buildFileResponse(HttpHeaders headers, DownloadFileResult response) {
		return ResponseEntity.ok()
				.headers(headers)
				.contentLength(response.getSizeInBytes())
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
				.body(new InputStreamResource(response.getBody()));
	}

}
