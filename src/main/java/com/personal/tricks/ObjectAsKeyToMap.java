/**
 * 
 */
package com.personal.tricks;

import java.util.HashMap;

/**
 * @author piyush
 *
 */
public class ObjectAsKeyToMap {
	
	class KeyWithSameHashcode {
		private String name;
		private int age;
		
		public KeyWithSameHashcode(String name, int age) {
			super();
			this.name = name;
			this.age = age;
		}

		@Override
		public int hashCode() {
			int result = 1;
//			final int prime = 31;
//			result = prime * result + getOuterType().hashCode();
//			result = prime * result + age;
//			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			KeyWithSameHashcode other = (KeyWithSameHashcode) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (age != other.age)
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}

		private ObjectAsKeyToMap getOuterType() {
			return ObjectAsKeyToMap.this;
		}

		@Override
		public String toString() {
			return "KeyWithSameHashcode [name=" + name + ", age=" + age + "]";
		}
		
	}

	class KeyWithSameEquals {
		private String name;
		private int age;
		
		public KeyWithSameEquals(String name, int age) {
			super();
			this.name = name;
			this.age = age;
		}

		@Override
		public int hashCode() {
			int result = 1;
			final int prime = 31;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + age;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}

		private ObjectAsKeyToMap getOuterType() {
			return ObjectAsKeyToMap.this;
		}
		
		@Override
		public boolean equals(Object obj) {
			return true;
		}


		@Override
		public String toString() {
			return "KeyWithSameEquals [name=" + name + ", age=" + age + "]";
		}
		
	}
	
	
	class KeyWithSameHashcodeAndEquals {
		private String name;
		private int age;
		
		public KeyWithSameHashcodeAndEquals(String name, int age) {
			super();
			this.name = name;
			this.age = age;
		}

		@Override
		public int hashCode() {
			int result = 1;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			return true;
		}


		@Override
		public String toString() {
			return "KeyWithSameHashcodeAndEquals [name=" + name + ", age=" + age + "]";
		}
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ObjectAsKeyToMap main1= new ObjectAsKeyToMap();
		
		HashMap<KeyWithSameHashcode, Object> hashMap1 = new HashMap<>();
		System.out.println("For class " + KeyWithSameHashcode.class.getSimpleName());
		for (int i = 0; i < 10; i++) {
			KeyWithSameHashcode key = main1.new KeyWithSameHashcode("name" + i, i * 5);
			int h;
			System.out.println(String.format("Hashcode for key %d. Hash for key: %s", key.hashCode(), (h = key.hashCode()) ^ (h >>> 16)));
			// Puts based on hashcode for finding exact node
			// Links node with another node using next pointer
			// or tree node (rb-tree with pinter reference as parent, left and right child) based on next pointer
			hashMap1.put(key, new Object());
		}
		System.out.println("Map size " + hashMap1.size());
		
		System.out.println("\nFor class " + KeyWithSameEquals.class.getSimpleName());
		HashMap<KeyWithSameEquals, Object> hashMap2 = new HashMap<>();
		for (int i = 0; i < 10; i++) {
			KeyWithSameEquals key = main1.new KeyWithSameEquals("name" + i, i * 5);
			int h;
			System.out.println(String.format("Hashcode for key %d. Hash for key: %s", key.hashCode(), (h = key.hashCode()) ^ (h >>> 16)));
			// Puts based on hashcode for finding exact node
			// Links node with another node using next pointer
			// or tree node (rb-tree with pinter reference as parent, left and right child) based on next pointer
			hashMap2.put(key, new Object());
		}
		System.out.println("Map size " + hashMap2.size());
		
		System.out.println("\nFor class " + KeyWithSameHashcodeAndEquals.class.getSimpleName());
		HashMap<KeyWithSameHashcodeAndEquals, Object> hashMap3 = new HashMap<>();
		for (int i = 0; i < 10; i++) {
			KeyWithSameHashcodeAndEquals key = main1.new KeyWithSameHashcodeAndEquals("name" + i, i * 5);
			int h;
			System.out.println(String.format("Hashcode for key %d. Hash for key: %s", key.hashCode(), (h = key.hashCode()) ^ (h >>> 16)));
			// Puts based on hashcode for finding exact node
			// Links node with another node using next pointer
			// or tree node (rb-tree with pinter reference as parent, left and right child) based on next pointer
			hashMap3.put(key, new Object());
		}
		System.out.println("Map size " + hashMap3.size());
		
		
	}

}
