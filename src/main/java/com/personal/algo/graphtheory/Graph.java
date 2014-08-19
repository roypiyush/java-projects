package com.personal.algo.graphtheory;

import java.util.LinkedList;
import java.util.List;


public class Graph {

	private List<Vertex> V;
	private List<Edge> E;

	public List<Vertex> getV() {
		return V;
	}

	public void setV(List<Vertex> v) {
		V = v;
	}
	
	public void addVertex(Vertex vertex) {
		if(V == null) 
			V = new LinkedList<>();
		V.add(vertex);
	}

	public List<Edge> getE() {
		return E;
	}

	public void setE(List<Edge> e) {
		E = e;
	}
	
	public void addEdge(Edge edge) {
		if(E == null) {
			E = new LinkedList<Edge>();
			V = new LinkedList<Vertex>();
		}
		V.add(edge.getV1());
		V.add(edge.getV2());
		E.add(edge);
	}
}
