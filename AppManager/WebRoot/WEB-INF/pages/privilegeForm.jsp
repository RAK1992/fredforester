<%@ include file="/common/taglibs.jsp"%>

<head>

    <title><fmt:message key="mainMenu.title"/></title>
    <meta name="heading" content=""/>
    <meta name="menu" content="MainMenu"/>
    
    <script>
    function formatStatus(cellvalue,options,rowObject)
    {
        if (cellvalue == "1")
            return "Active";
        else
            return "Inactive";
    }
    
    function checkGridOperation(response,postdata)
	{
	    //alert("Here");
	    var gridresponse = gridresponse || {};
	    gridresponse = jQuery.parseJSON(response.responseText);
	    var msg = gridresponse["gridOperationMessage"];
        if (msg == 'Record Saved')
        {
            return [true,""];
        }
        else
	    {
	        return [false,msg];   
	    }
        
	}
	
	function addPrivRole(formid)
	{
		var privNo = $("#hiddenPrivNo").val();
        var mydataUrl = "json/rolesSelect.html?privNo=" + privNo;
          
        $("#rolesprivgrid").jqGrid('setColProp','roleNo',
            {edittype:'select',
            editrules:{edithidden:true,required:true},
            editoptions:{readonly:false,dataUrl:mydataUrl,buildSelect:getRolesSelect}});
              
        $("#rolesprivgrid").jqGrid('setColProp','roleId',{editable:false,editoptions:{readonly:false}});
	}
	
	function updatePrivRole(formid)
	{
		$("#rolesprivgrid").jqGrid('setColProp','roleNo',{editable:true,edittype:'text',editrules:{edithidden:false},editoptions:{readonly:true}});
        $("#rolesprivgrid").jqGrid('setColProp','roleId',{editable:true,editoptions:{readonly:true}});
	}
	
    function setRolesPrivModel(postdata, formid)
    {
      //alert("here");
  	  postdata['rolesPriv.activeFlag'] = postdata['activeFlag'];
  	  postdata['rolesPriv.roleNo'] = postdata['roleNo'];
      return [true,""];
  	  
    }
    
    function getRolesSelect(response)
    {
  	    //alert(response.responseText);
        //privs = eval("(" + response.responseText + ")");
        var roles = roles || {};
        roles = jQuery.parseJSON(response.responseText);
        var options = '<option value="">Select</option>';
        for(var i=0;i<roles.length;i++)
        {
            options += '<option value="'+roles[i].roleNo+'">';
            options += roles[i].roleId + '</option>';
        }
        options = '<select name="rolenos">' + options + '</select>';
        return options;
    }
    
    
    $(document).ready(function() { 
    	  
    	  $('#cancellink').click(function() {
			  //alert('Hello ');
			  window.location = 'privilegeList.html';
			});
    	  
      });
      
    </script>
</head>

<s:url id="savePrivilege" action="savePrivilege"/> 
<s:url id="list" action="privilegeList"/> 


<s:form name="privForm" action="savePrivilege" method="post" validate="true">
<s:hidden name="privilege.privNo" id="hiddenPrivNo"/>
<sj:tabbedpanel id="privtabs">
    <sj:tab id="tab1" target="basic" label="Basic"/>
    <sj:tab id="tab2" target="rolespriv" label="PrivRoles"/>
    <div id="basic">
        <s:textfield required="true" label="Short Name" name="privilege.privId" size="60"/>
        <s:textfield required="true" label="Description" name="privilege.privDesc" size="60"/>
        <s:textarea cols="58" rows="4" label="Long Description" name="privilege.privDescExt"/>
        <s:textfield label="Url" name="privilege.privUrl" size="60"/>
        <s:textfield label="Department" name="privilege.privDpt" size="60"/>
        <s:select 
           name="privilege.activeFlag"
           list="#{'1':'Active','0':'Inactive'}"
           value="privilege.activeFlag"
           required="true"
           label="Status"
        />
        <s:select 
           name="privilege.isforcedredirect"
           list="#{'1':'Yes','0':'No'}"
           value="privilege.isforcedredirect"
           required="true"
           label="Force Redirect"
        />
        <s:select 
           name="privilege.privShowmenu"
           list="#{'Y':'Yes','N':'No'}"
           value="privilege.privShowmenu"
           required="true"
           label="Show Menu"
        />
    </div>
    
    <div id="rolespriv">
        <s:url id="rolesprivlisturl" action="json/rolesPrivGridList">
            <s:param name="privNo" value="%{privilege.privNo}"/>
        </s:url>
        <s:url id="gridEditRolesPriv" action="json/gridEditRolesPriv">
		    <s:param name="privNo" value="%{privilege.privNo}"/>
		</s:url>
		<s:url id="rolesselect" action="json/rolesSelect">
            <s:param name="privNo" value="%{privilege.privNo}"/>
        </s:url>
		
		<sjg:grid 
			id="rolesprivgrid" 
			caption="Privilege Roles" 
			dataType="json" 
			href="%{rolesprivlisturl}"
			gridModel="rolesPrivList"
			pager="true" 
			rownumbers="false"
			navigator="true"
	    	navigatorAddOptions="{height:300,width:400,recreateForm:true,reloadAfterSubmit:true,beforeInitData:addPrivRole,beforeSubmit:setRolesPrivModel,afterSubmit:checkGridOperation}"
	    	navigatorEditOptions="{height:300,width:400,recreateForm:true,reloadAfterSubmit:true,beforeInitData:updatePrivRole,beforeSubmit:setRolesPrivModel,afterSubmit:checkGridOperation}"
	    	navigatorEdit="true"
	    	navigatorView="false"
	    	navigatorSearch="false"
	    	navigatorDelete="false"
	    	editurl="%{gridEditRolesPriv}"
	    	editinline="false"
	    	
		>
		<sjg:gridColumn 
			    key="true"
			    name="rolesPrivNo" 
			    index="rolesPrivNo" 
			    title="ID" 
			    formatter="integer"
			    editable="false"
			    hidden="true"
			    sortable="false"/>
		
		<sjg:gridColumn 
			    name="roleId" 
			    index="roleId" 
			    hidden="false"
			    title="roleId"
			    editable="false"
			    sortable="false"/>
			    
		<sjg:gridColumn 
			    name="roleNo" 
			    index="roleNo" 
			    hidden="true"
			    title="roleNo"
			    editable="true"
			    editrules="{edithidden:true}" 
			    edittype="select"
			    editoptions="{dataUrl:'%{rolesselect}',buildSelect:getRolesSelect}"
			    sortable="false"/>
	    
		<sjg:gridColumn 
			    name="roleDescription" 
			    index="roleDescription" 
			    title="Role Description"
			    editable="false"
			    editrules="{required:false}" 
			    sortable="true"/>
			    
	    <sjg:gridColumn 
			    name="activeFlag" 
			    index="activeFlag" 
			    title="Active" 
			    editable="true"
			    edittype="select"
			    editoptions="{value:'1:Active;0:Inactive'}"
			    formatter="formatStatus"
			    sortable="true"/>
			    
	    </sjg:grid>
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