package m.groupe.bartender;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Classe utilitaire qui va gérer la connexion, la création et la mise à jour de la base de données.
 *
 * Cette classe va s'occuper de gérer la base de données. Elle s'occupera d'en créer une nouvelle
 * lors du premier lancement de l'application. Ensuite, en cas d'évolution de version de la base de
 * données (par exemple lors d'une amélioration de votre application), elle mettra à jour celle-ci
 * de manière adéquate.
 *
 * @author Damien Mercier
 * @version 1
 * @see <a href="http://d.android.com/reference/android/database/sqlite/SQLiteOpenHelper.html">SQLiteOpenHelper</a>
 */
public class MySQLiteHelper extends SQLiteOpenHelper
{
    /**
     * Nom du fichier sql contenant les instructions de création de la base de données. Le fichier
     * doit être placé dans le dossier assets/
     */
    private static final String DATABASE_SQL_FILENAME = "foobar.sql";
    /**
     * Nom du fichier de la base de données.
     */
    private static final String DATABASE_NAME = "foobar.sqlite";

    /**
     * Version de la base de données (à incrémenter en cas de modification de celle-ci afin que la
     * méthode onUpgrade soit appelée).
     *
     * @note Le numéro de version doit changer de manière monotone.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Instance de notre classe afin de pouvoir y accéder facilement depuis n'importe quel objet.
     */
    private static MySQLiteHelper instance;

    /**
     * Constructeur. Instancie l'utilitaire de gestion de la base de données.
     *
     * @param context Contexte de l'application.
     */
    private MySQLiteHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        instance = this;
    }

    /**
     * Fournit une instance de notre MySQLiteHelper.
     *
     * @return MySQLiteHelper
     */
    public static MySQLiteHelper get()
    {
        if (instance == null)
        {
            return new MySQLiteHelper(BartenderApp.getContext());
        }
        return instance;
    }

    /**
     * Méthode d'initialisation appelée lors de la création de la base de données.
     *
     * @param db Base de données à initialiser
     */
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        initDatabase(db);
    }

    /**
     * Méthode de mise à jour lors du changement de version de la base de données.
     *
     * @param db         Base de données à mettre à jour.
     * @param oldVersion Numéro de l'ancienne version.
     * @param newVersion Numéro de la nouvelle version.
     *
     * @pre La base de données est dans la version oldVersion.
     * @post La base de données a été mise à jour vers la version newVersion.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        /**
         * @note : Ici on se contente juste de supprimer toutes les données et de les re-créer par
         * après. Dans une vraie application en production (par ex. sur le Play Store), il faudra
         * faire en sorte que les données enregistrées par l'utilisateur ne soient pas complètement
         * effacées lorsqu'on veut mettre à jour la structure de la base de données.
         */
        deleteDatabase(db);
        onCreate(db);
    }

    /**
     * Crée les tables de la base de données et les remplit.
     *
     * @param db Base de données à initialiser.
     *
     * @note À l'avenir on peut imaginer aller chercher les requêtes à effectuer dans un fichier
     * local (dans le dossier assets) ou sur internet (sur un server distant), au lieu de les
     * encoder en dur ici. (En fait c’est une mauvaise pratique de les encoder en dur comme on a
     * fait ici, mais on a voulu simplifier le code pour des raisons didactiques.) Vous trouverez en
     * commentaires dans cette méthode le code permettant de charger la base de données depuis un
     * fichier SQL placé dans le dossier assets/.
     * @post Les tables nécessaires à l'application sont créées et les données initiales y sont
     * enregistrées.
     */
    private void initDatabase(SQLiteDatabase db)
    {
        try
        {
            // Ouverture du fichier sql.
            BufferedReader in = new BufferedReader(new InputStreamReader(BartenderApp.getContext().getAssets().open(DATABASE_SQL_FILENAME)));

            String line;
            // Parcourt du fichier ligne par ligne.
            while ((line = in.readLine()) != null)
            {
                /**
                 * @note : Pour des raisons de facilité, on ne prend en charge ici que les fichiers
                 * contenant une instruction par ligne. Si des instructions SQL se trouvent sur deux
                 * lignes, cela produira des erreurs (car l'instruction sera coupée)
                 */
                if (!line.trim().isEmpty() && !line.trim().startsWith("--"))
                {
                    Log.d("MySQL query", line);
                    db.execSQL(line);
                }
            }
        }
            catch (IOException e)
        {
            throw new RuntimeException("Erreur de lecture du fichier " + DATABASE_SQL_FILENAME + " : " + e.getMessage(), e);
        }
            catch (SQLException e)
        {
            throw new RuntimeException("Erreur SQL lors de la création de la base de données." +
                    "Vérifiez que chaque instruction SQL est au plus sur une ligne." +
                    "L'erreur est : " + e.getMessage(), e);
        }
    }


    /**
     * Supprime toutes les tables dans la base de données.
     *
     * @param db Base de données.
     *
     * @post Les tables de la base de données passées en argument sont effacées.
     */
    private void deleteDatabase(SQLiteDatabase db)
    {
        Cursor c = db.query("sqlite_master", new String[]{"name"}, "type = 'table' AND name NOT LIKE '%sqlite_%'", null, null, null, null, null);
        c.moveToFirst();
        while (!c.isAfterLast())
        {
            db.execSQL("DROP TABLE IF EXISTS " + c.getString(0));
            c.moveToNext();
        }
    }

    //Doc SQLite : http://developer.android.com/reference/android/database/sqlite/SQLiteDatabase.html
    //Doc Cursor : http://developer.android.com/reference/android/database/Cursor.html
    /**
     * Calcul le rating 
     *
     * @param db : Base de données dans laquelle on va chercher le rating.
     *        product_id : id du produit dont on veut le rating.
     *
     * @post Le rating du produit ayant l'id product_id
     */
    private int getRating(SQLiteDatabase db, int product_id)
    {
        String request = "SELECT SUM(NOTE)/COUNT(NOTE) FROM RATING WHERE ID_PRODUIT = " + String.valueOf(product_id);
        Cursor result = db.rawQuery(request, null);
        result.moveToFirst();
        int rating = result.getInt(0);
        result.close();
        return rating;
    }

    /**
     * Calcul des factures les commandes non payées (state = 1) par table.
     *
     * @param db Base de données dans laquelle on va chercher les factures impayées.
     *
     * @post une arraylist d'objet basique, 1 ere colonne = int command.table, 2eme colonne = double addition
     */
    private Object[][] getAllRatings(SQLiteDatabase db)
    {
        String request = "SELECT COMMAND.TABL, SUM(ROUND(PRODUIT.PRIX*QUANTITY.QUANTITY,2)) FROM COMMAND, PRODUIT, QUANTITY WHERE COMMAND.STATE != 0 AND PRODUIT.ID_PROD = QUANTITY.ID_PROD AND COMMAND.ID_COMMAND = QUANTITY.ID_COMMAND";
        Cursor result = db.rawQuery(request, null);
        result.moveToFirst();

        Object to_pay[][] = new Object[result.getCount()][result.getColumnCount()];

        int trigger = 0;

        for (int i = 0; i < result.getColumnCount() ; i++)
        {
            for (int j = 0; j < result.getCount() ; j++)
            {
                if (i == 1 && trigger == 0)
                {
                    result.moveToNext();
                    trigger = 1;
                }
                if (i == 0)
                {
                    to_pay[j][i] = result.getInt(j);
                }
                else
                {
                    to_pay[j][i] = result.getDouble(j);
                }
            }
        }
        result.close();
        return to_pay;
    }

    /**
     * Description d'un produit selon la langue de l'utilisateur
     *
     * @param db Base de données dans laquelle on va chercher les factures impayées, product_id l'id du produit dont on veut la traduction, user_id l'id de l'user pour qui on veut traduire le texte
     *
     * @post le product_id traduit
     */
    private String getTrad(SQLiteDatabase db, int product_id, int user_id)
    {
        String request = "STRING.TEXTE FROM USER, PRODUIT, STRING WHERE USER.LANGUE = STRING.LANGUE AND PRODUIT.ID_PROD = " + product_id + " AND STRING.ID_STRING = PRODUIT.DESCR AND USER.ID_LOGIN = " + user_id;
        Cursor result = db.rawQuery(request, null);
        result.moveToFirst();
        String trad = result.getString(0);
        result.close();

        return trad;
    }

    /**
     * recupere la liste des lot perime
     *
     * @param db Base de données dans laquelle on va chercher les produit perime
     *
     * @post un double tableau de string, 1ere colonne = nom du produit, 2eme colonne = id du lot perime
     */
    private Object[][] getOoD(SQLiteDatabase db)
    {
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        Date date = new Date();
        dateFormat.format(date);

        String request = "SELECT STRING.TEXTE, LOT.ID_LOT FROM USER, PRODUIT, STRING, LOT WHERE PRODUIT.NOM_PRODUIT = STRING.ID_STRING AND PRODUIT.ID_PROD = LOT.IDPROD AND LOT.DATELOT < " + dateFormat.format(date);
        Cursor result = db.rawQuery(request, null);

        result.moveToFirst();

        String to_trash[][] = new String[result.getCount()][result.getColumnCount()];

        int trigger = 0;

        for (int i = 0; i < result.getColumnCount() ; i++)
        {
            for (int j = 0; j < result.getCount() ; j++)
            {
                if (i == 1 && trigger == 0)
                {
                    result.moveToNext();
                    trigger = 1;
                }
                else
                {
                    to_trash[j][i] = result.getString(j);
                }
            }
        }
        result.close();
        return to_trash;
    }
}
