package m.groupe.bartender.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import m.groupe.bartender.BartenderApp;
import m.groupe.bartender.R;
//import m.groupe.bartender.activity.MenuActivity;
import m.groupe.bartender.activity.OrderActivity;
import m.groupe.bartender.model.User;
import m.groupe.bartender.R;


/**
 * Created by elie on 8/05/15.
 */
public class OrderActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
    }
}
