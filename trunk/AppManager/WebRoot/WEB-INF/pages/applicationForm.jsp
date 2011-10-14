<%@ include file="/common/taglibs.jsp"%>

<head>

    <title><fmt:message key="mainMenu.title"/></title>
    <meta name="heading" content=""/>
    <meta name="menu" content="MainMenu"/>
    
    <script>
      
      function addAppPriv(formid)
      {
          var appId = $("#hiddenApplicationid").val();
          
          var mydataUrl = "json/loadPrivSelectOptions.html";
          
          $("#appprivgrid").jqGrid('setColProp','privNo',
              {edittype:'select',
              editrules:{edithidden:true,required:true},
              editoptions:{readonly:false,dataUrl:mydataUrl,buildSelect:privSelect}});
              
          $("#appprivgrid").jqGrid('setColProp','privId',{editable:false,editoptions:{readonly:false}});
      }
      
      function updateAppPriv(formid)
      {
          //$('#tr_privNo').show(); 
          //$('#privNo').show(); 
          //$('#tr_activeFlag').show(); 
          //$('#activeFlag').show(); 
          //$('#privNo').attr("disabled",true);
          $("#appprivgrid").jqGrid('setColProp','privNo',{editable:true,edittype:'text',editrules:{edithidden:false},editoptions:{readonly:true}});
          $("#appprivgrid").jqGrid('setColProp','privId',{editable:true,editoptions:{readonly:true}});
      }
      
      function setAppPrivModel(postdata, formid)
      {
    	  postdata['applicationpriv.privNo'] = postdata['privNo'];
    	  postdata['applicationpriv.activeFlag'] = postdata['activeFlag'];
    	  return [true,""];
      }
      
      function addAppOwner(formid)
      {
      }
      
      function updateAppOwner(formid)
      {
      }
      
      function setAppOwnerModel(postdata, formid)
      {
    	  //alert("beforesubmit");
    	  postdata['applicationowner.owner'] = postdata['owner'];
    	  postdata['applicationowner.firstName'] = postdata['firstName'];
    	  postdata['applicationowner.lastName'] = postdata['lastName'];
    	  postdata['applicationowner.activeFlag'] = postdata['activeFlag'];
          return [true,""];
    	  
      }
      
      function addAppRoleOwner(formid)
      {
    	  
          //$('#owner').removeAttr("readonly");
          //$('#roleNo',formid).removeAttr('disabled');
          var appId = $("#hiddenApplicationid").val();
          
          var mydataUrl = "json/appPrivRoleSelect.html?aruvquery.applicationid=" + appId;
          
          $("#approleownersgrid").jqGrid('setColProp','roleNo',
              {edittype:'select',
              editrules:{edithidden:true,required:true},
              editoptions:{readonly:false,dataUrl:mydataUrl,buildSelect:getAppPrivRoleSelect}});
              
          $("#approleownersgrid").jqGrid('setColProp','roleId',{editable:false,editoptions:{readonly:false}});
          
      }
      
      function updateAppRoleOwner(formid)
      {
    	  
          //$('#owner').attr("readonly",true);
          //$('#roleNo',formid).attr("disabled",true);
          $("#approleownersgrid").jqGrid('setColProp','roleNo',{editable:true,edittype:'text',editrules:{edithidden:false},editoptions:{readonly:true}});
          $("#approleownersgrid").jqGrid('setColProp','roleId',{editable:true,editoptions:{readonly:true}});
      }
      
      function updateAppPrivRoleUV(formid)
      {
          var gr = $("#appprivroleuv").jqGrid('getGridParam','selrow');
          var rowData = $("#appprivroleuv").jqGrid('getRowData',gr);
          //alert(rowData['roleNo']);
          //$('#appuservaluetitle',formid).val(rowData['appuservaluetitle']);
          
          $("#appprivroleuv").jqGrid('setColProp','privNo',{editable:true,edittype:'text',editrules:{edithidden:false},editoptions:{readonly:true}});
          $("#appprivroleuv").jqGrid('setColProp','privId',{editable:true,editoptions:{readonly:true}});
          $("#appprivroleuv").jqGrid('setColProp','roleNo',{editable:true,edittype:'text',editrules:{edithidden:false},editoptions:{readonly:true}});
          $("#appprivroleuv").jqGrid('setColProp','roleId',{editable:true,editoptions:{readonly:true}});
          $("#appprivroleuv").jqGrid('setColProp','appuservalueid',{editable:true,edittype:'text',editrules:{edithidden:false},editoptions:{readonly:true}});
          $("#appprivroleuv").jqGrid('setColProp','appuservaluefieldname',{editable:true,editoptions:{readonly:true}});
          $("#appprivroleuv").jqGrid('setColProp','appuservaluefieldtitle',{editable:true,editoptions:{readonly:true}});
          
          
      }
      
      function addAppPrivRoleUV(formid)
      {
          $("#roleNo",formid).find("option").remove();
          var appId = $("#hiddenApplicationid").val();
          var mydataUrl = "json/appPrivSelect.html?applicationId=" + appId;
          //alert(mydataUrl);
          $("#appprivroleuv").jqGrid('setColProp','privNo',
              {edittype:'select',
              editrules:{edithidden:true,required:true},
              editoptions:{readonly:false,dataUrl:mydataUrl,buildSelect:getAppPrivSelect,dataEvents:[{type:'change',fn:privSelectChanged}]}});
              
          $("#appprivroleuv").jqGrid('setColProp','privId',{editable:false,editoptions:{readonly:false}});
          
          $("#appprivroleuv").jqGrid('setColProp','roleNo',
              {edittype:'select',
              editrules:{edithidden:true,required:true},
              editoptions:{readonly:false,value:'--:--'}});
              
          $("#appprivroleuv").jqGrid('setColProp','roleId',{editable:false,editoptions:{readonly:false}});
          
          $("#appprivroleuv").jqGrid('setColProp','appuservalueid',
              {edittype:'select',
              editrules:{edithidden:true,required:true},
              editoptions:{readonly:false,value:'--:00'}});
              
          $("#appprivroleuv").jqGrid('setColProp','appuservaluefieldname',{editable:false,editoptions:{readonly:false}});
          $("#appprivroleuv").jqGrid('setColProp','appuservaluefieldtitle',{editable:false,editoptions:{readonly:false}});
      }
      
      function setAppRoleOwnerModel(postdata, formid)
      {
    	  //alert("beforesubmit");
    	  postdata['approleowner.owner'] = postdata['owner'];
    	  postdata['approleowner.roleNo'] = postdata['roleNo'];
    	  postdata['approleowner.firstName'] = postdata['firstName'];
    	  postdata['approleowner.lastName'] = postdata['lastName'];
    	  postdata['approleowner.activeFlag'] = postdata['activeFlag'];
          return [true,""];
    	  
      }
      
      function setAppRoleUVModel(postdata, formid)
      {
    	  postdata['approleuservalue.roleNo'] = postdata['roleNo'];
    	  postdata['approleuservalue.privNo'] = postdata['privNo'];
    	  postdata['approleuservalue.appuservalueid'] = postdata['appuservalueid'];
    	  postdata['approleuservalue.activeFlag'] = postdata['activeFlag'];
    	  return [true,""];
      }
      
      
      function getAppPrivSelect(response)
      {
    	  var applicationprivs = applicationprivs || {};
          applicationprivs = jQuery.parseJSON(response.responseText);
          var options = '<option value="">Select</option>';
          for(var i=0;i<applicationprivs.length;i++)
          {
              options += '<option value="'+applicationprivs[i].privNo+'">';
              options += applicationprivs[i].privId + '</option>';
          }
          options = '<select name="uvprivno">' + options + '</select>';
          return options;
          
      }
      
      function getAppPrivRoleSelect(response)
      {
    	  var appprivrole = appprivrole || {};
          appprivrole = jQuery.parseJSON(response.responseText);
          //alert(appprivrole.length);
          var options = '<option value="">Select</option>';
          for(var i=0;i<appprivrole.length;i++)
          {
              options += '<option value="'+appprivrole[i].roleNo+'">';
              options += appprivrole[i].roleId + '</option>';
          }
          
          options = '<select name="appprivrole">' + options + '</select>';
          return options;
          
      }
      
      function privSelect(response)
      {
    	  //alert(response.responseText);
          //privs = eval("(" + response.responseText + ")");
          var privs = privs || {};
          privs = jQuery.parseJSON(response.responseText);
          var options = '<option value="">Select</option>';
          for(var i=0;i<privs.length;i++)
          {
              options += '<option value="'+privs[i].privNo+'">';
              options += privs[i].privId + '</option>';
          }
          options = '<select name="privnos">' + options + '</select>';
          return options;
      }
      
      function privSelectChanged(e)
      {
          var privNo = $("#privNo").val();
          var appId = $("#hiddenApplicationid").val();
          //alert("privNo " + privNo);
          //alert("appId " + appId);
          $("#roleNo").find("option").remove();
          $.getJSON("json/appPrivRoleSelect.html",
	            {"aruvquery.privNo":privNo,"aruvquery.applicationid":appId},
	            function(roles){
	                  $('#roleNo').append( new Option('Select','') );
			          for(var i=0;i<roles.length;i++)
			          {
			              $('#roleNo').append( new Option(roles[i].roleId,roles[i].roleNo) );
			          }
			     }
		   );
      }
      
      function loadCUVSelect()
      {
          var appId = $("#hiddenApplicationid").val();
          $("#appuservalueid").find("option").remove();
          $.getJSON("json/getCUVSelect.html",
	            {"applicationId":appId},
	            function(cuvs){
	                  $('#appuservalueid').append( new Option('Select','') );
			          for(var i=0;i<cuvs.length;i++)
			          {
			              $('#appuservalueid').append( new Option(cuvs[i].appuservaluefieldname,cuvs[i].appuservalueid) );
			          }
			     }
		   );
      }
      
      function cuvEditRoles(formid)
      {
          $("#roleNo",formid).find("option").remove();
          loadCUVSelect();
      }
      
      function formatStatus(cellvalue,options,rowObject)
      {
          if (cellvalue == "1")
              return "Active";
          else
              return "Inactive";
      }
      
	  function checkGridOperation(response,postdata)
	  {
	      var gridresponse = gridresponse || {};
	      gridresponse = jQuery.parseJSON(response.responseText);
	      var msg = gridresponse["gridOperationMessage"];
	      //alert(msg);
          if (msg == 'Record Saved')
          {
              return [true,""];
          }
          else
	      {
	          return [false,msg];   
	      }
        
	  }

		
      $(document).ready(function() { 
    	  
    	  $('#cancellink').click(function() {
			  window.location = 'applicationList.html';
			});
    	  
    	  $.subscribe('apgrowselect', function(event,data) {
    	     alert("selected");
    	     var grid = event.originalEvent.grid; 
             var gr = grid.jqGrid('getGridParam','selrow');
             //alert("selected " + gr);
             if( gr != null ) grid.jqGrid('editGridRow',gr,{height:300,width:400,reloadAfterSubmit:true,beforeShowForm:updatePriv}); 
           
          });
          
      });
    </script>
    
</head>



<div id="loadingdiv" style="display:none;">
Loading Privleges.....
</div>



<s:url id="saveApp" action="saveApp"/> 
<s:url id="list" action="applicationList"/> 

<s:form name="appForm" action="saveApp" method="post" validate="true">
<s:hidden name="application.applicationid" id="hiddenApplicationid"/>
<sj:tabbedpanel id="apptabs">
    <sj:tab id="tab1" target="basic" label="Basic"/>
    <sj:tab id="tab2" target="options" label="Options"/>
    <sj:tab id="tab3" target="privs" label="Privs"/>
    <sj:tab id="tab4" target="appowners" label="App Owners"/>
    <sj:tab id="tab5" target="approleowners" label="App Role Owners"/>
    <sj:tab id="tab6" target="uservalues" label="User Values"/>
    <div id="basic">
        <s:textfield required="true" label="Short Name" name="application.appShortname" size="60"/>
        <s:textfield required="true" label="Long Name" name="application.appLongname" size="60"/>
        <s:textarea label="Description" cols="58" rows="4" name="application.appDesc"/>
        <s:select 
           name="application.activeFlag"
           list="#{'1':'Active','0':'Inactive'}"
           value="application.activeFlag"
           required="true"
           label="Status"
        />
        
    </div>
    <div id="options">
        <s:textfield label="Href" size="60" name="application.appHref"/>
        <s:select 
           name="application.appIsintranetpublicflag"
           list="#{'1':'Yes','0':'No'}"
           value="application.appIsintranetpublicflag"
           required="true"
           label="Intranet Public"
        />
        <s:select 
           name="application.appHreflaunchmethodid"
           list="#{'1':'Yes','0':'No'}"
           value="application.appHreflaunchmethodid"
           required="true"
           label="Launch Method"
        />
        <s:select 
           name="application.appHrefversion"
           list="#{'1':'Yes','0':'No'}"
           value="application.appHrefversion"
           required="true"
           label="Href Version"
        />
    </div>
    <div id="privs">
        <s:url id="appPrivGrid" action="json/appPrivGridList">
            <s:param name="applicationId" value="%{application.applicationid}"/>
        </s:url>
        <s:url id="gridEditAppPrivs" action="json/gridEditAppPrivs">
    		<s:param name="applicationId" value="%{application.applicationid}"/>
		</s:url>
		<s:url id="loadprivselectoptions" action="json/loadPrivSelectOptions">
        </s:url>
		<sjg:grid 
			id="appprivgrid" 
			caption="Application Privs" 
			dataType="json" 
			href="%{appPrivGrid}"
			gridModel="applicationprivs"
			pager="true" 
			rownumbers="false"
			navigator="true"
	    	navigatorAddOptions="{height:300,width:400,recreateForm:true,reloadAfterSubmit:true,beforeInitData:addAppPriv,beforeSubmit:setAppPrivModel,afterSubmit:checkGridOperation}"
	    	navigatorEditOptions="{height:300,width:400,recreateForm:true,reloadAfterSubmit:true,beforeInitData:updateAppPriv,beforeSubmit:setAppPrivModel,afterSubmit:checkGridOperation}"
	    	navigatorEdit="true"
	    	navigatorView="false"
	    	navigatorSearch="false"
	    	navigatorDelete="false"
			editurl="%{gridEditAppPrivs}"
	    	editinline="false"
		>
			<sjg:gridColumn 
			    key="true"
			    name="applicationprivid" 
			    index="applicationprivid" 
			    title="ID" 
			    formatter="integer"
			    editable="false"
			    hidden="true"
			    sortable="false"/>
			<sjg:gridColumn 
			    name="privNo" 
			    index="privNo" 
			    title="PrivNo"
			    editable="true"
			    editoptions="{dataUrl:'%{loadprivselectoptions}',buildSelect:privSelect}"
			    hidden="true"
				editrules="{edithidden:true,required:true}" 
				edittype="select"
			    sortable="false"/>
			<sjg:gridColumn 
			    name="privId" 
			    index="privId" 
			    title="PrivId"
			    editable="false"
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
    
    <div id="appowners">
        <s:url id="appownersurl" action="json/appOwnersGridList">
            <s:param name="applicationId" value="%{application.applicationid}"/>
        </s:url>
        <s:url id="gridEditAppOwners" action="json/gridEditAppOwners">
		    <s:param name="applicationId" value="%{application.applicationid}"/>
		</s:url>
		
		<sjg:grid 
			id="appownersgrid" 
			caption="Application Owners" 
			dataType="json" 
			href="%{appownersurl}"
			gridModel="appOwnerList"
			pager="true" 
			rownumbers="false"
			navigator="true"
	    	navigatorAddOptions="{height:300,width:400,reloadAfterSubmit:true,beforeShowForm:addAppOwner,beforeSubmit:setAppOwnerModel,afterSubmit:checkGridOperation}"
	    	navigatorEditOptions="{height:300,width:400,reloadAfterSubmit:true,beforeShowForm:updateAppOwner,beforeSubmit:setAppOwnerModel,afterSubmit:checkGridOperation}"
	    	navigatorEdit="true"
	    	navigatorView="false"
	    	navigatorSearch="false"
	    	navigatorDelete="false"
	    	editurl="%{gridEditAppOwners}"
	    	editinline="false"
		>
		<sjg:gridColumn 
			    key="true"
			    name="applicationownid" 
			    index="applicationownid" 
			    title="ID" 
			    formatter="integer"
			    editable="false"
			    hidden="true"
			    sortable="false"/>
		
		<sjg:gridColumn 
			    name="owner" 
			    index="owner" 
			    title="App Owner"
			    editable="true"
			    editrules="{required:true}" 
			    sortable="true"/>
			    
	    <sjg:gridColumn 
			    name="firstName" 
			    index="firstName" 
			    title="First Name"
			    editrules="{required:true}" 
			    editable="true"
			    sortable="true"/>
			    
	    <sjg:gridColumn 
			    name="lastName" 
			    index="lastName" 
			    title="Last Name"
			    editrules="{required:true}"
			    editable="true"
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
    
    <div id="approleowners">
        <s:url id="approleownersurl" action="json/appRoleOwnersGridList">
            <s:param name="applicationId" value="%{application.applicationid}"/>
        </s:url>
        <s:url id="gridEditAppRoleOwners" action="json/gridEditAppRoleOwners">
		    <s:param name="applicationId" value="%{application.applicationid}"/>
		</s:url>
		<s:url id="appprivroleselect" action="json/appPrivRoleSelect">
            <s:param name="aruvquery.applicationid" value="%{application.applicationid}"/>
        </s:url>
		
		<sjg:grid 
			id="approleownersgrid" 
			caption="Application Role Owners" 
			dataType="json" 
			href="%{approleownersurl}"
			gridModel="appRoleOwnerList"
			pager="true" 
			rownumbers="false"
			navigator="true"
	    	navigatorAddOptions="{height:300,width:400,recreateForm:true,reloadAfterSubmit:true,beforeInitData:addAppRoleOwner,beforeSubmit:setAppRoleOwnerModel,afterSubmit:checkGridOperation}"
	    	navigatorEditOptions="{height:300,width:400,recreateForm:true,reloadAfterSubmit:true,beforeInitData:updateAppRoleOwner,beforeSubmit:setAppRoleOwnerModel,afterSubmit:checkGridOperation}"
	    	navigatorEdit="true"
	    	navigatorView="false"
	    	navigatorSearch="false"
	    	navigatorDelete="false"
	    	editurl="%{gridEditAppRoleOwners}"
	    	editinline="false"
	    	
		>
		<sjg:gridColumn 
			    key="true"
			    name="approleownid" 
			    index="approleownid" 
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
			    editrules="{edithidden:true,required:true}" 
			    edittype="select"
			    editoptions="{dataUrl:'%{appprivroleselect}',buildSelect:getAppPrivRoleSelect}"
			    sortable="false"/>
			    
		<sjg:gridColumn 
			    name="owner" 
			    index="owner" 
			    title="App Owner"
			    editable="true"
			    editrules="{required:true}" 
			    sortable="true"/>
			    
	    <sjg:gridColumn 
			    name="firstName" 
			    index="firstName" 
			    title="First Name"
			    editrules="{required:true}" 
			    editable="true"
			    sortable="true"/>
			    
	    <sjg:gridColumn 
			    name="lastName" 
			    index="lastName" 
			    title="Last Name"
			    editrules="{required:true}"
			    editable="true"
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
    
    <div id="uservalues">
        <s:url id="appuservaluesurl" action="json/appPrivRoleUserValuesList">
            <s:param name="applicationId" value="%{application.applicationid}"/>
        </s:url>
        <s:url id="appprivselect" action="json/appPrivSelect">
            <s:param name="applicationId" value="%{application.applicationid}"/>
        </s:url>
        <s:url id="gridEditAppPrivRoleUserValues" action="json/gridEditAppPrivRoleUserValues">
		    <s:param name="applicationId" value="%{application.applicationid}"/>
		</s:url>
		<sjg:grid 
			id="appprivroleuv" 
			caption="Application User Values" 
			dataType="json" 
			href="%{appuservaluesurl}"
			gridModel="appRoleUserValues"
			pager="true" 
			rownumbers="false"
			navigator="true"
	    	navigatorAddOptions="{height:300,width:400,recreateForm:true,reloadAfterSubmit:true,beforeShowForm:cuvEditRoles,beforeInitData:addAppPrivRoleUV,beforeSubmit:setAppRoleUVModel,afterSubmit:checkGridOperation}"
	    	navigatorEditOptions="{height:300,width:400,recreateForm:true,reloadAfterSubmit:true,beforeInitData:updateAppPrivRoleUV,beforeSubmit:setAppRoleUVModel,afterSubmit:checkGridOperation}"
	    	navigatorEdit="true"
	    	navigatorView="false"
	    	navigatorSearch="false"
	    	navigatorDelete="false"
	    	editurl="%{gridEditAppPrivRoleUserValues}"
	    	editinline="false"
		>
		    <sjg:gridColumn 
			    name="id" 
			    index="id" 
			    title="ID"
			    hidden="true"
			    editable="true"
			    sortable="true"/>
			    
			<sjg:gridColumn 
			    name="privId" 
			    index="privId" 
			    title="PrivId" 
			    editable="false"
			    sortable="false"/>
			    
			<sjg:gridColumn 
			    name="privNo" 
			    index="privNo" 
			    hidden="true"
			    title="PrivNo"
			    editable="true"
			    editrules="{edithidden:true,required:true}" 
			    edittype="select"
			    editoptions="{dataUrl:'%{appprivselect}',buildSelect:getAppPrivSelect,dataEvents:[{type:'change',fn:privSelectChanged}]}"
			    sortable="false"/>
			    
			<sjg:gridColumn 
			    name="roleId" 
			    index="roleId" 
			    title="RoleId"
			    sortable="true"/>
			    
			<sjg:gridColumn 
			    name="roleNo" 
			    index="roleNo" 
			    hidden="true"
			    title="roleNo"
			    editable="true"
			    editrules="{edithidden:true,required:true}" 
			    edittype="select"
			    editoptions="{value:'--:--'}"
			    sortable="false"/>
			    
			<sjg:gridColumn 
			    name="appuservalueid" 
			    index="appuservalueid" 
			    hidden="true"
			    title="appuservalueid"
			    editable="true"
			    editrules="{edithidden:true,required:true}" 
			    edittype="select"
			    editoptions="{value:'--:00'}"
			    sortable="false"/>
			    
			<sjg:gridColumn 
			    name="appuservaluefieldname" 
			    index="appuservaluefieldname" 
			    title="App Field Name"
			    editable="false"
			    sortable="true"/>
			    
			<sjg:gridColumn 
			    name="appuservaluetitle" 
			    index="appuservaluetitle" 
			    title="App Field Title"
			    editable="false"
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
