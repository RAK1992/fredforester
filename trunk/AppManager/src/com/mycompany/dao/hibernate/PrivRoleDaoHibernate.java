package com.mycompany.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Repository;

import com.mycompany.dao.PrivRoleDao;
import com.mycompany.exceptions.DuplicateRecordException;
import com.mycompany.model.DummyModel;
import com.mycompany.model.Priv;
import com.mycompany.model.Roles;
import com.mycompany.model.RolesPriv;
import com.mycompany.webapp.pagers.JQueryPager;

@Repository("privRoleDao")
public class PrivRoleDaoHibernate extends GenericDaoHibernate<DummyModel, Long> implements PrivRoleDao {

	public PrivRoleDaoHibernate() {
        super(DummyModel.class);
    }

	public Priv getPriv(Long id) {
		Priv priv = (Priv)getHibernateTemplate().get(Priv.class, id);
		return priv;
		
	}

	public Roles getRole(long id) {
		Roles role = (Roles)getHibernateTemplate().get(Roles.class, id);
		return role;
	}

	public Object savePriv(Priv o) {
		getHibernateTemplate().saveOrUpdate(o);
        // necessary to throw a DataIntegrityViolation and catch it in Manager
        getHibernateTemplate().flush();
        return o;
	}

	public Object saveRole(Roles o) {
			getHibernateTemplate().saveOrUpdate(o);
	        // necessary to throw a DataIntegrityViolation and catch it in Manager
	        getHibernateTemplate().flush();
	        return o;
	}

	public Object saveRolesPriv(RolesPriv o) {
		getHibernateTemplate().saveOrUpdate(o);
        // necessary to throw a DataIntegrityViolation and catch it in Manager
        getHibernateTemplate().flush();
        return o;
		
	}
	
	
	@SuppressWarnings("unchecked")
    public JQueryPager getPrivsCriteria(final JQueryPager paginatedList)
    {
        log.debug("getPrivsCriteria");
        log.debug("paginatedList " + paginatedList);
        
        if (paginatedList.getSortCriterion() == null)
        	paginatedList.setSortCriterion("privId");
    	Map<String,Object> resultMap = getAppManagerCriteria(paginatedList,Priv.class);
    	paginatedList.setList((List)resultMap.get("list"));
    	int count = 0;
    	Integer icount = (Integer)resultMap.get("count");
    	if (icount != null)
    		count = icount.intValue();
    	paginatedList.setTotalNumberOfRows(count);
        
        return paginatedList;
    }
	
	@SuppressWarnings("unchecked")
    public JQueryPager getRolesCriteria(final JQueryPager paginatedList)
    {
        log.debug("getRolesCriteria");
        log.debug("paginatedList " + paginatedList);
        
        if (paginatedList.getSortCriterion() == null)
        	paginatedList.setSortCriterion("roleId");
    	Map<String,Object> resultMap = getAppManagerCriteria(paginatedList,Roles.class);
    	paginatedList.setList((List)resultMap.get("list"));
    	int count = 0;
    	Integer icount = (Integer)resultMap.get("count");
    	if (icount != null)
    		count = icount.intValue();
    	paginatedList.setTotalNumberOfRows(count);
        
        return paginatedList;
    }
	
	@SuppressWarnings("unchecked")
    public List<RolesPriv> getRolesPrivByPriv(final String privNo) {
        List<RolesPriv> myList = new ArrayList<RolesPriv>();
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                String sql = 
                    "select " +
                    "rp.roles_priv_no as rolesPrivNo, " +
                    "rp.activeflag as activeFlag, " +
                    "p.priv_no as privNo, " +
                    "ro.role_no as roleNo, " +
                    "p.priv_id as privId," +
                    "ro.role_id as roleId, " +
                    "ro.role_desc as roleDescription " +
                    "from " +
                    "roles_priv rp " +
                    "join roles ro on ro.role_no = rp.role_no " +
                    "join priv p on p.priv_no = rp.priv_no " +
                    "where p.priv_no=:privNo ";
                
                log.debug("SQL " + sql);
                SQLQuery qry = session.createSQLQuery(sql);
                qry.setString("privNo", privNo);
                qry.addScalar("rolesPrivNo", StandardBasicTypes.LONG);
                qry.addScalar("privId", StandardBasicTypes.STRING);
                qry.addScalar("privNo", StandardBasicTypes.LONG);
                qry.addScalar("roleNo", StandardBasicTypes.LONG);
                qry.addScalar("roleId", StandardBasicTypes.STRING);
                qry.addScalar("activeFlag", StandardBasicTypes.LONG);
                qry.addScalar("roleDescription", StandardBasicTypes.STRING);
                qry.setResultTransformer(Transformers
                        .aliasToBean(RolesPriv.class));
                return qry.list();
            }
        };
        myList = (List) getHibernateTemplate().execute(callback);
        return myList;
    }
	
	@SuppressWarnings("unchecked")
    public List<Roles> getRoleSelect()
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
                        "ro.role_desc as roleDesc " +
                        "from " +
                        "roles ro " +
                        "where ro.activeflag=1";
                log.debug("SQL " + sql);
                SQLQuery qry = session.createSQLQuery(sql);
                qry.addScalar("roleId", StandardBasicTypes.STRING);
                qry.addScalar("roleNo", StandardBasicTypes.LONG);
                qry.addScalar("roletypeid", StandardBasicTypes.LONG);
                qry.addScalar("roleDesc", StandardBasicTypes.STRING);
                qry.setResultTransformer(Transformers
                        .aliasToBean(Roles.class));
                return qry.list();
            }
        };
        myList = (List) getHibernateTemplate().execute(callback);
        return myList;
    }

	public List<Priv> findByPrivId(String privId) {
		return getHibernateTemplate().find("from Priv where UPPER(PRIV_ID)=UPPER(?)", privId);
	}

	public List<Roles> findByRoleId(String roleId) {
		return getHibernateTemplate().find("from Roles where UPPER(ROLE_ID)=UPPER(?)", roleId);
	}
	
}
