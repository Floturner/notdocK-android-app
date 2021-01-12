package dev.corp.floturner.notdoc_k.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class EtapeDefinie implements Serializable {

    @SerializedName("id")
    private Integer id;
    @SerializedName("libelleEtape")
    private String libelleEtape;
    @SerializedName("dureeEtape")
    private String dureeEtape;
    @SerializedName("createdAt")
    private Date createdAt;
    @SerializedName("deletedAt")
    private Date deletedAt;
    @SerializedName("updatedAt")
    private Date updatedAt;
    @SerializedName("sousDomaine")
    private SousDomaine sousDomaine;
    @SerializedName("etapeDefinie")
    private EtapeDefinie etapeDefinie;

    public EtapeDefinie() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelleEtape() {
        return libelleEtape;
    }

    public void setLibelleEtape(String libelleEtape) {
        this.libelleEtape = libelleEtape;
    }

    public String getDureeEtape() {
        return dureeEtape;
    }

    public void setDureeEtape(String dureeEtape) {
        this.dureeEtape = dureeEtape;
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

    public SousDomaine getSousDomaine() {
        return sousDomaine;
    }

    public void setSousDomaine(SousDomaine sousDomaine) {
        this.sousDomaine = sousDomaine;
    }

    public EtapeDefinie getEtapeDefinie() {
        return etapeDefinie;
    }

    public void setEtapeDefinie(EtapeDefinie etapeDefinie) {
        this.etapeDefinie = etapeDefinie;
    }
}
