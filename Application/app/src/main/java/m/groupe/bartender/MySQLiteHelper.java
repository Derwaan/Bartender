package m.groupe.bartender;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import m.groupe.bartender.BartenderApp;

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
    private static final String DATABASE_SQL_FILENAME = "bartender.sql";
    /**
     * Nom du fichier de la base de données.
     */
    private static final String DATABASE_NAME = "bartender.sqlite";

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
  /*  private int getRating(SQLiteDatabase db, int product_id)
    {
        String request = "SELECT SUM(NOTE)/COUNT(NOTE) FROM RATING WHERE ID_PRODUIT = " + String.valueOf(id);
        Cursor result = db.rawQuery(request, null);
        int rating = result.getInt(0);
        result.close();
        return rating;
    }*/

    /**
     * Calcul le rating de tout les produit et retourne une liste 
     *
     * @param db : Base de données dans laquelle on va chercher le rating.
     *
     * @post un tableau contenant les ratings de tout les produit int[0] = rating du produit 1 (donc penser a faire +1 si on veut recup le nom du produit correspondant)
     */
   /* private int[] getAllRatings(SQLiteDatabase db)
    {
        String request = "SELECT SUM(RATING.NOTE)/COUNT(RATING.NOTE) FROM RATING, PRODUIT, STRING WHERE PRODUIT.ID_PROD = RATING.ID_PRODUIT GROUP BY PRODUIT.ID_PROD";
        Cursor result = db.rawQuery(request, null);

        int rating[result.getColumnCount()];

        for (int i = 0; i < result.getColumnCount() ; i++)
        {
            rating = result.getInt(i);
        }

        result.close();
        return rating;
    }*/
}
