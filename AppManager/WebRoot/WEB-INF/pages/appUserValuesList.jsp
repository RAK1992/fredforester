<%@ include file="/common/taglibs.jsp"%>


<head>

    <title><fmt:message key="mainMenu.title"/></title>
    <meta name="heading" content=""/>
    <meta name="menu" content="MainMenu"/>
    
    <script>
    
    function setAppUserValueModel(postdata, formid)
    {
  	  //alert("beforesubmit");
  	  postdata['appUserValue.appuservaluefieldname'] = postdata['appuservaluefieldname'];
  	  postdata['appUserValue.appuservaluetitle'] = postdata['appuservaluetitle'];
  	  postdata['appUserValue.activeFlag'] = postdata['activeFlag'];
      return [true,""];
  	  
    }
    
    function checkGridOperation(response,postdata)
	{
	    //alert("Here");
	    var gridresponse = gridresponse || {};
	    gridresponse = jQuery.parseJSON(response.responseText);
	    var msg = gridresponse["gridOperationMessage"];
        if (msg == 'AppUserValue Added')
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
         var appuservalueid = row['appuservalueid'];
         $("#gridinfo").html('<p>Loading..... ID : '+appuservalueid+'</p>');
         //alert("selected " + roleno);
         window.location = "editAppUserValue.html?id=" + appuservalueid;
         
         
       });
       
    });
    </script>
    
</head>


<s:url id="editurl" action="json/gridEditAppUserValue"/> 
<s:url id="remoteurl" action="json/appUserValuesList"/> 
<center>
    <sjg:grid 
    	id="gridtable" 
    	caption="User Values" 
    	dataType="json" 
    	href="%{remoteurl}"
    	pager="true" 
    	gridModel="appUserValues"
    	rowList="10,15,20"
    	rowNum="20"
    	editurl="%{editurl}"
    	rownumbers="false"
    	navigator="true"
    	navigatorSearchOptions="{sopt:['cn','bw','eq']}"
    	navigatorAddOptions="{reloadAfterSubmit:true,beforeSubmit:setAppUserValueModel,afterSubmit:checkGridOperation}"
    	navigatorEdit="false"
    	navigatorDelete="false"
    	editinline="false"
    	onSelectRowTopics="rowselect" 
    >
    	<sjg:gridColumn 
    	    name="appuservalueid" 
    	    search="false" 
    	    index="appuservalueid" 
    	    title="ID" 
    	    hidden="true"
    	    formatter="integer" 
    	    sortable="false"/>
    	<sjg:gridColumn 
    	    name="appuservaluefieldname" 
    	    index="appuservaluefieldname" 
    	    title="FieldName" 
    	    sortable="true"
    	    editable="true" 
    	    width="275"
            editrules="{required: true}" 
    	    />
    	<sjg:gridColumn 
    	    name="appuservaluetitle" 
    	    index="appuservaluetitle" 
    	    title="FieldTitle" 
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