--Generar una nueva tabla.

CREATE USER paco WITH password '1234';

﻿-- Database: capacitacion

-- DROP DATABASE capacitacion;

CREATE DATABASE capacitacion
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'en_US.UTF-8'
       LC_CTYPE = 'en_US.UTF-8'
       CONNECTION LIMIT = -1;
GRANT CONNECT, TEMPORARY ON DATABASE capacitacion TO public;
GRANT ALL ON DATABASE capacitacion TO postgres;
GRANT ALL ON DATABASE capacitacion TO paco;
GRANT ALL PRIVILEGES ON DATABASE capacitacion TO paco;

CREATE TABLE log(
	keyAccess serial,
	userName varchar(50),
	token varchar(32),
	startDate timestamp,
	endDate timestamp,
	CONSTRAINT log_pk PRIMARY KEY (keyAccess)
);


CREATE TABLE usr(
	keyUser serial,
	userName varchar(50),
	userPass char(32),
	CONSTRAINT user_pk PRIMARY KEY (keyUser)
); 
INSERT INTO usr(username,userpass) 
     VALUES ('Vale',md5('vale123')),
            ('Felipe',md5('felipe123')),
            ('Andy',md5('andy123')),
            ('Miriam',md5('miriam123')),
            ('Joanna',md5('joanna123'));


CREATE TABLE gender(
    keyGender char,
    description varchar(15) NOT NULL,    
    CONSTRAINT gender_pk PRIMARY KEY (keyGender)
);
INSERT INTO gender(keyGender,description)
    VALUES ('M','Male'),('F','Female');


CREATE TABLE address(
    keyAddress serial NOT NULL,
    street varchar(30) NOT NULL,
    intNum varchar(5),
    extNum varchar(5) NOT NULL,
    cp varchar(5),
    colony varchar(30) NOT NULL,
    keyCity int,
    CONSTRAINT address_pk PRIMARY KEY (keyAddress)
);
ALTER TABLE address ADD CONSTRAINT address_fk FOREIGN KEY (keyCity) REFERENCES city(id);

INSERT INTO address(street, intnum, extnum, cp, colony)
     VALUES ('Fco. Pimentel', null, '209A', '38010', 'Fovissste'),
            ('Morelia', null, '319', '36500', 'San Cristobal'),
            ('Paso de la muerte',null,'2014','36550','Las Carmelitas');


CREATE TABLE job(
    keyJob serial,
    nameJob varchar(50) NOT NULL,
    CONSTRAINT job_pk PRIMARY KEY (keyJob)
);
INSERT INTO job(namejob)
      VALUES('Maneger'),
            ('Head of Department'),
            ('Techer'),
            ('Secretary'),
            ('Treasurer');


CREATE TABLE employee(
	keyEmployee serial,
	nameEmp varchar(50) NOT NULL,
	lastName varchar(50) NOT NULL,
	lastNameMom varchar(50),
	bornDate date NOT NULL,
	emailEmp varchar(50) NOT NULL,
    phone varchar(10) NOT NULL,
    rfc varchar(18),
    keyGender char,
    keyAddress int,
	keyJob int,
	keyUser int,
	CONSTRAINT employee_pk PRIMARY KEY (keyEmployee)
);
ALTER TABLE employee ADD CONSTRAINT employee_fk1 FOREIGN KEY (keyJob) REFERENCES job(keyJob);
ALTER TABLE employee ADD CONSTRAINT employee_fk2 FOREIGN KEY (keyUser) REFERENCES usr(keyUser);
ALTER TABLE employee ADD CONSTRAINT employee_fk3 FOREIGN KEY (keyGender) REFERENCES gender(keyGender);
ALTER TABLE employee ADD CONSTRAINT employee_fk4 FOREIGN KEY (keyAddress) REFERENCES address(keyAddress);

INSERT INTO employee(nameemp,lastname,lastnamemom,borndate,emailemp,phone, rfc, keygender, keyaddress, keyjob,keyuser) 
    VALUES ('Valeria','Caudillo', 'Matrinez','1995-01-29','14030085@itcelaya.edu.mx','4621137817','CAMA950129MGTDMR09','F',3,1,2),
           ('Felipe','Aguilar', 'Hernandez','1994-05-25','13030395@itcelaya.edu.mx','4611562817','AGHF940525HGTDMR15','M',1,2,3);



CREATE TABLE type_course(
    keyTypeCourse serial,
    nameTypeCourse varchar(30) NOT NULL,    
    description varchar(30),    
    CONSTRAINT type_course_pk PRIMARY KEY (keyTypeCourse)
);

CREATE TABLE course(
    keyCourse serial,
    nameCourse varchar(30) NOT NULL,    
    temary text,
    objetive text,    
    keyTypeCourse int,
    CONSTRAINT course_pk PRIMARY KEY (keyCourse)
);
ALTER TABLE course ADD CONSTRAINT course_fk FOREIGN KEY (keyTypeCourse) REFERENCES type_course(keyTypeCourse);


CREATE TABLE company(
    keyCompany serial,
    nameCompany varchar(30),
	emailCompany varchar(50),
    phone varchar(10),
    rfc varchar(18),
    bussinesTurn varchar(30),
    keyAddress int,    
    CONSTRAINT company_pk PRIMARY KEY (keyCompany)
);
ALTER TABLE company ADD CONSTRAINT company_fk FOREIGN KEY (keyAddress) REFERENCES address(keyAddress);


CREATE TABLE instructor(
    keyInstructor serial,
    nameInst varchar(30),
    lastName varchar(50),
	lastNameMom varchar(50),
	bornDate date,
	emailIns varchar(50),
    phone varchar(10),
    rfc varchar(18),
    keyGender char,
    keyAddress int,    
    keyCompany int,
    CONSTRAINT instructor_pk PRIMARY KEY (keyInstructor)
);
ALTER TABLE instructor ADD CONSTRAINT instructor_fk1 FOREIGN KEY (keyGender) REFERENCES gender(keyGender);
ALTER TABLE instructor ADD CONSTRAINT instructor_fk2 FOREIGN KEY (keyAddress) REFERENCES address(keyAddress);
ALTER TABLE instructor ADD CONSTRAINT instructor_fk3 FOREIGN KEY (keyCompany) REFERENCES company(keyCompany);

CREATE TABLE course_training(
    keyCourseTraining serial,
    keyCourse int,
    keyInstructor int,
    schedule text,
    startDate Date,
    endDate Date,
    price float,
    capacity smallint,
    hours smallint,
    place varchar(50),
    startHour varchar(30),
    endtHour varchar(30)
);
ALTER TABLE course_training ADD CONSTRAINT course_training_pk PRIMARY KEY (keyCourseTraining, keyCurse)
ALTER TABLE course_training ADD CONSTRAINT course_training_fk1 FOREIGN KEY (keyCourse) REFERENCES course(keyCourse);
ALTER TABLE course_training ADD CONSTRAINT course_training_fk2 FOREIGN KEY (keyInstructor) REFERENCES instructor(keyInstructor);



CREATE TABLE history(
    keyHistory serial,
    keyCourseTraining int,
    keyCourse int,
    keyEmployee int,
    calification float
);
ALTER TABLE history ADD CONSTRAINT history_pk PRIMARY KEY (keyHistory, keyCourseTraining, keyCourse, keyEmployee);
ALTER TABLE history ADD CONSTRAINT history_fk1 FOREIGN KEY (keyCourseTraining,keyCourse) REFERENCES course_training(keyCourseTraining,keyCourse);
ALTER TABLE history ADD CONSTRAINT history_fk3 FOREIGN KEY (keyEmployee) REFERENCES employee(keyEmployee);
