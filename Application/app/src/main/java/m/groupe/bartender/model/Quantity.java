package m.groupe.bartender.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import m.groupe.bartender.MySQLiteHelper;

public class Quantity {
    /**
     * Database
     */
    private static final String DB_TABLE = "QUANTITY";

    private static final String DB_COLUMN_ID_PRODUCT = "ID_PROD";
    private static final String DB_COLUMN_ID_ORDER = "ID_COMMAND2";
    private static final String DB_COLUMN_QUANTITY = "QUANTITY";

    /**
     * Variables
     */
    private int id_product;
    private int id_order;
    private int quantity;

    public Quantity(int id_product, int id_order, int quantity){
        this.id_product = id_product;
        this.id_order = id_order;
        this.quantity = quantity;
    }

    public int getId_product() {
        return id_product;
    }

    public int getId_order() {
        return id_order;
    }

    public int getQuantity() {
        return quantity;
    }

    /******************************************************************************
     * Partie static de la classe.
     ******************************************************************************/

    public static boolean addToDB(Quantity qty){
        boolean res;
        SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DB_COLUMN_ID_PRODUCT, qty.getId_product());
        cv.put(DB_COLUMN_ID_ORDER, qty.getId_order());
        cv.put(DB_COLUMN_QUANTITY, qty.getQuantity());
        res = db.insert(DB_TABLE, null, cv) != -1;
        cv.clear();
        db.close();
        return res;
    }

    public static String getName(Quantity qty){
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();
        String[] columns = new String[]{"TEXTE"};
        String selection = "ID_PROD = ? AND LANGUE = ?";
        String []selectionArgs = {""+qty.getId_product(), User.getConnectedUser().getLanguage()};

        Cursor c = db.query("PRODUCT INNER JOIN STRING ON NOM_PRODUIT = ID STRING", columns, selection, selectionArgs, null, null, null);
        c.moveToFirst();

        while (!c.isAfterLast()) {
            String text = c.getString(0);
            return text;
        }
        c.close();
        db.close();
        return null;
    }
}
