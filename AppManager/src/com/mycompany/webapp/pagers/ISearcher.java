package com.mycompany.webapp.pagers;

public interface ISearcher {
	
	String getSearchField();
    void setSearchField(String searchField);
    
    String getSearchOper();
    void setSearchOper(String searchOper);
    
    String getSearchFor();
    void setSearchFor(String searchFor);
    
    boolean isSearchable();
    
}
