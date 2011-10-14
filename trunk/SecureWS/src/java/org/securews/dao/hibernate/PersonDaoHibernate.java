package org.securews.dao.hibernate;

import java.util.List;
import org.securews.dao.PersonDao;
import org.securews.model.Person;

public class PersonDaoHibernate extends GenericDaoHibernate<Person, Long> implements PersonDao {

    public PersonDaoHibernate() {
        super(Person.class);
    }

    public List<Person> findByEmail(final String email) {
        return getHibernateTemplate().find("from Person where UPPER(email)=UPPER(?)", email);
    }

    public Object savePerson(Person o) {
        getHibernateTemplate().saveOrUpdate(o);
        // necessary to throw a DataIntegrityViolation and catch it in Manager
        getHibernateTemplate().flush();
        return o;
    }
}
