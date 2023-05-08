package orbartal.demo.springboot.files.storage.file.storage;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import orbartal.demo.springboot.files.storage.file.model.DownloadFileResult;
import orbartal.demo.springboot.files.storage.file.model.FileMetaData;

public interface FileStorageApi {

	String writeFile(MultipartFile file) throws IOException;

	DownloadFileResult readFile(FileMetaData metaData) throws IOException;

}