package orbartal.demo.springboot.files.storage.hadoop;

import java.io.File;
import java.nio.file.Files;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import orbartal.demo.springboot.files.storage.e2e.FileTestUtil;

/**
 * Warning: you must run Apache Hadoop using external tool (for example docker-compose) before running this test.
 * The test assume an Hadoop service exists but does not run one itself.
 *
 */
@TestMethodOrder(OrderAnnotation.class)
public class HadoopApiTest {

	private static final String DOWNLOAD_FILE_NAME = UUID.randomUUID().toString().replaceAll("-", "");
	private static final String FILE_NAME = UUID.randomUUID().toString().replaceAll("-", "");
	private static final String FILE_TEXT = UUID.randomUUID().toString().replaceAll("-", "");
	private static final String HDFS_DIR = "/user/root/output/";
	private static final String HDFS_FILE_PATH = HDFS_DIR + FILE_NAME + ".txt";

	private static Optional<String> urlToUploadFile = Optional.empty();
	private static Optional<File> fileToUpload = Optional.empty();
	private static Optional<String> urlToDownloadFile = Optional.empty();
	private static Optional<File> fileToDownload = Optional.empty();

	@Order(1)
	@Test
	public void testGetUploadLinkFromHadoopNameNode() throws Exception {
		String url = HadoopTestUtil.buildCreateFileUrl("namenode", 9870, HDFS_FILE_PATH);
		Response response = RestAssured.given().when().put(url).andReturn();

		Assertions.assertNotNull(response);
		Assertions.assertEquals(HttpStatus.TEMPORARY_REDIRECT.value(), response.statusCode());
		Assertions.assertNotNull(response.getBody());
		Assertions.assertNotNull(response.getHeaders());
		Assertions.assertNotNull(response.getHeader("Location"));

		String location = response.getHeader("Location");
		urlToUploadFile = Optional.of(location);
	}

	@Order(2)
	@Test
	public void testCreateLocalFile() throws Exception {
		File file = FileTestUtil.createTempFile(FILE_NAME);
		FileTestUtil.writeTextIntoFile(file, FILE_TEXT);
		fileToUpload = Optional.of(file);
	}

	@Order(3)
	@Test
	public void testUploadFile() throws Exception {
		Assertions.assertTrue(urlToUploadFile.isPresent());
		Assertions.assertTrue(fileToUpload.isPresent());
		File input = fileToUpload.get();
		String url = urlToUploadFile.get();
		Assertions.assertTrue(input.exists());
		Assertions.assertTrue(input.canRead());
		Assertions.assertFalse(input.isDirectory());

		Response response = HadoopTestUtil.uploadFilePut(input, url);

		Assertions.assertNotNull(response);
		Assertions.assertEquals(HttpStatus.CREATED.value(), response.statusCode());
	}

	@Order(4)
	@Test
	public void testGetDownloadLinkFromNameNode() throws Exception {
		String url = HadoopTestUtil.buildReadFileUrl("namenode", 9870, HDFS_FILE_PATH);
		Response response = RestAssured.given().redirects().follow(false).head(url).andReturn();

		Assertions.assertNotNull(response);
		Assertions.assertEquals(HttpStatus.TEMPORARY_REDIRECT.value(), response.statusCode());
		Assertions.assertNotNull(response.getBody());
		Assertions.assertNotNull(response.getHeaders());
		Assertions.assertNotNull(response.getHeader("Location"));

		String location = response.getHeader("Location");
		urlToDownloadFile = Optional.of(location);
	}

	@Order(5)
	@Test
	public void testCreateDownloadlFile() throws Exception {
		File file = FileTestUtil.createTempFile(DOWNLOAD_FILE_NAME);
		fileToDownload = Optional.of(file);
	}

	@Order(6)
	@Test
	public void testDownloadFile() throws Exception {
		Assertions.assertTrue(urlToDownloadFile.isPresent());
		Assertions.assertTrue(fileToDownload.isPresent());

		String url = urlToDownloadFile.get();
		File output = fileToDownload.get();
		File dir = output.getParentFile();

		Assertions.assertTrue(output.exists());
		Assertions.assertTrue(dir.canWrite());
		Assertions.assertTrue(dir.isDirectory());

		Response response = HadoopTestUtil.downloadFile(url);

		Assertions.assertNotNull(response);
		Assertions.assertEquals(HttpStatus.OK.value(), response.statusCode());
		Assertions.assertNotNull(response.getBody());
		byte[] content = response.getBody().asByteArray();
		Files.write(output.toPath(), content);
	}

	@Order(7)
	@Test
	public void testCompareUploadToDownload() throws Exception {
		Assertions.assertTrue(fileToUpload.isPresent());
		Assertions.assertTrue(fileToDownload.isPresent());

		Assertions.assertTrue(fileToUpload.get().exists());
		Assertions.assertTrue(fileToDownload.get().exists());

		String upload = Files.readString(fileToUpload.get().toPath());
		String download = Files.readString(fileToDownload.get().toPath());
		
		Assertions.assertEquals(FILE_TEXT, upload);
		Assertions.assertEquals(FILE_TEXT, download);
	}

}