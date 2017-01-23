DELETE FROM Maintenance;
INSERT INTO Maintenance(id,report_retention_days,run_at_hour,on_off) VALUES (1,30,4,false);

DELETE FROM EmailConfig;
INSERT INTO EmailConfig(Email_Config_ID,Host,port,smtps,user_name,password,email_from,email_from_friendly,success_subject,success_text,error_subject,error_text,on_off) VALUES (1,'localhost',25,false,'someuser','pass','byomesh.jha@eproxim.com','Byomesh Jha','Success','You have a report waiting','Failure','We are sorry. Your request failed to generate report.',false);

DELETE FROM AppUser;
INSERT INTO AppUser(user_id,user_password,first_name,last_name,email_address,user_role,user_active,user_type) VALUES(1,'admin','System','Administrator','admin@bcalawfirm.com','Administrator',true,0);
INSERT INTO AppUser(user_id,user_password,first_name,last_name,email_address,user_role,user_active,user_type) VALUES(2,'manager','Report','Manager','reporter@eproxim.com','Manager',true,0);
INSERT INTO AppUser(user_id,user_password,first_name,last_name,email_address,user_role,user_active,user_type) VALUES(3,'reader','Reader','User','reader@eproxim.com','User',true,0);


DELETE FROM Firm;
INSERT INTO firm(firm_id, firm_domain, date_time_created, description, email_address, primary_fax, secondary_fax, other_fax, name, primary_phone, secondary_phone, other_phone, web_site_url,document_path) VALUES (1, 'bcalawfirm.com', CURRENT_TIMESTAMP, 'BCA Law Firm is an Immigration Law Firm with Offices in Georgia and Tennessee.We specialize in U.S. immigration laws and offer our services in all 50 states of United States of America. We provide our services to persons both in the United States and overseas, who wish to come to the United States.', 'support@bcalawfirm.com', '678-261-0973','', '', 'BCA Law', '678-292-6111','770-674-0109', '', 'www.bcalawfirm.com','C:/Users/bjha/lms-file-upload/');

DELETE FROM CaseStatus;
INSERT INTO CaseStatus(Case_Status_ID, firm_id, Status,Case_Status_Desc) values (1,1,'On-Going','Appeal Pending etc.');
INSERT INTO CaseStatus(Case_Status_ID, firm_id, Status,Case_Status_Desc) values (2,1,'Active','Being worked on');
INSERT INTO CaseStatus(Case_Status_ID, firm_id, Status,Case_Status_Desc) values (3,1,'Dormant','Waiting for next steps due to natural course');
INSERT INTO CaseStatus(Case_Status_ID, firm_id, Status,Case_Status_Desc) values (4,1,'Complete','No further steps needed');
INSERT INTO CaseStatus(Case_Status_ID, firm_id, Status,Case_Status_Desc) values (5,1,'Closed','Archieved');

DELETE FROM CaseType;
INSERT INTO CaseType(case_type_id, firm_id, case_type_description, case_type_folder) values (1, 1, 'Adjustment Of Status', 'AOS');
INSERT INTO CaseType(case_type_id, firm_id, case_type_description, case_type_folder) values (2, 1, 'Adjustment Of Status Pre reg', 'AOS PRE-Reg');
INSERT INTO CaseType(case_type_id, firm_id, case_type_description, case_type_folder) values (3, 1, 'Adoptions', 'ADOPTIONS');
INSERT INTO CaseType(case_type_id, firm_id, case_type_description, case_type_folder) values (4, 1, 'Asylum', 'ASYLUM');
INSERT INTO CaseType(case_type_id, firm_id, case_type_description, case_type_folder) values (5, 1, 'B1 B2', 'B1 B2');
INSERT INTO CaseType(case_type_id, firm_id, case_type_description, case_type_folder) values (6, 1, 'Bond Recovery', 'BOND RECOVERY');
INSERT INTO CaseType(case_type_id, firm_id, case_type_description, case_type_folder) values (7, 1, 'Chattanooga', 'CHATTANOOGA');
INSERT INTO CaseType(case_type_id, firm_id, case_type_description, case_type_folder) values (8, 1, 'Consultations', 'CONSULTATIONS');
INSERT INTO CaseType(case_type_id, firm_id, case_type_description, case_type_folder) values (9, 1, 'DACA', 'DACA');
INSERT INTO CaseType(case_type_id, firm_id, case_type_description, case_type_folder) values (10, 1, 'DAPA', 'DAPA');
INSERT INTO CaseType(case_type_id, firm_id, case_type_description, case_type_folder) values (11, 1, 'Deportations Removals', 'DEPORTATIONS REMOVALS');
INSERT INTO CaseType(case_type_id, firm_id, case_type_description, case_type_folder) values (12, 1, 'Divorce', 'divorce');
INSERT INTO CaseType(case_type_id, firm_id, case_type_description, case_type_folder) values (13, 1, 'E-2', 'E-2');
INSERT INTO CaseType(case_type_id, firm_id, case_type_description, case_type_folder) values (14, 1, 'EB IV Perm', 'EB IV PERM');
INSERT INTO CaseType(case_type_id, firm_id, case_type_description, case_type_folder) values (15, 1, 'EB-1 NIW O Researcher', 'EB-1 NIW O RESEARCHER');
INSERT INTO CaseType(case_type_id, firm_id, case_type_description, case_type_folder) values (16, 1, 'EB-5', 'EB-5');
INSERT INTO CaseType(case_type_id, firm_id, case_type_description, case_type_folder) values (17, 1, 'F-1', 'F-1');
INSERT INTO CaseType(case_type_id, firm_id, case_type_description, case_type_folder) values (18, 1, 'FOIA', 'FOIA');
INSERT INTO CaseType(case_type_id, firm_id, case_type_description, case_type_folder) values (19, 1, 'GA Cases', 'GA cases');
INSERT INTO CaseType(case_type_id, firm_id, case_type_description, case_type_folder) values (20, 1, 'H-1Bs', 'H-1Bs');
INSERT INTO CaseType(case_type_id, firm_id, case_type_description, case_type_folder) values (21, 1, 'Humanitarian Parole', 'HUMANITARIAN PAROLE');


DELETE FROM FirmUser;
INSERT INTO FirmUser(firm_id,user_id,user_password,first_name,last_name,email_address,user_role,user_active,user_type) VALUES(1,1,'abcd1234','Bhavya','Chaudhary','bhavya@bcalawfirm.com','Administrator',true,1);
INSERT INTO FirmUser(firm_id,user_id,user_password,first_name,last_name,email_address,user_role,user_active,user_type) VALUES(1,2,'abcd1234','Jemma','Patel','jemma@eproxim.com','Manager',true,1);
INSERT INTO FirmUser(firm_id,user_id,user_password,first_name,last_name,email_address,user_role,user_active,user_type) VALUES(1,3,'abcd1234','Versha','Gupta','admin@eproxim.com','User',true,1);
//INSERT INTO FirmUser(firm_id,user_id,user_password,first_name,last_name,email_address,user_role,user_active,user_type) VALUES(1,4,'abcd1234','June','Crumpton','june@eproxim.com','User',true,1);
//INSERT INTO FirmUser(firm_id,user_id,user_password,first_name,last_name,email_address,user_role,user_active,user_type) VALUES(1,5,'abcd1234','Swati','Singh','swati@eproxim.com','User',true,1);


DELETE FROM Client;
INSERT INTO Client(client_id,first_name,last_name,email_address,Primary_Phone,Date_Time_Created) VALUES(1,'Sam','Gosling','sam.gosling@someemail.com','1234567890', (CURRENT_TIMESTAMP - INTERVAL '1 day'));
INSERT INTO Client(client_id,first_name,last_name,email_address,Primary_Phone,Date_Time_Created) VALUES(2,'Tom','Thick','tom.thick@someemail.com','1112223333', (CURRENT_TIMESTAMP - INTERVAL '1 month'));
INSERT INTO Client(client_id,first_name,last_name,email_address,Primary_Phone,Date_Time_Created) VALUES(3,'Prafful','Patel','prafful.patel@someemail.com','4445556666', (CURRENT_TIMESTAMP - INTERVAL '5 day'));
INSERT INTO Client(client_id,first_name,last_name,email_address,Primary_Phone,Date_Time_Created) VALUES(4,'Manisha','Pandey','manisha.pandey@someemail.com','2223334444', (CURRENT_TIMESTAMP - INTERVAL '12 day'));
INSERT INTO Client(client_id,first_name,last_name,email_address,Primary_Phone,Date_Time_Created) VALUES(5,'Charan','Singh','charan.singh@someemail.com','8889995555', (CURRENT_TIMESTAMP - INTERVAL '3 month'));
INSERT INTO Client(client_id,first_name,last_name,email_address,Primary_Phone,Date_Time_Created) VALUES(6,'Kusum','Kumari','kusum.kumari@someemail.com','7778884444', (CURRENT_TIMESTAMP - INTERVAL '15 day'));
INSERT INTO Client(client_id,first_name,last_name,email_address,Primary_Phone,Date_Time_Created) VALUES(7,'Sunil','Sampat','sunil.sampat@someemail.com','1231231234', (CURRENT_TIMESTAMP - INTERVAL '19 day'));
INSERT INTO Client(client_id,first_name,last_name,email_address,Primary_Phone,Date_Time_Created) VALUES(8,'Kiran','Rathore','kiran.rathore@someemail.com','3213214321', (CURRENT_TIMESTAMP - INTERVAL '2 month'));
INSERT INTO Client(client_id,first_name,last_name,email_address,Primary_Phone,Date_Time_Created) VALUES(9,'Suman','Jham','suman.jham@someemail.com','9089089087', (CURRENT_TIMESTAMP - INTERVAL '25 day'));
INSERT INTO Client(client_id,first_name,last_name,email_address,Primary_Phone,Date_Time_Created) VALUES(10,'Rohit','Jhunjhunwala','rohit.jhunjhunwala@someemail.com','7657657654', (CURRENT_TIMESTAMP - INTERVAL '21 day'));
INSERT INTO Client(client_id,first_name,last_name,email_address,Primary_Phone,Date_Time_Created) VALUES(11,'Gaurav','Patel','gaurav.patel@someemail.com','2342342345', (CURRENT_TIMESTAMP - INTERVAL '36 month'));
INSERT INTO Client(client_id,first_name,last_name,email_address,Primary_Phone,Date_Time_Created) VALUES(12,'Sanjeev','Tyagi','sanjeev.tyagi@someemail.com','5677655678', (CURRENT_TIMESTAMP - INTERVAL '45 day'));
INSERT INTO Client(client_id,first_name,last_name,email_address,Primary_Phone,Date_Time_Created) VALUES(13,'Peter','D Costa','peter.dcosta@someemail.com','5558882222', (CURRENT_TIMESTAMP - INTERVAL '61 day'));
INSERT INTO Client(client_id,first_name,last_name,email_address,Primary_Phone,Date_Time_Created) VALUES(14,'Gyan','Prakash','gyan.prakash@someemail.com','1112134545', (CURRENT_TIMESTAMP - INTERVAL '31 month'));
INSERT INTO Client(client_id,first_name,last_name,email_address,Primary_Phone,Date_Time_Created) VALUES(15,'Kaushalya','Patel','kaushalya.patel@someemail.com','7887887789', (CURRENT_TIMESTAMP - INTERVAL '57 day'));
INSERT INTO Client(client_id,first_name,last_name,email_address,Primary_Phone,Date_Time_Created) VALUES(16,'Sam1','Gosling','sam.gosling@someemail.com','1234567890', (CURRENT_TIMESTAMP - INTERVAL '1 day'));
INSERT INTO Client(client_id,first_name,last_name,email_address,Primary_Phone,Date_Time_Created) VALUES(17,'Tom1','Thick','tom.thick@someemail.com','1112223333', (CURRENT_TIMESTAMP - INTERVAL '1 month'));
INSERT INTO Client(client_id,first_name,last_name,email_address,Primary_Phone,Date_Time_Created) VALUES(18,'Prafful1','Patel','prafful.patel@someemail.com','4445556666', (CURRENT_TIMESTAMP - INTERVAL '5 day'));
INSERT INTO Client(client_id,first_name,last_name,email_address,Primary_Phone,Date_Time_Created) VALUES(19,'Manisha1','Pandey','manisha.pandey@someemail.com','2223334444', (CURRENT_TIMESTAMP - INTERVAL '12 day'));
INSERT INTO Client(client_id,first_name,last_name,email_address,Primary_Phone,Date_Time_Created) VALUES(20,'Charan1','Singh','charan.singh@someemail.com','8889995555', (CURRENT_TIMESTAMP - INTERVAL '3 month'));
INSERT INTO Client(client_id,first_name,last_name,email_address,Primary_Phone,Date_Time_Created) VALUES(21,'Kusum1','Kumari','kusum.kumari@someemail.com','7778884444', (CURRENT_TIMESTAMP - INTERVAL '15 day'));
INSERT INTO Client(client_id,first_name,last_name,email_address,Primary_Phone,Date_Time_Created) VALUES(22,'Sunil1','Sampat','sunil.sampat@someemail.com','1231231234', (CURRENT_TIMESTAMP - INTERVAL '19 day'));
INSERT INTO Client(client_id,first_name,last_name,email_address,Primary_Phone,Date_Time_Created) VALUES(23,'Kiran1','Rathore','kiran.rathore@someemail.com','3213214321', (CURRENT_TIMESTAMP - INTERVAL '2 month'));
INSERT INTO Client(client_id,first_name,last_name,email_address,Primary_Phone,Date_Time_Created) VALUES(24,'Suman1','Jham','suman.jham@someemail.com','9089089087', (CURRENT_TIMESTAMP - INTERVAL '25 day'));
INSERT INTO Client(client_id,first_name,last_name,email_address,Primary_Phone,Date_Time_Created) VALUES(25,'Rohit1','Jhunjhunwala','rohit.jhunjhunwala@someemail.com','7657657654', (CURRENT_TIMESTAMP - INTERVAL '21 day'));
INSERT INTO Client(client_id,first_name,last_name,email_address,Primary_Phone,Date_Time_Created) VALUES(26,'Gaurav1','Patel','gaurav.patel@someemail.com','2342342345', (CURRENT_TIMESTAMP - INTERVAL '36 month'));
INSERT INTO Client(client_id,first_name,last_name,email_address,Primary_Phone,Date_Time_Created) VALUES(27,'Sanjeev1','Tyagi','sanjeev.tyagi@someemail.com','5677655678', (CURRENT_TIMESTAMP - INTERVAL '45 day'));
INSERT INTO Client(client_id,first_name,last_name,email_address,Primary_Phone,Date_Time_Created) VALUES(28,'Peter1','D Costa','peter.dcosta@someemail.com','5558882222', (CURRENT_TIMESTAMP - INTERVAL '61 day'));
INSERT INTO Client(client_id,first_name,last_name,email_address,Primary_Phone,Date_Time_Created) VALUES(29,'Gyan1','Prakash','gyan.prakash@someemail.com','1112134545', (CURRENT_TIMESTAMP - INTERVAL '31 month'));
INSERT INTO Client(client_id,first_name,last_name,email_address,Primary_Phone,Date_Time_Created) VALUES(30,'Kaushalya1','Patel','kaushalya.patel@someemail.com','7887887789', (CURRENT_TIMESTAMP - INTERVAL '57 day'));
