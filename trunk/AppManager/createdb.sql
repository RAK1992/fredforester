create tablespace appmanager
datafile '/home/oracle/app/oracle/oradata/orcl/appmanager_01.dbf'
size 1G
autoextend on
next 10m
maxsize 2G;


create user appmanager identified by abcd1234 default tablespace appmanager;

grant create session to appmanager;
grant create table to appmanager;
grant create view to appmanager;
grant create trigger to appmanager;
grant create procedure to appmanager;
grant create sequence to appmanager;
grant create synonym to appmanager;
grant unlimited tablespace to appmanager;
