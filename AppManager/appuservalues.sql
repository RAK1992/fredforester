insert into appuservalues (appuservalueid,appuservaluefieldname,appuservaluetitle,appuservaluesource,appuservaluesourcelocation,activeflag) values(1,'Field1','FieldTitle1','source field','source table',1);
insert into appuservalues (appuservalueid,appuservaluefieldname,appuservaluetitle,appuservaluesource,appuservaluesourcelocation,activeflag) values(2,'Field2','FieldTitle2','source field','source table',1);
insert into appuservalues (appuservalueid,appuservaluefieldname,appuservaluetitle,appuservaluesource,appuservaluesourcelocation,activeflag) values(3,'Field3','FieldTitle3','source field','source table',1);
insert into appuservalues (appuservalueid,appuservaluefieldname,appuservaluetitle,appuservaluesource,appuservaluesourcelocation,activeflag) values(4,'Field4','FieldTitle4','source field','source table',1);
insert into appuservalues (appuservalueid,appuservaluefieldname,appuservaluetitle,appuservaluesource,appuservaluesourcelocation,activeflag) values(5,'Field5','FieldTitle5','source field','source table',1);

DROP SEQUENCE "APPMANAGER"."APPUSERVALUES_SEQ";

CREATE SEQUENCE  "APPMANAGER"."APPUSERVALUES_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 6 CACHE 20 NOORDER  NOCYCLE ;