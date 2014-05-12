/**
 * 
 */
package com.personal.ninja.connectedsets;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * @author piyush
 *
 */

class Point {
	private int x;
	private int y;
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public String toString() {
		return String.format("(%d, %d)", x, y); 
	}
	
	/**
	 * Check validity against the square matrix's dimension
	 * and has 1 in it
	 * @param dimension
	 * @return
	 */
	public boolean isValid(int dimension) {
		boolean isValid = true;
		
		isValid = isValid & (0 <= x);
		isValid = isValid & (x < dimension);
		isValid = isValid & (0 <= y);
		isValid = isValid & (y < dimension);
		
		return isValid;
	}
	
}

public class Solution {

	public static void print(byte matrix[][])
	{
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + (j == matrix.length - 1 ? "" : " "));
			}
			System.out.println();
		}
	}
	
	public List<Point> getSurroundingPoints(int i, int j, int dimension) {
		List<Point> points = new ArrayList<Point>();
		
		Point point1 = new Point();
		point1.setX(i - 1);
		point1.setY(j);
		if(point1.isValid(dimension))
			points.add(point1);
		
		Point point2 = new Point();
		point2.setX(i);
		point2.setY(j - 1);
		if(point2.isValid(dimension))
			points.add(point2);
		
		Point point3 = new Point();
		point3.setX(i);
		point3.setY(j + 1);
		if(point3.isValid(dimension))
			points.add(point3);
		
		Point point4 = new Point();
		point4.setX(i + 1);
		point4.setY(j);
		if(point4.isValid(dimension))
			points.add(point4);
		
		Point point5 = new Point();
		point5.setX(i - 1);
		point5.setY(j + 1);
		if(point5.isValid(dimension))
			points.add(point5);
		
		Point point6 = new Point();
		point6.setX(i - 1);
		point6.setY(j - 1);
		if(point6.isValid(dimension))
			points.add(point6);
		
		Point point7 = new Point();
		point7.setX(i + 1);
		point7.setY(j + 1);
		if(point7.isValid(dimension))
			points.add(point7);
		
		Point point8 = new Point();
		point8.setX(i + 1);
		point8.setY(j - 1);
		if(point8.isValid(dimension))
			points.add(point8);
		
		return points;
		 
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Solution solution = new Solution();
		
		int matrixCount = 0;
		ArrayList<byte[][]> byteArrays = new ArrayList<byte[][]>();
		
		Scanner sc = null;
		try {
			sc = new Scanner(new BufferedInputStream(System.in));
			matrixCount = Integer.parseInt(sc.nextLine().trim());
			
			int count = 0;
			while (count < matrixCount && sc.hasNextLine()) {
				
				try {
					
					int dimension = Integer.parseInt(sc.nextLine().trim());
					byte[][] matrix = new byte[dimension][dimension];
					for(int i = 0; i < dimension; i++) {
						String data[] = sc.nextLine().trim().split(" ");
						for (int j = 0; j < data.length; j++) {
							matrix[i][j] = Byte.parseByte(data[j]);
						}
						
					}
					
					byteArrays.add(matrix);
					
				} catch (Exception e) {
					System.out.println(String.format("Cannot accept input due to %s", e.getMessage()));
					return;
				}
				
				count++;
			}
			
//			System.out.println("==========");
			for (Iterator<byte[][]> iterator = byteArrays.iterator(); iterator.hasNext();) {
				byte[][] bytes = iterator.next();
//				print(bytes);
//				System.out.println("-----------");
				System.out.println(solution.connectedSetCount(bytes));
			}
//			System.out.println("==========");
			
			
			
			
		} catch (Exception e) {
			System.out.println(String.format("Error due to %s", e.getLocalizedMessage()));
		} finally {
			if(sc != null) {
				sc.close();
			}
		}

	}

	private int connectedSetCount(byte[][] bytes) {
		
		
		int connectedSet = 0;
		
		// Iterate thru each row-column
		for (int i = 0; i < bytes.length; i++) {
			for (int j = 0; j < bytes.length; j++) {

				if(bytes[i][j] == 1) {
					// Increment counter which says a new connected set is found
					connectedSet = connectedSet + 1;
					visitPoints(bytes, i, j);
				}
				
				
			}
		}
		
		return connectedSet;
	}
	
	private void visitPoints(byte[][] bytes, int i, int j) {
		if(bytes[i][j] == 0) {
			return;
		}
		
		bytes[i][j] = 0;
		
		List<Point> points = getSurroundingPoints(i, j, bytes.length);
		for (Point point : points) {
			if(bytes[point.getX()][point.getY()] == 1) {
				visitPoints(bytes, point.getX(), point.getY());
			}
		}
				
		
	}

}
