package dev.corp.floturner.notdoc_k.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class SousDomaine implements Serializable {
    
    @SerializedName("id")
    private Integer id;
    @SerializedName("nomSdomaine")
    private String nomSdomaine;
    @SerializedName("descSdomaine")
    private String descSdomaine;
    @SerializedName("createdAt")
    private Date createdAt;
    @SerializedName("deletedAt")
    private Date deletedAt;
    @SerializedName("updatedAt")
    private Date updatedAt;
    @SerializedName("domaine")
    private Domaine domaine;

    public SousDomaine() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomSdomaine() {
        return nomSdomaine;
    }

    public void setNomSdomaine(String nomSdomaine) {
        this.nomSdomaine = nomSdomaine;
    }

    public String getDescSdomaine() {
        return descSdomaine;
    }

    public void setDescSdomaine(String descSdomaine) {
        this.descSdomaine = descSdomaine;
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

    public Domaine getDomaine() {
        return domaine;
    }

    public void setDomaine(Domaine domaine) {
        this.domaine = domaine;
    }
}
