package com.buycar.tw.model.request;

public class ProductInsertRequest {

    private String pname;
    private String pclass;
    private Integer pquantity;

    private String pdetail;
    private Integer pprice;

    private Integer psort;

    public String getPname() {return pname;}

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPclass() {
        return pclass;
    }

    public void setPclass(String pclass) {
        this.pclass = pclass;
    }

    public Integer getPquantity() {
        return pquantity;
    }

    public void setPquantity(Integer pquantity) {
        this.pquantity = pquantity;
    }

    public String getPdetail() {
        return pdetail;
    }

    public void setPdetail(String pdetail) {
        this.pdetail = pdetail;
    }

    public Integer getPprice() {
        return pprice;
    }

    public void setPprice(Integer pprice) {
        this.pprice = pprice;
    }

    public Integer getPsort() {
        return psort;
    }

    public void setPsort(Integer psort) {
        this.psort = psort;
    }

    @Override
    public String toString() {
        return "ProductInsertRequest{" +
                "pname='" + pname + '\'' +
                ", pclass='" + pclass + '\'' +
                ", pquantity=" + pquantity +
                ", pdetail='" + pdetail + '\'' +
                ", pprice=" + pprice +
                ", psort=" + psort +
                '}';
    }
}
