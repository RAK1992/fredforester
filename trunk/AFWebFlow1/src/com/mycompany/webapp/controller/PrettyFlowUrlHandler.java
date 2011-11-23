package com.mycompany.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.webflow.context.servlet.DefaultFlowUrlHandler;
import org.springframework.webflow.core.collection.AttributeMap;

public class PrettyFlowUrlHandler extends DefaultFlowUrlHandler {

	protected final Log log = LogFactory.getLog(getClass());
	
	@Override
	public String createFlowDefinitionUrl(String flowId, AttributeMap input, HttpServletRequest request) {
		return cleanUrl(super.createFlowDefinitionUrl(flowId, input, request),
				request);
	}

	@Override
	public String createFlowExecutionUrl(String flowId, String flowExecutionKey, HttpServletRequest request) {
		return cleanUrl(
				super.createFlowExecutionUrl(flowId, flowExecutionKey, request),
				request);
	}

	protected String cleanUrl(String url, HttpServletRequest request) {
		String pattern = request.getServletPath().substring(1) + "/";
		String retUrl = url.replaceFirst(pattern, "");
		log.debug("pretty url " + retUrl);
		return retUrl;
	}

}