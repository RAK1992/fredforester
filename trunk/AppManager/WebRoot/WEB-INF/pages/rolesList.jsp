<%@ include file="/common/taglibs.jsp"%>


<head>

    <title><fmt:message key="mainMenu.title"/></title>
    <meta name="heading" content=""/>
    <meta name="menu" content="MainMenu"/>
    
    <script>
    
    function setRoleModel(postdata, formid)
    {
  	  //alert("beforesubmit");
  	  postdata['role.roleId'] = postdata['roleId'];
  	  postdata['role.roleDesc'] = postdata['roleDesc'];
  	  postdata['role.activeFlag'] = postdata['activeFlag'];
  	  
      return [true,""];
  	  
    }
    
    function checkGridOperation(response,postdata)
	{
	    var gridresponse = gridresponse || {};
	    gridresponse = jQuery.parseJSON(response.responseText);
	    var msg = gridresponse["gridOperationMessage"];
	    //alert(msg);
        if (msg == 'Role Added')
        {
            return [true,""];
        }
        else
	    {
	        return [false,msg];   
	    }
        
	}
    
    function formatStatus(cellvalue,options,rowObject)
    {
        if (cellvalue == "1")
            return "Active";
        else
            return "Inactive";
    }
    
    $(document).ready(function() { 
       //alert("onready");
       $.subscribe('rowselect', function(event,data) {
         var row = $("#gridtable").jqGrid('getRowData',event.originalEvent.id);
         var roleno = row['roleNo'];
         $("#gridinfo").html('<p>Loading..... RoleNo : '+roleno+'</p>');
         //alert("selected " + roleno);
         window.location = "editRole.html?id=" + roleno;
         
         
       });
       
    });
    </script>
    
</head>


<s:url id="editurl" action="json/gridEditRole"/> 
<s:url id="remoteurl" action="json/rolesList"/> 
<center>
    <sjg:grid 
    	id="gridtable" 
    	caption="Roles" 
    	dataType="json" 
    	href="%{remoteurl}"
    	pager="true" 
    	gridModel="roles"
    	rowList="10,15,20"
    	rowNum="20"
    	editurl="%{editurl}"
    	rownumbers="false"
    	navigator="true"
    	navigatorSearchOptions="{sopt:['cn','bw','eq']}"
    	navigatorAddOptions="{reloadAfterSubmit:true,beforeSubmit:setRoleModel,afterSubmit:checkGridOperation}"
    	navigatorEdit="false"
    	navigatorDelete="false"
    	editinline="false"
    	onSelectRowTopics="rowselect" 
    >
    	<sjg:gridColumn 
    	    name="roleNo" 
    	    search="false" 
    	    index="roleNo" 
    	    title="ID" 
    	    hidden="true"
    	    formatter="integer" 
    	    sortable="false"/>
    	<sjg:gridColumn 
    	    name="roleId" 
    	    index="roleId" 
    	    title="RoleId" 
    	    sortable="true"
    	    editable="true" 
    	    width="275"
            editrules="{required: true}" 
    	    />
    	<sjg:gridColumn 
    	    name="roleDesc" 
    	    index="roleDesc" 
    	    title="RoleDesc" 
    	    sortable="true"
    	    editable="true" 
    	    width="300"
    	    hidden="false"
            editrules="{edithidden:true,required: true}" 
    	    />
    	<sjg:gridColumn 
    	    name="activeFlag" 
    	    search="true" 
    	    searchoptions="{sopt:['eq']}" 
    	    index="activeFlag" 
    	    title="ActiveFlag" 
    	    sortable="true"
    	    editable="true"
    	    edittype="select" 
    	    formatter="formatStatus"
    	    editoptions="{value:'1:1-Active;0:0-Inactive'}"
            editrules="{required: true}"
    	    />
    	
    </sjg:grid>
    <div id="gridinfo" class="ui-widget-content ui-corner-all"><p>Edit Mode for Row :</p></div>
    </center>