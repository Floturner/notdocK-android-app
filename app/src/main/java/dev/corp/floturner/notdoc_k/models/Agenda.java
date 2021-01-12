package dev.corp.floturner.notdoc_k.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Agenda implements Serializable {

    @SerializedName("id")
    private Integer id;
    @SerializedName("titreAgenda")
    private String titreAgenda;
    @SerializedName("descAgenda")
    private String descAgenda;
    @SerializedName("statutAgenda")
    private String statutAgenda;
    @SerializedName("dateDebut")
    private Date dateDebut;
    @SerializedName("dateFin")
    private Date dateFin;
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
    @SerializedName("typeAgenda")
    private TypeAgenda typeAgenda;

    public Agenda() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitreAgenda() {
        return titreAgenda;
    }

    public void setTitreAgenda(String titreAgenda) {
        this.titreAgenda = titreAgenda;
    }

    public String getDescAgenda() {
        return descAgenda;
    }

    public void setDescAgenda(String descAgenda) {
        this.descAgenda = descAgenda;
    }

    public String getStatutAgenda() {
        return statutAgenda;
    }

    public void setStatutAgenda(String statutAgenda) {
        this.statutAgenda = statutAgenda;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
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

    public TypeAgenda getTypeAgenda() {
        return typeAgenda;
    }

    public void setTypeAgenda(TypeAgenda typeAgenda) {
        this.typeAgenda = typeAgenda;
    }
}