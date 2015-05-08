-- Table USER (Login, mdp, cat, nom, langue, email, sexe, gsm, adresse)
--- Catégorie [1..3] 
INSERT INTO USER VALUES ('Pierre','foobar',1,'Jaques','fr','piet@brol.com','M',0467344356,'foobar 1050');
INSERT INTO USER VALUES ('Sacha','azerty',1,'Ketchum','en','sacha@pokemon.com','M',0235325234,'Pokedex');
INSERT INTO USER VALUES ('Brol','azerty',2,'Truc','fr','brol@trucl.com','F',123423452,'Foobar');
INSERT INTO USER VALUES ('Machin','azerty',3,'Machine','fr','machin@machine.com','M',023423452426,'truc');
INSERT INTO USER VALUES ('Lala','foobar',2,'Dipsy','en','pooo@ttbb.com','F',024962352,'tbb');
INSERT INTO USER VALUES ('Dany','foobar',1,'Danette','fr','dan@dan.fr','M',923345852,'foo');
INSERT INTO USER VALUES ('Brice','azerty',1,'Brice','en','b@b','en','M',012349522,'foo');

-- Table Produit (idProduit, desc(id), nom(id),cat(id), image, prix, seuil)
INSERT INTO PRODUIT VALUES (1,1000,100,1,NULL,2.2,8);
INSERT INTO PRODUIT VALUES (2,1001,101,2,NULL,2.1,10);
INSERT INTO PRODUIT VALUES (3,1002,102,3,NULL,1.8,5);
INSERT INTO PRODUIT VALUES (4,1003,103,4,NULL,5,2);
INSERT INTO PRODUIT VALUES (5,1004,104,1,NULL,11,12);
INSERT INTO PRODUIT VALUES (6,1005,105,2,NULL,9000,35);

-- Table string(id, lang, val)
INSERT INTO STRING VALUES (1,'fr','Bières à la pompe');
INSERT INTO STRING VALUES (1,'en','Biers (Draft)');
INSERT INTO STRING VALUES (2,'fr','Softs');
INSERT INTO STRING VALUES (2,'en','Softs');
INSERT INTO STRING VALUES (3,'fr','Eaux');
INSERT INTO STRING VALUES (3,'en','Water');
INSERT INTO STRING VALUES (4,'fr','Vins');
INSERT INTO STRING VALUES (4,'en','Wines');
INSERT INTO STRING VALUES (5,'fr','Cocktails');
INSERT INTO STRING VALUES (5,'en','Cocktails');
INSERT INTO STRING VALUES (6,'fr','Spiritueux');
INSERT INTO STRING VALUES (6,'en','Spirits');
INSERT INTO STRING VALUES (7,'fr','Digestifs');
INSERT INTO STRING VALUES (7,'en','Liqueurs');
INSERT INTO STRING VALUES (8,'fr','Bières en bouteille');
INSERT INTO STRING VALUES (8,'en','Biers (Bottle)');
INSERT INTO STRING VALUES (9,'fr','En-cas');
INSERT INTO STRING VALUES (9,'en','Snacks');


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


-- Table Lot(idlot,idprod,dateperemp,Q)
INSERT INTO LOT VALUES (1,1,28122010,40);
INSERT INTO LOT VALUES (2,2,24032012,30);
INSERT INTO LOT VALUES (3,1,23122011,30);
INSERT INTO LOT VALUES (4,3,24052034,12);
INSERT INTO LOT VALUES (5,4,24052012,50);
INSERT INTO LOT VALUES (6,5,21042011,23);
INSERT INTO LOT VALUES (7,6,23062013,60);
