package com.personal.jaxb;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class JaxbMain {

	public static void main(String[] args) {
		
		try {
			JAXBContext context = JAXBContext.newInstance(JaxbMain.class.getPackage().getName());
			
			Unmarshaller unmarshaller = context.createUnmarshaller();
			Object object = unmarshaller.unmarshal(new FileInputStream("src/main/resources/note.xml"));
			System.out.println(object);
			
		} catch (JAXBException | FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
