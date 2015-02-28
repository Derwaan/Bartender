# Faits élémentaires

Un client possède un login et un mot de passe, une commande, un historique des commandes, un numéro client, et des données personnelles. 
L'administrateur possède un mot de passe. 
Un serveur possède un mot de passe. 
Les boissons ont une catégorie, nom, prix, description, un stock, un seuil, une note. 
Les commandes ont des boissons, des quantités, la table, une date. 
Une table possède un numéro. 
La carte contient les boissons. 
Un historique des commandes qui possède un client ou un serveur et sa commande. 

http://www.yegor256.com/2014/12/01/orm-offensive-anti-pattern.html

--------------------------------------------------------------------- 


##Client

- Client (nom) Eric Dubois
- Client (nom) Eric Dubois a une langue (langue) francais.
- Client (nom) Eric Dubois possède une adresse (email) eric.dubois@gmail.com
- Client (nom) Eric Dubois habite (adresse) Rue de l'arbre, 42 Lafaurait
- Client (nom) Eric Dubois a un (sexe) M
- Client (nom) Eric Dubois a un (numéro) Eric possède un numéro de téléphone (numéro) 0478 00 00 00
- Client (nom) Eric Dubois a un (historique) Eric possède un historique de ses commandes

##Produit

- Produit (nom) Coca-Cola a une catégorie (Categorie) Soft
- Produit (nom) Coca-Cola
- Produit (nom) Coca-Cola a un (prix) 1.20€
- Produit (nom) Coca-Cola a un (seuil) 10
- Produit (nom) Coca-Cola possède une (description) Le Coca-Cola est une boisson gazeuse sucrée de type cola fabriquée  par la Coca-Cola Company. Le nom « Coca-Cola » est une marque  commerciale américaine déposée en 1886.
- Produit (nom) Coca-Cola est réprésenté par (image) ***
- Produit (nom) Coca-Cola a un (rating) 7/10
- Produit (nom) Coca-Cola possède (id) 0001
- Produit (nom) Coca-Cola fait partie d'un (lot)  Lot de Coca-Cola
- Produit (nom) Coca-Cola peut faire partie d'une commande (Commande)
- Produit (nom) Coca-Cola possède une quantité (Quantite) 10
- Produit (nom) Coca-Cola peut être lié a un utilisateur (User) Eric123

## Lot

- Lot (id) 154 a un id : 154.
- Lot(id) a une (date de péremption). Lot 154 de Coca-Cola périme le 13/05/16
- Lot(id) a une (quantité) de produit. Lot 154 contient 50 Coca-Cola.

## Administrateur

- Administrateur (nom) Paul-Henry a un nom : Paul-Henry  
- Administrateur (nom) Paul-Henry a un mot de passe :  ******* (mot de passe) 
- Aministrateur (nom) Paul-Henry a un identifiant Admin01 (Identifiant) 
- Administrateur (nom) Paul-Henry peut générer des statistiques.
- Administrateur (nom) admin peut consulter les données personnelles.
- Administrateur (nom) admin peut mettre à jour les quantités.
- Administrateur (nom) admin peut mettre à jour les prix.

## Serveur

- Serveur (nom) Jean a un login (login) Jean
- Serveur (nom) Jean a un mot de passe (mdp) azerty
- Serveur (nom) Jean peut créer une commande

## Commande

- Commande possède un numéro (numéro) 0001
- Commande (numéro) 0001 possède des produits (produit) Coca.
- Commande (numéro) 0001 possède des quantités (quantité) 2
- Commande (numéro) 0001 désigne un client (client)  Eric Dubois
- Commande (numéro) 0001 possède une date (date) 19/02/2015
- Commande (numéro) 0001 possède un état (état) Payé.
- Commande (numéro) 13456 a une (table) 12

## String

- String possède un numéro (id) 0001.
- String (id) 0001 possède une langue (langue) français.
- String (id) 0001 possède un texte (texte) "Ce produit se caractérise par sa fraîcheur et sa robe ambrée".

