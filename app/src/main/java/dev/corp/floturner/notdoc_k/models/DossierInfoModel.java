package dev.corp.floturner.notdoc_k.models;

public class DossierInfoModel {

    private String title;
    private String val;

    public DossierInfoModel(String title, String val) {
        this.title = title;
        this.val = val;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
