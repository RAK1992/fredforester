package com.mycompany.webapp.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.mycompany.exceptions.DuplicateRecordException;
import com.mycompany.model.Appuservalues;
import com.mycompany.service.AppUserValuesManager;
import com.mycompany.webapp.pagers.JQueryPager;
import com.mycompany.webapp.pagers.PagerFactory;
import com.opensymphony.xwork2.Preparable;

public class AppUserValuesAction extends BaseAction implements Preparable {

	private static final long serialVersionUID = -4368293342041532950L;
	
	private Appuservalues appUserValue;
	private List<Appuservalues> appUserValues;
	
	private AppUserValuesManager appUserValuesManager;
	private PagerFactory pagerFactory;
	
	// json error message
	private String gridOperationMessage;
	
	// entity paging
    private Integer rows=0;
    private Integer page=0;
    private String sord;
    private String sidx;
    private String searchField;
    private String searchString;
    private String searchOper;
    private Integer total=0;
    private Integer records=0;
    private String oper;

	public void prepare() throws Exception {
		
		log.debug("entering prepare");
        printParams();
        if (getRequest().getMethod().equalsIgnoreCase("post")) {
            // prevent failures on new
            String id = getRequest().getParameter("id");
            if (!StringUtils.isEmpty(id) && StringUtils.isNumeric(id))
            {
            	appUserValue = appUserValuesManager.get(new Long(id));
            }
        }
        
        this.clearSessionMessages();
        log.debug("leaving prepare");

	}
	
	public String edit()
    {
        log.debug("entering edit");
        
        String id = (String)getRequest().getParameter("id");
        log.debug("Getting appuservalue " + id);
        appUserValue = appUserValuesManager.getAppUserValue(new Long(id));
        
        log.debug("appuservalue " + appUserValue);
        log.debug("leaving edit");
        return SUCCESS;
    }
	
	@SuppressWarnings("unchecked")
    public String list()
    {
        log.debug("started list method");
   
        JQueryPager pagedRequests = null;
        pagedRequests = (JQueryPager)pagerFactory.getPager(PagerFactory.JQUERYTYPE,getRequest());
        log.debug("pagedrequest " + pagedRequests);
        pagedRequests = appUserValuesManager.getAppUserValuesCriteria(pagedRequests);
        appUserValues = (List<Appuservalues>)pagedRequests.getList();
        records = pagedRequests.getTotalNumberOfRows();
        //total = (int)Math.ceil((double)records / (double)pagedRequests.getPageSize());
        total = pagedRequests.getTotalNumberOfPages();
        page = pagedRequests.getPageNumber();
        log.debug("Tot Records " + records);
        log.debug("Num Records " + appUserValues.size());
        log.debug("Page Size " + pagedRequests.getPageSize());
        log.debug("Page Number " + pagedRequests.getPageNumber());
        log.debug("Start " + pagedRequests.getStart());
        log.debug("End " + pagedRequests.getEnd());
        log.debug("ending list method");
        return SUCCESS;
    }
	
	public String gridEditAppUserValue()
    {
        log.debug("updateAppUserValue oper " + oper);
        setAuditData(appUserValue);
        log.debug("AppUserValue " + appUserValue);
        
        
        if (oper.equals("del"))
        {
            return SUCCESS;
        }
        
        if (oper.equals("add"))
        {
        	setAuditData(appUserValue);
        	try
        	{
        	    appUserValuesManager.saveAppUserValue(appUserValue);
        	}
        	catch(DuplicateRecordException dre)
        	{
        		gridOperationMessage = dre.getMessage();
        		return SUCCESS;
        	}
        	gridOperationMessage = "AppUserValue Added";
        }
        return SUCCESS;
    }
	
	public String save()
    {
        log.debug("entering save");
        log.debug("AppUserValue " + appUserValue);
        setAuditData(appUserValue);
        
        try
        {
            appUserValuesManager.saveAppUserValue(appUserValue);
        }
        catch(DuplicateRecordException dre)
    	{
    		gridOperationMessage = dre.getMessage();
    		saveMessage(gridOperationMessage);
    		return SUCCESS;
    	}
        
        saveMessage("AppUserValue Saved");
        
        log.debug("leaving save");
        return SUCCESS;
    }
	
	public void setAuditData(Appuservalues appUserValue)
    {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        appUserValue.setLastUpdate(timestamp);
        String uname = getRequest().getRemoteUser();
        appUserValue.setUserName(uname);
    }
	
	public Appuservalues getAppUserValue() {
		return appUserValue;
	}

	public void setAppUserValue(Appuservalues appUserValue) {
		this.appUserValue = appUserValue;
	}

	public List<Appuservalues> getAppUserValues() {
		return appUserValues;
	}

	public void setAppUserValues(List<Appuservalues> appUserValues) {
		this.appUserValues = appUserValues;
	}

	public void setAppUserValuesManager(AppUserValuesManager appUserValuesManager) {
		this.appUserValuesManager = appUserValuesManager;
	}

	public void setPagerFactory(PagerFactory pagerFactory) {
		this.pagerFactory = pagerFactory;
	}
	
	public String getGridOperationMessage() {
		return gridOperationMessage;
	}

	public void setGridOperationMessage(String gridOperationMessage) {
		this.gridOperationMessage = gridOperationMessage;
	}

	// needed for paging
	public String getOper() {
        return oper;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }
    
    public Integer getTotal() {
        return total;
    }


    public void setTotal(Integer total) {
        this.total = total;
    }


    public Integer getRecords() {
        return records;
    }


    public void setRecords(Integer records) {
        this.records = records;
    }
    
    public Integer getRows() {
        return rows;
    }


    public void setRows(Integer rows) {
        this.rows = rows;
    }


    public String getSord() {
        return sord;
    }


    public void setSord(String sord) {
        this.sord = sord;
    }


    public String getSidx() {
        return sidx;
    }


    public void setSidx(String sidx) {
        this.sidx = sidx;
    }


    public String getSearchField() {
        return searchField;
    }


    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }


    public String getSearchString() {
        return searchString;
    }


    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }
    
    public String getSearchOper() {
        return searchOper;
    }


    public void setSearchOper(String searchOper) {
        this.searchOper = searchOper;
    }

	public Integer getPage() {
    	log.debug("getPage " + page);
        return page;
    }


    public void setPage(Integer page) {
        this.page = page;
        log.debug("setPage " + page);
    }

	

}
