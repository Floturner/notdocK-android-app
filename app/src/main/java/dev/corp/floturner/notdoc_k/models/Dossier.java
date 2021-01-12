package dev.corp.floturner.notdoc_k.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Dossier implements Serializable {

    @SerializedName("id")
    private Integer id;
    @SerializedName("codeDossier")
    private String codeDossier;
    @SerializedName("nomDossier")
    private String nomDossier;
    @SerializedName("descDossier")
    private String descDossier;
    @SerializedName("dateOuverture")
    private Date dateOuverture;
    @SerializedName("dateCloture")
    private Date dateCloture;
    @SerializedName("dateProbCloture")
    private Date dateProbCloture;
    @SerializedName("coutTotal")
    private Double coutTotal;
    @SerializedName("valide")
    private Boolean valide;
    @SerializedName("cloture")
    private Boolean cloture;
    @SerializedName("classe")
    private Boolean classe;
    @SerializedName("createdAt")
    private Date createdAt;
    @SerializedName("deletedAt")
    private Date deletedAt;
    @SerializedName("updatedAt")
    private Date updatedAt;
    @SerializedName("dossier")
    private Dossier dossier;
    @SerializedName("sousDomaine")
    private SousDomaine sousDomaine;

    public Dossier() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodeDossier() {
        return codeDossier;
    }

    public void setCodeDossier(String codeDossier) {
        this.codeDossier = codeDossier;
    }

    public String getNomDossier() {
        return nomDossier;
    }

    public void setNomDossier(String nomDossier) {
        this.nomDossier = nomDossier;
    }

    public String getDescDossier() {
        return descDossier;
    }

    public void setDescDossier(String descDossier) {
        this.descDossier = descDossier;
    }

    public Date getDateOuverture() {
        return dateOuverture;
    }

    public void setDateOuverture(Date dateOuverture) {
        this.dateOuverture = dateOuverture;
    }

    public Date getDateCloture() {
        return dateCloture;
    }

    public void setDateCloture(Date dateCloture) {
        this.dateCloture = dateCloture;
    }

    public Date getDateProbCloture() {
        return dateProbCloture;
    }

    public void setDateProbCloture(Date dateProbCloture) {
        this.dateProbCloture = dateProbCloture;
    }

    public Double getCoutTotal() {
        return coutTotal;
    }

    public void setCoutTotal(Double coutTotal) {
        this.coutTotal = coutTotal;
    }

    public Boolean getValide() {
        return valide;
    }

    public void setValide(Boolean valide) {
        this.valide = valide;
    }

    public Boolean getCloture() {
        return cloture;
    }

    public void setCloture(Boolean cloture) {
        this.cloture = cloture;
    }

    public Boolean getClasse() {
        return classe;
    }

    public void setClasse(Boolean classe) {
        this.classe = classe;
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

    public SousDomaine getSousDomaine() {
        return sousDomaine;
    }

    public void setSousDomaine(SousDomaine sousDomaine) {
        this.sousDomaine = sousDomaine;
    }
}
