package orbartal.demo.springboot.files.storage.file.storage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;
import orbartal.demo.springboot.files.storage.file.model.DownloadFileResult;
import orbartal.demo.springboot.files.storage.file.model.FileMetaData;

@Service
@ConditionalOnProperty(value="file.storage.service",havingValue = "local.filesystem.temp")
public class LocalTempFileStorageApi implements FileStorageApi {

	@Value("${file.storage.temp.dir}")
    private String filesDirPath;

	private final DirValidator dirValidator;

	public LocalTempFileStorageApi(DirValidator dirValidator) {
		this.dirValidator = dirValidator;
	}

	@PostConstruct
	private void init() {
		dirValidator.createIfNotExists(filesDirPath);
		dirValidator.validateDirPath(filesDirPath);
	}

	@Override
	public String writeFile(MultipartFile file) throws IOException {
		byte[] bytes = file.getBytes();
		String fileName = file.getOriginalFilename();
		Path path = Paths.get(filesDirPath + fileName);
		Files.write(path, bytes);
		return fileName;
	}

	@Override
	public DownloadFileResult readFile(FileMetaData metaData) throws IOException {
		Path path = Paths.get(filesDirPath + metaData.getKey());
		long fileSize = path.toFile().length();
		InputStream body = new FileInputStream(path.toFile());
		return new DownloadFileResult(metaData.getFileName(), body, fileSize);
	}

}
