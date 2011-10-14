package com.mycompany.service;

import java.util.List;

import com.mycompany.exceptions.DuplicateRecordException;
import com.mycompany.model.DummyModel;
import com.mycompany.model.Priv;
import com.mycompany.model.Roles;
import com.mycompany.model.RolesPriv;
import com.mycompany.webapp.pagers.JQueryPager;

public interface PrivRoleManager extends GenericManager<DummyModel, Long> {
	
	public Priv getPriv(final Long id);
	public Roles getRole(final long id);
	public Object savePriv(final Priv o) throws DuplicateRecordException;
	public Object saveRole(final Roles o) throws DuplicateRecordException;
	public Object saveRolesPriv(final RolesPriv o) throws DuplicateRecordException;
	public JQueryPager getPrivsCriteria(final JQueryPager paginatedList);
	public JQueryPager getRolesCriteria(final JQueryPager paginatedList);
	
	public List<RolesPriv> getRolesPrivByPriv(final String privNo);
	public List<Roles> getRoleSelect();
	
	// used to check for duplicates
	public List<Priv> findByPrivId(final String privId);
	public List<Roles> findByRoleId(final String roleId);

}
