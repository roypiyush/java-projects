package com.personal.algo.graphtheory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class VertexComparator implements Comparator<Vertex> {

	@Override
	public int compare(Vertex o1, Vertex o2) {
		return o1.getKey() - o2.getKey();
	}
	
}

public class Prims {

	public static void main(String[] args) {
		String[] adjMatrix = {
				"01000000",
				"00101100",
				"00010010",
				"00100001",
				"10000100",
				"00000010",
				"00000101",
				"00000001"};
		
		Graph graph = new Graph();
		List<Vertex> vertexs = new ArrayList<>();
		
		PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>(adjMatrix.length, new VertexComparator());
		
		for (int i = 0; i < adjMatrix.length; i++) {
			Vertex vertex = new Vertex();
			vertex.setId(i);
			if(i == 0)
				vertex.setKey(0);
			else
				vertex.setKey(Integer.MAX_VALUE);
			vertexs.set(vertex.getId(), vertex);
			priorityQueue.add(vertex);
		}
		graph.setV(vertexs);
		
//		Vertex source = priorityQueue.peek();
		
		while(!priorityQueue.isEmpty()) {
			Vertex u = priorityQueue.poll();
			for (int i = 0; i < adjMatrix[u.getId()].length(); i++) {
				// Considering Edges
				if(Integer.valueOf(adjMatrix[u.getId()].charAt(i)) > 0) {
					Vertex v = graph.getV().get(i);
					if(priorityQueue.contains(graph.getV().get(u.getId())) && 
							Integer.valueOf(adjMatrix[u.getId()].charAt(i)) < v.getKey() ) {
						v.setParent(u.getId());
						v.setKey(Integer.valueOf(adjMatrix[u.getId()].charAt(i)));
					}
				}
			}
		}
		
		// Print Graph
		
	}

}
