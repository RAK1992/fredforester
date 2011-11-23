package com.mycompany.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycompany.model.Customer;
import com.mycompany.model.SearchCriteria;
import com.mycompany.service.CustomerManager;

@Controller
public class CustomerController extends BaseController {

	static final String SEARCH_VIEW_PATH_KEY = "/customer/search";

	private static final String SEARCH_VIEW_KEY = "redirect:/customer/search";
	private static final String SEARCH_MODEL_KEY = "customers";

	@Autowired
	protected CustomerManager customerService = null;

	@RequestMapping(value = "/customer/delete", method = RequestMethod.POST)
	public String delete(Customer customer) {
		customerService.delete(customer);

		return SEARCH_VIEW_KEY;
	}

	@RequestMapping(value = "/customers", method = RequestMethod.GET)
	//public @ModelAttribute(SEARCH_MODEL_KEY)
	public String search(SearchCriteria criteria, Model model) {
		log.debug("findAllCustomers");
		if (customerService == null)
			log.debug("customerService is null");
		List<Customer> customers = customerService.getByCriteria(criteria);
		model.addAttribute(customers);
		return "customer/customerList";
	}
	
	@RequestMapping(value = "/customers/searchForm", method = RequestMethod.GET)
	//public @ModelAttribute(SEARCH_MODEL_KEY)
	public String searchForm(Model model) {
		SearchCriteria searchCriteria = new SearchCriteria();
		model.addAttribute(searchCriteria);
		return "customer/searchForm";
	}
}
