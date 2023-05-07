package orbartal.demo.springboot.files.storage.hadoop.config;

public class HadoopProptiesProvider {

	private final static HadoopPropties propties = new HadoopPropties("http://namenode:9870", "/user/root/output/");

	public static HadoopPropties getPropties() {
		return propties;
	}

}
