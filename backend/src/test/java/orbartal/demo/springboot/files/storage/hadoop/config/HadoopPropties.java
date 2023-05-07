package orbartal.demo.springboot.files.storage.hadoop.config;

public class HadoopPropties {

	private final String nodeNameUrl;
	private final String hdfsDir;

	public HadoopPropties(String nodeNameUrl, String hdfsDir) {
		this.nodeNameUrl = nodeNameUrl;
		this.hdfsDir = hdfsDir;
	}

	public String getNodeNameUrl() {
		return nodeNameUrl;
	}

	public String getHdfsDir() {
		return hdfsDir;
	}

	@Override
	public String toString() {
		return "HadoopPropties [nodeNameUrl=" + nodeNameUrl + ", hdfsDir=" + hdfsDir + "]";
	}

}
