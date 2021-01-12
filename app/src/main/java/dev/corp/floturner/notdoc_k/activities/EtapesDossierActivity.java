package dev.corp.floturner.notdoc_k.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import dev.corp.floturner.notdoc_k.R;
import dev.corp.floturner.notdoc_k.models.EtapesDossier;
import dev.corp.floturner.notdoc_k.utils.Utils;

public class EtapesDossierActivity extends AppCompatActivity {
    ListView listEtapes;
    List<EtapesDossier> allEtapesOnDossier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etapes_dossier);

        listEtapes = findViewById(R.id.etapes_dossier_list);

        Intent intent = getIntent();
        if (intent != null) {
            allEtapesOnDossier = (List<EtapesDossier>) intent.getSerializableExtra("listEtapesDossier");
            EtapeDossierAdapter adapter = new EtapeDossierAdapter(EtapesDossierActivity.this, allEtapesOnDossier);
            listEtapes.setAdapter(adapter);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    class EtapeDossierAdapter extends BaseAdapter {
        Activity activity;
        List<EtapesDossier> dataSource;
        TextView observationEtape, dateEtape, libelleEtape;

        EtapeDossierAdapter(Activity activity, List<EtapesDossier> dataSource) {
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
                v = layoutInflater.inflate(R.layout.etapes_dossier_list, viewGroup, false);

                libelleEtape = v.findViewById(R.id.libelle_etape);
                observationEtape = v.findViewById(R.id.observation_etape);
                dateEtape = v.findViewById(R.id.date_etape);
            }

            EtapesDossier etapesDossier = dataSource.get(i);
            libelleEtape.setText(etapesDossier.getEtapeDefinie().getLibelleEtape());
            observationEtape.setText(etapesDossier.getObservationEtat());
            dateEtape.setText(Utils.dateTimeFormate(etapesDossier.getDateEtape()));

            return v;
        }
    }
}
