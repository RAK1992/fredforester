package com.mycompany.service;

import java.util.List;

import javax.jws.WebService;

import com.mycompany.exceptions.DuplicateRecordException;
import com.mycompany.model.Person;
//import com.mycompany.webapp.pagers.JQueryPager;

@WebService
public interface PersonManager extends GenericManager<Person, Long> {
    //public JQueryPager getPersonCriteria(final JQueryPager paginatedList);
    public List<Person> findByEmail(final String email);
    public Object savePerson(Person o) throws DuplicateRecordException;
}
