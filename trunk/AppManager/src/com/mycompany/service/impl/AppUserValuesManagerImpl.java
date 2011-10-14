package com.mycompany.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;

import com.mycompany.dao.AppUserValuesDao;
import com.mycompany.exceptions.DuplicateRecordException;
import com.mycompany.model.Appuservalues;
import com.mycompany.service.AppUserValuesManager;
import com.mycompany.webapp.pagers.JQueryPager;

public class AppUserValuesManagerImpl extends GenericManagerImpl<Appuservalues, Long> implements AppUserValuesManager {

	private AppUserValuesDao appUserValuesDao;
	
	@Autowired
	public void setAppUserValuesDao(AppUserValuesDao dao) {
		this.appUserValuesDao = dao;
	}
	
	public JQueryPager getAppUserValuesCriteria(JQueryPager paginatedList) {
		return appUserValuesDao.getAppUserValuesCriteria(paginatedList);
	}

	public Appuservalues getAppUserValue(long id) {
		return appUserValuesDao.getAppUserValue(id);
	}

	public Object saveAppUserValue(Appuservalues o) throws DuplicateRecordException {
		
		try
		{
			return appUserValuesDao.saveAppUserValue(o);
		} catch (DataIntegrityViolationException e) {
	        log.warn(e.getMessage());
	        throw new DuplicateRecordException("UserValue '" + o.getAppuservaluefieldname() + "' already exists!");
	    } catch (JpaSystemException e) { // needed for JPA
	        log.warn(e.getMessage());
	        throw new DuplicateRecordException("UserValue '" + o.getAppuservaluefieldname() + "' already exists!");
	    }
	}

}
