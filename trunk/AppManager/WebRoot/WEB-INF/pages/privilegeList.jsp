<%@ include file="/common/taglibs.jsp"%>


<head>

    <title><fmt:message key="mainMenu.title"/></title>
    <meta name="heading" content=""/>
    <meta name="menu" content="MainMenu"/>
    
    <script>
    
    function setPrivilegeModel(postdata, formid)
    {
  	  //alert("beforesubmit");
  	  postdata['privilege.privId'] = postdata['privId'];
  	  postdata['privilege.privDesc'] = postdata['privDesc'];
  	  postdata['privilege.activeFlag'] = postdata['activeFlag'];
  	  
      return [true,""];
  	  
    }
    
    function checkGridOperation(response,postdata)
	{
	    //alert("Here");
	    var gridresponse = gridresponse || {};
	    gridresponse = jQuery.parseJSON(response.responseText);
	    var msg = gridresponse["gridOperationMessage"];
        if (msg == 'Privilege Added')
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
         var privno = row['privNo'];
         $("#gridinfo").html('<p>Loading..... PrivID : '+privno+'</p>');
         //alert("selected " + privno);
         window.location = "editPrivilege.html?id=" + privno;
         
         
       });
       
    });
    </script>
    
</head>


<s:url id="editurl" action="json/gridEditPrivilege"/> 
<s:url id="remoteurl" action="json/privilegeList"/> 
<center>
    <sjg:grid 
    	id="gridtable" 
    	caption="Privileges" 
    	dataType="json" 
    	href="%{remoteurl}"
    	pager="true" 
    	gridModel="privileges"
    	rowList="10,15,20"
    	rowNum="20"
    	editurl="%{editurl}"
    	rownumbers="false"
    	navigator="true"
    	navigatorSearchOptions="{sopt:['cn','bw','eq']}"
    	navigatorAddOptions="{reloadAfterSubmit:true,beforeSubmit:setPrivilegeModel,afterSubmit:checkGridOperation}"
    	navigatorEdit="false"
    	navigatorDelete="false"
    	editinline="false"
    	onSelectRowTopics="rowselect" 
    >
    	<sjg:gridColumn 
    	    name="privNo" 
    	    search="false" 
    	    index="privNo" 
    	    title="ID" 
    	    hidden="true"
    	    formatter="integer" 
    	    sortable="false"/>
    	<sjg:gridColumn 
    	    name="privId" 
    	    index="privId" 
    	    title="PrivId" 
    	    sortable="true"
    	    editable="true" 
    	    width="275"
            editrules="{required: true}" 
    	    />
    	<sjg:gridColumn 
    	    name="privDesc" 
    	    index="privDesc" 
    	    title="privDesc" 
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