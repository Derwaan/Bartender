package m.groupe.bartender.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import m.groupe.bartender.R;
import m.groupe.bartender.model.Quantity;

public class OrderAdapter extends BaseAdapter {
    /**
     * Permet d'instancier un fichier xml de layout dans une vue.
     */
    private final LayoutInflater mInflater;

    /**
     * Liste des elements de collection e mettre dans la liste.
     */
    private ArrayList<Quantity> quantities;

    /**
     * Constructeur.
     *
     * @param context        Contexte de l'application.
     * @param quantities Liste des elements de collection e placer dans la liste.
     */
    public OrderAdapter(Context context, ArrayList<Quantity> quantities) {
        mInflater = LayoutInflater.from(context);
        this.quantities = quantities;
    }

    @Override
    public int getCount() {
        return quantities.size();
    }

    @Override
    public Object getItem(int position) {
        return quantities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return quantities.get(position).getId_product();
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
        TextView quantity = (TextView) convertView.findViewById(R.id.price_row);

        // Recuperation et placement des donnees.
        Quantity quantity1 = quantities.get(position);
        nameTextView.setText(quantity1.getName(quantity1));
        quantity.setText(""+quantity1.getQuantity());

        return convertView;
    }

    /**
     * Change la liste des elements de collection affichee.
     *
     * Permet de changer completement la liste des elements affiches dans la liste.
     *
     * @param newCollectedItems La nouvelle liste des elements de collection e afficher.
     *
     * @post Les elements de la liste ont ete remplaces par les elements passes en argument.
     */
    public void setQuantities(ArrayList<Quantity> newCollectedItems) {
        this.quantities = newCollectedItems;
        notifyDataSetChanged();
    }
}
