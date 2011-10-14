<%@ include file="/common/taglibs.jsp"%>


<head>

    <title><fmt:message key="mainMenu.title"/></title>
    <meta name="heading" content=""/>
    <meta name="menu" content="MainMenu"/>
    
    <script>
    

    
    function setApplicationModel(postdata, formid)
    {
  	  //alert("beforesubmit");
  	  postdata['application.appShortname'] = postdata['appShortname'];
  	  postdata['application.appLongname'] = postdata['appLongname'];
  	  postdata['application.appDesc'] = postdata['appDesc'];
  	  postdata['application.activeFlag'] = postdata['activeFlag'];
  	  
      return [true,""];
  	  
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
         var appid = row['applicationid'];
         $("#gridinfo").html('<p>Loading..... AppID : '+appid+'</p>');
         window.location = "editApp.html?id=" + appid;
         //alert("selected " + appid);
         
       });
       
    });
    </script>
    
</head>

<!--  href="%{remoteurl}"  -->

<s:url id="editurl" action="updateApplication"/> 
<s:url id="remoteurl" action="json/applicationList"/> 
<center>
    <sjg:grid 
    	id="gridtable" 
    	caption="Applications" 
    	dataType="json" 
    	href="%{remoteurl}"
    	pager="true" 
    	gridModel="applications"
    	rowList="10,15,20"
    	rowNum="20"
    	editurl="%{editurl}"
    	rownumbers="false"
    	navigator="true"
    	navigatorSearchOptions="{sopt:['cn','bw','eq']}"
    	navigatorAddOptions="{reloadAfterSubmit:true,beforeSubmit:setApplicationModel}"
    	navigatorEdit="false"
    	navigatorDelete="false"
    	editinline="false"
    	onSelectRowTopics="rowselect" 
    >
    	<sjg:gridColumn 
    	    name="applicationid" 
    	    search="false" 
    	    index="applicationid" 
    	    title="ID" 
    	    hidden="true"
    	    formatter="integer" 
    	    sortable="false"/>
    	<sjg:gridColumn 
    	    name="appShortname" 
    	    index="appShortname" 
    	    title="ShortName" 
    	    sortable="true"
    	    editable="true" 
    	    width="275"
            editrules="{required: true}" 
            
    	    />
    	<sjg:gridColumn 
    	    name="appLongname" 
    	    index="appLongname" 
    	    title="LongName" 
    	    sortable="true"
    	    editable="true" 
    	    width="300"
    	    hidden="true"
            editrules="{edithidden:true,required: true}" 
    	    />
    	<sjg:gridColumn 
    	    name="appDesc" 
    	    index="appDesc" 
    	    title="AppDesc" 
    	    sortable="true"
    	    editable="true"
    	    width="400" 
    	    edittype="textarea"
            editrules="{required: true}" 
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
    
