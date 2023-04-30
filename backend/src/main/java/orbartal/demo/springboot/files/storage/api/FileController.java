package orbartal.demo.springboot.files.storage.api;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import orbartal.demo.springboot.files.storage.app.FileApp;

@RestController
@RequestMapping("/api/v1/file")
public class FileController {

	private final FileApp fileApp;

	public FileController(FileApp fileApp) {
		this.fileApp = fileApp;
	}

	@PostMapping(value = "/upload", consumes = { "multipart/form-data" })
	@Operation(summary = "Upload a single File")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile uploadfile) {
		return fileApp.writeFile(uploadfile);
	}

	@RequestMapping(path = "/download", method = RequestMethod.GET)
	@Operation(summary = "Download a File")
	public ResponseEntity<?> download(String fileName) throws IOException {
		return fileApp.readFile(fileName);

	}

}