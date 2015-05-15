package m.groupe.bartender.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import m.groupe.bartender.R;
import m.groupe.bartender.activity.adapter.OrderAdapter;
import m.groupe.bartender.model.Order;
import m.groupe.bartender.model.Quantity;

public class OrderActivity extends Activity{
    private ArrayList<Quantity> qty;
    private OrderAdapter orderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Order.getCurrentOrder() == null){
            setContentView(R.layout.activity_no_order);
        }
        else{
            setContentView(R.layout.activity_detailorder);

            // Chargement des éléments à afficher dans la variable de classe order
            qty = Order.getOrder();
            ListView myListView = (ListView) findViewById(R.id.order_list);

            // Création de l'adapter pour faire la liaison entre les données (products) et
            // l'affichage de chaque ligne de la liste.
            orderAdapter = new OrderAdapter(this, qty);
            myListView.setAdapter(orderAdapter);
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
    }
}
