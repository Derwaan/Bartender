package m.groupe.bartender.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import m.groupe.bartender.BartenderApp;
import m.groupe.bartender.MySQLiteHelper;
import m.groupe.bartender.R;
import m.groupe.bartender.model.User;

/**
 * Created by Tran Trong-Vu on 07-05-15.
 */
public class RegisterActivity extends Activity{
    private Spinner sex_spinner;
    private Spinner lan_spinner;

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

        lan_spinner = (Spinner) findViewById(R.id.lang_spin);
        ArrayAdapter<CharSequence> adapter_l = ArrayAdapter.createFromResource(this, R.array.lan, android.R.layout.simple_spinner_item);
        adapter_l.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lan_spinner.setAdapter(adapter_l);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void createUser(View v) {
        SQLiteDatabase db = MySQLiteHelper.get().getReadableDatabase();

        Cursor last_id = db.rawQuery("SELECT MAX(USER.ID_LOGIN) FROM USER", null);
        if (last_id != null) {
            last_id.moveToFirst();
        }

        //Get the values from register fields
        int id = last_id.getInt(0) + 1;

        last_id.close();
        db.close();

        String login = ((EditText) findViewById(R.id.login_field)).getText().toString();
        String password = ((EditText)findViewById(R.id.password_field)).getText().toString();
        String confirm = ((EditText) findViewById(R.id.confirm_field)).getText().toString();
        int type = 1;
        String name = ((EditText)findViewById(R.id.name_field)).getText().toString();;
        String language = "" + lan_spinner.getSelectedItem().toString().charAt(0);
        String email = ((EditText)findViewById(R.id.mail_field)).getText().toString();;
        String sex = "" + sex_spinner.getSelectedItem().toString().charAt(0);
        String phone = ((EditText)findViewById(R.id.phone_field)).getText().toString();
        String address = ((EditText)findViewById(R.id.address_field)).getText().toString();

        if(password.equals(confirm)) {
            User user = new User(id, login, password, type, name, language, email, sex, phone, address);
            boolean res = User.add(user);
            if (!res) {
                BartenderApp.notifyShort(R.string.unregistered);
            }
            else{
                BartenderApp.notifyShort(R.string.registered);
            }

            if(res) {
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            }
        } else {
            BartenderApp.notifyShort(R.string.confirm);
        }
    }
}
