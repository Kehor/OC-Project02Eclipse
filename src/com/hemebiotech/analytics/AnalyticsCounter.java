package com.hemebiotech.analytics;

import java.util.Map;
import java.util.TreeMap;


public class AnalyticsCounter {
	
	public static void main(String args[]) throws Exception {

		Map<String, Integer> symptomsMap = new TreeMap<String, Integer>(ReadSymptomDataFromFile.GetSymptoms()); //  Sort the keys of the Map in alphabetical order
		SymptomWriter.WriteSymptoms(symptomsMap);

	}
}
