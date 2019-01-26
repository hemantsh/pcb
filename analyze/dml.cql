INSERT into services (id,name) VALUES (1, 'Fabrication and Assembly');
INSERT into services (id,name) VALUES (2, 'Assembly');
INSERT into services (id,name) VALUES (3, 'Fabrication');


INSERT into file_types (id,type) VALUES (1, 'Signal');
INSERT into file_types (id,type) VALUES (2, 'Mixed');
INSERT into file_types (id,type) VALUES (3, 'power_ground');
INSERT into file_types (id,type) VALUES (4, 'solder_mask');
INSERT into file_types (id,type) VALUES (5, 'silk_screen');
INSERT into file_types (id,type) VALUES (6, 'solder_paste');
INSERT into file_types (id,type) VALUES (7, 'drill');
INSERT into file_types (id,type) VALUES (8, 'Rout');
INSERT into file_types (id,type) VALUES (9, 'Document');
INSERT into file_types (id,type) VALUES (10,'Component');



 	
INSERT into service_files (service_id, filetype_id, service , file ) 
VALUES ( 3, 11, 'Fabrication' , 'Fab Drawing');

INSERT into service_files (service_id, filetype_id, service , file ) 
VALUES ( 3, 7, 'Fabrication' , 'NC Drill File');

INSERT into service_files (service_id, filetype_id, service , file ) 
VALUES ( 3, 13, 'Fabrication' , 'Layer Sequence');

INSERT into service_files ( service_id, filetype_id, service , file ) 
VALUES ( 2, 11, 'Assembly' , 'Fab Drawing');

INSERT into service_files ( service_id, filetype_id, service , file ) 
VALUES ( 2, 7, 'Assembly' , 'NC Drill File');

INSERT into service_files ( service_id, filetype_id, service , file) 
VALUES ( 2, 13, 'Assembly' , 'Layer Sequence');

INSERT into service_files ( service_id, filetype_id, service , file ) 
VALUES ( 2, 1, 'Assembly' , 'Top Silkscreen');

INSERT into service_files ( service_id, filetype_id, service , file ) 
VALUES ( 2, 2, 'Assembly' , 'Top Soldermask');

INSERT into service_files ( service_id, filetype_id, service , file) 
VALUES ( 2, 3, 'Assembly' , 'Top Copper');

INSERT into service_files ( service_id, filetype_id, service , file ) 
VALUES ( 2, 12, 'Assembly' , 'Assembly Drawing');


INSERT into extensions (id,name) VALUES (1, 'gto');
INSERT into extensions (id,name) VALUES (2, 'sst');
INSERT into extensions (id,name) VALUES (3, 'plc');
INSERT into extensions (id,name) VALUES (4, 'smt');
INSERT into extensions (id,name) VALUES (5, 'stc');
INSERT into extensions (id,name) VALUES (6, 'gts');
INSERT into extensions (id,name) VALUES (7, 'gtl');
INSERT into extensions (id,name) VALUES (8, 'top');
INSERT into extensions (id,name) VALUES (9, 'cmp');
INSERT into extensions (id,name) VALUES (10, 'gbl');
INSERT into extensions (id,name) VALUES (11, 'bot');
INSERT into extensions (id,name) VALUES (12, 'sol');
INSERT into extensions (id,name) VALUES (13, 'gbs');
INSERT into extensions (id,name) VALUES (14, 'smb');
INSERT into extensions (id,name) VALUES (15, 'sts');
INSERT into extensions (id,name) VALUES (16, 'gbo');
INSERT into extensions (id,name) VALUES (17, 'ssb');
INSERT into extensions (id,name) VALUES (18, 'pls');
INSERT into extensions (id,name) VALUES (19, 'txt');
INSERT into extensions (id,name) VALUES (20, 'drd');
INSERT into extensions (id,name) VALUES (21, 'thruhole.tap');
INSERT into extensions (id,name) VALUES (22, 'gm1');
INSERT into extensions (id,name) VALUES (23, 'gko');
INSERT into extensions (id,name) VALUES (24, 'gp1');
INSERT into extensions (id,name) VALUES (25, 'g1');
INSERT into extensions (id,name) VALUES (26, 'gp2');
INSERT into extensions (id,name) VALUES (27, 'pdf');
INSERT into extensions (id,name) VALUES (28, 'jpeg');
INSERT into extensions (id,name) VALUES (29, 'gtp');
INSERT into extensions (id,name) VALUES (30, 'gbp');
INSERT into extensions (id,name) VALUES (31, 'gm');
INSERT into extensions (id,name) VALUES (32, 'gd');
INSERT into extensions (id,name) VALUES (33, 'gg');
INSERT into extensions (id,name) VALUES (34, 'drl');
INSERT into extensions (id,name) VALUES (35, 'tap');
INSERT into extensions (id,name) VALUES (36, 'npt');
INSERT into extensions (id,name) VALUES (37, 'bol');
INSERT into extensions (id,name) VALUES (38, 'in');
INSERT into extensions (id,name) VALUES (39, 'spt');
INSERT into extensions (id,name) VALUES (40, 'spb');
INSERT into extensions (id,name) VALUES (41, 'asb');
INSERT into extensions (id,name) VALUES (42, 'ast');
INSERT into extensions (id,name) VALUES (43, 'fab');
INSERT into extensions (id,name) VALUES (44, 'gtm');
INSERT into extensions (id,name) VALUES (45, 'gbm');
INSERT into extensions (id,name) VALUES (46, 'gtd');
INSERT into extensions (id,name) VALUES (47, 'dim');
INSERT into extensions (id,name) VALUES (48, 'i');
INSERT into extensions (id,name) VALUES (49, 'crc');
INSERT into extensions (id,name) VALUES (50, 'crs');


 
INSERT into extension_file ( extension_id, filetype_id, file, extension ) VALUES ( 1,1, 'Top Silkscreen', 'gto');
INSERT into extension_file ( extension_id, filetype_id,file, extension ) VALUES ( 2,1,'Top Silkscreen', 'sst');
INSERT into extension_file ( extension_id, filetype_id,file, extension ) VALUES ( 3,1,'Top Silkscreen', 'plc');

INSERT into extension_file ( extension_id, filetype_id,file, extension ) VALUES ( 4,2,'Top Soldermask', 'smt');
INSERT into extension_file ( extension_id, filetype_id,file, extension ) VALUES ( 5,2,'Top Soldermask', 'stc');
INSERT into extension_file ( extension_id, filetype_id,file, extension ) VALUES ( 6,2,'Top Soldermask', 'gts');

INSERT into extension_file ( extension_id, filetype_id,file, extension ) VALUES ( 7,3,'Top Copper', 'gtl');
INSERT into extension_file ( extension_id, filetype_id,file, extension ) VALUES ( 8,3,'Top Copper', 'top');
INSERT into extension_file ( extension_id, filetype_id,file, extension ) VALUES ( 9,3,'Top Copper', 'cmp');

INSERT into extension_file ( extension_id, filetype_id,file, extension ) VALUES ( 10,4,'Bottom Copper', 'gbl');
INSERT into extension_file ( extension_id, filetype_id,file, extension ) VALUES ( 11,4,'Bottom Copper', 'bot');
INSERT into extension_file ( extension_id, filetype_id,file, extension ) VALUES ( 12,4,'Bottom Copper', 'sol');

INSERT into extension_file ( extension_id, filetype_id,file, extension ) VALUES ( 13,5,'Bottom Soldermask', 'gbs');
INSERT into extension_file ( extension_id, filetype_id,file, extension ) VALUES ( 14,5,'Bottom Soldermask', 'smb');
INSERT into extension_file ( extension_id, filetype_id,file, extension ) VALUES ( 15,5,'Bottom Soldermask', 'sts');

INSERT into extension_file ( extension_id, filetype_id,file, extension ) VALUES ( 16,6,'Bottom Silkscreen', 'gbo');
INSERT into extension_file ( extension_id, filetype_id,file, extension ) VALUES ( 17,6,'Bottom Silkscreen', 'ssb');
INSERT into extension_file ( extension_id, filetype_id,file, extension ) VALUES ( 18,6,'Bottom Silkscreen', 'pls');

INSERT into extension_file ( extension_id, filetype_id,file, extension ) VALUES ( 19,7,'NC Drill File', 'txt');
INSERT into extension_file ( extension_id, filetype_id,file, extension ) VALUES ( 20,7,'NC Drill File', 'thruhole.tap');
INSERT into extension_file ( extension_id, filetype_id,file, extension ) VALUES ( 21,7,'NC Drill File', 'drd');

INSERT into extension_file ( extension_id, filetype_id,file, extension ) VALUES ( 22,8,'Board Outline', 'gm1');
INSERT into extension_file ( extension_id, filetype_id,file, extension ) VALUES ( 23,8,'Board Outline', 'gko');

INSERT into extension_file ( extension_id, filetype_id,file, extension ) VALUES ( 24,9,'GND Plane', 'gp1');
INSERT into extension_file ( extension_id, filetype_id,file, extension ) VALUES ( 25,9,'GND Plane', 'g1');

INSERT into extension_file ( extension_id, filetype_id,file, extension ) VALUES ( 26,10,'V3P3 Plane', 'gp2');
INSERT into extension_file ( extension_id, filetype_id,file, extension ) VALUES ( 27,11,'Fab Drawing', 'pdf');
INSERT into extension_file ( extension_id, filetype_id,file, extension ) VALUES ( 27,12,'Assembly Drawing', 'pdf');






