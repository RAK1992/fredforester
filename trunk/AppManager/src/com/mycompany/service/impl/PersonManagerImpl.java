package com.mycompany.service.impl;

import java.util.List;

import javax.jws.WebService;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;

import com.mycompany.dao.PersonDao;
import com.mycompany.exceptions.DuplicateRecordException;
import com.mycompany.model.Person;
import com.mycompany.service.PersonManager;
import com.mycompany.webapp.pagers.JQueryPager;

@WebService(serviceName = "PersonService", endpointInterface = "com.mycompany.service.PersonManager")
public class PersonManagerImpl extends GenericManagerImpl<Person, Long> implements PersonManager {
	
    private PersonDao personDao;


    public PersonManagerImpl(PersonDao personDao) {
        super(personDao);
        this.personDao = personDao;
    }

    public JQueryPager getPersonCriteria(JQueryPager paginatedList) {
		return personDao.getPersonCriteria(paginatedList);
	}
    
    public List<Person> findByEmail(final String email)
    {
    	return personDao.findByEmail(email);
    }
    
    public Object savePerson(Person o) throws DuplicateRecordException
    {
    	try
    	{
    		personDao.savePerson(o);
    		return o;
	    } catch (DataIntegrityViolationException e) {
	        log.warn(e.getMessage());
	        throw new DuplicateRecordException("Person '" + o.getEmail() + "' already exists!");
	    } catch (JpaSystemException e) { // needed for JPA
	        log.warn(e.getMessage());
	        throw new DuplicateRecordException("Person '" + o.getEmail() + "' already exists!");
	    }
    }

	

}
