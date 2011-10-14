package com.mycompany.dao.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.mycompany.dao.GenericDao;
import com.mycompany.webapp.pagers.JQueryPager;
import com.mycompany.webapp.pagers.SortOrderEnum;

/**
 * This class serves as the Base class for all other DAOs - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 * <p/>
 * <p>To register this class in your Spring context file, use the following XML.
 * <pre>
 *      &lt;bean id="fooDao" class="com.mycompany.dao.hibernate.GenericDaoHibernate"&gt;
 *          &lt;constructor-arg value="com.mycompany.model.Foo"/&gt;
 *      &lt;/bean&gt;
 * </pre>
 *
 * @author <a href="mailto:bwnoll@gmail.com">Bryan Noll</a>
 * @param <T> a type variable
 * @param <PK> the primary key for that type
 */
public class GenericDaoHibernate<T, PK extends Serializable> implements GenericDao<T, PK> {
    /**
     * Log variable for all child classes. Uses LogFactory.getLog(getClass()) from Commons Logging
     */
    protected final Log log = LogFactory.getLog(getClass());
    private final Class<T> persistentClass;
    private HibernateTemplate hibernateTemplate;
    private SessionFactory sessionFactory;

    /**
     * Constructor that takes in a class to see which type of entity to persist.
     * Use this constructor when subclassing.
     *
     * @param persistentClass the class type you'd like to persist
     */
    public GenericDaoHibernate(final Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    /**
     * Constructor that takes in a class and sessionFactory for easy creation of DAO.
     *
     * @param persistentClass the class type you'd like to persist
     * @param sessionFactory  the pre-configured Hibernate SessionFactory
     */
    public GenericDaoHibernate(final Class<T> persistentClass, SessionFactory sessionFactory) {
        this.persistentClass = persistentClass;
        this.sessionFactory = sessionFactory;
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    public HibernateTemplate getHibernateTemplate() {
        return this.hibernateTemplate;
    }

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    @Autowired
    @Required
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        return hibernateTemplate.loadAll(this.persistentClass);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<T> getAllDistinct() {
        Collection result = new LinkedHashSet(getAll());
        return new ArrayList(result);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public T get(PK id) {
        T entity = (T) hibernateTemplate.get(this.persistentClass, id);

        if (entity == null) {
            log.warn("Uh oh, '" + this.persistentClass + "' object with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(this.persistentClass, id);
        }

        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public boolean exists(PK id) {
        T entity = (T) hibernateTemplate.get(this.persistentClass, id);
        return entity != null;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public T save(T object) {
        return (T) hibernateTemplate.merge(object);
    }

    /**
     * {@inheritDoc}
     */
    public void remove(PK id) {
        hibernateTemplate.delete(this.get(id));
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<T> findByNamedQuery(String queryName, Map<String, Object> queryParams) {
        String[] params = new String[queryParams.size()];
        Object[] values = new Object[queryParams.size()];
        
        int index = 0;
        for (String s : queryParams.keySet()) {
            params[index] = s;
            values[index++] = queryParams.get(s);
        }

        return hibernateTemplate.findByNamedQueryAndNamedParam(queryName, params, values);
    }
    
    
    @SuppressWarnings("unchecked")
    public Map<String,Object> getAppManagerCriteria(final JQueryPager paginatedList,final Class object)
    {
		log.debug("getAppManagerCriteria");
        log.debug("paginatedList " + paginatedList);
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                Criteria criteria = session.createCriteria(object);
                String searchField = paginatedList.getSearchField();
                String searchFor = paginatedList.getSearchFor();
                String orderBy = paginatedList.getSortCriterion();
                
                
                criteria.setFirstResult(paginatedList.getStart());
                criteria.setMaxResults(paginatedList.getPageSize());
                
                if (orderBy != null)
                {
	                if (paginatedList.getSortDirection() == SortOrderEnum.DESCENDING)
	                    criteria.addOrder(Order.desc(orderBy));
	                else
	                    criteria.addOrder(Order.asc(orderBy));
                }
                
                boolean search = false;
                if (searchField != null && searchFor != null)
                {
                	search = true;
                    log.debug("Setting search option " + searchField);
                    String searchOper = paginatedList.getSearchOper();
                    if (searchOper == null)
                        searchOper = "bw";
                    
                    if (searchField.equals("activeFlag"))
                    {
                        if (!StringUtils.isNumeric(searchFor))
                            searchFor="1";
                    }
                    
                    if (searchOper.equalsIgnoreCase("bw"))
                    {
                        searchFor = searchFor + "%";
                        criteria.add(Restrictions.like(searchField,searchFor).ignoreCase());
                    }
                    
                    if (searchOper.equalsIgnoreCase("cn"))
                    {
                        searchFor = "%" + searchFor + "%";
                        criteria.add(Restrictions.like(searchField,searchFor).ignoreCase());
                    }
                    
                    if (searchOper.equalsIgnoreCase("eq"))
                    {
                        if (searchField.equals("activeFlag"))
                            criteria.add(Restrictions.eq(searchField,new Long(searchFor)));
                        else
                            criteria.add(Restrictions.eq(searchField,searchFor).ignoreCase());
                    }
                }
                
                Map<String,Object> resultMap = new HashMap<String,Object>();
                List recList = criteria.list();
                resultMap.put("list", recList);
                int count = 0;
                if (search)
                {
                	criteria.setProjection(Projections.rowCount());
                    List countList = criteria.list();
                    if (!countList.isEmpty())
                        count = ((Long) countList.get(0)).intValue();
                    resultMap.put("count",new Integer(count));
                }
                else
                {
                	Criteria criteriaC = session.createCriteria(object);
                	criteriaC.setProjection(Projections.rowCount());
                    List countList = criteriaC.list();
                    if (!countList.isEmpty())
                        count = ((Long) countList.get(0)).intValue();
                    resultMap.put("count",new Integer(count));
                	
                }
                
                return resultMap;
                
                //return criteria.list();
            }
        };
        
        try
        {
            Map<String,Object> resultMap = getHibernateTemplate().execute(callback);
            return resultMap;
        }
        catch(Exception e)
        {
        	log.error("Get Crtiteria Error",e);
        	List recList = new ArrayList();
        	int c = 0;
        	Map<String,Object> resultMap = new HashMap<String,Object>();
        	resultMap.put("list", recList);
        	resultMap.put("count",new Integer(c));
        	return resultMap;
        	
        }
        
    }
}
