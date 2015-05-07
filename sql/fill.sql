-- Table USER (Login, mdp, cat, nom, langue, email, sexe, gsm, adresse)
INSERT INTO USER VALUES ('Pierre','foobar',1,'Jaques','fr','piet@brol.com','M',0467344356,'foobar 1050');
INSERT INTO USER VALUES ('Sacha','azerty',1,'Ketchum','en','sacha@pokemon.com','M',0235325234,'Pokedex');
INSERT INTO USER VALUES ('Brol','azerty',2,'Truc','fr','brol@trucl.com','F',123423452,'Foobar');
INSERT INTO USER VALUES ('Machin','azerty',3,'Machine','fr','machin@machine.com','M',023423452426,'truc');
INSERT INTO USER VALUES ('Lala','foobar',2,'Dipsy','en','pooo@ttbb.com','F',024962352,'tbb');

-- Table Produit (idProduit, desc(id), nom(id),cat(id), image, prix, seuil)
INSERT INTO PRODUIT VALUES (1,1000,100,1,NULL,2.2,8);
INSERT INTO PRODUIT VALUES (2,1001,101,2,NULL,2.1,10);
INSERT INTO PRODUIT VALUES (3,1002,102,3,NULL,1.8,5);
INSERT INTO PRODUIT VALUES (4,1003,103,4,NULL,5,2);
INSERT INTO PRODUIT VALUES (5,1004,104,1,NULL,11,12);
INSERT INTO PRODUIT VALUES (6,1005,105,2,NULL,9000,35);

-- Table string(id, lang, val)
INSERT INTO STRING VALUES (1,'fr','Bière');
INSERT INTO STRING VALUES (1,'en','Bier');
INSERT INTO STRING VALUES (2,'fr','Soft');
INSERT INTO STRING VALUES (2,'en','Soft');
INSERT INTO STRING VALUES (3,'fr','Eaux');
INSERT INTO STRING VALUES (3,'en','Water');
INSERT INTO STRING VALUES (4,'fr','Vin');
INSERT INTO STRING VALUES (4,'en','Wine');
INSERT INTO STRING VALUES (5,'fr','Cocktail');
INSERT INTO STRING VALUES (5,'en','Cocktail');

INSERT INTO STRING VALUES (100,'fr','Carapils');
INSERT INTO STRING VALUES (100,'en','Carapils');
INSERT INTO STRING VALUES (101,'fr','Thé froid');
INSERT INTO STRING VALUES (101,'en','Iced Tea');
INSERT INTO STRING VALUES (102,'fr','Evian');
INSERT INTO STRING VALUES (102,'en','Evian');
INSERT INTO STRING VALUES (103,'fr','Vin rouge');
INSERT INTO STRING VALUES (103,'en','Red Wine');
INSERT INTO STRING VALUES (104,'fr','Marie saignante');
INSERT INTO STRING VALUES (104,'en','Bloody Mary');
INSERT INTO STRING VALUES (105,'fr','Orval');
INSERT INTO STRING VALUES (105,'en','Orval');
INSERT INTO STRING VALUES (106,'fr','Café');
INSERT INTO STRING VALUES (106,'en','Coffee');

INSERT INTO STRING VALUES (1000,'fr','Bière de légende');
INSERT INTO STRING VALUES (1000,'en','Legendary bier');
INSERT INTO STRING VALUES (1001,'fr','a');
INSERT INTO STRING VALUES (1001,'en','aen');
INSERT INTO STRING VALUES (1002,'fr','bfr');
INSERT INTO STRING VALUES (1002,'en','ben');
INSERT INTO STRING VALUES (1003,'fr','foobarfr');
INSERT INTO STRING VALUES (1003,'en','foobaren');
INSERT INTO STRING VALUES (1004,'fr','foobarfr');
INSERT INTO STRING VALUES (1004,'en','foobaren');
INSERT INTO STRING VALUES (1005,'fr','foobarfr');
INSERT INTO STRING VALUES (1005,'en','foobaren');

-- Table COMMAND (Id, login, state, datecm, table)
INSERT INTO COMMAND VALUES (1,'Pierre',0,22122009,1);
INSERT INTO COMMAND VALUES (2,'Sacha',1,22122009,1);
INSERT INTO COMMAND VALUES (3,'Pierre',0,22122009,3);
INSERT INTO COMMAND VALUES (4,'Lala',1,24122009,6);
INSERT INTO COMMAND VALUES (5,'Lala',1,24122009,4);
INSERT INTO COMMAND VALUES (6,'Lala',0,24122009,10);
INSERT INTO COMMAND VALUES (7,'Sacha',1,24122009,7);

-- Table Quantity (IdProd, IdComm, Quantity);
INSERT INTO QUANTITY VALUES (1,1,5);
INSERT INTO QUANTITY VALUES (2,1,2);
INSERT INTO QUANTITY VALUES (1,2,3);
INSERT INTO QUANTITY VALUES (1,3,10);
INSERT INTO QUANTITY VALUES (6,4,2);
INSERT INTO QUANTITY VALUES (4,5,1);
INSERT INTO QUANTITY VALUES (4,5,12);
INSERT INTO QUANTITY VALUES (1,6,1);
INSERT INTO QUANTITY VALUES (5,6,10);

-- Table Rating (login, IdProd, Rating)
INSERT INTO RATING VALUES ('Pierre',1,4);
INSERT INTO RATING VALUES ('Pierre',2,1);
INSERT INTO RATING VALUES ('Lala',1,3);
INSERT INTO RATING VALUES ('Machin',1,5);
INSERT INTO RATING VALUES ('Brol',1,4);
INSERT INTO RATING VALUES ('Brol',6,2);
INSERT INTO RATING VALUES ('Machin',4,5);

-- Table Lot(idlot,idprod,dateperemp,Q)
INSERT INTO LOT VALUES (1,1,28122010,40);
INSERT INTO LOT VALUES (2,2,24032012,30);
INSERT INTO LOT VALUES (3,1,23122011,30);
INSERT INTO LOT VALUES (4,3,24052034,12);
INSERT INTO LOT VALUES (5,4,24052012,50);
INSERT INTO LOT VALUES (6,5,21042011,23);

-- OKAOU BACKUP

INSERT INTO LOT VALUES (1,1,'2019-12-12',40);
INSERT INTO LOT VALUES (2,2,'2019-12-12',30);
INSERT INTO LOT VALUES (3,1,'2019-12-12',30);
INSERT INTO LOT VALUES (4,3,'2019-12-12',12);
INSERT INTO LOT VALUES (5,4,'2019-12-12',50);
INSERT INTO LOT VALUES (6,5,'2019-12-12',23);
INSERT INTO LOT VALUES (7,7,'2019-12-12',60000);
INSERT INTO LOT VALUES (8,6,'2019-12-12',60);
INSERT INTO LOT VALUES (9,8,'2019-12-12',60);
INSERT INTO LOT VALUES (10,9,'2019-12-12',60);
INSERT INTO LOT VALUES (11,10,'2019-12-12',60);
INSERT INTO LOT VALUES (12,11,'2019-12-12',60);
INSERT INTO LOT VALUES (13,12,'2019-12-12',60);
INSERT INTO LOT VALUES (14,13,'2019-12-12',60);
INSERT INTO LOT VALUES (15,14,'2019-12-12',60);
INSERT INTO LOT VALUES (16,15,'2019-12-12',60);
INSERT INTO LOT VALUES (17,16,'2019-12-12',60);
INSERT INTO LOT VALUES (18,17,'2019-12-12',60);
INSERT INTO LOT VALUES (19,18,'2019-12-12',60);
INSERT INTO LOT VALUES (20,19,'2019-12-12',60);
INSERT INTO LOT VALUES (21,20,'2019-12-12',60);
INSERT INTO LOT VALUES (22,21,'2019-12-12',60);
INSERT INTO LOT VALUES (23,22,'2019-12-12',60);
INSERT INTO LOT VALUES (24,23,'2019-12-12',60);
INSERT INTO LOT VALUES (25,24,'2019-12-12',60);
INSERT INTO LOT VALUES (26,25,'2019-12-12',60);
INSERT INTO LOT VALUES (27,26,'2019-12-12',60);
INSERT INTO LOT VALUES (28,27,'2019-12-12',60);
INSERT INTO LOT VALUES (29,28,'2019-12-12',60);
INSERT INTO LOT VALUES (30,29,'2019-12-12',60);
INSERT INTO LOT VALUES (31,30,'2019-12-12',60);

INSERT INTO LOT VALUES (7,6,23062013,60);
