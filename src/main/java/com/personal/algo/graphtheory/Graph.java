package com.personal.algo.graphtheory;

import java.util.LinkedList;
import java.util.List;

public class Graph {

	private List<Vertex> V;

	public List<Vertex> getV() {
		return V;
	}

	public void setV(List<Vertex> v) {
		V = v;
	}
	
	public void addVertex(Vertex vertex) {
		if(V == null) 
			V = new LinkedList<Vertex>();
		V.add(vertex);
	}
}
