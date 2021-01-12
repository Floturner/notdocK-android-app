package dev.corp.floturner.notdoc_k.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import dev.corp.floturner.notdoc_k.activities.DetailsDossierActivity;
import dev.corp.floturner.notdoc_k.R;
import dev.corp.floturner.notdoc_k.models.Dossier;
import dev.corp.floturner.notdoc_k.utils.APIService;
import dev.corp.floturner.notdoc_k.utils.ApiClient;
import dev.corp.floturner.notdoc_k.utils.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DossierFragment extends Fragment {
    APIService apiService;
    Retrofit retrofit;
    List<Dossier> allDossiers;
    Dossier dossier;
    ListView listDossiers;
    SwipeRefreshLayout swipeRefreshLayout;
    Activity context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        context = getActivity();
        if (context != null) {
            context.setTitle(R.string.nav_menu_dossier);
        }

        retrofit = ApiClient.getClient();
        apiService = retrofit.create(APIService.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dossier, container, false);

        swipeRefreshLayout = view.findViewById(R.id.dossier_swipe_refresh);
        listDossiers = view.findViewById(R.id.dossier_list);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        addDossiers();
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

        listDossiers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                dossier = (Dossier) adapterView.getItemAtPosition(i);
                if (dossier != null) {
                    Intent intent = new Intent(context, DetailsDossierActivity.class);
                    intent.putExtra("dossierSelected", dossier);
                    context.startActivity(intent);
                    context.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
            }
        });

        swipeRefreshLayout.setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                addDossiers();
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
                        addDossiers();
                    }
                }, 3000);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void addDossiers() {
        Call<List<Dossier>> listAllDossiersCall = apiService.getAllDossiers();
        listAllDossiersCall.enqueue(listAllDossiersCallback);
    }

    Callback<List<Dossier>> listAllDossiersCallback = new Callback<List<Dossier>>() {
        @Override
        public void onResponse(@NonNull Call<List<Dossier>> call, @NonNull Response<List<Dossier>> response) {
            if (response.isSuccessful()) {
                allDossiers = response.body();
                swipeRefreshLayout.setRefreshing(false);
                if (allDossiers != null) {
                    DossierAdapter dossierAdapter = new DossierAdapter(context, allDossiers);
                    listDossiers.setAdapter(dossierAdapter);
                    Snackbar.make(swipeRefreshLayout, R.string.data_refreshed, Snackbar.LENGTH_SHORT).show();
                }
                else {
                    Snackbar.make(swipeRefreshLayout, R.string.nothing_to_display, Snackbar.LENGTH_INDEFINITE).show();
                }
            }
        }

        @Override
        public void onFailure(@NonNull Call<List<Dossier>> call, @NonNull Throwable t) {
            swipeRefreshLayout.setRefreshing(false);
            Snackbar.make(swipeRefreshLayout, R.string.network_disconnected, Snackbar.LENGTH_INDEFINITE).show();
        }
    };

    class DossierAdapter extends BaseAdapter {
        Activity activity;
        List<Dossier> dataSource;
        TextView nomDossier, dateOuvDossier, nomSousDomaine, coutDossier;

        DossierAdapter(Activity activity, List<Dossier> dataSource) {
            this.activity = activity;
            this.dataSource = dataSource;
        }

        @Override
        public int getCount() {
            return dataSource.size();
        }

        @Override
        public Object getItem(int i) {
            return dataSource.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = null;
            if (layoutInflater != null) {
                v = layoutInflater.inflate(R.layout.dossier_cell_cards, viewGroup, false);
                nomDossier = v.findViewById(R.id.nom_dossier);
                dateOuvDossier = v.findViewById(R.id.date_ouv_dossier);
                nomSousDomaine = v.findViewById(R.id.nom_sous_domaine);
                coutDossier = v.findViewById(R.id.cout_dossier);
            }

            Dossier dossier = dataSource.get(i);
            nomDossier.setText(dossier.getNomDossier() != null ? dossier.getNomDossier() : "");
            dateOuvDossier.setText(dossier.getDateOuverture() != null ? Utils.dateFormate(dossier.getDateOuverture()) : "");
            nomSousDomaine.setText(dossier.getSousDomaine() != null ? dossier.getSousDomaine().getNomSdomaine() : "");
            coutDossier.setText(dossier.getCoutTotal() != null ? Utils.decimalFormat.format(dossier.getCoutTotal()).concat(" FCFA") : "");

            return  v;
        }
    }
}
