package orbartal.demo.springboot.files.storage.app;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import orbartal.demo.springboot.files.storage.model.DownloadFile;
import orbartal.demo.springboot.files.storage.model.UploadFileResult;
import orbartal.demo.springboot.files.storage.service.FileService;

@Service
public class FileApp {

	private final FileService fileService;

	private final HttpResponseFactory httpResponseFactory;

	public FileApp(FileService filesService, HttpResponseFactory httpResponseFactory) {
		this.fileService = filesService;
		this.httpResponseFactory = httpResponseFactory;
	}

	public ResponseEntity<?> writeFile(MultipartFile file) {
		try {
			MultipartFileValidator.validateMultipartFile(file);
			UploadFileResult r = fileService.writeFile(file);
			return httpResponseFactory.buildSuccess(r);
		} catch (IOException e) {
			return httpResponseFactory.buildBadRequest();
		}
	}

	public ResponseEntity<?> readFile(String fileName) {
		try {
			DownloadFile response = fileService.readFile(fileName);
			return httpResponseFactory.buildFileResponse(response);
		} catch (IOException e) {
			return httpResponseFactory.buildBadRequest();
		}
	}

}