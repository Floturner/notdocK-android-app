package dev.corp.floturner.notdoc_k.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

import com.google.gson.JsonObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.corp.floturner.notdoc_k.R;
import dev.corp.floturner.notdoc_k.models.Token;
import dev.corp.floturner.notdoc_k.models.UserAccount;
import dev.corp.floturner.notdoc_k.utils.APIService;
import dev.corp.floturner.notdoc_k.utils.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {
    Token token;
    APIService apiService;
    Retrofit retrofit;
    int st = 2;

    @BindView(R.id.login_view)
    ScrollView scrollView;
    @BindView(R.id.input_user_id)
    EditText _userIdText;
    @BindView(R.id.input_user_password)
    EditText _passwordText;
    @BindView(R.id.btn_login)
    Button _loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences loginPreferences = getSharedPreferences(getString(R.string.login_status_file), MODE_PRIVATE);
        String status = loginPreferences.getString("status", getString(R.string.logged_out));
        if (status.equals(getString(R.string.logged_in))) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        ButterKnife.bind(this);

        retrofit = ApiClient.getClient();
        apiService = retrofit.create(APIService.class);

        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public void login() {
        if (!validate()) {
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this, R.style.AppTheme_Login_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getString(R.string.login_loading));
        progressDialog.show();

        JsonObject object = new JsonObject();
        object.addProperty("login", _userIdText.getText().toString());
        object.addProperty("password", _passwordText.getText().toString());

        Call<Token> userCall = apiService.userLogin(object);
        userCall.enqueue(userCallback);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                _loginButton.setEnabled(true);
                progressDialog.dismiss();
                if (st == 0) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
                else if (st == 1) {
                    Snackbar.make(scrollView, R.string.login_failed, Snackbar.LENGTH_LONG).show();
                }
                else if (st == 2){
                    Snackbar.make(scrollView, R.string.network_disconnected, Snackbar.LENGTH_LONG).show();
                }
            }
        }, 3000);
    }

    Callback<Token> userCallback = new Callback<Token>() {
        @Override
        public void onResponse(@NonNull Call<Token> call, @NonNull Response<Token> response) {
            if (response.isSuccessful()) {
                token = response.body();
                if (token != null) {
                    st = 0;

                    UserAccount connectdUserAccount = token.getUserAccount();

                    // Saving connected user infos
                    SharedPreferences userPrefers = getSharedPreferences(getString(R.string.connected_user_infos_file), MODE_PRIVATE);
                    SharedPreferences.Editor editor = userPrefers.edit();
                    editor.putString("lastname", connectdUserAccount.getPartenaire().getNomPartenaire());
                    editor.putString("firstname", connectdUserAccount.getPartenaire().getPrenomPartenaire());
                    editor.putString("username", connectdUserAccount.getLogin());
                    editor.putString("usermail", connectdUserAccount.getPartenaire().getEmailPartenaire());
                    editor.putString("usercell", connectdUserAccount.getPartenaire().getTelPartenaire());
                    editor.putString("accounttype", connectdUserAccount.getUserType().getLibelleType());
                    editor.apply();

                    // Saving login status
                    SharedPreferences loginPrefs = getSharedPreferences(getString(R.string.login_status_file), MODE_PRIVATE);
                    loginPrefs.edit().putString("status", getString(R.string.logged_in)).apply();
                }
            }
            else {
                st = 1;
            }
        }

        @Override
        public void onFailure(@NonNull Call<Token> call, @NonNull Throwable t) {
            st = 2;
        }
    };

    public boolean validate() {
        boolean valid = true;

        String userId = _userIdText.getText().toString();
        String password = _passwordText.getText().toString();

        if (userId.isEmpty()) {
            _userIdText.setError(getString(R.string.user_id_error));
            valid = false;
        } else {
            _userIdText.setError(null);
        }

        if (password.isEmpty()) {
            _passwordText.setError(getString(R.string.user_password_error));
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

}