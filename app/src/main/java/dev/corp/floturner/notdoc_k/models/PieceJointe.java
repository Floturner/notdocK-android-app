package dev.corp.floturner.notdoc_k.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class PieceJointe implements Serializable {
    
    @SerializedName("id")
    private Integer id;
    @SerializedName("nomFichier")
    private String nomFichier;
    @SerializedName("requise")
    private Boolean requise;
    @SerializedName("createdAt")
    private Date createdAt;
    @SerializedName("deletedAt")
    private Date deletedAt;
    @SerializedName("updatedAt")
    private Date updatedAt;
    @SerializedName("sousDomaine")
    private SousDomaine sousDomaine;

    public PieceJointe() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomFichier() {
        return nomFichier;
    }

    public void setNomFichier(String nomFichier) {
        this.nomFichier = nomFichier;
    }

    public Boolean getRequise() {
        return requise;
    }

    public void setRequise(Boolean requise) {
        this.requise = requise;
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
}
