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
 
 CREATE TABLE report (
    project_id text,
    version int,
    service_id int,
    customer_id text,
    project_files set<text>,
    summary text,
    filetype_filename map<int, text>,
    errors set<text>,
    notes set<text>,
    status text,
    PRIMARY KEY ( project_id, version )
 );