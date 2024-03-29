package com.mycompany.webapp.pagers;

public class DisplayTagPager extends PagerImpl {

	private static String SORT = "sort";
	private static String PAGE = "page";
	private static String ASC = "asc";
	private static String DESC = "desc";
	private static String DIRECTION = "dir";
	private static String PAGESIZE = "rows";
    
	public String getRequestValueSort() {
		return this.SORT;
	}

	public String getRequestValuePage() {
		return this.PAGE;
	}

	public String getRequestValueAsc() {
		return this.ASC;
	}

	public String getRequestValueDesc() {
		return this.DESC;
	}

	public String getRequestValueDirection() {
		return this.DIRECTION;
	}

	public String getRequestValuePagesize() {
		return this.PAGESIZE;
	}

	public String getSearchField() {
		return null;
	}

	public void setSearchField(String searchField) {
		
	}

	public String getSearchOper() {
		return null;
	}

	public void setSearchOper(String searchOper) {
		
	}

	public String getSearchFor() {
		return null;
	}

	public void setSearchFor(String searchFor) {
		
	}

	public boolean isSearchable() {
		return false;
	}

	public String getRequestValueSearchFor() {
		return null;
	}

	public String getRequestValueSearchOper() {
		return null;
	}

	public String getRequestValueSearchField() {
		return null;
	}

}