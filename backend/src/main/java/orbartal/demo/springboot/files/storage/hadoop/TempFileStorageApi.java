package orbartal.demo.springboot.files.storage.hadoop;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@ConditionalOnProperty(value = "file.storage.service", havingValue = "hadoop")
public class TempFileStorageApi {

	public String writeFile(MultipartFile file) throws IOException {
		Path path = Files.createTempFile(file.getOriginalFilename(), ".tmp");
		byte[] bytes = file.getBytes();
		Files.write(path, bytes);
		return path.toString();
	}
	
	public boolean deleteFile(String path) throws IOException {
		File file = new File(path);
		return file.delete();
	}

}
