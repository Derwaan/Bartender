package m.groupe.bartender.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import m.groupe.bartender.BartenderApp;
import m.groupe.bartender.MySQLiteHelper;
import m.groupe.bartender.R;

/**
 * Created by Tran Trong-Vu on 07-05-15.
 */
public class RegisterActivity extends Activity{
    private Spinner sex_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        sex_spinner = (Spinner) findViewById(R.id.sex_spin);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sex, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        sex_spinner.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void createUser(View v){
        SQLiteDatabase db = MySQLiteHelper.get().getWritableDatabase();

        String login = findViewById(R.id.login_field).toString();
        String password = findViewById(R.id.password_field).toString();
        int type = 1;
        String name = findViewById(R.id.name_field).toString();
        String language = "fr";
        String email = findViewById(R.id.mail_field).toString();
        String sex = ""+sex_spinner.getSelectedItem().toString().charAt(0);
        String phone = findViewById(R.id.phone_field).toString();
        String address = findViewById(R.id.address_field).toString();

        Cursor last_id = db.rawQuery("SELECT MAX(USER.ID_LOGIN) FROM USER", null);
        if (last_id != null) {
            last_id.moveToFirst();
        }
        int id = last_id.getInt(0) +  1;

        String query = "INSERT INTO USER VALUES " + id + "," + login +","+password+"," +type+" , "+name+","+language+","+ email+","+","+sex+","+phone+","+ address +");";
        db.execSQL(query,null);

//        String[] data_user = {String.valueOf(id), login, password, String.valueOf(type), name,language, email, sex, phone, address};
//        db.execSQL( (?,?,?,?,?,?,?,?,?,?)", data_user);

        BartenderApp.notifyShort(R.string.registered);
        db.close();

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
