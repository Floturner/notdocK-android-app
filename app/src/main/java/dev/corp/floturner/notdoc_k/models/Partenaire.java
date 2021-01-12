package dev.corp.floturner.notdoc_k.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Partenaire implements Serializable {
    
    @SerializedName("id")
    private Integer id;
    @SerializedName("nomPartenaire")
    private String nomPartenaire;
    @SerializedName("prenomPartenaire")
    private String prenomPartenaire;
    @SerializedName("emailPartenaire")
    private String emailPartenaire;
    @SerializedName("telPartenaire")
    private String telPartenaire;
    @SerializedName("numPieceId")
    private String numPieceId;
    @SerializedName("typePiece")
    private String typePiece;
    @SerializedName("adresseParten")
    private String adresseParten;
    @SerializedName("dateDelivPiece")
    private Date dateDelivPiece;
    @SerializedName("dateExpPiece")
    private Date dateExpPiece;
    @SerializedName("lieuDelivPiece")
    private String lieuDelivPiece;
    @SerializedName("createdAt")
    private Date createdAt;
    @SerializedName("deletedAt")
    private Date deletedAt;
    @SerializedName("updatedAt")
    private Date updatedAt;
    @SerializedName("fonction")
    private Fonction fonction;
    @SerializedName("typePartenaire")
    private TypePartenaire typePartenaire;

    public Partenaire() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomPartenaire() {
        return nomPartenaire;
    }

    public void setNomPartenaire(String nomPartenaire) {
        this.nomPartenaire = nomPartenaire;
    }

    public String getPrenomPartenaire() {
        return prenomPartenaire;
    }

    public void setPrenomPartenaire(String prenomPartenaire) {
        this.prenomPartenaire = prenomPartenaire;
    }

    public String getEmailPartenaire() {
        return emailPartenaire;
    }

    public void setEmailPartenaire(String emailPartenaire) {
        this.emailPartenaire = emailPartenaire;
    }

    public String getTelPartenaire() {
        return telPartenaire;
    }

    public void setTelPartenaire(String telPartenaire) {
        this.telPartenaire = telPartenaire;
    }

    public String getNumPieceId() {
        return numPieceId;
    }

    public void setNumPieceId(String numPieceId) {
        this.numPieceId = numPieceId;
    }

    public String getTypePiece() {
        return typePiece;
    }

    public void setTypePiece(String typePiece) {
        this.typePiece = typePiece;
    }

    public String getAdresseParten() {
        return adresseParten;
    }

    public void setAdresseParten(String adresseParten) {
        this.adresseParten = adresseParten;
    }

    public Date getDateDelivPiece() {
        return dateDelivPiece;
    }

    public void setDateDelivPiece(Date dateDelivPiece) {
        this.dateDelivPiece = dateDelivPiece;
    }

    public Date getDateExpPiece() {
        return dateExpPiece;
    }

    public void setDateExpPiece(Date dateExpPiece) {
        this.dateExpPiece = dateExpPiece;
    }

    public String getLieuDelivPiece() {
        return lieuDelivPiece;
    }

    public void setLieuDelivPiece(String lieuDelivPiece) {
        this.lieuDelivPiece = lieuDelivPiece;
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

    public Fonction getFonction() {
        return fonction;
    }

    public void setFonction(Fonction fonction) {
        this.fonction = fonction;
    }

    public TypePartenaire getTypePartenaire() {
        return typePartenaire;
    }

    public void setTypePartenaire(TypePartenaire typePartenaire) {
        this.typePartenaire = typePartenaire;
    }
}
