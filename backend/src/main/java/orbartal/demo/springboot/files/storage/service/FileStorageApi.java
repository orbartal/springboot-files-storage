package orbartal.demo.springboot.files.storage.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import orbartal.demo.springboot.files.storage.model.DownloadFileResult;

public interface FileStorageApi {

	String writeFile(MultipartFile file) throws IOException;

	DownloadFileResult readFile(String fileName) throws IOException;

}