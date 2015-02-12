package com.personal.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationMain {

	public static void main(String[] args) {
		
		SerializableSubclass bean = new SerializableSubclass();
		bean.setName("Piyush");
		bean.setValue("Priceless");
		
		// Making some value set in runtime which will be
		// gone when de-serialized
		bean.setInternalField("Test");
		
		SerializableSubclass beanDeserialized = null;
		
		// Serializing the class
		try
	      {
	         FileOutputStream fileOut =
	         new FileOutputStream("target/bean.ser");
	         
	         System.out.printf("Serializing object: %s with Super Internal Field: %s\n", bean, bean.getInternalField());
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(bean);
	         out.close();
	         fileOut.close();

		
	         // De-serializing the class
	         FileInputStream fileIn =
	         new FileInputStream("target/bean.ser");
	         ObjectInputStream oin = new ObjectInputStream(fileIn);
	         beanDeserialized = (SerializableSubclass) oin.readObject();
	         System.out.printf("De-Serializing object: %s with Super Internal Field: %s\n", beanDeserialized, beanDeserialized.getInternalField());
	         oin.close();
	         fileIn.close();
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println(String.format("HashCode of initial object: %s, HashCode of deserialized object: %s", bean.hashCode(), beanDeserialized.hashCode()));
		System.out.println(beanDeserialized.equals(bean) ? "Objects are equal" : "Objects are not equal");

	}

}
