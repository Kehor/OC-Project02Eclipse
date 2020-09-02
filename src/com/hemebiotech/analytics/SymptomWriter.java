package com.hemebiotech.analytics;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * Write a Map of Strings and Integer in an output file,
 *
 * return nothing
 */
public class SymptomWriter {

	private static String filepath;

	/**
	 * @param filepath A full path to the output file with symptoms and their count in it, one per line
	 */
	public SymptomWriter(String filepath) throws IOException {
		String appConfigPath = "app.properties";

		Properties appProps = new Properties();
		appProps.load(new FileInputStream(appConfigPath));

		this.filepath = appProps.getProperty(filepath);
	}

	static void WriteSymptoms(Map<String, Integer> symptomsMap) throws IOException {
		new SymptomWriter("outputPath");
		// generate output
		FileWriter writer = new FileWriter (filepath);
		symptomsMap.forEach((k, v) -> {
			try {
				writer.write(k +": " + v + "\n");	//	try to write down each symptom and its value
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.format("key: %s, value: %d%n", k, v);
		});
		writer.close();
		return;
	}
}
