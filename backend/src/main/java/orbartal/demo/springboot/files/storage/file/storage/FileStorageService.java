package orbartal.demo.springboot.files.storage.file.storage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;
import orbartal.demo.springboot.files.storage.file.model.DownloadFileResult;

@Service
@ConditionalOnProperty(value="file.storage.service",havingValue = "local.filesystem.temp")
public class FileStorageService implements FileStorageApi {

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

	@Override
	public String writeFile(MultipartFile file) throws IOException {
		byte[] bytes = file.getBytes();
		String fileName = file.getOriginalFilename();
		Path path = Paths.get(UPLOADED_FOLDER + fileName);
		Files.write(path, bytes);
		return fileName;
	}

	@Override
	public DownloadFileResult readFile(String fileName) throws IOException {
		Path path = Paths.get(UPLOADED_FOLDER + fileName);
		long fileSize = path.toFile().length();
		InputStream body = new FileInputStream(path.toFile());
		return new DownloadFileResult(fileName, body, fileSize);
	}

}
