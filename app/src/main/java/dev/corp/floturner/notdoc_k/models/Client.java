package dev.corp.floturner.notdoc_k.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Client implements Serializable {

    @SerializedName("id")
    private Integer id;
    @SerializedName("typeClient")
    private String typeClient;
    @SerializedName("nomClient")
    private String nomClient;
    @SerializedName("prenomClient")
    private String prenomClient;
    @SerializedName("sexeClient")
    private String sexeClient;
    @SerializedName("telClient")
    private String telClient;
    @SerializedName("emailClient")
    private String emailClient;
    @SerializedName("raisonSocial")
    private String raisonSocial;
    @SerializedName("numEnregistrement")
    private String numEnregistrement;
    @SerializedName("adresseClient")
    private String adresseClient;
    @SerializedName("numPieceId")
    private String numPieceId;
    @SerializedName("typePiece")
    private String typePiece;
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

    public Client() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeClient() {
        return typeClient;
    }

    public void setTypeClient(String typeClient) {
        this.typeClient = typeClient;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getPrenomClient() {
        return prenomClient;
    }

    public void setPrenomClient(String prenomClient) {
        this.prenomClient = prenomClient;
    }

    public String getSexeClient() {
        return sexeClient;
    }

    public void setSexeClient(String sexeClient) {
        this.sexeClient = sexeClient;
    }

    public String getTelClient() {
        return telClient;
    }

    public void setTelClient(String telClient) {
        this.telClient = telClient;
    }

    public String getEmailClient() {
        return emailClient;
    }

    public void setEmailClient(String emailClient) {
        this.emailClient = emailClient;
    }

    public String getRaisonSocial() {
        return raisonSocial;
    }

    public void setRaisonSocial(String raisonSocial) {
        this.raisonSocial = raisonSocial;
    }

    public String getNumEnregistrement() {
        return numEnregistrement;
    }

    public void setNumEnregistrement(String numEnregistrement) {
        this.numEnregistrement = numEnregistrement;
    }

    public String getAdresseClient() {
        return adresseClient;
    }

    public void setAdresseClient(String adresseClient) {
        this.adresseClient = adresseClient;
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
}
