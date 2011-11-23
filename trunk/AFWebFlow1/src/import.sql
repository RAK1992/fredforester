
CREATE SEQUENCE HOTEL_SEQ AS INTEGER START WITH 1 INCREMENT BY 1;

INSERT INTO APP_USER VALUES (-2,'0','0','','Denver','US','80210','CO','0','matt@raibledesigns.com','1','Matt','Raible','d033e22ae348aeb5660fc2140aec35850c4da997','Not a female kitty.','','adminuser',1,'http://raibledesigns.com');
INSERT INTO APP_USER VALUES (-1,'0','0','','Denver','US','80210','CO','0','matt_raible@yahoo.com','1','Tomcat','User','12dea96fec20593566ab75692c9949596833adc9','A male kitty.','','appuser',1,'http://tomcat.apache.org');

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

CREATE SEQUENCE PERSON_SEQ AS INTEGER START WITH 14 INCREMENT BY 1;

insert into customer(id,username,password,name,email,firstname,lastname) values(1,'fforester','mypassword','Fred Forester','fred@fred.com','Fred','Forester');
insert into customer(id,username,password,name,email,firstname,lastname) values(2,'aforester','mypassword','Ared Aforester','fred@fred.com','Ared','Aforester');
insert into customer(id,username,password,name,email,firstname,lastname) values(3,'bforester','mypassword','Bred Aforester','fred@fred.com','Bred','Bforester');
insert into customer(id,username,password,name,email,firstname,lastname) values(4,'cforester','mypassword','Cred Aforester','fred@fred.com','Cred','Cforester');
insert into customer(id,username,password,name,email,firstname,lastname) values(5,'dforester','mypassword','Dred Aforester','fred@fred.com','Dred','Dforester');
insert into customer(id,username,password,name,email,firstname,lastname) values(6,'eforester','mypassword','Ered Aforester','fred@fred.com','Ered','Eforester');
insert into customer(id,username,password,name,email,firstname,lastname) values(7,'fforester','mypassword','Fred Aforester','fred@fred.com','Fred','Fforester');
insert into customer(id,username,password,name,email,firstname,lastname) values(8,'gforester','mypassword','Gred Aforester','fred@fred.com','Gred','Gforester');
insert into customer(id,username,password,name,email,firstname,lastname) values(9,'hforester','mypassword','Hred Aforester','fred@fred.com','Hred','Hforester');
insert into customer(id,username,password,name,email,firstname,lastname) values(10,'iforester','mypassword','Ired Aforester','fred@fred.com','Ired','Iforester');
insert into customer(id,username,password,name,email,firstname,lastname) values(11,'jforester','mypassword','Jred Aforester','fred@fred.com','Jred','Jforester');
insert into customer(id,username,password,name,email,firstname,lastname) values(12,'kforester','mypassword','Kred Aforester','fred@fred.com','Kred','Kforester');
insert into customer(id,username,password,name,email,firstname,lastname) values(13,'lforester','mypassword','Lred Aforester','fred@fred.com','Lred','Lforester');
insert into customer(id,username,password,name,email,firstname,lastname) values(14,'mforester','mypassword','Mred Aforester','fred@fred.com','Mred','Mforester');
insert into customer(id,username,password,name,email,firstname,lastname) values(15,'nforester','mypassword','Nred Aforester','fred@fred.com','Nred','Nforester');
insert into customer(id,username,password,name,email,firstname,lastname) values(16,'oforester','mypassword','Ored Aforester','fred@fred.com','Ored','Oforester');
insert into customer(id,username,password,name,email,firstname,lastname) values(17,'pforester','mypassword','Pred Aforester','fred@fred.com','Pred','Pforester');
insert into customer(id,username,password,name,email,firstname,lastname) values(18,'qforester','mypassword','Qred Aforester','fred@fred.com','Qred','Qforester');
insert into customer(id,username,password,name,email,firstname,lastname) values(19,'rforester','mypassword','Rred Aforester','fred@fred.com','Rred','Rforester');
insert into customer(id,username,password,name,email,firstname,lastname) values(20,'sforester','mypassword','Sred Aforester','fred@fred.com','Sred','Sforester');
insert into customer(id,username,password,name,email,firstname,lastname) values(21,'tforester','mypassword','Tred Aforester','fred@fred.com','Tred','Tforester');
insert into customer(id,username,password,name,email,firstname,lastname) values(22,'uforester','mypassword','Ured Aforester','fred@fred.com','Ured','Uforester');


CREATE SEQUENCE CUSTOMER_SEQ AS INTEGER START WITH 2 INCREMENT BY 1;

insert into SECURITY_QUESTION(id,question) values(1,'Favorite Pet');
insert into SECURITY_QUESTION(id,question) values(2,'City Born in');
insert into SECURITY_QUESTION(id,question) values(3,'Mothers Maiden Name');

CREATE SEQUENCE SECURITY_QUESTION_SEQ AS INTEGER START WITH 4 INCREMENT BY 1;

CREATE SEQUENCE CUSTOMER_SECURITY_QUESTION_SEQ AS INTEGER START WITH 1 INCREMENT BY 1;
