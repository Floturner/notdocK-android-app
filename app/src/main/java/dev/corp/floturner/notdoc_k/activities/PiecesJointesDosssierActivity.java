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
import dev.corp.floturner.notdoc_k.models.PieceFournie;


public class PiecesJointesDosssierActivity extends AppCompatActivity {
    ListView listPJ;
    List<PieceFournie> allPiecesFourniesOnDossier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pieces_jointes_dosssier);

        listPJ = findViewById(R.id.pieces_jointes_dossier_list);

        Intent intent = getIntent();
        if (intent != null) {
            allPiecesFourniesOnDossier = (List<PieceFournie>) intent.getSerializableExtra("listPJsDossier");
            PieceFournieAdapter adapter = new PieceFournieAdapter(PiecesJointesDosssierActivity.this, allPiecesFourniesOnDossier);
            listPJ.setAdapter(adapter);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    class PieceFournieAdapter extends BaseAdapter {
        Activity activity;
        List<PieceFournie> dataSource;
        TextView typePJ, nomFichierPJ;

        PieceFournieAdapter(Activity activity, List<PieceFournie> dataSource) {
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
                v = layoutInflater.inflate(R.layout.pieces_jointes_dossier_list, viewGroup, false);

                typePJ = v.findViewById(R.id.type_pj);
                nomFichierPJ = v.findViewById(R.id.nom_fichier_pj);
            }

            PieceFournie pieceFournie = dataSource.get(i);
            typePJ.setText(pieceFournie.getPieceJointe().getNomFichier());
            nomFichierPJ.setText(pieceFournie.getNomFichier());

            return v;
        }
    }
}
