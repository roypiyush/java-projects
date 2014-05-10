package com.personal.ninja.shortestsubsegment;
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
		if(endIndex >= startIndex)
			return endIndex - startIndex + 1;
		else
			return Integer.MAX_VALUE;
	}
}


public class Solution {
	

	public void minSegment(String paragraph[], Map<String, Boolean> wordMap) {
		
		int minLength = Integer.MAX_VALUE;
		Index minSegment = null;
		
		for (int i = 0; i <= paragraph.length - wordMap.size(); i++) {
			
			Index segment = segment(paragraph, new HashMap<String, Boolean>(wordMap), i);
			
			if(segment == null && minSegment == null) {
				System.out.println("NO SUBSEGMENT FOUND");
				return;
			}
			
			if (segment != null) {
				if (segment.getDistance() == wordMap.size()) {
					// this would be minimum length that can be obtained
					minSegment = segment;
					minLength = segment.getDistance();
					break;
				}
				if (segment.getDistance() < minLength) {
					minSegment = segment;
					minLength = segment.getDistance();
				}
				
			}
		}
		
		for (int i = minSegment.getStartIndex(); i <= minSegment.getEndIndex(); i++) {
			System.out.print(paragraph[i] + (i == (paragraph.length - 1) ? "" : " "));
		}
		
	}
	
	public Index segment(String paragraph[], Map<String, Boolean> wordMap, int start) {
		
		Index index = new Index();
		index.setStartIndex(start);
		index.setEndIndex(start);
		
		
		int matched = 0;
		
		while(index.getEndIndex() < paragraph.length) {
			
			
			Boolean isMatched = wordMap.get(paragraph[index.getEndIndex()].toLowerCase());
			if(isMatched != null && !isMatched) {
				matched = matched + 1;
				wordMap.put(paragraph[index.getEndIndex()].toLowerCase(), !isMatched);
			}
			
			if(matched == wordMap.size()) {
				// All of words got matched
				return index;
			}
			
			index.incrementEnd();
			
		} 
		
		if(matched < wordMap.size()) {
			return null;
		}
		
		
		
		return null;
	}



	public static void main(String[] args) {
		
		Solution solution = new Solution();
		
		LinkedList<String> paragraph = new LinkedList<String>();

		Map<String, Boolean> wordMap = new HashMap<String, Boolean>();
		
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
					wordMap.put(singleWord, new Boolean(false));

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
		
		// long startTime = System.currentTimeMillis();
		solution.minSegment(paragraph.toArray(new String[paragraph.size()]), wordMap);
		// long endTime = System.currentTimeMillis();
		
		// System.out.println(endTime - startTime);
		
		
	}

}