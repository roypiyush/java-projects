package com.personal.old.ninja.shortestsubsegment;

import java.io.BufferedInputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Index {
	private int startIndex;
	private int endIndex;
	
	public Index(int startIndex, int endIndex) {
		super();
		this.startIndex = startIndex;
		this.endIndex = endIndex;
	}
	
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
	
	public void incrementEnd() {
		endIndex = endIndex + 1;
	}
	
	public int getDistance() {
		if(endIndex > startIndex)
			return endIndex - startIndex;
		else
			return Integer.MAX_VALUE;
	}
	public String toString() {
		return String.format("(%d, %d)", startIndex, endIndex);
	}
}


public class Solution {
	
	public Index minimumSegment(String paragraph[], Map<String, Integer> map, int starting) {
		
		Map<String, Integer> nextMap = new HashMap<String, Integer>(map);
		
		int start = starting;
		int end = starting;
		
		int unique = 0;
		while(unique < map.size() && end < paragraph.length) {
			
			String word = paragraph[end].toLowerCase();
			Integer count = map.get(word);
			
			if(count == null) {
				// Doesn't exist
			}
			else {
				
				map.put(word, count + 1);
				
				if(count == 0) {
					unique = unique + 1;
				}
				
			}
			end++;
		}
		
		if(unique < map.size()) {
			return null;
		}
		
		boolean flag = true;
		while(flag) {

			String word = paragraph[start].toLowerCase();
			Integer count = map.get(word);
			
			if(count == null) {
				// Doesn't exist
			}
			else {
				if(count > 1)
					map.put(word, count - 1);
				if(count == 1)
					flag = false;
				
			}
			start++;
		}
		
		Index index = new Index(start - 1, end);
		Index next = minimumSegment(paragraph, nextMap, start);
		
		return findMinimumIndex(index, next);
	}

	private Index findMinimumIndex(Index index, Index index1) {
		if (index1 != null && index != null) { 
			
			if(index1.getDistance() < index.getDistance()) {
				return index1;
			} 
			else if(index1.getDistance() > index.getDistance()) {
				return index;
			}
			else {
				if(index.getEndIndex() < index1.getEndIndex()) {
					return index;
				}
				else {
					return index1;
				}
			}
		}
		else if (index1 != null && index == null) {
			return index1;
		} 
		else if (index1 == null && index != null) {
			return index;
		} 
		else {
			return null;
		}
	}


	public static void main(String[] args) {
		
		Solution solution = new Solution();
		
		LinkedList<String> paragraph = new LinkedList<String>();

		Map<String, Integer> wordCollection = new HashMap<String, Integer>();
		
		Scanner sc = null;
		try {
			sc = new Scanner(new BufferedInputStream(System.in));
			

			String inputParagraph = sc.nextLine().trim();
				
			Pattern compile = Pattern.compile("[a-zA-Z]+");
			Matcher matcher = compile.matcher(inputParagraph);
				
			while (matcher.find()) {
			      
			      String t = matcher.group();
			      paragraph.add(t);
			}

			
			int wordCount = Integer.valueOf(sc.nextLine().trim());
			
			int count = 0;
			while (count < wordCount) {
				try {
					
					String singleWord = sc.nextLine().trim(); 
					wordCollection.put(singleWord, 0);

				} catch (Exception e) {
					System.out.println(String.format("Cannot accept input due to %s", e.getMessage()));
					return;
				}
				
				count++;
			}
			
			
						
		} catch (Exception e) {
			System.out.println(String.format("Error due to %s", e.getMessage()));
		} finally {
			if(sc != null) {
				sc.close();
			}
		}

		
		Index[] table = new Index[paragraph.size()];
		for (int i = 0; i < table.length; i++) {
			table[i] = null;
		}
		
		Index minSegment = solution.minimumSegment(paragraph.toArray(new String[paragraph.size()]), wordCollection, 0);

		if(minSegment == null) {
			System.out.println("NO SUBSEGMENT FOUND");
			return;
		}

		for(int i = minSegment.getStartIndex(); i < minSegment.getEndIndex(); i++) {
			
			System.out.print(paragraph.get(i) + ( i == minSegment.getEndIndex() ? "" : " "));
			
		} 
		
		
	}

	

}