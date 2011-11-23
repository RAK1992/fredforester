package com.mycompany.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.mycompany.dao.CustomerDao;
import com.mycompany.model.Customer;
import com.mycompany.model.CustomerAddress;
import com.mycompany.model.CustomerSecurityQuestion;
import com.mycompany.model.Person;
import com.mycompany.model.SearchCriteria;

@Repository("customerDao")
public class CustomerDaoHibernate extends GenericDaoHibernate<Customer, Long> implements CustomerDao {

	public CustomerDaoHibernate() {
        super(Customer.class);
    }
	

	@Override
	public Customer findPersonById(Long id) {
		return get(id);
	}

	@Override
	public List<Customer> findCustomers() {
		return getAll();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Customer> findCustomersByLastName(String lastName) {
	    return getHibernateTemplate().find("from Customer where UPPER(lastname)=UPPER(?)", lastName);
	}

	@Override
	public void delete(Customer person) {
		 remove(person.getId());

	}

	@Override
	public Customer saveAddress(Long id, CustomerAddress address) {
		Customer person = findPersonById(id);
    	log.debug("saveAddress for " + person.getFirstname() + " " + person.getLastname());
    	address.setCustomerId(id);
    	log.debug("new address " + address);
    	Set<CustomerAddress> addrs = person.getCustomerAddresses();
    	
    	for(CustomerAddress ca : addrs)
    	{
    		log.debug("Has Address " + ca);
    	}
    	
    	if (address.getId() == null)
        {
        	log.debug("adding address " + address);
            person.getCustomerAddresses().add(address);
            return save(person);
        }
    	
        if (person.getCustomerAddresses().contains(address)) {
        	log.debug("replacing address " + address);
            person.getCustomerAddresses().remove(address);
            person.getCustomerAddresses().add(address);    
        }
        return save(person);
	}

	@Override
	public Customer deleteAddress(Long id, Long addressId) {
		Customer person = findPersonById(id);

    	CustomerAddress address = new CustomerAddress();
        address.setId(addressId);

        if (person.getCustomerAddresses().contains(address)) {
            for (CustomerAddress a : person.getCustomerAddresses()) {
                if (a.getId().equals(addressId)) {
                	//this.getEntityManager().remove(a);
                    person.getCustomerAddresses().remove(address);
                    
                    break;
                }
            }
        }

        return person;
	}
	
	public Customer saveQuestion(Long id, CustomerSecurityQuestion question)
	{
		Customer person = findPersonById(id);
    	log.debug("saveAddress for " + person.getFirstname() + " " + person.getLastname());
    	question.setCustomerId(id);
    	log.debug("new address " + question);
    	List<CustomerSecurityQuestion> qs = person.getQuestions();
    	
    	for(CustomerSecurityQuestion q : qs)
    	{
    		log.debug("Has Question " + q);
    	}
    	
    	if (question.getId() == null)
        {
        	log.debug("adding address " + question);
            person.getQuestions().add(question);
            return save(person);
        }
    	
        if (person.getQuestions().contains(question)) {
        	log.debug("replacing question " + question);
            person.getQuestions().remove(question);
            person.getQuestions().add(question);    
        }
        return save(person);
	}
	
    public Customer deleteQuestion(Long id, Long questionId)
    {
    	Customer person = findPersonById(id);

    	CustomerSecurityQuestion question = new CustomerSecurityQuestion();
    	question.setId(questionId);

        if (person.getCustomerAddresses().contains(question)) {
            for (CustomerSecurityQuestion q : person.getQuestions()) {
                if (q.getId().equals(questionId)) {
                	//this.getEntityManager().remove(a);
                    person.getQuestions().remove(question);
                    break;
                }
            }
        }
        return person;
    }
    
	@SuppressWarnings("unchecked")
    public List<Customer> getByCriteria(final SearchCriteria searchCriteria)
    {
		log.debug("getByCriteria");
        log.debug("paginatedList " + searchCriteria);
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                Criteria criteria = session.createCriteria(Customer.class);
                String searchFor = searchCriteria.getSearchString();
                if (searchFor == null)
                	searchFor = "";
                criteria.setFirstResult(searchCriteria.getStart());
                criteria.setMaxResults(searchCriteria.getPageSize());
                
                if (searchFor.trim().length() > 0)
                {
                	searchFor = "%" + searchFor + "%";
                	Criterion cfName = Restrictions.like("firstname",searchFor).ignoreCase();
                	Criterion clName = Restrictions.like("lastname",searchFor).ignoreCase();
                	Criterion cName = Restrictions.like("name",searchFor).ignoreCase();
                	Disjunction disjunction = Restrictions.disjunction();
                	disjunction.add(cfName);
                	disjunction.add(clName);
                	disjunction.add(cName);
                    criteria.add(disjunction);
                }
                
                List<Customer> recList = criteria.list();
                return recList;
                
                
            }
        };
        
        try
        {
            List<Customer> recList = getHibernateTemplate().execute(callback);
            return recList;
        }
        catch(Exception e)
        {
        	log.error("Get Crtiteria Error",e);
        	List<Customer> recList = new ArrayList<Customer>();
        	return recList;
        	
        }
    }

}
