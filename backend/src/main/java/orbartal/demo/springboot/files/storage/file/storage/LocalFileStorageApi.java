package orbartal.demo.springboot.files.storage.file.storage;

import java.io.File;
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
@ConditionalOnProperty(value="file.storage.service",havingValue = "filesystem")
public class LocalFileStorageApi implements FileStorageApi {

	@Value("${file.storage.dir}")
    private String filesDirPath;

	private final DirValidator dirValidator;

	public LocalFileStorageApi(DirValidator dirValidator) {
		this.dirValidator = dirValidator;
	}

	@PostConstruct
	private void init() {
		dirValidator.createIfNotExists(filesDirPath);
		dirValidator.validateDirPath(filesDirPath);
	}

	@Override
	public String writeFile(MultipartFile file) {
		String fileName = file.getOriginalFilename();
		File target = Paths.get(filesDirPath, fileName).toFile();
		writeMultipartFileIntoFile(file, target);
		return target.getAbsolutePath();
	}

	private void writeMultipartFileIntoFile(MultipartFile inputFile, File targetFile) {
		try (InputStream is = inputFile.getInputStream()) {
			Files.copy(is, targetFile.toPath());
		}catch (Throwable t) {
			throw new RuntimeException("Fail to write upload file into local file", t);
		}
	}

	@Override
	public DownloadFileResult readFile(FileMetaData metaData) throws IOException {
		Path path = Paths.get(metaData.getKey());
		long fileSize = path.toFile().length();
		InputStream body = new FileInputStream(path.toFile());
		return new DownloadFileResult(metaData.getFileName(), body, fileSize);
	}

}
