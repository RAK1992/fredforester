package com.mycompany.service;

import com.mycompany.exceptions.DuplicateRecordException;
import com.mycompany.model.Appuservalues;
import com.mycompany.webapp.pagers.JQueryPager;

public interface AppUserValuesManager extends GenericManager<Appuservalues, Long> {
	
	public Appuservalues getAppUserValue(final long id);
	public Object saveAppUserValue(final Appuservalues o) throws DuplicateRecordException;
	public JQueryPager getAppUserValuesCriteria(final JQueryPager paginatedList);

}
