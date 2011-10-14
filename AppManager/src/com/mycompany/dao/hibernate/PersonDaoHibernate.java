package com.mycompany.dao.hibernate;

import java.util.List;
import java.util.Map;

import com.mycompany.dao.PersonDao;
import com.mycompany.model.Appuservalues;
import com.mycompany.model.Person;
import com.mycompany.webapp.pagers.JQueryPager;

public class PersonDaoHibernate extends GenericDaoHibernate<Person, Long> implements PersonDao {

    public PersonDaoHibernate() {
        super(Person.class);
    }

    
    
    public JQueryPager getPersonCriteria(JQueryPager paginatedList) {
		
    	try
    	{
			log.debug("getPersonCriteria");
	        log.debug("paginatedList " + paginatedList);
	        
	        if (paginatedList.getSortCriterion() == null)
	    	    paginatedList.setSortCriterion("lastName");
	    	Map<String,Object> resultMap = getAppManagerCriteria(paginatedList,Person.class);
	    	paginatedList.setList((List)resultMap.get("list"));
	    	int count = 0;
	    	Integer icount = (Integer)resultMap.get("count");
	    	if (icount != null)
	    		count = icount.intValue();
	    	paginatedList.setTotalNumberOfRows(count);
	        return paginatedList;
    	}
    	catch(Exception e)
    	{
    		log.error("getPersonCriteria",e);
    		return paginatedList;
    	}
		
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
