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
     * Nom de colonne sur laquelle le tri est effectu�
     */
    public static String order_by = DB_COL_NAME;

    /**
     * Ordre de tri : ASC pour croissant et DESC pour d�croissant
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
        // On enregistre l'instance de l'�l�ment de collection courant dans la hashMap.
        Product.productSparseArray.put(pId, this);

        // On charge les donn�es depuis la base de donn�es.
        loadData();
    }

    /**
     * Fournit l'id de l'�l�ment de collection courant.
     */
    public int getId() {
        return id;
    }

    /**
     * Fournit le nom de l'�l�ment de collection courant.
     */
    public String getName() {
        return name;
    }

    /**
     * Fournit la description de l'�l�ment de collection courant ou null s'il n'y a aucune
     * description.
     */
    public String getDescription() {
        return description;
    }

    public String getPrice(){
        return ""+price;
    }

    /**
     * Fournit la note de l'�l�ment de collection courant (comprise entre 0 et 5).
     */
    public float getRating() {
        return rating.get(User.getConnectedUser().getId());
    }

    /**
     * Modifie la note de l'objet courant pour l'utilisateur actuellement connect� � l'application.
     *
     * @param newRating Nouvelle note pour l�objet courant.
     *
     * @return Retourne vrai (true) si l�op�ration s�est bien d�roul�e, faux (false) sinon.
     * @pre newRating doit �tre compris dans [0;5].
     * @post Modifie la newRating de l'objet courant dans la base de donn�es.
     */
    public boolean setRating(float newRating) {

        // V�rification de la pr�-condition.
        if (newRating < 0 || newRating > 5) {
            return false;
        }

        // R�cup�ration de la base de donn�es en mode "�criture".
        SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();

        // Indique les valeurs � mettre � jour.
        ContentValues values = new ContentValues();
        values.put(DB_COL_RATING, newRating);

        // Indique sur quelle ligne effectuer la mise � jour.
        String selection = DB_COL_ID_LOGIN + " = ? AND " + DB_COL_ID_PRODUIT + " = ?";
        String[] selectionArgs = new String[]{String.valueOf(User.getConnectedUser().getId()), String.valueOf(id)};

        // Requ�te UPDATE sur la base de donn�es.
        db.update(DB_TABLE_RATING, values, selection, selectionArgs);

        // Fermeture de la base de donn�es.
        db.close();

        // Mise � jour de la note de l'�l�ment courant pour l'utilisateur connect�.
        this.rating.put(User.getConnectedUser().getId(), newRating);

        return true;
    }

    private void loadData() {
        // R�cup�ration de la base de donn�es en mode "lecture".
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

        /* R�cup�ration des noms dans la table STRING */
        columns = new String[]{DB_COL_TEXTE};
        selection = DB_COL_ID_STRING + " = ? AND " + DB_COL_LANGUE + " = ?";
        selectionArgs = new String[]{this.name, User.getConnectedUser().getLanguage()};
        c = db.query(DB_TABLE_STRING, columns, selection, selectionArgs, null, null, null);

        c.moveToFirst();
        this.name = c.getString(0);
        c.close();

        /* R�cup�ration des diff�rentes notes pour les diff�rents utilisateurs. */
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
     * Contient les instances d�j� existantes des objets afin d'�viter de cr�er deux instances du
     * m�me objet.
     */
    private static final SparseArray<Product> productSparseArray = new SparseArray<Product>();


    /**
     * Cr�e un nouvel �l�ment dans la base de donn�es et l'associe � l'utilisateur actuellement
     * connect�.
     *
     * @param name        Nom du nouvel �l�ment de collection.
     * @param description Description du nouvel �l�ment de collection.
     * @param rating      Note pour le nouvel �l�ment de collection. Doit �tre comprise entre 0 et 5
     *                    (sinon est consid�r�e comme null).
     * @param picture     Fichier picture pour le nouvel objet.
     *
     * @return Vrai (true) en cas de succ�s, faux (false) en cas d'�chec.
     * @post Enregistre le nouvel objet dans la base de donn�es.
     */
    public static boolean create(String name, String description, float rating, String picture) {

        // R�cup�ration de la base de donn�es.
        SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();

        // D�finition des valeurs pour le nouvel �l�ment dans la table "PRODUIT".
        ContentValues cv = new ContentValues();
        cv.put(DB_COL_NAME, name);
        cv.put(DB_COL_DESCR, description);
        cv.put(DB_COL_IMG, picture);

        // Ajout � la base de donn�es (table collected_items).
        int ci_id = (int) db.insert(DB_TABLE_PRODUCT, null, cv);

        if (ci_id == -1) {
            return false; // En cas d'erreur d'ajout, on retourne false directement.
        }
        cv.clear();

        // D�finition des valeurs pour le nouvel �l�ment dans la table "RATING".
        cv.put(DB_COL_ID_PRODUIT, ci_id);
        cv.put(DB_COL_ID_LOGIN, User.getConnectedUser().getId());
        cv.put(DB_COL_RATING, rating);

        int result = (int) db.insert(DB_TABLE_RATING, null, cv);

        if (result == -1) {
            // En cas d'erreur dans l'ajout de la deuxi�me table, il faut supprimer la ligne qu'on
            // vient d'ajouter dans la premi�re table pour ne pas qu'il y ait un �l�ment qui n'est
            // dans la collection de personne.
            db.delete(DB_TABLE_PRODUCT, DB_COL_ID_PROD + " = ?", new String[]{String.valueOf(ci_id)});
            return false;
        }
        return true;
    }

    /**
     * Fournit la liste de tous les �l�ments de la collection de l'utilisateur courant.
     *
     * @return Liste d'�l�ments.
     */
    public static ArrayList<Product> getProducts() {
//        // R�cup�ration de l'ID de l'utilisateur courant.
//        int u_id = User.getConnectedUser().getId();
//
//        // Crit�re de s�lection : appartient � l'utilisateur courant.
//        String selection = DB_COL_ID_LOGIN + " = ?";
//        String[] selectionArgs = new String[]{String.valueOf(u_id)};
//
//        // Le crit�re de s�lection est pass� � la sous-m�thode de r�cup�ration des �l�ments.
//        return getProducts(selection, selectionArgs);
        return getProducts(null, null);
    }

    /**
     * Fournit la liste de tous les �l�ments de la collection de l'utilisateur courant dont le nom
     * contient searchQuery.
     *
     * @param searchQuery Requ�te de recherche.
     *
     * @return Liste d'�l�ments de collection r�pondant � la requ�te de recherche.
     */
    public static ArrayList<Product> searchProducts(String searchQuery) {
        // R�cup�ration de l'id de l'utilisateur courant.
        int u_id = User.getConnectedUser().getId();

        // Crit�res de s�lection (partie WHERE) :�appartiennent � l'utilisateur courant et ont un nom
        // correspondant � la requ�te de recherche.
        String selection = DB_COL_ID_LOGIN + " = ? AND " + DB_COL_NAME + " LIKE ?";
        String[] selectionArgs = new String[]{String.valueOf(u_id), "%" + searchQuery + "%"};

        // Les crit�res de selection sont pass�s � la sous-m�thode de r�cup�ration des �l�ments.
        return getProducts(selection, selectionArgs);
    }

    /**
     * Fournit la liste de tous les objets correspondant aux crit�res de s�lection demand�s.
     *
     * Cette m�thode est une sous-m�thode de getCollectedItems et de searchCollectedItems.
     *
     * @param selection     Un filtre d�clarant quels �l�ments retourner, format� comme la clause
     *                      SQL WHERE (excluant le WHERE lui-m�me). Donner null retournera tous les
     *                      �l�ments.
     * @param selectionArgs Vous pouvez inclure des ? dans selection, qui seront remplac�s par les
     *                      valeurs de selectionArgs, dans leur ordre d'apparition dans selection.
     *                      Les valeurs seront li�es en tant que cha�nes.
     *
     * @return Liste d'objets. La liste peut �tre vide si aucun objet ne correspond.
     */
    private static ArrayList<Product> getProducts(String selection, String[] selectionArgs) {
        // Initialisation de la liste des products.
        ArrayList<Product> products = new ArrayList<Product>();

        // R�cup�ration du SQLiteHelper pour r�cup�rer la base de donn�es.
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();

        // Colonnes � r�cup�rer. Ici uniquement l'id de l'�l�ment, le reste sera r�cup�r� par
        // loadData() � la cr�ation de l'instance de l'�l�ment. (choix de d�veloppement).
        String[] columns = new String[]{DB_COL_PROD_ID};

        // Requ�te SELECT � la base de donn�es.
        Cursor c = db.query(DB_TABLE_PRODUCT, columns, selection, selectionArgs, null, null, Product.order_by + " " + Product.order);

        c.moveToFirst();
        while (!c.isAfterLast()) {
            // Id de l'�l�ment.
            int pId = c.getInt(0);
            // L'instance de l'�l�ment de collection est r�cup�r� avec la m�thode get(ciId)
            // (Si l'instance n'existe pas encore, elle est cr��e par la m�thode get)
            Product product = Product.get(pId);

            // Ajout de l'�l�ment de collection � la liste.
            products.add(product);

            c.moveToNext();
        }

        // Fermeture du curseur et de la base de donn�es.
        c.close();
        db.close();

        return products;
    }


    /**
     * Fournit l'instance d'un �l�ment de collection pr�sent dans la base de donn�es. Si l'�l�ment
     * de collection n'est pas encore instanci�, une instance est cr��e.
     *
     * @param pId Id de l'�l�ment de collection.
     *
     * @return L'instance de l'�l�ment de collection.
     * @pre L'�l�ment correspondant � l'id donn� doit exister dans la base de donn�es.
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
     * @post La valeur de CollectedItem.order a �t� modifi�e et est soit ASC soit DESC.
     */
    public static void reverseOrder() {
        if (Product.order.equals("ASC")) {
            Product.order = "DESC";
        } else {
            Product.order = "ASC";
        }
    }
}
