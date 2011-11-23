<%@ include file="/common/taglibs.jsp"%>


<h1><fmt:message key="customer.form.title"/></h1>

<div id="messages">
    <c:if test="${not empty statusMessageKey}">
       <p><fmt:message key="${statusMessageKey}"/></p>
    </c:if>
</div>

<form:form modelAttribute="customer">
<form:errors path="*" cssClass="error" element="div"/>
    <form:hidden path="id" />

    <fieldset>
        <div class="form-row">
            <appfuse:label styleClass="desc" key="customer.form.firstName"/>
            <form:errors path="firstname" cssClass="fieldError"/>
            <span class="input"><form:input path="firstname" /></span>
        </div>       
        <div class="form-row">
            <appfuse:label styleClass="desc" key="customer.form.lastName"/>
            <form:errors path="lastname" cssClass="fieldError"/>
            <span class="input"><form:input path="lastname" /></span>
        </div>
        <div class="form-buttons">
            <div class="button">
                <input type="submit" id="save" name="_eventId_save" value="<fmt:message key="button.save"/>" />&#160;
                <input type="submit" name="_eventId_cancel" value="Cancel"/>&#160;          
            </div>    
        </div>
    </fieldset>
</form:form>
<div>

<c:if test="${not empty customer.id}">
<div>
<a href="${flowExecutionUrl}&_eventId=addAddress" ><fmt:message key="address.form.button.add"/></a>
</div>
</c:if>

<c:if test="${empty customer.customerAddresses}">
    <p>No Addresses</p>
</c:if>

<c:if test="${not empty customer.customerAddresses}">
<table class="search">
    <tr>
        <th><fmt:message key="address.form.street"/></th>
        <th><fmt:message key="address.form.city"/></th>
        <th><fmt:message key="address.form.state"/></th>
        <th><fmt:message key="address.form.zip"/></th>
        <th><fmt:message key="address.form.country"/></th>
    </tr>
<c:forEach var="address" items="${customer.customerAddresses}">
    <tr>
            <td>${address.street}</td>
            <td>${address.city}</td>
            <td>${address.state}</td>
            <td>${address.zip}</td>
            <td>${address.country}</td> 
            <td>
                <a href="${flowExecutionUrl}&_eventId=editAddress&addressId=${address.id}" ><fmt:message key="button.edit"/></a>
                <security:authorize ifAllGranted="ROLE_ADMIN">
                    <a href="${flowExecutionUrl}&_eventId=deleteAddress&addressId=${address.id}" ><fmt:message key="button.delete"/></a>
                </security:authorize>
            </td>
    </tr>
</c:forEach>
</table>
</c:if>

<c:if test="${not empty customer.id}">
<div>
<a href="${flowExecutionUrl}&_eventId=addQuestion" ><fmt:message key="question.form.button.add"/></a>
</div>
</c:if>

<c:if test="${empty customer.questions}">
    <p>No Questions</p>
</c:if>

<c:if test="${not empty customer.questions}">
<table class="search">
    <tr>
        <th><fmt:message key="question.form.question"/></th>
        <th><fmt:message key="question.form.answer"/></th>
    </tr>
<c:forEach var="question" items="${customer.questions}">
    <tr>
            <td>${question.question}</td>
            <td>${question.answer}</td>
            <td>
                <a href="${flowExecutionUrl}&_eventId=editQuestion&questionId=${question.id}" ><fmt:message key="button.edit"/></a>
                <security:authorize ifAllGranted="ROLE_ADMIN">
                    <a href="${flowExecutionUrl}&_eventId=deleteQuestion&questionId=${question.id}" ><fmt:message key="button.delete"/></a>
                </security:authorize>
            </td>
    </tr>
</c:forEach>
</table>
</c:if>

</div>