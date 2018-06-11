package com.personal.old.tricks;

import java.util.Iterator;

class HolderCollection<E> implements Iterable<Object> {
	
	private Iterator<Object> iterator = new Iterator<Object>() {
		private int counter = 0;

		@Override
		public boolean hasNext() {
			System.out.println("hasNext called");
			return (counter < 10) ? true : false;
		}

		@Override
		public Object next() {
			counter++;
			System.out.print("inside next() " + counter + " ");
			return getObject();
		}
	};
	
	private Object getObject() {
		return new Object();
	}
	
	public Iterator<Object> iterator() {
		return iterator;
	}
}

public class SimpleIteratorMain {
	
	public static void main(String[] args) {
		HolderCollection<Object> holder = new HolderCollection<Object>();
		for (Object o : holder) {
			System.out.println(o.hashCode());
		}
	}

}

