package com.mycompany.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.webflow.core.FlowException;
import org.springframework.webflow.execution.FlowExecutionOutcome;
import org.springframework.webflow.execution.repository.NoSuchFlowExecutionException;
import org.springframework.webflow.mvc.servlet.AbstractFlowHandler;

public class CustomerFlowHandler extends AbstractFlowHandler {

	private static final String DEFAULT_URL = "/customers";

	@Override
	public String handleExecutionOutcome(FlowExecutionOutcome outcome,
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println("CustomerFlowHandler:handleExecutionOutcome " + outcome.getId());
		return DEFAULT_URL;
	}

	@Override
	public String handleException(FlowException e, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("CustomerFlowHandler:handleException " + e);
		e.printStackTrace();
		if (e instanceof NoSuchFlowExecutionException) {
			return DEFAULT_URL;
		} else {
			throw e;
		}
	}

}
