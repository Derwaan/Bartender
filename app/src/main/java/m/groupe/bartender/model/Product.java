package m.groupe.bartender.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.SparseArray;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import m.groupe.bartender.BartenderApp;
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
     *  Unions
     */
    public static final String DB_COL_PROD_ID = DB_TABLE_PRODUCT + "." + DB_COL_ID_PROD;
    public static final String DB_COL_RATING_ID = DB_TABLE_RATING + "." + DB_COL_ID_PRODUIT;
    public static final String DB_TABLES = DB_TABLE_PRODUCT + " INNER JOIN " + DB_TABLE_RATING + " ON " + DB_COL_PROD_ID + " = " + DB_COL_RATING_ID;
    public static final String DB_TABLES_PRODUCT_STRING = DB_TABLE_PRODUCT + " INNER JOIN " + DB_TABLE_STRING + " ON " + DB_COL_ID_STRING + " = " + DB_COL_NAME;
    /**
     * Nom de colonne sur laquelle le tri est effectue
     */
    public static String order_by = DB_COL_NAME;

    /**
     * Ordre de tri : ASC pour croissant et DESC pour decroissant
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
        // On enregistre l'instance de l'element de collection courant dans la hashMap.
        Product.productSparseArray.put(pId, this);

        // On charge les donnees depuis la base de donnees.
        loadData();
    }

    /**
     * Fournit l'id de l'element de collection courant.
     */
    public int getId() {
        return id;
    }

    /**
     * Fournit le nom de l'element de collection courant.
     */
    public String getName() {
        return "  "+name;
    }

    /**
     * Fournit la description de l'element de collection courant ou null s'il n'y a aucune
     * description.
     */
    public String getDescription() {
        return "  "+description;
    }

    public Bitmap getImage() {
        if (this.image == null) {
            return null;
        }

        try {
            FileInputStream in = BartenderApp.getContext().openFileInput(image);
            Bitmap bitmap = BitmapFactory.decodeStream(in);
            in.close();

            return bitmap;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getPrice(){
        return ""+price;
    }

    /**
     * Fournit la note de l'element de collection courant (comprise entre 0 et 5).
     */
    public float getRating() {
        if(rating.get(User.getConnectedUser().getId()) == null)
            return 0;
        else{
            return rating.get(User.getConnectedUser().getId());
        }
    }

    /**
     * Modifie la note de l'objet courant pour l'utilisateur actuellement connecte e l'application.
     *
     * @param newRating Nouvelle note pour leobjet courant.
     *
     * @return Retourne vrai (true) si leoperation s'est bien deroulee, faux (false) sinon.
     * @pre newRating doit etre compris dans [0;5].
     * @post Modifie la newRating de l'objet courant dans la base de donnees.
     */
    public boolean setRating(float newRating) {

        // Verification de la pre-condition.
        if (newRating < 0 || newRating > 5) {
            return false;
        }

        // Recuperation de la base de donnees en mode "ecriture".
        SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();

        // Indique les valeurs a mettre e jour.
        ContentValues values = new ContentValues();
        values.put(DB_COL_RATING, newRating);

        // Indique sur quelle ligne effectuer la mise e jour.
        String selection = DB_COL_ID_LOGIN + " = ? AND " + DB_COL_ID_PRODUIT + " = ?";
        String[] selectionArgs = new String[]{String.valueOf(User.getConnectedUser().getId()), String.valueOf(id)};

        // Requete UPDATE sur la base de donnees.
        db.update(DB_TABLE_RATING, values, selection, selectionArgs);

        // Fermeture de la base de donnees.
        db.close();

        // Mise e jour de la note de l'element courant pour l'utilisateur connecte.
        this.rating.put(User.getConnectedUser().getId(), newRating);

        return true;
    }

    private void loadData() {
        // Recuperation de la base de donnees en mode "lecture".
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

        /**
         *  Recuperation des noms dans la table STRING
         */
        columns = new String[]{DB_COL_TEXTE};
        selection = DB_COL_ID_STRING + " = ? AND " + DB_COL_LANGUE + " = ?";
        selectionArgs = new String[]{this.name, User.getConnectedUser().getLanguage()};
        c = db.query(DB_TABLE_STRING, columns, selection, selectionArgs, null, null, null);

        c.moveToFirst();
        this.name = c.getString(0);
        c.close();

        /**
         *  Recuperation des descriptions dans la table STRING
         */
        columns = new String[]{DB_COL_TEXTE};
        selection = DB_COL_ID_STRING + " = ? AND " + DB_COL_LANGUE + " = ?";
        selectionArgs = new String[]{this.description, User.getConnectedUser().getLanguage()};
        c = db.query(DB_TABLE_STRING, columns, selection, selectionArgs, null, null, null);

        c.moveToFirst();
        this.description = c.getString(0);
        c.close();

        /**
         * Recuperation des differentes notes pour les differents utilisateurs.
         */
        this.rating = new SparseArray<Float>();

        columns = new String[]{DB_COL_ID_LOGIN, DB_COL_RATING};
        selection = DB_COL_ID_PRODUIT + " = ?";
        selectionArgs = new String[]{""+User.getConnectedUser().getId()};
        c = db.query(DB_TABLE_RATING, columns, selection, selectionArgs, null, null, null);

        c.moveToFirst();
        while (!c.isAfterLast()) {
            this.rating.put(c.getInt(0), c.getFloat(1));
            c.moveToNext();
        }
        c.close();
        db.close();
    }



    /******************************************************************************
     * Partie static de la classe.
     ******************************************************************************/

    /**
     * Contient les instances deje existantes des objets afin d'eviter de creer deux instances du
     * meme objet.
     */
    private static final SparseArray<Product> productSparseArray = new SparseArray<Product>();


    /**
     * Cree un nouvel element dans la base de donnees et l'associe e l'utilisateur actuellement
     * connecte.
     *
     * @param name        Nom du nouvel element de collection.
     * @param description Description du nouvel element de collection.
     * @param rating      Note pour le nouvel element de collection. Doit etre comprise entre 0 et 5
     *                    (sinon est consideree comme null).
     * @param picture     Fichier picture pour le nouvel objet.
     *
     * @return Vrai (true) en cas de succes, faux (false) en cas d'echec.
     * @post Enregistre le nouvel objet dans la base de donnees.
     */
    public static boolean create(String name, String description, float rating, String picture) {

        // Recuperation de la base de donnees.
        SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();

        // Definition des valeurs pour le nouvel element dans la table "PRODUIT".
        ContentValues cv = new ContentValues();
        cv.put(DB_COL_NAME, name);
        cv.put(DB_COL_DESCR, description);
        cv.put(DB_COL_IMG, picture);

        // Ajout e la base de donnees (table collected_items).
        int ci_id = (int) db.insert(DB_TABLE_PRODUCT, null, cv);

        if (ci_id == -1) {
            return false; // En cas d'erreur d'ajout, on retourne false directement.
        }
        cv.clear();

        // Definition des valeurs pour le nouvel element dans la table "RATING".
        cv.put(DB_COL_ID_PRODUIT, ci_id);
        cv.put(DB_COL_ID_LOGIN, User.getConnectedUser().getId());
        cv.put(DB_COL_RATING, rating);

        int result = (int) db.insert(DB_TABLE_RATING, null, cv);

        if (result == -1) {
            // En cas d'erreur dans l'ajout de la deuxieme table, il faut supprimer la ligne qu'on
            // vient d'ajouter dans la premiere table pour ne pas qu'il y ait un element qui n'est
            // dans la collection de personne.
            db.delete(DB_TABLE_PRODUCT, DB_COL_ID_PROD + " = ?", new String[]{String.valueOf(ci_id)});
            return false;
        }
        return true;
    }

    /**
     * Fournit la liste de tous les elements de la collection de l'utilisateur courant.
     *
     * @return Liste d'elements.
     */
    public static ArrayList<Product> getProducts() {
//        // Recuperation de l'ID de l'utilisateur courant.
//        int u_id = User.getConnectedUser().getId();
//
//        // Critere de selection : appartient e l'utilisateur courant.
//        String selection = DB_COL_ID_LOGIN + " = ?";
//        String[] selectionArgs = new String[]{String.valueOf(u_id)};
//
//        // Le critere de selection est passe e la sous-methode de recuperation des elements.
//        return getProducts(selection, selectionArgs);
        return getProducts(null, null);
    }

    /**
     * Fournit la liste de tous les elements de la collection de l'utilisateur courant dont le nom
     * contient searchQuery.
     *
     * @param searchQuery Requete de recherche.
     *
     * @return Liste d'elements de collection repondant e la requete de recherche.
     */
    public static ArrayList<Product> searchProducts(String searchQuery) {
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        ArrayList<Product> products = new ArrayList<Product>();

        String[] columns = new String[]{DB_COL_ID_PROD};
        String selection = DB_COL_LANGUE + " = ? AND " + DB_COL_TEXTE + " LIKE ?";
        String[] selectionArgs = new String[]{User.getConnectedUser().getLanguage(), "%" + searchQuery + "%"};
        Cursor c = db.query(DB_TABLES_PRODUCT_STRING, columns, selection, selectionArgs, null, null, null);

        c.moveToFirst();
        while (!c.isAfterLast()) {
            int pId = c.getInt(0);
            Product product = Product.get(pId);
            products.add(product);
            c.moveToNext();
        }
        c.close();
        return products;
    }

    /**
     * Fournit la liste de tous les objets correspondant aux criteres de selection demandes.
     *
     * Cette methode est une sous-methode de getCollectedItems et de searchCollectedItems.
     *
     * @param selection     Un filtre declarant quels elements retourner, formate comme la clause
     *                      SQL WHERE (excluant le WHERE lui-meme). Donner null retournera tous les
     *                      elements.
     * @param selectionArgs Vous pouvez inclure des ? dans selection, qui seront remplaces par les
     *                      valeurs de selectionArgs, dans leur ordre d'apparition dans selection.
     *                      Les valeurs seront liees en tant que chaenes.
     *
     * @return Liste d'objets. La liste peut etre vide si aucun objet ne correspond.
     */
    private static ArrayList<Product> getProducts(String selection, String[] selectionArgs) {
        ArrayList<Product> products = new ArrayList<Product>();
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();

        String[] columns = new String[]{DB_COL_ID_PROD};

        Cursor c = db.query(DB_TABLE_PRODUCT, columns, selection, selectionArgs, null, null, Product.order_by + " " + Product.order);

        c.moveToFirst();
        while (!c.isAfterLast()) {
            int pId = c.getInt(0);
            Product product = Product.get(pId);
            products.add(product);
            c.moveToNext();
        }
        c.close();
        db.close();

        return products;
    }


    /**
     * Fournit l'instance d'un element de collection present dans la base de donnees. Si l'element
     * de collection n'est pas encore instancie, une instance est creee.
     *
     * @param pId Id de l'element de collection.
     *
     * @return L'instance de l'element de collection.
     * @pre L'element correspondant e l'id donne doit exister dans la base de donnees.
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
     * @post La valeur de CollectedItem.order a ete modifiee et est soit ASC soit DESC.
     */
    public static void reverseOrder() {
        if (Product.order.equals("ASC")) {
            Product.order = "DESC";
        } else {
            Product.order = "ASC";
        }
    }
}
