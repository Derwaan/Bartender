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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // R�cup�ration de l'id de l'�l�ment de collection ou si rien n'est trouv�, -1 est la valeur
        // par d�faut.
        // Lire http://d.android.com/training/basics/firstapp/starting-activity.html#ReceiveIntent
        int id = getIntent().getIntExtra("p_id", -1);

        if (id == -1) {
            // Ne devrait jamais arriver.
            throw new RuntimeException("Aucun id d'element n'a ete specifie.");
        }

        // R�cup�ration de l'�l�ment de collection.
        currentProduct = Product.get(id);

        // Compl�tition des diff�rents champs avec les donn�es de l'�l�ment de collection.
        TextView name = (TextView) findViewById(R.id.show_details_name);
        name.setText(currentProduct.getName());

        TextView description = (TextView) findViewById(R.id.show_details_description);
        description.setText(currentProduct.getDescription());

        RatingBar rating = (RatingBar) findViewById(R.id.show_details_rating);
        rating.setRating(currentProduct.getRating());

        // Indique que cette classe recevra les modifications de note (rating) gr�ce � la m�thode
        // onRatingChanged.
        rating.setOnRatingBarChangeListener(this);

        // R�cup�ration et affichage de l'image.
        // S'il n'y a pas d'image, l'emplacement pr�vu doit �tre masqu�.
        Bitmap bitmap = currentProduct.getImage();
        if (bitmap != null) {
            ImageView picture = (ImageView) findViewById(R.id.show_details_picture);
            picture.setImageBitmap(bitmap);
        } else {
            View pictureLL = findViewById(R.id.show_details_picture_ll);
            // La visibilit� GONE implique que l'�l�ment ne prend aucune place (contrairement � INVISIBLE).
            pictureLL.setVisibility(View.GONE);
        }

    }

    /**
     * Enregistre les changements de la note (rating).
     *
     * @param ratingBar La RatingBar concern�e (ici il n'y en a qu'une dont l'id est
     *                  show_details_rating).
     * @param rating    La valeur de la nouvelle note (rating).
     * @param fromUser  Indique si le changement de note (rating) est effectu� par l'utilisateur ou
     *                  par le programme (par exemple par appel de la m�thode
     *                  ratingBar.setRating(x)).
     */
    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        if (fromUser) {
            if (!currentProduct.setRating(rating)) {
                // En cas d'erreur, il faut notifier l'utilisateur et afficher la valeur qui est
                // r�ellement enregistr�e.
                BartenderApp.notifyShort(R.string.show_details_rating_change_error);
                ratingBar.setRating(currentProduct.getRating());
            }
        }
    }
}
