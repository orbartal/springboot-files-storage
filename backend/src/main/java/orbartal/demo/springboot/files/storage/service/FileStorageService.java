package orbartal.demo.springboot.files.storage.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;
import orbartal.demo.springboot.files.storage.model.DownloadFile;

@Service
public class FileStorageService {

	private static String UPLOADED_FOLDER = "/tmp/uploads/";

	private final DirValidator dirValidator;

	public FileStorageService(DirValidator dirValidator) {
		this.dirValidator = dirValidator;
	}

	@PostConstruct
	private void init() {
		dirValidator.createIfNotExists(UPLOADED_FOLDER);
		dirValidator.validateDirPath(UPLOADED_FOLDER);
	}

	public String writeFile(MultipartFile file) throws IOException {
		byte[] bytes = file.getBytes();
		String fileName = file.getOriginalFilename();
		Path path = Paths.get(UPLOADED_FOLDER + fileName);
		Files.write(path, bytes);
		return fileName;
	}

	public DownloadFile readFile(String fileName) throws IOException {
		Path path = Paths.get(UPLOADED_FOLDER + fileName);
		long fileSize = path.toFile().length();
		InputStream body = new FileInputStream(path.toFile());
		return new DownloadFile(fileName, body, fileSize);
	}

}
