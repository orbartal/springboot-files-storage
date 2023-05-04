package orbartal.demo.springboot.files.storage.e2e;

import java.io.File;
import java.net.HttpURLConnection;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.restassured.response.Response;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OneFileApiTest {

	private static final String FILE_TEXT = "hello world";

	@LocalServerPort
	private int port;

	@Test
	public void test01UploadFile() throws Exception {
		File inputFile = FileTestUtil.createTempFile("test01UploadFile");
		FileTestUtil.writeTextIntoFile(inputFile, FILE_TEXT);
		String url = FileTestUtil.buildUploadUrl(port);
		Response response = FileTestUtil.uploadFile(inputFile, url);

		Assertions.assertNotNull(response);
		Assertions.assertEquals(HttpURLConnection.HTTP_OK, response.statusCode());
		Assertions.assertNotNull(response.getBody());

		@SuppressWarnings("rawtypes")
		Map responseMap = response.getBody().as(Map.class);
		Assertions.assertNotNull(responseMap.get("uid"));
	}



}