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
        company text,
        part_rev text,
        assembly_spec text,
        part_number text,
        rnumber text,
 	setId text,
 	customer_name text,
 	customer_id text,
 	customer_email text,
 	service_type text,
 	layer_count int,
 	pcb_class text,
 	board_type text,
 	itar boolean,
        nofly boolean,
 	assembly_turntime_quantity text,
        fabrication_turntime_quantity text,
 	design_specification text,
 	zipfile_name text,
 	zipfile_size text,
 	create_date timestamp,
 	modified_date timestamp,
 	errors map<text,text>,
 	
 	PRIMARY KEY ( (project_id, version) )
 );
 
CREATE INDEX on project (project_id);
CREATE INDEX on project (customer_id);
CREATE INDEX on project (rnumber);
CREATE INDEX on project (customer_email);
CREATE INDEX on project (zipfile_name);

 CREATE TABLE project_files ( 	
 	project_id text,
 	version timeuuid,
 	name text,
 	type text,
 	size text,
       selected boolean,
 	file_date timestamp,
 	format text,
 	step text,
        status text,
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
 
 CREATE INDEX on project_files (project_id);
 
CREATE TABLE difference_report(
        project_id text,
        version timeuuid,
        differences set<text>,
        PRIMARY KEY(project_id)
);

 CREATE TABLE filetype_extensions (
	id uuid,
	file_type text,
	extensions set<text>, 
	PRIMARY KEY(id,file_type)
);

CREATE INDEX on filetype_extensions (extensions);

CREATE TABLE service_filetypes (
	service_id int,
        file_type text,
        PRIMARY KEY ( service_id ,file_type)
);
