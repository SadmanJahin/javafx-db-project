create database immune_history
drop database immune_history

drop table user_table
drop table doctor
drop table appointment
drop table consult


select * from user_table
select * from doctor
select * from appointment
select * from consult




CREATE TABLE user_table
(
user_id int IDENTITY(1,1) PRIMARY KEY,
user_name varchar(50) NOT NULL,
age int NOT NULL CHECK (age >=0 AND age <=120),
gender varchar(10),
date_of_birth date NOT NULL,CHECK(date_of_birth <= GETDATE()),
phone varchar(50) CHECK (phone NOT LIKE '%[^0-9]%'),
email varchar(50) NULL ,
address varchar(200) NULL ,
zip_code int NOT NULL
)


INSERT INTO user_table(user_name, age,gender,date_of_birth,phone,email,address,zip_code) 
VALUES ('Rahim', 32,'Male','10-12-1988','0152111234','rahim@gmail.com','12/A,Gulshan',1217),
       ('Karim', 30,'Male','10-12-1990','0172413254','kahim@gmail.com','13/B,Dhanmondi',1217),
       ('Mahim', 28,'Male','10-12-1992','0152131430','mahim@yahoo.com','122/A,Malibagh',1219),
	   ('Salma', 28,'Female','10-12-1992','015531430','salma@yahoo.com','122/A,Malibagh',1219),
       ('Jamshed',14,'Male','10-12-2006','0174501531','Jamshed@gmail.com','20/C,Moghbazar',1219),
       ('Joy', 31,'Male','10-12-1989','0182171274','joy123@gmail.com','14/D,Rampura',1217),
	   ('Rahima', 28,'Female','10-12-1992','0142131430','rahima@yahoo.com','122/A,Malibagh',1219),
       ('Shuvo', 40,'Male','10-12-1980','0192961234','shuvo@gmail.com','110C/A,Banani',1217),
       ('Hashim', 55,'Male','10-12-1965','0164311254','hashim@yahoo.com','15/E,Malibagh',1219),
       ('Rahim', 47,'Male','10-12-1975','0152100230','rahim@site.com','15/A Mohammadpur',1219)



Create Table doctor
(doctor_id int IDENTITY(1,1) PRIMARY KEY,
doctor_name varchar(50) NOT NULL,
age int NOT NULL CHECK (age >=0 AND age <=120),
degree varchar(50) NOT NULL,
hospital varchar(50) NOT NULL,
email varchar(50) NULL,
address varchar(200) NULL  DEFAULT  'Dhaka',
phone varchar(50) NOT NULL
)

INSERT INTO doctor(doctor_name,age,phone,degree,hospital,email,address)
VALUES ('Dr.Aminur Rahman', 42,0152811234,'MBBS','LabAid Hospital','amin@gmail.com','12/A,Gulshan'),
       ('Dr.Javed Omar', 30,0172513254,'MBBS,FCPS','GreenLife Hospital','javed@gmail.com','13/B,Dhanmondi'),
       ('Dr.Karim Haidar', 32,0152151425,'FRCS','HolyFamily Hospital','haidar@yahoo.com','122/A,Malibagh'),
       ('Dr.Imran Tahir',29,0174101531,'BDS','Apollo Hospital','tahir@gmail.com','20/C,Moghbazar'),
       ('Dr.Junaid Bhuiyan', 31,0182171374,'MBBS','Kurmitola Hospital','junayed123@gmail.com','14/D,Rampura'),
       ('Dr.Shuvo Ahmed', 40,0162941234,'MBBS,FRCS','Alpha Hospital','shuvo@gmail.com','110C/A,Banani'),
       ('Dr Mostofa Kaiser', 52,0194351254,'Phd,MBChB','ICD Hospital','kaiser@yahoo.com','15/E,Malibagh'),
       ('Dr.Yousuf Mia', 62,0142100260,'BMBS','ICD Hospital','yousuf@site.com','15/A Mohammadpur') 


Create Table consult
(
consult_id int identity(1,1) PRIMARY KEY,
doctor_id int NOT NULL FOREIGN KEY REFERENCES doctor (doctor_id),
)

INSERT INTO consult(doctor_id)
VALUES(1),
      (1),
	  (3),
	  (5),
	  (4)

Create Table appointment
(
ap_sl_no int IDENTITY(1,1) PRIMARY KEY,
user_id int NOT NULL FOREIGN KEY REFERENCES user_table(user_id),
consult_id int 
constraint fk_consult_id foreign key(consult_id)
	  references consult(consult_id),
date_of_appointment_taken datetime NULL,
date_of_consult datetime NOT NULL,
appoint_active BIT NULL DEFAULT 1
)




INSERT INTO appointment(user_id,consult_id,date_of_appointment_taken,date_of_consult,appoint_active)
VALUES (1,1,convert(datetime,'18-06-20 10:34:01 PM',5),convert(datetime,'25-06-20 10:34:09 PM',5),1),
       (2,2,convert(datetime,'19-06-20 09:23:02 PM',5),convert(datetime,'26-06-20 09:34:09 PM',5),1),
       (4,3,convert(datetime,'18-06-20 11:15:04 PM',5),convert(datetime,'27-06-20 09:34:09 PM',5),1),
       (1,1,convert(datetime,'18-06-20 08:11:05 PM',5),convert(datetime,'28-06-20 09:34:09 PM',5),1),
       (5,5,convert(datetime,'18-06-20 07:28:03 PM',5),convert(datetime,'28-06-20 09:34:09 PM',5),1)




CREATE TABLE ASSISTANT(
	--assistant_id int AUTO_INCREMENT PRIMARY KEY,
	assistant_id int IDENTITY(1,1) PRIMARY KEY,
	assistant_name varchar(255) NOT NULL,
    	mobile varchar(50) NOT NULL,
    	degree varchar(50),
		hospital varchar(255),
	email varchar(255),
	address varchar(255),
	zipcode varchar(255)
)
drop table ASSISTANT
select * from ASSISTANT


INSERT INTO assistant(assistant_name, mobile, degree,hospital, email, address, zipcode )
VALUES 
('Aminur Rahman','152811234','MBBS','ICD Hospital','amin@gmail.com','12/A Gulshan','1217'), 
('Javed Omar','172513254','MBBS','Square Hospital','javed@gmail.com','13/B Dhanmondi','1217'),
('Karim Haidar','152151425','MBBS','Square Hospital','haidar@yahoo.com','122/A Malibagh','1219'),
('Imran Tahir','174101531','STUDENT','Square Hospital','tahir@gmail.com','20/C Moghbazar','1219'),
('Junaid Bhuiyan','182171374','STUDENT','Green life Hospital','junayed123@gmail.com','14/D Rampura','1217'),
('Shuvo Ahmed','162941234','STUDENT','Green life Hospital','shuvo@gmail.com','110C/A Banani','1217'),
('Mostofa Kaiser','194351254','STUDENT','Green life Hospital','kaiser@yahoo.com','15/E Malibagh','1219'),
('Yousuf Mia','142100260','STUDENT','ICD Hospital','yousuf@site.com','15/A Mohammadpur','1219')





CREATE TABLE REFERENCE(
	ref_id varchar(255) NOT NULL PRIMARY KEY,
	referred_by_doctor_id int
)

INSERT INTO reference( ref_id, referred_by_doctor_id ) VALUES
('#FF2351','6'),
('#FF2352','7'),
('#FF2353','8'),
('#FF2354','6'),
('#FF2355','7')



CREATE TABLE lab_test(
    --test_id int AUTO_INCREMENT PRIMARY KEY,
	test_id int IDENTITY(1,1) PRIMARY KEY,
    
    --doctor_id int NOT NULL,
	--FOREIGN KEY( doctor_id ) REFERENCES doctor( doctor_id ),
	doctor_id int NOT NULL FOREIGN KEY REFERENCES doctor( doctor_id ),
    
    --ref_id varchar(255) NOT NULL,
	--FOREIGN KEY( ref_id ) REFERENCES reference( ref_id ),
	ref_id varchar(255) NOT NULL FOREIGN KEY REFERENCES reference( ref_id ),
	
	--consult_id int NOT NULL,
	--FOREIGN KEY( consult_id ) REFERENCES doctor_relationship_consult( consult_id ),
	consult_id int NOT NULL FOREIGN KEY REFERENCES consult( consult_id ),

	assistant_id int NOT NULL FOREIGN KEY REFERENCES ASSISTANT( assistant_id ),
	
	test_name varchar(255) NOT NULL,
    report varchar(255) NULL,
	test_center varchar(255) NOT NULL,
	date_of_test datetime,
	date_of_result datetime
)

INSERT INTO lab_test( doctor_id, ref_id, consult_id, assistant_id , test_name, report, test_center, date_of_test, date_of_result ) VALUES
('1','#FF2351','1','1','COVID-19','POSITIVE', 'ICD Hospital',	        '2020-06-18 22:34:01',	    '2020-06-25 22:34:09'),
('1','#FF2352','2','2','COVID-19','POSITIVE', 'Square Hospital',		'2020-06-19 21:23:02',		'2020-06-26 21:34:09'),
('3','#FF2353','3','3','COVID-19','POSITIVE', 'Square Hospital',		'2020-06-18 23:15:04',		'2020-06-27 21:34:09'),
('5','#FF2354','4','4','COVID-19','POSITIVE', 'Square Hospital',		'2020-06-18 20:11:05',		'2020-06-28 21:34:09'),
('4','#FF2355','5','5','COVID-19','POSITIVE', 'Green life Hospital',	'2020-06-18 19:28:03',	    '2020-06-28 22:34:09')


drop table lab_test
select * from lab_test










--..........................................................................................
--.............................................................................................


select* from disease_type
select * from disease_name
select * from symptom
select * from medicine_generic_name
select * from medicine_local_name
select * from disease_info


drop table disease_type
drop table disease_name
drop table disease_info
drop table medicine_generic_name
drop table medicine_local_name
drop table disease_info
drop table symptom




create table disease_type(
        disease_type_id int not null identity(1,1) constraint pk_disease_type primary key,
		disease_type varchar(255)
)

BULK INSERT immune_history.dbo.disease_type
from 'D:\Resource\AUST\CSE 3.1\CSE 3104 Database Lab\project\immune_history\disease_type.csv'
with(
     FIELDTERMINATOR=',',
	 ROWTERMINATOR='\n',
	 FIRSTROW=2
	 )


create table disease_name(
       disease_id int not null identity(1,1) constraint pk_disease_name primary key,
    	 disease_name varchar(255) not null,
		 disease_type_id int
		 constraint fk_disease_type foreign key(disease_type_id)
	  references disease_type(disease_type_id) default '3'
	
	)


--insert into disease_name(disease_name) values ('unknown1')

BULK INSERT immune_history.dbo.disease_name
from 'D:\Resource\AUST\CSE 3.1\CSE 3104 Database Lab\project\immune_history\disease_name.csv'
with(
     FIELDTERMINATOR=',',
	 ROWTERMINATOR='\n',
	 FIRSTROW=2
	 )

create table symptom(
         symptom_id int identity(1,1) constraint pk_symptom_id primary key not null,
		 symptom_name varchar(255)
)


BULK INSERT immune_history.dbo.symptom
from 'D:\Resource\AUST\CSE 3.1\CSE 3104 Database Lab\project\immune_history\symptom.csv'
with(
     FIELDTERMINATOR=',',
	 ROWTERMINATOR='\n',
	 FIRSTROW=2
	 )

create table medicine_generic_name(
       medicine_id int not null identity(1,1) constraint pk_medicine_generic_name primary key,
    	 medicine_generic_name varchar(255) not null
			  )
BULK INSERT immune_history.dbo.medicine_generic_name
from 'D:\Resource\AUST\CSE 3.1\CSE 3104 Database Lab\project\immune_history\medicine_generic_name.csv'
with(
     FIELDTERMINATOR=',',
	 ROWTERMINATOR='\n',
	 FIRSTROW=2
	 )


create table medicine_local_name(   
      medicine_id int not null 
	  constraint fk_medicine_local_name foreign key(medicine_id)
	  references medicine_generic_name(medicine_id),
	  medicine_local_name varchar(255)

	  )

create table disease_info(
      disease_id int not null 
	  constraint fk_disease_info foreign key(disease_id)
	  references disease_name(disease_id),
	  symptom_id int 
	  constraint fk_symptom_id foreign key(symptom_id)
	  references symptom(symptom_id),
	  min_period int,
	  max_period int,
	  medicine_id int 
	  constraint fk_medicine_disease foreign key(medicine_id)
	  references medicine_generic_name(medicine_id) default '1',
	   anti_medicine_id int 
	  constraint fk_anti_medicine_disease foreign key(medicine_id)
	  references medicine_generic_name(medicine_id) default '1'

	  )
BULK INSERT immune_history.dbo.disease_info
from 'D:\Resource\AUST\CSE 3.1\CSE 3104 Database Lab\project\immune_history\disease_info.csv'
with(
     FIELDTERMINATOR=',',
	 ROWTERMINATOR='\n',
	 FIRSTROW=2
	 )





--............................................................................
--.................................................................................



Create Table prescription
(
ap_sl_no int NOT NULL FOREIGN KEY REFERENCES appointment (ap_sl_no),
symptoms varchar(250) NULL,
test_name varchar(250) NULL,
medicine_generic_name varchar(255),
dosage varchar(40) NULL,
dosage_taking_date datetime NULL,
dosage_ending_date datetime NOT NULL,
remarks varchar(200)
)

Create Table doctor_rating
(
consult_id int NOT NULL FOREIGN KEY REFERENCES consult(consult_id),
doctor_id int NOT NULL FOREIGN KEY REFERENCES doctor (doctor_id),
rating int NOT NULL CHECK (rating >=0  AND rating <=5),

)
drop table doctor_rating
select * from doctor_rating



Create Table vaxine_history
(
user_id int NOT NULL FOREIGN KEY REFERENCES user_table(user_id),
vaxine_id int NOT NULL FOREIGN KEY REFERENCES medicine_generic_name(medicine_id),
vaxination_date date NOT NULL,
)