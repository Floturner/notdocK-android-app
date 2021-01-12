package dev.corp.floturner.notdoc_k.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import dev.corp.floturner.notdoc_k.R;
import dev.corp.floturner.notdoc_k.models.Dossier;
import dev.corp.floturner.notdoc_k.models.DossierInfoModel;
import dev.corp.floturner.notdoc_k.utils.Utils;

public class DossierInfoActivity extends AppCompatActivity {
    Dossier dossier;
    ListView listDossInfo;
    ArrayList<DossierInfoModel> allDossierInfoModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dossier_info);

        listDossInfo = findViewById(R.id.dossier_info_list);

        Intent intent = getIntent();
        if (intent != null) {
            dossier = (Dossier) intent.getSerializableExtra("dossierInfo");

            allDossierInfoModels = new ArrayList<>();
            allDossierInfoModels.add(new DossierInfoModel(getString(R.string.code_dossier), String.valueOf(dossier.getCodeDossier())));
            allDossierInfoModels.add(new DossierInfoModel(getString(R.string.nom_dossier), dossier.getNomDossier()));
            allDossierInfoModels.add(new DossierInfoModel(getString(R.string.desc_dossier), dossier.getDescDossier()));
            allDossierInfoModels.add(new DossierInfoModel(getString(R.string.date_ouv_dossier), Utils.dateFormate(dossier.getDateOuverture())));
            allDossierInfoModels.add(new DossierInfoModel(getString(R.string.date_prob_cloture_dossier), Utils.dateFormate(dossier.getDateProbCloture())));
            allDossierInfoModels.add(new DossierInfoModel(getString(R.string.cout_dossier), Utils.decimalFormat.format(dossier.getCoutTotal()).concat(" FCFA")));
            allDossierInfoModels.add(new DossierInfoModel(getString(R.string.domaine_dossier), dossier.getSousDomaine().getDomaine().getNomDomaine()));
            allDossierInfoModels.add(new DossierInfoModel(getString(R.string.sous_domaine_dossier), dossier.getSousDomaine().getNomSdomaine()));

            MyAdapter adapter = new MyAdapter(DossierInfoActivity.this, allDossierInfoModels);
            listDossInfo.setAdapter(adapter);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    class MyAdapter extends BaseAdapter {
        Activity activity;
        ArrayList<DossierInfoModel> dataSource;
        TextView txtTitle, txtVal;

        MyAdapter(Activity activity, ArrayList<DossierInfoModel> dataSource) {
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
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View v = null;
            if (inflater != null) {
                v = inflater.inflate(R.layout.info_dossier_list, viewGroup, false);
                txtTitle = v.findViewById(R.id.dos_info_title);
                txtVal = v.findViewById(R.id.dos_info_val);
            }

            DossierInfoModel dossierInfoModel = dataSource.get(i);
            txtTitle.setText(dossierInfoModel.getTitle());
            txtVal.setText(dossierInfoModel.getVal());

            return v;
        }
    }
}
