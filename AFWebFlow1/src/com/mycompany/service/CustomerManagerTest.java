package com.mycompany.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.common.util.SortedArraySet;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mycompany.model.Customer;
import com.mycompany.model.CustomerAddress;
import com.mycompany.model.CustomerSecurityQuestion;

public class CustomerManagerTest extends BaseManagerTestCase {
	
private Log log = LogFactory.getLog(CustomerManagerTest.class);
	
    @Autowired
    CustomerManager customerManager;
    
    @Test
    public void getAllCustomers()
    {
    	List<Customer> customers = customerManager.getAll();
    	assertNotNull(customers);
    	assertEquals(1, customers.size());
        for(Customer c : customers)
        {
        	log.debug(c);
        }
    }
    
    @Test
    public void addCustomer()
    {
    	Customer c = new Customer();
    	c.setEmail("fred@fred.com");
    	c.setFirstname("Fred");
    	c.setLastname("Forrester");
    	c.setName("Fred Forrester");
    	c.setPassword("mypassword");
    	c.setUsername("fforrester");
    	
    	Customer nc = customerManager.save(c);
    	assertNotNull(nc);
    	assertEquals("Forrester", nc.getLastname());
    	
    	CustomerAddress a = new CustomerAddress();
    	a.setCustomerId(nc.getId());
    	a.setCity("Thorndale");
    	a.setCountry("USA");
    	a.setState("PA");
    	a.setStreet("36 Horseshoe Dr");
    	a.setZip("19372");
    	
    	Set<CustomerAddress> addresses = new SortedArraySet<CustomerAddress>();
    	addresses.add(a);
    	nc.setCustomerAddresses(addresses);
    	
    	List<CustomerSecurityQuestion> csqs = new ArrayList<CustomerSecurityQuestion>();
    	
    	CustomerSecurityQuestion csq = new CustomerSecurityQuestion();
    	csq.setAnswer("Romando");
    	csq.setQuestion("Mothers Maiden Name");
    	csq.setCustomerId(nc.getId());
    	log.debug("Security Question " + csq);
    	csqs.add(csq);
    	nc.setQuestions(csqs);
    	
    	Customer nca = customerManager.save(nc);
    	log.debug("Saved " + nca);
    	log.debug("Saved Security Question " + nca.getQuestions().get(0));
    	
    	c = customerManager.get(nc.getId());
    	assertNotNull(c);
    	
    	addresses = c.getCustomerAddresses();
    	assertEquals(1, addresses.size());
    	
    	for(CustomerAddress na : addresses)
    	    assertEquals("36 Horseshoe Dr", na.getStreet());
    	
    	log.debug("Gotten " + c);
    	
    }

}
