/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.dao;

import java.util.List;

import com.mycompany.model.Application;
import com.mycompany.model.Applicationowner;
import com.mycompany.model.Applicationpriv;
import com.mycompany.model.Approleowner;
import com.mycompany.model.Approleuservalues;
import com.mycompany.model.Appuservalues;
import com.mycompany.model.Priv;
import com.mycompany.model.Roles;
import com.mycompany.model.RolesPriv;
import com.mycompany.webapp.pagers.JQueryPager;

/**
 *
 * @author Fred Forester
 */
public interface IamDao extends GenericDao<Application, Long> {

    public Application getApplication(final Long id);
    public List<Application> getApplications();
    public JQueryPager getApplicationsCriteria(final JQueryPager paginatedList);
    public List<Applicationpriv> getAppPrivListByApp(final String appId);
    public List<RolesPriv> getAppPrivRolesByApp(final String appId,final String privNo);
    public List<Approleuservalues> getAppRoleUserValuesByApp(final String appId);
    public List<Priv> getPrivByApp(final String appId);
    public List<Priv> getPrivSelect();
    public String getPrivSelectString();
    public Object saveApp(final Application o);
    public Object saveAppPriv(Applicationpriv o);
    public List<Roles> getRoleSelectByPriv(final String privNo);
    public List<Appuservalues> getCUVSelect();
    public Object saveAppUserValues(final Approleuservalues o);
    public Object saveAppOwner(final Applicationowner o);
    public List<Applicationowner> getApplicationOwners(final String appId);
    public Object saveAppRoleOwner(final Approleowner o);
    public List<Approleowner> getApplicationRoleOwners(final String appId);
	
	
}
