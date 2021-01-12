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
import dev.corp.floturner.notdoc_k.models.Commentaire;
import dev.corp.floturner.notdoc_k.utils.Utils;

public class CommentsDossierActivity extends AppCompatActivity {
    ListView listComments;
    List<Commentaire> allCommentsOnDossier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments_dossier);

        listComments = findViewById(R.id.comments_dossier_list);

        Intent intent = getIntent();
        if (intent != null) {
            allCommentsOnDossier = (List<Commentaire>) intent.getSerializableExtra("listCommentairesDossier");
            CommentaireAdapter adapter = new CommentaireAdapter(CommentsDossierActivity.this, allCommentsOnDossier);
            listComments.setAdapter(adapter);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    class CommentaireAdapter extends BaseAdapter {
        Activity activity;
        List<Commentaire> dataSource;
        TextView nomPartenaireComment, contenuComment, dateComment;

        CommentaireAdapter(Activity activity, List<Commentaire> dataSource) {
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
                v = layoutInflater.inflate(R.layout.comments_dossier_list, viewGroup, false);

                nomPartenaireComment = v.findViewById(R.id.nom_partenaire_comment);
                contenuComment = v.findViewById(R.id.contenu_comment);
                dateComment = v.findViewById(R.id.date_comment);
            }

            Commentaire commentaire = dataSource.get(i);
            nomPartenaireComment.setText(Utils.capitalizeFirstLetter(commentaire.getPartenaire().getNomPartenaire()
                    .concat(" ").concat(commentaire.getPartenaire().getPrenomPartenaire())));
            contenuComment.setText(commentaire.getContenu());
            dateComment.setText(Utils.formateDate(commentaire.getDateComment()));

            return v;
        }
    }
}
