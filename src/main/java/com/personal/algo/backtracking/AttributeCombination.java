package com.personal.algo.backtracking;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class AttributeCombination {

	public void printCombinations(HashMap<String, List<String>> map,
			String[] attributes) {

		int attributeIndex = 0;

		formCombinationString(map, attributes, attributeIndex, "");

	}

	private void formCombinationString(HashMap<String, List<String>> map,
			String[] attributes, int attributeIndex, String combination) {

		if (attributeIndex >= attributes.length) {
			System.out.println(combination.trim());
			return;
		}

		// Size of values of particular attributeIndex
		int size = map.get(attributes[attributeIndex]).size();
		for (int i = 0; i < size; i++) {
			String str = combination + " "
					+ map.get(attributes[attributeIndex]).get(i);
			formCombinationString(map, attributes, attributeIndex + 1, str);
		}

	}

	public static void main(String[] args) {

		String color = "red blue green violet";
		String size = "X ML";
		String brand = "Spykar Levis Lee";
		String[] attributes = "brand color size".split(" ");

		HashMap<String, List<String>> map = new HashMap<String, List<String>>();
		map.put("color", Arrays.asList(color.split(" ")));
		map.put("size", Arrays.asList(size.split(" ")));
		map.put("brand", Arrays.asList(brand.split(" ")));

		long startTime = System.currentTimeMillis();
		new AttributeCombination().printCombinations(map, attributes);
		long endTime = System.currentTimeMillis();
		System.out.println(String.format("Time required: %s",
				(endTime - startTime)));

	}

}
