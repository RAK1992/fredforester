package org.securews.dao;

import java.util.List;
import org.securews.model.Person;

public interface PersonDao extends GenericDao<Person, Long> {

    public List<Person> findByEmail(final String email);
    public Object savePerson(Person o);
}
