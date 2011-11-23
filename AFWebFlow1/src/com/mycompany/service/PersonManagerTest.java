package com.mycompany.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mycompany.model.Person;

public class PersonManagerTest extends BaseManagerTestCase {
	
	private Log log = LogFactory.getLog(PersonManagerTest.class);
	
    @Autowired
    private PersonManager mgr;
    
    String emailAddress = "fred@fred.com";
    
    private Person person;
    
    @Test
    public void testGetAllPeople() throws Exception {
        List<Person> people = mgr.getAll();
        assertNotNull(people);
        
        assertEquals(13, people.size());
        for(Person p : people)
        {
        	log.debug(p);
        }
        
    }
    
    @Test
    public void testGetPerson() throws Exception {
        List<Person> people = mgr.findByEmail(emailAddress);
        assertEquals(1, people.size());
        person = people.get(0);
        assertNotNull(person);
        log.debug(person);
    }

    @Test
    public void testSavePerson() throws Exception {
    	List<Person> people = mgr.findByEmail(emailAddress);
        assertEquals(1, people.size());
        person = people.get(0);
        assertNotNull(person);
        log.debug(person);
        person.setLastName("Forrester");
        log.debug("saving user with updated phone number: " + person);
        person = mgr.save(person);
        assertEquals("Forrester", person.getLastName());
    }


}
