package orbartal.demo.springboot.files.storage.hadoop.config;

import orbartal.demo.springboot.files.storage.hadoop.HadoopPropties;

public class HadoopProptiesProvider {

	private final static HadoopPropties propties = new HadoopPropties("http://namenode:9870", "/user/root/output/");

	public static HadoopPropties getPropties() {
		return propties;
	}

}
