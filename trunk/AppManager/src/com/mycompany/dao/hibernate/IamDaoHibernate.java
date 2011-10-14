/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.mycompany.dao.IamDao;
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
@Repository("iamDao")
public class IamDaoHibernate extends GenericDaoHibernate<Application, Long> implements IamDao {


	//private final String fromApplication = " FROM APPLICATION Application ";
    //private final String topCountSQL = "select * from  (SELECT  x.*, rownum as r FROM (";
    //private final String botCountSQL = ") x ) where r between ";
    
    public IamDaoHibernate() {
        super(Application.class);
    }
    
    @SuppressWarnings("unchecked")
    public List<Applicationpriv> getAppPrivListByApp(final String appId) {
        List<Applicationpriv> myList = new ArrayList<Applicationpriv>();
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String sql = "select ap.applicationprivid as applicationprivid," +
                		     "a.app_shortname as appShortname, " +
                             "p.priv_id as privId, " +
                             "ap.priv_no as privNo," +
                             "ap.activeflag as activeFlag, " +
                             "ap.last_update as lastUpdate, " +
                             "ap.username as userName " +
                             "from applicationpriv ap " +
                             "join application a on ap.applicationid=a.applicationid " +
                             "join priv p on ap.priv_no=p.priv_no " +
                             "where a.applicationid=:appId " +
                             "order by a.app_shortname, p.priv_id";
                log.debug("SQL " + sql);
                SQLQuery qry = session.createSQLQuery(sql);
                qry.setString("appId", appId);
                qry.addScalar("applicationprivid", StandardBasicTypes.LONG);
                qry.addScalar("appShortname", StandardBasicTypes.STRING);
                qry.addScalar("privId", StandardBasicTypes.STRING);
                qry.addScalar("privNo", StandardBasicTypes.LONG);
                qry.addScalar("activeFlag", StandardBasicTypes.LONG);
                qry.addScalar("lastUpdate", StandardBasicTypes.TIMESTAMP);
                qry.addScalar("userName", StandardBasicTypes.STRING);
                qry.setResultTransformer(Transformers
                        .aliasToBean(Applicationpriv.class));
                return qry.list();
            }
        };
        myList = (List<Applicationpriv>) getHibernateTemplate().execute(callback);
        return myList;
    }
    
    @SuppressWarnings("unchecked")
    public List<RolesPriv> getAppPrivRolesByApp(final String appId, final String privNo) {
        List<RolesPriv> myList = new ArrayList<RolesPriv>();
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String sql = 
                    "select " +
                    "p.priv_no as privNo, " +
                    "ro.role_no as roleNo, " +
                    "p.priv_id as privId," +
                    "ro.role_id as roleId, " +
                    "ro.role_desc as roleDescription " +
                    "from " +
                    "applicationpriv ap " +
                    "join roles_priv rp on ap.priv_no = rp.priv_no " +
                    "join roles ro on ro.role_no = rp.role_no " +
                    "join priv p on p.priv_no = ap.priv_no " +
                    "where ap.applicationid=:appId ";
                
                if (!StringUtils.isEmpty(privNo) && StringUtils.isNumeric(privNo))
                    sql += "and ap.priv_no=:privNo";

                log.debug("SQL " + sql);
                SQLQuery qry = session.createSQLQuery(sql);
                qry.setString("appId", appId);
                if (!StringUtils.isEmpty(privNo) && StringUtils.isNumeric(privNo))
                    qry.setString("privNo", privNo);
                
                qry.addScalar("privId", StandardBasicTypes.STRING);
                qry.addScalar("privNo", StandardBasicTypes.LONG);
                qry.addScalar("roleNo", StandardBasicTypes.LONG);
                qry.addScalar("roleId", StandardBasicTypes.STRING);
                qry.addScalar("roleDescription", StandardBasicTypes.STRING);
                qry.setResultTransformer(Transformers
                        .aliasToBean(RolesPriv.class));
                return qry.list();
            }
        };
        myList = (List<RolesPriv>) getHibernateTemplate().execute(callback);
        return myList;
    }
    
    
    @SuppressWarnings("unchecked")
    public List<Priv> getPrivSelect() {
        List<Priv> myList = new ArrayList<Priv>();
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String sql = "select distinct PRIV_NO as privNo," +
                             "PRIV_ID as privId " +
                             "from Priv " +
                             "where ACTIVEFLAG=1 " +
                             "order by PRIV_ID";
                log.debug("SQL " + sql);
                SQLQuery qry = session.createSQLQuery(sql);
                
                qry.addScalar("privId", StandardBasicTypes.STRING);
                qry.addScalar("privNo", StandardBasicTypes.LONG);
                qry.setResultTransformer(Transformers
                        .aliasToBean(Priv.class));
                return qry.list();
            }
        };
        myList = (List<Priv>) getHibernateTemplate().execute(callback);
        return myList;
    }
    
    public String getPrivSelectString()
    {
        List<Priv> privs = this.getPrivSelect();
        String privOptions = "";
        int length = privs.size();
        for(int i=0;i<length;i++)
        {
            Priv p = (Priv)privs.get(i);
            privOptions += p.getPrivNo() + ":" + p.getPrivId().trim();
            if (i != (length - 1))
                privOptions += ";";
        }
        //log.debug("SelectPriv " + privOptions);
        return privOptions;
    }
    
    @SuppressWarnings("unchecked")
    public List<Application> getApplications()
    {
        List<Application> myList = (List<Application>)getHibernateTemplate().find("from Application");
        return myList;
    }
    
    
    @SuppressWarnings("unchecked")
    public List<Applicationowner> getApplicationOwners(final String appId)
    {
    	List<Applicationowner> myList = new ArrayList<Applicationowner>();
    	HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
            	String sql = "select " +
	            	 "applicationownid as applicationownid, " +
					 "owner as owner, " +
					 "activeflag as activeFlag, " +
					 "last_update as lastUpdate, " +
					 "username as userName, " +
					 "first_name as firstName, " +
					 "last_name as lastName " +
					 "from applicationowner " +
					 "where applicationid = :appId";
                
                log.debug("SQL " + sql);
                SQLQuery qry = session.createSQLQuery(sql);
                qry.setString("appId", appId);
                qry.addScalar("applicationownid", StandardBasicTypes.LONG);
                qry.addScalar("owner", StandardBasicTypes.STRING);
                qry.addScalar("activeFlag", StandardBasicTypes.LONG);
                qry.addScalar("userName", StandardBasicTypes.STRING);
                qry.addScalar("firstName", StandardBasicTypes.STRING);
                qry.addScalar("lastName", StandardBasicTypes.STRING);
                qry.setResultTransformer(Transformers
                        .aliasToBean(Applicationowner.class));
                return qry.list();
            }
        };
        myList = (List<Applicationowner>) getHibernateTemplate().execute(callback);
        return myList;
    	
    }
    
    @SuppressWarnings("unchecked")
    public List<Approleowner> getApplicationRoleOwners(final String appId)
    {
    	List<Approleowner> myList = new ArrayList<Approleowner>();
    	HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
            	String sql = "select " +  
			            	"a.approleownid as approleownid," +  
			            	"a.owner as owner," +
			            	"a.activeflag as activeFlag," +  
			            	"a.last_update as lastUpdate," +  
			            	"a.username as userName," +  
			            	"a.first_name as firstName," +  
			            	"a.last_name as lastName," +
			            	"r.role_no as roleNo, " +  
			            	"r.role_id as roleId " +  
			            	"from approleowner a, roles r " +  
			            	"where applicationid = :appId " +
			            	"and a.role_no = r.role_no";
                
                log.debug("SQL " + sql);
                SQLQuery qry = session.createSQLQuery(sql);
                qry.setString("appId", appId);
                qry.addScalar("approleownid", StandardBasicTypes.LONG);
                qry.addScalar("owner", StandardBasicTypes.STRING);
                qry.addScalar("activeFlag", StandardBasicTypes.LONG);
                qry.addScalar("userName", StandardBasicTypes.STRING);
                qry.addScalar("firstName", StandardBasicTypes.STRING);
                qry.addScalar("lastName", StandardBasicTypes.STRING);
                qry.addScalar("roleId", StandardBasicTypes.STRING);
                qry.addScalar("roleNo", StandardBasicTypes.LONG);
                qry.setResultTransformer(Transformers
                        .aliasToBean(Approleowner.class));
                return qry.list();
            }
        };
        myList = (List<Approleowner>) getHibernateTemplate().execute(callback);
        return myList;
    	
    }
    
    @SuppressWarnings("unchecked")
    public List<Priv> getPrivByApp(final String appId)
    {
        List<Priv> myList = new ArrayList<Priv>();
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String sql = "select p.priv_no as privNo,p.priv_id as privId " +
                             "from priv p,applicationpriv ap " +
                             "where ap.priv_no = p.priv_no " +
                             "and ap.applicationid = :appId";
                
                log.debug("SQL " + sql);
                SQLQuery qry = session.createSQLQuery(sql);
                qry.setString("appId", appId);
                qry.addScalar("privNo", StandardBasicTypes.LONG);
                qry.addScalar("privId", StandardBasicTypes.STRING);
                qry.setResultTransformer(Transformers
                        .aliasToBean(Priv.class));
                return qry.list();
            }
        };
        myList = (List<Priv>) getHibernateTemplate().execute(callback);
        return myList;
    }
    
    @SuppressWarnings("unchecked")
    public JQueryPager getApplicationsCriteria(final JQueryPager paginatedList)
    {
    	log.debug("getApplicationsCriteria");
        log.debug("paginatedList " + paginatedList);
        
        if (paginatedList.getSortCriterion() == null)
        	paginatedList.setSortCriterion("appShortname");
    	Map<String,Object> resultMap = getAppManagerCriteria(paginatedList,Application.class);
    	paginatedList.setList((List)resultMap.get("list"));
    	int count = 0;
    	Integer icount = (Integer)resultMap.get("count");
    	if (icount != null)
    		count = icount.intValue();
    	paginatedList.setTotalNumberOfRows(count);
        
        return paginatedList;
    }
    
    
    
    public Object saveApp(Application o) {
        return getHibernateTemplate().merge(o);
    }
    
    public Object saveAppPriv(Applicationpriv o) {
    	getHibernateTemplate().saveOrUpdate(o);
        // necessary to throw a DataIntegrityViolation and catch it in Manager
        getHibernateTemplate().flush();
        return o;
    }
    
    public Object saveAppUserValues(final Approleuservalues o)
    {
    	getHibernateTemplate().saveOrUpdate(o);
        // necessary to throw a DataIntegrityViolation and catch it in Manager
        getHibernateTemplate().flush();
        return o;
    }
    
    public Object saveAppOwner(final Applicationowner o)
    {
    	getHibernateTemplate().saveOrUpdate(o);
        // necessary to throw a DataIntegrityViolation and catch it in Manager
        getHibernateTemplate().flush();
        return o;
    }
    
    public Object saveAppRoleOwner(final Approleowner o)
    {
    	getHibernateTemplate().saveOrUpdate(o);
        // necessary to throw a DataIntegrityViolation and catch it in Manager
        getHibernateTemplate().flush();
        return o;
    }
    
    @SuppressWarnings("unchecked")
    public List<Approleuservalues> getAppRoleUserValuesByApp(final String appId)
    {
        List<Approleuservalues> myList = new ArrayList<Approleuservalues>();
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String sql = 
                	"select " +  
                	"aruv.id as id, " +
                	"aruv.applicationid as applicationid, " +
                	"aruv.activeflag as activeFlag," +
                	"aruv.appuservalueid as appuservalueid, " + 
                	"p.priv_id as privId, " +
                	"p.priv_no as privNo, " +
                	"r.role_id as roleId, " +
                	"r.role_no as roleNo, " +
                	"auv.appuservaluefieldname as appuservaluefieldname, " +   
                	"auv.appuservaluetitle as appuservaluetitle, " +
                	"auv.appuservaluesource as appuservaluesource " +
                	"from approleuservalues aruv " +
                	"join priv p on p.priv_no = aruv.priv_no " +
                	"join roles r on r.role_no = aruv.role_no " +
                	"join appuservalues auv on aruv.appuservalueid = auv.appuservalueid " +
                	"where aruv.applicationid=:appId";
                
                log.debug("SQL " + sql);
                SQLQuery qry = session.createSQLQuery(sql);
                qry.setString("appId", appId);
                qry.addScalar("id", StandardBasicTypes.LONG);
                qry.addScalar("privId", StandardBasicTypes.STRING);
                qry.addScalar("privNo", StandardBasicTypes.LONG);
                qry.addScalar("roleId", StandardBasicTypes.STRING);
                qry.addScalar("roleNo", StandardBasicTypes.LONG);
                qry.addScalar("activeFlag", StandardBasicTypes.LONG);
                qry.addScalar("appuservalueid", StandardBasicTypes.LONG);
                qry.addScalar("appuservaluefieldname", StandardBasicTypes.STRING);
                qry.addScalar("appuservaluetitle", StandardBasicTypes.STRING);
                qry.addScalar("appuservaluesource", StandardBasicTypes.STRING);
                qry.setResultTransformer(Transformers
                        .aliasToBean(Approleuservalues.class));
                return qry.list();
            }
        };
        myList = (List<Approleuservalues>) getHibernateTemplate().execute(callback);
        return myList;
    }
    
    @SuppressWarnings("unchecked")
    public List<Roles> getRoleSelectByPriv(final String privNo)
    {
        List<Roles> myList = new ArrayList<Roles>();
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String sql = 
                        "select " +
                        "ro.role_no as roleNo, " +
                        "ro.role_id as roleId, " +
                        "ro.roletypeid as roletypeid, " + 
                        "ro.role_desc as roleDesc,  " +
                        "ro.role_desc_ext as roleDescExt, " + 
                        "ro.last_update as lastUpdate, " +
                        "ro.activeflag as asctiveflag  " +
                        "from " +
                        "roles ro " +
                        "join roles_priv rp on ro.role_no = rp.role_no " +
                        "where ro.activeflag=1 and rp.priv_no=:privNo ";
                log.debug("SQL " + sql);
                SQLQuery qry = session.createSQLQuery(sql);
                qry.setString("privNo", privNo);
                qry.addScalar("roleId", StandardBasicTypes.STRING);
                qry.addScalar("roleNo", StandardBasicTypes.LONG);
                qry.addScalar("roletypeid", StandardBasicTypes.LONG);
                qry.setResultTransformer(Transformers
                        .aliasToBean(Roles.class));
                return qry.list();
            }
        };
        myList = (List<Roles>) getHibernateTemplate().execute(callback);
        return myList;
    }
    
    @SuppressWarnings("unchecked")
    public List<Appuservalues> getCUVSelect()
    {
        
        List<Appuservalues> myList = (List<Appuservalues>)getHibernateTemplate().find("from Appuservalues order by appuservaluefieldname");
        return myList;
    }
    
    public Application getApplication(final Long id)
    {
        Application app = null;
        try
        {
            app = (Application)getHibernateTemplate().get(Application.class, id);
        }
        catch(Exception e)
        {
            log.error("Error: " + e.getMessage(),e);
        }
        return app;
    }
    
    
}
