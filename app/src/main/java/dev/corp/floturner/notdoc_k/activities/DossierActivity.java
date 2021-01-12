package dev.corp.floturner.notdoc_k.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import dev.corp.floturner.notdoc_k.R;
import dev.corp.floturner.notdoc_k.models.Dossier;
import dev.corp.floturner.notdoc_k.utils.Utils;

public class DossierActivity extends AppCompatActivity {
    ListView listDossiers;
    List<Dossier> allDossiers;
    Dossier dossier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dossier);

        listDossiers = findViewById(R.id.stats_dossier_list);

        listDossiers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                dossier = (Dossier) adapterView.getItemAtPosition(i);
                if (dossier != null) {
                    Intent intent = new Intent(DossierActivity.this, DetailsDossierActivity.class);
                    intent.putExtra("dossierSelected", dossier);
                    startActivity(intent);
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            if (intent.getSerializableExtra("listDossiersEnCours") != null) {
                allDossiers = (List<Dossier>) intent.getSerializableExtra("listDossiersEnCours");
                this.setTitle(R.string.dossiers_en_cours);
            }
            else if (intent.getSerializableExtra("listDossiersNonSoldes") != null) {
                allDossiers = (List<Dossier>) intent.getSerializableExtra("listDossiersNonSoldes");
                this.setTitle(R.string.dossiers_non_soldes);
            }

            DossierAdapter adapter = new DossierAdapter(DossierActivity.this, allDossiers);
            listDossiers.setAdapter(adapter);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

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
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
