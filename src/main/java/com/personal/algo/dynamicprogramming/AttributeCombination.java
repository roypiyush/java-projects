/**
 * 
 */
package com.personal.algo.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * @author piyush
 *
 */
public class AttributeCombination {

	public void printCombinations(HashMap<String, List<String>> map, String[] attributes) {

		String[] combinedOutput = getCombinedOutput(map, attributes, 0).split("\n");
		for (int i = 0; i < combinedOutput.length; i++) {
			System.out.println(combinedOutput[i]);
		}		

	}


	public String getCombinedOutput(HashMap<String, List<String>> map, String[] attributes, int attributeIndex) {

		String output = "";
		
		List<String> list = map.get(attributes[attributeIndex]);

		if(attributeIndex == attributes.length - 1) {
	
			for(int k = 0; k < list.size(); k++)
			{
				output = output + list.get(k) + "\n";
			}

			return output;
		}

		String[] values = getCombinedOutput(map, attributes, attributeIndex + 1).split("\n");
		for(int i = 0; i < list.size(); i++) {
			for (int j = 0; j < values.length; j++) {
				output = output + list.get(i) + " " + values[j] + "\n";
			}
		}

		return output;
		

	}
	
	
	public static void printAttributes(HashMap<String, List<String>> map, String[] attributes) {
		
		List<String> output = getCombinations(map, attributes, 0);
		for (int i = 0; i < output.size(); i++) {
			
			System.out.println(output.get(i));
		}
		
	}
	
	private static List<String> getCombinations(HashMap<String, List<String>> map,
			String[] attributes, int index) {
		
		
		List<String> output = new ArrayList<String>();
		
		if(index == attributes.length - 1) {

			return map.get(attributes[index]);
		}
		
		List<String> combinations = getCombinations(map, attributes, index + 1);
		
		for (int j = 0; j < combinations.size(); j++) {
			for (Iterator<String> iterator = map.get(attributes[index]).iterator(); iterator.hasNext();) {
				output.add(combinations.get(j) + " " + iterator.next());
			}
		}
		
		return output;
	}

	public static void main(String[] args) {
		
		String color = "red blue green violet";
		String size = "X ML";
		String brand = "Spykar Levis Lee";
		String[] attributes = "color size brand".split(" ");
		
		HashMap<String, List<String>> map = new HashMap<String, List<String>>();
		map.put("color", Arrays.asList(color.split(" ")));
		map.put("size", Arrays.asList(size.split(" ")));
		map.put("brand", Arrays.asList(brand.split(" ")));

//		new AttributeCombination().printCombinations(map, attributes);
		printAttributes(map, attributes);
		

	}

}
