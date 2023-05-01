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
import orbartal.demo.springboot.files.storage.model.UploadFileResult;

@Service
public class FileService {

	private static String UPLOADED_FOLDER = "/tmp/uploads/";

	private final DirValidator dirValidator;

	public FileService(DirValidator dirValidator) {
		this.dirValidator = dirValidator;
	}

	@PostConstruct
	private void init() {
		dirValidator.createIfNotExists(UPLOADED_FOLDER);
		dirValidator.validateDirPath(UPLOADED_FOLDER);
	}

	public UploadFileResult writeFile(MultipartFile file) throws IOException {
		dirValidator.validateDirPath(UPLOADED_FOLDER);
		byte[] bytes = file.getBytes();
		Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
		Files.write(path, bytes);
		return new UploadFileResult(file.getOriginalFilename());
	}

	public DownloadFile readFile(String fileName) throws IOException {
		Path path = Paths.get(UPLOADED_FOLDER + fileName);
		long fileSize = path.toFile().length();
		InputStream body = new FileInputStream(path.toFile());
		return new DownloadFile(fileName, body, fileSize);
	}

}