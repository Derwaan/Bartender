package m.groupe.bartender.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import m.groupe.bartender.BartenderApp;
import m.groupe.bartender.R;
import m.groupe.bartender.model.Product;

public class DetailActivity extends Activity implements RatingBar.OnRatingBarChangeListener {

    private Product currentProduct;
    private int amount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Recuperation de l'id de l'element de collection ou si rien n'est trouve, -1 est la valeur
        // par defaut.
        // Lire http://d.android.com/training/basics/firstapp/starting-activity.html#ReceiveIntent
        int id = getIntent().getIntExtra("p_id", -1);

        if (id == -1) {
            // Ne devrait jamais arriver.
            throw new RuntimeException("Aucun id d'element n'a ete specifie.");
        }

        // Recuperation de l'element de collection.
        currentProduct = Product.get(id);

        // Completition des differents champs avec les donnees de l'element de collection.
        TextView name = (TextView) findViewById(R.id.show_details_name);
        name.setText(currentProduct.getName());

        TextView description = (TextView) findViewById(R.id.show_details_description);
        description.setText(currentProduct.getDescription());

        RatingBar rating = (RatingBar) findViewById(R.id.show_details_rating);
        rating.setRating(currentProduct.getRating());

        // Indique que cette classe recevra les modifications de note (rating) grece e la methode
        // onRatingChanged.
        rating.setOnRatingBarChangeListener(this);

        // Recuperation et affichage de l'image.
        // S'il n'y a pas d'image, l'emplacement prevu doit etre masque.
        Bitmap bitmap = currentProduct.getImage();
        if (bitmap != null) {
            ImageView picture = (ImageView) findViewById(R.id.show_details_picture);
            picture.setImageBitmap(bitmap);
        } else {
            View pictureLL = findViewById(R.id.show_details_picture_ll);
            // La visibilite GONE implique que l'element ne prend aucune place (contrairement e INVISIBLE).
            pictureLL.setVisibility(View.GONE);
        }

    }

    /**
     * Enregistre les changements de la note (rating).
     *
     * @param ratingBar La RatingBar concernee (ici il n'y en a qu'une dont l'id est
     *                  show_details_rating).
     * @param rating    La valeur de la nouvelle note (rating).
     * @param fromUser  Indique si le changement de note (rating) est effectue par l'utilisateur ou
     *                  par le programme (par exemple par appel de la methode
     *                  ratingBar.setRating(x)).
     */
    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        if (fromUser) {
            if (!currentProduct.setRating(rating)) {
                // En cas d'erreur, il faut notifier l'utilisateur et afficher la valeur qui est
                // reellement enregistree.
                BartenderApp.notifyShort(R.string.show_details_rating_change_error);
                ratingBar.setRating(currentProduct.getRating());
            }
        }
    }

    public void increment(View v) {
        amount++;
    }

    public void decrement(View v) {
        amount = amount > 0 ? --amount : 0;
    }
}
