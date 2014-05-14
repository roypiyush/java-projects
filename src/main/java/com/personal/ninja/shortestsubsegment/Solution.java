package com.personal.ninja.shortestsubsegment;
import java.io.BufferedInputStream;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;
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
			return endIndex - startIndex + 1;
		else
			return Integer.MAX_VALUE;
	}
	public String toString() {
		return String.format("(%d, %d)", startIndex, endIndex);
	}
}


public class Solution {
	
	public Index minimumSegment(String paragraph[], Set<String> wordMap, int end, Index[] table) {

		if(end >= paragraph.length) {
			return null;
		}

		if(table[end] != null) {
			return table[end];
		}
		
		int i = checkWordExistence(paragraph, wordMap , end);
		Index index1 = null;
		Index index = null;
		
		if (i == -1) {
			// then increase the index and then search
			index1 = minimumSegment(paragraph, wordMap, end + 1, table);
		} 
		else {
			// found
			index = new Index(i, end);
			
			
			if (end + 1 < paragraph.length)
				index1 = minimumSegment(paragraph, wordMap, end + 1, table);

		}

		table[end] = findMinimumIndex(index, index1);
		return table[end];
		
		
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
	
	/**
	 * 
	 * @param paragraph
	 * @param wordMap
	 * @param start
	 * @param end
	 * 
	 * @return the start index
	 * or else -1 NO SUBSEGMENT FOUND
	 */
	public int checkWordExistence(String paragraph[], Set<String> remainingWords, int end) {
		
		if(end >= paragraph.length) {
			return -1;
		}
		
		// Either Segment can exist or does not exist
		Set<String> rw = new HashSet<String>(remainingWords);
		
		for(int i = end; i >= 0; i--) {
			
			String word = paragraph[i].toLowerCase();
			rw.remove(word);
		
			if(rw.size() == 0) {
				// All of words got matched
				return i;
			}
			
		} 
		
		return -1;
	}



	public static void main(String[] args) {
		
		Solution solution = new Solution();
		
		LinkedList<String> paragraph = new LinkedList<String>();

		Set<String> wordSet = new HashSet<String>();
		
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
					wordSet.add(singleWord);

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
		
		Index minSegment = solution.minimumSegment(paragraph.toArray(new String[paragraph.size()]), wordSet, wordSet.size() - 1, table);

		if(minSegment == null) {
			System.out.println("NO SUBSEGMENT FOUND");
			return;
		}

		for(int i = minSegment.getStartIndex(); i <= minSegment.getEndIndex(); i++) {
			
			System.out.print(paragraph.get(i) + ( i == minSegment.getEndIndex() ? "" : " "));
			
		} 
		
		
	}

	

}