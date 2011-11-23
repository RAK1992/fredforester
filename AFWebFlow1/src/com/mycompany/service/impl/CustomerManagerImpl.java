package com.mycompany.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.dao.CustomerDao;
import com.mycompany.model.Customer;
import com.mycompany.model.CustomerAddress;
import com.mycompany.model.CustomerSecurityQuestion;
import com.mycompany.model.SearchCriteria;
import com.mycompany.service.CustomerManager;

@Service("customerManager")
public class CustomerManagerImpl extends GenericManagerImpl<Customer, Long> implements CustomerManager {

	private CustomerDao customerDao;
	
	@Autowired
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
		this.dao = customerDao;
	}
	
	@Override
	public List<Customer> getAll() {
		return customerDao.getAll();
	}

	@Override
	public Customer get(Long id) {
		return customerDao.get(id);
	}

	@Override
	public boolean exists(Long id) {
		return customerDao.exists(id);
	}

	@Override
	public Customer save(Customer object) {
		log.debug("Save Customer " + object);
		Customer c = customerDao.save(object);
		log.debug("Saved Customer " + c);
		return c;
	}

	@Override
	public void remove(Long id) {
		customerDao.remove(id);

	}

	@Override
	public Customer findPersonById(Long id) {
		return customerDao.findPersonById(id);
	}

	@Override
	public Collection<Customer> findCustomers() {
		return customerDao.findCustomers();
	}

	@Override
	public void delete(Customer person) {
		customerDao.delete(person);
	}

	@Override
	public Customer saveAddress(Long id, CustomerAddress address) {
		return customerDao.saveAddress(id, address);
	}

	@Override
	public Customer deleteAddress(Long id, Long addressId) {
		return customerDao.deleteAddress(id, addressId);
	}
	
	public Customer saveQuestion(Long id, CustomerSecurityQuestion question)
	{
		return customerDao.saveQuestion(id, question);
	}
	
    public Customer deleteQuestion(Long id, Long questionId)
    {
    	return customerDao.deleteQuestion(id, questionId);
    }

	@Override
	public Collection<Customer> findCustomersByLastName(String lastName) {
		return customerDao.findCustomersByLastName(lastName);
	}
	
	@Override
	public List<Customer> getByCriteria(final SearchCriteria searchCriteria)
	{
		return customerDao.getByCriteria(searchCriteria);
	}

}
