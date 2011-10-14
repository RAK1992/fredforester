package com.mycompany.dao;

import java.util.List;

import com.mycompany.model.Person;
import com.mycompany.webapp.pagers.JQueryPager;

public interface PersonDao extends GenericDao<Person, Long> {
	
    public JQueryPager getPersonCriteria(final JQueryPager paginatedList);
    public List<Person> findByEmail(final String email);
    public Object savePerson(Person o);
}
