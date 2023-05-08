package orbartal.demo.springboot.files.storage.hadoop;

public class HadoopWebApiUrlFactory {
	
	public static String buildCreateFileUrl(String nodeNameUrl, String hdfsPath) {
		StringBuilder sb = new StringBuilder();
		sb.append(nodeNameUrl);
		sb.append("/webhdfs/v1");
		sb.append(hdfsPath);
		sb.append("?op=CREATE");
		String url = sb.toString();
		return url;
	}

	public static String buildReadFileUrl(String nodeNameUrl, String hdfsPath) {
		StringBuilder sb = new StringBuilder();
		sb.append(nodeNameUrl);
		sb.append("/webhdfs/v1");
		sb.append(hdfsPath);
		sb.append("?op=OPEN");
		return sb.toString();
	}

}
