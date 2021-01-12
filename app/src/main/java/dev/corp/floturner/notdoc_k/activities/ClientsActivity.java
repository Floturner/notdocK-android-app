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
import dev.corp.floturner.notdoc_k.models.Client;
import dev.corp.floturner.notdoc_k.utils.Utils;

public class ClientsActivity extends AppCompatActivity {
    ListView listClients;
    List<Client> allClients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clients);

        listClients = findViewById(R.id.all_clients_list);

        Intent intent = getIntent();
        if (intent != null) {
            allClients = (List<Client>) intent.getSerializableExtra("listAllClients");
            ClientAdapter adapter = new ClientAdapter(ClientsActivity.this, allClients);
            listClients.setAdapter(adapter);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    class ClientAdapter extends BaseAdapter {
        Activity activity;
        List<Client> dataSource;
        TextView nom, numPiece, type, fonction, telephone, email, adresse;

        ClientAdapter(Activity activity, List<Client> dataSource) {
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
                v = layoutInflater.inflate(R.layout.clients_dossier_list, viewGroup, false);

                nom = v.findViewById(R.id.nom_client);
                numPiece = v.findViewById(R.id.num_piece_client);
                type = v.findViewById(R.id.type_client);
                fonction = v.findViewById(R.id.fonction_client);
                telephone = v.findViewById(R.id.telephone_client);
                email = v.findViewById(R.id.email_client);
                adresse = v.findViewById(R.id.adresse_client);
            }

            Client client = dataSource.get(i);
            nom.setText(client.getNomClient() != null && client.getPrenomClient() != null ? Utils.capitalizeFirstLetter(client.getNomClient().concat(" ").concat(client.getPrenomClient())) : (client.getRaisonSocial() != null ? client.getRaisonSocial() : ""));
            numPiece.setText(client.getTypePiece() != null && client.getNumPieceId() != null ? client.getTypePiece() + " - " + client.getNumPieceId() : client.getNumEnregistrement() != null ? client.getNumEnregistrement() : "");
            type.setText(client.getTypeClient() != null ? (client.getTypeClient().equals("PP") ? "Personne Physique" : (client.getTypeClient().equals("PM") ? "Personne Morale" : "Inconnu")) : "");
            fonction.setText(client.getFonction() != null ? client.getFonction().getLibelleFonction() : "");
            telephone.setText(client.getTelClient() != null ? client.getTelClient() : "");
            email.setText(client.getEmailClient() != null ? client.getEmailClient() : "");
            adresse.setText(client.getAdresseClient() != null ? client.getAdresseClient() : "");

            return v;
        }
    }
}
