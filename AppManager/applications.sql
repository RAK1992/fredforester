delete from application;

insert into application (applicationid,app_shortname,app_desc,activeflag) values(1,'My App For Billing','This is a fancy Billing App',1);
insert into application (applicationid,app_shortname,app_desc,activeflag) values(2,'My App For Accounting','This is a fancy Accounting App',1);
insert into application (applicationid,app_shortname,app_desc,activeflag) values(3,'My App For Human Resources','This is a fancy HR App',1);
insert into application (applicationid,app_shortname,app_desc,activeflag) values(4,'My App For Medical Records','This is a fancy EMR App',1);

DROP SEQUENCE "APPMANAGER"."APPLICATION_SEQ";
CREATE SEQUENCE  "APPMANAGER"."APPLICATION_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 5 CACHE 20 NOORDER NOCYCLE ;