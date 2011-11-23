<%@ include file="/common/taglibs.jsp"%>

<h1><fmt:message key="address.form.title"/></h1>

<div id="messages">
    <c:if test="${not empty statusMessageKey}">
       <p><fmt:message key="${statusMessageKey}"/></p>
    </c:if>
</div>

<form:form modelAttribute="address">
<form:errors path="*" cssClass="error" element="div"/>
    <form:hidden path="id" />

    <fieldset>
        <div class="form-row">
        	<appfuse:label styleClass="desc" key="address.form.street"/>
            <form:errors path="street" cssClass="fieldError"/>
            <span class="input"><form:input path="street" /></span>
        </div>       
        <div class="form-row">
        	<appfuse:label styleClass="desc" key="address.form.city"/>
            <form:errors path="city" cssClass="fieldError"/>
            <span class="input"><form:input path="city" /></span>
        </div>       
        <div class="form-row">
        	<appfuse:label styleClass="desc" key="address.form.state"/>
            <form:errors path="state" cssClass="fieldError"/>
            <span class="input"><form:input path="state" /></span>
        </div>       
        <div class="form-row">
        	<appfuse:label styleClass="desc" key="address.form.zip"/>
            <form:errors path="zip" cssClass="fieldError"/>
            <span class="input"><form:input path="zip" /></span>
        </div>       
        <div class="form-row">
        	<appfuse:label styleClass="desc" key="address.form.country"/>
            <form:errors path="country" cssClass="fieldError"/>
            <span class="input"><form:input path="country" /></span>
        </div>       
        <div class="form-buttons">
            <div class="button">
                <input type="submit" id="save" name="_eventId_save" value="<fmt:message key="button.save"/>" />&#160;
                <input type="submit" name="_eventId_cancel" value="Cancel"/>&#160;          
            </div>    
        </div>
    </fieldset>
</form:form>