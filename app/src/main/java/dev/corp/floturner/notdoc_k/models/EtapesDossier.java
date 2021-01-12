package dev.corp.floturner.notdoc_k.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class EtapesDossier implements Serializable {
    
    @SerializedName("id")
    private Integer id;
    @SerializedName("observationEtat")
    private String observationEtat;
    @SerializedName("dateEtape")
    private Date dateEtape;
    @SerializedName("createdAt")
    private Date createdAt;
    @SerializedName("deletedAt")
    private Date deletedAt;
    @SerializedName("updatedAt")
    private Date updatedAt;
    @SerializedName("dossier")
    private Dossier dossier;
    @SerializedName("etapeDefinie")
    private EtapeDefinie etapeDefinie;

    public EtapesDossier() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObservationEtat() {
        return observationEtat;
    }

    public void setObservationEtat(String observationEtat) {
        this.observationEtat = observationEtat;
    }

    public Date getDateEtape() {
        return dateEtape;
    }

    public void setDateEtape(Date dateEtape) {
        this.dateEtape = dateEtape;
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

    public EtapeDefinie getEtapeDefinie() {
        return etapeDefinie;
    }

    public void setEtapeDefinie(EtapeDefinie etapeDefinie) {
        this.etapeDefinie = etapeDefinie;
    }
}
