package dev.corp.floturner.notdoc_k.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Courrier implements Serializable {

    @SerializedName("id")
    private Integer id;
    @SerializedName("expediteur")
    private String expediteur;
    @SerializedName("destinataire")
    private String destinataire;
    @SerializedName("objet")
    private String objet;
    @SerializedName("dateCourrier")
    private Date dateCourrier;
    @SerializedName("typeCourrier")
    private String typeCourrier;
    //@SerializedName("fichierB64")
    //private String fichierB64;
    @SerializedName("createdAt")
    private Date createdAt;
    @SerializedName("deletedAt")
    private Date deletedAt;
    @SerializedName("updatedAt")
    private Date updatedAt;
    @SerializedName("dossier")
    private Dossier dossier;

    public Courrier() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExpediteur() {
        return expediteur;
    }

    public void setExpediteur(String expediteur) {
        this.expediteur = expediteur;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public Date getDateCourrier() {
        return dateCourrier;
    }

    public void setDateCourrier(Date dateCourrier) {
        this.dateCourrier = dateCourrier;
    }

    public String getTypeCourrier() {
        return typeCourrier;
    }

    public void setTypeCourrier(String typeCourrier) {
        this.typeCourrier = typeCourrier;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Dossier getDossier() {
        return dossier;
    }

    public void setDossier(Dossier dossier) {
        this.dossier = dossier;
    }
}
