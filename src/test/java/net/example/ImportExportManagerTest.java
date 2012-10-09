package net.example;

import static org.junit.Assert.*;

import java.io.File;

import javax.xml.bind.JAXBException;

import net.example.data.Address;
import net.example.data.Customer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ImportExportManagerTest {
	
	private Customer customer;
	private String filename;
	
	@Before
	public void setUp() {
		customer = new Customer();
		
		customer.setLastname("Lastname");
		customer.setName("Name");
		
		for(int i = 0 ; i < 3; i++) {
			Address address = new Address();
			address.setAddress("Address street number " + i );
			address.setCity("Athens");
			address.setCity("Greece");
			address.setPostCode(Integer.toString(10000+ i));
			
			customer.getAddresses().add(address);
		}
		
		filename = "customer.xml";
	}

	@Test
	public void testMarshal() throws JAXBException {
		ImportExportManager manager = new ImportExportManager();
		manager.marshal(customer, filename);
		
		File file = new File(filename);
		assertTrue(file.exists());
		
		Customer importedCustomer = manager.unmarshal(filename);
		
		assertEquals(customer.getLastname(), importedCustomer.getLastname());
		assertEquals(customer.getName(), importedCustomer.getName());
		assertEquals(customer.getAddresses().size(), importedCustomer.getAddresses().size());
		
	}
	
	@After
	public void tearDown() {
		File file = new File(filename);
		file.delete();
	}

}
