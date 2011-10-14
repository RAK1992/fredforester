<%@ include file="/common/taglibs.jsp"%>

<head>

    <title><fmt:message key="mainMenu.title"/></title>
    <meta name="heading" content=""/>
    <meta name="menu" content="MainMenu"/>
    
    <script>
    $(document).ready(function() { 
    	  
    	  $('#cancellink').click(function() {
			  //alert('Hello ');
			  window.location = 'appUserValuesList.html';
			});
    	  
      });
      
    </script>
</head>

<s:url id="saveAppUserValue" action="saveAppUserValue"/> 
<s:url id="list" action="appUserValuesList"/> 

<s:form id="appuservalueformid" name="appUserValueForm" action="saveAppUserValue" method="post" validate="true">
<s:hidden name="appUserValue.appuservalueid"/>
<sj:tabbedpanel id="appuservaluetabs">
    <sj:tab id="tab1" target="basic" label="Basic"/>
    <div id="basic">
        <s:textfield required="true" label="Field Name" name="appUserValue.appuservaluefieldname" size="60"/>
        <s:textfield required="true" label="Field Title" name="appUserValue.appuservaluetitle" size="60"/>
        <s:textfield label="Source Field" name="appUserValue.appuservaluesource" size="60"/>
        <s:textfield label="Source Table" name="appUserValue.appuservaluesourcelocation" size="60"/>
        <s:select 
           name="appUserValue.activeFlag"
           list="#{'1':'Active','0':'Inactive'}"
           value="appUserValue.activeFlag"
           required="true"
           label="Status"
        />
    </div>
</sj:tabbedpanel>

<table>
	<tr>
	<td>
	<sj:submit value="Save"	button="true"/>
	</td>
	<td>
	<sj:a id="cancellink" value="Cancel" button="true" href="%{list}">Done</sj:a>
	</td>
	</tr>
</table>

</s:form>