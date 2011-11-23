<%@ include file="/common/taglibs.jsp"%>

<h1>Search customers</h1>

<c:url var="customersUrl" value="/customers"/>
<form:form modelAttribute="searchCriteria" action="${customersUrl}" method="get" cssClass="inline">
    <form:hidden path="page"/>
    <span class="errors span-18">
    	<form:errors path="*"/>
    </span>
	<fieldset>
		<div class="span-8">
			<label for="searchString">Search String:</label>
			<form:input id="searchString" path="searchString"/>
		</div>
		<div class="span-6">
			<div>
				<label for="pageSize">Maximum results:</label>
				<form:select id="pageSize" path="pageSize">
					<form:option label="5" value="5"/>
					<form:option label="10" value="10"/>
					<form:option label="20" value="20"/>
				</form:select>
			</div>
		</div>
		<div class="span-3 last button">
			<button type="submit">Find</button>
		</div>		
    </fieldset>
</form:form>