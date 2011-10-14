package com.mycompany.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;

import com.mycompany.dao.PrivRoleDao;
import com.mycompany.exceptions.DuplicateRecordException;
import com.mycompany.model.DummyModel;
import com.mycompany.model.Priv;
import com.mycompany.model.Roles;
import com.mycompany.model.RolesPriv;
import com.mycompany.service.PrivRoleManager;
import com.mycompany.webapp.pagers.JQueryPager;


public class PrivRoleManagerImpl extends GenericManagerImpl<DummyModel, Long> implements PrivRoleManager {

	PrivRoleDao privRoleDao;
	
	@Autowired
	public void setPrivRoleDao(PrivRoleDao privRoleDao) {
		this.privRoleDao = privRoleDao;
	}

	/*
	public PrivRoleManagerImpl(PrivRoleDao privRoleDao) {
		this.privRoleDao = privRoleDao;
	}
	*/

	public Priv getPriv(Long id) {
		return privRoleDao.getPriv(id);
	}

	public Roles getRole(long id) {
		return privRoleDao.getRole(id);
	}

	public Object savePriv(Priv o) throws DuplicateRecordException {
		
		try {
			return privRoleDao.savePriv(o);
        } catch (DataIntegrityViolationException e) {
            //e.printStackTrace();
            log.warn(e.getMessage());
            throw new DuplicateRecordException("Priv '" + o.getPrivId() + "' already exists!");
        } catch (JpaSystemException e) { // needed for JPA
            //e.printStackTrace();
            log.warn(e.getMessage());
            throw new DuplicateRecordException("Priv '" + o.getPrivId() + "' already exists!");
        }
	}

	public Object saveRole(Roles o) throws DuplicateRecordException {
		
		try {
			return privRoleDao.saveRole(o);
        } catch (DataIntegrityViolationException e) {
            //e.printStackTrace();
            log.warn(e.getMessage());
            throw new DuplicateRecordException("Role '" + o.getRoleId() + "' already exists!");
        } catch (JpaSystemException e) { // needed for JPA
            //e.printStackTrace();
            log.warn(e.getMessage());
            throw new DuplicateRecordException("Role '" + o.getRoleId() + "' already exists!");
        }
		
	}

	public Object saveRolesPriv(RolesPriv o) throws DuplicateRecordException {
		try {
			return privRoleDao.saveRolesPriv(o);
        } catch (DataIntegrityViolationException e) {
            //e.printStackTrace();
            log.warn(e.getMessage());
            throw new DuplicateRecordException("PrivRole already exists!");
        } catch (JpaSystemException e) { // needed for JPA
            //e.printStackTrace();
            log.warn(e.getMessage());
            throw new DuplicateRecordException("PrivRole already exists!");
        }
	}
	
	public JQueryPager getPrivsCriteria(final JQueryPager paginatedList)
	{
		return privRoleDao.getPrivsCriteria(paginatedList);
	}
	
	public JQueryPager getRolesCriteria(final JQueryPager paginatedList)
	{
		return privRoleDao.getRolesCriteria(paginatedList);
	}
	
	public List<RolesPriv> getRolesPrivByPriv(final String privNo)
	{
		return privRoleDao.getRolesPrivByPriv(privNo);
	}
	
	public List<Roles> getRoleSelect()
	{
		return privRoleDao.getRoleSelect();
	}
	
	// used to check for duplicates
	public List<Priv> findByPrivId(final String privId)
	{
		return privRoleDao.findByPrivId(privId);
	}
	
	public List<Roles> findByRoleId(final String roleId)
	{
		return privRoleDao.findByRoleId(roleId);
	}

}
