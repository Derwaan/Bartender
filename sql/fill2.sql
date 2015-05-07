-- Table USER (Login, mdp, cat, nom, langue, email, sexe, gsm, adresse)
--- Catégorie [1..3] 1 : User, 2 : Serveur, 3 : Admin
INSERT INTO USER VALUES ('Pierre','foobar',1,'Jaques','fr','piet@brol.com','M',0467344356,'foobar 1050');
INSERT INTO USER VALUES ('Sacha','azerty',1,'Ketchum','en','sacha@pokemon.com','M',0235325234,'Pokedex');
INSERT INTO USER VALUES ('Brol','azerty',2,'Truc','fr','brol@trucl.com','F',123423452,'Foobar');
INSERT INTO USER VALUES ('Machin','azerty',3,'Machine','fr','machin@machine.com','M',023423452426,'truc');
INSERT INTO USER VALUES ('Lala','foobar',2,'Dipsy','en','pooo@ttbb.com','F',024962352,'tbb');
INSERT INTO USER VALUES ('Dany','foobar',1,'Danette','fr','dan@dan.fr','M',923345852,'foo');
INSERT INTO USER VALUES ('Brice','azerty',1,'Brice','en','b@b','en','M',012349522,'foo');

-- Table Produit (idProduit, desc(id), nom(id),cat(id), image, prix, seuil)
--- /!\ Problème dans les indices si on dépasse 100 catégories.
INSERT INTO PRODUIT VALUES (1,1001,101,1,NULL,0.4,9); -- Carapils
INSERT INTO PRODUIT VALUES (2,1002,102,1,NULL,1.2,9); -- Youpielair
INSERT INTO PRODUIT VALUES (3,1003,103,1,NULL,1.5,3); -- Foobar
INSERT INTO PRODUIT VALUES (4,1004,104,2,NULL,1,3); -- Surge
INSERT INTO PRODUIT VALUES (5,1005,105,2,NULL,1,6); -- Ok
INSERT INTO PRODUIT VALUES (6,1006,106,2,NULL,1.2,9); -- Coke BCV
INSERT INTO PRODUIT VALUES (7,1007,107,3,NULL,0,1); -- Eau du robinet
INSERT INTO PRODUIT VALUES (8,1008,108,3,NULL,3,7); -- Badoit
INSERT INTO PRODUIT VALUES (9,1009,109,3,NULL,3,8); -- Waiakea
INSERT INTO PRODUIT VALUES (10,1010,110,4,NULL,2,7); -- Pinot Noir
INSERT INTO PRODUIT VALUES (11,1011,111,4,NULL,2,6); -- Soubirac
INSERT INTO PRODUIT VALUES (12,1012,112,4,NULL,2,7); -- Merlot
INSERT INTO PRODUIT VALUES (13,1013,113,5,NULL,4,10); -- Bloody Mary
INSERT INTO PRODUIT VALUES (14,1014,114,5,NULL,4,10); -- Cervelle de singe
INSERT INTO PRODUIT VALUES (15,1015,115,5,NULL,4,10); -- Sex on the Beach
INSERT INTO PRODUIT VALUES (16,1016,116,5,NULL,42,10); -- The Funny Super Green
INSERT INTO PRODUIT VALUES (17,1017,117,6,NULL,6,5); -- Big Peat
INSERT INTO PRODUIT VALUES (18,1018,118,6,NULL,6,5); -- Vodka
INSERT INTO PRODUIT VALUES (19,1019,119,6,NULL,6,5); -- Gin
INSERT INTO PRODUIT VALUES (20,1020,120,6,NULL,6,5); -- Rhum
INSERT INTO PRODUIT VALUES (21,1021,121,7,NULL,4,5); -- Amaraetto
INSERT INTO PRODUIT VALUES (22,1022,122,7,NULL,4,5); -- Limoncello
INSERT INTO PRODUIT VALUES (23,1023,123,7,NULL,4,5); -- Calvados
INSERT INTO PRODUIT VALUES (24,1024,124,7,NULL,4,5); -- Grappa
INSERT INTO PRODUIT VALUES (25,1025,125,8,NULL,2,5); -- Hercule
INSERT INTO PRODUIT VALUES (26,1026,126,8,NULL,2,5); -- Saison
INSERT INTO PRODUIT VALUES (27,1027,127,8,NULL,2,5); -- Orval
INSERT INTO PRODUIT VALUES (28,1028,128,8,NULL,2,5); -- Gueuze
INSERT INTO PRODUIT VALUES (29,1029,129,8,NULL,2,6); -- Ginette
INSERT INTO PRODUIT VALUES (30,1030,130,9,NULL,0.5,10); -- Cahuètes



-- Table string(id, lang, val)
--- Catégories
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

--- Nom
INSERT INTO STRING VALUES (101,'fr','Carapils');
INSERT INTO STRING VALUES (101,'en','Carapils');
INSERT INTO STRING VALUES (102,'fr','Youpielair (Maison)');
INSERT INTO STRING VALUES (102,'en','Youpielair (Homemade)');
INSERT INTO STRING VALUES (103,'fr','FooooBar');
INSERT INTO STRING VALUES (103,'en','FooooBar');
INSERT INTO STRING VALUES (104,'fr','Surge');
INSERT INTO STRING VALUES (104,'en','Surge');
INSERT INTO STRING VALUES (105,'fr','Ok');
INSERT INTO STRING VALUES (105,'en','Ok');
INSERT INTO STRING VALUES (106,'fr','Coca-Cola Cerise - Vanille');
INSERT INTO STRING VALUES (106,'en','Coca-Cola Black Cherry - Vanilla');
INSERT INTO STRING VALUES (107,'fr','Eau (Maison)');
INSERT INTO STRING VALUES (107,'en','Water (Homemade)');
INSERT INTO STRING VALUES (108,'fr','Badoit (Saint-Galmier)');
INSERT INTO STRING VALUES (108,'en','Badoit (Saint-Galmier)');
INSERT INTO STRING VALUES (109,'fr','Eau de source du volcan hawaien Waiakea');
INSERT INTO STRING VALUES (109,'en','Waiakea Hawaiian Volcanic Water');
INSERT INTO STRING VALUES (110,'fr','Pinot noir 12% (Chateau Bon Baron)');
INSERT INTO STRING VALUES (110,'en','Pinot noir 12% (Chateau Bon Baron)');
INSERT INTO STRING VALUES (111,'fr','Soubirac 14% (Chateau Mal de Tête');
INSERT INTO STRING VALUES (111,'en','Soubirac 14% (Chateau Headache');
INSERT INTO STRING VALUES (112,'fr','Merlot/Dornfelder 12% (Hagelander)');
INSERT INTO STRING VALUES (112,'en','Merlot/Dornfelder 12% (Hagelander)');
INSERT INTO STRING VALUES (113,'fr','Marie Sanglante');
INSERT INTO STRING VALUES (113,'en','Bloody Mary');
INSERT INTO STRING VALUES (114,'fr','Cervelle de singe');
INSERT INTO STRING VALUES (114,'en','Monkey Brain');
INSERT INTO STRING VALUES (115,'fr','Sexe à la plage');
INSERT INTO STRING VALUES (115,'en','Sex on the Beach');
INSERT INTO STRING VALUES (116,'fr','Le Vert Super Chouette');
INSERT INTO STRING VALUES (116,'en','The Funny Super Green');
INSERT INTO STRING VALUES (117,'fr','Big Peat 46% (Douglas Laing)');
INSERT INTO STRING VALUES (117,'en','Big Peat 46% (Douglas Laing)');
INSERT INTO STRING VALUES (118,'fr','Vodka 47% (Zubrowka)');
INSERT INTO STRING VALUES (118,'en','Vodka 47% (Zubrowka)');
INSERT INTO STRING VALUES (119,'fr','Biercée Gin "Less is More" 44% (Distillerie de Biercée)');
INSERT INTO STRING VALUES (119,'en','Biercée Gin "Less is More" 44% (Distillerie de Biercée)');
INSERT INTO STRING VALUES (120,'fr','Rhum arrangé vanille 10 ans d\âge (Maison)');
INSERT INTO STRING VALUES (120,'en','Rhum mixed with vanilla 10 years old (Homemade)');
INSERT INTO STRING VALUES (121,'fr','Amaretto 28% (Disaronno)');
INSERT INTO STRING VALUES (121,'en','Amaretto 28% (Disaronno)');
INSERT INTO STRING VALUES (122,'fr','Limoncello 32% (Maison)');
INSERT INTO STRING VALUES (122,'en','Limoncello 32% (Homemade)');
INSERT INTO STRING VALUES (123,'fr','Calvados 40% (Père Magloire)');
INSERT INTO STRING VALUES (123,'en','Calavados 40% (Père Magloire)');
INSERT INTO STRING VALUES (124,'fr','Grappa 52% (Maison)');
INSERT INTO STRING VALUES (124,'en','Grappa 52% (Homemade)');
INSERT INTO STRING VALUES (125,'fr','Hercule 9% (Brasserie des légendes)');
INSERT INTO STRING VALUES (125,'en','Hercule 9% (Brasserie des légendes)' );
INSERT INTO STRING VALUES (126,'fr','Saison Voisin 5% (Brasserie des légendes)');
INSERT INTO STRING VALUES (126,'en','Saison Voisin 5% (Brasserie des légendes)');
INSERT INTO STRING VALUES (127,'fr','Orval 6% (Brasserie d\'Orval)');
INSERT INTO STRING VALUES (127,'en','Orval 6% (Brasserie d\'Orval)');
INSERT INTO STRING VALUES (128,'fr','Gueuze 5% (Brasserie Cantillon)');
INSERT INTO STRING VALUES (128,'en','Gueuze 5% (Brasserie Cantillon');
INSERT INTO STRING VALUES (129,'fr','Ginette Blonde Naturelle 5% (Brasserie Ginette)');
INSERT INTO STRING VALUES (129,'en','Ginette Natural Blond 5% (Brasserie Ginette)');
INSERT INTO STRING VALUES (130,'fr','Cacahuètes');
INSERT INTO STRING VALUES (130,'en','Peanuts');

--- Descriptions
INSERT INTO STRING VALUES (1001,'fr','Légende urbaine.');
INSERT INTO STRING VALUES (1001,'en','Urban legend.');
INSERT INTO STRING VALUES (1002,'fr','Bière du patron');
INSERT INTO STRING VALUES (1002,'en','Homemade beer');
INSERT INTO STRING VALUES (1003,'fr','Bière aléatoire dans une sélection secrète');
INSERT INTO STRING VALUES (1003,'en','Ramdom beer taken from a secret selection');
INSERT INTO STRING VALUES (1004,'fr','Vert fluo et vous donne la force de Hulk grâce à son taux en caféine illégal ! Merci Coca-Cola Company !');
INSERT INTO STRING VALUES (1004,'en','Bright green and will give you Hulk strength with its illegal caffeine rate! Thank you Coca-Cola Company!');
INSERT INTO STRING VALUES (1005,'fr','La boisson gazeuse cynique par Coca-Cola. Le soda Ok vous dit "Ne soyez pas dans l\'illusion qu\'il existe une raison à toute chose. C\'est OK pour vous de penser que je ne suis pas OK mais je le suis."');
INSERT INTO STRING VALUES (1005,'en','The cynical soft drink by Coca-Cola. OK soda says "Don’t be fooled into thinking there has to be a reason for everything. It’s OK for you to think I’m not OK but I am"');
INSERT INTO STRING VALUES (1006,'fr','Prenez un Coca-Cola, une cerise et de la vanille. Mélangez le tout sur un nid de glaçon et sa rondelle de citron.');
INSERT INTO STRING VALUES (1006,'en','Take a Coke, a black cherry and vanilla. Shake it and put it on ice with some lemon.');
INSERT INTO STRING VALUES (1007,'fr','Eau de ville dont s\'abreuve nos robinets qui vous désalterera.');
INSERT INTO STRING VALUES (1007,'en','City water which quenches our faucets that will refresh you.');
INSERT INTO STRING VALUES (1008,'fr','Vénérées par les Gaulois et appréciées par les rois, les eaux de Saint-Galmier ont longtemps été reconnues pour leurs vertues réparatrice. Toutefois, il a fallu attendre l\'action d\'un entrepreneur connu sous le nom d\'Auguste Badoit pour partager les eaux de sources de ce petit village avec le monde entier. À une hauteur de 500m sous la vallée de la Loire, l\'eau est naturellement chargée en minéraux donnant le côté gazeux telle une signature de la marque. De nos jours, l\'eau de qualité est une habituée des grands restaurants où sa carbonatation délicate et son goût équilibrée plaira aux palais sensibles des gourmets.');
INSERT INTO STRING VALUES (1008,'en','Revered by the ancient Gauls and enjoyed by kings, the waters of Saint-Galmier have long been recognized for their restorative properties. However, it took an enterprising silk salesman named Auguste Badoit to share the spring water of this tiny French town with the world. Traveling to the surface from 500 feet below the Loire Valley, the water is naturally endowed with a generous amount of trace minerals as well as light, playful bubbles that give Badoit its signature taste. Today, the water is a common sight at fine restaurants, where its delicate carbonation and balanced taste complement the subtle flavors of gourmet meals.');
INSERT INTO STRING VALUES (1009,'fr','Provenant du pic enneigé du Mauna Loa, le plus grand volcan de subaérien du monde, l\'eau de Waiakea est naturellement filtrée à travers des milliers de pieds de roche volcanique poreuse avant d\'être embouteillé. Le produit résultant est riche en électrolytes et minéraux essentiels, et est également inhabituellement alcaline, donnant à l\'eau sucrée caractéristique sa finition.');
INSERT INTO STRING VALUES (1009,'en','Sourced from the snowy peak of Mauna Loa, the world\'s largest subaerial volcano, Waiakea\'s water is naturally filtered through thousands of feet of porous volcanic rock before being bottled. The resulting product is rich in electrolytes and essential minerals, and is also unusually alkaline, giving the water its distinctive sweet finish. ');
INSERT INTO STRING VALUES (1010,'fr','Délicieux Pinot noir vieilli un an en fûts de chêne francais neufs. De couleur rubis intense, il offre un bouquet fin de beaux fruits et de subtils tons de vanille. La bouche gourmande présente un délicieux palais de fruits des bois, de cerise noire et une légère touche fumée.');
INSERT INTO STRING VALUES (1010,'en','Delicious Pinot Noir aged one year in barrels of new French oak. Intense ruby color, it offers a bouquet fine beautiful fruits and subtle tones of vanilla. The greedy mouth has a delicious palate of berries, black cherries and a touch of smoke.');
INSERT INTO STRING VALUES (1011,'fr','Pour des lendemains douloureux.');
INSERT INTO STRING VALUES (1011,'en','For painful tomorrows.');
INSERT INTO STRING VALUES (1012,'fr','Un vin rouge foncé, dévoilant la générosité du Dornfelder et l’élégance du Merlot. En bouche, un fruité serré alterne avec les touches vanillées apportées par le passage en barrique. Le vin offre une finale longue, persistante et très agréable.');
INSERT INTO STRING VALUES (1012,'en','A dark red wine, revealing the generosity of Dornfelder and elegance of Merlot. In the mouth, a tight fruity alternates with vanilla touches brought by the passage in barrels. The wine has a long finish, persistent and very pleasant.');
INSERT INTO STRING VALUES (1013,'fr','Vodka, jus de tomates, jus de citron, sauce Worcestershire, tabasco, sel de céleri, sel, poivre');
INSERT INTO STRING VALUES (1013,'en','Vodka, tomato juice, lemon juice, Worcestershire sauce, Tabasco sauce, celery salt, salt, pepper');
INSERT INTO STRING VALUES (1014,'fr','Grenadine, Baileys, vodka');
INSERT INTO STRING VALUES (1014,'en','Grenadine, Baileys, vodka');
INSERT INTO STRING VALUES (1015,'fr','Vodka, melon, chambord, jus d\'ananas, jus de cranberry');
INSERT INTO STRING VALUES (1015,'en','Vodka, melon, Chambord, pineapple juice, cranberry juice');
INSERT INTO STRING VALUES (1016,'fr','Citron, vodka, tequila, curaçao bleu, jus d\'oranges, sucre et cannelle');
INSERT INTO STRING VALUES (1016,'en','Lemon, vodka, tequila, blue curacao, orange juice, sugar and cinnamon');
INSERT INTO STRING VALUES (1017,'fr','Tous les single malts de l\'île d\'Islay, y compris le rarissime Port Ellen, sont présents au sein de ce Blended Malt (assemblage de single malts) résolument tourbé au nom évocateur signé Douglas Laing. Une tourbe sèche et fumée qui ravira les amateurs de jeunes Islay très typés.');
INSERT INTO STRING VALUES (1017,'en','All single malts from Islay, including rare Port Ellen, are present within that Blended Malt (assembly of single malts) resolutely peaty evocative name signed Douglas Laing. A dry peat and smoke delight fans young Islay very typical.');
INSERT INTO STRING VALUES (1018,'fr','Zubrowka est une vodka polonaise à l\'herbe de bison. Egalement appelée Vodka de Bison, Zubrowka doit son nom à une herbe aromatique des plaines orientales de Pologne très appréciée du bison. La Vodka Zubrowka est élaboré à partir de seigle de grande qualité cultivé dans les régions du Nord-Ouest de la Pologne.');
INSERT INTO STRING VALUES (1019,'en','
Zubrowka is a Polish vodka bison grass. Also called Buffalo Vodka, Zubrowka is named after an aromatic herb of the eastern plains of Poland appreciated the buffalo. Zubrowka Vodka is made from high quality rye grown in the North-West of Poland.');
INSERT INTO STRING VALUES (1019,'fr','a Distillerie de Biercée est parvenue à ramener le gin à son essence même : un gin franc et sans détour dans le respect de la tradition. L’ensemble botanique bien défini et sa teneur en alcool de 44% lui confèrent un caractère épicé équilibré alliant saveurs de baies de genévrier, d’agrumes frais et notes poivrées.');
INSERT INTO STRING VALUES (1019,'en','Distillerie de Biercée has succeeded in bringing gin back to basics: an honest, straight forward gin elaborated according to tradition. The well-defined selection of botanicals and its 44 % alcohol content give it a spicy, balanced character combining the flavours of juniper berries, fresh citrus fruit and peppery notes.');
INSERT INTO STRING VALUES (1020,'fr','Rhum arrangé maison où des gousses de vanille ont mariné durant 10 ans.');
INSERT INTO STRING VALUES (1020,'en','Rum where vanilla beans marinated for 10 years.');
INSERT INTO STRING VALUES (1021,'fr','Digestif italien aromatisé aux herbes et aux fruits ayant trempé dans l\'huile de noyaux d\'abricots'.);
INSERT INTO STRING VALUES (1021,'en','Italian liqueur flavored with herbs and fruits soaked in apricot kernel oil.');
INSERT INTO STRING VALUES (1022,'fr','Limoncello fait maison avec amour !');
INSERT INTO STRING VALUES (1022,'en','Limoncello homemade with love!');
INSERT INTO STRING VALUES (1023,'fr','Le Calvados Père Magloire se distingue par une distillation en continu en alambic de cuivre qui permet de concentrer les arômes de pomme, accentués par l\'assemblage des cuvées les plus fruitées et un vieillissement en fûts de chêne centenaire.');
INSERT INTO STRING VALUES (1023,'en','Père Magloire Calvados is distinguished by a copper alembic continuous distillation which concentrates the aromas of apple, accented by the assembly of the most fruity wines and aging in old oak.');
INSERT INTO STRING VALUES (1024,'fr','Déliceuse Grappa réalisée avec amour dans nos caves!');
INSERT INTO STRING VALUES (1024,'en','Delicious Grappa homade with love in our cellar!');
INSERT INTO STRING VALUES (1025,'fr','Brassée en hommage à Hercules Poirot, cette stout se caractérise par son goût de chocolat et de moka. Élue meilleure bière de Wallonie en 2012');
INSERT INTO STRING VALUES (1025,'en','Brewed in honor of Hercules Poirot, the stout is characterized by its taste of chocolate and mocha. Voted the Best Belgian Brown in Wallonia in 2012.');
INSERT INTO STRING VALUES (1026,'fr','Brassée depuis 1884 selon la recette originale, la SAISON VOISIN est une véritable bière de terroir. Caractérisée par une touche houblonnée (40 EBU) et une belle robe ambrée, la Saison Voisin vous plongera dans les moissons d\'antan.Elue meilleur bière ambrée belge wallonne 2012.');
INSERT INTO STRING VALUES (1026,'en','SAISON VOISIN has been brewed since 1884 according to the original recipe – it’s a truly local beer of the lands. It is characterised by its hoppy taste (40 EBU) and a lovely amber finish. Saison Voisin will throws you back to the harvests of yesteryear. Voted the ‘Best Belgian Amber of Wallonia’ in 2012.');
INSERT INTO STRING VALUES (1027,'fr','L\'Orval est une bière ambrée de fermentation haute. Elle est constituée exclusivement d\'eau de source, de malts, de houblons, de sucre et de levures. Elle a un arôme et un goût houblonnés très typiques. Refermentée en bouteille, l\'Orval offre toute sa saveur lorsqu\'elle est servie dans son verre d\'origine à une température entre 12 et 14 degrés.');
INSERT INTO STRING VALUES (1027,'en','The Orval is an amber beer of high fermentation. It consists of spring water exclusively, malt, hops, sugar and yeast. It has a typical aroma and a very hoppy taste. Refermented in the bottle, the Orval offers all its flavor when served in its original glass at a temperature between 12 and 14 degrees.');
INSERT INTO STRING VALUES (1028,'fr',' La Cantillon Gueuze présente une robe orangée trouble et une mousse abondante et compacte. Ses arômes sont fruités et boisés.');
INSERT INTO STRING VALUES (1028,'en','The Cantillon Gueuze has blurred orange robe and abundant and compact foam. Its aromas are fruity and woody.');
INSERT INTO STRING VALUES (1029,'fr','Ginette "Blonde Naturelle" s\'inscrit dans le respect d\'authenticité, d\'artisanat et est brassée esclusivement à partir d\'ingrédients issus de l\'agriculture biologique.');
INSERT INTO STRING VALUES (1029,'en','Ginette "Natural Blond" is in respect of authenticity, craftsmanship and is brewed exclusively from ingredients from organic agriculture.');
INSERT INTO STRING VALUES (1030,'fr','Une robe croquante accompagnée de son arachide authentique servie en bol et accompagnée de ses nombreuses amies !');
INSERT INTO STRING VALUES (1030,'en','Tasty nuts in a bowl to share with friends!');


-- Table Lot(idlot,idprod,dateperemp,Q)
--- FORMAT DATe MM-DD-YYYY
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (1,23,"11-25-2014",97);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (2,16,"01-29-2016",172);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (3,3,"11-17-2015",146);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (4,26,"09-16-2015",32);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (5,25,"04-26-2015",7);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (6,1,"08-12-2014",150);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (7,30,"11-05-2014",88);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (8,20,"11-25-2014",108);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (9,20,"10-28-2014",141);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (10,11,"04-18-2015",84);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (11,8,"08-11-2014",39);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (12,12,"11-10-2014",89);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (13,7,"07-08-2015",34);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (14,12,"03-07-2016",92);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (15,21,"11-02-2014",193);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (16,2,"07-27-2014",114);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (17,8,"07-14-2015",188);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (18,2,"10-19-2015",4);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (19,14,"12-08-2015",148);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (20,24,"01-04-2016",154);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (21,2,"02-27-2015",187);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (22,3,"08-12-2014",44);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (23,3,"03-15-2015",188);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (24,7,"05-02-2015",149);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (25,21,"04-26-2015",164);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (26,12,"05-26-2015",118);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (27,28,"06-15-2014",191);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (28,27,"06-02-2015",36);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (29,12,"11-09-2015",4);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (30,21,"07-25-2015",15);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (31,26,"10-14-2014",87);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (32,15,"06-26-2015",80);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (33,24,"02-27-2016",153);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (34,2,"01-11-2016",62);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (35,4,"06-15-2014",48);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (36,10,"07-16-2015",178);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (37,2,"08-11-2015",71);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (38,27,"12-19-2014",14);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (39,17,"10-12-2014",122);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (40,4,"09-16-2014",36);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (41,16,"04-01-2015",66);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (42,13,"11-23-2015",86);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (43,27,"01-09-2016",182);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (44,10,"08-12-2014",97);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (45,23,"04-10-2015",162);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (46,16,"07-20-2014",16);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (47,26,"09-01-2014",22);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (48,8,"03-30-2016",2);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (49,26,"11-29-2014",109);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (50,15,"03-07-2015",18);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (51,19,"04-05-2015",154);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (52,13,"11-18-2014",147);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (53,27,"12-26-2014",81);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (54,1,"05-13-2015",81);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (55,9,"02-08-2015",18);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (56,30,"07-15-2014",158);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (57,4,"06-13-2015",85);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (58,16,"05-23-2014",128);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (59,16,"09-23-2014",10);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (60,16,"01-31-2015",98);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (61,16,"04-30-2015",192);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (62,5,"12-04-2015",123);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (63,21,"10-03-2015",31);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (64,20,"11-24-2014",26);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (65,28,"12-18-2014",105);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (66,29,"09-14-2015",127);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (67,10,"05-31-2015",185);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (68,14,"01-21-2015",69);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (69,21,"10-20-2014",190);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (70,23,"06-30-2014",175);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (71,7,"01-06-2015",90);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (72,2,"01-26-2015",41);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (73,24,"04-23-2015",7);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (74,28,"11-18-2014",49);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (75,12,"01-24-2016",140);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (76,1,"12-03-2015",102);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (77,2,"06-25-2014",116);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (78,3,"10-07-2015",22);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (79,26,"11-02-2015",176);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (80,16,"07-11-2015",80);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (81,16,"02-08-2016",112);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (82,27,"02-08-2015",10);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (83,11,"11-20-2014",148);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (84,20,"01-03-2015",189);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (85,1,"06-14-2014",189);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (86,26,"11-30-2015",89);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (87,3,"09-24-2014",79);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (88,7,"06-26-2015",136);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (89,12,"06-09-2015",50);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (90,11,"08-31-2014",103);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (91,22,"09-08-2015",154);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (92,20,"05-10-2014",17);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (93,17,"04-20-2016",25);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (94,14,"11-06-2014",51);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (95,16,"05-17-2014",29);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (96,10,"02-19-2015",43);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (97,17,"09-08-2014",157);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (98,13,"09-09-2014",158);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (99,7,"11-22-2015",42);
INSERT INTO LOT (ID_LOT,IDPROD,DATELOT,QTY) VALUES (100,22,"10-07-2014",130);

