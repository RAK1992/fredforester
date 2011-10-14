<%@ include file="/common/taglibs.jsp"%>

<head>

    <title><fmt:message key="mainMenu.title"/></title>
    <meta name="heading" content=""/>
    <meta name="menu" content="MainMenu"/>
    
    <script>
    
    $(document).ready(function() { 
    	  
    	  $('#cancellink').click(function() {
			  //alert('Hello ');
			  window.location = 'personList.html';
			});
    	  
      });
      
    </script>
</head>

<s:url id="saveperson" action="saveperson"/> 
<s:url id="list" action="personList"/> 

<s:form name="personForm" action="savePerson" method="post" validate="true">
<s:hidden name="person.id"/>
<sj:tabbedpanel id="persontabs">
    <sj:tab id="tab1" target="basic" label="Basic"/>
    <div id="basic">
        <s:textfield required="true" label="First Name" name="person.firstName" size="60"/>
        <s:textfield required="true" label="Last Name" name="person.lastName" size="60"/>
        <s:textfield label="Email" name="person.email" size="60"/>
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