delete from application;

insert into application (applicationid,app_shortname,app_desc,activeflag) values(1,'My App For Billing','This is a fancy Billing App',1);
insert into application (applicationid,app_shortname,app_desc,activeflag) values(2,'My App For Accounting','This is a fancy Accounting App',1);
insert into application (applicationid,app_shortname,app_desc,activeflag) values(3,'My App For Human Resources','This is a fancy HR App',1);
insert into application (applicationid,app_shortname,app_desc,activeflag) values(4,'My App For Medical Records','This is a fancy EMR App',1);

DROP SEQUENCE "APPMANAGER"."APPLICATION_SEQ";
CREATE SEQUENCE  "APPMANAGER"."APPLICATION_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 5 CACHE 20 NOORDER NOCYCLE ;

insert into appuservalues (appuservalueid,appuservaluefieldname,appuservaluetitle,appuservaluesource,appuservaluesourcelocation,activeflag) values(1,'Field1','FieldTitle1','source field','source table',1);
insert into appuservalues (appuservalueid,appuservaluefieldname,appuservaluetitle,appuservaluesource,appuservaluesourcelocation,activeflag) values(2,'Field2','FieldTitle2','source field','source table',1);
insert into appuservalues (appuservalueid,appuservaluefieldname,appuservaluetitle,appuservaluesource,appuservaluesourcelocation,activeflag) values(3,'Field3','FieldTitle3','source field','source table',1);
insert into appuservalues (appuservalueid,appuservaluefieldname,appuservaluetitle,appuservaluesource,appuservaluesourcelocation,activeflag) values(4,'Field4','FieldTitle4','source field','source table',1);
insert into appuservalues (appuservalueid,appuservaluefieldname,appuservaluetitle,appuservaluesource,appuservaluesourcelocation,activeflag) values(5,'Field5','FieldTitle5','source field','source table',1);

DROP SEQUENCE "APPMANAGER"."APPUSERVALUES_SEQ";

CREATE SEQUENCE  "APPMANAGER"."APPUSERVALUES_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 6 CACHE 20 NOORDER  NOCYCLE ;

delete from priv;

insert into priv (priv_no,priv_id,priv_desc,activeflag,ISFORCEDREDIRECT) values(1,'PRIVID01','Priv 01',1,0);
insert into priv (priv_no,priv_id,priv_desc,activeflag,ISFORCEDREDIRECT) values(2,'PRIVID02','Priv 02',1,0);
insert into priv (priv_no,priv_id,priv_desc,activeflag,ISFORCEDREDIRECT) values(3,'PRIVID03','Priv 03',1,0);
insert into priv (priv_no,priv_id,priv_desc,activeflag,ISFORCEDREDIRECT) values(4,'PRIVID04','Priv 04',1,0);
insert into priv (priv_no,priv_id,priv_desc,activeflag,ISFORCEDREDIRECT) values(5,'PRIVID05','Priv 05',1,0);
insert into priv (priv_no,priv_id,priv_desc,activeflag,ISFORCEDREDIRECT) values(6,'PRIVID06','Priv 06',1,0);
insert into priv (priv_no,priv_id,priv_desc,activeflag,ISFORCEDREDIRECT) values(7,'PRIVID07','Priv 07',1,0);
insert into priv (priv_no,priv_id,priv_desc,activeflag,ISFORCEDREDIRECT) values(8,'PRIVID08','Priv 08',1,0);
insert into priv (priv_no,priv_id,priv_desc,activeflag,ISFORCEDREDIRECT) values(9,'PRIVID09','Priv 09',1,0);
insert into priv (priv_no,priv_id,priv_desc,activeflag,ISFORCEDREDIRECT) values(10,'PRIVID10','Priv 10',1,0);
insert into priv (priv_no,priv_id,priv_desc,activeflag,ISFORCEDREDIRECT) values(11,'PRIVID11','Priv 11',1,0);
insert into priv (priv_no,priv_id,priv_desc,activeflag,ISFORCEDREDIRECT) values(12,'PRIVID12','Priv 12',1,0);
insert into priv (priv_no,priv_id,priv_desc,activeflag,ISFORCEDREDIRECT) values(13,'PRIVID13','Priv 13',1,0);
insert into priv (priv_no,priv_id,priv_desc,activeflag,ISFORCEDREDIRECT) values(14,'PRIVID14','Priv 14',1,0);
insert into priv (priv_no,priv_id,priv_desc,activeflag,ISFORCEDREDIRECT) values(15,'PRIVID15','Priv 15',1,0);
insert into priv (priv_no,priv_id,priv_desc,activeflag,ISFORCEDREDIRECT) values(16,'PRIVID16','Priv 16',1,0);
insert into priv (priv_no,priv_id,priv_desc,activeflag,ISFORCEDREDIRECT) values(17,'PRIVID17','Priv 17',1,0);
insert into priv (priv_no,priv_id,priv_desc,activeflag,ISFORCEDREDIRECT) values(18,'PRIVID18','Priv 18',1,0);
insert into priv (priv_no,priv_id,priv_desc,activeflag,ISFORCEDREDIRECT) values(19,'PRIVID19','Priv 19',1,0);
insert into priv (priv_no,priv_id,priv_desc,activeflag,ISFORCEDREDIRECT) values(20,'PRIVID20','Priv 20',1,0);
insert into priv (priv_no,priv_id,priv_desc,activeflag,ISFORCEDREDIRECT) values(21,'PRIVID21','Priv 21',1,0);
insert into priv (priv_no,priv_id,priv_desc,activeflag,ISFORCEDREDIRECT) values(22,'PRIVID22','Priv 22',1,0);
insert into priv (priv_no,priv_id,priv_desc,activeflag,ISFORCEDREDIRECT) values(23,'PRIVID23','Priv 23',1,0);
insert into priv (priv_no,priv_id,priv_desc,activeflag,ISFORCEDREDIRECT) values(24,'PRIVID24','Priv 24',1,0);
insert into priv (priv_no,priv_id,priv_desc,activeflag,ISFORCEDREDIRECT) values(25,'PRIVID25','Priv 25',1,0);
insert into priv (priv_no,priv_id,priv_desc,activeflag,ISFORCEDREDIRECT) values(26,'PRIVID26','Priv 26',1,0);
insert into priv (priv_no,priv_id,priv_desc,activeflag,ISFORCEDREDIRECT) values(27,'PRIVID27','Priv 27',1,0);
insert into priv (priv_no,priv_id,priv_desc,activeflag,ISFORCEDREDIRECT) values(28,'PRIVID28','Priv 28',1,0);
insert into priv (priv_no,priv_id,priv_desc,activeflag,ISFORCEDREDIRECT) values(29,'PRIVID29','Priv 29',1,0);
insert into priv (priv_no,priv_id,priv_desc,activeflag,ISFORCEDREDIRECT) values(30,'PRIVID30','Priv 30',1,0);
insert into priv (priv_no,priv_id,priv_desc,activeflag,ISFORCEDREDIRECT) values(31,'PRIVID31','Priv 31',1,0);

DROP SEQUENCE "APPMANAGER"."PRIV_SEQ";
CREATE SEQUENCE  "APPMANAGER"."PRIV_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 32 CACHE 20 NOORDER NOCYCLE;

delete from roles;

insert into roles (role_no,role_id,role_desc,activeflag,ROLETYPEID) values(1,'ROLEID01','Role 01',1,1);
insert into roles (role_no,role_id,role_desc,activeflag,ROLETYPEID) values(2,'ROLEID02','Role 02',1,1);
insert into roles (role_no,role_id,role_desc,activeflag,ROLETYPEID) values(3,'ROLEID03','Role 03',1,1);
insert into roles (role_no,role_id,role_desc,activeflag,ROLETYPEID) values(4,'ROLEID04','Role 04',1,1);
insert into roles (role_no,role_id,role_desc,activeflag,ROLETYPEID) values(5,'ROLEID05','Role 05',1,1);
insert into roles (role_no,role_id,role_desc,activeflag,ROLETYPEID) values(6,'ROLEID06','Role 06',1,1);
insert into roles (role_no,role_id,role_desc,activeflag,ROLETYPEID) values(7,'ROLEID07','Role 07',1,1);
insert into roles (role_no,role_id,role_desc,activeflag,ROLETYPEID) values(8,'ROLEID08','Role 08',1,1);
insert into roles (role_no,role_id,role_desc,activeflag,ROLETYPEID) values(9,'ROLEID09','Role 09',1,1);
insert into roles (role_no,role_id,role_desc,activeflag,ROLETYPEID) values(10,'ROLEID10','Role 10',1,1);
insert into roles (role_no,role_id,role_desc,activeflag,ROLETYPEID) values(11,'ROLEID11','Role 11',1,1);
insert into roles (role_no,role_id,role_desc,activeflag,ROLETYPEID) values(12,'ROLEID12','Role 12',1,1);
insert into roles (role_no,role_id,role_desc,activeflag,ROLETYPEID) values(13,'ROLEID13','Role 13',1,1);
insert into roles (role_no,role_id,role_desc,activeflag,ROLETYPEID) values(14,'ROLEID14','Role 14',1,1);
insert into roles (role_no,role_id,role_desc,activeflag,ROLETYPEID) values(15,'ROLEID15','Role 15',1,1);
insert into roles (role_no,role_id,role_desc,activeflag,ROLETYPEID) values(16,'ROLEID16','Role 16',1,1);
insert into roles (role_no,role_id,role_desc,activeflag,ROLETYPEID) values(17,'ROLEID17','Role 17',1,1);
insert into roles (role_no,role_id,role_desc,activeflag,ROLETYPEID) values(18,'ROLEID18','Role 18',1,1);
insert into roles (role_no,role_id,role_desc,activeflag,ROLETYPEID) values(19,'ROLEID19','Role 19',1,1);
insert into roles (role_no,role_id,role_desc,activeflag,ROLETYPEID) values(20,'ROLEID20','Role 20',1,1);
insert into roles (role_no,role_id,role_desc,activeflag,ROLETYPEID) values(21,'ROLEID21','Role 21',1,1);
insert into roles (role_no,role_id,role_desc,activeflag,ROLETYPEID) values(22,'ROLEID22','Role 22',1,1);
insert into roles (role_no,role_id,role_desc,activeflag,ROLETYPEID) values(23,'ROLEID23','Role 23',1,1);
insert into roles (role_no,role_id,role_desc,activeflag,ROLETYPEID) values(24,'ROLEID24','Role 24',1,1);
insert into roles (role_no,role_id,role_desc,activeflag,ROLETYPEID) values(25,'ROLEID25','Role 25',1,1);
insert into roles (role_no,role_id,role_desc,activeflag,ROLETYPEID) values(26,'ROLEID26','Role 26',1,1);
insert into roles (role_no,role_id,role_desc,activeflag,ROLETYPEID) values(27,'ROLEID27','Role 27',1,1);
insert into roles (role_no,role_id,role_desc,activeflag,ROLETYPEID) values(28,'ROLEID28','Role 28',1,1);
insert into roles (role_no,role_id,role_desc,activeflag,ROLETYPEID) values(29,'ROLEID29','Role 29',1,1);
insert into roles (role_no,role_id,role_desc,activeflag,ROLETYPEID) values(30,'ROLEID30','Role 30',1,1);
insert into roles (role_no,role_id,role_desc,activeflag,ROLETYPEID) values(31,'ROLEID31','Role 31',1,1);

DROP SEQUENCE "APPMANAGER"."ROLES_SEQ";
CREATE SEQUENCE  "APPMANAGER"."ROLES_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 32 CACHE 20 NOORDER NOCYCLE;

