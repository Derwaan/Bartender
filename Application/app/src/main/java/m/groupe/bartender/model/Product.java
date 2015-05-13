package m.groupe.bartender.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.SparseArray;

import java.util.ArrayList;

import m.groupe.bartender.MySQLiteHelper;

/**
 * Product construit un objet
 *
 * @ Trong-Vu Tran
 */

public class Product {
    /**
     * Noms des tables
     */
    public static final String DB_TABLE_PRODUCT = "PRODUIT";
    public static final String DB_TABLE_RATING = "RATING";
    public static final String DB_TABLE_STRING = "STRING";

    /**
     * Nom des colonnes de la table PRODUIT
     */
    public static final String DB_COL_ID_PROD = "ID_PROD";
    public static final String DB_COL_DESCR = "DESCR";
    public static final String DB_COL_NAME = "NOM_PRODUIT";
    public static final String DB_COL_CAT = "CATEGORY";
    public static final String DB_COL_IMG = "IMAGE";
    public static final String DB_COL_PRICE = "PRIX";
    public static final String DB_COL_THRESH = "SEUIL";

    /**
     * Nom des colonnes de la table STRING
     */
    public static final String DB_COL_ID_STRING = "ID_STRING";
    public static final String DB_COL_LANGUE = "LANGUE";
    public static final String DB_COL_TEXTE = "TEXTE";

    /**
     * Nom des colonnes de la table RATING
     */
    public static final String DB_COL_ID_LOGIN = "ID_LOGIN";
    public static final String DB_COL_ID_PRODUIT = "ID_PRODUIT";
    public static final String DB_COL_RATING = "NOTE";

    /**
     *
     */
    public static final String DB_COL_PROD_ID = DB_TABLE_PRODUCT + "." + DB_COL_ID_PROD;
    public static final String DB_COL_RATING_ID = DB_TABLE_RATING + "." + DB_COL_ID_PRODUIT;
    public static final String DB_TABLES = DB_TABLE_PRODUCT + " INNER JOIN " + DB_TABLE_RATING + " ON " + DB_COL_PROD_ID + " = " + DB_COL_RATING_ID;

    /**
     * Nom de colonne sur laquelle le tri est effectué
     */
    public static String order_by = DB_COL_NAME;

    /**
     * Ordre de tri : ASC pour croissant et DESC pour décroissant
     */
    public static String order = "ASC";

    /**
     * Variables du produit courant
     */
    private final int id;
    private String description;
    private String name;
    private String category;
    private String image;
    private float price;
    private int threshold;

    private SparseArray<Float> rating;

    /**
     * Constructor
     */
    private Product(int pId) {

        // On enregistre l'id dans la variable d'instance.
        this.id = pId;
        // On enregistre l'instance de l'élément de collection courant dans la hashMap.
        Product.productSparseArray.put(pId, this);

        // On charge les données depuis la base de données.
        loadData();
    }

    /**
     * Fournit l'id de l'élément de collection courant.
     */
    public int getId() {
        return id;
    }

    /**
     * Fournit le nom de l'élément de collection courant.
     */
    public String getName() {
        return name;
    }

    /**
     * Fournit la description de l'élément de collection courant ou null s'il n'y a aucune
     * description.
     */
    public String getDescription() {
        return description;
    }

    public String getPrice(){
        return ""+price;
    }

    /**
     * Fournit la note de l'élément de collection courant (comprise entre 0 et 5).
     */
    public float getRating() {
        return rating.get(User.getConnectedUser().getId());
    }

    /**
     * Modifie la note de l'objet courant pour l'utilisateur actuellement connecté à l'application.
     *
     * @param newRating Nouvelle note pour l´objet courant.
     *
     * @return Retourne vrai (true) si l´opération s´est bien déroulée, faux (false) sinon.
     * @pre newRating doit être compris dans [0;5].
     * @post Modifie la newRating de l'objet courant dans la base de données.
     */
    public boolean setRating(float newRating) {

        // Vérification de la pré-condition.
        if (newRating < 0 || newRating > 5) {
            return false;
        }

        // Récupération de la base de données en mode "écriture".
        SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();

        // Indique les valeurs à mettre à jour.
        ContentValues values = new ContentValues();
        values.put(DB_COL_RATING, newRating);

        // Indique sur quelle ligne effectuer la mise à jour.
        String selection = DB_COL_ID_LOGIN + " = ? AND " + DB_COL_ID_PRODUIT + " = ?";
        String[] selectionArgs = new String[]{String.valueOf(User.getConnectedUser().getId()), String.valueOf(id)};

        // Requête UPDATE sur la base de données.
        db.update(DB_TABLE_RATING, values, selection, selectionArgs);

        // Fermeture de la base de données.
        db.close();

        // Mise à jour de la note de l'élément courant pour l'utilisateur connecté.
        this.rating.put(User.getConnectedUser().getId(), newRating);

        return true;
    }

    private void loadData() {
        // Récupération de la base de données en mode "lecture".
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();

        String[] columns = new String[]{ DB_COL_DESCR, DB_COL_NAME, DB_COL_CAT, DB_COL_IMG, DB_COL_PRICE, DB_COL_THRESH};
        String selection = DB_COL_ID_PROD + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(id)};
        Cursor c = db.query(DB_TABLE_PRODUCT, columns, selection, selectionArgs, null, null, null);

        c.moveToFirst();
        this.description = c.getString(0);
        this.name = c.getString(1);
        this.category = c.getString(2);
        this.image = c.getString(3);
        this.price = c.getFloat(4);
        this.threshold = c.getInt(5);
        c.close();

        /* Récupération des noms dans la table STRING */
        columns = new String[]{DB_COL_TEXTE};
        selection = DB_COL_ID_STRING + " = ? AND " + DB_COL_LANGUE + " = ?";
        selectionArgs = new String[]{this.name, User.getConnectedUser().getLanguage()};
        c = db.query(DB_TABLE_STRING, columns, selection, selectionArgs, null, null, null);

        c.moveToFirst();
        this.name = c.getString(0);
        c.close();

        /* Récupération des différentes notes pour les différents utilisateurs. */
//        this.rating = new SparseArray<Float>();
//
//        columns = new String[]{DB_COL_ID_LOGIN, DB_COL_RATING};
//        selection = DB_COL_ID_PRODUIT + " = ?";
//        selectionArgs = new String[]{String.valueOf(id)};
//        c = db.query(DB_TABLE_RATING, columns, selection, selectionArgs, null, null, null);
//
//        c.moveToFirst();
//        while (!c.isAfterLast()) {
//            this.rating.put(c.getInt(0), c.getFloat(1));
//            c.moveToNext();
//        }
//        c.close();
        db.close();
    }



    /******************************************************************************
     * Partie static de la classe.
     ******************************************************************************/

    /**
     * Contient les instances déjà existantes des objets afin d'éviter de créer deux instances du
     * même objet.
     */
    private static final SparseArray<Product> productSparseArray = new SparseArray<Product>();


    /**
     * Crée un nouvel élément dans la base de données et l'associe à l'utilisateur actuellement
     * connecté.
     *
     * @param name        Nom du nouvel élément de collection.
     * @param description Description du nouvel élément de collection.
     * @param rating      Note pour le nouvel élément de collection. Doit être comprise entre 0 et 5
     *                    (sinon est considérée comme null).
     * @param picture     Fichier picture pour le nouvel objet.
     *
     * @return Vrai (true) en cas de succès, faux (false) en cas d'échec.
     * @post Enregistre le nouvel objet dans la base de données.
     */
    public static boolean create(String name, String description, float rating, String picture) {

        // Récupération de la base de données.
        SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();

        // Définition des valeurs pour le nouvel élément dans la table "PRODUIT".
        ContentValues cv = new ContentValues();
        cv.put(DB_COL_NAME, name);
        cv.put(DB_COL_DESCR, description);
        cv.put(DB_COL_IMG, picture);

        // Ajout à la base de données (table collected_items).
        int ci_id = (int) db.insert(DB_TABLE_PRODUCT, null, cv);

        if (ci_id == -1) {
            return false; // En cas d'erreur d'ajout, on retourne false directement.
        }
        cv.clear();

        // Définition des valeurs pour le nouvel élément dans la table "RATING".
        cv.put(DB_COL_ID_PRODUIT, ci_id);
        cv.put(DB_COL_ID_LOGIN, User.getConnectedUser().getId());
        cv.put(DB_COL_RATING, rating);

        int result = (int) db.insert(DB_TABLE_RATING, null, cv);

        if (result == -1) {
            // En cas d'erreur dans l'ajout de la deuxième table, il faut supprimer la ligne qu'on
            // vient d'ajouter dans la première table pour ne pas qu'il y ait un élément qui n'est
            // dans la collection de personne.
            db.delete(DB_TABLE_PRODUCT, DB_COL_ID_PROD + " = ?", new String[]{String.valueOf(ci_id)});
            return false;
        }
        return true;
    }

    /**
     * Fournit la liste de tous les éléments de la collection de l'utilisateur courant.
     *
     * @return Liste d'éléments.
     */
    public static ArrayList<Product> getProducts() {
//        // Récupération de l'ID de l'utilisateur courant.
//        int u_id = User.getConnectedUser().getId();
//
//        // Critère de sélection : appartient à l'utilisateur courant.
//        String selection = DB_COL_ID_LOGIN + " = ?";
//        String[] selectionArgs = new String[]{String.valueOf(u_id)};
//
//        // Le critère de sélection est passé à la sous-méthode de récupération des éléments.
//        return getProducts(selection, selectionArgs);
        return getProducts(null, null);
    }

    /**
     * Fournit la liste de tous les éléments de la collection de l'utilisateur courant dont le nom
     * contient searchQuery.
     *
     * @param searchQuery Requête de recherche.
     *
     * @return Liste d'éléments de collection répondant à la requête de recherche.
     */
    public static ArrayList<Product> searchProducts(String searchQuery) {
        // Récupération de l'id de l'utilisateur courant.
        int u_id = User.getConnectedUser().getId();

        // Critères de sélection (partie WHERE) : appartiennent à l'utilisateur courant et ont un nom
        // correspondant à la requête de recherche.
        String selection = DB_COL_ID_LOGIN + " = ? AND " + DB_COL_NAME + " LIKE ?";
        String[] selectionArgs = new String[]{String.valueOf(u_id), "%" + searchQuery + "%"};

        // Les critères de selection sont passés à la sous-méthode de récupération des éléments.
        return getProducts(selection, selectionArgs);
    }

    /**
     * Fournit la liste de tous les objets correspondant aux critères de sélection demandés.
     *
     * Cette méthode est une sous-méthode de getCollectedItems et de searchCollectedItems.
     *
     * @param selection     Un filtre déclarant quels éléments retourner, formaté comme la clause
     *                      SQL WHERE (excluant le WHERE lui-même). Donner null retournera tous les
     *                      éléments.
     * @param selectionArgs Vous pouvez inclure des ? dans selection, qui seront remplacés par les
     *                      valeurs de selectionArgs, dans leur ordre d'apparition dans selection.
     *                      Les valeurs seront liées en tant que chaînes.
     *
     * @return Liste d'objets. La liste peut être vide si aucun objet ne correspond.
     */
    private static ArrayList<Product> getProducts(String selection, String[] selectionArgs) {
        // Initialisation de la liste des products.
        ArrayList<Product> products = new ArrayList<Product>();

        // Récupération du SQLiteHelper pour récupérer la base de données.
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();

        // Colonnes à récupérer. Ici uniquement l'id de l'élément, le reste sera récupéré par
        // loadData() à la création de l'instance de l'élément. (choix de développement).
        String[] columns = new String[]{DB_COL_PROD_ID};

        // Requête SELECT à la base de données.
        Cursor c = db.query(DB_TABLE_PRODUCT, columns, selection, selectionArgs, null, null, Product.order_by + " " + Product.order);

        c.moveToFirst();
        while (!c.isAfterLast()) {
            // Id de l'élément.
            int pId = c.getInt(0);
            // L'instance de l'élément de collection est récupéré avec la méthode get(ciId)
            // (Si l'instance n'existe pas encore, elle est créée par la méthode get)
            Product product = Product.get(pId);

            // Ajout de l'élément de collection à la liste.
            products.add(product);

            c.moveToNext();
        }

        // Fermeture du curseur et de la base de données.
        c.close();
        db.close();

        return products;
    }


    /**
     * Fournit l'instance d'un élément de collection présent dans la base de données. Si l'élément
     * de collection n'est pas encore instancié, une instance est créée.
     *
     * @param pId Id de l'élément de collection.
     *
     * @return L'instance de l'élément de collection.
     * @pre L'élément correspondant à l'id donné doit exister dans la base de données.
     */
    public static Product get(int pId) {
        Product p = Product.productSparseArray.get(pId);
        if (p != null) {
            return p;
        }
        return new Product(pId);
    }


    /**
     * Inverse l'ordre de tri actuel.
     *
     * @pre La valeur de CollectedItem.order est soit ASC soit DESC.
     * @post La valeur de CollectedItem.order a été modifiée et est soit ASC soit DESC.
     */
    public static void reverseOrder() {
        if (Product.order.equals("ASC")) {
            Product.order = "DESC";
        } else {
            Product.order = "ASC";
        }
    }
}
