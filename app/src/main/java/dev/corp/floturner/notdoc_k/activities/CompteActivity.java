package dev.corp.floturner.notdoc_k.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import dev.corp.floturner.notdoc_k.R;
import dev.corp.floturner.notdoc_k.utils.Utils;

public class CompteActivity extends AppCompatActivity {
    TextView lastname, firstname, username, usermail, usercell, accounttype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compte);

        lastname = findViewById(R.id.lastname);
        firstname = findViewById(R.id.firstname);
        username = findViewById(R.id.username);
        usermail = findViewById(R.id.usermail);
        usercell = findViewById(R.id.usercell);
        accounttype = findViewById(R.id.accounttype);

        SharedPreferences userPreferences = getSharedPreferences(getString(R.string.connected_user_infos_file), MODE_PRIVATE);
        lastname.setText(Utils.capitalizeFirstLetter(userPreferences.getString("lastname", null)));
        firstname.setText(Utils.capitalizeFirstLetter(userPreferences.getString("firstname", null)));
        username.setText(userPreferences.getString("username", null));
        usermail.setText(userPreferences.getString("usermail", null));
        usercell.setText(userPreferences.getString("usercell", null));
        accounttype.setText(Utils.capitalizeFirstLetter(userPreferences.getString("accounttype", null)));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }
}
