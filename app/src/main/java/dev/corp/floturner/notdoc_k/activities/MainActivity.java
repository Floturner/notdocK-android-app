package dev.corp.floturner.notdoc_k.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import dev.corp.floturner.notdoc_k.R;
import dev.corp.floturner.notdoc_k.fragments.DashboardFragment;
import dev.corp.floturner.notdoc_k.fragments.DossierFragment;
import dev.corp.floturner.notdoc_k.utils.Utils;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    FragmentTransaction fragmentTransaction;
    DashboardFragment dashboardFragment;
    DossierFragment dossierFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = headerView.findViewById(R.id.nav_username);
        TextView navUseremail = headerView.findViewById(R.id.nav_useremail);

        SharedPreferences userPreferences = getSharedPreferences(getString(R.string.connected_user_infos_file), MODE_PRIVATE);
        navUsername.setText(Utils.capitalizeFirstLetter(userPreferences.getString("lastname", null).concat(" ")
                .concat(userPreferences.getString("firstname", null))));
        navUseremail.setText(userPreferences.getString("usermail", null));

        dashboardFragment = new DashboardFragment();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, dashboardFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            moveTaskToBack(true);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_dashboard:
                dashboardFragment = new DashboardFragment();
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, dashboardFragment);
                fragmentTransaction.commit();
                break;

            case R.id.nav_dossier:
                dossierFragment = new DossierFragment();
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, dossierFragment);
                fragmentTransaction.commit();
                break;

            case R.id.nav_account:
                Intent in = new Intent(this, CompteActivity.class);
                startActivity(in);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;

            case R.id.nav_logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.confirm_dialog_title);
                builder.setMessage(R.string.logout_confirm_dialog_message);
                builder.setPositiveButton(R.string.confirm_dialog_btn_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Login Preferences
                        SharedPreferences loginPrefs = getSharedPreferences(getString(R.string.login_status_file), MODE_PRIVATE);
                        loginPrefs.edit().putString("status", getString(R.string.logged_out)).apply();
                        // Connected User Preferences
                        SharedPreferences userPrefers = getSharedPreferences(getString(R.string.connected_user_infos_file), MODE_PRIVATE);
                        SharedPreferences.Editor editor = userPrefers.edit();
                        editor.putString("lastname", null);
                        editor.putString("firstname", null);
                        editor.putString("username", null);
                        editor.putString("usermail", null);
                        editor.putString("usercell", null);
                        editor.putString("accounttype", null);
                        editor.apply();
                        // Switching to LoginActivity
                        dialogInterface.cancel();
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                    }
                });
                builder.setNegativeButton(R.string.confirm_dialog_btn_no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        drawer.openDrawer(GravityCompat.START);
                    }
                });
                builder.setCancelable(false);
                AlertDialog alert = builder.create();
                alert.show();
                Button positive = alert.getButton(DialogInterface.BUTTON_POSITIVE);
                Button negative = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
                positive.setTextColor(getResources().getColor(R.color.colorPrimary));
                negative.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}