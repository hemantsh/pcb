// services dml data insertions.
insert into services (id,name) values (1,'assembly');
insert into services (id,name) values (2,'fabrication');
insert into services (id,name) values (3,'fabrication and assembly');
insert into services (id,name) values (4,'flying probe assembly test');

// filetype_extensions dml data insertions.

insert into filetype_extensions (id, file_type, extensions) values  
(3fc19a70-79a8-11e9-b032-2b151ce6b001, 'drill' , {'drd','npt','drl'} );

insert into filetype_extensions (id, file_type, extensions) values  
(3fc19a70-79a8-11e9-b032-2b151ce6b002, 'bom' , {'xls'} );

insert into filetype_extensions (id, file_type, extensions) values  
(3fc19a70-79a8-11e9-b032-2b151ce6b003, 'x&y' , {} );

insert into filetype_extensions (id, file_type, extensions) values  
(3fc19a70-79a8-11e9-b032-2b151ce6b004, 'gerber or odb or ipc2581' , {} );

insert into filetype_extensions (id, file_type, extensions) values  
(3fc19a70-79a8-11e9-b032-2b151ce6b005, 'assembly_drawing' , {'pdf'} );

insert into filetype_extensions (id, file_type, extensions) values  
(3fc19a70-79a8-11e9-b032-2b151ce6b006, 'solder_mask' , {'gts'} );

insert into filetype_extensions (id, file_type, extensions) values  
(3fc19a70-79a8-11e9-b032-2b151ce6b007, 'silk_screen' , {'gto'} );

insert into filetype_extensions (id, file_type, extensions) values  
(3fc19a70-79a8-11e9-b032-2b151ce6b008, 'drawing or fabrication_drawing' , {} );

insert into filetype_extensions (id, file_type, extensions) values  
(3fc19a70-79a8-11e9-b032-2b151ce6b009, 'fabrication_drawing' , {'pdf'} );

insert into filetype_extensions (id, file_type, extensions) values  
(3fc19a70-79a8-11e9-b032-2b151ce6b010, 'drawing or assembly_drawing' , {} );

insert into filetype_extensions (id, file_type, extensions) values  
(3fc19a70-79a8-11e9-b032-2b151ce6b011, 'odb or ipc2581' , {} );


// service_filetypes dml data insertions.
insert into service_filetypes (service_id,file_type) values (1,'drill');
insert into service_filetypes (service_id,file_type) values (1,'bom');
insert into service_filetypes (service_id,file_type) values (1,'x&y');
insert into service_filetypes (service_id,file_type) values (1,'gerber or odb or ipc2581');
insert into service_filetypes (service_id,file_type) values (1,'drill');
insert into service_filetypes (service_id,file_type) values (1,'assembly_drawing');

insert into service_filetypes (service_id,file_type) values (2,'solder_mask');
insert into service_filetypes (service_id,file_type) values (2,'silk_screen');
insert into service_filetypes (service_id,file_type) values (2,'drill');
insert into service_filetypes (service_id,file_type) values (2,'fabrication_drawing');

insert into service_filetypes (service_id,file_type) values (3,'gerber or odb or ipc2581');
insert into service_filetypes (service_id,file_type) values (3,'fabrication_drawing');

insert into service_filetypes (service_id,file_type) values (4,'drill');
insert into service_filetypes (service_id,file_type) values (4,'assembly_drawing');
insert into service_filetypes (service_id,file_type) values (4,'odb or ipc2581');
insert into service_filetypes (service_id,file_type) values (4,'fabrication_drawing');





