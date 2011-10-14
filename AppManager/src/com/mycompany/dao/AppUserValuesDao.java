package com.mycompany.dao;

import com.mycompany.model.Appuservalues;
import com.mycompany.model.DummyModel;
import com.mycompany.webapp.pagers.JQueryPager;

public interface AppUserValuesDao extends GenericDao<DummyModel, Long> {
	
	public Appuservalues getAppUserValue(final long id);
	public Object saveAppUserValue(final Appuservalues o);
	public JQueryPager getAppUserValuesCriteria(final JQueryPager paginatedList);

}
