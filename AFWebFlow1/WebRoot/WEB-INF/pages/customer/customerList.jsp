<%@ include file="/common/taglibs.jsp"%>


<h1><fmt:message key="customer.search.title"/></h1>

<a id="changeSearchLink" href="customers/searchForm?searchString=${searchCriteria.searchString}&pageSize=${searchCriteria.pageSize}">Change Search</a>
 || <a href="customer">Add</a>

<c:if test="${not empty customerList}">
<div class="buttonGroup">
	<div class="span-3">
	    <c:choose>
		<c:when test="${searchCriteria.page > 0}">
			<a id="prevResultsLink" href="customers?searchString=${searchCriteria.searchString}&pageSize=${searchCriteria.pageSize}&page=${searchCriteria.page - 1}">Previous Results</a>
			
		</c:when>
		<c:otherwise>Previous Results</c:otherwise>
		</c:choose>
		<c:if test="${not empty customerList && fn:length(customerList) == searchCriteria.pageSize}">
			<a id="moreResultsLink" href="customers?searchString=${searchCriteria.searchString}&pageSize=${searchCriteria.pageSize}&page=${searchCriteria.page + 1}">More Results</a>
			
		</c:if>		
	</div>
</div>
</c:if>

<table class="summary">
    <thead>
    <tr>
        <th><fmt:message key="customer.form.firstName"/></th>
        <th><fmt:message key="customer.form.lastName"/></th>
        <th>Action</th>
    </tr>
    </thead>
<c:forEach var="customer" items="${customerList}">
    <tr>
        <c:url var="editUrl" value="/customer/edit">
            <c:param name="id" value="${customer.id}" />
        </c:url>
        <c:url var="deleteUrl" value="/customer/delete">
            <c:param name="id" value="${customer.id}" />
        </c:url>

       <td>${customer.firstname}</td>
       <td>${customer.lastname}</td> 
       <td>
            <a href="customer?_eventId=edit&id=${customer.id}" ><fmt:message key="button.edit"/></a>
            <security:authorize ifAllGranted="ROLE_ADMIN">
                <a href='<c:out value="${deleteUrl}"/>'><fmt:message key="button.delete"/></a>
            </security:authorize>
        </td>
    </tr>
</c:forEach>
</table>

