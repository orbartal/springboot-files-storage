package orbartal.demo.springboot.files.storage.hadoop;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HadoopWebHdfsApi {

	@Autowired
	private RestAssuredHadoopClientApi clientApi;

	public String getUploadLinkFromHadoopNameNode(String nodeNameUrl, String hdfsPath) {
		String nameUrl = HadoopWebApiUrlFactory.buildCreateFileUrl(nodeNameUrl, hdfsPath);
		String dataUrl = clientApi.getUploadLinkFromHadoopNameNode(nameUrl);
		return dataUrl;
	}

	public void uploadFilePut(String urlUpload, String path) {
		File file = new File(path);
		clientApi.uploadFilePut(urlUpload, file);
	}

	public String getDownloadLinkFromHadoopNameNode(String nodeNameUrl, String filePathInHdfs) {
		String nameUrl = HadoopWebApiUrlFactory.buildReadFileUrl(nodeNameUrl, filePathInHdfs);
		String dataUrl = clientApi.getDownloadLinkFromHadoopNameNode(nameUrl);
		return dataUrl;
	}

	public byte[] downloadFile(String url) {
		return clientApi.downloadFile(url);
	}

}
