package dev.corp.floturner.notdoc_k.utils;

import com.google.gson.JsonObject;

import java.util.List;

import dev.corp.floturner.notdoc_k.models.Agenda;
import dev.corp.floturner.notdoc_k.models.Client;
import dev.corp.floturner.notdoc_k.models.Commentaire;
import dev.corp.floturner.notdoc_k.models.Courrier;
import dev.corp.floturner.notdoc_k.models.Dossier;
import dev.corp.floturner.notdoc_k.models.EtapesDossier;
import dev.corp.floturner.notdoc_k.models.Partenaire;
import dev.corp.floturner.notdoc_k.models.PieceFournie;
import dev.corp.floturner.notdoc_k.models.ReglementFinance;
import dev.corp.floturner.notdoc_k.models.Token;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIService {

    // User
    @POST("users/login")
    Call<Token> userLogin(@Body JsonObject loginString);


    // Dossiers
    @GET("dossiers")
    Call<List<Dossier>> getAllDossiers();

    @GET("dossiers/cloture/false")
    Call<List<Dossier>> getDossiersEnCours();

    @GET("dossiers/nonsolde/all")
    Call<List<Dossier>> getDossiersNonSoldes();

    @PUT("dossiers/{id}/update")
    Call<Dossier> updateDossier(@Path("id") int dossierId, @Body Dossier dossier);


    // Partenaires
    @GET("partenaires")
    Call<List<Partenaire>> getAllPartenaires();

    @GET("partenaires/ondossier/{id}")
    Call<List<Partenaire>> getAllPartenairesByDossier(@Path("id") int dossierId);


    // Clients
    @GET("clients")
    Call<List<Client>> getAllClients();

    @GET("clients/ondossier/{id}")
    Call<List<Client>> getAllClientsByDossier(@Path("id") int dossierId);


    // Règlements financiers
    @GET("regfinances/bydoss/{id}")
    Call<List<ReglementFinance>> getAllRegFinByDossier(@Path("id") int dossierId);


    // Pièces jointes
    @GET("pieces/bydossier/{id}")
    Call<List<PieceFournie>> getAllPiecesJointesByDossier(@Path("id") int dossierId);


    // Commentaires
    @GET("comments/bydoss/{id}")
    Call<List<Commentaire>> getAllCommentsByDossier(@Path("id") int dossierId);


    // Agendas
    @GET("agendas/bydoss/{id}")
    Call<List<Agenda>> getAllAgendasByDossier(@Path("id") int dossierId);


    // Etapes dossier
    @GET("dosetapes/{id}/bydossier")
    Call<List<EtapesDossier>> getAllEtapesDossierByDossier(@Path("id") int dossierId);


    // Courriers
    @GET("courriers/bydossier/{id}")
    Call<List<Courrier>> getAllCourriersByDossier(@Path("id") int dossierId);
}
