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
import dev.corp.floturner.notdoc_k.models.Partenaire;
import dev.corp.floturner.notdoc_k.utils.Utils;

public class PartenairesActivity extends AppCompatActivity {
    ListView listPartenaires;
    List<Partenaire> allPartenaires;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partenaires);

        listPartenaires = findViewById(R.id.all_partenaires_list);

        Intent intent = getIntent();
        if (intent != null) {
            allPartenaires = (List<Partenaire>) intent.getSerializableExtra("listAllPartenaires");
            PartenaireAdapter adapter = new PartenaireAdapter(PartenairesActivity.this, allPartenaires);
            listPartenaires.setAdapter(adapter);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    class PartenaireAdapter extends BaseAdapter {
        Activity activity;
        List<Partenaire> dataSource;
        TextView nom, numPiece, dateDelivPiece, dateExpPiece, lieuDelivPiece, type, fonction, telephone, email, adresse;

        PartenaireAdapter(Activity activity, List<Partenaire> dataSource) {
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
                v = layoutInflater.inflate(R.layout.partenaires_dossier_list, viewGroup, false);

                nom = v.findViewById(R.id.nom_partenaire);
                numPiece = v.findViewById(R.id.num_piece_partenaire);
                dateDelivPiece = v.findViewById(R.id.date_deliv_piece_partenaire);
                dateExpPiece = v.findViewById(R.id.date_exp_piece_partenaire);
                lieuDelivPiece = v.findViewById(R.id.lieu_deliv_piece_partenaire);
                type = v.findViewById(R.id.type_partenaire);
                fonction = v.findViewById(R.id.fonction_partenaire);
                telephone = v.findViewById(R.id.telephone_partenaire);
                email = v.findViewById(R.id.email_partenaire);
                adresse = v.findViewById(R.id.adresse_partenaire);
            }

            Partenaire partenaire = dataSource.get(i);
            nom.setText(partenaire.getNomPartenaire() != null && partenaire.getPrenomPartenaire() != null ? Utils.capitalizeFirstLetter(partenaire.getNomPartenaire().concat(" ").concat(partenaire.getPrenomPartenaire())) : "");
            numPiece.setText(partenaire.getTypePiece() != null && partenaire.getNumPieceId() != null ? partenaire.getTypePiece() + " - " + partenaire.getNumPieceId() : "");
            dateDelivPiece.setText(partenaire.getDateDelivPiece() != null ? Utils.dateFormate(partenaire.getDateDelivPiece()) : "");
            dateExpPiece.setText(partenaire.getDateExpPiece() != null ? Utils.dateFormate(partenaire.getDateExpPiece()) : "");
            lieuDelivPiece.setText(partenaire.getLieuDelivPiece() != null ? partenaire.getLieuDelivPiece() : "");
            type.setText(partenaire.getTypePartenaire() != null ? partenaire.getTypePartenaire().getLibelleTypeParten() : "");
            fonction.setText(partenaire.getFonction() != null ? partenaire.getFonction().getLibelleFonction() : "");
            telephone.setText(partenaire.getTelPartenaire() != null ? partenaire.getTelPartenaire() : "");
            email.setText(partenaire.getEmailPartenaire() != null ? partenaire.getEmailPartenaire() : "");
            adresse.setText(partenaire.getAdresseParten() != null ? partenaire.getAdresseParten() : "");

            return v;
        }
    }
}
