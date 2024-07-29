package com.example.test1.Adapter;

public class Information {

    private String codename, tex1;
    private boolean expandable;

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }

    public Information(String codename, String tex1,boolean expandable) {
        this.codename = codename;
        this.tex1 = tex1;
        this.expandable = expandable;
    }

    public String getCodename() {
        return codename;
    }

    public void setCodename(String codename) {
        this.codename = codename;
    }

    public String getTex1() {
        return tex1;
    }

    public void setTex1(String tex1) {
        this.tex1 = tex1;
    }

    @Override
    public String toString() {
        return "Information{" +
                "codename='" + codename + '\'' +
                ", tex1='" + tex1 + '\'' +
                '}';
    }
}
