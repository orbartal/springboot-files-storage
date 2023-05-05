package orbartal.demo.springboot.files.storage.file.storage;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import orbartal.demo.springboot.files.storage.file.model.DownloadFileResult;

public interface FileStorageApi {

	String writeFile(MultipartFile file) throws IOException;

	DownloadFileResult readFile(String fileIdInStorage) throws IOException;

}