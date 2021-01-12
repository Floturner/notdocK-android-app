package dev.corp.floturner.notdoc_k.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Fonction implements Serializable {
    
    @SerializedName("id")
    private Integer id;
    @SerializedName("libelleFonction")
    private String libelleFonction;
    @SerializedName("descFonction")
    private String descFonction;
    @SerializedName("createdAt")
    private Date createdAt;
    @SerializedName("deletedAt")
    private Date deletedAt;
    @SerializedName("updatedAt")
    private Date updatedAt;

    public Fonction() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelleFonction() {
        return libelleFonction;
    }

    public void setLibelleFonction(String libelleFonction) {
        this.libelleFonction = libelleFonction;
    }

    public String getDescFonction() {
        return descFonction;
    }

    public void setDescFonction(String descFonction) {
        this.descFonction = descFonction;
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
}
