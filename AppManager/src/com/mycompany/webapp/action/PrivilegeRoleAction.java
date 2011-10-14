package com.mycompany.webapp.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.mycompany.exceptions.DuplicateRecordException;
import com.mycompany.model.Priv;
import com.mycompany.model.Roles;
import com.mycompany.model.RolesPriv;
import com.mycompany.service.PrivRoleManager;
import com.mycompany.webapp.pagers.JQueryPager;
import com.mycompany.webapp.pagers.PagerFactory;
import com.opensymphony.xwork2.Preparable;

public class PrivilegeRoleAction extends BaseAction implements Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -789870517742883166L;
	
	
	// priv tab
	private Priv privilege;
	private List<Priv> privileges;
	
	// dao manager
	private PrivRoleManager privRoleManager;
	
	
	// jquery paginator for the privilege list
	private PagerFactory pagerFactory;
	
	// jsonized errors
	private String gridOperationMessage;
	
	// rolesPriv tab
	private String privNo;
	private List<RolesPriv> rolesPrivList;
	private RolesPriv rolesPriv;
	private List<Roles> rolesSelect;
	
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
            	privilege = privRoleManager.getPriv(new Long(id));
            }
        }
        
        this.clearSessionMessages();
        
        log.debug("leaving prepare");
		
	}
	
	public String edit()
    {
        log.debug("entering edit");
        
        String id = (String)getRequest().getParameter("id");
        log.debug("Getting privno " + id);
        privilege = privRoleManager.getPriv(new Long(id));
        //privs = privRoleManager.get(priv.getPrivNo().toString());
        
        log.debug("Priv " + privilege);
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
        pagedRequests = privRoleManager.getPrivsCriteria(pagedRequests);
        privileges = (List<Priv>)pagedRequests.getList();
        records = pagedRequests.getTotalNumberOfRows();
        //total = (int)Math.ceil((double)records / (double)pagedRequests.getPageSize());
        total = pagedRequests.getTotalNumberOfPages();
        page = pagedRequests.getPageNumber();
        log.debug("Tot Records " + records);
        log.debug("Num Records " + privileges.size());
        log.debug("Page Size " + pagedRequests.getPageSize());
        log.debug("Page Number " + pagedRequests.getPageNumber());
        log.debug("Start " + pagedRequests.getStart());
        log.debug("End " + pagedRequests.getEnd());
        log.debug("ending list method");
        return SUCCESS;
    }
	
	public String gridEditPrivilege()
    {
        log.debug("gridEditPrivilege oper " + oper);
        setAuditData(privilege);
        log.debug("gridEditPrivilege app  " + privilege);
        
        if (oper.equals("del"))
        {
            return SUCCESS;
        }
        
        if (oper.equals("add"))
        {
        	privilege.setIsforcedredirect(new Long("0"));
        	try
        	{
        	    privRoleManager.savePriv(privilege);
        	}
        	catch(DuplicateRecordException dre)
        	{
        		gridOperationMessage = dre.getMessage();
        		return SUCCESS;
        	}
        	gridOperationMessage = "Privilege Added";
        }
        return SUCCESS;
    }
	
	public String save()
    {
        log.debug("entering save");
        log.debug("Privilege " + privilege);
        setAuditData(privilege);
        
        try
    	{
            privRoleManager.savePriv(privilege);
    	}
        catch(DuplicateRecordException dre)
    	{
    		gridOperationMessage = dre.getMessage();
    		saveMessage(gridOperationMessage);
    		return SUCCESS;
    	}
    		
        saveMessage("Privilege Saved");
        
        log.debug("leaving save");
        return SUCCESS;
    }
	
	// rolesPriv Tab
	
	// return rolesPrivs to the RolesPriv tab
	public String rolesPrivList()
	{
		log.debug("start rolesPrivList for pirvNo " + privNo);
		if (privNo == null || privNo.trim().length() == 0)
			rolesPrivList =  new ArrayList<RolesPriv>();
        else        
        	rolesPrivList = privRoleManager.getRolesPrivByPriv(privNo);
        return SUCCESS;
	}
	
	public String rolesSelect()
	{
		rolesSelect = privRoleManager.getRoleSelect();
        if (rolesSelect == null)
        	rolesSelect = new ArrayList<Roles>();
        log.debug(rolesSelect.size() + " rolesSelectRecords");
        return SUCCESS;
	}
	
	public String gridEditRolesPriv()
	{
		log.debug("gridEditRolesPriv oper " + oper);
        log.debug("gridEditRolesPriv privNo " + privNo);
        log.debug("gridEditRolesPriv rolesPriv " + rolesPriv);
        
        if (!StringUtils.isNumeric(privNo))
        {
        	gridOperationMessage = "Invalid privNo";
            return SUCCESS;
        }
        
        setAuditData(rolesPriv);
        
        if (oper.equals("add"))
        {
        	rolesPriv.setPrivNo(new Long(privNo));
        	
        	log.debug("Adding rolesPriv " + rolesPriv);
        	try
        	{
        	    privRoleManager.saveRolesPriv(rolesPriv);
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
            rolesPriv.setPrivNo(new Long(privNo));
            rolesPriv.setRolesPrivNo(new Long(id));
            log.debug("Updating rolesPriv " + rolesPriv);
            try
            {
                privRoleManager.saveRolesPriv(rolesPriv);
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
	
	public void setAuditData(Priv priv)
    {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        priv.setLastUpdate(timestamp);
        String uname = getRequest().getRemoteUser();
        priv.setUserName(uname);
    }
	
	public void setAuditData(RolesPriv rolesPriv)
    {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        rolesPriv.setLastUpdate(timestamp);
        String uname = getRequest().getRemoteUser();
        rolesPriv.setUserName(uname);
    }

	public void setPagerFactory(PagerFactory pagerFactory) {
		this.pagerFactory = pagerFactory;
	}
	
	public void setPrivRoleManager(PrivRoleManager privRoleManager) {
		this.privRoleManager = privRoleManager;
	}
	
	public void setPrivilege(Priv privilege) {
		this.privilege = privilege;
	}
	
	public Priv getPrivilege() {
		return this.privilege;
	}
	
	public void setPrivileges(List<Priv> privileges) {
		this.privileges = privileges;
	}
	
	public List<Priv> getPrivileges() {
		return this.privileges;
	}
	
	
	// rolespriv tab
	
	public String getPrivNo() {
		return privNo;
	}

	public void setPrivNo(String privNo) {
		this.privNo = privNo;
	}

	public List<RolesPriv> getRolesPrivList() {
		return rolesPrivList;
	}

	public void setRolesPrivList(List<RolesPriv> rolesPrivList) {
		this.rolesPrivList = rolesPrivList;
	}

	public RolesPriv getRolesPriv() {
		return rolesPriv;
	}

	public void setRolesPriv(RolesPriv rolesPriv) {
		this.rolesPriv = rolesPriv;
	}

	public List<Roles> getRolesSelect() {
		return rolesSelect;
	}

	public void setRolesSelect(List<Roles> rolesSelect) {
		this.rolesSelect = rolesSelect;
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
