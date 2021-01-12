package dev.corp.floturner.notdoc_k.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import dev.corp.floturner.notdoc_k.activities.ClientsActivity;
import dev.corp.floturner.notdoc_k.activities.DossierActivity;
import dev.corp.floturner.notdoc_k.activities.PartenairesActivity;
import dev.corp.floturner.notdoc_k.R;
import dev.corp.floturner.notdoc_k.models.Client;
import dev.corp.floturner.notdoc_k.models.Dossier;
import dev.corp.floturner.notdoc_k.models.Partenaire;
import dev.corp.floturner.notdoc_k.utils.APIService;
import dev.corp.floturner.notdoc_k.utils.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DashboardFragment extends Fragment {
    APIService apiService;
    Retrofit retrofit;
    Activity context;
    SwipeRefreshLayout swipeRefreshLayout;
    TextView nbDossiersEnCours, nbDossiersNonSoldes, nbPartenaires, nbClients;
    CardView dossiersEnCoursCard, dossiersNonSolderCard, partenairesCard, clientsCard;
    List<Dossier> allDossiersEnCours;
    List<Dossier> allDossierNonSoldes;
    List<Partenaire> allPartenaires;
    List<Client> allClients;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        context = getActivity();
        if (context != null) {
            context.setTitle(R.string.nav_menu_dashboard);
        }

        retrofit = ApiClient.getClient();
        apiService = retrofit.create(APIService.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        swipeRefreshLayout = view.findViewById(R.id.dashboard_swipe_refresh);
        dossiersEnCoursCard = view.findViewById(R.id.dossiers_en_cours_card);
        dossiersNonSolderCard = view.findViewById(R.id.dossiers_non_soldes_card);
        partenairesCard = view.findViewById(R.id.all_partners_card);
        clientsCard = view.findViewById(R.id.all_clients_card);
        nbDossiersEnCours = view.findViewById(R.id.nb_dossiers_en_courrs);
        nbDossiersNonSoldes = view.findViewById(R.id.nb_dossiers_non_soldes);
        nbPartenaires = view.findViewById(R.id.nb_partenaires);
        nbClients = view.findViewById(R.id.nb_clients);

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

        dossiersEnCoursCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (allDossiersEnCours != null && allDossiersEnCours.size() != 0) {
                    Intent intent = new Intent(context, DossierActivity.class);
                    intent.putExtra("listDossiersEnCours", (Serializable) allDossiersEnCours);
                    startActivity(intent);
                    context.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
                else {
                    Snackbar.make(view, getString(R.string.nothing_to_display), Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        dossiersNonSolderCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (allDossierNonSoldes != null && allDossierNonSoldes.size() != 0) {
                    Intent intent = new Intent(context, DossierActivity.class);
                    intent.putExtra("listDossiersNonSoldes", (Serializable) allDossierNonSoldes);
                    startActivity(intent);
                    context.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
                else {
                    Snackbar.make(view, getString(R.string.nothing_to_display), Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        partenairesCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (allPartenaires != null && allPartenaires.size() != 0) {
                    Intent intent = new Intent(context, PartenairesActivity.class);
                    intent.putExtra("listAllPartenaires", (Serializable) allPartenaires);
                    startActivity(intent);
                    context.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
                else {
                    Snackbar.make(view, getString(R.string.nothing_to_display), Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        clientsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (allClients != null && allClients.size() != 0) {
                    Intent intent = new Intent(context, ClientsActivity.class);
                    intent.putExtra("listAllClients", (Serializable) allClients);
                    startActivity(intent);
                    context.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
                else {
                    Snackbar.make(view, getString(R.string.nothing_to_display), Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        swipeRefreshLayout.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light)
        );


        swipeRefreshLayout.setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getResults();
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 3000);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
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
        Call<List<Dossier>> listDossiersEnCoursCall = apiService.getDossiersEnCours();
        listDossiersEnCoursCall.enqueue(listDossiersEnCoursCallback);

        Call<List<Dossier>> listDossiersNonSoldesCall = apiService.getDossiersNonSoldes();
        listDossiersNonSoldesCall.enqueue(listDossiersNonSoldesCallback);

        Call<List<Partenaire>> listPartenairesCall = apiService.getAllPartenaires();
        listPartenairesCall.enqueue(listPartenairesCallback);

        Call<List<Client>> listClientsCall = apiService.getAllClients();
        listClientsCall.enqueue(listClientsCallback);
    }

    Callback<List<Dossier>> listDossiersEnCoursCallback = new Callback<List<Dossier>>() {
        @Override
        public void onResponse(@NonNull Call<List<Dossier>> call, @NonNull Response<List<Dossier>> response) {
            if (response.isSuccessful()) {
                allDossiersEnCours = response.body();
                if (allDossiersEnCours != null) {
                    nbDossiersEnCours.setText(String.valueOf(allDossiersEnCours.size()));
                }
                else {
                    nbDossiersEnCours.setText("0");
                }
            }
        }

        @Override
        public void onFailure(@NonNull Call<List<Dossier>> call, @NonNull Throwable t) {
            Snackbar.make(swipeRefreshLayout, R.string.network_disconnected, Snackbar.LENGTH_INDEFINITE).show();
        }
    };

    Callback<List<Dossier>> listDossiersNonSoldesCallback = new Callback<List<Dossier>>() {
        @Override
        public void onResponse(@NonNull Call<List<Dossier>> call, @NonNull Response<List<Dossier>> response) {
            if (response.isSuccessful()) {
                allDossierNonSoldes = response.body();
                if (allDossierNonSoldes != null) {
                    nbDossiersNonSoldes.setText(String.valueOf(allDossierNonSoldes.size()));
                }
                else {
                    nbDossiersNonSoldes.setText("0");
                }
            }
        }

        @Override
        public void onFailure(@NonNull Call<List<Dossier>> call, @NonNull Throwable t) {

        }
    };

    Callback<List<Partenaire>> listPartenairesCallback = new Callback<List<Partenaire>>() {
        @Override
        public void onResponse(@NonNull Call<List<Partenaire>> call, @NonNull Response<List<Partenaire>> response) {
            if (response.isSuccessful()) {
                allPartenaires = response.body();
                if (allPartenaires != null) {
                    nbPartenaires.setText(String.valueOf(allPartenaires.size()));
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

    Callback<List<Client>> listClientsCallback = new Callback<List<Client>>() {
        @Override
        public void onResponse(@NonNull Call<List<Client>> call, @NonNull Response<List<Client>> response) {
            if (response.isSuccessful()) {
                allClients = response.body();
                if (allClients != null) {
                    nbClients.setText(String.valueOf(allClients.size()));
                }
                else {
                    nbClients.setText("0");
                }
            }
        }

        @Override
        public void onFailure(@NonNull Call<List<Client>> call, @NonNull Throwable t) {

        }
    };
}
