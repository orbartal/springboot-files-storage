package orbartal.demo.springboot.files.storage.file.storage;

import java.io.File;

import org.springframework.stereotype.Service;

@Service
public class DirValidator {

	public void createIfNotExists(String path) {
		File folder = new File(path);
		if (!folder.exists()) {
			folder.mkdir();
		}
	}

	public void validateDirPath(String path) {
		File folder = new File(path);
		if (!folder.exists()) {
			throw new RuntimeException("Invalid file output path dir dosen't exsits");
		}
		if (!folder.isDirectory()) {
			throw new RuntimeException("Invalid file output path it is not a directory");
		}
		if (!folder.canWrite()) {
			throw new RuntimeException("Invalid file output path no write permissions");
		}
	}

}