CREATE TABLE "APPMANAGER"."APP_USER" (
  "ID" NUMBER(20,0) NOT NULL,
  "ACCOUNT_EXPIRED" CHAR NOT NULL,
  "ACCOUNT_LOCKED" CHAR NOT NULL,
  "ADDRESS" VARCHAR2(150) DEFAULT NULL,
  "CITY" VARCHAR2(50) DEFAULT NULL,
  "COUNTRY" VARCHAR2(100) DEFAULT NULL,
  "POSTAL_CODE" VARCHAR2(15) DEFAULT NULL,
  "PROVINCE" VARCHAR2(100) DEFAULT NULL,
  "CREDENTIALS_EXPIRED" CHAR NOT NULL,
  "EMAIL" VARCHAR2(255) NOT NULL,
  "ACCOUNT_ENABLED" CHAR DEFAULT NULL,
  "FIRST_NAME" VARCHAR2(50) NOT NULL,
  "LAST_NAME" VARCHAR2(50) NOT NULL,
  "PASSWORD" VARCHAR2(255) NOT NULL,
  "PASSWORD_HINT" VARCHAR2(255) DEFAULT NULL,
  "PHONE_NUMBER" VARCHAR2(255) DEFAULT NULL,
  "USERNAME" VARCHAR2(50) NOT NULL,
  "VERSION" NUMBER(11, 0) DEFAULT NULL,
  "WEBSITE" VARCHAR2(255) DEFAULT NULL,
  CONSTRAINT APP_USER_PK PRIMARY KEY (ID),
  CONSTRAINT APP_USER_UNIQUE_EMAIL UNIQUE (EMAIL),
  CONSTRAINT APP_USER_UNIQUE_USERNAME UNIQUE (USERNAME)
) SEGMENT CREATION DEFERRED 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  TABLESPACE "APPMANAGER" ;

INSERT INTO APP_USER VALUES (1,'0','0','','Denver','US','80210','CO','0','matt@raibledesigns.com','1','Matt','Raible','d033e22ae348aeb5660fc2140aec35850c4da997','Not a female kitty.','','admin',1,'http://raibledesigns.com');
INSERT INTO APP_USER VALUES (2,'0','0','','Denver','US','80210','CO','0','matt_raible@yahoo.com','1','Tomcat','User','12dea96fec20593566ab75692c9949596833adc9','A male kitty.','','user',1,'http://tomcat.apache.org');

CREATE SEQUENCE  "APPMANAGER"."APP_USER_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 3 CACHE 20 NOORDER  NOCYCLE ;