package m.groupe.bartender;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import m.groupe.bartender.BartenderApp;
import m.groupe.bartender.R;
import m.groupe.bartender.model.User;

/**
 * Gère l'affichage du menu principal de l'application.
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Affichage du message de bienvenue.
        TextView welcomeTxt = (TextView) findViewById(R.id.welcomeTxt);
        welcomeTxt.setText(getString(R.string.main_welcome) + " " + User.getConnectedUser().getName());
        //Verifie l'identité de l'utilisateur et affiche ce qui lui est permis de voir
        if(User.getConnectedUser().getType==1) {
            View b = findViewById(R.id.stock_button);
            b.setVisibility(View.GONE);
            View c = findViewById(R.id.addWaiter_btn);
            b.setVisibility(View.GONE);
            View d = findViewById(R.id.stats_btn);
            b.setVisibility(View.GONE);
        }

    }


    /**
     * @note Les méthodes showMenu, search, order, account, showStock, add, showStat et logout sont appelées lors d'un clic sur les boutons
     * correspondant grâce à l'attribut onClick présent dans les fichiers de layout.
     *
     * Lire http://developer.android.com/reference/android/R.attr.html#onClick
     */

    /**
     * Lance l'activité d'affichage du menu.
     */
    public void showMenu(View v) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    /**
     * Lance l'activité d'affichage des commandes.
     */
    public void order(View v) {
        Intent intent = new Intent(this, OrderActivity.class);
        startActivity(intent);
    }

    /**
     * Déconnecte l'utilisateur actuellement connecté et retourne vers l'écran de connexion.
     */
    public void logout(View v) {
        User.logout();
        finish();
    }

    /**
     * Lance l'activité de gestion du compte.
     */
    public void account(View v) {
        Intent intent = new Intent(this, AccountActivity.class);
        startActivity(intent);
    }

    /**
     * Lance l'activité de gestion des stocks.
     */
    public void showStock(View v) {
        Intent intent = new Intent(this, StockActivity.class);
        startActivity(intent);
    }

    /**
     * Lance l'activité d'ajout de serveur.
     */
    public void add(View v) {
        Intent intent = new Intent(this, AddWaiterActivity.class);
        startActivity(intent);
    }

    /**
     * Lance l'activité d'affichage des statistiques.
     */
    public void showStat(View v) {
        Intent intent = new Intent(this, ShowStatActivity.class);
        startActivity(intent);
    }



    /**
     * Désactive le bouton de retour. Désactive le retour à l'activité précédente (donc l'écran de
     * connexion dans ce cas-ci) et affiche un message indiquant qu'il faut se déconnecter.
     */
    @Override
    public void onBackPressed() {
        // On désactive le retour (car on se trouve au menu principal) en ne faisant
        // rien dans cette méthode si ce n'est afficher un message à l'utilisateur.
        CollectorApp.notifyShort(R.string.main_back_button_disable);
    }
}