<%@ include file="/common/taglibs.jsp"%>


<div id="customerForm">
	
	<div class="span-12">
		<spring:hasBindErrors name="user">
			<div class="error">
				<spring:bind path="user.*">
					<c:forEach items="${status.errorMessages}" var="error">
						<span><c:out value="${error}"/></span><br>
					</c:forEach>
				</spring:bind>
			</div>
		</spring:hasBindErrors>
		<form:form modelAttribute="customer" action="${flowExecutionUrl}">
		    <form:hidden path="id" />
			<fieldset>
				<legend>Customer Profile</legend>
				<div>
					<div class="span-4">
						<label for="name">First Name:</label>
					</div>
					<div class="span-7 last">
						<p><form:input path="firstname" value="${customer.firstname}"/></p>
						<script type="text/javascript">
							Spring.addDecoration(new Spring.ElementDecoration({
								elementId : "firstname",
								widgetType : "dijit.form.ValidationTextBox",
								widgetAttrs : { required : true  }}));
						</script>
					</div>
				</div>
				<div>
					<div class="span-4">
						<label for="name">Last Name:</label>
					</div>
					<div class="span-7 last">
						<p><form:input path="lastname" value="${customer.lastname}"/></p>
						<script type="text/javascript">
							Spring.addDecoration(new Spring.ElementDecoration({
								elementId : "lastname",
								widgetType : "dijit.form.ValidationTextBox",
								widgetAttrs : { required : true  }}));
						</script>
					</div>
				</div>
				<div>
					<div class="span-4">
						<label for="email">Email Address:</label>
					</div>
					<div class="span-7 last">
						<p><form:input id="email" path="email" maxlength="40"/></p>
						<script type="text/javascript">
							Spring.addDecoration(new Spring.ElementDecoration({
								elementId : "email",
								widgetType : "dijit.form.ValidationTextBox",
								widgetAttrs : { required : true }}));
						</script>
					</div>
				</div>
				
				
				<div>
					<p>
					<input type="submit" id="save" name="_eventId_save" value="<fmt:message key="button.save"/>" 
                    onclick="Spring.remoting.submitForm('save', 'customer')"/>&#160;
                    <input type="submit" name="_eventId_cancel" value="Cancel"/>&#160;  
					</p>
					<script type="text/javascript">
						Spring.addDecoration(new Spring.ValidateAllDecoration({elementId:'save', event:'onclick'}));
					</script>
				</div>
			</fieldset>
		</form:form>	
	</div>
	
	<c:if test="${not empty customer.id}">
	<div>
	<a href="${flowExecutionUrl}&_eventId=addAddress" ><fmt:message key="address.form.button.add"/></a>
	</div>
	</c:if>
	
	<c:if test="${not empty customer.customerAddresses}">
	<table class="search">
	    <tr>
	        <th><fmt:message key="address.form.address"/></th>
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
</div>
