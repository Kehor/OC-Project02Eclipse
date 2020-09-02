package com.hemebiotech.analytics;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Anything that will read symptom data from a source
 * The important part is, the return value from the operation, which is a Map of Strings and Integer,
 * that's not contained any duplications
 *
 * The implementation can order the Map
 */
public class ReadSymptomDataFromFile {

	private static String filepath;

	/**
	 * @param filepath a full path to file with symptom strings in it, one per line
	 */
	public ReadSymptomDataFromFile (String filepath) throws IOException {
		String appConfigPath = "app.properties";

		Properties appProps = new Properties();
		appProps.load(new FileInputStream(appConfigPath));

		this.filepath = appProps.getProperty(filepath);
	}

	public static Map<String, Integer> GetSymptoms() throws IOException {
		new ReadSymptomDataFromFile("inputPath");
		Map<String, Integer> result = new HashMap<String, Integer>();

		if (filepath != null) {
			try {
				BufferedReader reader = new BufferedReader (new FileReader(filepath));
				String line = reader.readLine();

				while (line != null) {
					if(result.get( line ) != null){	//	For each line in file verify if the line already exists in Map
						result.put( line , ( result.get(line) + 1) );	//	If yes, replace the line by itself with an increment value
					}else result.put( line , 1);	//	else, add the line to Map and set value to 1
					line = reader.readLine();	//	go to the next line
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return result;	//	returns a map of key and value pair without duplicates
	}

}
