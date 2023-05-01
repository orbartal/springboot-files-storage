package orbartal.demo.springboot.files.storage.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;
import orbartal.demo.springboot.files.storage.model.FileResponse;

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

	public void writeFile(MultipartFile file) throws IOException {
		dirValidator.validateDirPath(UPLOADED_FOLDER);
		byte[] bytes = file.getBytes();
		Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
		Files.write(path, bytes);
	}

	public FileResponse readFile(String fileName) throws IOException {
		Path path = Paths.get(UPLOADED_FOLDER + fileName);
		long fileSize = path.toFile().length();
		Resource resource = new ByteArrayResource(Files.readAllBytes(path));
		return new FileResponse(fileName, resource, fileSize);
	}

}