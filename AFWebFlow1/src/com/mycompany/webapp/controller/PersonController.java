package com.mycompany.webapp.controller;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.model.Person;
import com.mycompany.service.PersonManager;

@Controller
@RequestMapping("/persons*")

public class PersonController {

	private transient final Log log = LogFactory.getLog(PersonController.class);
	
	private PersonManager personManager;

	@Autowired
	public void setPersonManager(@Qualifier("personManager") PersonManager personManager) {
		this.personManager = personManager;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest() throws Exception {
		List<Person> persons = null;
		try
		{
		    persons = personManager.getAll();
		    
		}
		catch(Exception e)
		{
		    log.error("persons " + persons,e);
		}
		return new ModelAndView().addObject(persons);
		
	}

}
