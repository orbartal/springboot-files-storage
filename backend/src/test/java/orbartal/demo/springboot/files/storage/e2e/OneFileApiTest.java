package orbartal.demo.springboot.files.storage.e2e;

import java.io.File;
import java.net.HttpURLConnection;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.restassured.response.Response;

@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OneFileApiTest {

	private static final String FILE_TEXT = "hello world";

	private static Optional<String> fileUid = Optional.empty();

	@LocalServerPort
	private int port;

	@Order(1)
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
		fileUid = Optional.of(responseMap.get("uid").toString());
	}

	@Order(2)
	@Test
	public void test02DownloadFile() throws Exception {
		Assertions.assertTrue(fileUid.isPresent());
		String url = FileTestUtil.buildDownloadUrl(port, fileUid.get());
		Response response = FileTestUtil.downloadFile(url);

		Assertions.assertNotNull(response);
		Assertions.assertEquals(HttpURLConnection.HTTP_OK, response.statusCode());
		Assertions.assertNotNull(response.getBody());

		byte[] downloadFileBytes = response.getBody().asByteArray();
		String actual = new String(downloadFileBytes);

		Assertions.assertEquals(FILE_TEXT, actual);
	}

}