package m.groupe.bartender.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Calendar;

import m.groupe.bartender.MySQLiteHelper;
public class Order {
    /**
     * Database
     */
    private static final String DB_TABLE_ORDER = "COMMAND";
    private static final String DB_TABLE_PRODUCT = "PRODUIT";
    private static final String DB_TABLE_QUANTITY = "QUANTITY";
    private static final String DB_TABLE_STRING = "STRING";

    private static final String DB_COLUMN_ID_ORDER = "ID_COMMAND";
    private static final String DB_COLUMN_ID_USER = "ID_LOGIN";
    private static final String DB_COLUMN_STATE = "STATE";
    private static final String DB_COLUMN_DATE = "DATE_COMMAND";
    private static final String DB_COLUMN_TABLE = "TABL";

    private static final String DB_COLUMN_ID_PRODUCT = "ID_PROD";
    private static final String DB_COLUMN_ID_ORDER2 = "ID_COMMAND2";
    private static final String DB_COLUMN_QUANTITY = "QUANTITY";

    public static final String DB_TABLES_ALL = DB_TABLE_ORDER + " INNER JOIN " + DB_TABLE_QUANTITY + " ON " + DB_COLUMN_ID_ORDER + " = " + DB_COLUMN_ID_ORDER2; //+ " INNER JOIN " + DB_TABLE_PRODUCT + " ON " + DB_COLUMN_ID_PRODUCT + " = " + DB_COLUMN_ID_PRODUCT + " INNER JOIN " + DB_TABLE_STRING + " ON NOM_PRODUIT = ID_STRING";
    /**
     * Variables declaration
     */
    private int id_order;
    private int id_user;
    private int state;
    private String date;
    private int table;

    /**
     * Constructor
     * @param id_order
     * @param id_user
     * @param state
     * @param date
     * @param table
     */
    public Order(int id_order, int id_user, int state, String date, int table){
        this.id_order = id_order;
        this.id_user = id_user;
        this.state = state;
        this.date = date;
        this.table = table;
    }

    public int getId_order() {
        return id_order;
    }

    public int getId_user() {
        return id_user;
    }

    public String getDate() {
        return date;
    }

    /******************************************************************************
     * Partie static de la classe.
     ******************************************************************************/

    private static Order currentOrder = null;

    public static Order getCurrentOrder(){
        return Order.currentOrder;
    }

    public static void createOrder() {
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();

        //Getting order's id
        Cursor last_id = db.rawQuery("SELECT MAX(COMMAND.ID_COMMAND) FROM COMMAND", null);
        if (last_id != null) {
            last_id.moveToFirst();
        }
        int id = last_id.getInt(0) + 1;
        last_id.close();
        db.close();

        //Getting date
        Calendar c = Calendar.getInstance();
        String date = "" + c.get(Calendar.DATE);

        //Create the new order
        Order order = new Order(id, User.getConnectedUser().getId(), 0, date, 1);
        currentOrder = order;

    }

    public static boolean writeOrder(){
        SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DB_COLUMN_ID_ORDER, currentOrder.getId_order());
        cv.put(DB_COLUMN_ID_USER, currentOrder.getId_user());
        cv.put(DB_COLUMN_STATE, 0);
        cv.put(DB_COLUMN_DATE, currentOrder.getDate());
        cv.put(DB_COLUMN_TABLE, 1);

        int res = (int) db.insert(DB_TABLE_ORDER, null, cv);

        if (res == -1) {
            return false;
        }
        cv.clear();
        db.close();
        return true;
    }

    public static ArrayList<Quantity> getOrder() {
        ArrayList<Quantity> qty = new ArrayList<Quantity>();
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();

        String[] columns = new String[]{DB_COLUMN_ID_PRODUCT, DB_COLUMN_ID_ORDER, DB_COLUMN_QUANTITY};
        String selection = "ID_LOGIN = ? AND " + DB_COLUMN_ID_ORDER + " = ?";
        String []selectionArgs = {""+User.getConnectedUser().getId(), ""+Order.getCurrentOrder().getId_order()};

        Cursor c = db.query(DB_TABLES_ALL, columns, selection, selectionArgs, null, null, null);

        c.moveToFirst();
        while (!c.isAfterLast()) {
            int id_prod = c.getInt(0);
            int id_order = c.getInt(1);
            int quantity = c.getInt(2);
            Quantity item = new Quantity(id_prod, id_order, quantity);
            qty.add(item);
            c.moveToNext();
        }
        c.close();
        db.close();

        return qty;
    }
}
