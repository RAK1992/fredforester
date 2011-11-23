<%@ include file="/common/taglibs.jsp"%>

<h1><fmt:message key="question.form.title"/></h1>

<div id="messages">
    <c:if test="${not empty statusMessageKey}">
       <p><fmt:message key="${statusMessageKey}"/></p>
    </c:if>
</div>

<form:form modelAttribute="question">
<form:errors path="*" cssClass="error" element="div"/>
    <form:hidden path="id" />

    <fieldset>
        <div class="form-row">
        	<appfuse:label styleClass="desc" key="question.form.question"/>
            <form:errors path="question" cssClass="fieldError"/>
            <span class="input"><form:input path="question" /></span>
        </div>       
        <div class="form-row">
        	<appfuse:label styleClass="desc" key="question.form.answer"/>
            <form:errors path="answer" cssClass="fieldError"/>
            <span class="input"><form:input path="answer" /></span>
        </div>       
        <div class="form-buttons">
            <div class="button">
                <input type="submit" id="save" name="_eventId_save" value="<fmt:message key="button.save"/>" />&#160;
                <input type="submit" name="_eventId_cancel" value="Cancel"/>&#160;          
            </div>    
        </div>
    </fieldset>
</form:form>