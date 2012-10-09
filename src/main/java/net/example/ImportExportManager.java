package net.example;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import net.example.data.Customer;

public class ImportExportManager {
	
	public void marshal(Customer customer, String filename) throws JAXBException {
		File file = new File(filename);
		
		//create a new Context to point to you class that is the root of the aggregation (denoted with @XmlRootElement)
		JAXBContext context = JAXBContext.newInstance(Customer.class);
		Marshaller marshaller = context.createMarshaller();
		
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); //format the output using white spaces
		marshaller.marshal(customer, file);
	}
	
	public Customer unmarshal(String filename) throws JAXBException {
		File file = new File(filename);
		
		JAXBContext context = JAXBContext.newInstance(Customer.class);
		 Unmarshaller unmarshaller = context.createUnmarshaller();
		 
		 Customer customer = (Customer)unmarshaller.unmarshal(file);
		 
		 return customer;
	}

}
