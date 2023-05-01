package orbartal.demo.springboot.files.storage.app;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import orbartal.demo.springboot.files.storage.model.DownloadFile;

@Component
public class HttpResponseFactory {
	
	private final HttpHeadersFactory httpHeadersFactory;
	
	private final  ResponseEntityFactory httpResponseFactory;

	public HttpResponseFactory(HttpHeadersFactory httpHeadersFactory, ResponseEntityFactory httpResponseFactory) {
		this.httpHeadersFactory = httpHeadersFactory;
		this.httpResponseFactory = httpResponseFactory;
	}
	
	
	public ResponseEntity<?> buildBadRequest() {
		return httpResponseFactory.buildBadRequest();
	}

	public ResponseEntity<String> buildSuccess(MultipartFile file) {
		return httpResponseFactory.buildSuccess(file);
	}

	public ResponseEntity<?> buildFileResponse(DownloadFile response) {
		HttpHeaders headers = httpHeadersFactory.buildDownloadHead(response.getName());
		return httpResponseFactory.buildFileResponse(headers, response);
	}

}
