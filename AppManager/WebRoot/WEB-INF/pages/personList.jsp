<%@ include file="/common/taglibs.jsp"%>


<head>

    <title><fmt:message key="mainMenu.title"/></title>
    <meta name="heading" content=""/>
    <meta name="menu" content="MainMenu"/>
    
    <script>
    
    function setPersonModel(postdata, formid)
    {
  	  //alert("beforesubmit");
  	  postdata['person.firstName'] = postdata['firstName'];
  	  postdata['person.lastName'] = postdata['lastName'];
  	  postdata['person.email'] = postdata['email'];
      return [true,""];
  	  
    }
    
	
	function checkGridOperation(response,postdata)
	{
	    var gridresponse = gridresponse || {};
	    gridresponse = jQuery.parseJSON(response.responseText);
	    var msg = gridresponse["gridOperationMessage"];
	    //alert(msg);
        if (msg == 'Person Saved')
        {
            return [true,""];
        }
        else
	    {
	        return [false,msg];   
	    }
        
	}
	    
    $(document).ready(function() { 
       //alert("onready");
       $.subscribe('rowselect', function(event,data) {
         var row = $("#gridtable").jqGrid('getRowData',event.originalEvent.id);
         var appuservalueid = row['id'];
         $("#gridinfo").html('<p>Loading..... ID : '+appuservalueid+'</p>');
         //alert("selected " + roleno);
         window.location = "editPerson.html?id=" + appuservalueid;
       });
    });
    </script>
    
</head>


<s:url id="editurl" action="json/gridEditPerson"/> 
<s:url id="remoteurl" action="json/personList"/> 
<center>
    <sjg:grid 
    	id="gridtable" 
    	caption="People" 
    	dataType="json" 
    	href="%{remoteurl}"
    	pager="true" 
    	gridModel="persons"
    	rowList="10,15,20"
    	rowNum="20"
    	editurl="%{editurl}"
    	rownumbers="false"
    	navigator="true"
    	navigatorDelete="false"
    	navigatorSearchOptions="{sopt:['cn','bw','eq']}"
    	navigatorAddOptions="{reloadAfterSubmit:true,beforeSubmit:setPersonModel,afterSubmit:checkGridOperation}"
    	navigatorEdit="false"
    	editinline="false"
    	onSelectRowTopics="rowselect" 
    >
    	<sjg:gridColumn 
    	    name="id" 
    	    search="false" 
    	    index="id" 
    	    title="ID" 
    	    hidden="true"
    	    formatter="integer" 
    	    sortable="false"/>
    	<sjg:gridColumn 
    	    name="firstName" 
    	    index="firstName" 
    	    title="FirstName" 
    	    sortable="true"
    	    editable="true" 
    	    width="275"
            editrules="{required: true}" 
    	    />
    	<sjg:gridColumn 
    	    name="lastName" 
    	    index="lastName" 
    	    title="LastName" 
    	    sortable="true"
    	    editable="true" 
    	    width="300"
    	    hidden="false"
            editrules="{required: true}" 
    	    />
    	 <sjg:gridColumn 
    	    name="email" 
    	    index="email" 
    	    title="Email" 
    	    sortable="true"
    	    editable="true" 
    	    width="300"
    	    hidden="false"
            editrules="{required: true}" 
    	    />
    	
    	
    </sjg:grid>
    <div id="gridinfo" class="ui-widget-content ui-corner-all"><p>Edit Mode for Row :</p></div>
    </center>