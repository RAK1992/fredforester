DROP TABLE "APPMANAGER"."PERSON";
DROP SEQUENCE "APPMANAGER"."PERSON_SEQ";

CREATE TABLE "APPMANAGER"."PERSON" 
   (	
	"ID" NUMBER,
    "FIRST_NAME" VARCHAR2(256 BYTE),
    "LAST_NAME" VARCHAR2(256 BYTE),
    "EMAIL" VARCHAR2(256 BYTE),
    CONSTRAINT PERSON_PK PRIMARY KEY (ID),
    CONSTRAINT PERSON_UNIQUE_EMAIL UNIQUE (EMAIL)
) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  TABLESPACE "APPMANAGER" ;
  
insert into person (id,first_name,last_name,email) values(1,'Fred','Forester','fred@fred.com');
insert into person (id,first_name,last_name,email) values(2,'Margie','Forester','margie@fred.com');
insert into person (id,first_name,last_name,email) values(3,'Orlean','Forester','orf@fred.com');
insert into person (id,first_name,last_name,email) values(4,'Leon','Forester','leon@fred.com');
insert into person (id,first_name,last_name,email) values(5,'Noel','Forester','noel@fred.com');
insert into person (id,first_name,last_name,email) values(6,'Annie','Forester','annie@fred.com');
insert into person (id,first_name,last_name,email) values(7,'Jim','Moore','jmoore@fred.com');
insert into person (id,first_name,last_name,email) values(8,'Doris','Moore','doris@fred.com');
insert into person (id,first_name,last_name,email) values(9,'Louise','Moore','louise@fred.com');
insert into person (id,first_name,last_name,email) values(10,'Susie','Alexander','suea@fred.com');
insert into person (id,first_name,last_name,email) values(11,'Carolyn','Forester','auntc@fred.com');
insert into person (id,first_name,last_name,email) values(12,'Mark','Hartman','mark@fred.com');
insert into person (id,first_name,last_name,email) values(13,'George','Fine','george@fred.com');



CREATE SEQUENCE  "APPMANAGER"."PERSON_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 14 CACHE 20 NOORDER  NOCYCLE ;


