package orbartal.demo.springboot.files.storage.hadoop;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import orbartal.demo.springboot.files.storage.file.model.DownloadFileResult;
import orbartal.demo.springboot.files.storage.file.storage.FileStorageApi;

@Service
@Transactional
@ConditionalOnProperty(value = "file.storage.service", havingValue = "hadoop")
public class HadoopFileStorageApi implements FileStorageApi {

	@Autowired
	private HadoopPropties hadoopPropties;

	@Autowired
	private TempFileStorageApi TempFileStorageApi;

	@Autowired
	private HadoopWebHdfsApi hadoopWebHdfsApi;

	@Override
	public String writeFile(MultipartFile file) throws IOException {
		String nodeNameUrl = hadoopPropties.getNodeNameUrl();
		String hdfsPath = hadoopPropties.getHdfsDir();
		String fileName = file.getOriginalFilename();
		String rand = System.currentTimeMillis() + "";
		String fullPath = hdfsPath + "/" + rand + "/" + fileName;
		String urlUpload = hadoopWebHdfsApi.getUploadLinkFromHadoopNameNode(nodeNameUrl, fullPath);
		String inputPath = TempFileStorageApi.writeFile(file);
		hadoopWebHdfsApi.uploadFilePut(urlUpload, inputPath);
		TempFileStorageApi.deleteFile(inputPath);
		return fullPath;
	}

	@Override
	public DownloadFileResult readFile(String filePathInHdfs) {
		String nodeNameUrl = hadoopPropties.getNodeNameUrl();
		String urlDownload = hadoopWebHdfsApi.getDownloadLinkFromHadoopNameNode(nodeNameUrl, filePathInHdfs);
		byte[] content = hadoopWebHdfsApi.downloadFile(urlDownload);
		long size = content.length;
		InputStream body = new ByteArrayInputStream(content);
		String name = "TODO"; //TODO: Replace with original file name
		return new DownloadFileResult(name, body, size);
	}

}
