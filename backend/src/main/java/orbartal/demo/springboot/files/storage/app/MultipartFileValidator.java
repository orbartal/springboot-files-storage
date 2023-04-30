package orbartal.demo.springboot.files.storage.app;

import org.springframework.web.multipart.MultipartFile;

public class MultipartFileValidator {
	
	public static void validateMultipartFile(MultipartFile file) {
		if (file == null || file.getName() == null || file.getResource() == null) {
			throw new RuntimeException("Invalid input file");
		}
	}

}
