package com.personal.algo.graphtheory;

enum Color {
	WHITE, BLACK;
}

public class Vertex {
	
	private int id;
	private Color color = Color.WHITE;
	private int startTime;
	private int endTime;
	private int key;
	private int parent; // another id
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public int getStartTime() {
		return startTime;
	}
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	public int getEndTime() {
		return endTime;
	}
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	@Override
	public boolean equals(Object vertex) {
		if(vertex instanceof Vertex) {
			return this.getId() == ((Vertex) vertex).getId();
		}
		else {
			return false;
		}
	}
	@Override
	public String toString() {
		return "Vertex [id=" + id + ", color=" + color + ", startTime="
				+ startTime + ", endTime=" + endTime + ", key=" + key + "]";
	}
	
}