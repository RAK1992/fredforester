package com.mycompany.model;

import java.io.Serializable;

/**
 * A backing bean for the main hotel search form. Encapsulates the criteria
 * needed to perform a hotel search.
 * 
 * It is expected a future milestone of Spring Web Flow 2.0 will allow
 * flow-scoped beans like this one to hold references to transient services that
 * are restored automatically when the flow is resumed on subsequent requests.
 * This would allow this SearchCriteria object to delegate to the
 * {@link BookingManager} directly, for example, eliminating the need for the
 * actions in {@link MainActions}.
 */
public class SearchCriteria implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * The user-provided search criteria for finding Hotels.
	 */
	private String searchString;

	/**
	 * The maximum page size of the Hotel result list
	 */
	private int pageSize = 10;

	/**
	 * The current page of the Hotel result list.
	 */
	private int page = 1;

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getEnd()
    {
        return getPageSize() * getPage();
    }
    
    public int getStart()
    {
        return getEnd() -  getPageSize();
    }
    
	@Override
	public String toString() {
		return "SearchCriteria [searchString=" + searchString + ", pageSize="
				+ pageSize + ", page=" + page + "]";
	}
	
	

}