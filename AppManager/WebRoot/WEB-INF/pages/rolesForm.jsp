<%@ include file="/common/taglibs.jsp"%>

<head>

    <title><fmt:message key="mainMenu.title"/></title>
    <meta name="heading" content=""/>
    <meta name="menu" content="MainMenu"/>
    
    <script>
    $(document).ready(function() { 
    	  
    	  $('#cancellink').click(function() {
			  //alert('Hello ');
			  window.location = 'rolesList.html';
			});
    	  
      });
      
    </script>
</head>

<s:url id="saveRole" action="saveRole"/> 
<s:url id="list" action="rolesList"/> 

<s:form name="rolesForm" action="saveRole" method="post" validate="true">
<s:hidden name="role.roleNo"/>
<sj:tabbedpanel id="roletabs">
    <sj:tab id="tab1" target="basic" label="Basic"/>
    <div id="basic">
        <s:textfield required="true" label="Short Name" name="role.roleId" size="60"/>
        <s:textfield required="true" label="Description" name="role.roleDesc" size="60"/>
        <s:textarea cols="58" rows="4" label="Long Description" name="role.roleDescExt"/>
        <s:textfield label="Request Method Title" name="role.requestmethodtitle" size="60"/>
        
        <s:select 
           name="role.activeFlag"
           list="#{'1':'Active','0':'Inactive'}"
           value="role.activeFlag"
           required="true"
           label="Status"
        />
        <s:select 
           name="role.roletypeid"
           list="#{'1':'Application','0':'None'}"
           value="role.roletypeid"
           required="true"
           label="RoleType"
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