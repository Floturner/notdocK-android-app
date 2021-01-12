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
import dev.corp.floturner.notdoc_k.models.Dossier;
import dev.corp.floturner.notdoc_k.models.ReglementFinance;
import dev.corp.floturner.notdoc_k.utils.Utils;

public class RegFinDossierActivity extends AppCompatActivity {
    ListView listRegFin;
    List<ReglementFinance> allRegFinOnDossier;
    TextView totalPaye, resteAPayer;
    Double totPay = 0.0d, soldeDossier = 0.0d;
    Dossier dossier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_fin_dossier);

        listRegFin = findViewById(R.id.reg_fin_dossier_list);
        totalPaye = findViewById(R.id.total_paye_reg_fin);
        resteAPayer = findViewById(R.id.reste_a_payer_reg_fin);

        Intent intent = getIntent();
        if (intent != null) {
            allRegFinOnDossier = (List<ReglementFinance>) intent.getSerializableExtra("listRegFinDossier");
            dossier = allRegFinOnDossier.get(0).getDossier();

            for (ReglementFinance reg : allRegFinOnDossier) {
                totPay += reg.getMontantRegle();
            }
            soldeDossier = dossier.getCoutTotal() - totPay;
            totalPaye.setText(Utils.decimalFormat.format(totPay).concat(" FCFA"));
            resteAPayer.setText(Utils.decimalFormat.format(soldeDossier).concat(" FCFA"));

            ReglementFinanceAdapter adapter = new ReglementFinanceAdapter(RegFinDossierActivity.this, allRegFinOnDossier);
            listRegFin.setAdapter(adapter);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    class ReglementFinanceAdapter extends BaseAdapter {
        Activity activity;
        List<ReglementFinance> dataSource;
        TextView montant, date;

        ReglementFinanceAdapter(Activity activity, List<ReglementFinance> dataSource) {
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
                v = layoutInflater.inflate(R.layout.reg_fin_dossier_list, viewGroup, false);

                montant = v.findViewById(R.id.montant_reg_fin);
                date = v.findViewById(R.id.date_reg_fin);
            }

            ReglementFinance reglementFinance = dataSource.get(i);
            montant.setText(Utils.decimalFormat.format(reglementFinance.getMontantRegle()).concat(" FCFA"));
            date.setText(Utils.dateFormate(reglementFinance.getDateReglement()));

            return v;
        }
    }
}
