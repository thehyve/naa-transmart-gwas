--------------------------------------------------------
--  File created - Thursday-October-25-2012   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table BIO_DATA_OBSERVATION
--------------------------------------------------------

  CREATE TABLE "BIOMART"."BIO_DATA_OBSERVATION" 
   (	"BIO_DATA_ID" NUMBER(18,0), 
	"BIO_OBSERVATION_ID" NUMBER(18,0), 
	"ETL_SOURCE" VARCHAR2(100 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS NOLOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "BIOMART" ;
--------------------------------------------------------
--  DDL for Index BIO_DATA_OBSERVATION_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "BIOMART"."BIO_DATA_OBSERVATION_PK" ON "BIOMART"."BIO_DATA_OBSERVATION" ("BIO_DATA_ID", "BIO_OBSERVATION_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 NOLOGGING COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "INDX" ;
--------------------------------------------------------
--  Constraints for Table BIO_DATA_OBSERVATION
--------------------------------------------------------

  ALTER TABLE "BIOMART"."BIO_DATA_OBSERVATION" MODIFY ("BIO_DATA_ID" NOT NULL ENABLE);
 
  ALTER TABLE "BIOMART"."BIO_DATA_OBSERVATION" MODIFY ("BIO_OBSERVATION_ID" NOT NULL ENABLE);

