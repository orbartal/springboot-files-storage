package orbartal.demo.springboot.files.storage.hadoop;

import java.io.File;

import org.springframework.stereotype.Component;

import io.restassured.RestAssured;
import io.restassured.response.Response;

//TODO: Add validation to each method input and response
@Component
public class RestAssuredHadoopClientApi {

	private static final String APPLICATION_OCTET_STREAM = "application/octet-stream";

	public String getUploadLinkFromHadoopNameNode(String url) {
		Response response = RestAssured.given().when().put(url).andReturn();
		String location = response.getHeader("Location");
		return location;
	}

	@SuppressWarnings("unused")
	public void uploadFilePut(String url, File file) {
		Response response = RestAssured.given()
			.contentType(APPLICATION_OCTET_STREAM)
			.body(file)
			.put(url);
	}

	public String getDownloadLinkFromHadoopNameNode(String url) {
		Response response = RestAssured.given().redirects().follow(false).head(url).andReturn();
		String location = response.getHeader("Location");
		return location;
	}

	//TODO: Modify to a format that support larger files
	public byte[] downloadFile(String url) {
		Response response = RestAssured.given()
								.contentType(APPLICATION_OCTET_STREAM)
								.when().get(url)
								.andReturn();
		byte[] content = response.getBody().asByteArray();
		return content;
	}

}
