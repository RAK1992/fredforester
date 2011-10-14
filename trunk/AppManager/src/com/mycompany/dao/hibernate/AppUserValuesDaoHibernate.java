package com.mycompany.dao.hibernate;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mycompany.dao.AppUserValuesDao;
import com.mycompany.model.Appuservalues;
import com.mycompany.model.DummyModel;
import com.mycompany.webapp.pagers.JQueryPager;

// the manager needs to have a setAppUserValuesDao method.
@Repository("appUserValuesDao")
public class AppUserValuesDaoHibernate extends GenericDaoHibernate<DummyModel, Long> implements AppUserValuesDao {
 
	public AppUserValuesDaoHibernate()
	{
		super(DummyModel.class);
	}
	
	public JQueryPager getAppUserValuesCriteria(JQueryPager paginatedList) {
		
		log.debug("getAppUserValuesCriteria");
        log.debug("paginatedList " + paginatedList);
        
        if (paginatedList.getSortCriterion() == null)
    	    paginatedList.setSortCriterion("appuservaluefieldname");
    	Map<String,Object> resultMap = getAppManagerCriteria(paginatedList,Appuservalues.class);
    	paginatedList.setList((List)resultMap.get("list"));
    	int count = 0;
    	Integer icount = (Integer)resultMap.get("count");
    	if (icount != null)
    		count = icount.intValue();
    	paginatedList.setTotalNumberOfRows(count);
        return paginatedList;
		
	}

	public Appuservalues getAppUserValue(long id) {
		Appuservalues appUserValue = (Appuservalues)getHibernateTemplate().get(Appuservalues.class, id);
		return appUserValue;
	}

	public Object saveAppUserValue(Appuservalues o) {
		getHibernateTemplate().saveOrUpdate(o);
        // necessary to throw a DataIntegrityViolation and catch it in Manager
        getHibernateTemplate().flush();
        return o;
	}
	
}
