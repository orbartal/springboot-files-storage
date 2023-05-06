package orbartal.demo.springboot.files.storage.hadoop;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class HadoopTestUtil {

	private static final String MULTIPART_MIXED = "multipart/mixed";
	
	public static String buildCreateFileUrl(String nodeNameHost, int nodeNamePort, String hdfsPath) {
		StringBuilder sb = new StringBuilder();
		sb.append("http://");
		sb.append(nodeNameHost);
		sb.append(":");
		sb.append(nodeNamePort);
		sb.append("/webhdfs/v1");
		sb.append(hdfsPath);
		sb.append("?op=CREATE");
		String url = sb.toString();
		return url;
	}

	public static Response uploadFilePut(File file, String url) {
		return RestAssured.given()
				.contentType(MULTIPART_MIXED)
				.multiPart(file)
				.put(url);
	}

}
