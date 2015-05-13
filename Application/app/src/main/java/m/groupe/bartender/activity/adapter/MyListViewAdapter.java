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
 * G�re l'affichage personnalis� de notre liste.
 *
 * Cette classe permet de cr�er un Adapter personnalis� pour notre liste d'�l�ments de collection.
 * De cette mani�re il nous est possible d'utiliser un layout particulier (ici
 * collected_item_row.xml) pour chaque ligne reprenant le nom de l'�l�ment et sa note (rating).
 *
 * @author Damien Mercier
 * @version 1
 * @see <a href="http://d.android.com/reference/android/widget/Adapter.html">Adapter</a>
 * @see <a href="http://d.android.com/reference/android/widget/BaseAdapter.html">BaseAdapter</a>
 */
public class MyListViewAdapter extends BaseAdapter {
    /**
     * Permet d'instancier un fichier xml de layout dans une vue.
     */
    private final LayoutInflater mInflater;

    /**
     * Liste des �l�ments de collection � mettre dans la liste.
     */
    private ArrayList<Product> products;

    /**
     * Constructeur.
     *
     * @param context        Contexte de l'application.
     * @param products Liste des �l�ments de collection � placer dans la liste.
     */
    public MyListViewAdapter(Context context, ArrayList<Product> products) {
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
     * Cette m�thode est appel�e par Android pour construire la vue de la liste (lors de la
     * construction de listview).
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Si la vue n'a pas encore �t� cr�� (typiquement lors du premi�re affichage de la liste).
        // Android recycle en effet les layout d�j� charg�s des �l�ments de la liste (par exemple
        // lors du changement de l'ordre dans la liste.)

        if (convertView == null) {
            // Cr�ation d'un nouvelle vue avec le layout correspondant au fichier xml
            convertView = mInflater.inflate(R.layout.activity_product, parent, false);
        }

        // R�cup�ration des deux �l�ments de notre vue dans le but d'y placer les donn�es.
        TextView nameTextView = (TextView) convertView.findViewById(R.id.name_text);
        RatingBar ratingBar = (RatingBar) convertView.findViewById(R.id.rating_bar);

        // R�cup�ration et placement des donn�es.
        Product product = products.get(position);
        nameTextView.setText(product.getName());
        //ratingBar.setRating(product.getRating());

        return convertView;
    }

    /**
     * Change la liste des �l�ments de collection affich�e.
     *
     * Permet de changer compl�tement la liste des �l�ments affich�s dans la liste.
     *
     * @param newProducts La nouvelle liste des �l�ments de collection � afficher.
     *
     * @post Les �l�ments de la liste ont �t� remplac�s par les �l�ments pass�s en argument.
     */
    public void setProducts(ArrayList<Product> newProducts) {
        this.products = newProducts;
        notifyDataSetChanged();
    }
}
