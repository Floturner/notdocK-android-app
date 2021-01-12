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
import dev.corp.floturner.notdoc_k.models.Courrier;
import dev.corp.floturner.notdoc_k.utils.Utils;

public class CourriersDossierActivity extends AppCompatActivity {
    ListView listCourriers;
    List<Courrier> allCourriersOnDossier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courriers_dossier);

        listCourriers = findViewById(R.id.courriers_dossier_list);

        Intent intent = getIntent();
        if (intent != null) {
            allCourriersOnDossier = (List<Courrier>) intent.getSerializableExtra("listCourriersDossier");
            CourrierAdapter adapter = new CourrierAdapter(CourriersDossierActivity.this, allCourriersOnDossier);
            listCourriers.setAdapter(adapter);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    class CourrierAdapter extends BaseAdapter {
        Activity activity;
        List<Courrier> dataSource;
        TextView expediteur, destinataire, objet, date, type;

        CourrierAdapter(Activity activity, List<Courrier> dataSource) {
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
                v = layoutInflater.inflate(R.layout.courriers_dossier_list, viewGroup, false);

                expediteur = v.findViewById(R.id.expediteur_courrier);
                destinataire = v.findViewById(R.id.destinataire_courrier);
                objet = v.findViewById(R.id.objet_courrier);
                date = v.findViewById(R.id.date_courrier);
                type = v.findViewById(R.id.type_courrier);
            }

            Courrier courrier = dataSource.get(i);
            expediteur.setText(courrier.getExpediteur());
            destinataire.setText(String.format("à %s", courrier.getDestinataire()));
            objet.setText(courrier.getObjet());
            date.setText(Utils.dateTimeFormate(courrier.getDateCourrier()));
            type.setText(courrier.getTypeCourrier());

            return v;
        }
    }
}
