package com.personal.algo.graphtheory;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;


class SortVertex implements Comparator<Vertex> {
	

	public int compare(Vertex v1, Vertex v2) {
		return v2.getEndTime() - v1.getEndTime();
	}
}

public class StronglyConnectedComponent {

	private int time = 0;
	
	private void depthFirstSearch(Graph graph, String[] adjMatrix, boolean isTranspose) {
				
		List<Vertex> vertexs = graph.getV();
		
		for (Iterator<Vertex> iterator = vertexs.iterator(); iterator.hasNext();) {
			Vertex vertex = (Vertex) iterator.next();
			if(vertex.getColor() == Color.WHITE) {
				depthFirstSearchVisit(graph, vertex, adjMatrix, isTranspose);
				
				if(isTranspose)
				System.out.println();
			}
		}
		
	}
	
	private void depthFirstSearchVisit(Graph graph, Vertex vertex, String[] adjMatrix, boolean isTranspose) {
		
		time = time + 1;
		vertex.setStartTime(time);
		vertex.setColor(Color.BLACK);
		
		if(isTranspose)
		System.out.print(vertex.getId() + " ");
		
		String adjList = adjMatrix[vertex.getId()];
		
		for (int i = 0; i < adjList.length(); i++) {
			// Check color
			if(adjList.charAt(i) == '1') {
				Iterator<Vertex> iterator = graph.getV().iterator();
				while (iterator.hasNext()) {
					Vertex temp = iterator.next();
					if(temp.getId() == i && temp.getColor() == Color.WHITE) {
						depthFirstSearchVisit(graph, temp, adjMatrix, isTranspose);
					}
					
				}
				
			}
		}
		time = time + 1;
		vertex.setEndTime(time);
	}

	
	private void transpose(String[] adjMatrix) {
		
		for (int i = 0; i < adjMatrix.length; i++) {
			for (int j = i; j < adjMatrix.length; j++) {
				char c = adjMatrix[i].charAt(j);
				char r = adjMatrix[j].charAt(i);
				
				StringBuffer buffer = new StringBuffer(adjMatrix[i]);
				buffer.deleteCharAt(j);
				buffer.insert(j, r);
				adjMatrix[i] = buffer.toString();
				
				buffer = new StringBuffer(adjMatrix[j]);
				buffer.deleteCharAt(i);
				buffer.insert(i, c);
				adjMatrix[j] = buffer.toString();
			}
		}
		
	}
	
	public static void main(String[] args) {
		String[] adjMatrix = {
				"00110",
				"10000",
				"01000",
				"00001",
				"00000"};
		
		StronglyConnectedComponent scc = new StronglyConnectedComponent();
		
		Graph graph = new Graph();
		
		for (int i = 0; i < adjMatrix.length; i++) {
			Vertex vertex = new Vertex();
			vertex.setId(i);
			vertex.setColor(Color.WHITE);
			graph.addVertex(vertex);
		}
		
		scc.depthFirstSearch(graph, adjMatrix, false);
		
//		for (int i = 0; i < adjMatrix.length; i++) {
//			System.out.println(adjMatrix[i]);
//		}
		scc.transpose(adjMatrix);
//		System.out.println("---------------");
		for (int i = 0; i < adjMatrix.length; i++) {
//			System.out.println(adjMatrix[i]);
			graph.getV().get(i).setColor(Color.WHITE);
		}
		
		Collections.sort(graph.getV(), new SortVertex());
		scc.depthFirstSearch(graph, adjMatrix, true);
	}
}
