package dev.corp.floturner.notdoc_k.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import dev.corp.floturner.notdoc_k.R;
import dev.corp.floturner.notdoc_k.models.Agenda;
import dev.corp.floturner.notdoc_k.models.Client;
import dev.corp.floturner.notdoc_k.models.Commentaire;
import dev.corp.floturner.notdoc_k.models.Courrier;
import dev.corp.floturner.notdoc_k.models.Dossier;
import dev.corp.floturner.notdoc_k.models.EtapesDossier;
import dev.corp.floturner.notdoc_k.models.Partenaire;
import dev.corp.floturner.notdoc_k.models.PieceFournie;
import dev.corp.floturner.notdoc_k.models.ReglementFinance;
import dev.corp.floturner.notdoc_k.utils.APIService;
import dev.corp.floturner.notdoc_k.utils.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DetailsDossierActivity extends AppCompatActivity {
    APIService apiService;
    Retrofit retrofit;
    Dossier dossier;
    List<Client> allClientsOnDossier;
    List<Partenaire> allPartenairesOnDossier;
    List<PieceFournie> allPieceFournieOnDossier;
    List<ReglementFinance> allRegFinOnDossier;
    List<Commentaire> allCommentsOnDossier;
    List<Agenda> allAgendasOnDossier;
    List<EtapesDossier> allEtapesDossierOnDossier;
    List<Courrier> allCourriersOnDossier;
    SwipeRefreshLayout swipeRefreshLayout;
    Button btnValid;
    TextView nomDossier, nbClients, nbPartenaires, nbComments, nbRegFin, nbPiecesJointes, nbAgendas, nbEtapesDossier, nbCourriers, nomDossierLie;
    CardView dossierCard, regFinCard, piecesJointesCard, agendasCard, courriersCard, etapesDossierCard, clientsCard, partenairesCard, commentsCard, dossierLieCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_dossier);

        swipeRefreshLayout = findViewById(R.id.detail_swipe_refresh);

        dossierCard = findViewById(R.id.info_dossier_card);
        regFinCard = findViewById(R.id.reg_fin_card);
        piecesJointesCard = findViewById(R.id.pieces_jointes_card);
        agendasCard = findViewById(R.id.agendas_card);
        courriersCard = findViewById(R.id.courriers_card);
        etapesDossierCard = findViewById(R.id.etapes_dossier_card);
        clientsCard = findViewById(R.id.clients_card);
        partenairesCard = findViewById(R.id.partenaires_card);
        commentsCard = findViewById(R.id.comments_card);
        dossierLieCard = findViewById(R.id.dossier_lie_card);

        btnValid = findViewById(R.id.btn_valid);
        nomDossier = findViewById(R.id.nom_dossier_detail);
        nomDossierLie = findViewById(R.id.nom_dossier_lie);
        nbClients = findViewById(R.id.nb_clients_detail);
        nbPartenaires = findViewById(R.id.nb_partenaires_detail);
        nbComments = findViewById(R.id.nb_comments);
        nbRegFin = findViewById(R.id.nb_reg_fin);
        nbPiecesJointes = findViewById(R.id.nb_pieces_jointes);
        nbAgendas = findViewById(R.id.nb_agendas);
        nbCourriers = findViewById(R.id.nb_courriers);
        nbEtapesDossier = findViewById(R.id.nb_etapes_dossier);

        retrofit = ApiClient.getClient();
        apiService = retrofit.create(APIService.class);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getResults();
                        swipeRefreshLayout.setRefreshing(false);
                        Snackbar.make(swipeRefreshLayout, R.string.data_refreshed, Snackbar.LENGTH_SHORT).show();
                    }
                }, 3000);
            }
        });

        swipeRefreshLayout.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light)
        );

        dossierCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsDossierActivity.this, DossierInfoActivity.class);
                intent.putExtra("dossierInfo", dossier);
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        dossierLieCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsDossierActivity.this, DetailsDossierActivity.class);
                intent.putExtra("dossierSelected", dossier.getDossier());
                startActivity(intent);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        regFinCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (allRegFinOnDossier != null && allRegFinOnDossier.size() != 0) {
                    Intent intent = new Intent(DetailsDossierActivity.this, RegFinDossierActivity.class);
                    intent.putExtra("listRegFinDossier", (Serializable) allRegFinOnDossier);
                    startActivity(intent);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
                else {
                    Snackbar.make(view, getString(R.string.nothing_to_display), Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        piecesJointesCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (allPieceFournieOnDossier != null && allPieceFournieOnDossier.size() != 0) {
                    Intent intent = new Intent(DetailsDossierActivity.this, PiecesJointesDosssierActivity.class);
                    intent.putExtra("listPJsDossier", (Serializable) allPieceFournieOnDossier);
                    startActivity(intent);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
                else {
                    Snackbar.make(view, getString(R.string.nothing_to_display), Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        agendasCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (allAgendasOnDossier != null && allAgendasOnDossier.size() != 0) {
                    Intent intent = new Intent(DetailsDossierActivity.this, AgendasDossierActivity.class);
                    intent.putExtra("listAgendasDossier", (Serializable) allAgendasOnDossier);
                    startActivity(intent);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
                else {
                    Snackbar.make(view, getString(R.string.nothing_to_display), Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        courriersCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (allCourriersOnDossier != null && allCourriersOnDossier.size() != 0) {
                    Intent intent = new Intent(DetailsDossierActivity.this, CourriersDossierActivity.class);
                    intent.putExtra("listCourriersDossier", (Serializable) allCourriersOnDossier);
                    startActivity(intent);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
                else {
                    Snackbar.make(view, getString(R.string.nothing_to_display), Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        etapesDossierCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (allEtapesDossierOnDossier != null && allEtapesDossierOnDossier.size() != 0) {
                    Intent intent = new Intent(DetailsDossierActivity.this, EtapesDossierActivity.class);
                    intent.putExtra("listEtapesDossier", (Serializable) allEtapesDossierOnDossier);
                    startActivity(intent);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
                else {
                    Snackbar.make(view, getString(R.string.nothing_to_display), Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        clientsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (allClientsOnDossier != null && allClientsOnDossier.size() != 0) {
                    Intent intent = new Intent(DetailsDossierActivity.this, ClientsDossierActivity.class);
                    intent.putExtra("listClientsDossier", (Serializable) allClientsOnDossier);
                    startActivity(intent);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
                else {
                    Snackbar.make(view, getString(R.string.nothing_to_display), Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        partenairesCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (allPartenairesOnDossier != null && allPartenairesOnDossier.size() != 0) {
                    Intent intent = new Intent(DetailsDossierActivity.this, PartenairesDossierActivity.class);
                    intent.putExtra("listPartenairesDossier", (Serializable) allPartenairesOnDossier);
                    startActivity(intent);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
                else {
                    Snackbar.make(view, getString(R.string.nothing_to_display), Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        commentsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (allCommentsOnDossier != null && allCommentsOnDossier.size() != 0) {
                    Intent intent = new Intent(DetailsDossierActivity.this, CommentsDossierActivity.class);
                    intent.putExtra("listCommentairesDossier", (Serializable) allCommentsOnDossier);
                    startActivity(intent);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
                else {
                    Snackbar.make(view, getString(R.string.nothing_to_display), Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            SharedPreferences preferences = getSharedPreferences(getString(R.string.connected_user_infos_file), MODE_PRIVATE);
            String accounttype = preferences.getString("accounttype", null);
            dossier = (Dossier) intent.getSerializableExtra("dossierSelected");
            if (!dossier.getValide()) {
                btnValid.setVisibility(View.VISIBLE);
                if (accounttype != null && !accounttype.equalsIgnoreCase("admin")) {
                    btnValid.setEnabled(false);
                }
            }
            if (dossier.getDossier() != null) {
                dossierLieCard.setVisibility(View.VISIBLE);
                nomDossierLie.setText(dossier.getDossier().getNomDossier());
            }
            nomDossier.setText(dossier.getNomDossier());
        }

        btnValid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailsDossierActivity.this);
                builder.setTitle(R.string.confirm_dialog_title);
                builder.setMessage(R.string.valid_confirm_dialog_message);
                builder.setPositiveButton(R.string.confirm_dialog_btn_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        dossier.setValide(true);
                        Call<Dossier> dossierUpdateCall = apiService.updateDossier(dossier.getId(), dossier);
                        dossierUpdateCall.enqueue(dossierUpdateCallback);
                    }
                });
                builder.setNegativeButton(R.string.confirm_dialog_btn_no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.setCancelable(false);
                AlertDialog alert = builder.create();
                alert.show();
                Button positive = alert.getButton(DialogInterface.BUTTON_POSITIVE);
                Button negative = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
                positive.setTextColor(getResources().getColor(R.color.colorPrimary));
                negative.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });

        swipeRefreshLayout.setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getResults();
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 3000);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                swipeRefreshLayout.setRefreshing(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getResults();
                        swipeRefreshLayout.setRefreshing(false);
                        Snackbar.make(swipeRefreshLayout, R.string.data_refreshed, Snackbar.LENGTH_SHORT).show();
                    }
                }, 3000);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void getResults() {
        Call<List<Partenaire>> listPartenairesOnDossierCall = apiService.getAllPartenairesByDossier(dossier.getId());
        listPartenairesOnDossierCall.enqueue(listPartenairesOnDossierCallback);

        Call<List<ReglementFinance>> listRegFinsOnDossierCall = apiService.getAllRegFinByDossier(dossier.getId());
        listRegFinsOnDossierCall.enqueue(listRegFinsOnDossierCallback);

        Call<List<PieceFournie>> listPieceFourniesOnDossierCall = apiService.getAllPiecesJointesByDossier(dossier.getId());
        listPieceFourniesOnDossierCall.enqueue(listPieceFourniesOnDossierCallback);

        Call<List<Commentaire>> listCommentsOnDossierCall = apiService.getAllCommentsByDossier(dossier.getId());
        listCommentsOnDossierCall.enqueue(listCommentsOnDossierCallback);

        Call<List<Agenda>> listAgendasOnDossierCall = apiService.getAllAgendasByDossier(dossier.getId());
        listAgendasOnDossierCall.enqueue(listAgendasOnDossierCallback);

        Call<List<EtapesDossier>> listEtapesDossierOnDossierCall = apiService.getAllEtapesDossierByDossier(dossier.getId());
        listEtapesDossierOnDossierCall.enqueue(listEtapesDossierOnDossierCallback);

        Call<List<Courrier>> listCourriersOnDossierCall = apiService.getAllCourriersByDossier(dossier.getId());
        listCourriersOnDossierCall.enqueue(listCourriersOnDossierCallback);

        Call<List<Client>> listClientsOnDossierCall = apiService.getAllClientsByDossier(dossier.getId());
        listClientsOnDossierCall.enqueue(listClientsOnDossierCallback);
    }

    Callback<Dossier> dossierUpdateCallback = new Callback<Dossier>() {
        @Override
        public void onResponse(@NonNull Call<Dossier> call, @NonNull Response<Dossier> response) {
            if (response.isSuccessful()) {
                btnValid.setVisibility(View.GONE);
                Snackbar.make(swipeRefreshLayout, R.string.dossier_valide, Snackbar.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(@NonNull Call<Dossier> call, @NonNull Throwable t) {
            Snackbar.make(swipeRefreshLayout, R.string.network_disconnected, Snackbar.LENGTH_INDEFINITE).show();
        }
    };

    Callback<List<PieceFournie>> listPieceFourniesOnDossierCallback = new Callback<List<PieceFournie>>() {
        @Override
        public void onResponse(@NonNull Call<List<PieceFournie>> call, @NonNull Response<List<PieceFournie>> response) {
            if (response.isSuccessful()) {
                allPieceFournieOnDossier = response.body();
                if (allPieceFournieOnDossier != null) {
                    nbPiecesJointes.setText(String.valueOf(allPieceFournieOnDossier.size()));
                }
                else {
                    nbPiecesJointes.setText("0");
                }
            }
        }

        @Override
        public void onFailure(@NonNull Call<List<PieceFournie>> call, @NonNull Throwable t) {

        }
    };

    Callback<List<Commentaire>> listCommentsOnDossierCallback = new Callback<List<Commentaire>>() {
        @Override
        public void onResponse(@NonNull Call<List<Commentaire>> call, @NonNull Response<List<Commentaire>> response) {
            if(response.isSuccessful()) {
                allCommentsOnDossier = response.body();
                if (allCommentsOnDossier != null) {
                    nbComments.setText(String.valueOf(allCommentsOnDossier.size()));
                }
                else {
                    nbComments.setText("0");
                }
            }
        }

        @Override
        public void onFailure(@NonNull Call<List<Commentaire>> call, @NonNull Throwable t) {

        }
    };

    Callback<List<ReglementFinance>> listRegFinsOnDossierCallback = new Callback<List<ReglementFinance>>() {
        @Override
        public void onResponse(@NonNull Call<List<ReglementFinance>> call, @NonNull Response<List<ReglementFinance>> response) {
            if (response.isSuccessful()) {
                allRegFinOnDossier = response.body();
                if (allRegFinOnDossier != null) {
                    nbRegFin.setText(String.valueOf(allRegFinOnDossier.size()));
                }
                else {
                    nbRegFin.setText("0");
                }
            }
        }

        @Override
        public void onFailure(@NonNull Call<List<ReglementFinance>> call, @NonNull Throwable t) {

        }
    };

    Callback<List<Partenaire>> listPartenairesOnDossierCallback = new Callback<List<Partenaire>>() {
        @Override
        public void onResponse(@NonNull Call<List<Partenaire>> call, @NonNull Response<List<Partenaire>> response) {
            if (response.isSuccessful()) {
                allPartenairesOnDossier = response.body();
                if (allPartenairesOnDossier != null) {
                    nbPartenaires.setText(String.valueOf(allPartenairesOnDossier.size()));
                }
                else {
                    nbPartenaires.setText("0");
                }
            }
        }

        @Override
        public void onFailure(@NonNull Call<List<Partenaire>> call, @NonNull Throwable t) {

        }
    };

    Callback<List<Client>> listClientsOnDossierCallback = new Callback<List<Client>>() {
        @Override
        public void onResponse(@NonNull Call<List<Client>> call, @NonNull Response<List<Client>> response) {
            if (response.isSuccessful()) {
                allClientsOnDossier = response.body();
                if (allClientsOnDossier != null) {
                    nbClients.setText(String.valueOf(allClientsOnDossier.size()));
                }
                else {
                    nbClients.setText("0");
                }
            }
        }

        @Override
        public void onFailure(@NonNull Call<List<Client>> call, @NonNull Throwable t) {
            Snackbar.make(swipeRefreshLayout, R.string.network_disconnected, Snackbar.LENGTH_INDEFINITE).show();
        }
    };

    Callback<List<Agenda>> listAgendasOnDossierCallback = new Callback<List<Agenda>>() {
        @Override
        public void onResponse(@NonNull Call<List<Agenda>> call, @NonNull Response<List<Agenda>> response) {
            if (response.isSuccessful()) {
                allAgendasOnDossier = response.body();
                if (allAgendasOnDossier != null) {
                    nbAgendas.setText(String.valueOf(allAgendasOnDossier.size()));
                }
                else {
                    nbAgendas.setText("0");
                }
            }
        }

        @Override
        public void onFailure(@NonNull Call<List<Agenda>> call, @NonNull Throwable t) {

        }
    };

    Callback<List<EtapesDossier>> listEtapesDossierOnDossierCallback = new Callback<List<EtapesDossier>>() {
        @Override
        public void onResponse(@NonNull Call<List<EtapesDossier>> call, @NonNull Response<List<EtapesDossier>> response) {
            if (response.isSuccessful()) {
                allEtapesDossierOnDossier = response.body();
                if (allEtapesDossierOnDossier != null) {
                    nbEtapesDossier.setText(String.valueOf(allEtapesDossierOnDossier.size()));
                }
                else {
                    nbEtapesDossier.setText("0");
                }
            }
        }

        @Override
        public void onFailure(@NonNull Call<List<EtapesDossier>> call, @NonNull Throwable t) {

        }
    };

    Callback<List<Courrier>> listCourriersOnDossierCallback = new Callback<List<Courrier>>() {
        @Override
        public void onResponse(@NonNull Call<List<Courrier>> call, @NonNull Response<List<Courrier>> response) {
            if (response.isSuccessful()) {
                allCourriersOnDossier = response.body();
                if (allCourriersOnDossier != null) {
                    nbCourriers.setText(String.valueOf(allCourriersOnDossier.size()));
                }
                else {
                    nbCourriers.setText("0");
                }
            }
        }

        @Override
        public void onFailure(@NonNull Call<List<Courrier>> call, @NonNull Throwable t) {

        }
    };
}
