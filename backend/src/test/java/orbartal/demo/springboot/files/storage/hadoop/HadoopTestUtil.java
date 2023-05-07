package orbartal.demo.springboot.files.storage.hadoop;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class HadoopTestUtil {

	private static final String APPLICATION_OCTET_STREAM = "application/octet-stream";
	
	public static String buildCreateFileUrl(String nodeNameUrl, String hdfsPath) {
		StringBuilder sb = new StringBuilder();
		sb.append(nodeNameUrl);
		sb.append("/webhdfs/v1");
		sb.append(hdfsPath);
		sb.append("?op=CREATE");
		String url = sb.toString();
		return url;
	}

	public static Response uploadFilePut(File file, String url) {
		return RestAssured.given()
				.contentType(APPLICATION_OCTET_STREAM)
				.body(file)
				.put(url);
	}

	public static String buildReadFileUrl(String nodeNameUrl, String hdfsPath) {
		StringBuilder sb = new StringBuilder();
		sb.append(nodeNameUrl);
		sb.append("/webhdfs/v1");
		sb.append(hdfsPath);
		sb.append("?op=OPEN");
		return sb.toString();
	}

	public static Response downloadFile(String url) {
		return RestAssured.given()
				.contentType(APPLICATION_OCTET_STREAM)
				.when()
				.get(url)
				.andReturn();
	}

}
