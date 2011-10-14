package com.mycompany.webapp.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.EmailValidator;

import com.mycompany.exceptions.DuplicateRecordException;
import com.mycompany.model.Person;
import com.mycompany.service.PersonManager;
import com.mycompany.webapp.pagers.JQueryPager;
import com.mycompany.webapp.pagers.PagerFactory;
import com.opensymphony.xwork2.Preparable;


public class PersonAction extends BaseAction implements Preparable {
	
    private PersonManager personManager;
    private PagerFactory pagerFactory;
    private List<Person> persons;
    private Person person;
    private String id;
    
    private String gridOperationMessage;
    
    // entity paging
    protected Integer rows=0;
    protected Integer page=0;
    protected String sord;
    protected String sidx;
    protected String searchField;
    protected String searchString;
    protected String searchOper;
    protected Integer total=0;
    protected Integer records=0;
    protected String oper;

    public void setPersonManager(PersonManager personManager) {
        this.personManager = personManager;
    }

    public void setPagerFactory(PagerFactory pagerFactory) {
		this.pagerFactory = pagerFactory;
	}
    
    public void prepare() throws Exception {
    	clearSessionMessages();
	}

	public String list() 
    {
    	
    	try
    	{
	    	log.debug("started list method");
	        JQueryPager pagedRequests = null;
	        pagedRequests = (JQueryPager)pagerFactory.getPager(PagerFactory.JQUERYTYPE,getRequest());
	        log.debug("pagedrequest " + pagedRequests);
	        pagedRequests = personManager.getPersonCriteria(pagedRequests);
	        persons = (List<Person>)pagedRequests.getList();
	        records = pagedRequests.getTotalNumberOfRows();
	        //total = (int)Math.ceil((double)records / (double)pagedRequests.getPageSize());
	        total = pagedRequests.getTotalNumberOfPages();
	        page = pagedRequests.getPageNumber();
	        log.debug("Tot Records " + records);
	        log.debug("Num Records " + persons.size());
	        log.debug("Page Size " + pagedRequests.getPageSize());
	        log.debug("Page Number " + pagedRequests.getPageNumber());
	        log.debug("Start " + pagedRequests.getStart());
	        log.debug("End " + pagedRequests.getEnd());
	        log.debug("ending list method");
    	}  
        catch(Exception e)
        {
        	log.error("List Error", e);
        }
        return SUCCESS;
    }

    

    public void setId(String id) {
        this.id = id;
    }
    
    public String getId(String id) {
        return this.id;
    }

    public List getPersons() {
        return persons;
    }
    
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String delete() {
        personManager.remove(person.getId());
        saveMessage(getText("person.deleted"));

        return SUCCESS;
    }

    public String edit() {
        if (id != null) {
            person = personManager.get(new Long(id));
        } else {
            person = new Person();
        }

        return SUCCESS;
    }

    // called from Grid Add Form
    public String gridEditPerson()
    {
    	log.debug("updatePerson oper " + oper);
        log.debug("Person " + person);
        
        if (oper.equals("del"))
        {
        	gridOperationMessage = "Operation Not Supported";
            return SUCCESS;
        }
        
        if (oper.equals("add") || oper.equals("edit"))
        {
        	String error = isValidPerson();
        	if (!error.equalsIgnoreCase(SUCCESS))
        	{
        		gridOperationMessage = error;
        		return SUCCESS;
        	}
        	try
        	{
        	    personManager.savePerson(person);
        	}
        	catch(DuplicateRecordException dre)
        	{
        		gridOperationMessage = dre.getMessage();
        		return SUCCESS;
        	}
        	gridOperationMessage = "Person Saved";
        }
        return SUCCESS;
    }
    
    // called from Edit Form
    public String save() throws Exception {

        oper = "edit";
        gridEditPerson();
        saveMessage(gridOperationMessage);
        return SUCCESS;
    }
    
    private String isValidPerson()
    {
    	if (person == null)
    	{
            return "Invalid Person Data";
    	}
    	if (StringUtils.isEmpty(person.getFirstName()) || StringUtils.isBlank(person.getFirstName()))
    	{
    		return "First Name Required";
    	}
    	if (StringUtils.isEmpty(person.getLastName()) || StringUtils.isBlank(person.getLastName()))
    	{
    		return "Last Name Required";
    	}
    	log.debug("Checking Email");
    	if (person == null)
    	{
            return "Invalid Person Data";
    	}
    	String email = person.getEmail();
    	if (StringUtils.isEmpty(email) || StringUtils.isBlank(email))
    	{
    		return "Email Required";
    	}
    	
    	/* replace by UNIQUE checking
    	List<Person> p = personManager.findByEmail(email);
    	log.debug("Person Emails " + p);
    	
    	if (p.size() > 0)
    	{
    		log.debug("Person Emails Count " + p.size());
    		if (p.size() > 1)
    		    return "Duplicate Email Address";
    		Person pemail = p.get(0);
    		if (oper.equals("edit"))
    		{
    		    if (pemail.getId().longValue() != person.getId().longValue())
    		    {
    		    	return "Duplicate Email Address";
    		    }
    		}
    		else
    		{
    			// oper is an add
    			return "Duplicate Email Address";
    		}
    	}
    	*/
    	
    	EmailValidator emv = EmailValidator.getInstance();
    	if (!emv.isValid(email))
    	{
    		return "Invalid Email";
    	}
    	log.debug("Email is valid");
    	return SUCCESS;
    	
    	
    }
    
    
    public String getGridOperationMessage() {
		return gridOperationMessage;
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
