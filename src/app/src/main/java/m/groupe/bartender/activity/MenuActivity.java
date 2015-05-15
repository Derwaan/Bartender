package m.groupe.bartender.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import m.groupe.bartender.BartenderApp;
import m.groupe.bartender.R;
import m.groupe.bartender.activity.adapter.ProductAdapter;
import m.groupe.bartender.model.Product;

/**
 * Gère l'affichage sous forme de liste du menu. Si
 * une requête de recherche est passée dans l'Intent, la recherche est effectuée et la liste des
 * éléments affichés sera la liste des résultats.
 */
public class MenuActivity extends Activity implements OnItemClickListener{


    private ArrayList<Product> products;
    private ProductAdapter productAdapter;

    /**
     * Lance l'activité de recherche.
     */
    public void search(View v) {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);

        // Chargement des éléments à afficher dans la variable de classe products
        loadProducts();


        ListView myListView = (ListView) findViewById(R.id.products_list);

        // Création de l'adapter pour faire la liaison entre les données (products) et
        // l'affichage de chaque ligne de la liste.
        productAdapter = new ProductAdapter(this, products);
        myListView.setAdapter(productAdapter);

        // Indique que le clic d'un élément de la liste doit appeler la méthode onItemClick d
        // cette classe (this).
        myListView.setOnItemClickListener(this);


        // Mise à jour des icones de tri afin de correspondre au tri actuel. (les options de tri
        // sont gardées en mémoire dans la classe CollectedItem tout au long de l'exécution de
        // l'application)
        updateDrawableOrder();
    }

    /**
     * Charge la liste des éléments de collection dans la variables de classe products.
     *
     * Charge la liste des éléments de la collection de l'utilisateur connecté et si une requête de
     * recherche est passée lors du lancement de l'activité, effectue la recherche et charge la
     * liste des résultats.
     */
    private void loadProducts() {

        // Récupération de la requête de recherche.
        // Si aucune requête n'a été passée lors de la création de l'activité, searchQuery sera null.
        String searchQuery = getIntent().getStringExtra("searchQuery");

        if (searchQuery == null) {
            products = Product.getProducts();
        } else {
            products = Product.searchProducts(searchQuery);
        }

        // S'il n'y a aucun éléments dans la liste, il faut afficher un message. Ce message est différent
        // s'il y avait une requête de recherche (message du type "Aucun résultat trouvé") ou si
        // l'utilisateur vient directement du menu principal et veut tout afficher (message du type
        // "Aucun élément n'est présent dans votre collection).
        if (products.isEmpty()) {
            if (searchQuery == null) {
               BartenderApp.notifyShort(R.string.show_list_error_no_item);
            } else {
                BartenderApp.notifyShort(R.string.show_list_no_result);
            }
            // Cloture de l'activité d'affichage de la liste (car liste vide). Retour à l'écran
            // précédent.
            finish();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        // La liste des éléments est ici rechargées car en cas de modification d'un élément, l'ordre
        // a peut-être changé.

        loadProducts();

        productAdapter.setProducts(products);
    }

    /**
     * Lance l'activité de vue des détails d'un élément de collection lors du clic sur un élément de
     * la liste.
     *
     * @param position Position de l'élément dans la liste.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, DetailActivity.class);
        // L'id de l'élément de collection est passé en argument afin que la vue de détails puisse
        // récupérer celui-ci.
        intent.putExtra("p_id", products.get(position).getId());
        startActivity(intent);
    }

    /**
     * Gère le changement du tri sur la liste.
     *
     * Cette méthode est appelée grâce à l'arttribut onClick présent dans le fichier xml de layout.
     *
     * @param view Vue sur laquelle l'utilisateur a cliqué.
     */
    public void change_order(View view) {
        // Détermine si le clic a été fait sur la colonne de nom (name) ou de note (rating).
        switch (view.getId()) {
            case R.id.name_btn:
                if (Product.order_by.equals(Product.DB_COL_NAME)) {
                    // Si le tri est déjà effectué sur les noms, il faut juste inverser l'ordre.
                    Product.reverseOrder();
                } else {
                    // Sinon il faut indiquer que le tri se fait sur les noms par ordre alphabétique (croissant)
                    Product.order_by = Product.DB_COL_NAME;
                    Product.order = "ASC";
                }
                break;
            case R.id.price_btn:
                if (Product.order_by.equals(Product.DB_COL_PRICE)) {
                    // Si le tri est déjà effectué sur les prix, il faut juste inverser l'ordre
                    Product.reverseOrder();
                } else {
                    // Sinon il faut indiquer que le tri se fait sur les prix par ordre décroissant
                    // (le plus cher d'abord)
                    Product.order_by = Product.DB_COL_PRICE;
                    Product.order = "DESC";
                }
                break;
//            case R.id.rating_btn:
//                if (Product.order_by.equals(Product.DB_COL_RATING)) {
//                    // Si le tri est déjà effectué sur les cotes, il faut juste inverser l'ordre
//                    Product.reverseOrder();
//                } else {
//                    // Sinon il faut indiquer que le tri se fait sur les notes par ordre décroissant
//                    // (le meilleure note d'abord)
//                    Product.order_by = Product.DB_COL_RATING;
//                    Product.order = "DESC";
//                }
//                break;
//            case R.id.cat_btn:
//                if (Product.order_by.equals(Product.DB_COL_CAT)) {
//                    // Si le tri est déjà effectué sur les catégories, il faut juste inverser l'ordre
//                    Product.reverseOrder();
//                } else {
//                    // Sinon il faut indiquer que le tri se fait sur les categories par ordre alphabetique
//                    Product.order_by = Product.DB_COL_CAT;
//                    Product.order = "ASC";
//                }
//                break;
        }

        // Mise à jour des icônes de tri.
        updateDrawableOrder();

        // Re-chargement de la liste des éléments de collection pour prendre en compte le nouveau tri.
        loadProducts();

        // Mise à jour de la liste des éléments dans l'adapter pour que l'affichage soit modifié.
        productAdapter.setProducts(products);
    }


    /**
     * Met à jour les icônes de tri afin qu'elles correspondent au tri actuellement en cours.
     *
     * @pre Les valeurs de CollectedItem.order et de CollectedItem.order_by sont correctement
     * définies.
     * @post Les icônes de tri sont mises à jour et correspondent aux valeurs de CollectedItem.order
     * et de CollectedItem.order_by.
     */
    private void updateDrawableOrder() {
        TextView priceValue = (TextView) findViewById(R.id.price_btn);
        TextView nameTitle = (TextView) findViewById(R.id.name_btn);
       // TextView catTitle = (TextView) findViewById(R.id.cat_btn);
      //  TextView ratingTitle = (TextView) findViewById(R.id.rating_btn);

        /**
         * Remise à zéro des images de tri.
         * @note : Attention, le tri par défaut pour les noms est croissant
         * (up) et celui pour les notes est décroissant (down). Il faut que cela correspondent dans
         * le comportement de la méthode change_order.
         */
        // Read your drawable from somewhere
        Drawable dr = getResources().getDrawable(R.drawable.ic_up_inactive);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
// Scale it to 50 x 50
        Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 50, 50, true));
        nameTitle.setCompoundDrawablesWithIntrinsicBounds(d, null, null, null);
       // ratingTitle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_down_inactive, 0, 0, 0);


        // Détermination de la colonne sur laquelle le tri est effectué.
        TextView orderTitle;
        boolean orderByRating = Product.order_by.equals(Product.DB_COL_RATING);
        //if (orderByRating) {
        //    orderTitle = ratingTitle;
        //}
        //else {
            orderTitle = nameTitle;
        //}
        Drawable dr2 = getResources().getDrawable(R.drawable.ic_down_active);
        Bitmap bitmap2 = ((BitmapDrawable) dr2).getBitmap();
// Scale it to 50 x 50
        Drawable d2 = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap2, 50, 50, true));
        Drawable dr3 = getResources().getDrawable(R.drawable.ic_up_active);
        Bitmap bitmap3 = ((BitmapDrawable) dr3).getBitmap();
// Scale it to 50 x 50
        Drawable d3 = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap3, 50, 50, true));
        // Détermination de l'ordre de tri.
        boolean orderDesc = Product.order.equals("DESC");

        // Placement de l'icône en fonction de l'ordre de tri.
        if (orderDesc) {

            orderTitle.setCompoundDrawablesWithIntrinsicBounds(d2, null, null, null);
        } else {
            orderTitle.setCompoundDrawablesWithIntrinsicBounds(d3, null, null, null);
        }
    }
}
