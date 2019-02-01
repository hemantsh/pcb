CREATE KEYSPACE fileexchange
    WITH REPLICATION = {
        'class': 'SimpleStrategy', 
        'replication_factor': 1
    };
    
 USE fileexchange;
 
 CREATE TABLE services (
    id int PRIMARY KEY,
 	name text 
 );
 
 CREATE TABLE file_types (
    id int PRIMARY KEY,
 	type text
 );
 
 CREATE TABLE service_files (
    service_id int,
    filetype_id int,
 	service text ,
 	file text,
 	PRIMARY KEY ( service_id , filetype_id )
 );
 
 CREATE INDEX on service_files (service);
 CREATE INDEX on service_files (filetype_id);
 
 CREATE TABLE extensions (
    id int PRIMARY KEY,
 	name text
 );
 
 CREATE TABLE extension_file (
    extension_id int,
    filetype_id int,
 	extension text,
 	file text,
 	PRIMARY KEY ( extension_id, filetype_id)
 );
 CREATE INDEX on extension_file (filetype_id); 

 CREATE INDEX on extension_file (extension);
 
 
 CREATE TABLE project (
 
 	project_id text,
 	version timeuuid,
 	
 	customer_name text,
 	customer_id text,
 	customer_email text,
 	service_type text,
 	layer_count int,
 	pcb_class text,
 	board_type text,
 	itar text,
 	turntime_quantity map<int,int>,
 	design_specification text,
 	zipfile_name text,
 	zipfile_size text,
 	create_date timestamp,
 	modified_date timestamp,
 	errors map<text,text>,
 	
 	PRIMARY KEY ( (project_id, version) )
 );
 
 CREATE TABLE project_files (
 	
 	project_id text,
 	version timeuuid,
 	name text,
 	
 	size text,
 	file_date timestamp,
 	format text,
 	step text,
 	context text,
 	polarity text,
 	side text,
 	layer_order int,
 	start_name text,
 	end_name text,
 	copper_weight text,
 	layer_name text,
 	attributes map<text, text>,
 	create_date timestamp,
 	modified_date timestamp,
 	errors map<text,text>,
 	
 	PRIMARY KEY ( (project_id, version), name )
 );
 
 
 
 
 
 