/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;

import com.mycompany.dao.IamDao;
import com.mycompany.exceptions.DuplicateRecordException;
import com.mycompany.model.Application;
import com.mycompany.model.Applicationowner;
import com.mycompany.model.Applicationpriv;
import com.mycompany.model.Approleowner;
import com.mycompany.model.Approleuservalues;
import com.mycompany.model.Appuservalues;
import com.mycompany.model.Priv;
import com.mycompany.model.Roles;
import com.mycompany.model.RolesPriv;
import com.mycompany.service.IamManager;
import com.mycompany.webapp.pagers.JQueryPager;

/**
 *
 * @author Fred Forester
 */
public class IamManagerImpl extends GenericManagerImpl<Application, Long> implements IamManager {

    IamDao iamDao;

    /*
     * 
     * this setter must match the repository name used in the IamDaoHibernate
     */
    
    @Autowired
    public void setIamDao(IamDao iamDao) {
        this.iamDao = iamDao;
        this.dao = iamDao;
    }
    
    
    /*
    public IamManagerImpl(IamDao iamDao)
    {
    	super(iamDao);
        this.iamDao = iamDao;
    }
    */

    public List<Priv> getPrivSelect() {
        return iamDao.getPrivSelect();
    }

    public List<Priv> getPrivByApp(final String appId)
    {
        return iamDao.getPrivByApp(appId);
    }
    
    
	public List<Application> getApplications()
	{
	    return iamDao.getApplications();
	}
	
	public Application getApplication(final Long id)
	{
	    return iamDao.getApplication(id);
	}
	
	public List<Applicationpriv> getAppPrivListByApp(final String appId)
	{
	    return iamDao.getAppPrivListByApp(appId);
	}
	
	public String getPrivSelectString()
	{
	    return iamDao.getPrivSelectString();
	}
	
	public JQueryPager getApplicationsCriteria(final JQueryPager paginatedList)
	{
	    return iamDao.getApplicationsCriteria(paginatedList);
	}
	
	public Object saveApp(final Application o)
	{
	    return iamDao.saveApp(o);
	}
	
	public Object saveAppPriv(Applicationpriv o) throws DuplicateRecordException
	{
		try {
			return iamDao.saveAppPriv(o);
		} catch (DataIntegrityViolationException e) {
			// e.printStackTrace();
			log.warn(e.getMessage());
			throw new DuplicateRecordException("App Privilege already exists!");
		} catch (JpaSystemException e) { // needed for JPA
			// e.printStackTrace();
			log.warn(e.getMessage());
			throw new DuplicateRecordException("App Privilege already exists!");
		}
	}
	
	public List<RolesPriv> getAppPrivRolesByApp(final String appId,final String privNo)
	{
	    return iamDao.getAppPrivRolesByApp(appId,privNo);
	}
	
	public List<Approleuservalues> getAppRoleUserValuesByApp(final String appId)
	{
	    return iamDao.getAppRoleUserValuesByApp(appId);
	}
	
	public List<Roles> getRoleSelectByPriv(final String privNo)
	{
	    return iamDao.getRoleSelectByPriv(privNo);
	}
	
	public List<Appuservalues> getCUVSelect()
	{
	    return iamDao.getCUVSelect();
	}
	
	public Object saveAppUserValues(final Approleuservalues o) throws DuplicateRecordException
	{
		try {
			return iamDao.saveAppUserValues(o);
		} catch (DataIntegrityViolationException e) {
			// e.printStackTrace();
			log.warn(e.getMessage());
			throw new DuplicateRecordException("App User Value already exists!");
		} catch (JpaSystemException e) { // needed for JPA
			// e.printStackTrace();
			log.warn(e.getMessage());
			throw new DuplicateRecordException("App User Value already exists!");
		}
	}
	
	public Object saveAppOwner(final Applicationowner o) throws DuplicateRecordException
	{
		try {
			return iamDao.saveAppOwner(o);
		} catch (DataIntegrityViolationException e) {
			// e.printStackTrace();
			log.warn(e.getMessage());
			throw new DuplicateRecordException("App Owner '" + o.getOwner() + "' already exists!");
		} catch (JpaSystemException e) { // needed for JPA
			// e.printStackTrace();
			log.warn(e.getMessage());
			throw new DuplicateRecordException("App Owner '" + o.getOwner() + "' already exists!");
		}
	}
	
	public List<Applicationowner> getApplicationOwners(final String appId)
	{
		return iamDao.getApplicationOwners(appId);
	}
	
	public Object saveAppRoleOwner(final Approleowner o) throws DuplicateRecordException
	{
		try {
			return iamDao.saveAppRoleOwner(o);
		} catch (DataIntegrityViolationException e) {
			// e.printStackTrace();
			log.warn(e.getMessage());
			throw new DuplicateRecordException("App Role Owner '" + o.getOwner() + "' already exists!");
		} catch (JpaSystemException e) { // needed for JPA
			// e.printStackTrace();
			log.warn(e.getMessage());
			throw new DuplicateRecordException("App Role Owner '" + o.getOwner() + "' already exists!");
		}
	}
	
	public List<Approleowner> getApplicationRoleOwners(final String appId)
	{
		return iamDao.getApplicationRoleOwners(appId);
	}
}
