package m.groupe.bartender.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import m.groupe.bartender.R;
import m.groupe.bartender.model.Product;

/**
 * Gere l'affichage personnalise de notre liste.
 *
 * Cette classe permet de creer un Adapter personnalise pour notre liste d'elements de collection.
 * De cette maniere il nous est possible d'utiliser un layout particulier (ici
 * collected_item_row.xml) pour chaque ligne reprenant le nom de l'element et sa note (rating).
 *
 * @author Damien Mercier
 * @version 1
 * @see <a href="http://d.android.com/reference/android/widget/Adapter.html">Adapter</a>
 * @see <a href="http://d.android.com/reference/android/widget/BaseAdapter.html">BaseAdapter</a>
 */
public class ProductAdapter extends BaseAdapter {
    /**
     * Permet d'instancier un fichier xml de layout dans une vue.
     */
    private final LayoutInflater mInflater;

    /**
     * Liste des elements de collection e mettre dans la liste.
     */
    private ArrayList<Product> products;

    /**
     * Constructeur.
     *
     * @param context        Contexte de l'application.
     * @param products Liste des elements de collection e placer dans la liste.
     */
    public ProductAdapter(Context context, ArrayList<Product> products) {
        mInflater = LayoutInflater.from(context);
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return products.get(position).getId();
    }

    /**
     * Remplit chaque ligne de la liste avec un layout particulier.
     *
     * Cette methode est appelee par Android pour construire la vue de la liste (lors de la
     * construction de listview).
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Si la vue n'a pas encore ete cree (typiquement lors du premiere affichage de la liste).
        // Android recycle en effet les layout deje charges des elements de la liste (par exemple
        // lors du changement de l'ordre dans la liste.)

        if (convertView == null) {
            // Creation d'un nouvelle vue avec le layout correspondant au fichier xml
            convertView = mInflater.inflate(R.layout.activity_product, parent, false);
        }

        // Recuperation des deux elements de notre vue dans le but d'y placer les donnees.
        TextView nameTextView = (TextView) convertView.findViewById(R.id.name_row);
        TextView priceTextView = (TextView) convertView.findViewById(R.id.price_row);

        // Recuperation et placement des donnees.
        Product product = products.get(position);
        nameTextView.setText(product.getName());
        priceTextView.setText(product.getPrice()+"€");
        //ratingBar.setRating(product.getRating());

        return convertView;
    }

    /**
     * Change la liste des elements de collection affichee.
     *
     * Permet de changer completement la liste des elements affiches dans la liste.
     *
     * @param newProducts La nouvelle liste des elements de collection e afficher.
     *
     * @post Les elements de la liste ont ete remplaces par les elements passes en argument.
     */
    public void setProducts(ArrayList<Product> newProducts) {
        this.products = newProducts;
        notifyDataSetChanged();
    }
}
