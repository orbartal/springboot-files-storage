package orbartal.demo.springboot.files.storage.e2e;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;

public class FileTestUtil {
	
	private static final String MULTIPART_FORM_DATA_VALUE = "multipart/form-data";

	public static File createTempFile(String fileName) {
		String osTmpDir = System.getProperty("java.io.tmpdir");
		try {
			Path temp = Files.createTempFile(fileName, ".txt");
			return temp.toFile();
		} catch (IOException e) {
			throw new RuntimeException("Fail to create new temp file: " + fileName + ", at dir = " + osTmpDir);
		}
	}

	public static void writeTextIntoFile(File input, String text) throws IOException {
		Path path = input.toPath();
		byte[] strToBytes = text.getBytes();
		Files.write(path, strToBytes);
	}

	public static String buildUploadUrl(int serverPort) {
		return "http://localhost:" + serverPort + "/api/v1/file" + "/upload";
	}

	public static Response uploadFile(File inputFile, String url) {
		return RestAssured.given()
				.contentType(MULTIPART_FORM_DATA_VALUE)
				.multiPart("file", inputFile)
				.request(Method.POST, url);
	}

}
