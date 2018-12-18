
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
 
 CREATE INDEX on extension_file (extension);