package com.mycompany.webapp.pagers;

import javax.servlet.http.HttpServletRequest;


public class PagerFactory {
	
	public static final String JQUERYTYPE = "JQUERY";
	public static final String DISPLAYTAGTYPE = "DISPLAYTAG";
	
	public PagerImpl getPager(String type,HttpServletRequest request)
	{
		if (type.equals(JQUERYTYPE))
		{
			JQueryPager jqp = new JQueryPager();
			makePager(jqp,request);
			return jqp;
		}
		if (type.equals(DISPLAYTAGTYPE))
		{
			DisplayTagPager dtp = new DisplayTagPager();
			makePager(dtp,request);
			return dtp;
		}
		return null;
		
	}
	
	private void makePager(PagerImpl pager,HttpServletRequest request)
	{
		String sortCriterion = null;
        String thePage = null;
        int pageSize = 0;
        if (request != null) {
            sortCriterion = request.getParameter(pager.getRequestValueSort());
            pager.setSortDirection(pager.getRequestValueDesc()
                            .equals(request.getParameter(pager.getRequestValueDirection())) ? SortOrderEnum.DESCENDING
                            : SortOrderEnum.ASCENDING);
            thePage = request.getParameter(pager.getRequestValuePage());
            String ps = request.getParameter(pager.getRequestValuePagesize());
            if (ps != null && ps.trim().length() > 0)
            {
                pageSize = new Integer(ps).intValue();
            }
            else
                pageSize = IPager.DEFAULT_PAGE_SIZE;
            if (sortCriterion == null || sortCriterion.trim().length() == 0)
                sortCriterion = null;
            
            if (pager.isSearchable())
            {
            	String tmp = request.getParameter(pager.getRequestValueSearchField());
	            pager.setSearchField(tmp);
	            tmp = request.getParameter(pager.getRequestValueSearchFor());
	            pager.setSearchFor(tmp);
	            tmp = request.getParameter(pager.getRequestValueSearchOper());
	            pager.setSearchOper(tmp);
            }
        }
        pager.setSortCriterion(sortCriterion);
        pager.setPageSize(pageSize);
        // the index is the zero based page number.
        // we correct this via getPageNumber
        if (thePage != null) {
            int index = pager == null ? 0
                    : Integer.parseInt(thePage) - 1;
            pager.setIndex(index);
        } else {
            pager.setIndex(0);
        }
	}

}
