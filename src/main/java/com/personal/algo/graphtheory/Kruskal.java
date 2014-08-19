package com.personal.algo.graphtheory;

public class Kruskal {

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
		
		for (int i = 0; i < adjMatrix.length; i++) {
			Vertex vertex = new Vertex();
			vertex.setId(i);
			graph.addVertex(vertex);
		}

	}

}
