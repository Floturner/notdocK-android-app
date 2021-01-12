package dev.corp.floturner.notdoc_k.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class UserAccount implements Serializable {
    
    @SerializedName("id")
    private Integer id;
    @SerializedName("login")
    private String login;
    @SerializedName("password")
    private String password;
    @SerializedName("active")
    private Boolean active;
    @SerializedName("createdAt")
    private Date createdAt;
    @SerializedName("deletedAt")
    private Date deletedAt;
    @SerializedName("updatedAt")
    private Date updatedAt;
    @SerializedName("partenaire")
    private Partenaire partenaire;
    @SerializedName("userType")
    private UserType userType;

    public UserAccount() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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

    public Partenaire getPartenaire() {
        return partenaire;
    }

    public void setPartenaire(Partenaire partenaire) {
        this.partenaire = partenaire;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
