<%@ include file="/common/taglibs.jsp"%>

<h1><fmt:message key="customer.form.title"/></h1>

<div id="messages">
    <c:if test="${not empty statusMessageKey}">
       <p><fmt:message key="${statusMessageKey}"/></p>
    </c:if>

    <spring:hasBindErrors name="customer">
        <h2>Errors</h2>
        <div class="formerror">
            <ul>
            <c:forEach var="error" items="${errors.allErrors}">
                <li>${error.defaultMessage}</li>
            </c:forEach>
            </ul>
        </div>
    </spring:hasBindErrors>
</div>

<c:if test="${not empty customer.id}">
<div>
<a href="${flowExecutionUrl}&_eventId=addAnother" ><fmt:message key="button.add"/></a>
<a href="${flowExecutionUrl}&_eventId=editCurrent&id=${customer.id}" ><fmt:message key="button.edit"/></a>
<a href="${flowExecutionUrl}&_eventId=cancel" ><fmt:message key="button.cancel"/></a>
</div>
</c:if>
