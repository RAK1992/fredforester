package com.mycompany.webapp.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.mycompany.exceptions.DuplicateRecordException;
import com.mycompany.model.Roles;
import com.mycompany.service.PrivRoleManager;
import com.mycompany.webapp.pagers.JQueryPager;
import com.mycompany.webapp.pagers.PagerFactory;
import com.opensymphony.xwork2.Preparable;

public class RolesAction extends BaseAction implements Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -789870517742883166L;
	
	
	private Roles role;
	private List<Roles> roles;
	
	private PrivRoleManager privRoleManager;
	private PagerFactory pagerFactory;
	
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
            	role = privRoleManager.getRole(new Long(id));
            }
        }
        this.clearSessionMessages();
        log.debug("leaving prepare");
		
	}
	
	public String edit()
    {
        log.debug("entering edit");
        
        String id = (String)getRequest().getParameter("id");
        log.debug("Getting roleno " + id);
        role = privRoleManager.getRole(new Long(id));
        
        log.debug("Role " + role);
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
        pagedRequests = privRoleManager.getRolesCriteria(pagedRequests);
        roles = (List<Roles>)pagedRequests.getList();
        records = pagedRequests.getTotalNumberOfRows();
        total = (int)Math.ceil((double)records / (double)pagedRequests.getPageSize());
        page = pagedRequests.getPageNumber();
        log.debug("Tot Records " + records);
        log.debug("Num Records " + roles.size());
        log.debug("Page Size " + pagedRequests.getPageSize());
        log.debug("Page Number " + pagedRequests.getPageNumber());
        log.debug("Start " + pagedRequests.getStart());
        log.debug("End " + pagedRequests.getEnd());
        log.debug("ending list method");
        return SUCCESS;
    }
	
	public String gridEditRole()
    {
        log.debug("updateRole oper " + oper);
        setAuditData(role);
        
        if (oper.equals("del"))
        {
            return SUCCESS;
        }
        
        if (oper.equals("add"))
        {
        	role.setAppAccessrequestmethodid(new Long("0"));
        	try
        	{
        	    privRoleManager.saveRole(role);
        	}
        	catch(DuplicateRecordException dre)
        	{
        		gridOperationMessage = dre.getMessage();
        		return SUCCESS;
        	}
        	gridOperationMessage = "Role Added";
        }
        return SUCCESS;
    }
	
	public String save()
    {
        log.debug("entering save");
        log.debug("Role " + role);
        setAuditData(role);
        
        try
        {
            privRoleManager.saveRole(role);
        }
        catch(DuplicateRecordException dre)
    	{
    		gridOperationMessage = dre.getMessage();
    		saveMessage(gridOperationMessage);
    		return SUCCESS;
    	}
        
        saveMessage("Role Saved");
        
        log.debug("leaving save");
        return SUCCESS;
    }
	
	public void setAuditData(Roles role)
    {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        role.setLastUpdate(timestamp);
        String uname = getRequest().getRemoteUser();
        role.setUserName(uname);
    }

	
	
	public void setPagerFactory(PagerFactory pagerFactory) {
		this.pagerFactory = pagerFactory;
	}

	public void setPrivRoleManager(PrivRoleManager privRoleManager) {
		this.privRoleManager = privRoleManager;
	}
	
	public void setRole(Roles role) {
		this.role = role;
	}
	
	public Roles getRole() {
		return this.role;
	}
	
	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}
	
	public List<Roles> getRoles() {
		return this.roles;
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
