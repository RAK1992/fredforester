package com.mycompany.dao;

import java.util.List;

import com.mycompany.model.Customer;
import com.mycompany.model.CustomerAddress;
import com.mycompany.model.CustomerSecurityQuestion;
import com.mycompany.model.SearchCriteria;

public interface CustomerDao extends GenericDao<Customer, Long> {
	
	public Customer findPersonById(Long id);
    public List<Customer> findCustomers();
    public List<Customer> findCustomersByLastName(String lastName);
    public void delete(Customer person);
    public Customer saveAddress(Long id, CustomerAddress address);
    public Customer deleteAddress(Long id, Long addressId);
    public Customer saveQuestion(Long id, CustomerSecurityQuestion question);
    public Customer deleteQuestion(Long id, Long questionId);
    public List<Customer> getByCriteria(final SearchCriteria searchCriteria);

}
