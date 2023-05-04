package orbartal.demo.springboot.files.storage.e2e;

import java.net.HttpURLConnection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.restassured.RestAssured;
import io.restassured.response.Response;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloApiTest {

	@LocalServerPort
	private int port;

	@Test
	public void testGetHello() throws Exception {
		String url = "http://localhost:" + port + "/api/v1/hello";
		Response response = RestAssured.given().when().get(url).andReturn();

		Assertions.assertNotNull(response);
		Assertions.assertEquals(HttpURLConnection.HTTP_OK, response.statusCode());
		Assertions.assertNotNull(response.getBody());

		byte[] responseBytes = response.getBody().asByteArray();
		String actual = new String(responseBytes);
		Assertions.assertEquals("hello", actual);
	}

}