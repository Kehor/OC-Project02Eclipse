package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


public class AnalyticsCounter {
	private static int headacheCount = 0;	// initialize to 0
	private static int rashCount = 0;		// initialize to 0
	private static int pupilCount = 0;		// initialize to 0
	
	public static void main(String args[]) throws Exception {
		// first get input
		BufferedReader reader = new BufferedReader (new FileReader("symptoms.txt"));
		String line = reader.readLine();

		Map<String, Integer> unsortMap = new HashMap<String, Integer>();
		while (line != null) {
			if(unsortMap.get('"'+line+'"') != null){
				unsortMap.put( '"'+line+'"' , ( unsortMap.get('"'+line+'"') + 1) );
			}else unsortMap.put( '"'+line+'"' , 1);
			line = reader.readLine();
		}
		Map<String, Integer> symptomsMap = new TreeMap<String, Integer>(unsortMap);
		printMap(symptomsMap);

		// next generate output
		FileWriter writer = new FileWriter ("result.out");
		symptomsMap.forEach((k, v) -> {
			try {
				writer.write(k +": " + v + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.format("key: %s, value: %d%n", k, v);
		});
		writer.write("headache: " + headacheCount + "\n");
		writer.close();
	}
	public static <K, V> void printMap(Map<K, V> map) {
		for (Map.Entry<K, V> entry : map.entrySet()) {
			System.out.println("Key : " + entry.getKey()
					+ " Value : " + entry.getValue());
		}
	}
}
