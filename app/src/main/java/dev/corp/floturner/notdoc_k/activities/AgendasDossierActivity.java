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
import dev.corp.floturner.notdoc_k.models.Agenda;
import dev.corp.floturner.notdoc_k.utils.Utils;

public class AgendasDossierActivity extends AppCompatActivity {
    ListView listAgendas;
    List<Agenda> allAgendasOnDossier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendas_dossier);

        listAgendas = findViewById(R.id.agendas_dossier_list);

        Intent intent = getIntent();
        if (intent != null) {
            allAgendasOnDossier = (List<Agenda>) intent.getSerializableExtra("listAgendasDossier");
            AgendaAdapter adapter = new AgendaAdapter(AgendasDossierActivity.this, allAgendasOnDossier);
            listAgendas.setAdapter(adapter);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    class AgendaAdapter extends BaseAdapter {
        Activity activity;
        List<Agenda> dataSource;
        TextView titreAg, descAg, typeAg, dateDebutAg, dateFinAg;

        AgendaAdapter(Activity activity, List<Agenda> dataSource) {
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
                v = layoutInflater.inflate(R.layout.agendas_dossier_list, viewGroup, false);

                titreAg = v.findViewById(R.id.titre_agenda);
                descAg = v.findViewById(R.id.description_agenda);
                typeAg = v.findViewById(R.id.type_agenda);
                dateDebutAg = v.findViewById(R.id.date_debut_agenda);
                dateFinAg = v.findViewById(R.id.date_fin_agenda);
            }

            Agenda agenda = dataSource.get(i);
            titreAg.setText(agenda.getTitreAgenda());
            descAg.setText(agenda.getDescAgenda());
            typeAg.setText(agenda.getTypeAgenda().getLibelle());
            dateDebutAg.setText(Utils.dateTimeFormate(agenda.getDateDebut()));
            dateFinAg.setText(Utils.dateTimeFormate(agenda.getDateFin()));

            return v;
        }
    }
}
