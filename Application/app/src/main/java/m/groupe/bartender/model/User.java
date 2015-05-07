package m.groupe.bartender.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.SparseArray;

import java.util.ArrayList;

//import m.groupe.bartender.model.SQL;


/**
 * Représente un utilisateur et gère l'authentification de celui-ci à l'application.
 *
 * Cette classe représente un utilisateur de l'application. Elle utilise pour cela la base de
 * données par l'intermédiaire du MySQLiteHelper.
 *
 * Les méthodes statiques permettent de récupérer la liste des utilisateurs, récupérer l'utilisateur
 * actuellement connecté (s'il y en a un) et de déconnecter l'utilisateur.
 *
 * @author Damien Mercier
 * @version 1
 */
public class User {
    private static final String DB_TABLE = "USER";

    private static final String DB_COLUMN_ID = "ID_LOGIN";
    private static final String DB_COLUMN_PASSWORD = "MDP";
    private static final String DB_COLUMN_TYPE = "CATEGORIE USER";
    private static final String DB_COLUMN_NAME = "NOM_USER";
    private static final String DB_COLUMN_LANGUAGE = "LANGUE";
    private static final String DB_COLUMN_EMAIL = "EMAIL";
    private static final String DB_COLUMN_SEX = "SEX";
    private static final String DB_COLUMN_GSM = "GSM";
    private static final String DB_COLUMN_ADRESS = "ADRESSE";

    /**
     * Identifiant unique de l'utilisateur courant. Correspond à _id dans la base de données.
     */
    private final int id;
    /**
     * Login de l'utilisateur
     */
    private String login;
    /**
     * Mot de passe de l'utilisateur courant. Correspond à u_password dans la base de données.
     */
    private String password;
    /**
     * Type de l'utilisateur courant. Correspond à ??? dans la base de données.
     */
    private int type;
    /**
     * Nom (unique) de l'utilisateur courant. Correspond à u_nom dans la base de données.
     */
    private String name;
    /**
     * Langue de l'utilisateur
     */
    private String language;
    /**
     * Email de l'utilisateur
     */
    private String email;
    /**
     * Sexe de l'utilisateur
     */
    private String sex;
    /**
     * Téléphone de l'utilisateur.
     */
    private String gsm;
    /**
     * Adresse de l'utilisateur
     */
    private String adress;

    /**
     * Constructeur de l'utilisateur. Initialise une instance de l'utilisateur présent dans la base
     * de données.
     *
     * @note Ce constructeur est privé (donc utilisable uniquement depuis cette classe). Cela permet
     * d'éviter d'avoir deux instances différentes d'un même utilisateur.
     */
    private User(int uId, String uPassword, int uType, String uName, String uLanguage, String uEmail, String uSex, String uGSM, String uAdress) {

        this.id = uId;
        this.login= Integer.toString(id);
        this.password = uPassword;
        this.type = uType;
        this.name = uName;
        this.language = uLanguage;
        this.email = uEmail;
        this.sex = uSex;
        this.gsm = uGSM;
        this.adress = uAdress;
        User.userSparseArray.put(uId, this);
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getGsm() {
        return gsm;
    }

    public void setGsm(String gsm) {
        this.gsm = gsm;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    /**
     * Connecte l'utilisateur courant.
     *
     * @param passwordToTry le mot de passe entré.
     *
     * @return Vrai (true) si l'utilisateur à l'autorisation de se connecter, false sinon.
     */
    public boolean login(String passwordToTry) {
        if (this.password.equals(passwordToTry)) {
            // Si le mot de passe est correct, modification de l'utilisateur connecté.
            User.connectedUser = this;
            return true;
        }
        return false;
    }

    /**
     * Fournit une représentation textuelle de l'utilisateur courant. (Ici le nom)
     *
     * @note Cette méthode est utilisée par l'adaptateur ArrayAdapter afin d'afficher la liste des
     * utilisateurs. (Voir LoginActivity).
     */
    public String toString() {
        return getName();
    }

    /******************************************************************************
     * Partie static de la classe.
     ******************************************************************************/

    /**
     * Contient les instances déjà existantes des utilisateurs afin d'éviter de créer deux instances
     * du même utilisateur.
     */
    private static SparseArray<User> userSparseArray = new SparseArray<User>();

    /**
     * Utilisateur actuellement connecté à l'application. Correspond à null si aucun utilisateur
     * n'est connecté.
     */
    private static User connectedUser = null;

    /**
     * Fournit l'utilisateur actuellement connecté.
     */
    public static User getConnectedUser() {
        return User.connectedUser;
    }


    /**
     * Déconnecte l'utilisateur actuellement connecté à l'application.
     */
    public static void logout() {
        User.connectedUser = null;
    }

    /**
     * Fournit la liste des utilisateurs.
     */
    public static ArrayList<User> getUtilisateurs() {
        // Récupération du  SQLiteHelper et de la base de données.
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();

        // Colonnes à récupérer
        String[] colonnes = {DB_COLUMN_ID, DB_COLUMN_PASSWORD, DB_COLUMN_TYPE, DB_COLUMN_NAME, DB_COLUMN_LANGUAGE, DB_COLUMN_EMAIL, DB_COLUMN_SEX, DB_COLUMN_GSM, DB_COLUMN_ADRESS};

        // Requête de selection (SELECT)
        Cursor cursor = db.query(DB_TABLE, colonnes, null, null, null, null, null);

        // Placement du curseur sur la première ligne.
        cursor.moveToFirst();

        // Initialisation la liste des utilisateurs.
        ArrayList<User> users = new ArrayList<User>();

        // Tant qu'il y a des lignes.
        while (!cursor.isAfterLast()) {
            // Récupération des informations de l'utilisateur pour chaque ligne.
            int uId = cursor.getInt(0);
            String uPassword = cursor.getString(1);
            int uType = cursor.getInt(2);
            String uName = cursor.getString(3);
            String uLanguage = cursor.getString(4);
            String uEmail = cursor.getString(5);
            String uSex = cursor.getString(6);
            String uGSM = cursor.getString(7);
            String uAdress = cursor.getString(8);


            // Vérification pour savoir s'il y a déjà une instance de cet utilisateur.
            User user = User.userSparseArray.get(uId);
            if (user == null) {
                // Si pas encore d'instance, création d'une nouvelle instance.
                user = new User(uId, uPassword, uType, uName, uLanguage, uEmail, uSex, uGSM, uAdress);
            }

            // Ajout de l'utilisateur à la liste.
            users.add(user);

            // Passe à la ligne suivante.
            cursor.moveToNext();
        }

        // Fermeture du curseur et de la base de données.
        cursor.close();
        db.close();

        return users;
    }

}

