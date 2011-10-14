package com.mycompany.webapp.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

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
import com.mycompany.webapp.pagers.PagerFactory;
import com.opensymphony.xwork2.Preparable;


public class ApplicationAction extends BaseAction implements Preparable {

   
    private static final long serialVersionUID = 1814088117394360144L;
    
    private IamManager IamManager;
    private List<Application> applications;
    private Application application;
    private PagerFactory pagerFactory;
    	
    private String gridOperationMessage;
    
    private List<Applicationpriv> applicationprivs;
    private Applicationpriv applicationpriv;
    
    // roles tab
    private List<Roles> rolesList;
    private List<Priv> appPrivList;
    private RolesPriv rolePriv;
    private List<Approleuservalues> appRoleUserValues;
    private List<Applicationpriv> appPrivRoleSelect;
    private List<Appuservalues> appuservaluesList;
    
    private Approleuservalues approleuservalue;
    private Approleuservalues aruvquery;
    //private String roleNo;
    private String appuservalueid;
    
    
    // priv tab
    private List<Priv> privSelect;
    private List<Priv> privSelectOptions;
    private String applicationId;
    //private String privNo;
    private String oper;
    //private String activeFlag;
    
    
    // app owners tab
   
    private Applicationowner applicationowner;
    private List<Applicationowner> appOwnerList;
    
    // app role owner
    private Approleowner approleowner;
    private List<Approleowner> appRoleOwnerList;
    private List<RolesPriv> appPrivRolesList;
    
    
    // application paging
    private Integer             rows=0;
    private Integer             page=0;
    private String              sord;
    private String              sidx;
    private String              searchField;
    private String              searchString;
    private String              searchOper;
    private Integer             total            = 0;
    private Integer             records          = 0;

    
    public void prepare() throws Exception {
        log.debug("entering prepare");
        printParams();
        if (getRequest().getMethod().equalsIgnoreCase("post")) {
            // prevent failures on new
            String id = getRequest().getParameter("id");
            if (!StringUtils.isEmpty(id) && StringUtils.isNumeric(id))
            {
                application = IamManager.getApplication(new Long(id));
            }
        }
        
        log.debug("leaving prepare");
    }
    
    public String loadPrivSelectOptions()
    {
    	privSelectOptions = (List<Priv>)getSession().getServletContext().getAttribute("PRIV_SELECT");
    	if (privSelectOptions == null || privSelectOptions.size() == 0)
    		privSelectOptions = IamManager.getPrivSelect();
    	return SUCCESS;
    }

    @SuppressWarnings("unchecked")
    public String list()
    {
        log.debug("started list method");
        //applications = IamManager.getApplications();
   
        JQueryPager pagedRequests = null;
        pagedRequests = (JQueryPager)pagerFactory.getPager(PagerFactory.JQUERYTYPE,getRequest());
        log.debug("pagedrequest " + pagedRequests);
        //pagedRequests = IamManager.getApplications(pagedRequests);
        pagedRequests = IamManager.getApplicationsCriteria(pagedRequests);
        applications = (List<Application>)pagedRequests.getList();
        records = pagedRequests.getTotalNumberOfRows();
        //total = (int)Math.ceil((double)records / (double)pagedRequests.getPageSize());
        total = pagedRequests.getTotalNumberOfPages();
        page = pagedRequests.getPageNumber();
        log.debug("Tot Records " + records);
        log.debug("Num Records " + applications.size());
        log.debug("Page Size " + pagedRequests.getPageSize());
        log.debug("Page Number " + pagedRequests.getPageNumber());
        log.debug("Start " + pagedRequests.getStart());
        log.debug("End " + pagedRequests.getEnd());
        log.debug("ending list method");
        return SUCCESS;
    }
    
    
    
    public String edit()
    {
        log.debug("entering edit");
        
        String id = (String)getRequest().getParameter("id");
        log.debug("Getting appid " + id);
        application = IamManager.getApplication(new Long(id));
        applicationprivs = IamManager.getAppPrivListByApp(application.getApplicationid().toString());
        
        log.debug("Application " + application);
        log.debug("leaving edit");
        return SUCCESS;
    }
    
    
    public String gridEditAppPrivRoleUserValues()
    {
        log.debug("updateAppUserValues oper " + oper);
        log.debug("updateAppUserValues approleuservalue " + approleuservalue);
        
        if (!StringUtils.isNumeric(applicationId))
        {
        	gridOperationMessage = "Invalid Application Id";
            return SUCCESS;
        }
        
        if (oper.equals("add"))
        {
        	approleuservalue.setApplicationid(new Long(applicationId));
        	//approleuservalue.setActiveFlag(new Long(1));
            setAuditData(approleuservalue);
            log.debug("Adding userValue " + approleuservalue);
            try
            {
                IamManager.saveAppUserValues(approleuservalue);
            }
            catch(DuplicateRecordException dre)
        	{
        		gridOperationMessage = dre.getMessage();
        		return SUCCESS;
        	}
        }
        
        if (oper.equals("del"))
        {
        }
        
        if (oper.equals("edit"))
        {
        	approleuservalue.setApplicationid(new Long(applicationId));
        	String id = getRequest().getParameter("id");
            if (!StringUtils.isNumeric(id))
            {
            	gridOperationMessage = "Invalid approleuservalueid Id";
                return SUCCESS;
            }
            
            approleuservalue.setId(new Long(id));
            setAuditData(approleuservalue);
            log.debug("Updating approleuservalue " + approleuservalue);
        	try
            {
                IamManager.saveAppUserValues(approleuservalue);
            }
            catch(DuplicateRecordException dre)
        	{
        		gridOperationMessage = dre.getMessage();
        		return SUCCESS;
        	}
        }
        
        gridOperationMessage = "Record Saved";
        return SUCCESS;
    }
    
    public String gridEditAppPrivs()
    {
        log.debug("gridEditAppPrivs oper " + oper);
        log.debug("gridEditAppPrivs applicationpriv " + applicationpriv);
        
        if (!StringUtils.isNumeric(applicationId))
        {
        	gridOperationMessage = "Invalid Application Id";
            return SUCCESS;
        }
        
        if (oper.equals("add"))
        {
        	applicationpriv.setApplicationid(new Long(applicationId));
        	applicationpriv.setActiveFlag(new Long(1));
        	setAuditData(applicationpriv);
        	log.debug("Adding Priv " + applicationpriv);
        	try
        	{
                IamManager.saveAppPriv(applicationpriv);
        	}
            catch(DuplicateRecordException dre)
        	{
        		gridOperationMessage = dre.getMessage();
        		return SUCCESS;
        	}
        }
        
        if (oper.equals("edit"))
        {
            String id = getRequest().getParameter("id");
            if (!StringUtils.isNumeric(id))
            {
            	gridOperationMessage = "Invalid Application Priv Id";
                return SUCCESS;
            }
            applicationpriv.setApplicationid(new Long(applicationId));
            applicationpriv.setApplicationprivid(new Long(id));
            setAuditData(applicationpriv);
            log.debug("Updating Priv " + applicationpriv);
            try
            {
                IamManager.saveAppPriv(applicationpriv);
            }
            catch(DuplicateRecordException dre)
        	{
        		gridOperationMessage = dre.getMessage();
        		return SUCCESS;
        	}
        }
        gridOperationMessage = "Record Saved";
        return SUCCESS;
    }
    
    

	public String gridEditAppOwners()
    {
        log.debug("updateAppOwners oper " + oper);
        log.debug("updateAppOwners bean " + applicationowner);
        
        if (!StringUtils.isNumeric(applicationId))
        {
        	gridOperationMessage = "Invalid Application Id";
            return SUCCESS;
        }
        
        if (oper.equals("del"))
        {
        }
        
        applicationowner.setApplicationid(new Long(applicationId));
        
        if (oper.equals("add"))
        {
        	applicationowner.setActiveFlag(new Long(1));
            setAuditData(applicationowner);
            log.debug("Adding appOwner " + applicationowner);
            try
            {
            	IamManager.saveAppOwner(applicationowner);
            }
            catch(DuplicateRecordException dre)
        	{
        		gridOperationMessage = dre.getMessage();
        		return SUCCESS;
        	}
        }
        
        if (oper.equals("edit"))
        {
            String id = getRequest().getParameter("id");
            if (!StringUtils.isNumeric(id))
            {
                gridOperationMessage = "Invalid Application Id";
                return SUCCESS;
            }
            applicationowner.setApplicationownid(new Long(id));
            //applicationowner.setActiveFlag(new Long(activeFlag));
            setAuditData(applicationowner);
            log.debug("Updating appOwner " + applicationowner);
            try
            {
            	IamManager.saveAppOwner(applicationowner);
            }
            catch(DuplicateRecordException dre)
        	{
        		gridOperationMessage = dre.getMessage();
        		return SUCCESS;
        	}
        }
        gridOperationMessage = "Record Saved";
        return SUCCESS;
    }
    
    public String gridEditAppRoleOwners()
    {
        log.debug("updateAppRoleOwners oper " + oper);
        log.debug("updateAppRoleOwners bean " + approleowner);
        
        
        	
        if (!StringUtils.isNumeric(applicationId))
        {
        	gridOperationMessage = "Invalid Application Id";
            return SUCCESS;
        }
        
        if (oper.equals("del"))
        	// to support this we would have to read the appowner record then change the status and update.
        	return SUCCESS;
        
        approleowner.setApplicationid(new Long(applicationId));
        
        if (oper.equals("add"))
        {
        	approleowner.setActiveFlag(new Long(1));
            setAuditData(approleowner);
            log.debug("Adding appRoleOwner " + approleowner);
            try
            {
                IamManager.saveAppRoleOwner(approleowner);
            }
            catch(DuplicateRecordException dre)
        	{
        		gridOperationMessage = dre.getMessage();
        		return SUCCESS;
        	}
        }
        
        if (oper.equals("edit"))
        {
            String id = getRequest().getParameter("id");
            if (!StringUtils.isNumeric(id))
            {
            	gridOperationMessage = "Invalid Approleownid Id";
                return SUCCESS;
            }
            approleowner.setApproleownid(new Long(id));
            //applicationowner.setActiveFlag(new Long(activeFlag));
            setAuditData(approleowner);
            log.debug("Updating appRoleOwner " + approleowner);
            try
            {
                IamManager.saveAppRoleOwner(approleowner);
            }
            catch(DuplicateRecordException dre)
        	{
        		gridOperationMessage = dre.getMessage();
        		return SUCCESS;
        	}
        }
        gridOperationMessage = "Record Saved";
        return SUCCESS;
    }
    
    public String save()
    {
        log.debug("entering save");
        log.debug("Application " + application);
        setAuditData(application);
        
        IamManager.saveApp(application);
        saveMessage("Application Saved");
        
        log.debug("leaving save");
        return SUCCESS;
    }
    
    public String updateApplication()
    {
        log.debug("updateApplication oper " + oper);
        log.debug("updateApplication app  " + application);
        
        if (oper.equals("del"))
        {
            //String id = getRequest().getParameter("id");
            //IamManager.remove(Application.class, new Long(id));
            return SUCCESS;
        }
        
        if (oper.equals("add"))
        {
        	application.setAppIsintranetpublicflag(new Long("0"));
        	IamManager.saveApp(application);
        	saveMessage("Application Added");
        }
        return SUCCESS;
    }
    
    
    public String appPrivGridList()
    {
        if (applicationId == null || applicationId.trim().length() == 0)
            applicationprivs =  new ArrayList<Applicationpriv>();
        else        
            applicationprivs = IamManager.getAppPrivListByApp(applicationId);
        return SUCCESS;
    }
    
    public String appPrivSelect()
    {
        if (applicationId == null || applicationId.trim().length() == 0)
            appPrivList =  new ArrayList<Priv>();
        else        
            appPrivList = IamManager.getPrivByApp(applicationId);
        return SUCCESS;
    }
    
    public String appPrivRoleSelect()
    {
    	log.debug("appPrivRoleSelect query " + aruvquery);
    	
    	appPrivRolesList = new ArrayList<RolesPriv>();
    	
    	if (aruvquery != null)
        {
    		String privNo = "";
    		String appId = "";
        	Long l = aruvquery.getApplicationid();
        	appId = ((l == null) ? "" : l.toString());
        	l = aruvquery.getPrivNo();
        	privNo = ((l == null) ? "" : l.toString());
        	log.debug("appPrivRoleSelect app priv " + appId + ":" + privNo);
            appPrivRolesList = IamManager.getAppPrivRolesByApp(appId,privNo);
        }
        return SUCCESS;
    }
    
    public String getCUVSelect()
    {
        appuservaluesList = IamManager.getCUVSelect();
        if (appuservaluesList == null)
            appuservaluesList = new ArrayList<Appuservalues>();
        log.debug(appuservaluesList.size() + " uservaluerecords");
        return SUCCESS;
    }
    
    
    public String appPrivRoleUserValuesList()
    {
        if (applicationId == null || applicationId.trim().length() == 0)
            appRoleUserValues =  new ArrayList<Approleuservalues>();
        else        
            appRoleUserValues = IamManager.getAppRoleUserValuesByApp(applicationId);
        return SUCCESS;
    }
    
    public String appOwnersList()
    {
        if (applicationId == null || applicationId.trim().length() == 0)
        	appOwnerList =  new ArrayList<Applicationowner>();
        else        
        	appOwnerList = IamManager.getApplicationOwners(applicationId);
        return SUCCESS;
    }
    
    public String appRoleOwnersList()
    {
        if (applicationId == null || applicationId.trim().length() == 0)
        	appRoleOwnerList =  new ArrayList<Approleowner>();
        else        
        	appRoleOwnerList = IamManager.getApplicationRoleOwners(applicationId);
        return SUCCESS;
    }
    
    
    
    public void setAuditData(Application app)
    {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        app.setLastUpdate(timestamp);
        String uname = getRequest().getRemoteUser();
        app.setUserName(uname);
    }
    
    public void setAuditData(Applicationpriv app)
    {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        app.setLastUpdate(timestamp);
        String uname = getRequest().getRemoteUser();
        app.setUserName(uname);
    }
    
    public void setAuditData(Approleuservalues app)
    {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        app.setLastUpdate(timestamp);
        String uname = getRequest().getRemoteUser();
        app.setUserName(uname);
    }
    
    public void setAuditData(Applicationowner app)
    {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        app.setLastUpdate(timestamp);
        String uname = getRequest().getRemoteUser();
        app.setUserName(uname);
    }
    
    public void setAuditData(Approleowner app)
    {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        app.setLastUpdate(timestamp);
        String uname = getRequest().getRemoteUser();
        app.setUserName(uname);
    }
    
    public String privSelect()
    {
        privSelect = IamManager.getPrivSelect();
        return SUCCESS;
    }
    
    public IamManager getIamManager() {
        return IamManager;
    }


    public void setIamManager(IamManager iamManager) {
        IamManager = iamManager;
    }
    
    public List<Application> getApplications() {
        return applications;
    }


    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }


    public Integer getRows() {
        return rows;
    }


    public void setRows(Integer rows) {
        this.rows = rows;
    }


    public Integer getPage() {
        return page;
    }


    public void setPage(Integer page) {
        this.page = page;
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


    public void setPagerFactory(PagerFactory pagerFactory) {
		this.pagerFactory = pagerFactory;
	}

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public List<Applicationpriv> getApplicationprivs() {
        return applicationprivs;
    }

    public void setApplicationprivs(List<Applicationpriv> applicationprivs) {
        this.applicationprivs = applicationprivs;
    }

    public Applicationpriv getApplicationpriv() {
        return applicationpriv;
    }

    public void setApplicationpriv(Applicationpriv applicationpriv) {
        this.applicationpriv = applicationpriv;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getOper() {
        return oper;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }

    public List<Priv> getPrivSelect() {
        return privSelect;
    }

    public void setPrivSelect(List<Priv> privSelect) {
        this.privSelect = privSelect;
    }

    public List<Priv> getPrivSelectOptions() {
        return privSelectOptions;
    }

    public void setPrivSelectOptions(List<Priv> privSelectOptions) {
        this.privSelectOptions = privSelectOptions;
    }


    public List<Roles> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<Roles> rolesList) {
        this.rolesList = rolesList;
    }

    public RolesPriv getRolePriv() {
        return rolePriv;
    }

    public void setRolePriv(RolesPriv rolePriv) {
        this.rolePriv = rolePriv;
    }

    public List<Approleuservalues> getAppRoleUserValues() {
        return appRoleUserValues;
    }

    public void setAppRoleUserValues(List<Approleuservalues> appRoleUserValues) {
        this.appRoleUserValues = appRoleUserValues;
    }

    public List<Applicationpriv> getAppPrivRoleSelect() {
        return appPrivRoleSelect;
    }

    public void setAppPrivRoleSelect(List<Applicationpriv> appPrivRoleSelect) {
        this.appPrivRoleSelect = appPrivRoleSelect;
    }

    public List<Priv> getAppPrivList() {
        return appPrivList;
    }

    public void setAppPrivList(List<Priv> appPrivList) {
        this.appPrivList = appPrivList;
    }

    public List<Appuservalues> getAppuservaluesList() {
        return appuservaluesList;
    }

    public void setAppuservaluesList(List<Appuservalues> appuservaluesList) {
        this.appuservaluesList = appuservaluesList;
    }

   
	public List<Applicationowner> getAppOwnerList() {
		return appOwnerList;
	}

	public void setAppOwnerList(List<Applicationowner> appOwnerList) {
		this.appOwnerList = appOwnerList;
	}

	public Applicationowner getApplicationowner() {
		return applicationowner;
	}

	public void setApplicationowner(Applicationowner applicationowner) {
		this.applicationowner = applicationowner;
	}

	public Approleowner getApproleowner() {
		return approleowner;
	}

	public void setApproleowner(Approleowner approleowner) {
		this.approleowner = approleowner;
	}

	public List<Approleowner> getAppRoleOwnerList() {
		return appRoleOwnerList;
	}

	public void setAppRoleOwnerList(List<Approleowner> appRoleOwnerList) {
		this.appRoleOwnerList = appRoleOwnerList;
	}

	public List<RolesPriv> getAppPrivRolesList() {
		return appPrivRolesList;
	}

	public void setAppPrivRolesList(List<RolesPriv> appPrivRolesList) {
		this.appPrivRolesList = appPrivRolesList;
	}
	
	public Approleuservalues getApproleuservalue() {
		return approleuservalue;
	}

	public void setApproleuservalue(Approleuservalues approleuservalue) {
		this.approleuservalue = approleuservalue;
	}

	public String getAppuservalueid() {
		return appuservalueid;
	}

	public void setAppuservalueid(String appuservalueid) {
		this.appuservalueid = appuservalueid;
	}

	public void setAruvquery(Approleuservalues aruvquery) {
		log.debug("setAruvquery " + aruvquery);
		this.aruvquery = aruvquery;
	}
	
	public Approleuservalues getAruvquery() {
		log.debug("getAruvquery " + aruvquery);
		return this.aruvquery;
	}

	public String getGridOperationMessage() {
		return gridOperationMessage;
	}

	public void setGridOperationMessage(String gridOperationMessage) {
		this.gridOperationMessage = gridOperationMessage;
	}
	
	

	
}
