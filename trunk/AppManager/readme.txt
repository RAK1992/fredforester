
Handling UNIQUE Contraints
note: Be sure the table has a unique contraint defined

1) change the daohibernate

getHibernateTemplate().saveOrUpdate(o);
// necessary to throw a DataIntegrityViolation and catch it in Manager
getHibernateTemplate().flush();
return o;

2) change the save method prototype in the manager
 throws DuplicateRecordException

3) change the manager impl

try {
        return yourDao.saveRecord(o);
} catch (DataIntegrityViolationException e) {
       //e.printStackTrace();
       log.warn(e.getMessage());
       throw new DuplicateRecordException("Priv '" + o.getPrivId() + "' already exists!");
} catch (JpaSystemException e) { // needed for JPA
       //e.printStackTrace();
       log.warn(e.getMessage());
       throw new DuplicateRecordException("Priv '" + o.getPrivId() + "' already exists!");
}

4) change the action
try
{
    yourManager.saveRecord(record);
}
catch(DuplicateRecordException dre)
{
    gridOperationMessage = dre.getMessage();
    return SUCCESS;
}

4a) add getter/setter for String gridOperationMessage

5) add a json action for gridEditRecordname

<!-- called when the submit button is clicked on the grid add form -->
<action name="gridEditPerson" class="com.mycompany.webapp.action.PersonAction" method="gridEditPerson">
    <result type="json">
       <param name="includeProperties">gridOperationMessage</param>
    </result>
</action>

6) add function checkGridOperation(response,postdata) to the list page

7) wire it to the grid 
navigatorAddOptions="{reloadAfterSubmit:true,beforeSubmit:setPersonModel,afterSubmit:checkGridOperation}"

8) change the editurl
<s:url id="editurl" action="json/gridEditPerson"/> 